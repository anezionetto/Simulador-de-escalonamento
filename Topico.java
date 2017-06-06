package Modelo;

import Vista.Drawings;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JScrollPane;

public class Topico extends Thread {

    JScrollPane graficos;
    ListaDupla listaDupla = new ListaDupla();

    public Topico(JScrollPane graficos, ListaDupla listaDupla) {
        this.graficos = graficos;
        this.listaDupla = listaDupla;
    }

    public void vuelta() {

        try {
            graficos.setViewportView(new Drawings(listaDupla.size(), listaDupla));
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Topico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
