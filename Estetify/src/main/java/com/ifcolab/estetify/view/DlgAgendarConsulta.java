package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AgendaController;
import com.ifcolab.estetify.controller.EnfermeiraController;
import com.ifcolab.estetify.controller.PacienteController;
import com.ifcolab.estetify.controller.ProcedimentoController;
import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;



public class DlgAgendarConsulta extends javax.swing.JDialog {

    private final Agenda agendaSelecionada;
    private boolean consultaAgendada;
    private final AgendaController agendaController;
    private final PacienteController pacienteController;
    private final EnfermeiraController enfermeiraController;
    private final ProcedimentoController procedimentoController;
    
    
    
    public DlgAgendarConsulta(java.awt.Dialog parent, boolean modal, Agenda agenda) {
        super(parent, modal);
        initComponents();
        
        this.agendaSelecionada = agenda;
        this.consultaAgendada = false;
        
        this.agendaController = new AgendaController();
        this.pacienteController = new PacienteController();
        this.enfermeiraController = new EnfermeiraController();
        this.procedimentoController = new ProcedimentoController();
        
        configurarComponentes();
    }
    
    private void configurarComponentes() {
        // Carregar horários disponíveis
        DefaultComboBoxModel<LocalTime> modelHorarios = new DefaultComboBoxModel<>();
        List<LocalTime> horariosDisponiveis = agendaController.getHorariosDisponiveis(agendaSelecionada);
        if (horariosDisponiveis != null && !horariosDisponiveis.isEmpty()) {
            horariosDisponiveis.forEach(modelHorarios::addElement);
        }
        cbxSelecionarHorario.setModel(modelHorarios);
        
        // Carregar pacientes
        DefaultComboBoxModel<Paciente> modelPacientes = new DefaultComboBoxModel<>();
        pacienteController.findAll().forEach(modelPacientes::addElement);
        cbxSelecionarPaciente.setModel(modelPacientes);
        
        // Carregar enfermeiras
        DefaultComboBoxModel<Enfermeira> modelEnfermeiras = new DefaultComboBoxModel<>();
        enfermeiraController.findAll().forEach(modelEnfermeiras::addElement);
        cbxSelecionarEnfermeira.setModel(modelEnfermeiras);
        
        // Carregar procedimentos
        DefaultListModel<Procedimento> modelProcedimentos = new DefaultListModel<>();
        procedimentoController.findAll().forEach(modelProcedimentos::addElement);
        lstProcedimentos.setModel(modelProcedimentos);
    }
    
    public boolean isConsultaAgendada() {
        return consultaAgendada;
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCPF = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblObservacoes = new javax.swing.JLabel();
        scrollObservacoes = new javax.swing.JScrollPane();
        txtObservacoes = new com.ifcolab.estetify.components.CustomTextArea();
        cbxSelecionarEnfermeira = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarHorario = new com.ifcolab.estetify.components.CustomComboBox();
        cbxSelecionarPaciente = new com.ifcolab.estetify.components.CustomComboBox();
        btnConfirmar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnCancelar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        scrollProcedimentos = new javax.swing.JScrollPane();
        lstProcedimentos = new javax.swing.JList();
        lblEmail1 = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(850, 550));
        getContentPane().setLayout(null);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("Enfermeira");
        getContentPane().add(lblCPF);
        lblCPF.setBounds(50, 260, 190, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Paciente");
        getContentPane().add(lblNome);
        lblNome.setBounds(50, 190, 220, 17);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Procedimentos");
        getContentPane().add(lblEmail);
        lblEmail.setBounds(450, 120, 170, 17);

        lblObservacoes.setForeground(new java.awt.Color(51, 51, 51));
        lblObservacoes.setText("Observacoes");
        getContentPane().add(lblObservacoes);
        lblObservacoes.setBounds(50, 330, 110, 17);

        txtObservacoes.setColumns(20);
        txtObservacoes.setRows(5);
        scrollObservacoes.setViewportView(txtObservacoes);

        getContentPane().add(scrollObservacoes);
        scrollObservacoes.setBounds(40, 350, 380, 160);

        cbxSelecionarEnfermeira.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxSelecionarEnfermeiraPropertyChange(evt);
            }
        });
        getContentPane().add(cbxSelecionarEnfermeira);
        cbxSelecionarEnfermeira.setBounds(40, 280, 380, 44);

        cbxSelecionarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxSelecionarHorarioActionPerformed(evt);
            }
        });
        cbxSelecionarHorario.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                cbxSelecionarHorarioPropertyChange(evt);
            }
        });
        getContentPane().add(cbxSelecionarHorario);
        cbxSelecionarHorario.setBounds(40, 140, 380, 44);
        getContentPane().add(cbxSelecionarPaciente);
        cbxSelecionarPaciente.setBounds(40, 210, 380, 44);

        btnConfirmar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnConfirmar.setText(" Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });
        getContentPane().add(btnConfirmar);
        btnConfirmar.setBounds(40, 80, 170, 30);

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnCancelar.setText(" Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(250, 80, 170, 30);

        lstProcedimentos.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        scrollProcedimentos.setViewportView(lstProcedimentos);

        getContentPane().add(scrollProcedimentos);
        scrollProcedimentos.setBounds(450, 140, 360, 370);

        lblEmail1.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail1.setText("Horário");
        getContentPane().add(lblEmail1);
        lblEmail1.setBounds(50, 120, 170, 17);

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(-10, 20, 840, 550);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 850, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbxSelecionarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxSelecionarHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSelecionarHorarioActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        try {
            LocalTime horario = (LocalTime) cbxSelecionarHorario.getSelectedItem();
            if (horario == null) {
                throw new Exception("Selecione um horário disponível.");
            }
            
            Paciente paciente = (Paciente) cbxSelecionarPaciente.getSelectedItem();
            if (paciente == null) {
                throw new Exception("Selecione um paciente.");
            }
            
            Enfermeira enfermeira = (Enfermeira) cbxSelecionarEnfermeira.getSelectedItem();
            if (enfermeira == null) {
                throw new Exception("Selecione uma enfermeira.");
            }
            
            List<Procedimento> procedimentos = lstProcedimentos.getSelectedValuesList();
            if (procedimentos.isEmpty()) {
                throw new Exception("Selecione pelo menos um procedimento.");
            }
            
            String observacoes = txtObservacoes.getText();
            
            agendaController.cadastrarConsulta(
                agendaSelecionada,
                horario,
                paciente,
                enfermeira,
                procedimentos,
                observacoes
            );
            
            consultaAgendada = true;
            JOptionPane.showMessageDialog(this,
                "Consulta agendada com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnConfirmarActionPerformed

    private void cbxSelecionarHorarioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxSelecionarHorarioPropertyChange

    }//GEN-LAST:event_cbxSelecionarHorarioPropertyChange

    private void cbxSelecionarEnfermeiraPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_cbxSelecionarEnfermeiraPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxSelecionarEnfermeiraPropertyChange

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.SecondaryCustomButton btnCancelar;
    private com.ifcolab.estetify.components.PrimaryCustomButton btnConfirmar;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarEnfermeira;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarHorario;
    private com.ifcolab.estetify.components.CustomComboBox cbxSelecionarPaciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEmail1;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblObservacoes;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JList lstProcedimentos;
    private javax.swing.JScrollPane scrollObservacoes;
    private javax.swing.JScrollPane scrollProcedimentos;
    private com.ifcolab.estetify.components.CustomTextArea txtObservacoes;
    // End of variables declaration//GEN-END:variables
}
