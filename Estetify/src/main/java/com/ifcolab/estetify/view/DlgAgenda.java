package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.controller.EnfermeiraController;
import com.ifcolab.estetify.controller.MedicoController;
import com.ifcolab.estetify.controller.tablemodel.TMConsultaDia;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.view.DlgNovaConsulta;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeEvent;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;


public class DlgAgenda extends javax.swing.JDialog {

    private ConsultaController consultaController;
    private MedicoController medicoController;
    private EnfermeiraController enfermeiraController;
    private TMConsultaDia tableModel;
    private Map<Date, List<Consulta>> consultasPorDia;
    
    public DlgAgenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        consultaController = new ConsultaController();
        medicoController = new MedicoController();
        enfermeiraController = new EnfermeiraController();
        tableModel = new TMConsultaDia(List.of());
        
        configurarComponentes();
        carregarDados();
    }


    private void configurarComponentes() {
        // Configurar calendário
        consultaCalendar.addPropertyChangeListener("date", (PropertyChangeEvent evt) -> {
            atualizarConsultasDoDia();
        });
        
        // Configurar tabela
        tblConsultas.setModel(tableModel);
        
        // Configurar ComboBoxes
        configurarComboBoxes();
    }
    
    private void configurarComboBoxes() {
        // Adicionar item vazio primeiro
        cboMedico.addItem(null);
        cboEnfermeira.addItem(null);
        
        // Carregar dados
        List<Medico> medicos = medicoController.findAll();
        List<Enfermeira> enfermeiras = enfermeiraController.findAll();
        
        for (Medico medico : medicos) {
            cboMedico.addItem(medico);
        }
        
        for (Enfermeira enfermeira : enfermeiras) {
            cboEnfermeira.addItem(enfermeira);
        }
    }
    
    private void carregarDados() {
        List<Consulta> consultas = consultaController.findAll();
        tableModel.atualizarConsultas(consultas);
    }
    
    private void atualizarConsultasDoDia() {
        LocalDate dataSelecionada = consultaCalendar.getSelectedDate();
        List<Consulta> consultasDoDia = consultaCalendar.getConsultasNaData(dataSelecionada);
        tableModel.atualizarConsultas(consultasDoDia);
    }
    
    private void filtrarConsultas() {
        Medico medicoSelecionado = (Medico) cboMedico.getSelectedItem();
        Enfermeira enfermeiraSelecionada = (Enfermeira) cboEnfermeira.getSelectedItem();
        
        List<Consulta> consultas = consultaController.findAll();
        List<Consulta> consultasFiltradas = consultas.stream()
            .filter(c -> (medicoSelecionado == null || c.getMedico().equals(medicoSelecionado)) &&
                        (enfermeiraSelecionada == null || c.getEnfermeira().equals(enfermeiraSelecionada)))
            .toList();
        
        tableModel.atualizarConsultas(consultasFiltradas);
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblConsultas = new com.ifcolab.estetify.components.CustomTable();
        consultaCalendar = new com.ifcolab.estetify.components.ConsultaCalendar();
        btnNovaConsulta = new com.ifcolab.estetify.components.PrimaryCustomButton();
        cboEnfermeira = new com.ifcolab.estetify.components.CustomComboBox();
        cboMedico = new com.ifcolab.estetify.components.CustomComboBox();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
        getContentPane().setLayout(null);

        tblConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tblConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblConsultasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblConsultas);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(50, 586, 1260, 220);
        getContentPane().add(consultaCalendar);
        consultaCalendar.setBounds(50, 80, 1260, 420);

        btnNovaConsulta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnNovaConsulta.setText(" Nova Consulta");
        btnNovaConsulta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovaConsultaActionPerformed(evt);
            }
        });
        getContentPane().add(btnNovaConsulta);
        btnNovaConsulta.setBounds(60, 550, 170, 30);

        cboEnfermeira.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboEnfermeiraItemStateChanged(evt);
            }
        });
        getContentPane().add(cboEnfermeira);
        cboEnfermeira.setBounds(780, 550, 310, 30);

        cboMedico.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboMedicoItemStateChanged(evt);
            }
        });
        getContentPane().add(cboMedico);
        cboMedico.setBounds(440, 550, 310, 30);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Adicione, edite ou remova informações dos médicos, incluindo especialidades e dados de contato.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 780, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Agenda");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(0, 530, 1330, 290);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(0, 40, 1390, 500);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNovaConsultaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovaConsultaActionPerformed
        DlgNovaConsulta dialog = new DlgNovaConsulta(null, true);
        dialog.setVisible(true);
        if (dialog.isConsultaCadastrada()) {
            carregarDados();
        }
    }//GEN-LAST:event_btnNovaConsultaActionPerformed

    private void cboMedicoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboMedicoItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            filtrarConsultas();
        }
    }//GEN-LAST:event_cboMedicoItemStateChanged

    private void cboEnfermeiraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboEnfermeiraItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            filtrarConsultas();
        }
    }//GEN-LAST:event_cboEnfermeiraItemStateChanged

    private void tblConsultasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblConsultasMouseClicked
        if (evt.getClickCount() == 2) {
            int row = tblConsultas.getSelectedRow();
            if (row >= 0) {
                Consulta consulta = tableModel.getConsulta(row);
                if (consulta != null) {
                    exibirDetalhesConsulta(consulta);
                }
            }
        }
    }//GEN-LAST:event_tblConsultasMouseClicked

    private void exibirDetalhesConsulta(Consulta consulta) {
        // Aqui você pode abrir um novo dialog para mostrar/editar os detalhes
        // da consulta selecionada
        DlgNovaConsulta dialog = new DlgNovaConsulta(null, true);
        dialog.preencherFormulario(consulta); // Método que precisamos adicionar ao DlgNovaConsulta
        dialog.setVisible(true);
        if (dialog.isConsultaAlterada()) { // Novo método que precisamos adicionar
            carregarDados();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnNovaConsulta;
    private com.ifcolab.estetify.components.CustomComboBox cboEnfermeira;
    private com.ifcolab.estetify.components.CustomComboBox cboMedico;
    private com.ifcolab.estetify.components.ConsultaCalendar consultaCalendar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private com.ifcolab.estetify.components.CustomTable tblConsultas;
    // End of variables declaration//GEN-END:variables
}
