package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.PacienteController;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.PacienteException;
import com.ifcolab.estetify.model.exceptions.PessoaException;
import com.ifcolab.estetify.model.exceptions.ValidateException;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class DlgGerenciaPaciente extends javax.swing.JDialog {

    private PacienteController controller;
    private int idPacienteEditando;
    

    public DlgGerenciaPaciente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new PacienteController();
        idPacienteEditando = -1;
 
        this.configurarComboBoxes();
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        grdPacientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdPacientesMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdPacientes);
    }
    
    private void configurarComboBoxes() {
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
        edtHistoricoMedico.setEnabled(habilitar);
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
        edtHistoricoMedico.setText("");
    }

    private void preencherFormulario(Paciente paciente) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        
        edtNome.setText(paciente.getNome());
        edtEmail.setText(paciente.getEmail());
        fEdtCPF.setText(paciente.getCpf());
        cboSexo.setSelectedItem(paciente.getSexo());
        fEdtDataNascimento.setText(paciente.getDataNascimento().format(formatter));
        fEdtTelefone.setText(paciente.getTelefone());
        edtEndereco.setText(paciente.getEndereco());
        edtHistoricoMedico.setText(paciente.getHistoricoMedico());
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdPacientes.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdPacientes.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    private void grdPacientesMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        fEdtTelefone = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtDataNascimento = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtCPF = new com.ifcolab.estetify.components.CustomFormattedTextField();
        edtEmail = new com.ifcolab.estetify.components.CustomTextField();
        edtNome = new com.ifcolab.estetify.components.CustomTextField();
        edtEndereco = new com.ifcolab.estetify.components.CustomTextField();
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.estetify.components.SecondaryCustomButton();
        edtHistoricoMedico = new com.ifcolab.estetify.components.CustomTextField();
        lblHistoricoMedico = new javax.swing.JLabel();
        cboSexo = new com.ifcolab.estetify.components.CustomComboBox();
        tmMedicos = new javax.swing.JScrollPane();
        grdPacientes = new com.ifcolab.estetify.components.CustomTable();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("CPF");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(890, 140, 50, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Nome");
        getContentPane().add(lblNome);
        lblNome.setBounds(70, 140, 160, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(540, 140, 170, 17);

        lblSexo.setForeground(new java.awt.Color(51, 51, 51));
        lblSexo.setText("Sexo");
        getContentPane().add(lblSexo);
        lblSexo.setBounds(1140, 140, 50, 17);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Data de Nascimento");
        getContentPane().add(lblDataNascimento);
        lblDataNascimento.setBounds(70, 210, 170, 17);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Telefone");
        getContentPane().add(lblTelefone);
        lblTelefone.setBounds(770, 210, 140, 17);

        lblEndereco.setForeground(new java.awt.Color(51, 51, 51));
        lblEndereco.setText("Endereço");
        getContentPane().add(lblEndereco);
        lblEndereco.setBounds(350, 210, 140, 17);

        fEdtTelefone.setText("Telefone");
        getContentPane().add(fEdtTelefone);
        fEdtTelefone.setBounds(760, 230, 230, 38);

        fEdtDataNascimento.setText("Data de Nascimento");
        getContentPane().add(fEdtDataNascimento);
        fEdtDataNascimento.setBounds(60, 230, 250, 38);

        fEdtCPF.setText("CPF");
        getContentPane().add(fEdtCPF);
        fEdtCPF.setBounds(880, 160, 230, 38);

        edtEmail.setText("E-mail");
        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        getContentPane().add(edtEmail);
        edtEmail.setBounds(530, 160, 330, 40);

        edtNome.setText("Nome");
        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });
        getContentPane().add(edtNome);
        edtNome.setBounds(60, 160, 440, 40);

        edtEndereco.setText("Endereço");
        edtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEnderecoActionPerformed(evt);
            }
        });
        getContentPane().add(edtEndereco);
        edtEndereco.setBounds(340, 230, 390, 40);

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

        edtHistoricoMedico.setText("Histórico Médico");
        getContentPane().add(edtHistoricoMedico);
        edtHistoricoMedico.setBounds(1020, 230, 260, 38);

        lblHistoricoMedico.setForeground(new java.awt.Color(51, 51, 51));
        lblHistoricoMedico.setText("Histórico Médico");
        getContentPane().add(lblHistoricoMedico);
        lblHistoricoMedico.setBounds(1030, 210, 140, 17);
        getContentPane().add(cboSexo);
        cboSexo.setBounds(1130, 160, 160, 44);

        grdPacientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tmMedicos.setViewportView(grdPacientes);

        getContentPane().add(tmMedicos);
        tmMedicos.setBounds(40, 380, 1260, 420);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 340, 1390, 500);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Administre os dados dos pacientes, incluindo histórico médico, contato, endereço e dados pessoais.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 760, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Gerenciar Pacientes");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 290);

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

    private void edtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEnderecoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        this.limparFormulario();
        this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (idPacienteEditando > 0) {
                Paciente pacienteAtual = controller.find(idPacienteEditando);
                controller.atualizar(
                    idPacienteEditando,
                    edtNome.getText(),
                    edtEmail.getText(),
                    pacienteAtual.getSenha(),
                    fEdtCPF.getText(),
                    (TipoSexo) cboSexo.getSelectedItem(),
                    fEdtDataNascimento.getText(),
                    fEdtTelefone.getText(),
                    edtEndereco.getText(),
                    edtHistoricoMedico.getText(),
                    pacienteAtual.getAvatar()
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
                    edtHistoricoMedico.getText(),
                    1
                );
            }

            this.idPacienteEditando = -1;
            controller.atualizarTabela(grdPacientes);
            this.habilitarFormulario(false);
            this.limparFormulario();

        } catch (ValidateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Paciente pacienteEditando = (Paciente) this.getObjetoSelecionadoNaGrid();

        if (pacienteEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(pacienteEditando);
            this.idPacienteEditando = pacienteEditando.getId();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Paciente pacienteExcluido = (Paciente) this.getObjetoSelecionadoNaGrid();

        if (pacienteExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir o Paciente \n("
                + pacienteExcluido.getNome() + ", "
                + pacienteExcluido.getCpf() + ") ?",
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(pacienteExcluido);
                    controller.atualizarTabela(grdPacientes);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (PacienteException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomComboBox cboSexo;
    private com.ifcolab.estetify.components.CustomTextField edtEmail;
    private com.ifcolab.estetify.components.CustomTextField edtEndereco;
    private com.ifcolab.estetify.components.CustomTextField edtHistoricoMedico;
    private com.ifcolab.estetify.components.CustomTextField edtNome;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtCPF;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtDataNascimento;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtTelefone;
    private com.ifcolab.estetify.components.CustomTable grdPacientes;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblHistoricoMedico;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JScrollPane tmMedicos;
    // End of variables declaration//GEN-END:variables
}
