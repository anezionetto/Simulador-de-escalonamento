package Vista;

import Controlador.Fifo;
//import Controlador.Prioridade;
//import Controlador.RoundRobyn;
import Controlador.SJF;
import Modelo.Topico;
import Modelo.ListaDupla;
import Modelo.Processo;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Processos extends javax.swing.JFrame {

    DefaultTableModel m;
    DefaultTableModel aux;
    Vector<Object> listaAlgoritmos;
    ListaDupla listaDupla = new ListaDupla();

    public Processos() {
        initComponents();
        listaAlgoritmos = new Vector<Object>();
        tabla();
        Algoritmos();
        tablaResultados();
        bloqueo_cajas_texto();
        bloqueo_botones();
        setLocationRelativeTo(this);
        txtCuantun.setVisible(false);
        lblCuantun.setVisible(false);
    }
    Fifo f = new Fifo(listaDupla);

    public void tabla() {
        String cabecera[] = {"Processo", "burst CPU", "TempoDeChegada", "Prioridade", "Estado"};
        m = new DefaultTableModel(null, cabecera);
        tblProcesos.setModel(m);

    }

    public void bloqueo_cajas_texto() {
        txtProceso.setEnabled(false);
        txtRafaga.setEnabled(false);
        txtTiempo.setEnabled(false);
        txtPrioridad.setEnabled(false);
    }

    public void bloqueo_botones() {
        btnPrepararProceso.setEnabled(false);
        btnAceptar.setEnabled(false);
        btnAceptar.setEnabled(false);

        btnResultados.setEnabled(false);
    }

    public void limpiar_cajas_texto() {
        txtProceso.setText(null);
        txtRafaga.setText(null);
        txtTiempo.setText(null);
        txtPrioridad.setText(null);
    }

    public void tablaResultados() {
        String cabecera[] = {"Processo", "Tempo Espera", "Tempo Retorno", "Tempo de Resposta", "Estado"};
        aux = new DefaultTableModel(null, cabecera);
        tblResultados.setModel(aux);
    }

    public void Algoritmos() {
        listaAlgoritmos.add("-----Selecione-----");
        listaAlgoritmos.add("FIFO");
        listaAlgoritmos.add("SJF");
        for (int i = 0; i < listaAlgoritmos.size(); i++) {
            cbnListaAlgoritmos.addItem(listaAlgoritmos.get(i));
        }
    }
    int CPU;
    int tempo;
    int prioridade;
    int numeroProcessos = 0;

    void fifo() {

        CPU = Integer.parseInt(txtRafaga.getText());
        tempo = Integer.parseInt(txtTiempo.getText());
        prioridade = Integer.parseInt(txtPrioridad.getText());
        String estado = "Preparado";
        String Datos[] = new String[5];
        Datos[0] = txtProceso.getText();
        Datos[1] = String.valueOf(CPU);
        Datos[2] = String.valueOf(tempo);
        Datos[3] = String.valueOf(prioridade);
        Datos[4] = String.valueOf(estado);
        m.addRow(Datos);
        Processo p = new Processo(txtProceso.getText(), CPU, tempo, prioridade, estado);
        listaDupla.insertarPrincipio(p);

        jspGraficos.setViewportView(new Drawings(listaDupla.size(), listaDupla));
    }

    void SJF() {
        CPU = Integer.parseInt(txtRafaga.getText());
        tempo = Integer.parseInt(txtTiempo.getText());
        prioridade = Integer.parseInt(txtPrioridad.getText());
        String estado = "Preparado";
        String Datos[] = new String[5];
        Datos[0] = txtProceso.getText();
        Datos[1] = String.valueOf(CPU);
        Datos[2] = String.valueOf(tempo);
        Datos[3] = String.valueOf(prioridade);
        Datos[4] = String.valueOf(estado);
        m.addRow(Datos);
        Processo p = new Processo(txtProceso.getText(), CPU, tempo, prioridade, estado);
        listaDupla.insertarPrincipio(p);

        jspGraficos.setViewportView(new Drawings(listaDupla.size(), listaDupla));
    }

    public void eleicao() {
        Topico h = new Topico(jspGraficos, listaDupla);
        h.vuelta();
    }

    public void tempoProcessos_FIFO() {
        double t = 0;
        int tempoespera = 0;
        int temporesposta = 0;
        int temporetorno = 0;
        String Dados[] = new String[5];
        for (int i = 0; i < listaDupla.size(); i++) {

            Dados[0] = listaDupla.get(i).dado.getNome();
            if (i == 0) {
                tempoespera += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                Dados[1] = String.valueOf(t);
            } else {
                t = tempoespera - listaDupla.get(i).dado.getTempo();
                tempoespera += listaDupla.get(i).dado.getBurst();
                Dados[1] = String.valueOf(t);
            }
            if (i == 0) {
                temporetorno += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo() + listaDupla.get(i).dado.getBurst();
                Dados[2] = String.valueOf(t);
            } else {
                t = temporetorno + listaDupla.get(i).dado.getBurst();
                temporetorno += listaDupla.get(i).dado.getBurst();
                Dados[2] = String.valueOf(t);
            }
            if (i == 0) {
                temporesposta += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                Dados[3] = String.valueOf(t);
            } else {
                t = temporesposta - listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                temporesposta += listaDupla.get(i).dado.getBurst();
                Dados[3] = String.valueOf(t);
            }
            Dados[4] = String.valueOf("Processado");
            aux.addRow(Dados);
        }
    }

    public void tempoProcessos_JSF() {
        double t = 0;
        int tempoespera = 0;
        int temporesposta = 0;
        int temporetorno = 0;
        String Dados[] = new String[5];
        for (int i = 0; i < listaDupla.size(); i++) {

            Dados[0] = listaDupla.get(i).dado.getNome();
            if (i == 0) {
                tempoespera += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                Dados[1] = String.valueOf(t);
            } else {
                t = tempoespera - listaDupla.get(i).dado.getTempo();
                tempoespera += listaDupla.get(i).dado.getBurst();
                Dados[1] = String.valueOf(t);
            }
            if (i == 0) {
                temporetorno += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo() + listaDupla.get(i).dado.getBurst();
                Dados[2] = String.valueOf(t);
            } else {
                t = temporetorno + listaDupla.get(i).dado.getBurst();
                temporetorno += listaDupla.get(i).dado.getBurst();
                Dados[2] = String.valueOf(t);
            }
            if (i == 0) {
                temporesposta += listaDupla.get(i).dado.getBurst() + listaDupla.get(i).dado.getTempo();
                t = listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                Dados[3] = String.valueOf(t);
            } else {
                t = temporesposta - listaDupla.get(i).dado.getTempo();
                t = t + listaDupla.get(i).dado.getBurst();
                temporesposta += listaDupla.get(i).dado.getBurst();
                Dados[3] = String.valueOf(t);
            }
            Dados[4] = String.valueOf("Processado");
            aux.addRow(Dados);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtProceso = new javax.swing.JTextField();
        txtRafaga = new javax.swing.JTextField();
        txtTiempo = new javax.swing.JTextField();
        txtPrioridad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProcesos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        cbnListaAlgoritmos = new javax.swing.JComboBox();
        btnAceptar = new javax.swing.JButton();
        btnPrepararProceso = new javax.swing.JButton();
        jspGraficos = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblResultados = new javax.swing.JTable();
        btnResultados = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lblTPResp = new javax.swing.JLabel();
        lblTPE = new javax.swing.JLabel();
        lblTPRet = new javax.swing.JLabel();
        lblCuantun = new javax.swing.JLabel();
        txtCuantun = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("WenQuanYi Micro Hei", 1, 36)); // NOI18N
        jLabel1.setText("Processos ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 20, -1, -1));

        jLabel2.setText("Processo");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        jLabel3.setText("Burst");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        jLabel4.setText("Tempo de chegada");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel5.setText("Prioridade");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 130, -1, -1));
        getContentPane().add(txtProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 100, -1));
        getContentPane().add(txtRafaga, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 100, -1));
        getContentPane().add(txtTiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 110, -1));
        getContentPane().add(txtPrioridad, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 110, -1));

        tblProcesos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblProcesos);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 190, 590, 200));

        jLabel6.setText("Algoritmo");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, -1, -1));

        cbnListaAlgoritmos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnListaAlgoritmosActionPerformed(evt);
            }
        });
        getContentPane().add(cbnListaAlgoritmos, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 170, -1));

        btnAceptar.setText("Aplicar Algoritmo");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 400, 150, -1));

        btnPrepararProceso.setText("Preparar Processos");
        btnPrepararProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrepararProcesoActionPerformed(evt);
            }
        });
        getContentPane().add(btnPrepararProceso, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 80, -1, -1));
        getContentPane().add(jspGraficos, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 190, 1040, 200));

        tblResultados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblResultados);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 440, 870, 120));

        btnResultados.setText("Resultados");
        btnResultados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResultadosActionPerformed(evt);
            }
        });
        getContentPane().add(btnResultados, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 450, -1, -1));

        jButton2.setText("Finalizar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 510, 90, -1));

        jLabel7.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel7.setText("Tempo Médio");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 570, -1, -1));

        lblTPResp.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(lblTPResp, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 570, 80, 20));

        lblTPE.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(lblTPE, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 570, 80, 20));

        lblTPRet.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        getContentPane().add(lblTPRet, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 570, 80, 20));

        lblCuantun.setText("Quantidade");
        getContentPane().add(lblCuantun, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 90, -1, -1));
        getContentPane().add(txtCuantun, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 90, 100, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code
        if (cbnListaAlgoritmos.getSelectedItem().equals("FIFO")) {
            Fifo f = new Fifo(listaDupla);
            f.FCSC();
            jspGraficos.setViewportView(new Threads(listaDupla.size(), listaDupla));
        } else if (cbnListaAlgoritmos.getSelectedItem().equals("SJF")) {
            SJF a = new SJF(listaDupla);
            a.FCSC();
            a.NoApropiativo();
            jspGraficos.setViewportView(new Threads(listaDupla.size(), listaDupla));
        }
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnPrepararProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrepararProcesoActionPerformed
        // TODO add your handling code here:
        if (cbnListaAlgoritmos.getSelectedItem().equals("FIFO")) {
            if ((txtProceso.getText().equals("")) || (txtRafaga.getText().equals("")) || (txtTiempo.getText().equals("")) || (txtPrioridad.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.");
            } else {
                fifo();
                limpiar_cajas_texto();
            }
        } else if (cbnListaAlgoritmos.getSelectedItem().equals("SJF")) {
            if ((txtProceso.getText().equals("")) || (txtRafaga.getText().equals("")) || (txtTiempo.getText().equals("")) || (txtPrioridad.getText().equals(""))) {
                JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios.");
            } else {
                SJF();
                limpiar_cajas_texto();
            }
        }
    }//GEN-LAST:event_btnPrepararProcesoActionPerformed

    private void btnResultadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResultadosActionPerformed
        // TODO add your handling code here:
        if (cbnListaAlgoritmos.getSelectedItem().equals("FIFO")) {
            tempoProcessos_FIFO();
            bloqueo_botones();
            bloqueo_cajas_texto();
            Fifo f = new Fifo(listaDupla);
            f.FCSC();
            lblTPE.setText(String.valueOf(f.tempoEspera()));
            lblTPRet.setText(String.valueOf(f.tempoRetorno()));
            lblTPResp.setText(String.valueOf(f.tempoResposta()));
        } else if (cbnListaAlgoritmos.getSelectedItem().equals("SJF")) {
            tempoProcessos_JSF();
            bloqueo_botones();
            bloqueo_cajas_texto();
            SJF a = new SJF(listaDupla);
            lblTPE.setText(String.valueOf(a.tempoEspera()));
            lblTPRet.setText(String.valueOf(a.tempoRetorno()));
            lblTPResp.setText(String.valueOf(a.tempoResposta()));
        }
    }//GEN-LAST:event_btnResultadosActionPerformed

    private void cbnListaAlgoritmosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnListaAlgoritmosActionPerformed
        // TODO add your handling code here:
        if (cbnListaAlgoritmos.getSelectedItem().equals("FIFO")) {
            txtProceso.setEnabled(true);
            txtRafaga.setEnabled(true);
            txtTiempo.setEnabled(true);
            txtPrioridad.setEnabled(true);
            btnPrepararProceso.setEnabled(true);
            btnAceptar.setEnabled(true);
            btnResultados.setEnabled(true);
        } else if (cbnListaAlgoritmos.getSelectedItem().equals("SJF")) {
            txtProceso.setEnabled(true);
            txtRafaga.setEnabled(true);
            txtTiempo.setEnabled(true);
            txtPrioridad.setEnabled(true);
            btnPrepararProceso.setEnabled(true);
            btnAceptar.setEnabled(true);
            btnResultados.setEnabled(true);
        }
    }//GEN-LAST:event_cbnListaAlgoritmosActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        menu m = new menu();
        m.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Processos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Processos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Processos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Processos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Processos().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnPrepararProceso;
    private javax.swing.JButton btnResultados;
    private javax.swing.JComboBox cbnListaAlgoritmos;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JScrollPane jspGraficos;
    private javax.swing.JLabel lblCuantun;
    private javax.swing.JLabel lblTPE;
    private javax.swing.JLabel lblTPResp;
    private javax.swing.JLabel lblTPRet;
    private javax.swing.JTable tblProcesos;
    private javax.swing.JTable tblResultados;
    private javax.swing.JTextField txtCuantun;
    private javax.swing.JTextField txtPrioridad;
    private javax.swing.JTextField txtProceso;
    private javax.swing.JTextField txtRafaga;
    private javax.swing.JTextField txtTiempo;
    // End of variables declaration//GEN-END:variables
}
