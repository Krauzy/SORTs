package sort.sorts;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Random;
import sort.Register;
import sort.SORT;

public class Files {
    private String filename;
    private RandomAccessFile file;
    private int comp;
    private int mov;
    private int cont;
    
    public Files(String filename) {
        try {
            this.cont = 0;
            this.file = new RandomAccessFile(filename, "rw");
            this.filename = filename;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Files() {
        this.cont = 0;
    }
    
    public void truncate(long pos) {
        try {
            this.file.setLength(pos * Register.length());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void copyFile(RandomAccessFile file) {
        try {
            Register R = new Register();
            int i = 0;
            int len = (int) file.length() / Register.length();
            this.file = new RandomAccessFile("temp.dat", "rw");
            this.truncate(0);
            file.seek(0);
            while(i < len) {
                R.read(file);
                R.write(this.file);
                i++;
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public RandomAccessFile getFile() {
        return this.file;
    }
    
    public boolean EOF() {
        boolean response = false;
        try {
            if(this.file.getFilePointer() == this.file.length())
                response = true;
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return response;
    }
    
    public int filesize() {
        try {
            return (int) (this.file.length() / Register.length());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    
    public void initComp() {
        this.comp = 0;
    }

    public void initMov() {
        this.mov = 0;
    }

    public int getComp() {
        return comp;
    }

    public int getMov() {
        return mov;
    }
    
    public void show() {
        Register aux = new Register();
        this.seek(0);
        while(!this.EOF()) {
            aux.read(this.file);
            aux.show();
        }
    }
    
    public void seek(int pos) {
        try {
            this.file.seek(pos * Register.length());
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void doSorted() {
        for(int i = 0; i < SORT.reg; i++) {
            Register R = new Register(i);
            R.write(this.file);
        }
    }
    
    public void doReversed() {
        for(int i = 0; i < SORT.reg; i++) {
            Register R = new Register(SORT.reg - i - 1);
            R.write(this.file);
        }
    }
    
    public void doRandom() {
        Random rand = new Random();
        for(int i = 0; i < SORT.reg; i++) {
            Register R = new Register(rand.nextInt(SORT.reg));
            R.write(this.file);
        }
    }
    
    public void SelectionSort() {
        Register aux = new Register();
        Register reg = new Register();
        int TL = this.filesize();

        for(int i = 0; i < TL - 1; i++) {
            int pos = i;
            this.seek(pos);
            reg.read(this.file);

            int j = i + 1;
            while(j < TL) {
                this.seek(j);
                aux.read(this.file);

                this.comp++;
                if(aux.getCode()< reg.getCode()) {
                    pos = j;
                    this.seek(pos);
                    reg.read(this.file);
                }
                j++;
            }

            this.seek(i);
            aux.read(this.file);

            this.mov++;
            this.seek(pos);
            aux.write(this.file);

            this.mov++;
            this.seek(i);
            reg.write(this.file);
        }
    }
    
    private void insertion(int ini, int end) {
        Register aux = new Register();
        Register reg = new Register();
        int i = ini + 1;
        int TL = end;
        int pos;
                
        while(i < TL) {
            pos = i;
            this.seek(pos - 1);
            reg.read(this.file);
            aux.read(this.file);

            this.comp++;
            while(pos > ini && aux.getCode() < reg.getCode()) {
                mov++;
                this.seek(pos);
                reg.write(this.file);

                pos--;

                this.seek(pos - 1);
                reg.read(this.file);

                this.comp++;
            }

            this.mov++;
            this.seek(pos);
            aux.write(this.file);

            i++;
        }
    }
    
    public void InsertionSort() {
        this.insertion(0, this.filesize());
    }
    
    private int BinarySearch(int ele, int ini, int TL) {
        int end = TL - 1;
        int mid = (ini + end) / 2;
        Register reg = new Register();

        this.seek(mid);
        reg.read(this.file);
        this.comp++;
        while(mid != ini && ele != reg.getCode()) {
            this.comp++;
            if(ele < reg.getCode())
                end = mid;
            else
                ini = mid;

            mid = (ini + end) / 2;
            this.seek(mid);
            reg.read(this.file);
            this.comp++;
        }
        this.comp++;
        this.seek(TL - 1);
        reg.read(this.file);
        if(ele > reg.getCode())
            return TL;
        this.comp++;
        this.seek(mid);
        reg.read(this.file);
        if(ele > reg.getCode())
            return mid + 1;
        return mid;
    }
    
    public void BinaryInsert(int ini, int end) {
        Register aux = new Register();
        Register reg = new Register();
        Register reg_pos = new Register();
        int TL = (end == -1) ? filesize() : end;
        int i = ini + 1;
        
        while(i < TL) {
            this.seek(i);
            aux.read(this.file);
            int pos = BinarySearch(aux.getCode(), ini, i);
            for(int j = i; j > pos; j--) {
                this.seek(j - 1);
                reg.read(this.file);
                reg_pos.read(this.file);
                this.mov += 2;
                this.seek(j - 1);
                reg_pos.write(this.file);
                reg.write(this.file);
            }
            i++;
        }
    }
    
    public void BubbleSort() {
        Register reg = new Register();
        Register aux = new Register();
        int TL = filesize();

        while(TL > 1) {
            int i = 0;
            while(i < TL - 1) {
                this.seek(i);
                reg.read(this.file);
                aux.read(this.file);
                this.comp++;
                if(aux.getCode() < reg.getCode()) {
                    this.mov += 2;
                    this.seek(i);
                    aux.write(this.file);
                    reg.write(this.file);
                }
                i++;
            }
            TL--;
        }
    }
    
    public void ShakeSort() {
        int ini = 0;
        int end = filesize();
        Register reg = new Register();
        Register aux = new Register();

        while(ini < end) {
            int i;
            for(i = ini; i < end - 1; i++) {
                this.seek(i);
                reg.read(this.file);
                aux.read(this.file);

                this.comp++;
                if(reg.getCode() > aux.getCode()) {
                    this.mov += 2;
                    this.seek(i);
                    aux.write(this.file);
                    reg.write(this.file);
                }
            }
            for(; i > ini; i--) {
                this.seek(i - 1);
                reg.read(this.file);
                aux.read(this.file);
                this.comp++;
                if(aux.getCode() < reg.getCode()) {
                    mov += 2;
                    this.seek(i - 1);
                    aux.write(this.file);
                    reg.write(this.file);
                }
            }
            end--;
            ini++;
        }
    }
    
    public void HeapSort() {
        Register reg = new Register();
        Register aux = new Register();
        int TL = filesize();
        int pai, fd, fe, maior;

        while(TL > 1) {
            for(pai = TL / 2 - 1; pai >= 0; pai--) {
                fe = pai + pai + 1;
                fd = fe + 1;
                maior = fe;
                this.seek(fe);
                reg.read(this.file);
                this.seek(fd);
                aux.read(this.file);                
                if(fd < TL) {
                    comp++;
                    if(aux.getCode() > reg.getCode())
                        maior = fd;
                }
                this.seek(pai);
                reg.read(this.file);
                this.seek(maior);
                aux.read(this.file);
                this.comp++;
                if(aux.getCode() > reg.getCode()) {
                    this.mov++;
                    this.seek(pai);
                    aux.write(this.file);
                    this.mov++;
                    this.seek(maior);
                    reg.write(this.file);
                }
            }
            this.seek(0);
            reg.read(this.file);
            this.seek(TL - 1);
            aux.read(this.file);
            this.mov++;
            this.seek(0);
            aux.write(this.file);
            this.mov++;
            this.seek(TL - 1);
            reg.write(this.file);
            TL--;
        }
    }
    
    public void ShellSort() {
        Register reg = new Register();
        Register aux = new Register();
        int TL = filesize();

        for(int dist = 4; dist > 0; dist /= 2) {
            for(int i = 0; i < dist; i++) {
                for(int j = i; j + dist < TL; j += dist) {
                    this.seek(j);
                    reg.read(this.file);
                    this.seek(j + dist);
                    aux.read(this.file);
                    this.comp++;
                    if(reg.getCode() > aux.getCode()) {
                        this.mov += 2;
                        this.seek(j);
                        aux.write(this.file);
                        this.seek(j + dist);
                        reg.write(this.file);
                        int k = j;
                        if(k - dist >= 0) {
                            this.comp++;
                            this.seek(k);
                            reg.read(this.file);
                            this.seek(k - dist);
                            aux.read(this.file);
                        }
                        for(; k >= 0 && reg.getCode() < aux.getCode(); k -= dist) {
                            this.mov += 2;
                            this.seek(k);
                            aux.write(this.file);
                            this.seek(k - dist);
                            reg.write(this.file);
                            if(k - dist >= 0) {
                                this.comp++;
                                this.seek(k);
                                reg.read(this.file);
                                this.seek(k - dist);
                                aux.read(this.file);
                            }
                        }
                    }
                }
            }
        }
    }
    
    //public 
}
