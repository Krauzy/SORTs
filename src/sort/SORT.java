package sort;

import java.io.File;
import java.io.IOException;
import sort.sorts.Files;
import sort.sorts.List;

public class SORT {
    public static int reg;
    private Files sorted, reversed, random, auxrev, auxrand, table;
    private int tini, tend, mov, com;
    private List sortlist, reverselist, randomlist;
    
    private void initFiles() {
        this.sorted = new Files("Ordenado.dat");
        this.reversed = new Files("Reverso.dat");
        this.random = new Files("Aleatorio.dat");
        this.table = new Files("Tabela.txt");
        this.auxrand = new Files();
        this.auxrev = new Files();
    }
    
    private void writeHeader() throws IOException {
        table.getFile().writeBytes("|MÉTODOS DE ORDENAÇÃO|ARQUIVO ORDENADO\t\t\t\t|ARQUIVO EM ORDEM REVERSA\t\t "
                + "|ARQUIVO RANDÔMICO\n");
        table.getFile().writeBytes("|\t\t     |Comp. 1\t|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|Comp. 1|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|"
                + "Comp. 1|Comp. 2|Mov. 1\t|Mov. 2\t|Tempo\t|\n");
    }
    
    private void writeTable(String name, int cp, double ce, int mp, double me, double time) throws IOException {
        table.getFile().writeBytes(name + " " + cp + "\t| " + (int) ce + "\t| " + mp + "\t| " +  (int) me + "\t| " + (int) time + "\t|");
    }
    
    private void InsertionSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.InsertionSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Insertion Sort\t     |", com, reg - 1, mov, 3 * (reg - 1) , tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.InsertionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2) + reg - 4) / 4, mov, (Math.pow(reg, 2) + (3 * reg) - 4) / 2 , tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.InsertionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2) + reg - 2) / 4, mov, (Math.pow(reg, 2) + (9 * reg) - 10) / 4, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void BinaryInsertion() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.BinaryInsert(0, -1);
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Binary Insertion    |", com, 0, mov, 3 * (reg - 1) , tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.BinaryInsert(0, -1);
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2)- reg) / 2, mov, (Math.pow(reg, 2) + 3 * reg - 4) / 2 , tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.BinaryInsert(0, -1);
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, reg * (Math.log(reg)), mov, (Math.pow(reg, 2) + 9 * reg - 10) / 4 , tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void SelectionSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.SelectionSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Select Sort\t     |", com, (Math.pow(reg, 2)- reg) / 2, mov, 3 * (reg - 1) , tend - tini);
        
        //Arquivo Reverso
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.SelectionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2)- reg) / 2, mov, Math.pow(reg, 2) / 4 + (3 * (reg - 1)) , tend - tini);
        
        //Arquivo Randômico
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.SelectionSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2)- reg) / 2, mov, reg * (Math.log((double) reg) + 0.577216f), tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void BubbleSort() throws IOException {
        //BOLHA
        //Arquivo Odernado
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.BubbleSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Bubble Sort\t     |", com, (Math.pow(reg, 2)- reg) / 2, mov, 0, tend - tini);
        
        //Arquivo Reverso
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.BubbleSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2)- reg) / 2, mov, Math.pow(reg, 2) / (4 + 3 * (reg - 1)) , tend - tini);
        
        //Arquivo Randômico
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.BubbleSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2)- reg) / 2, mov, reg * (Math.log((double) reg) + 0.577216f) , tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void ShakeSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.ShakeSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Shake Sort\t     |", com, (Math.pow(reg, 2) - reg) / 2, mov, 0 , tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.ShakeSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, 3 * (Math.pow(reg, 2) - reg) / 4, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.ShakeSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2) - reg) / 2, mov, 3 * (Math.pow(reg, 2) - reg) / 2, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void ShellSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.ShellSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Shell Sort\t     |", com, -1, mov, -1, tend - tini);
        
        //Arquivo Reverso
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.ShellSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        //Arquivo Randômico
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.ShellSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void HeapSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.HeapSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Heap Sort\t     |", com, -1, mov, -1, tend - tini);
        
        //Arquivo Reverso
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.HeapSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        //Arquivo Randômico
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.HeapSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
        
    private void QuickWithoutPivet() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.QuickWithoutPivet();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Quick Sort 1\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.QuickWithoutPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.QuickWithoutPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void QuickWithPivet() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.QuickWithPivet();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Quick Sort 2\t     |", com, -1, mov, -1, tend - tini);
        
        //Arquivo Reverso
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.QuickWithPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        //Arquivo Randômico
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.QuickWithPivet();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, (Math.pow(reg, 2)- reg) / 2, mov, reg * (Math.log((double) reg) + 0.577216f) , tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void MergeSort1() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.Merge1();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Merge 1\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.Merge1();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.Merge1();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void MergeSort2() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.Merge2();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Merge 2\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.Merge2();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        //Arquivo Randômico
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.Merge2();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void CountingSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.CountingSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|CountingSort\t     |", com, -1, mov, -1, tend - tini);
        
        //Arquivo Reverso
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.CountingSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        //Arquivo Randômico
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.CountingSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
         table.getFile().writeBytes("\n");
    }
    
    private void BucketSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.BucketSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Gnome Sort\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.BucketSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.BucketSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
   
    private void RadixSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.RadixSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Radix\t\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.RadixSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.RadixSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void CombSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.CombSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Comb\t\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.CombSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.CombSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void GnomeSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.GnomeSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Gnome Sort\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.GnomeSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.GnomeSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void TimSort() throws IOException {
        sorted.initComp();
        sorted.initMov();
        tini = (int) System.currentTimeMillis();
        sorted.TimSort();
        tend = (int) System.currentTimeMillis();
        com = sorted.getComp();
        mov = sorted.getMov();
        writeTable("|Tim Sort\t     |", com, -1, mov, -1, tend - tini);
        
        auxrev.copyFile(reversed.getFile());
        auxrev.initComp();
        auxrev.initMov();
        tini = (int) System.currentTimeMillis();
        auxrev.TimSort();
        tend = (int) System.currentTimeMillis();
        com = auxrev.getComp();
        mov = auxrev.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        
        auxrand.copyFile(random.getFile());
        auxrand.initComp();
        auxrand.initMov();
        tini = (int) System.currentTimeMillis();
        auxrand.TimSort();
        tend = (int) System.currentTimeMillis();
        com = auxrand.getComp();
        mov = auxrand.getMov();
        writeTable("", com, -1, mov, -1, tend - tini);
        table.getFile().writeBytes("\n");
    }
    
    private void initList() {
        sortlist = new List();
        reverselist = new List();
        randomlist = new List();
    }
    
    public void doList() {
        initList();
        sortlist.toSorted(); //nao utilizo pois ja esta ordenada não vi necessidade de exibi-la.
        reverselist.toReverse(); //Nao é utilizado para nao poluir a exibição, se os algoritmos ordenam uma lista randomica certamente ordenarão em ordem reversa
        
        randomlist.toRandom(); 
        System.out.println("Lista Randomica Insereção Direta: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.InsertionSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Insereção Binaria: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.BinaryInsertionSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Seleção Direta: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.SelectionSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Bubble Sort: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.BubbleSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Shake: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.ShakeSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Shell: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.ShellSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Head: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.HeapSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Quick sem Pivo: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.QuickWhitoutPivet();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Quick com Pivo: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.QuickWithPivet();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Merge 1 implementação: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.merge();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Merge 2 implementação: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.merge2();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Counting: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.CountingSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Bucket: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.BucketSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");

        randomlist.toRandom();
        System.out.println("Lista Randomica Radix: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.RadixSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Comb: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.CombSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");

        randomlist.toRandom();
        System.out.println("Lista Randomica Gnome: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.GnomeSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
        
        randomlist.toRandom();
        System.out.println("Lista Randomica Tim: ");
        System.out.print("Não ordenado = ");
        randomlist.show();
        randomlist.TimSort();
        System.out.print("Ordenado     = ");
        randomlist.show();
        System.out.println("");
    }
    
    public void deleteBack() {
        File temp;        
        temp = new File("auxMerge.dat");temp.delete();
        temp = new File("Ordenado.dat");temp.delete();
        temp = new File("Aleatorio.dat");temp.delete();
        temp = new File("Reverso.dat");temp.delete();
        temp = new File("temp.dat");temp.delete();
        temp = new File("Tabela.txt");temp.delete();
    }
    
    public void doTable() throws IOException {        
        deleteBack();
        initFiles();
        writeHeader();        
        System.out.println("Criando Arquivos");
        sorted.doSorted();
        reversed.doReversed();
        random.doRandom();        
        System.out.println("Ordenando, aguarde...");
        
        InsertionSort();
        BinaryInsertion();
        SelectionSort();
        BubbleSort();
        ShakeSort();
        ShellSort();
        HeapSort();
        QuickWithoutPivet();
        QuickWithPivet();
        MergeSort1();
        MergeSort2();
        CountingSort();
        BucketSort();
        RadixSort();
        CombSort();
        GnomeSort();
        TimSort();
    }
    
    public static void main(String[] args) throws IOException {
        SORT app = new SORT();
        reg = 1024;
        app.doTable();
        reg = 32;
        app.doList();
    }
}
