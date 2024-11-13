package com.ifcolab.estetify.view;

public class FrNovaConsulta extends javax.swing.JDialog {

    
    public FrNovaConsulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCPF = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblObservacoes = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        lblDataNascimento = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        txtObservacoes = new javax.swing.JScrollPane();
        customTextArea1 = new com.ifcolab.estetify.components.CustomTextArea();
        fEdtHora = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtData = new com.ifcolab.estetify.components.CustomFormattedTextField();
        cbxSelecionarProcedimento = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarEnfermeira = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarMedico = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarPaciente = new com.ifcolab.estetify.components.CustomComboBox();
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.estetify.components.SecondaryCustomButton();
        tmMedicos = new javax.swing.JScrollPane();
        customTable1 = new com.ifcolab.estetify.components.CustomTable();
        lblLogo = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblEstetify = new javax.swing.JLabel();
        lblSidebar = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("Selecionar Enfermeira");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(790, 120, 190, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Selecionar Paciente");
        getContentPane().add(lblNome);
        lblNome.setBounds(310, 120, 220, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Selecionar Médico");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(540, 120, 170, 17);

        lblObservacoes.setForeground(new java.awt.Color(51, 51, 51));
        lblObservacoes.setText("Observacoes");
        getContentPane().add(lblObservacoes);
        lblObservacoes.setBounds(670, 200, 110, 17);

        lblData.setForeground(new java.awt.Color(51, 51, 51));
        lblData.setText("Hora");
        getContentPane().add(lblData);
        lblData.setBounds(500, 200, 110, 17);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Selecionar Procedimento");
        getContentPane().add(lblDataNascimento);
        lblDataNascimento.setBounds(1050, 120, 230, 17);

        lblHora.setForeground(new java.awt.Color(51, 51, 51));
        lblHora.setText("Data");
        getContentPane().add(lblHora);
        lblHora.setBounds(320, 200, 130, 17);

        customTextArea1.setColumns(20);
        customTextArea1.setRows(5);
        txtObservacoes.setViewportView(customTextArea1);

        getContentPane().add(txtObservacoes);
        txtObservacoes.setBounds(660, 220, 610, 116);

        fEdtHora.setText("Hora");
        getContentPane().add(fEdtHora);
        fEdtHora.setBounds(490, 220, 140, 38);

        fEdtData.setText("Data");
        getContentPane().add(fEdtData);
        fEdtData.setBounds(310, 220, 160, 38);
        getContentPane().add(cbxSelecionarProcedimento);
        cbxSelecionarProcedimento.setBounds(1040, 140, 230, 44);
        getContentPane().add(cbxSelecionarEnfermeira);
        cbxSelecionarEnfermeira.setBounds(780, 140, 240, 44);

        cbxSelecionarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSelecionarMedicoActionPerformed(evt);
            }
        });
        getContentPane().add(cbxSelecionarMedico);
        cbxSelecionarMedico.setBounds(530, 140, 230, 44);
        getContentPane().add(cbxSelecionarPaciente);
        cbxSelecionarPaciente.setBounds(300, 140, 210, 44);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Agendar");
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(300, 80, 170, 30);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnSalvar.setText(" Salvar");
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(870, 80, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        getContentPane().add(btnEditar);
        btnEditar.setBounds(490, 80, 170, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        btnRemover.setText(" Remover");
        getContentPane().add(btnRemover);
        btnRemover.setBounds(680, 80, 170, 30);

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
        tmMedicos.setViewportView(customTable1);

        getContentPane().add(tmMedicos);
        tmMedicos.setBounds(290, 380, 1010, 406);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo45x40.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(10, 10, 50, 50);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCadastro.png"))); // NOI18N
        lblBackgroundTabela.setText("jLabel18");
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(230, 330, 1120, 500);

        lblEstetify.setFont(new java.awt.Font("Fira Sans Condensed Medium", 0, 18)); // NOI18N
        lblEstetify.setForeground(new java.awt.Color(51, 51, 51));
        lblEstetify.setText("Estetify");
        getContentPane().add(lblEstetify);
        lblEstetify.setBounds(60, 30, 90, 22);

        lblSidebar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidebar.png"))); // NOI18N
        getContentPane().add(lblSidebar);
        lblSidebar.setBounds(-460, 0, 750, 900);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Gerenciar Médicos");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(280, 40, 210, 17);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCad.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(230, 60, 1100, 280);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Nova Consulta");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(280, 20, 210, 22);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSelecionarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSelecionarMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSelecionarMedicoActionPerformed

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
            java.util.logging.Logger.getLogger(FrNovaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrNovaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrNovaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrNovaConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrNovaConsulta dialog = new FrNovaConsulta(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarEnfermeira;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarMedico;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarPaciente;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarProcedimento;
    private com.ifcolab.estetify.components.CustomTable customTable1;
    private com.ifcolab.estetify.components.CustomTextArea customTextArea1;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtData;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtHora;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEstetify;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblObservacoes;
    private javax.swing.JLabel lblSidebar;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JScrollPane tmMedicos;
    private javax.swing.JScrollPane txtObservacoes;
    // End of variables declaration//GEN-END:variables
}
