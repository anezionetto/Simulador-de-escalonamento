package Controlador;

import Modelo.ListaDupla;
import Modelo.Processo;

public class SJF {

    ListaDupla listaDupla = new ListaDupla();
    public double tempoespera;
    public double temporetorno;
    public double temporesposta;

    public SJF(ListaDupla listaDupla) {
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

    public void NoApropiativo() {
        Processo base;
        int burst = listaDupla.get(0).dado.getBurst() + listaDupla.get(0).dado.getTempo();
        for (int i = 0; i < listaDupla.size() - 1; i++) {
            base = listaDupla.get(i + 1).dado;
            for (int j = i + 1; j < listaDupla.size() - 1; j++) {

                if (listaDupla.get(j + 1).dado.getBurst() < base.getBurst()) {
                    if (listaDupla.get(j + 1).dado.getTempo() <= burst) {
                        base = listaDupla.get(j + 1).dado;
                        listaDupla.modificar(j + 1, listaDupla.get(i + 1).dado);
                        listaDupla.modificar(i + 1, base);
                    }
                } else {
                    if (listaDupla.get(j + 1).dado.getBurst() == listaDupla.get(j).dado.getBurst()) {
                        base = listaDupla.get(j).dado;
                        listaDupla.modificar(j + 1, listaDupla.get(j + 1).dado);
                        listaDupla.modificar(j, base);
                    }
                }
            }

            burst += listaDupla.get(i + 1).dado.getBurst();
        }
    }

    public double tempoEspera() {
        double t = 0;
        double r = 0;
        for (int i = 0; i < listaDupla.size(); i++) {
            if (i == 0) {
                tempoespera += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                r += t;
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = tempoespera - listaDupla.get(i).dado.getTempo();
                r += t;
                tempoespera += listaDupla.get(i).dado.getBurst();
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
                temporetorno += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo() + listaDupla.get(i).dado.getBurst();
                r += t;
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = temporetorno + listaDupla.get(i).dado.getBurst();
                r += t;
                temporetorno += listaDupla.get(i).dado.getBurst();
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
                temporesposta += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                r += t;
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = temporesposta - listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                r += t;
                temporesposta += listaDupla.get(i).dado.getBurst();
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
        l.insertarCabecera(p1);
        l.insertarInicio(p2);
        l.insertarInicio(p3);
        l.insertarInicio(p4);
        l.insertarInicio(p5);
        SJF s = new SJF(l);
        s.FCSC();
        s.NoApropiativo();
        System.out.println(s.presentar());
        System.out.println("----");
        System.out.println(s.tempoEspera());
        System.out.println("----");
        System.out.println(s.tempoResposta());
        System.out.println("----");
        System.out.println(s.tempoRetorno());
    }
}
