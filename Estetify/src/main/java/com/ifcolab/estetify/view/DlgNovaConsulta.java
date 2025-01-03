package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.controller.EnfermeiraController;
import com.ifcolab.estetify.controller.MedicoController;
import com.ifcolab.estetify.controller.PacienteController;
import com.ifcolab.estetify.controller.ProcedimentoController;
import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class DlgNovaConsulta extends javax.swing.JDialog {

    private final ConsultaController controller;
    private final PacienteController pacienteController;
    private final MedicoController medicoController;
    private final EnfermeiraController enfermeiraController;
    private final ProcedimentoController procedimentoController;
    private ConfiguracaoSistema config;
    private int idConsultaEditando;
    private boolean consultaCadastrada;
    private boolean consultaAlterada;
    private List<Procedimento> procedimentosSelecionados;
    
    public DlgNovaConsulta(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        // Inicializa os controllers
        controller = new ConsultaController();
        pacienteController = new PacienteController();
        medicoController = new MedicoController();
        enfermeiraController = new EnfermeiraController();
        procedimentoController = new ProcedimentoController();
        
        // Inicializa a configuração primeiro
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        config = configDAO.getConfiguracao();
        
        if (config == null) {
            JOptionPane.showMessageDialog(this,
                "Erro ao carregar configurações do sistema",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            this.dispose();
            return;
        }
        
        // Inicializa as outras variáveis
        idConsultaEditando = -1;
        consultaCadastrada = false;
        consultaAlterada = false;
        procedimentosSelecionados = new ArrayList<>();
        
        // Configura os componentes depois de ter a configuração
        this.configurarComponentes();
        this.carregarCombos();
        this.configurarHorarioInicial();
        this.habilitarFormulario(false);
    }
    
    public boolean isConsultaCadastrada() {
        return consultaCadastrada;
    }
    
    public boolean isConsultaAlterada() {
        return consultaAlterada;
    }

        private void configurarComponentes() {
        try {
            // Configurar máscara da data
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            maskData.install(fEdtData);

            // Configurar máscara da hora
            MaskFormatter maskHora = new MaskFormatter("##:##");
            maskHora.setPlaceholderCharacter('_');
            maskHora.install(fEdtHora);

            // Configurar tooltip com horário de funcionamento
            String tooltip = String.format("Horário de funcionamento: %s às %s", 
                config.getHorarioAbertura().format(DateTimeFormatter.ofPattern("HH:mm")),
                config.getHorarioFechamento().format(DateTimeFormatter.ofPattern("HH:mm")));
            fEdtHora.setToolTipText(tooltip);

        } catch (ParseException ex) {
            Logger.getLogger(DlgNovaConsulta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    private void carregarCombos() {
        // Carregar médicos
        cbxSelecionarMedico.removeAllItems();
        medicoController.findAll().forEach(medico -> {
            cbxSelecionarMedico.addItem(medico);
        });
        
        // Carregar enfermeiras
        cbxSelecionarEnfermeira.removeAllItems();
        enfermeiraController.findAll().forEach(enfermeira -> {
            cbxSelecionarEnfermeira.addItem(enfermeira);
        });
        
        // Carregar pacientes
        cbxSelecionarPaciente.removeAllItems();
        pacienteController.findAll().forEach(paciente -> {
            cbxSelecionarPaciente.addItem(paciente);
        });
        
        // Carregar procedimentos disponíveis
        cbxSelecionarProcedimento.removeAllItems();
        procedimentoController.findAll().forEach(procedimento -> {
            cbxSelecionarProcedimento.addItem(procedimento);
        });
    }

    private void configurarHorarioInicial() {
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime proximaData = agora;

        // Encontra o próximo dia útil
        while (!config.isDiaFuncionamento(proximaData.getDayOfWeek())) {
            proximaData = proximaData.plusDays(1);
        }

        // Configura o horário
        LocalTime horario;
        if (proximaData.toLocalDate().equals(agora.toLocalDate())) {
            // Se for hoje, pega o próximo horário disponível
            horario = agora.toLocalTime();

            if (horario.isBefore(config.getHorarioAbertura())) {
                horario = config.getHorarioAbertura();
            } else if (horario.isAfter(config.getHorarioFechamento())) {
                // Se passou do horário, vai para o próximo dia
                proximaData = proximaData.plusDays(1);
                while (!config.isDiaFuncionamento(proximaData.getDayOfWeek())) {
                    proximaData = proximaData.plusDays(1);
                }
                horario = config.getHorarioAbertura();
            } else {
                // Ajusta para o próximo intervalo disponível
                horario = config.proximoHorarioDisponivel(horario);

                if (horario.isAfter(config.getHorarioFechamento())) {
                    proximaData = proximaData.plusDays(1);
                    while (!config.isDiaFuncionamento(proximaData.getDayOfWeek())) {
                        proximaData = proximaData.plusDays(1);
                    }
                    horario = config.getHorarioAbertura();
                }
            }
        } else {
            horario = config.getHorarioAbertura();
        }

        // Formata e define os valores
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter horaFormatter = DateTimeFormatter.ofPattern("HH:mm");

        fEdtData.setText(proximaData.format(dataFormatter));
        fEdtHora.setText(horario.format(horaFormatter));
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
        lstProcedimentos.setEnabled(habilitar);
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
    
    public void preencherFormulario(Consulta consulta) {
        if (consulta == null) return;
        
        this.idConsultaEditando = consulta.getId();
        cbxSelecionarPaciente.setSelectedItem(consulta.getPaciente());
        cbxSelecionarMedico.setSelectedItem(consulta.getMedico());
        cbxSelecionarEnfermeira.setSelectedItem(consulta.getEnfermeira());
        
        // Formata data e hora
        fEdtData.setText(consulta.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        fEdtHora.setText(consulta.getDataHora().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm")));
        
        txtObeservacoes.setText(consulta.getObservacoes());
        procedimentosSelecionados = new ArrayList<>(consulta.getProcedimentos());
        atualizarListaProcedimentos();
        
        this.habilitarFormulario(true);
    }
    
    private Consulta getObjetoSelecionadoNaGrid() {
        int rowCliked = grdConsultas.getSelectedRow();
        if (rowCliked >= 0) {
            // Pega o objeto Consulta que está associado à linha
            return (Consulta) grdConsultas.getModel().getValueAt(rowCliked, 0);
        }
        return null;
    }    
    
    private void atualizarListaProcedimentos() {
        DefaultListModel<Procedimento> model = new DefaultListModel<>();
        for (Procedimento proc : procedimentosSelecionados) {
            model.addElement(proc);
        }
        lstProcedimentos.setModel(model);
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
        lstProcedimentos = new javax.swing.JList<>();
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
        txtObservacoesScrollPane.setBounds(240, 206, 720, 130);

        fEdtHora.setText("Hora");
        fEdtHora.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fEdtHoraPropertyChange(evt);
            }
        });
        getContentPane().add(fEdtHora);
        fEdtHora.setBounds(60, 280, 160, 38);

        fEdtData.setText("Data");
        fEdtData.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                fEdtDataPropertyChange(evt);
            }
        });
        getContentPane().add(fEdtData);
        fEdtData.setBounds(60, 210, 160, 38);
        getContentPane().add(cbxSelecionarProcedimento);
        cbxSelecionarProcedimento.setBounds(980, 140, 250, 44);

        cbxSelecionarEnfermeira.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxSelecionarEnfermeiraPropertyChange(evt);
            }
        });
        getContentPane().add(cbxSelecionarEnfermeira);
        cbxSelecionarEnfermeira.setBounds(700, 140, 250, 44);

        cbxSelecionarMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSelecionarMedicoActionPerformed(evt);
            }
        });
        cbxSelecionarMedico.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxSelecionarMedicoPropertyChange(evt);
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

        lstProcedimentos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lstProcedimentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                lstProcedimentosKeyPressed(evt);
            }
        });
        scrollProcedimentos.setViewportView(lstProcedimentos);

        getContentPane().add(scrollProcedimentos);
        scrollProcedimentos.setBounds(980, 200, 300, 130);

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
        grdConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdConsultasMouseClicked(evt);
            }
        });
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

    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
    try {
        // Converte data e hora para LocalDateTime
        String dataHoraStr = fEdtData.getText() + " " + fEdtHora.getText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime dataHora = LocalDateTime.parse(dataHoraStr, formatter);
        
        // Obtém os itens selecionados
        Paciente paciente = (Paciente) cbxSelecionarPaciente.getSelectedItem();
        Medico medico = (Medico) cbxSelecionarMedico.getSelectedItem();
        Enfermeira enfermeira = (Enfermeira) cbxSelecionarEnfermeira.getSelectedItem();
        String observacoes = txtObeservacoes.getText();
        
        if (idConsultaEditando > 0) {
            controller.atualizar(
                idConsultaEditando,
                dataHora,
                observacoes,
                paciente,
                medico,
                enfermeira,
                procedimentosSelecionados
            );
            consultaAlterada = true;
        } else {
            controller.cadastrar(
                dataHora,
                observacoes,
                paciente,
                medico,
                enfermeira,
                procedimentosSelecionados
            );
            consultaCadastrada = true;
        }

           this.idConsultaEditando = -1;
           controller.atualizarTabela(grdConsultas);
           this.habilitarFormulario(false);
           this.limparFormulario();

           dispose();

       } catch (ConsultaException e) {
           System.err.println(e.getMessage());
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

    private void lstProcedimentosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lstProcedimentosKeyPressed
        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_DELETE) {
            int index = lstProcedimentos.getSelectedIndex();
            if (index != -1) {
                procedimentosSelecionados.remove(index);
                atualizarListaProcedimentos();
            }
        }
    }//GEN-LAST:event_lstProcedimentosKeyPressed

    private void fEdtDataPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fEdtDataPropertyChange

    }//GEN-LAST:event_fEdtDataPropertyChange

    private void fEdtHoraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_fEdtHoraPropertyChange

    }//GEN-LAST:event_fEdtHoraPropertyChange

    private void cbxSelecionarMedicoPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxSelecionarMedicoPropertyChange

    }//GEN-LAST:event_cbxSelecionarMedicoPropertyChange

    private void cbxSelecionarEnfermeiraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxSelecionarEnfermeiraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSelecionarEnfermeiraPropertyChange

    private void grdConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdConsultasMouseClicked

    }//GEN-LAST:event_grdConsultasMouseClicked

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
    private javax.swing.JList<Procedimento> lstProcedimentos;
    private javax.swing.JScrollPane scrollProcedimentos;
    private javax.swing.JScrollPane tmConsultas;
    private com.ifcolab.estetify.components.CustomTextArea txtObeservacoes;
    private javax.swing.JScrollPane txtObservacoesScrollPane;
    // End of variables declaration//GEN-END:variables
}
