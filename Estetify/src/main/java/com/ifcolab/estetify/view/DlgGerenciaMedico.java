package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.MedicoController;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.enums.EspecializacaoMedico;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.MedicoException;
import com.ifcolab.estetify.model.exceptions.PacienteException;
import com.ifcolab.estetify.model.exceptions.PessoaException;
import com.ifcolab.estetify.model.exceptions.ValidateException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;


public class DlgGerenciaMedico extends javax.swing.JDialog {

    private MedicoController controller;
    private int idMedicoEditando;

    
    public DlgGerenciaMedico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new MedicoController();
        idMedicoEditando = -1;
        
        this.configurarComboBoxes();
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        // Adicionar listener de duplo clique
        grdMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdMedicosMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdMedicos);
    }
    
    private void configurarComboBoxes() {
        // Adiciona todas as especializações ao ComboBox
        cboEspecializacao.removeAllItems();
        for (EspecializacaoMedico esp : EspecializacaoMedico.values()) {
            cboEspecializacao.addItem(esp);
        }
        
        // Configurar ComboBox de Sexo
        cboSexo.removeAllItems();
        for (TipoSexo s : TipoSexo.values()) {
            cboSexo.addItem(s);
        }
    }
    
    private void adicionarMascaraNosCampos() {
        try {
            // Máscara para CPF: 999.999.999-99
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.setPlaceholderCharacter('_');
            maskCPF.install(fEdtCPF);
            
            // Máscara para telefone: (99) 99999-9999
            MaskFormatter maskTelefone = new MaskFormatter("(##) #####-####");
            maskTelefone.setPlaceholderCharacter('_');
            maskTelefone.install(fEdtTelefone);
            
            // Máscara para data: dd/mm/yyyy
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            maskData.install(fEdtDataNascimento);
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgGerenciaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void habilitarFormulario(boolean habilitar) {
        edtNome.setEnabled(habilitar);
        edtEmail.setEnabled(habilitar);
        fEdtCPF.setEnabled(habilitar);
        cboSexo.setEnabled(habilitar);
        fEdtDataNascimento.setEnabled(habilitar);
        fEdtTelefone.setEnabled(habilitar);
        edtEndereco.setEnabled(habilitar);
        edtCRM.setEnabled(habilitar);
        cboEspecializacao.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }

    private void limparFormulario() {
        edtNome.setText("");
        edtEmail.setText("");
        fEdtCPF.setText("");
        cboSexo.setSelectedItem(null);
        fEdtDataNascimento.setText("");
        fEdtTelefone.setText("");
        edtEndereco.setText("");
        edtCRM.setText("");
        cboEspecializacao.setSelectedItem(null);
    }

    private void preencherFormulario(Medico medico) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        edtNome.setText(medico.getNome());
        edtEmail.setText(medico.getEmail());
        fEdtCPF.setText(medico.getCpf());
        cboSexo.setSelectedItem(medico.getSexo());
        fEdtDataNascimento.setText(medico.getDataNascimento().format(formatter));
        fEdtTelefone.setText(medico.getTelefone());
        edtEndereco.setText(medico.getEndereco());
        edtCRM.setText(medico.getCrm());
        cboEspecializacao.setSelectedItem(medico.getEspecializacao());
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdMedicos.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdMedicos.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCPF = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblDataNascimento = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblEndereco = new javax.swing.JLabel();
        lblCRM = new javax.swing.JLabel();
        lblEspecializacao = new javax.swing.JLabel();
        fEdtCPF = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtDataNascimento = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtTelefone = new com.ifcolab.estetify.components.CustomFormattedTextField();
        edtEmail = new com.ifcolab.estetify.components.CustomTextField();
        edtNome = new com.ifcolab.estetify.components.CustomTextField();
        edtCRM = new com.ifcolab.estetify.components.CustomTextField();
        edtEndereco = new com.ifcolab.estetify.components.CustomTextField();
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.estetify.components.SecondaryCustomButton();
        cboEspecializacao = new com.ifcolab.estetify.components.CustomComboBox();
        cboSexo = new com.ifcolab.estetify.components.CustomComboBox();
        tmMedicos = new javax.swing.JScrollPane();
        grdMedicos = new com.ifcolab.estetify.components.CustomTable();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("CPF");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(890, 120, 50, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Nome");
        getContentPane().add(lblNome);
        lblNome.setBounds(70, 120, 170, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(530, 120, 240, 17);

        lblSexo.setForeground(new java.awt.Color(51, 51, 51));
        lblSexo.setText("Sexo");
        getContentPane().add(lblSexo);
        lblSexo.setBounds(1140, 120, 50, 17);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Data de Nascimento");
        getContentPane().add(lblDataNascimento);
        lblDataNascimento.setBounds(70, 190, 140, 17);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Telefone");
        getContentPane().add(lblTelefone);
        lblTelefone.setBounds(720, 190, 140, 17);

        lblEndereco.setForeground(new java.awt.Color(51, 51, 51));
        lblEndereco.setText("Endereço");
        getContentPane().add(lblEndereco);
        lblEndereco.setBounds(310, 190, 140, 17);

        lblCRM.setForeground(new java.awt.Color(51, 51, 51));
        lblCRM.setText("CRM");
        getContentPane().add(lblCRM);
        lblCRM.setBounds(1030, 190, 140, 17);

        lblEspecializacao.setForeground(new java.awt.Color(51, 51, 51));
        lblEspecializacao.setText("Especialização");
        getContentPane().add(lblEspecializacao);
        lblEspecializacao.setBounds(70, 260, 140, 17);

        fEdtCPF.setText("CPF");
        getContentPane().add(fEdtCPF);
        fEdtCPF.setBounds(880, 140, 230, 38);

        fEdtDataNascimento.setText("Data de Nascimento");
        getContentPane().add(fEdtDataNascimento);
        fEdtDataNascimento.setBounds(60, 210, 210, 38);

        fEdtTelefone.setText("Telefone");
        getContentPane().add(fEdtTelefone);
        fEdtTelefone.setBounds(710, 210, 280, 38);

        edtEmail.setText("E-mail");
        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(edtEmail);
        edtEmail.setBounds(520, 140, 330, 40);

        edtNome.setText("Nome");
        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(edtNome);
        edtNome.setBounds(60, 140, 430, 40);

        edtCRM.setText("CRM");
        edtCRM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtCRMActionPerformed(evt);
            }
        });
        getContentPane().add(edtCRM);
        edtCRM.setBounds(1020, 210, 210, 40);

        edtEndereco.setText("Endereço");
        edtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(edtEndereco);
        edtEndereco.setBounds(300, 210, 380, 40);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(60, 80, 170, 30);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnSalvar.setText(" Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(690, 80, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(270, 80, 170, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        btnRemover.setText(" Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(480, 80, 170, 30);
        getContentPane().add(cboEspecializacao);
        cboEspecializacao.setBounds(60, 280, 330, 44);
        getContentPane().add(cboSexo);
        cboSexo.setBounds(1130, 140, 160, 44);

        grdMedicos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        grdMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdMedicosMouseClicked(evt);
            }
        });
        tmMedicos.setViewportView(grdMedicos);

        getContentPane().add(tmMedicos);
        tmMedicos.setBounds(40, 380, 1260, 420);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Adicione, edite ou remova informações dos médicos, incluindo especialidades e dados de contato.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 780, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Gerenciar Médicos");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 290);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 340, 1390, 500);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    private void edtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeActionPerformed

    private void edtCRMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtCRMActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtCRMActionPerformed

    private void edtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEnderecoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        this.limparFormulario();
        this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (idMedicoEditando > 0) {
                Medico medicoAtual = controller.find(idMedicoEditando);
                controller.atualizar(
                    idMedicoEditando,
                    edtNome.getText(),
                    edtEmail.getText(),
                    medicoAtual.getSenha(),
                    fEdtCPF.getText(),
                    (TipoSexo) cboSexo.getSelectedItem(),
                    fEdtDataNascimento.getText(),
                    fEdtTelefone.getText(),
                    edtEndereco.getText(),
                    edtCRM.getText(),
                    (EspecializacaoMedico) cboEspecializacao.getSelectedItem(),
                    medicoAtual.getAvatar()
                );
            } else {
                controller.cadastrar(
                    edtNome.getText(),
                    edtEmail.getText(),
                    fEdtCPF.getText(),
                    (TipoSexo) cboSexo.getSelectedItem(),
                    fEdtDataNascimento.getText(),
                    fEdtTelefone.getText(),
                    edtEndereco.getText(),
                    edtCRM.getText(),
                    (EspecializacaoMedico) cboEspecializacao.getSelectedItem(),
                    1
                );
            }

            this.idMedicoEditando = -1;
            controller.atualizarTabela(grdMedicos);
            this.habilitarFormulario(false);
            this.limparFormulario();

        } catch (ValidateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Medico medicoEditando = (Medico) this.getObjetoSelecionadoNaGrid();

        if (medicoEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(medicoEditando);
            this.idMedicoEditando = medicoEditando.getId();
        }        
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Medico medicoExcluido = (Medico) this.getObjetoSelecionadoNaGrid();

        if (medicoExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir o Médico \n("
                + medicoExcluido.getNome() + ", "
                + medicoExcluido.getCpf() + ") ?",
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(medicoExcluido);
                    controller.atualizarTabela(grdMedicos);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (PacienteException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void grdMedicosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdMedicosMouseClicked
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }
    }//GEN-LAST:event_grdMedicosMouseClicked



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomComboBox cboEspecializacao;
    private com.ifcolab.estetify.components.CustomComboBox cboSexo;
    private com.ifcolab.estetify.components.CustomTextField edtCRM;
    private com.ifcolab.estetify.components.CustomTextField edtEmail;
    private com.ifcolab.estetify.components.CustomTextField edtEndereco;
    private com.ifcolab.estetify.components.CustomTextField edtNome;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtCPF;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtDataNascimento;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtTelefone;
    private com.ifcolab.estetify.components.CustomTable grdMedicos;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblCRM;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblEspecializacao;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JScrollPane tmMedicos;
    // End of variables declaration//GEN-END:variables
}
