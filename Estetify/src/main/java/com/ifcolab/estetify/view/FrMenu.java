package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.components.AgendaPanel;

public class FrMenu extends javax.swing.JFrame {

    private final AutenticacaoController authController;
    private final AgendaPanel agendaPanel;
    
    public FrMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        authController = new AutenticacaoController();
        agendaPanel = new AgendaPanel();
        
        
        // Configurar a sidebar
        pnlSidebar.setParentFrame(this);
        
    }
    
    public void mostrarPerfil() {
        // Limpar o conteúdo atual
        getContentPane().removeAll();
        
        // Adicionar componentes
        getContentPane().add(pnlAppBar);
        getContentPane().add(pnlSidebar);
        getContentPane().add(pnlPerfil);

        getContentPane().add(lblBackground);
        
        // Atualizar a tela
        revalidate();
        repaint();
    }
    
    public void atualizarAppBar() {
        if (pnlAppBar != null) {
            pnlAppBar.atualizarInterface();
        }
    }
    


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        pnlPerfil = new com.ifcolab.estetify.view.pnlPerfil();
        pnlAppBar = new com.ifcolab.estetify.components.AppBar();
        pnlSidebar = new com.ifcolab.estetify.components.CustomSidebar();
        lblBackground = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(pnlPerfil);
        pnlPerfil.setBounds(280, 80, 1040, 730);
        getContentPane().add(pnlAppBar);
        pnlAppBar.setBounds(250, 0, 1100, 50);
        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 250, 850);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lblBackground;
    private com.ifcolab.estetify.components.AppBar pnlAppBar;
    private com.ifcolab.estetify.view.pnlPerfil pnlPerfil;
    private com.ifcolab.estetify.components.CustomSidebar pnlSidebar;
    // End of variables declaration//GEN-END:variables
}
