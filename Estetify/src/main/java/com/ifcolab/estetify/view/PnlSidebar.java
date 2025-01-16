package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AutenticacaoController;
import javax.swing.JFrame;

public class PnlSidebar extends javax.swing.JPanel {

    private final AutenticacaoController authController;
    private JFrame parentFrame;
    
    public PnlSidebar() {
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
        btnGerenciarProcedimentos.setVisible(false);
        btnHistoricoProcedimento.setVisible(false);
        btnFeedbacksPaciente.setVisible(false);
        btnSair.setVisible(true);
        btnConfiguracoes.setVisible(false);
        btnMinhasConsultas.setVisible(false);
        btnFeedbacks.setVisible(false);
        btnFeedbacksAdmin.setVisible(false);
        btnGerenciarAdmins.setVisible(false);
        btnAgenda.setVisible(false);
        btnPerfil.setVisible(false);
        btnGerenciarPagamentos.setVisible(false);
        lblProcedimentos.setVisible(false);
        
        
        // Mostra botões baseado no tipo de usuário
        if (authController.isAdmin()) {
            btnAgenda.setVisible(true);
            btnPerfil.setVisible(true);
            btnGerenciarMedicos.setVisible(true);
            btnGerenciarEnfermeiras.setVisible(true);
            btnGerenciarPacientes.setVisible(true);
            btnGerenciarRecepcionistas.setVisible(true);
            btnGerenciarProcedimentos.setVisible(true);
            btnHistoricoProcedimento.setVisible(true);
            btnGerenciarPagamentos.setVisible(true);
            btnGerenciarAdmins.setVisible(true);
            btnConfiguracoes.setVisible(true);
            btnFeedbacksAdmin.setVisible(true);
            btnGerenciarPagamentos.setVisible(true);
        } 
        else if (authController.isPaciente()) {
            btnMinhasConsultas.setVisible(true);
            btnFeedbacksPaciente.setVisible(true);
            btnPerfil.setVisible(true);
        }
        else if (authController.isMedico() || authController.isEnfermeira()) {
            btnAgenda.setVisible(true);
            btnPerfil.setVisible(true);
            btnFeedbacks.setVisible(true);
            btnGerenciarProcedimentos.setVisible(true);
            btnHistoricoProcedimento.setVisible(true);
            lblProcedimentos.setVisible(true);
        }
        else if (authController.isRecepcionista()) {
            btnAgenda.setVisible(true);
            btnGerenciarPacientes.setVisible(true);
            btnPerfil.setVisible(true);
            btnFeedbacks.setVisible(true);
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        lblLogo = new javax.swing.JLabel();
        lblEstetify = new javax.swing.JLabel();
        btnAgenda = new com.ifcolab.estetify.components.CustomButton();
        btnConfiguracoes = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarPacientes = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarMedicos = new com.ifcolab.estetify.components.CustomButton();
        btnPerfil = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarEnfermeiras = new com.ifcolab.estetify.components.CustomButton();
        btnMinhasConsultas = new com.ifcolab.estetify.components.CustomButton();
        btnFeedbacksPaciente = new com.ifcolab.estetify.components.CustomButton();
        btnFeedbacks = new com.ifcolab.estetify.components.CustomButton();
        btnSair = new com.ifcolab.estetify.components.CustomButton();
        btnFeedbacksAdmin = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarProcedimentos = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarRecepcionistas = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarPagamentos = new com.ifcolab.estetify.components.CustomButton();
        btnGerenciarAdmins = new com.ifcolab.estetify.components.CustomButton();
        btnHistoricoProcedimento = new com.ifcolab.estetify.components.CustomButton();
        lblProcedimentos = new javax.swing.JLabel();

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
        btnAgenda.setBounds(10, 90, 230, 40);

        btnConfiguracoes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/config.png"))); // NOI18N
        btnConfiguracoes.setText("Configurações");
        btnConfiguracoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfiguracoesActionPerformed(evt);
            }
        });
        add(btnConfiguracoes);
        btnConfiguracoes.setBounds(10, 190, 230, 40);

        btnGerenciarPacientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/coracao.png"))); // NOI18N
        btnGerenciarPacientes.setText("Gerenciar Pacientes");
        btnGerenciarPacientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarPacientesActionPerformed(evt);
            }
        });
        add(btnGerenciarPacientes);
        btnGerenciarPacientes.setBounds(10, 240, 230, 40);

        btnGerenciarMedicos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/estetoscopio.png"))); // NOI18N
        btnGerenciarMedicos.setText("Gerenciar Médicos");
        btnGerenciarMedicos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarMedicosActionPerformed(evt);
            }
        });
        add(btnGerenciarMedicos);
        btnGerenciarMedicos.setBounds(10, 390, 230, 40);

        btnPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/perfil.png"))); // NOI18N
        btnPerfil.setText("Perfil");
        btnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPerfilActionPerformed(evt);
            }
        });
        add(btnPerfil);
        btnPerfil.setBounds(10, 140, 230, 40);

        btnGerenciarEnfermeiras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/seringa.png"))); // NOI18N
        btnGerenciarEnfermeiras.setText("Gerenciar Enfermeiras");
        btnGerenciarEnfermeiras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarEnfermeirasActionPerformed(evt);
            }
        });
        add(btnGerenciarEnfermeiras);
        btnGerenciarEnfermeiras.setBounds(10, 440, 230, 40);

        btnMinhasConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calendar.png"))); // NOI18N
        btnMinhasConsultas.setText("Minhas Consultas");
        btnMinhasConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMinhasConsultasActionPerformed(evt);
            }
        });
        add(btnMinhasConsultas);
        btnMinhasConsultas.setBounds(10, 90, 230, 40);

        btnFeedbacksPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/feedbacks.png"))); // NOI18N
        btnFeedbacksPaciente.setText("Feedbacks");
        btnFeedbacksPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbacksPacienteActionPerformed(evt);
            }
        });
        add(btnFeedbacksPaciente);
        btnFeedbacksPaciente.setBounds(10, 190, 230, 40);

        btnFeedbacks.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/feedbacks.png"))); // NOI18N
        btnFeedbacks.setText("Feedbacks");
        btnFeedbacks.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbacksActionPerformed(evt);
            }
        });
        add(btnFeedbacks);
        btnFeedbacks.setBounds(10, 190, 230, 40);

        btnSair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/iconLogout.png"))); // NOI18N
        btnSair.setText(" Sair");
        btnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSairActionPerformed(evt);
            }
        });
        add(btnSair);
        btnSair.setBounds(10, 780, 230, 50);

        btnFeedbacksAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/feedbacks.png"))); // NOI18N
        btnFeedbacksAdmin.setText("Feedbacks");
        btnFeedbacksAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFeedbacksAdminActionPerformed(evt);
            }
        });
        add(btnFeedbacksAdmin);
        btnFeedbacksAdmin.setBounds(10, 640, 230, 40);

        btnGerenciarProcedimentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/procedimentos.png"))); // NOI18N
        btnGerenciarProcedimentos.setText("Gerenciar Procedimentos");
        btnGerenciarProcedimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarProcedimentosActionPerformed(evt);
            }
        });
        add(btnGerenciarProcedimentos);
        btnGerenciarProcedimentos.setBounds(10, 340, 230, 40);

        btnGerenciarRecepcionistas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/recepcionista.png"))); // NOI18N
        btnGerenciarRecepcionistas.setText("Gerenciar Recepcionistas");
        btnGerenciarRecepcionistas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarRecepcionistasActionPerformed(evt);
            }
        });
        add(btnGerenciarRecepcionistas);
        btnGerenciarRecepcionistas.setBounds(10, 490, 230, 40);

        btnGerenciarPagamentos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/historico.png"))); // NOI18N
        btnGerenciarPagamentos.setText("Gerenciar Pagamentos");
        btnGerenciarPagamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarPagamentosActionPerformed(evt);
            }
        });
        add(btnGerenciarPagamentos);
        btnGerenciarPagamentos.setBounds(10, 590, 230, 40);

        btnGerenciarAdmins.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/admin.png"))); // NOI18N
        btnGerenciarAdmins.setText("Gerenciar Admins");
        btnGerenciarAdmins.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGerenciarAdminsActionPerformed(evt);
            }
        });
        add(btnGerenciarAdmins);
        btnGerenciarAdmins.setBounds(10, 540, 230, 40);

        btnHistoricoProcedimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/historico.png"))); // NOI18N
        btnHistoricoProcedimento.setText("Historico de Procedimentos");
        btnHistoricoProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistoricoProcedimentoActionPerformed(evt);
            }
        });
        add(btnHistoricoProcedimento);
        btnHistoricoProcedimento.setBounds(10, 290, 230, 40);

        lblProcedimentos.setText("Procedimentos");
        add(lblProcedimentos);
        lblProcedimentos.setBounds(20, 237, 170, 40);
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

        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarAgenda();
        }
    }//GEN-LAST:event_btnAgendaActionPerformed

    private void btnFeedbacksAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbacksAdminActionPerformed
        DlgFeedback dialog = new DlgFeedback(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnFeedbacksAdminActionPerformed

    private void btnGerenciarProcedimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarProcedimentosActionPerformed
        DlgGerenciaProcedimento dialog = new DlgGerenciaProcedimento(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarProcedimentosActionPerformed

    private void btnMinhasConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMinhasConsultasActionPerformed
        if (parentFrame instanceof FrMenu) {
            ((FrMenu) parentFrame).mostrarMinhasConsultas();
        }
    }//GEN-LAST:event_btnMinhasConsultasActionPerformed

    private void btnGerenciarRecepcionistasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarRecepcionistasActionPerformed
        DlgGerenciaRecepcionista dialog = new DlgGerenciaRecepcionista(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarRecepcionistasActionPerformed

    private void btnGerenciarPagamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarPagamentosActionPerformed
        DlgGerenciaPagamento dialog = new DlgGerenciaPagamento(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarPagamentosActionPerformed

    private void btnGerenciarAdminsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGerenciarAdminsActionPerformed
        DlgGerenciaAdmin dialog = new DlgGerenciaAdmin(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnGerenciarAdminsActionPerformed

    private void btnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSairActionPerformed
            // Realiza o logout
        authController.logout();

        // Fecha a janela atual (parentFrame) e redireciona para a tela de login
        if (parentFrame != null) {
            parentFrame.dispose();
        }

        // Abre a tela de login
        FrLogin telaLogin = new FrLogin();
        telaLogin.setLocationRelativeTo(null);
        telaLogin.setVisible(true);
    }//GEN-LAST:event_btnSairActionPerformed

    private void btnFeedbacksPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFeedbacksPacienteActionPerformed
        DlgFeedbackPaciente dialog = new DlgFeedbackPaciente(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnFeedbacksPacienteActionPerformed

    private void btnHistoricoProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistoricoProcedimentoActionPerformed
        DlgHistoricoProcedimento dialog = new DlgHistoricoProcedimento(null, true);
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnHistoricoProcedimentoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.CustomButton btnAgenda;
    private com.ifcolab.estetify.components.CustomButton btnConfiguracoes;
    private com.ifcolab.estetify.components.CustomButton btnFeedbacks;
    private com.ifcolab.estetify.components.CustomButton btnFeedbacksAdmin;
    private com.ifcolab.estetify.components.CustomButton btnFeedbacksPaciente;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarAdmins;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarEnfermeiras;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarMedicos;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarPacientes;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarPagamentos;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarProcedimentos;
    private com.ifcolab.estetify.components.CustomButton btnGerenciarRecepcionistas;
    private com.ifcolab.estetify.components.CustomButton btnHistoricoProcedimento;
    private com.ifcolab.estetify.components.CustomButton btnMinhasConsultas;
    private com.ifcolab.estetify.components.CustomButton btnPerfil;
    private com.ifcolab.estetify.components.CustomButton btnSair;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLabel lblEstetify;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblProcedimentos;
    // End of variables declaration//GEN-END:variables
}
