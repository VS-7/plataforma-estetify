/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ifcolab.estetify.view;

/**
 *
 * @author vitorsrgio
 */
public class FrLogin extends javax.swing.JFrame {

    /**
     * Creates new form FrLogin
     */
    public FrLogin() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        customTextField1 = new com.ifcolab.estetify.components.CustomTextField();
        customTextField2 = new com.ifcolab.estetify.components.CustomTextField();
        primaryCustomButton1 = new com.ifcolab.estetify.components.PrimaryCustomButton();
        lblLine = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
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

        customTextField1.setText("Insira sua senha");
        customTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(customTextField1);
        customTextField1.setBounds(890, 410, 360, 40);

        customTextField2.setText("Insira seu e-mail");
        customTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(customTextField2);
        customTextField2.setBounds(890, 340, 360, 40);

        primaryCustomButton1.setText("Entrar");
        primaryCustomButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                primaryCustomButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(primaryCustomButton1);
        primaryCustomButton1.setBounds(1000, 480, 140, 30);

        lblLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line.png"))); // NOI18N
        getContentPane().add(lblLine);
        lblLine.setBounds(880, 530, 390, 40);

        jLabel1.setForeground(new java.awt.Color(51, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo30x25.png"))); // NOI18N
        jLabel1.setText("Estetify");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(1030, 570, 90, 40);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidebar.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(710, 0, 660, 850);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void txtSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSenhaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSenhaActionPerformed

    private void customTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customTextField1ActionPerformed

    private void customTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customTextField2ActionPerformed

    private void primaryCustomButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_primaryCustomButton1ActionPerformed
        FrAgenda dlgGerenciaPaciente = new FrAgenda(this, true);
        
        // Configura o posicionamento relativo, faz o dialgo aparecer na mesma tela que o pai(qdo estamos mais de uma tela)
        dlgGerenciaPaciente.setLocationRelativeTo(this);
        dlgGerenciaPaciente.setVisible(true);

    }//GEN-LAST:event_primaryCustomButton1ActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.CustomTextField customTextField1;
    private com.ifcolab.estetify.components.CustomTextField customTextField2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDescricaoPontos;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTitutoSidebar;
    private com.ifcolab.estetify.components.PrimaryCustomButton primaryCustomButton1;
    // End of variables declaration//GEN-END:variables
}
