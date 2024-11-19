package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.controller.EnfermeiraController;
import com.ifcolab.estetify.controller.MedicoController;
import com.ifcolab.estetify.controller.PacienteController;
import com.ifcolab.estetify.controller.ProcedimentoController;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class FrNovaConsulta extends javax.swing.JDialog {

    private ConsultaController controller;
    private PacienteController pacienteController;
    private MedicoController medicoController;
    private EnfermeiraController enfermeiraController;
    private ProcedimentoController procedimentoController;
    private List<Procedimento> procedimentosSelecionados;
    private int idConsultaEditando;
    
    public FrNovaConsulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new ConsultaController();
        pacienteController = new PacienteController();
        medicoController = new MedicoController();
        enfermeiraController = new EnfermeiraController();
        procedimentoController = new ProcedimentoController();
        procedimentosSelecionados = new ArrayList<>(); 
        idConsultaEditando = -1;
        
        this.adicionarMascaraNosCampos();
        this.preencherComboBoxes();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        controller.atualizarTabela(grdConsultas);
    }
    
    private void adicionarMascaraNosCampos() {
        try {
            // Máscara para data: dd/MM/yyyy
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            maskData.install(fEdtData);
            
            // Máscara para hora: HH:mm
            MaskFormatter maskHora = new MaskFormatter("##:##");
            maskHora.setPlaceholderCharacter('_');
            maskHora.install(fEdtHora);
            
        } catch (ParseException ex) {
            System.err.println("Erro ao criar máscaras: " + ex.getMessage());
        }
    }
    
    private void preencherComboBoxes() {
        try {
            // Preenche ComboBox de Pacientes
            DefaultComboBoxModel<Paciente> modelPaciente = new DefaultComboBoxModel<>();
            modelPaciente.addElement(null); // Adiciona item vazio
            pacienteController.findAll().forEach(modelPaciente::addElement);
            cbxSelecionarPaciente.setModel(modelPaciente);

            // Preenche ComboBox de Médicos
            DefaultComboBoxModel<Medico> modelMedico = new DefaultComboBoxModel<>();
            modelMedico.addElement(null); // Adiciona item vazio
            medicoController.findAll().forEach(modelMedico::addElement);
            cbxSelecionarMedico.setModel(modelMedico);

            // Preenche ComboBox de Enfermeiras
            DefaultComboBoxModel<Enfermeira> modelEnfermeira = new DefaultComboBoxModel<>();
            modelEnfermeira.addElement(null); // Adiciona item vazio
            enfermeiraController.findAll().forEach(modelEnfermeira::addElement);
            cbxSelecionarEnfermeira.setModel(modelEnfermeira);

            // Preenche ComboBox de Procedimentos
            DefaultComboBoxModel<Procedimento> modelProcedimento = new DefaultComboBoxModel<>();
            modelProcedimento.addElement(null); // Adiciona item vazio
            procedimentoController.findAll().forEach(modelProcedimento::addElement);
            cbxSelecionarProcedimento.setModel(modelProcedimento);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao carregar dados: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void habilitarFormulario(boolean habilitar) {
        cbxSelecionarPaciente.setEnabled(habilitar);
        cbxSelecionarMedico.setEnabled(habilitar);
        cbxSelecionarEnfermeira.setEnabled(habilitar);
        cbxSelecionarProcedimento.setEnabled(habilitar);
        fEdtData.setEnabled(habilitar);
        fEdtHora.setEnabled(habilitar);
        txtObeservacoes.setEnabled(habilitar);
        btnAdicionarProcedimento.setEnabled(habilitar);
        listProcedimentosSelecionados.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }
    
    private void limparFormulario() {
        cbxSelecionarPaciente.setSelectedIndex(-1);
        cbxSelecionarMedico.setSelectedIndex(-1);
        cbxSelecionarEnfermeira.setSelectedIndex(-1);
        cbxSelecionarProcedimento.setSelectedIndex(-1);
        fEdtData.setText("");
        fEdtHora.setText("");
        txtObeservacoes.setText("");
        procedimentosSelecionados.clear();
        atualizarListaProcedimentos();
    }
    
    private void preencherFormulario(Consulta consulta) {
        cbxSelecionarPaciente.setSelectedItem(consulta.getPaciente());
        cbxSelecionarMedico.setSelectedItem(consulta.getMedico());
        cbxSelecionarEnfermeira.setSelectedItem(consulta.getEnfermeira());
        
        // Formata data e hora
        fEdtData.setText(consulta.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        fEdtHora.setText(consulta.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
        
        txtObeservacoes.setText(consulta.getObservacoes());
        procedimentosSelecionados = new ArrayList<>(consulta.getProcedimentos());
    }
    
    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdConsultas.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdConsultas.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }    
    
    private void verificarDisponibilidade() {
        try {
            String dataHora = fEdtData.getText() + " " + fEdtHora.getText();
            Medico medico = (Medico) cbxSelecionarMedico.getSelectedItem();
            Enfermeira enfermeira = (Enfermeira) cbxSelecionarEnfermeira.getSelectedItem();
            
            if (medico != null && enfermeira != null) {
                boolean disponivel = controller.verificarDisponibilidade(
                    dataHora,
                    medico.getId(),
                    enfermeira.getId(),
                    idConsultaEditando
                );
                
                if (!disponivel) {
                    JOptionPane.showMessageDialog(this, 
                        "Médico ou enfermeira não disponível neste horário.",
                        "Indisponibilidade",
                        JOptionPane.WARNING_MESSAGE);
                }
            }
        } catch (ConsultaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
    
    private void atualizarListaProcedimentos() {
        DefaultListModel<Procedimento> model = new DefaultListModel<>();
        for (Procedimento proc : procedimentosSelecionados) {
            model.addElement(proc);
        }
        listProcedimentosSelecionados.setModel(model);
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
        txtObservacoesScrollPane = new javax.swing.JScrollPane();
        txtObeservacoes = new com.ifcolab.estetify.components.CustomTextArea();
        fEdtHora = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtData = new com.ifcolab.estetify.components.CustomFormattedTextField();
        cbxSelecionarProcedimento = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarEnfermeira = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarMedico = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarPaciente = new com.ifcolab.estetify.components.CustomComboBox();
        btnAdicionarProcedimento = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.estetify.components.SecondaryCustomButton();
        scrollProcedimentos = new javax.swing.JScrollPane();
        listProcedimentosSelecionados = new javax.swing.JList<>();
        tmConsultas = new javax.swing.JScrollPane();
        grdConsultas = new com.ifcolab.estetify.components.CustomTable();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("Selecionar Enfermeira");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(710, 120, 190, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Selecionar Paciente");
        getContentPane().add(lblNome);
        lblNome.setBounds(70, 120, 220, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Selecionar Médico");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(390, 120, 170, 17);

        lblObservacoes.setForeground(new java.awt.Color(51, 51, 51));
        lblObservacoes.setText("Observacoes");
        getContentPane().add(lblObservacoes);
        lblObservacoes.setBounds(240, 190, 110, 17);

        lblData.setForeground(new java.awt.Color(51, 51, 51));
        lblData.setText("Hora");
        getContentPane().add(lblData);
        lblData.setBounds(70, 260, 110, 17);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Selecionar Procedimento");
        getContentPane().add(lblDataNascimento);
        lblDataNascimento.setBounds(990, 120, 230, 17);

        lblHora.setForeground(new java.awt.Color(51, 51, 51));
        lblHora.setText("Data");
        getContentPane().add(lblHora);
        lblHora.setBounds(70, 190, 130, 17);

        txtObeservacoes.setColumns(20);
        txtObeservacoes.setRows(5);
        txtObservacoesScrollPane.setViewportView(txtObeservacoes);

        getContentPane().add(txtObservacoesScrollPane);
        txtObservacoesScrollPane.setBounds(240, 206, 730, 130);

        fEdtHora.setText("Hora");
        getContentPane().add(fEdtHora);
        fEdtHora.setBounds(60, 280, 160, 38);

        fEdtData.setText("Data");
        getContentPane().add(fEdtData);
        fEdtData.setBounds(60, 210, 160, 38);
        getContentPane().add(cbxSelecionarProcedimento);
        cbxSelecionarProcedimento.setBounds(980, 140, 250, 44);
        getContentPane().add(cbxSelecionarEnfermeira);
        cbxSelecionarEnfermeira.setBounds(700, 140, 250, 44);

        cbxSelecionarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSelecionarMedicoActionPerformed(evt);
            }
        });
        getContentPane().add(cbxSelecionarMedico);
        cbxSelecionarMedico.setBounds(380, 140, 290, 44);
        getContentPane().add(cbxSelecionarPaciente);
        cbxSelecionarPaciente.setBounds(60, 140, 290, 44);

        btnAdicionarProcedimento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionarProcedimento.setText("");
        btnAdicionarProcedimento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarProcedimentoActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionarProcedimento);
        btnAdicionarProcedimento.setBounds(1240, 140, 40, 40);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Agendar");
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
        btnSalvar.setBounds(720, 80, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(280, 80, 170, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        btnRemover.setText(" Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(500, 80, 170, 30);

        listProcedimentosSelecionados.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        listProcedimentosSelecionados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listProcedimentosSelecionadosKeyPressed(evt);
            }
        });
        scrollProcedimentos.setViewportView(listProcedimentosSelecionados);

        getContentPane().add(scrollProcedimentos);
        scrollProcedimentos.setBounds(980, 190, 300, 150);

        grdConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tmConsultas.setViewportView(grdConsultas);

        getContentPane().add(tmConsultas);
        tmConsultas.setBounds(40, 366, 1260, 430);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Agende novas consultas vinculando pacientes, profissionais e horários disponíveis.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 550, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Nova Consulta");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 330, 1350, 500);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 290);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSelecionarMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSelecionarMedicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSelecionarMedicoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        this.limparFormulario();
        this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Consulta consultaEditando = (Consulta) this.getObjetoSelecionadoNaGrid();

        if (consultaEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(consultaEditando);
            this.idConsultaEditando = consultaEditando.getId();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Consulta consultaExcluido = (Consulta) this.getObjetoSelecionadoNaGrid();

        if (consultaExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir a consulta?"
                ,
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(consultaExcluido);
                    controller.atualizarTabela(grdConsultas);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (ConsultaException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            String dataHora = fEdtData.getText() + " " + fEdtHora.getText();
            Paciente paciente = (Paciente) cbxSelecionarPaciente.getSelectedItem();
            Medico medico = (Medico) cbxSelecionarMedico.getSelectedItem();
            Enfermeira enfermeira = (Enfermeira) cbxSelecionarEnfermeira.getSelectedItem();
            
            if (idConsultaEditando > 0) {
                controller.atualizar(
                    idConsultaEditando,
                    dataHora,
                    txtObeservacoes.getText(),
                    paciente,
                    medico,
                    enfermeira,
                    procedimentosSelecionados
                );
            } else {
                controller.cadastrar(
                    dataHora,
                    txtObeservacoes.getText(),
                    paciente,
                    medico,
                    enfermeira,
                    procedimentosSelecionados
                );
            }
            
            this.idConsultaEditando = -1;
            controller.atualizarTabela(grdConsultas);
            this.habilitarFormulario(false);
            this.limparFormulario();
            
        } catch (ConsultaException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnAdicionarProcedimentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarProcedimentoActionPerformed
        Procedimento procedimento = (Procedimento) cbxSelecionarProcedimento.getSelectedItem();
        if (procedimento != null) {
            if (!procedimentosSelecionados.contains(procedimento)) {
                procedimentosSelecionados.add(procedimento);
                atualizarListaProcedimentos();
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Este procedimento já foi adicionado",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnAdicionarProcedimentoActionPerformed

    private void listProcedimentosSelecionadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listProcedimentosSelecionadosKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            int index = listProcedimentosSelecionados.getSelectedIndex();
            if (index != -1) {
                procedimentosSelecionados.remove(index);
                atualizarListaProcedimentos();
            }
        }
    }//GEN-LAST:event_listProcedimentosSelecionadosKeyPressed

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
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionarProcedimento;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarEnfermeira;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarMedico;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarPaciente;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarProcedimento;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtData;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtHora;
    private com.ifcolab.estetify.components.CustomTable grdConsultas;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblObservacoes;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JList<Procedimento> listProcedimentosSelecionados;
    private javax.swing.JScrollPane scrollProcedimentos;
    private javax.swing.JScrollPane tmConsultas;
    private com.ifcolab.estetify.components.CustomTextArea txtObeservacoes;
    private javax.swing.JScrollPane txtObservacoesScrollPane;
    // End of variables declaration//GEN-END:variables
}
