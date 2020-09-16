package sort.sorts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import sort.Node;
import sort.SORT;

public class List {
    private Node init;
    private Node end;
    
    public List() {
        this.init = this.end = null;
    }
    
    public List(Node init, Node end) {
        this.init = init;
        this.end = end;
    }
    
    public void setInit(Node init) {
        this.init = init;
    }
    
    public Node getInit() {
        return this.init;
    }
    
    public Node getList() {
        return this.init;
    }
    
    public void setEnd(Node end) {
        this.end = end;
    }
    
    public Node getEnd() {
        return this.end;
    }
    
    public void insert(Node node) {
        if(this.init == null) {
            this.init = node;
            this.end = this.init;
        }
        else {
            Node aux = this.end;
            while(aux.getProx() != null)
                aux = aux.getProx();
            
            node.setAnt(aux);
            aux.setProx(node);
            this.end = node;
        }
    }
    
    public void show() {
        Node aux = this.init;
        while(aux != null) {
            if(aux.getInfo() < 10)
                System.out.println("|0" + aux.getInfo() + "|");
            else
                System.out.println("|" + aux.getInfo() + "|");
            aux = aux.getProx();
        }
        System.out.println("\n");
    }
    
    public void toSorted() {
        for(int i = 0; i < SORT.reg; i++) {
            Node node = new Node(i, null, null);
            this.insert(node);
        }
    }
    
    public void toReverse() {
        this.init = null;
        for(int i = 0; i < SORT.reg; i++) {
            Node node = new Node(SORT.reg - i - 1, null, null);
            this.insert(node);
        }
    }
    
    public void toRandom() {
        Random rand = new Random();
        this.init = null;
        for(int i = 0; i < SORT.reg; i++) {
            Node node = new Node(rand.nextInt(SORT.reg), null, null);
            this.insert(node);
        }
    }
    
    public Node getNode(int index) {
        Node node = this.init;
        while(index > 0) {
            node = node.getProx();
            index--;
        }
        return node;
    }
    
    public void InsertionSort() {
        Node i = this.init;
        Node pos;
        int aux;
        while(i != null) {
            aux = i.getInfo();
            pos = i;
            while(pos != this.init && aux < pos.getAnt().getInfo()) {
                pos.setInfo(pos.getAnt().getInfo());
                pos = pos.getAnt();
            }
            pos.setInfo(aux);
            i = i.getProx();
        }
    }
    
    public int BinarySearch(int key, int index) {
        int ini = 0;
        int end = index - 1;
        int mid = end / 2;
        while(ini < end) {
            if(key < this.getNode(mid).getInfo())
                end = mid - 1;
            else
                ini = mid + 1;
            mid = (ini + end) / 2;
        }
        if(key > this.getNode(mid).getInfo())
            return mid + 1;
        return mid;
    }
    
    public void BinaryInsertionSort() {
        int aux;
        int pos;
        for(int i = 0; i < SORT.reg; i++) {
            aux = this.getNode(i).getInfo();
            pos = this.BinarySearch(aux, i);
            for(int j = i; j < pos; j--)
                this.getNode(j).setInfo(this.getNode(j - 1).getInfo());
            this.getNode(pos).setInfo(aux);
        }
    }
    
    public void SelectionSort() {
        int menor;
        int pos;
        for(int i = 0; i < SORT.reg; i++) {
            menor = this.getNode(i).getInfo();
            pos = i;
            for(int j = i + 1; j < SORT.reg; j++) {
                if(this.getNode(j).getInfo() < menor) {
                    menor = this.getNode(j).getInfo();
                    pos = j;
                }
            }
            this.getNode(pos).setInfo(this.getNode(i).getInfo());
            this.getNode(i).setInfo(menor);
        }
    }
    
    public void BubbleSort() {
        int aux;
        int TL = SORT.reg;
        while(TL > 1) {
            for(int i = 0; i < TL - 1; i++) {
                if(this.getNode(i).getInfo() > this.getNode(i + 1).getInfo()) {
                    aux = this.getNode(i).getInfo();
                    this.getNode(i).setInfo(this.getNode(i + 1).getInfo());
                    this.getNode(i + 1).setInfo(aux);
                }
            }
            TL--;
        }
    }
    
    public void ShakeSort() {
        int aux;
        int TL = SORT.reg;
        int ini = 0;
        
        while(TL >= ini) {
            for(int i = ini; i < TL - 1; i++) {
                if(this.getNode(i).getInfo() > this.getNode(i + 1).getInfo()) {
                    aux = this.getNode(i).getInfo();
                    this.getNode(i).setInfo(this.getNode(i + 1).getInfo());
                    this.getNode(i + 1).setInfo(aux);
                }
            }
            TL--;
            for(int i = TL - 1; i < ini - 1; i--) {
                if(this.getNode(i).getInfo() < this.getNode(i - 1).getInfo()) {
                    aux = this.getNode(i).getInfo();
                    this.getNode(i).setInfo(this.getNode(i - 1).getInfo());
                    this.getNode(i - 1).setInfo(aux);
                }
            }
            ini++;
        }
    }
    
    public void ShellSort() {
        int dist = 4;
        int aux;
        while(dist > 0) {
            for (int i = 0; i < dist; i++) {
                for (int j = i; j + dist < SORT.reg; j += dist) {
                    if (this.getNode(j).getInfo() > this.getNode(j + dist).getInfo()) {
                        aux = this.getNode(j).getInfo();
                        this.getNode(j).setInfo(this.getNode(j + dist).getInfo());
                        this.getNode(j + dist).setInfo(aux);
                        int k = j;
                        while (k - dist >= 0 && this.getNode(k).getInfo() < this.getNode(k - dist).getInfo()) {
                            aux = this.getNode(k).getInfo();
                            this.getNode(k).setInfo(this.getNode(k - dist).getInfo());
                            this.getNode(k - dist).setInfo(aux);
                            k -= dist;
                        }

                    }
                }
            }
            dist /= 2;
        }
    }
    
    public void HeapSort() {
        int TL = SORT.reg;
        int pai, fe, fd, maior, aux;
        while (TL > 0) {
            pai = TL / 2 - 1;
            while (pai >= 0) {
                fe = 2 * pai + 1;
                fd = 2 * pai + 2;

                if (fd < TL && this.getNode(fd).getInfo() > this.getNode(fe).getInfo()) {
                    maior = fd;
                } else {
                    maior = fe;
                }

                if (this.getNode(maior).getInfo() > this.getNode(pai).getInfo()) {
                    aux = this.getNode(pai).getInfo();
                    this.getNode(pai).setInfo(this.getNode(maior).getInfo());
                    this.getNode(maior).setInfo(aux);
                }
                pai--;
            }
            TL--;
            aux = this.getNode(pai).getInfo();
            this.getNode(pai).setInfo(this.getNode(TL).getInfo());
            this.getNode(TL).setInfo(aux);
        }
    }
    
    public void QuickWhitoutPivet() {
        this.QuickSortWhitouPivet(0, SORT.reg - 1);
    }
    
    public void QuickSortWhitouPivet(int ini, int end) {
        int i = ini;
        int j = end;
        int aux;
        boolean flag = true;
        while (i < j) {
            if (flag) {
                while (i < j && this.getNode(i).getInfo() <= this.getNode(j).getInfo())
                    i++;
            } else {
                while (i < j && this.getNode(i).getInfo() <= this.getNode(j).getInfo())
                    j--;
            }

            flag = !flag;
            aux = this.getNode(i).getInfo();
            this.getNode(i).setInfo(this.getNode(j).getInfo());
            this.getNode(j).setInfo(aux);
        }

        if (ini < i - 1) {
            QuickSortWhitouPivet(ini, i - 1);
        }
        if (end > j + 1) {
            QuickSortWhitouPivet(j + 1, end);
        }
    }
    
    public void QuickWithPivet() {
        this.QuickSortWithPivet(0, SORT.reg - 1);
    }
    
    public void QuickSortWithPivet(int ini, int end) {
        int i = ini;
        int j = end;
        int pivet = this.getNode((i + j) / 2).getInfo();
        int aux;

        while (i <= j) {
            while (i < end && this.getNode(i).getInfo() < pivet) {
                i++;
            }
            while (j > ini && this.getNode(j).getInfo() > pivet) {
                j--;
            }

            if (i <= j) {
                aux = this.getNode(i).getInfo();
                this.getNode(i).setInfo(this.getNode(j).getInfo());
                this.getNode(j).setInfo(aux);
                i++;
                j--;
            }

        }

        if (ini < j) {
            QuickSortWithPivet(ini, j);
        }
        if (i < end) {
            QuickSortWithPivet(i, end);
        }
    }
    
    public void partition(int[] vet1, int[] vet2) {
        int i = 0, j;

        while (i < SORT.reg / 2) {
            vet1[i] = this.getNode(i).getInfo();
            i++;
        }
        j = 0;
        while (i < SORT.reg) {
            vet2[j++] = this.getNode(i).getInfo();
            i++;
        }
    }
    
    public void fusion(int[] vet1, int[] vet2, int seq) {
        int i = 0;
        int j = 0;
        int k = 0;
        int aux_seq = seq;

        while (k < SORT.reg) {
            while (i < seq && j < seq) {
                if (vet1[i] < vet2[j]) {
                    this.getNode(k++).setInfo(vet1[i++]);
                } else {
                    this.getNode(k++).setInfo(vet2[j++]);
                }
            }

            while (i < seq) {
                this.getNode(k++).setInfo(vet1[i++]);
            }

            while (j < seq) {
                this.getNode(k++).setInfo(vet2[j++]);
            }

            seq += aux_seq;
        }
    }
    
    public void merge2() {
        this.MergeSort(0, SORT.reg - 1);
    }
    
    public void merge() {
        int vet1[] = new int[SORT.reg / 2];
        int vet2[] = new int[SORT.reg / 2];
        int seq = 1;
        while (seq < SORT.reg) {
            this.partition(vet1, vet2);
            this.fusion(vet1, vet2, seq);
            seq = seq * 2;
        }
    }
    
    public void merge(int left, int mid, int right) {
        int len = mid - left + 1;
        int len_aux = right - mid;
        int vet1[] = new int[len];
        int vet2[] = new int[len_aux];

        for (int i = 0; i < len; i++) 
            vet1[i] = this.getNode(left + i).getInfo();
        for (int i = 0; i < len_aux; i++)
            vet2[i] = this.getNode(mid + 1 + i).getInfo();
        int i = 0;
        int j = 0;
        int k = left;

        while (i < len && j < len_aux) {
            if (vet1[i] < vet2[j])
                this.getNode(k++).setInfo(vet1[i++]);
            else
                this.getNode(k++).setInfo(vet2[j++]);
        }

        while (i < len) 
            this.getNode(k++).setInfo(vet1[i++]);
        while (j < len_aux)
            this.getNode(k++).setInfo(vet2[j++]);
    }
    
    public void MergeSort(int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            this.MergeSort(left, mid);
            this.MergeSort(mid + 1, right);

            this.merge(left, mid, right);
        }
    }
    
    public void CountingSort() {
        int count[] = new int[SORT.reg];
        int vet_aux[] = new int[SORT.reg];
        Node aux = this.init;

        for (int i = 0; i < SORT.reg; i++) {
            count[aux.getInfo()]++;
            aux = aux.getProx();
        }

        for (int i = 0; i < SORT.reg - 1; i++) 
            count[i + 1] += count[i];
        
        aux = this.end;
        while (aux != null) {
            vet_aux[--count[aux.getInfo()]] = aux.getInfo();
            aux = aux.getAnt();
        }

        aux = this.init;
        for (int i = 0; i < SORT.reg; i++) {
            aux.setInfo(vet_aux[i]);
            aux = aux.getProx();
        }
    }
    
    public void GnomeSort() {
        Node i = this.init;
        Node j;
        while (i != this.end) {
            if (i.getInfo() > i.getProx().getInfo()) {
                j = i.getProx();
                while (j != this.init && j.getInfo() < j.getAnt().getInfo()) {
                    int aux = j.getInfo();
                    j.setInfo(j.getAnt().getInfo());
                    j.getAnt().setInfo(aux);
                    j = j.getAnt();
                }
            }
            i = i.getProx();
        }
    }
    
    public void counting(int exp) {
        int aux[] = new int[SORT.reg];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        for (i = 0; i < SORT.reg; i++) {
            count[(this.getNode(i).getInfo() / exp) % 10]++;
        }

        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (i = SORT.reg - 1; i >= 0; i--) {
            aux[count[(this.getNode(i).getInfo() / exp) % 10] - 1] = this.getNode(i).getInfo();
            count[(this.getNode(i).getInfo() / exp) % 10]--;
        }

        for (i = 0; i < SORT.reg; i++) {
            this.getNode(i).setInfo(aux[i]);
        }
    }
    
    public void RadixSort() {
        int maior = this.getNode(0).getInfo();
        for (int i = 1; i < SORT.reg; i++) {
            if (this.getNode(i).getInfo() > maior)
                maior = this.getNode(i).getInfo();
        }
        for (int exp = 1; maior / exp > 0; exp *= 10)
            this.counting(exp);
    }
    
    public int next(int fator) {
        fator = (int) (fator * 10) / 13;
        if (fator < 1) {
            return 1;
        }
        return fator;
    }
    
    public void CombSort() {
        int n = SORT.reg;
        int fator = n;

        boolean swit = true;

        while (fator != 1 || swit == true) {
            fator = next(fator);
            swit = false;

            for (int i = 0; i < n - fator; i++) {
                if (this.getNode(i).getInfo() > this.getNode(i + fator).getInfo()) {
                    int aux = this.getNode(i).getInfo();
                    this.getNode(i).setInfo(this.getNode(i + fator).getInfo());
                    this.getNode(i + fator).setInfo(aux);
                    swit = true;
                }
            }
        }
    }
    
    public void BucketSort() {
        int TL = SORT.reg;
        int maior = SORT.reg;
        int divisor = (int) ((maior + 1) * 100) / 10;
        ArrayList<ArrayList<Integer>> lista = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            lista.add(new ArrayList<>());
        }

        for (int i = 0; i < TL; i++) {
            int j = this.getNode(i).getInfo() / divisor;
            lista.get(j).add(this.getNode(i).getInfo());
        }
        for (int i = 0; i < 10; i++) {
            Collections.sort(lista.get(i));
        }
        int pos = 0;
        for (int i = 0; i < 10; i++) {
            for (int x = 0; x < lista.get(i).size(); x++) {
                this.getNode(pos++).setInfo(lista.get(i).get(x));
            }
        }
    }
    
    public void InserTim(int left, int right) {
        int aux, j;
        for (int i = left + 1; i <= right; i++) {
            aux = this.getNode(i).getInfo();
            j = i - 1;

            while (j >= left && this.getNode(j).getInfo() > aux) {
                this.getNode(j + 1).setInfo(this.getNode(j--).getInfo());
            }

            this.getNode(j + 1).setInfo(aux);
        }
    }
    
    public void TimSort() {
        int run = 32;
        int mid;
        int right;

        for (int i = 0; i < SORT.reg; i += run) {
            this.InserTim(i, Math.min((i + 31), (SORT.reg - 1)));
        }

        for (int i = run; i < SORT.reg; i = 2 * i) {
            for (int esq = 0; esq < SORT.reg; esq += 2 * i) {
                mid = esq + i - 1;
                right = Math.min((esq + 2 * i - 1), (SORT.reg - 1));
                merge(esq, mid, right);
            }
        }
    }
}
