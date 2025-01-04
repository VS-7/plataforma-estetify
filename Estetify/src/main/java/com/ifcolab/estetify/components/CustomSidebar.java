package com.ifcolab.estetify.components;

import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.view.DlgAgenda;
import com.ifcolab.estetify.view.DlgConfiguracaoSistema;
import com.ifcolab.estetify.view.DlgFeedback;
import com.ifcolab.estetify.view.DlgGerenciaEnfermeira;
import com.ifcolab.estetify.view.DlgGerenciaMedico;
import com.ifcolab.estetify.view.DlgGerenciaPaciente;
import com.ifcolab.estetify.view.DlgGerenciaRecepcionista;
import com.ifcolab.estetify.view.FrMenu;
import javax.swing.JFrame;

public class CustomSidebar extends javax.swing.JPanel {

    private final AutenticacaoController authController;
    private JFrame parentFrame;
    
    public CustomSidebar() {
        initComponents();

        authController = new AutenticacaoController();
    }
    
    public void setParentFrame(JFrame parent) {
        this.parentFrame = parent;
        configurarVisualizacao();
    }

     private void configurarVisualizacao() {
        // Esconde todos os botões inicialmente
        btnGerenciarMedicos.setVisible(false);
        btnGerenciarEnfermeiras.setVisible(false);
        btnGerenciarPacientes.setVisible(false);
        btnGerenciarRecepcionistas.setVisible(false);
        btnSair.setVisible(false);
        btnConfiguracoes.setVisible(false);
        btnMinhasConsultas.setVisible(false);
        btnFeedbacks.setVisible(false);
        btnFeedbacksAdmin.setVisible(false);
        btnAgenda.setVisible(false);
        btnPerfil.setVisible(false);
        
        // Mostra botões baseado no tipo de usuário
        if (authController.isAdmin()) {
            btnAgenda.setVisible(true);
            btnPerfil.setVisible(true);
            btnGerenciarMedicos.setVisible(true);
            btnGerenciarEnfermeiras.setVisible(true);
            btnGerenciarPacientes.setVisible(true);
            btnGerenciarRecepcionistas.setVisible(true);
            btnConfiguracoes.setVisible(true);
            btnFeedbacksAdmin.setVisible(true);
            btnSair.setVisible(true);
        } 
        else if (authController.isPaciente()) {
            btnMinhasConsultas.setVisible(true);
            btnFeedbacks.setVisible(true);
            btnPerfil.setVisible(true);
            btnSair.setVisible(true);
        }
        else if (authController.isMedico() || authController.isEnfermeira()) {
            btnAgenda.setVisible(true);
            btnPerfil.setVisible(true);
            btnFeedbacks.setVisible(true);
            btnSair.setVisible(true);
        }
        else if (authController.isRecepcionista()) {
            btnAgenda.setVisible(true);
            btnGerenciarPacientes.setVisible(true);
            btnPerfil.setVisible(true);
            btnFeedbacks.setVisible(true);
            btnSair.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblEstetify = new javax.swing.JLabel();
        btnAgenda = new com.ifcolab.estetify.components.CustomButton();
        btnConfiguracoes = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarPacientes = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarMedicos = new com.ifcolab.estetify.components.CustomButton();
        btnPerfil = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarEnfermeiras = new com.ifcolab.estetify.components.CustomButton();
        btnMinhasConsultas = new com.ifcolab.estetify.components.CustomButton();
        btnFeedbacks = new com.ifcolab.estetify.components.CustomButton();
        btnSair = new com.ifcolab.estetify.components.CustomButton();
        btnFeedbacksAdmin = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarRecepcionistas = new com.ifcolab.estetify.components.CustomButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo45x40.png"))); // NOI18N
        add(lblLogo);
        lblLogo.setBounds(20, 10, 50, 50);

        lblEstetify.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblEstetify.setForeground(new java.awt.Color(0, 0, 0));
        lblEstetify.setText("Estetify");
        add(lblEstetify);
        lblEstetify.setBounds(70, 30, 130, 20);

        btnAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calendar.png"))); // NOI18N
        btnAgenda.setText("Agenda");
        btnAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendaActionPerformed(evt);
            }
        });
        add(btnAgenda);
        btnAgenda.setBounds(10, 90, 230, 50);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/config.png"))); // NOI18N
        btnConfiguracoes.setText("Configurações");
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        add(btnConfiguracoes);
        btnConfiguracoes.setBounds(10, 210, 230, 50);

        btnGerenciarPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/coracao.png"))); // NOI18N
        btnGerenciarPacientes.setText("Gerenciar Pacientes");
        btnGerenciarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarPacientesActionPerformed(evt);
            }
        });
        add(btnGerenciarPacientes);
        btnGerenciarPacientes.setBounds(10, 270, 230, 50);

        btnGerenciarMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/estetoscopio.png"))); // NOI18N
        btnGerenciarMedicos.setText("Gerenciar Medicos");
        btnGerenciarMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarMedicosActionPerformed(evt);
            }
        });
        add(btnGerenciarMedicos);
        btnGerenciarMedicos.setBounds(10, 330, 230, 50);

        btnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/perfil.png"))); // NOI18N
        btnPerfil.setText("Perfil");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });
        add(btnPerfil);
        btnPerfil.setBounds(10, 150, 230, 50);

        btnGerenciarEnfermeiras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/seringa.png"))); // NOI18N
        btnGerenciarEnfermeiras.setText("Gerenciar Enfermeiras");
        btnGerenciarEnfermeiras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarEnfermeirasActionPerformed(evt);
            }
        });
        add(btnGerenciarEnfermeiras);
        btnGerenciarEnfermeiras.setBounds(10, 390, 230, 50);

        btnMinhasConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calendar.png"))); // NOI18N
        btnMinhasConsultas.setText("Minhas Consultas");
        btnMinhasConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinhasConsultasActionPerformed(evt);
            }
        });
        add(btnMinhasConsultas);
        btnMinhasConsultas.setBounds(10, 90, 230, 50);

        btnFeedbacks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/feedbacks.png"))); // NOI18N
        btnFeedbacks.setText("Feedbacks");
        btnFeedbacks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbacksActionPerformed(evt);
            }
        });
        add(btnFeedbacks);
        btnFeedbacks.setBounds(10, 210, 230, 50);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconLogout.png"))); // NOI18N
        btnSair.setText(" Sair");
        add(btnSair);
        btnSair.setBounds(10, 727, 230, 50);

        btnFeedbacksAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/feedbacks.png"))); // NOI18N
        btnFeedbacksAdmin.setText("Feedbacks");
        btnFeedbacksAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbacksAdminActionPerformed(evt);
            }
        });
        add(btnFeedbacksAdmin);
        btnFeedbacksAdmin.setBounds(10, 500, 230, 50);

        btnGerenciarRecepcionistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/recepcionista.png"))); // NOI18N
        btnGerenciarRecepcionistas.setText("Gerenciar Recepcionistas");
        btnGerenciarRecepcionistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarRecepcionistasActionPerformed(evt);
            }
        });
        add(btnGerenciarRecepcionistas);
        btnGerenciarRecepcionistas.setBounds(10, 450, 230, 50);
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfiguracoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfiguracoesActionPerformed
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarConfiguracoes();
        }
    }//GEN-LAST:event_btnConfiguracoesActionPerformed

    private void btnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPerfilActionPerformed
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarPerfil();
        }
    }//GEN-LAST:event_btnPerfilActionPerformed

    private void btnFeedbacksActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbacksActionPerformed
        DlgFeedback dialog = new DlgFeedback(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnFeedbacksActionPerformed

    private void btnGerenciarMedicosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarMedicosActionPerformed
        DlgGerenciaMedico dialog = new DlgGerenciaMedico(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarMedicosActionPerformed

    private void btnGerenciarPacientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarPacientesActionPerformed
        DlgGerenciaPaciente dialog = new DlgGerenciaPaciente(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarPacientesActionPerformed

    private void btnGerenciarEnfermeirasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarEnfermeirasActionPerformed
        DlgGerenciaEnfermeira dialog = new DlgGerenciaEnfermeira(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarEnfermeirasActionPerformed

    private void btnAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendaActionPerformed
       /* DlgAgenda dialog = new DlgAgenda(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);*/
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarAgenda();
        }
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnFeedbacksAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbacksAdminActionPerformed
        DlgFeedback dialog = new DlgFeedback(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnFeedbacksAdminActionPerformed

    private void btnGerenciarRecepcionistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarRecepcionistasActionPerformed
        DlgGerenciaRecepcionista dialog = new DlgGerenciaRecepcionista(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarRecepcionistasActionPerformed

    private void btnMinhasConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinhasConsultasActionPerformed
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarMinhasConsultas();
        }
    }//GEN-LAST:event_btnMinhasConsultasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.CustomButton btnAgenda;
    private com.ifcolab.estetify.components.CustomButton btnConfiguracoes;
    private com.ifcolab.estetify.components.CustomButton btnFeedbacks;
    private com.ifcolab.estetify.components.CustomButton btnFeedbacksAdmin;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarEnfermeiras;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarMedicos;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarPacientes;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarRecepcionistas;
    private com.ifcolab.estetify.components.CustomButton btnMinhasConsultas;
    private com.ifcolab.estetify.components.CustomButton btnPerfil;
    private com.ifcolab.estetify.components.CustomButton btnSair;
    private javax.swing.JLabel lblEstetify;
    private javax.swing.JLabel lblLogo;
    // End of variables declaration//GEN-END:variables
}
