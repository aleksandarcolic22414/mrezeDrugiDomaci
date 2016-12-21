/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import kontroler.KontrolerServer;


/**
 *
 * @author Korisnik
 */
public class ServerGUI extends javax.swing.JFrame {

    /**
     * Creates new form Glavna
     */
    public ServerGUI() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        initComponents();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int x = JOptionPane.showConfirmDialog(null, 
                        "Da li zelite da izadjete iz chat-a?",
                            "Izlaz", JOptionPane.YES_NO_OPTION,
                                JOptionPane.INFORMATION_MESSAGE);
                if (x == JOptionPane.YES_OPTION) {
                    KontrolerServer.ugasiServer();
                    System.exit(0);
                }
            }
            
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlGlavni = new javax.swing.JPanel();
        lblPoruka = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtPoruke = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAktivniKlijenti = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Chat Server");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMinimumSize(new java.awt.Dimension(435, 270));
        setPreferredSize(new java.awt.Dimension(650, 350));
        setResizable(false);

        lblPoruka.setText("Chat:");

        txtPoruke.setEditable(false);
        txtPoruke.setColumns(20);
        txtPoruke.setRows(5);
        jScrollPane1.setViewportView(txtPoruke);

        jLabel2.setText("Aktivni klijenti:");

        txtAktivniKlijenti.setEditable(false);
        txtAktivniKlijenti.setColumns(20);
        txtAktivniKlijenti.setRows(5);
        jScrollPane2.setViewportView(txtAktivniKlijenti);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Server ///");

        javax.swing.GroupLayout pnlGlavniLayout = new javax.swing.GroupLayout(pnlGlavni);
        pnlGlavni.setLayout(pnlGlavniLayout);
        pnlGlavniLayout.setHorizontalGroup(
            pnlGlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlGlavniLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(pnlGlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPoruka, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(pnlGlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlGlavniLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlGlavniLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(32, Short.MAX_VALUE))))
            .addGroup(pnlGlavniLayout.createSequentialGroup()
                .addGap(223, 223, 223)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlGlavniLayout.setVerticalGroup(
            pnlGlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlGlavniLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlGlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPoruka, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(pnlGlavniLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(jScrollPane2))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(pnlGlavni, java.awt.BorderLayout.CENTER);

        getAccessibleContext().setAccessibleName("ChatServer");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblPoruka;
    private javax.swing.JPanel pnlGlavni;
    private javax.swing.JTextArea txtAktivniKlijenti;
    private javax.swing.JTextArea txtPoruke;
    // End of variables declaration//GEN-END:variables


    public JPanel getPnlGlavni() {
        return pnlGlavni;
    }

    public void setPnlGlavni(JPanel pnlGlavni) {
        this.pnlGlavni = pnlGlavni;
    }

    public JTextArea getTxtAktivniKlijenti() {
        return txtAktivniKlijenti;
    }

    public void setTxtAktivniKlijenti(JTextArea txtAktivniKlijenti) {
        this.txtAktivniKlijenti = txtAktivniKlijenti;
    }

    public JTextArea getTxtPoruke() {
        return txtPoruke;
    }

    public void setTxtPoruke(JTextArea txtPoruke) {
        this.txtPoruke = txtPoruke;
    }


}
