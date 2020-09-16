package sort;

public class Node {
    private int info;
    private Node prox;
    private Node ant;
    
    public Node() {
        this.info = 0;
        this.prox = null;
        this.ant = null;
    }
    
    public Node(int info, Node prox, Node ant) {
        this.info = info;
        this.prox = prox;
        this.ant = ant;
    }
    
    public void setInfo(int info) {
        this.info = info;
    }
    
    public int getInfo() {
        return this.info;
    }
    
    public void setProx(Node prox) {
        this.prox = prox;
    }
    
    public Node getProx() {
        return this.prox;
    }
    
    public void setAnt(Node ant) {
        this.ant = ant;
    }
    
    public Node getAnt() {
        return this.ant;
    }
    
    @Override
    public String toString() {
        return this.info + "";
    }
}
