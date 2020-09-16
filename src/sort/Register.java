package sort;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Register {
    public final int TF = 1022;
    private int code;
    private char[] tresh;
    
    public Register() {
        this.tresh = new char[this.TF];
    }
    
    public Register(int code) {
        this.tresh = new char[this.TF];
        this.code = code;
        for (int i = 0; i < this.TF; i++)
            tresh[i] = 'X';
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getTresh() {
        return new String(this.tresh);
    }
    
    public void write(RandomAccessFile file) {
        try {
            file.writeInt(this.code);
            for (int i = 0; i < this.TF; i++)
                file.writeChar(tresh[i]);
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void read(RandomAccessFile file) {
        try {
            this.code = file.readInt();
            for (int i = 0; i < this.TF; i++)
                tresh[i] = file.readChar();
        }
        catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void show() {
        System.out.println("Code:" + this.code);
    }
    
    public static int length() {
        return 2048;
    }
}
