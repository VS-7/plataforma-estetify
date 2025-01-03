package com.ifcolab.estetify.view;

import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JOptionPane;
import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.components.AgendaPanel;

public class FrMenu extends javax.swing.JFrame {

    private final AutenticacaoController authController;
    private final AgendaPanel agendaPanel;
    
    public FrMenu() {
        initComponents();
        this.setLocationRelativeTo(null);
        
        authController = new AutenticacaoController();
        agendaPanel = new AgendaPanel();
        
        // Configurar o painel de conteúdo
        pnlConteudo.setLayout(new BorderLayout());
        pnlConteudo.add(agendaPanel, BorderLayout.CENTER);
        
        // Configurar a sidebar
        pnlSidebar.setParentFrame(this);
        
        // Carregar consultas
        carregarConsultas();
    }
    
    private void carregarConsultas() {
        try {
            ConsultaController controller = new ConsultaController();
            List<Consulta> consultas = controller.findAll();
            agendaPanel.setConsultas(consultas);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar consultas: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        pnlConteudo = new com.ifcolab.estetify.components.AgendaPanel();
        pnlSidebar = new com.ifcolab.estetify.components.CustomSidebar();
        jLabel3 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
        setResizable(false);
        getContentPane().setLayout(null);
        getContentPane().add(pnlConteudo);
        pnlConteudo.setBounds(280, 20, 1040, 780);
        getContentPane().add(pnlSidebar);
        pnlSidebar.setBounds(0, 0, 250, 850);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private com.ifcolab.estetify.components.AgendaPanel pnlConteudo;
    private com.ifcolab.estetify.components.CustomSidebar pnlSidebar;
    // End of variables declaration//GEN-END:variables
}
