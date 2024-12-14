package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AgendaController;
import com.ifcolab.estetify.controller.MedicoController;
import com.ifcolab.estetify.controller.tablemodel.TMViewAgenda;
import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.view.DlgAgendarConsulta;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;



public class FrAgenda extends javax.swing.JDialog {

    private AgendaController controller;
    private MedicoController medicoController;
    
    public FrAgenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        controller = new AgendaController();
        medicoController = new MedicoController();
        
        this.adicionarMascaraNosCampos();
        this.carregarMedicos();
        this.configurarListas();
        this.limparCampos();
        
        // Adicionar listener de seleção na tabela
        grdAgenda.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                atualizarDetalhesAgenda();
            }
        });
        
        controller.atualizarTabela(grdAgenda);        

    }
    
    private void adicionarMascaraNosCampos() {
        try {
            // Máscara para data de cadastro
            MaskFormatter maskDataCadastro = new MaskFormatter("##/##/####");
            maskDataCadastro.setPlaceholderCharacter('_');
            maskDataCadastro.install(fEdtDataCadastro);
            
            // Máscara para data de filtro
            MaskFormatter maskDataFiltro = new MaskFormatter("##/##/####");
            maskDataFiltro.setPlaceholderCharacter('_');
            maskDataFiltro.install(fEdtDataFiltro);
            
        } catch (ParseException ex) {
            Logger.getLogger(FrAgenda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void carregarMedicos() {
        List<Medico> medicos = medicoController.findAll();
        DefaultComboBoxModel<Medico> modelCadastro = new DefaultComboBoxModel<>();
        DefaultComboBoxModel<Medico> modelFiltro = new DefaultComboBoxModel<>();
        
        modelCadastro.addElement(null);
        modelFiltro.addElement(null);
        
        for (Medico medico : medicos) {
            modelCadastro.addElement(medico);
            modelFiltro.addElement(medico);
        }
        
        cbxSelecionarMedicoCadastro.setModel(modelCadastro);
        cbxSelecionarMedicoFiltro.setModel(modelFiltro);
    }
    
    private void limparCampos() {
        cbxSelecionarMedicoCadastro.setSelectedItem(null);
        fEdtDataCadastro.setText("");
        cbxSelecionarMedicoFiltro.setSelectedItem(null);
        fEdtDataFiltro.setText("");
        limparListas();
    }
    
    private void limparListas() {
        lstHorariosDisponiveis.setModel(new DefaultListModel<>());
        lstConsultasMarcadas.setModel(new DefaultListModel<>());
    }
    
    private void atualizarDetalhesAgenda() {
        int row = grdAgenda.getSelectedRow();
        if (row != -1) {
            TMViewAgenda model = (TMViewAgenda) grdAgenda.getModel();
            Agenda agenda = model.getAgenda(row);
            
            // Atualizar lista de horários disponíveis
            DefaultListModel<LocalTime> modelHorarios = new DefaultListModel<>();
            List<LocalTime> horarios = controller.getHorariosDisponiveis(agenda);
            if (horarios != null) {
                horarios.forEach(modelHorarios::addElement);
            }
            lstHorariosDisponiveis.setModel(modelHorarios);
            
            // Atualizar lista de consultas
            DefaultListModel<Consulta> modelConsultas = new DefaultListModel<>();
            List<Consulta> consultas = controller.listarConsultasDoDia(agenda);
            if (consultas != null) {
                consultas.forEach(modelConsultas::addElement);
            }
            lstConsultasMarcadas.setModel(modelConsultas);
            
        } else {
            limparListas();
        }
    }

    private void configurarListas() {
        // Configurar renderizador para a lista de horários
        lstHorariosDisponiveis.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            LocalTime horario = (LocalTime) value;
            String texto = horario.format(DateTimeFormatter.ofPattern("HH:mm"));
            return new javax.swing.JLabel(texto);
        });
        
        // Configurar renderizador para a lista de consultas
        lstConsultasMarcadas.setCellRenderer((list, value, index, isSelected, cellHasFocus) -> {
            Consulta consulta = (Consulta) value;
            String texto = String.format("%s - %s", 
                consulta.getDataHora().format(DateTimeFormatter.ofPattern("HH:mm")),
                consulta.getPaciente().getNome());
            return new javax.swing.JLabel(texto);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDataCadastro = new javax.swing.JLabel();
        lblSelecionarMedicoCadastro = new javax.swing.JLabel();
        fEdtDataCadastro = new com.ifcolab.estetify.components.CustomFormattedTextField();
        btnFiltrarAgenda = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnAgendarConsulta = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnLimparFiltros = new com.ifcolab.estetify.components.SecondaryCustomButton();
        cbxSelecionarMedicoCadastro = new com.ifcolab.estetify.components.CustomComboBox();
        tmAgenda = new javax.swing.JScrollPane();
        grdAgenda = new com.ifcolab.estetify.components.CustomTable();
        cbxSelecionarMedicoFiltro = new com.ifcolab.estetify.components.CustomComboBox();
        btnCancelarConsulta = new com.ifcolab.estetify.components.SecondaryCustomButton();
        lblSelecionarMedicoFiltro = new javax.swing.JLabel();
        lblDataFiltro = new javax.swing.JLabel();
        fEdtDataFiltro = new com.ifcolab.estetify.components.CustomFormattedTextField();
        scrollListaConsultas = new javax.swing.JScrollPane();
        lstConsultasMarcadas = new javax.swing.JList();
        scrollListaHorarios = new javax.swing.JScrollPane();
        lstHorariosDisponiveis = new javax.swing.JList();
        lblCadastrarAgenda = new javax.swing.JLabel();
        lblFiltrarAgenda = new javax.swing.JLabel();
        lblLogo = new javax.swing.JLabel();
        lblEstetify = new javax.swing.JLabel();
        lblSidebar = new javax.swing.JLabel();
        lblFiltrarAgenda1 = new javax.swing.JLabel();
        lblFiltrarAgenda2 = new javax.swing.JLabel();
        btnCadastrarAgenda = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnLimpar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundFiltroAgenda = new javax.swing.JLabel();
        lblBackgroundCadastro1 = new javax.swing.JLabel();
        lblBackgroundCadastroAgenda = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
        getContentPane().setLayout(null);

        lblDataCadastro.setForeground(new java.awt.Color(51, 51, 51));
        lblDataCadastro.setText("Data");
        getContentPane().add(lblDataCadastro);
        lblDataCadastro.setBounds(300, 210, 170, 17);

        lblSelecionarMedicoCadastro.setForeground(new java.awt.Color(51, 51, 51));
        lblSelecionarMedicoCadastro.setText("Selecionar Médico");
        getContentPane().add(lblSelecionarMedicoCadastro);
        lblSelecionarMedicoCadastro.setBounds(300, 140, 170, 17);

        fEdtDataCadastro.setText("Data de Cadastro");
        getContentPane().add(fEdtDataCadastro);
        fEdtDataCadastro.setBounds(290, 230, 190, 38);

        btnFiltrarAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnFiltrarAgenda.setText(" Filtrar Agenda");
        btnFiltrarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarAgendaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFiltrarAgenda);
        btnFiltrarAgenda.setBounds(840, 100, 210, 30);

        btnAgendarConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAgendarConsulta.setText(" Agendar Consulta");
        btnAgendarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgendarConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgendarConsulta);
        btnAgendarConsulta.setBounds(270, 790, 210, 30);

        btnLimparFiltros.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btnLimparFiltros.setText(" Limpar Filtros");
        btnLimparFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparFiltrosActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimparFiltros);
        btnLimparFiltros.setBounds(1080, 100, 200, 30);
        getContentPane().add(cbxSelecionarMedicoCadastro);
        cbxSelecionarMedicoCadastro.setBounds(290, 160, 450, 44);

        grdAgenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tmAgenda.setViewportView(grdAgenda);

        getContentPane().add(tmAgenda);
        tmAgenda.setBounds(280, 320, 1030, 250);
        getContentPane().add(cbxSelecionarMedicoFiltro);
        cbxSelecionarMedicoFiltro.setBounds(840, 160, 450, 44);

        btnCancelarConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btnCancelarConsulta.setText(" Cancelar Consulta");
        btnCancelarConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelarConsulta);
        btnCancelarConsulta.setBounds(500, 790, 200, 30);

        lblSelecionarMedicoFiltro.setForeground(new java.awt.Color(51, 51, 51));
        lblSelecionarMedicoFiltro.setText("Selecionar Médico");
        getContentPane().add(lblSelecionarMedicoFiltro);
        lblSelecionarMedicoFiltro.setBounds(850, 140, 170, 17);

        lblDataFiltro.setForeground(new java.awt.Color(51, 51, 51));
        lblDataFiltro.setText("Data");
        getContentPane().add(lblDataFiltro);
        lblDataFiltro.setBounds(850, 210, 170, 17);

        fEdtDataFiltro.setText("Data de Filtro");
        getContentPane().add(fEdtDataFiltro);
        fEdtDataFiltro.setBounds(840, 230, 190, 38);

        lstConsultasMarcadas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrollListaConsultas.setViewportView(lstConsultasMarcadas);

        getContentPane().add(scrollListaConsultas);
        scrollListaConsultas.setBounds(820, 620, 480, 150);

        lstHorariosDisponiveis.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrollListaHorarios.setViewportView(lstHorariosDisponiveis);

        getContentPane().add(scrollListaHorarios);
        scrollListaHorarios.setBounds(290, 620, 480, 150);

        lblCadastrarAgenda.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 13)); // NOI18N
        lblCadastrarAgenda.setForeground(new java.awt.Color(51, 51, 51));
        lblCadastrarAgenda.setText("Filtrar Agenda");
        getContentPane().add(lblCadastrarAgenda);
        lblCadastrarAgenda.setBounds(840, 70, 230, 17);

        lblFiltrarAgenda.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 13)); // NOI18N
        lblFiltrarAgenda.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltrarAgenda.setText("Consultas Marcadas");
        getContentPane().add(lblFiltrarAgenda);
        lblFiltrarAgenda.setBounds(820, 600, 230, 17);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo45x40.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(10, 10, 50, 50);

        lblEstetify.setFont(new java.awt.Font("Fira Sans Condensed Medium", 0, 18)); // NOI18N
        lblEstetify.setForeground(new java.awt.Color(51, 51, 51));
        lblEstetify.setText("Estetify");
        getContentPane().add(lblEstetify);
        lblEstetify.setBounds(60, 30, 90, 22);

        lblSidebar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidebar.png"))); // NOI18N
        getContentPane().add(lblSidebar);
        lblSidebar.setBounds(-460, 0, 750, 900);

        lblFiltrarAgenda1.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 13)); // NOI18N
        lblFiltrarAgenda1.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltrarAgenda1.setText("Cadastrar Agenda");
        getContentPane().add(lblFiltrarAgenda1);
        lblFiltrarAgenda1.setBounds(290, 70, 230, 17);

        lblFiltrarAgenda2.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 13)); // NOI18N
        lblFiltrarAgenda2.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltrarAgenda2.setText("Horários Disponíveis");
        getContentPane().add(lblFiltrarAgenda2);
        lblFiltrarAgenda2.setBounds(290, 600, 230, 17);

        btnCadastrarAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnCadastrarAgenda.setText(" Cadastrar Agenda");
        btnCadastrarAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarAgendaActionPerformed(evt);
            }
        });
        getContentPane().add(btnCadastrarAgenda);
        btnCadastrarAgenda.setBounds(290, 100, 210, 30);

        btnLimpar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        btnLimpar.setText(" Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });
        getContentPane().add(btnLimpar);
        btnLimpar.setBounds(530, 100, 200, 30);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Visualize e realize novos agendamentos de consultas");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(280, 40, 400, 17);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCad.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(230, 590, 1100, 190);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Agenda");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(280, 20, 210, 22);

        lblBackgroundFiltroAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCadAgenda.png"))); // NOI18N
        getContentPane().add(lblBackgroundFiltroAgenda);
        lblBackgroundFiltroAgenda.setBounds(780, 40, 560, 270);

        lblBackgroundCadastro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCad.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro1);
        lblBackgroundCadastro1.setBounds(230, 300, 1100, 280);

        lblBackgroundCadastroAgenda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCadAgenda.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastroAgenda);
        lblBackgroundCadastroAgenda.setBounds(230, 40, 560, 270);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgendarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgendarConsultaActionPerformed
        int row = grdAgenda.getSelectedRow();
        if (row != -1) {
            TMViewAgenda model = (TMViewAgenda) grdAgenda.getModel();
            Agenda agenda = model.getAgenda(row);
            
            DlgAgendarConsulta dialog = new DlgAgendarConsulta(this, true, agenda);
            dialog.setVisible(true);
            
            if (dialog.isConsultaAgendada()) {
                controller.atualizarTabela(grdAgenda);
                atualizarDetalhesAgenda();
            }
        } else {
            JOptionPane.showMessageDialog(this,
                "Selecione uma agenda para agendar a consulta.",
                "Aviso",
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnAgendarConsultaActionPerformed

    private void btnLimparFiltrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparFiltrosActionPerformed
        cbxSelecionarMedicoFiltro.setSelectedItem(null);
        fEdtDataFiltro.setText("");
        controller.atualizarTabela(grdAgenda);
    }//GEN-LAST:event_btnLimparFiltrosActionPerformed

    private void btnFiltrarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarAgendaActionPerformed
        try {
            Medico medico = (Medico) cbxSelecionarMedicoFiltro.getSelectedItem();
            String dataStr = fEdtDataFiltro.getText().trim();
            
            if (medico != null && !dataStr.isEmpty()) {
                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                Agenda agenda = controller.buscarPorMedicoEData(medico, data);
                if (agenda != null) {
                    TMViewAgenda model = new TMViewAgenda(Arrays.asList(agenda));
                    grdAgenda.setModel(model);
                }
            } else if (medico != null) {
                controller.filtrarTabelaPorMedico(grdAgenda, medico);
            } else if (!dataStr.isEmpty()) {
                LocalDate data = LocalDate.parse(dataStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                controller.filtrarTabelaPorData(grdAgenda, data);
            } else {
                controller.atualizarTabela(grdAgenda);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnFiltrarAgendaActionPerformed

    private void btnCancelarConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarConsultaActionPerformed
        try {
            Medico medico = (Medico) cbxSelecionarMedicoCadastro.getSelectedItem();
            String data = fEdtDataCadastro.getText();
            
            controller.cadastrarComHorariosDefault(medico, data);
            controller.atualizarTabela(grdAgenda);
            limparCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Agenda cadastrada com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCancelarConsultaActionPerformed

    private void btnCadastrarAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarAgendaActionPerformed
        try {
            Medico medico = (Medico) cbxSelecionarMedicoCadastro.getSelectedItem();
            String data = fEdtDataCadastro.getText();
            
            controller.cadastrarComHorariosDefault(medico, data);
            controller.atualizarTabela(grdAgenda);
            limparCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Agenda cadastrada com sucesso!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, 
                ex.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCadastrarAgendaActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        cbxSelecionarMedicoCadastro.setSelectedItem(null);
        fEdtDataCadastro.setText("");
    }//GEN-LAST:event_btnLimparActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAgendarConsulta;
    private com.ifcolab.estetify.components.PrimaryCustomButton btnCadastrarAgenda;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnCancelarConsulta;
    private com.ifcolab.estetify.components.PrimaryCustomButton btnFiltrarAgenda;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnLimpar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnLimparFiltros;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarMedicoCadastro;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarMedicoFiltro;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtDataCadastro;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtDataFiltro;
    private com.ifcolab.estetify.components.CustomTable grdAgenda;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundCadastro1;
    private javax.swing.JLabel lblBackgroundCadastroAgenda;
    private javax.swing.JLabel lblBackgroundFiltroAgenda;
    private javax.swing.JLabel lblCadastrarAgenda;
    private javax.swing.JLabel lblDataCadastro;
    private javax.swing.JLabel lblDataFiltro;
    private javax.swing.JLabel lblEstetify;
    private javax.swing.JLabel lblFiltrarAgenda;
    private javax.swing.JLabel lblFiltrarAgenda1;
    private javax.swing.JLabel lblFiltrarAgenda2;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSelecionarMedicoCadastro;
    private javax.swing.JLabel lblSelecionarMedicoFiltro;
    private javax.swing.JLabel lblSidebar;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JList lstConsultasMarcadas;
    private javax.swing.JList lstHorariosDisponiveis;
    private javax.swing.JScrollPane scrollListaConsultas;
    private javax.swing.JScrollPane scrollListaHorarios;
    private javax.swing.JScrollPane tmAgenda;
    // End of variables declaration//GEN-END:variables
}
