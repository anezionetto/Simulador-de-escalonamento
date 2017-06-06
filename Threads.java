package Vista;

import Controlador.Fifo;
import Modelo.ListaDupla;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class Threads extends javax.swing.JPanel {

    String nome;
    int cont = 0;
    ListaDupla listaDupla = new ListaDupla();
    Fifo f = new Fifo(listaDupla);

    public Threads(int cont, ListaDupla listaDupla) {
        initComponents();
        this.setOpaque(true);
        this.setSize(new Dimension(3000, 20));
        this.setBackground(Color.black);
        this.cont = cont;
        this.listaDupla = listaDupla;
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        g.setColor(Color.gray);

        int disNo = 50, aumentoNo = 120;

        int disNome = 77, aumentoNome = 120;

        for (int i = 0; i < cont; i++) {

            //g.drawImage(img.getImage(), disNodo, 35, 170, 50, this);
            g.setColor(Color.gray);
            g.fillRect(disNo, 80, 100, 50);
            disNo += aumentoNo;

            this.setSize(disNo + 200, 21);

            g.setColor(Color.black);
            g.drawString(listaDupla.get(i).dado.getNome(), disNome, 100);

//            g.setColor(Color.orange);
//            g.drawString(String.valueOf(tiempoespera), disNombre, 190);
            this.repaint();

            disNome += aumentoNome;

        }
        disNome = 50;
        aumentoNome = 115;
        int t = 0;
        double r = 0;
        int tiempoespera = 0;
        for (int j = 0; j < listaDupla.size(); j++) {
            if (j == 0) {
                tiempoespera += listaDupla.get(j).dado.getBurst() + listaDupla.get(j).dado.getTempo();
                t = listaDupla.get(j).dado.getTempo();
                g.setColor(Color.orange);
                g.drawString(String.valueOf(t), disNome, 150);
            } else {
                g.setColor(Color.orange);
                g.drawString(String.valueOf(tiempoespera), disNome, 150);
                tiempoespera += listaDupla.get(j).dado.getBurst();
            }
            disNome += aumentoNome;
        }
        g.setColor(Color.orange);
        g.drawString(String.valueOf(tiempoespera), disNome, 150);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
