package Modelo;

public class No {

    public Processo dado;
    public No seguinte;
    public No anterior;

    public No(Processo dado) {
        this.dado = dado;
        this.seguinte = null;
        this.anterior = null;
    }

    public Processo getDado() {
        return dado;
    }

    public void setDado(Processo dado) {
        this.dado = dado;
    }

    public No getAnterior() {
        return anterior;
    }

    public void setAnterior(No anterior) {
        this.anterior = anterior;
    }

}
