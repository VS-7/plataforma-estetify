/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.ifcolab.estetify.view;

/**
 *
 * @author vitorsrgio
 */
public class FrGerenciaPaciente extends javax.swing.JFrame {

    /**
     * Creates new form FrLogin
     */
    public FrGerenciaPaciente() {
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
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnSalvar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnPaginaInicial = new com.ifcolab.estetify.components.SidebarCustomButton();
        lblSexo = new javax.swing.JLabel();
        txtSexo = new com.ifcolab.estetify.components.CustomTextField();
        txtDataNascimento = new com.ifcolab.estetify.components.CustomTextField();
        lblTelefone = new javax.swing.JLabel();
        txtTelefone = new com.ifcolab.estetify.components.CustomTextField();
        txtCpf = new com.ifcolab.estetify.components.CustomTextField();
        lblEndereco = new javax.swing.JLabel();
        txtEndereco = new com.ifcolab.estetify.components.CustomTextField();
        lblEspecialidade = new javax.swing.JLabel();
        lblCpf = new javax.swing.JLabel();
        lblCrm = new javax.swing.JLabel();
        txtCrm = new com.ifcolab.estetify.components.CustomTextField();
        lblNome = new javax.swing.JLabel();
        txtNome = new com.ifcolab.estetify.components.CustomTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        customTable1 = new com.ifcolab.estetify.components.CustomTable();
        lblLogoText = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackgroundCadastroTable = new javax.swing.JLabel();
        lblSidebar = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        jLabel4.setText("jLabel4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1440, 1024));
        setResizable(false);
        getContentPane().setLayout(null);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Adicionar");
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(373, 110, 150, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(540, 110, 150, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnRemover.setText(" Salvar");
        getContentPane().add(btnRemover);
        btnRemover.setBounds(880, 110, 150, 30);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        btnSalvar.setText(" Remover");
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(710, 110, 150, 30);

        btnPaginaInicial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/home.png"))); // NOI18N
        btnPaginaInicial.setText(" Página principal");
        getContentPane().add(btnPaginaInicial);
        btnPaginaInicial.setBounds(10, 130, 210, 46);

        lblSexo.setForeground(new java.awt.Color(51, 51, 51));
        lblSexo.setText("Sexo");
        getContentPane().add(lblSexo);
        lblSexo.setBounds(690, 170, 160, 17);

        txtSexo.setText("Sexo");
        txtSexo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSexoActionPerformed(evt);
            }
        });
        getContentPane().add(txtSexo);
        txtSexo.setBounds(680, 190, 140, 38);

        txtDataNascimento.setText("Endereço");
        txtDataNascimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDataNascimentoActionPerformed(evt);
            }
        });
        getContentPane().add(txtDataNascimento);
        txtDataNascimento.setBounds(560, 260, 310, 38);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Telefone");
        getContentPane().add(lblTelefone);
        lblTelefone.setBounds(370, 240, 70, 17);

        txtTelefone.setText("Telefone");
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });
        getContentPane().add(txtTelefone);
        txtTelefone.setBounds(360, 260, 180, 38);

        txtCpf.setText("CPF");
        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });
        getContentPane().add(txtCpf);
        txtCpf.setBounds(1080, 190, 270, 38);

        lblEndereco.setForeground(new java.awt.Color(51, 51, 51));
        lblEndereco.setText("Endereco");
        getContentPane().add(lblEndereco);
        lblEndereco.setBounds(570, 240, 160, 17);

        txtEndereco.setText("Data de Nascimento");
        txtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(txtEndereco);
        txtEndereco.setBounds(840, 190, 220, 38);

        lblEspecialidade.setForeground(new java.awt.Color(51, 51, 51));
        lblEspecialidade.setText("COREN");
        getContentPane().add(lblEspecialidade);
        lblEspecialidade.setBounds(900, 240, 160, 17);

        lblCpf.setForeground(new java.awt.Color(51, 51, 51));
        lblCpf.setText("CPF");
        getContentPane().add(lblCpf);
        lblCpf.setBounds(1090, 170, 110, 17);

        lblCrm.setForeground(new java.awt.Color(51, 51, 51));
        lblCrm.setText("Data de Nascimento");
        getContentPane().add(lblCrm);
        lblCrm.setBounds(850, 170, 160, 17);

        txtCrm.setText("COREN");
        txtCrm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCrmActionPerformed(evt);
            }
        });
        getContentPane().add(txtCrm);
        txtCrm.setBounds(890, 260, 170, 38);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Nome");
        getContentPane().add(lblNome);
        lblNome.setBounds(370, 170, 130, 17);

        txtNome.setText("Nome");
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(txtNome);
        txtNome.setBounds(360, 190, 300, 38);

        customTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(customTable1);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(350, 380, 1010, 560);

        lblLogoText.setFont(new java.awt.Font("Fira Sans Medium", 0, 24)); // NOI18N
        lblLogoText.setForeground(new java.awt.Color(0, 0, 0));
        lblLogoText.setText("Estetify");
        getContentPane().add(lblLogoText);
        lblLogoText.setBounds(60, 20, 120, 40);

        lblLogo.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblLogo.setForeground(new java.awt.Color(0, 0, 0));
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo45x40.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(10, 20, 50, 40);

        lblTitulo.setFont(new java.awt.Font("Fira Sans Medium", 0, 18)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("Gerenciar pacientes");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(330, 40, 250, 20);

        lblSubtitulo.setForeground(new java.awt.Color(153, 153, 153));
        lblSubtitulo.setText("Aqui você pode criar, visualizar e fazer o que quiser meu irmão.");
        getContentPane().add(lblSubtitulo);
        lblSubtitulo.setBounds(330, 60, 510, 20);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCadastro.png"))); // NOI18N
        lblBackgroundCadastro.setText("jLabel7");
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(290, 80, 1100, 290);

        lblBackgroundCadastroTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCadastroLista.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastroTable);
        lblBackgroundCadastroTable.setBounds(290, 340, 1100, 620);

        lblSidebar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/SidebarProjeto.png"))); // NOI18N
        getContentPane().add(lblSidebar);
        lblSidebar.setBounds(0, 0, 280, 1020);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1440, 1020);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtCrmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCrmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCrmActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtDataNascimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDataNascimentoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDataNascimentoActionPerformed

    private void txtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEnderecoActionPerformed

    private void txtSexoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSexoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSexoActionPerformed

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
            java.util.logging.Logger.getLogger(FrGerenciaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrGerenciaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrGerenciaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrGerenciaPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrGerenciaPaciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.SidebarCustomButton btnPaginaInicial;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomTable customTable1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundCadastroTable;
    private javax.swing.JLabel lblCpf;
    private javax.swing.JLabel lblCrm;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblEspecialidade;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoText;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSidebar;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitulo;
    private com.ifcolab.estetify.components.CustomTextField txtCpf;
    private com.ifcolab.estetify.components.CustomTextField txtCrm;
    private com.ifcolab.estetify.components.CustomTextField txtDataNascimento;
    private com.ifcolab.estetify.components.CustomTextField txtEndereco;
    private com.ifcolab.estetify.components.CustomTextField txtNome;
    private com.ifcolab.estetify.components.CustomTextField txtSexo;
    private com.ifcolab.estetify.components.CustomTextField txtTelefone;
    // End of variables declaration//GEN-END:variables
}
