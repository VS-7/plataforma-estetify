package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.model.Pessoa;


public class pnlMinhasConsultas extends javax.swing.JPanel {

    private final AutenticacaoController autenticacaoController;
    private Pessoa usuario;
     
    public pnlMinhasConsultas() {
        initComponents();
        
        autenticacaoController = new AutenticacaoController();

    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblAvatar = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customTable1 = new com.ifcolab.estetify.components.CustomTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblAvatar.setForeground(new java.awt.Color(51, 51, 51));
        add(lblAvatar);
        lblAvatar.setBounds(30, 450, 170, 0);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Minhas Consultas");
        add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Personalize seu perfil ao seu gosto.");
        add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 550, 17);

        customTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(customTable1);

        add(jScrollPane1);
        jScrollPane1.setBounds(30, 70, 960, 620);
    }// </editor-fold>//GEN-END:initComponents



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.CustomTable customTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    // End of variables declaration//GEN-END:variables
}
