package Controlador;

import Modelo.ListaDupla;
import Modelo.Processo;

public class Fifo {

    ListaDupla listaDupla = new ListaDupla();
    public double tempo_Espera;
    public double tempo_Retorno;
    public double tempo_Resposta;

    public Fifo(ListaDupla listaDupla) {
        this.listaDupla = listaDupla;
    }

    public void FCSC() {
        for (int i = 1; i < listaDupla.size(); i++) {
            for (int j = 0; j < listaDupla.size() - 1; j++) {
                if (listaDupla.get(i).dado.getTempo() < listaDupla.get(j).dado.getTempo()) {
                    Processo aux = listaDupla.get(j).getDado();
                    listaDupla.modificar(j, listaDupla.get(i).dado);
                    listaDupla.modificar(i, aux);
                }
            }
        }
    }

    public double tempoEspera() {
        double t = 0;
        double r = 0;
        String resultado;
        for (int i = 0; i < listaDupla.size(); i++) {
            if (i == 0) {
                tempo_Espera += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                r += t;
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = tempo_Espera - listaDupla.get(i).dado.getTempo();
                r += t;
                tempo_Espera += listaDupla.get(i).dado.getBurst();
                System.out.println(listaDupla.get(i).dado + "=" + t);
            }

        }
        System.out.println("Tempo médio de espera.");
        return r / listaDupla.size();
    }

    public double tempoRetorno() {
        double t = 0;
        double r = 0;
        for (int i = 0; i < listaDupla.size(); i++) {
            if (i == 0) {
                tempo_Retorno += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo() + listaDupla.get(i).dado.getBurst();
                r += t;
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = tempo_Retorno + listaDupla.get(i).dado.getBurst();
                r += t;
                tempo_Retorno += listaDupla.get(i).dado.getBurst();
                System.out.println(listaDupla.get(i).dado + "=" + t);
            }

        }
        System.out.println("Tempo médio de retorno.");
        return r / listaDupla.size();
    }

    public double tempoResposta() {
        double t = 0;
        double r = 0;
        for (int i = 0; i < listaDupla.size(); i++) {
            if (i == 0) {
                tempo_Resposta += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                r += t;
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = tempo_Resposta - listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                r += t;
                tempo_Resposta += listaDupla.get(i).dado.getBurst();
                System.out.println(listaDupla.get(i).dado + "=" + t);
            }

        }
        System.out.println("Tempo médio de resposta.");
        return r / listaDupla.size();
    }

    public String presentar() {
        String resposta = "";
        for (int i = 0; i < listaDupla.size(); i++) {
            resposta += String.valueOf(listaDupla.get(i).dado);
        }
        return resposta;
    }

    public static void main(String[] args) {
        ListaDupla l = new ListaDupla();
        Processo p1 = new Processo("p1", 3, 2, 4, null);
        Processo p2 = new Processo("p2", 1, 4, 4, null);
        Processo p3 = new Processo("p3", 3, 0, 4, null);
        Processo p4 = new Processo("p4", 4, 1, 4, null);
        Processo p5 = new Processo("p5", 2, 3, 4, null);
        l.insertarPrincipio(p1);
        l.insertarPrincipio(p2);
        l.insertarPrincipio(p3);
        l.insertarPrincipio(p4);
        l.insertarPrincipio(p5);
        Fifo f = new Fifo(l);
        System.out.println(f.presentar());
        f.FCSC();
        System.out.println(f.presentar());
        System.out.println("---");
        System.out.println(f.tempoEspera());
        System.out.println("---");
        System.out.println(f.tempoRetorno());
        System.out.println("---");
        System.out.println(f.tempoResposta());
    }
}
