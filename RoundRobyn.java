package Controlador;

import Modelo.ListaDupla;
import Modelo.Processo;
import javax.swing.JLabel;

public class RoundRobyn {

    ListaDupla listaDupla = new ListaDupla();
    public double tempoespera;
    public double temporetorno;
    public double temporesposta;
    public double numeroProcessosEspera;

    public RoundRobyn(ListaDupla listaDupla) {
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
            numeroProcessosEspera++;
        }
        numeroProcessosEspera++;
    }

    public void RR(int cuantun) {
        boolean a = true;
        int cpu = 0;
        int r = 0;
        while (a == true) {
            a = false;
        }
        for (int i = 0; i < listaDupla.size(); i++) {
            if (listaDupla.get(i).dado.getBurst() > cuantun) {
                r = listaDupla.get(i).dado.getBurst() - cuantun;
                listaDupla.insertarFinal(new Processo(listaDupla.get(i).dado.getNome(), listaDupla.get(i).dado.getBurst()
                        - cuantun, listaDupla.get(i).dado.getTempo(), listaDupla.get(i).dado.getPrioridade(),
                        listaDupla.get(i).dado.getEstado()));
                cpu = listaDupla.get(i).dado.getBurst() - r;
                listaDupla.get(i).dado.setBurst(cpu);
                a = true;
            }
        }
    }

    public double tempo_Espera() {
        double t = 0;
        double r = 0;
        String resultado;
        for (int i = 0; i < listaDupla.size(); i++) {
            if (i == 0) {
                tempoespera += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                r += t;
                listaDupla.get(i).dado.setTempoespera((int) t);
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = tempoespera - listaDupla.get(i).dado.getTempo();
                r += t;
                System.out.println(listaDupla.get(i).dado + "=" + tempoespera);
                listaDupla.get(i).dado.setTempoespera((int) tempoespera);
                tempoespera += listaDupla.get(i).dado.getBurst();
            }

        }
        System.out.println(" Tempo médio de espera ");
        return r;
    }

    public double tempoRetorno() {
        double t = 0;
        double r = 0;
        for (int i = 0; i < listaDupla.size(); i++) {
            if (i == 0) {
                temporetorno += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo() + listaDupla.get(i).dado.getBurst();
                t = listaDupla.get(i).dado.getTempo();
                r += t;
                listaDupla.get(i).dado.setTemporetorno((int) t);
                System.out.println(listaDupla.get(i).dado + "=" + t);
            } else {
                t = temporetorno + listaDupla.get(i).dado.getBurst();
                r += t;
                listaDupla.get(i).dado.setTemporetorno((int) temporetorno);
                System.out.println(listaDupla.get(i).dado + "=" + t);
                temporetorno += listaDupla.get(i).dado.getBurst();
            }

        }
        System.out.println(" Tempo médio de retorno ");
        return r / listaDupla.size();
    }

    public double TER(int numero) {
        int r = 0;
        String f;
        int t = 0;
        Processo d;
        int cont = 0;
        double resposta = 0;
        for (int i = 0; i < listaDupla.size() - 1; i++) {
            //System.out.println(listaDoble.get(i).dato.getNombre()+"="+listaDoble.get(i).dato.getTiempoespera());
            f = listaDupla.get(i).dado.getNome();
            d = listaDupla.get(i).dado;
            for (int j = i + 1; j < listaDupla.size(); j++) {
                if (listaDupla.get(i).dado.getNome() == listaDupla.get(j).dado.getNome()) {
                    f = listaDupla.get(j).dado.getNome();
                    d = listaDupla.get(j).dado;
                    t += listaDupla.get(j).dado.getBurst();
                }

            }
            if (listaDupla.get(i).dado.getNome() == d.getNome()) {
                t = t - d.getBurst();
                t += listaDupla.get(i).dado.getBurst();
            }
            r = d.getTempoespera() - d.getTempo() - t;
            resposta += r;
            t = 0;

            if (cont <= numeroProcessosEspera) {
                System.out.println(f + "=" + r);

            }
            cont++;
        }
//        System.out.println(" Tempo médio de espera: ");
        resposta = resposta / numero;
        return resposta;
    }

    public double TRetorno(int numero) {
        int r = 0;
        String f;
        int t = 0;
        Processo d;
        int cont = 0;
        int burst = 0;
        double resposta = 0;
        for (int i = 0; i < listaDupla.size() - 1; i++) {
            //System.out.println(listaDoble.get(i).dato.getNombre()+"="+listaDoble.get(i).dato.getTiempoespera());
            f = listaDupla.get(i).dado.getNome();
            d = listaDupla.get(i).dado;
            for (int j = i + 1; j < listaDupla.size(); j++) {
                if (listaDupla.get(i).dado.getNome() == listaDupla.get(j).dado.getNome()) {
                    f = listaDupla.get(j).dado.getNome();
                    d = listaDupla.get(j).dado;
                    t += listaDupla.get(j).dado.getBurst();
                    burst += d.getBurst();
                }

            }
            if (listaDupla.get(i).dado.getNome() == d.getNome()) {
                t = t - d.getBurst();
                t += listaDupla.get(i).dado.getBurst();
                burst += listaDupla.get(i).dado.getBurst();
                d.setBurst(burst);
            }
            r = d.getTemporetorno() - t + d.getBurst();
            resposta += r;
            t = 0;
            burst = 0;
            if (cont <= numeroProcessosEspera) {
                System.out.println(f + "=" + r);

            }
            cont++;
        }
//        System.out.println(" Tempo médio de retorno: ");
        resposta = resposta / numero;
        return resposta;
    }

    public double TResposta(int numero) {
        int r = 0;
        String f;
        int t = 0;
        Processo d;
        int cont = 0;
        int burst = 0;
        double resposta = 0;
        for (int i = 0; i < listaDupla.size() - 1; i++) {
            //System.out.println(listaDoble.get(i).dato.getNombre()+"="+listaDoble.get(i).dato.getTiempoespera());
            f = listaDupla.get(i).dado.getNome();
            d = listaDupla.get(i).dado;
            for (int j = i + 1; j < listaDupla.size(); j++) {
                if (listaDupla.get(i).dado.getNome() == listaDupla.get(j).dado.getNome()) {
                    f = listaDupla.get(j).dado.getNome();
                    d = listaDupla.get(j).dado;
                    t += listaDupla.get(j).dado.getBurst();

                }

            }
            if (listaDupla.get(i).dado.getNome() == d.getNome()) {
                t = t - d.getBurst();
                t += listaDupla.get(i).dado.getBurst();

            }
            r = d.getTempoespera() - d.getTempo() - t;
            d.setTemporesposta(r);
            r = r + d.getBurst();
            resposta += r;
            t = 0;
            burst = 0;
            if (cont <= numeroProcessosEspera) {
                System.out.println(f + "=" + r);

            }
            cont++;
        }
        System.out.println(" Tempo médio de resposta: ");
        resposta = resposta / numero;
        return resposta;

    }

    public String presentar() {
        String resposta = "";
        for (int i = 0; i < listaDupla.size(); i++) {
            resposta += String.valueOf(listaDupla.get(i).dado);
        }
        return resposta;
    }

    public void unionE(JLabel label, int numero) {
        tempo_Espera();
        label.setText(String.valueOf(TER(numero)));
    }

    public void unionRet(JLabel label, int numero) {
        tempoRetorno();
        label.setText(String.valueOf(TRetorno(numero)));
    }

    public void unionResp(JLabel label, int numero) {
        label.setText(String.valueOf(TResposta(numero)));
    }

    public static void main(String[] args) {
        ListaDupla l = new ListaDupla();
        Processo p1 = new Processo("p1", 3, 2, 1, null);
        Processo p2 = new Processo("p2", 1, 4, 1, null);
        Processo p3 = new Processo("p3", 3, 0, 2, null);
        Processo p4 = new Processo("p4", 4, 1, 3, null);
        Processo p5 = new Processo("p5", 2, 3, 4, null);
        l.insertarCabecera(p1);
        l.insertarInicio(p2);
        l.insertarInicio(p3);
        l.insertarInicio(p4);
        l.insertarInicio(p5);
        RoundRobyn r = new RoundRobyn(l);
        r.FCSC();
        r.RR(3);
        System.out.println(r.presentar());

    }
}