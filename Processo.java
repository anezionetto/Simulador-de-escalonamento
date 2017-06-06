package Modelo;

public class Processo {

    private String nome;
    private int burst;
    private int tempo;
    private int prioridade;
    private String estado;
    private int tempoespera;
    private int burstaux;
    private int temporetorno;
    private int temporesposta;

    public Processo() {
    }

    public Processo(String nome, int burst, int tempo, int prioridade, String estado) {
        this.nome = nome;
        this.burst = burst;
        this.tempo = tempo;
        this.prioridade = prioridade;
        this.estado = estado;
    }

    public String getNome() {
        return nome;
    }

    public int getBurstaux() {
        return burstaux;
    }

    public int getTemporesposta() {
        return temporesposta;
    }

    public void setTemporesposta(int temporesposta) {
        this.temporesposta = temporesposta;
    }

    public int getTemporetorno() {
        return temporetorno;
    }

    public void setTemporetorno(int temporetorno) {
        this.temporetorno = temporetorno;
    }

    public void setBurstaux(int burstaux) {
        this.burstaux = burstaux;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getBurst() {
        return burst;
    }

    public void setBurst(int burst) {
        this.burst = burst;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return getNome();
    }

    public int getTempoespera() {
        return tempoespera;
    }

    public void setTempoespera(int tempoespera) {
        this.tempoespera = tempoespera;
    }

}
