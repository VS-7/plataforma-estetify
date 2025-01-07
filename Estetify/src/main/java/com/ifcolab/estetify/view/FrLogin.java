package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.model.exceptions.AutenticacaoException;
import com.ifcolab.estetify.view.FrMenu;
import javax.swing.JOptionPane;


public class FrLogin extends javax.swing.JFrame {

    private final AutenticacaoController controller;
    
    public FrLogin() {
        initComponents();
        this.setLocationRelativeTo(null);
        controller = new AutenticacaoController();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel4 = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblDescricaoPontos = new javax.swing.JLabel();
        lblTitutoSidebar = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        edtSenha = new com.ifcolab.estetify.components.CustomPasswordField();
        edtEmail = new com.ifcolab.estetify.components.CustomTextField();
        btnEntrar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        lblEsqueceuSenha = new javax.swing.JLabel();
        lblLine = new javax.swing.JLabel();
        lblEstetify = new javax.swing.JLabel();
        lblBackgroundSidebar = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        setResizable(false);
        getContentPane().setLayout(null);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo100x88.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(60, 110, 100, 90);

        lblTitulo.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 30)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("<html>Comece a aproveitar as ferramentas<br>de gestão da Estetify<html>");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(70, 250, 640, 88);

        lblDescricao.setFont(new java.awt.Font("Fira Sans", 0, 16)); // NOI18N
        lblDescricao.setForeground(new java.awt.Color(0, 0, 0));
        lblDescricao.setText("<html>Ao fazer login, você pode acessar todas as funcionalidades para gerenciar<br>sua clínica de estética em um só lugar. <b>Organize consultas</b>, acompanhe <br><b>históricos</b> de <b>procedimentos</b> e obtenha <b>feedback</b> dos pacientes, tudo<br>com <b>praticidade</b> e <b>segurança</b>.</html>");
        getContentPane().add(lblDescricao);
        lblDescricao.setBounds(70, 360, 660, 120);

        lblDescricaoPontos.setFont(new java.awt.Font("Fira Sans", 0, 14)); // NOI18N
        lblDescricaoPontos.setForeground(new java.awt.Color(0, 0, 0));
        lblDescricaoPontos.setText("<html>Nossas ferramentas ajudam você a:<br> <li>Ganhar tempo ao organizar sua agenda de consultas e procedimentos de maneira centralizada.</li> <li>Acompanhar o histórico de tratamentos e preferências de cada paciente.</li> <li>Receber feedback e obter insights valiosos para aprimorar a experiência dos seus pacientes.</li></html>");
        getContentPane().add(lblDescricaoPontos);
        lblDescricaoPontos.setBounds(70, 490, 630, 160);

        lblTitutoSidebar.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitutoSidebar.setForeground(new java.awt.Color(0, 0, 0));
        lblTitutoSidebar.setText("Entre nas ferramentas da Estetify");
        getContentPane().add(lblTitutoSidebar);
        lblTitutoSidebar.setBounds(920, 270, 300, 20);

        jLabel5.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 51, 51));
        jLabel5.setText("Senha");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(900, 390, 70, 16);

        jLabel6.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setText("E-mail");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(900, 320, 110, 16);
        getContentPane().add(edtSenha);
        edtSenha.setBounds(890, 410, 360, 38);

        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(edtEmail);
        edtEmail.setBounds(890, 340, 360, 40);

        btnEntrar.setText("Entrar");
        btnEntrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntrar);
        btnEntrar.setBounds(1000, 490, 140, 30);

        lblEsqueceuSenha.setForeground(new java.awt.Color(51, 51, 51));
        lblEsqueceuSenha.setText("Esqueceu sua senha?");
        lblEsqueceuSenha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblEsqueceuSenhaMouseClicked(evt);
            }
        });
        getContentPane().add(lblEsqueceuSenha);
        lblEsqueceuSenha.setBounds(1120, 450, 200, 20);

        lblLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line.png"))); // NOI18N
        getContentPane().add(lblLine);
        lblLine.setBounds(880, 530, 390, 40);

        lblEstetify.setForeground(new java.awt.Color(51, 51, 51));
        lblEstetify.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo30x25.png"))); // NOI18N
        lblEstetify.setText("Estetify");
        getContentPane().add(lblEstetify);
        lblEstetify.setBounds(1030, 570, 90, 40);

        lblBackgroundSidebar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidebar.png"))); // NOI18N
        getContentPane().add(lblBackgroundSidebar);
        lblBackgroundSidebar.setBounds(710, 0, 660, 870);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 870);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    private void btnEntrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrarActionPerformed
        try {
            String email = edtEmail.getText().trim();
            String senha = new String(edtSenha.getPassword());
            
            // Validações básicas
            if (email.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, informe o email",
                    "Campo obrigatório",
                    JOptionPane.WARNING_MESSAGE);
                edtEmail.requestFocus();
                return;
            }
            
            if (senha.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor, informe a senha",
                    "Campo obrigatório",
                    JOptionPane.WARNING_MESSAGE);
                edtSenha.requestFocus();
                return;
            }
            
            // Tenta realizar o login
            controller.login(email, senha);
            
            // Se chegou aqui, login foi bem sucedido
            // Abre o menu principal
            FrMenu telaMenu = new FrMenu();
            telaMenu.setVisible(true);
            this.dispose(); // Fecha a tela de login
            
            // Limpa os campos
            edtEmail.setText("");
            edtSenha.setText("");
            
        } catch (AutenticacaoException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Erro de autenticação",
                JOptionPane.ERROR_MESSAGE);
            edtSenha.setText("");
            edtSenha.requestFocus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao realizar login: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btnEntrarActionPerformed

    private void lblEsqueceuSenhaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblEsqueceuSenhaMouseClicked
        DlgEsqueceuSenha dlg = new DlgEsqueceuSenha(null, true);
        dlg.setVisible(true);
    }//GEN-LAST:event_lblEsqueceuSenhaMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnEntrar;
    private com.ifcolab.estetify.components.CustomTextField edtEmail;
    private com.ifcolab.estetify.components.CustomPasswordField edtSenha;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundSidebar;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDescricaoPontos;
    private javax.swing.JLabel lblEsqueceuSenha;
    private javax.swing.JLabel lblEstetify;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitutoSidebar;
    // End of variables declaration//GEN-END:variables
}
