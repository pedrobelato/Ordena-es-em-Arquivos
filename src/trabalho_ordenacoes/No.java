package trabalho_ordenacoes;

public class No {
    private No Ant;
    private No Prox;
    private int info;

    public No(No Ant, int info, No Prox) {
        this.Prox = Prox;
        this.info = info;
        this.Ant = Ant;
    }

    public No getAnt() {
        return Ant;
    }

    public void setAnt(No Ant) {
        this.Ant = Ant;
    }

    public No getProx() {
        return Prox;
    }

    public void setProx(No Prox) {
        this.Prox = Prox;
    }

    public int getInfo() {
        return info;
    }

    public void setInfo(int info) {
        this.info = info;
    }
}
