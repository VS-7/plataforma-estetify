package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.controller.PacienteController;
import com.ifcolab.estetify.controller.tablemodel.TMViewHistoricoProcedimento;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import javax.swing.DefaultComboBoxModel;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author vitorsrgio
 */
public class DlgHistoricoProcedimento extends javax.swing.JDialog {
    private final ConsultaController consultaController;
    private final PacienteController pacienteController;

    public DlgHistoricoProcedimento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.consultaController = new ConsultaController();
        this.pacienteController = new PacienteController();
        
        configurarComponentes();
        configurarEventos();
    }
    
    private void configurarComponentes() {
        configurarComboBoxPacientes();
        configurarTabela();
    }
    
    private void configurarComboBoxPacientes() {
        List<Paciente> pacientes = pacienteController.findAll();
        DefaultComboBoxModel<Paciente> model = new DefaultComboBoxModel<>();
        
        for (Paciente paciente : pacientes) {
            model.addElement(paciente);
        }
        
        cboPacientes.setModel(model);
    }
    
    private void configurarTabela() {
        grdHistorico.getSelectionModel().addListSelectionListener((e) -> {
            btnVisualizarRelatorio.setEnabled(grdHistorico.getSelectedRow() != -1);
        });
    }
    
    private void configurarEventos() {
        cboPacientes.addActionListener((evt) -> {
            atualizarHistorico();
        });
        
        btnBuscarPaciente.addActionListener((evt) -> {
            atualizarHistorico();
        });
        
        btnVisualizarRelatorio.addActionListener((evt) -> {
            visualizarRelatorio();
        });
    }
    
    private void atualizarHistorico() {
        Paciente pacienteSelecionado = (Paciente) cboPacientes.getSelectedItem();
        
        if (pacienteSelecionado != null) {
            List<Consulta> consultas = consultaController.buscarConsultasPorPaciente(
                pacienteSelecionado.getId()
            );
            
            TMViewHistoricoProcedimento tmHistorico = new TMViewHistoricoProcedimento(consultas);
            grdHistorico.setModel(tmHistorico);
        }
    }
    
    private void visualizarRelatorio() {
        try {
            int linhaSelecionada = grdHistorico.getSelectedRow();
            if (linhaSelecionada == -1) {
                exibirAviso("Selecione uma consulta para visualizar o relatório.");
                return;
            }

            TMViewHistoricoProcedimento modelo = (TMViewHistoricoProcedimento) grdHistorico.getModel();
            Consulta consulta = modelo.getConsulta(linhaSelecionada);
            
            if (consulta.getProcedimentos().isEmpty()) {
                exibirAviso("Não há procedimentos registrados nesta consulta.");
                return;
            }

            if (consulta.getProcedimentos().size() == 1) {
                abrirTelaRelatorio(consulta, consulta.getProcedimentos().get(0));
            } else {
                selecionarProcedimentoEAbrirRelatorio(consulta);
            }
            
        } catch (Exception e) {
            exibirErro("Erro ao visualizar relatório", e);
        }
    }
    
    private void selecionarProcedimentoEAbrirRelatorio(Consulta consulta) {
        Procedimento procedimento = (Procedimento) JOptionPane.showInputDialog(
            this,
            "Selecione o procedimento:",
            "Gerar Relatório",
            JOptionPane.QUESTION_MESSAGE,
            null,
            consulta.getProcedimentos().toArray(),
            consulta.getProcedimentos().get(0)
        );
        
        if (procedimento != null) {
            abrirTelaRelatorio(consulta, procedimento);
        }
    }
    
    private void abrirTelaRelatorio(Consulta consulta, Procedimento procedimento) {
        DlgRelatorio dialog = new DlgRelatorio(null, true, consulta, procedimento);
        dialog.setVisible(true);
        if (dialog.isRelatorioSalvo()) {
            atualizarHistorico();
        }
    }
    
    private void exibirAviso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Aviso", 
            JOptionPane.WARNING_MESSAGE);
    }
    
    private void exibirErro(String mensagem, Exception e) {
        JOptionPane.showMessageDialog(this, 
            mensagem + ": " + e.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblNome = new javax.swing.JLabel();
        cboPacientes = new com.ifcolab.estetify.components.CustomComboBox();
        btnBuscarPaciente = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnVisualizarRelatorio = new com.ifcolab.estetify.components.SecondaryCustomButton();
        tmRecepcionistas = new javax.swing.JScrollPane();
        grdHistorico = new com.ifcolab.estetify.components.CustomTable();
        pnlHistoricos = new javax.swing.JPanel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Selecionar Paciente");
        getContentPane().add(lblNome);
        lblNome.setBounds(60, 120, 160, 17);
        getContentPane().add(cboPacientes);
        cboPacientes.setBounds(50, 140, 650, 44);

        btnBuscarPaciente.setText("Buscar Paciente");
        getContentPane().add(btnBuscarPaciente);
        btnBuscarPaciente.setBounds(50, 70, 190, 30);

        btnVisualizarRelatorio.setText("Visualizar Relatorio");
        getContentPane().add(btnVisualizarRelatorio);
        btnVisualizarRelatorio.setBounds(260, 70, 230, 30);

        grdHistorico.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tmRecepcionistas.setViewportView(grdHistorico);

        getContentPane().add(tmRecepcionistas);
        tmRecepcionistas.setBounds(40, 236, 1260, 570);

        pnlHistoricos.setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().add(pnlHistoricos);
        pnlHistoricos.setBounds(30, 220, 1280, 600);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Visualize os procedimentos por paciente");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 650, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Historico de Procedimentos");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 360, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 140);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnBuscarPaciente;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnVisualizarRelatorio;
    private com.ifcolab.estetify.components.CustomComboBox cboPacientes;
    private com.ifcolab.estetify.components.CustomTable grdHistorico;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JPanel pnlHistoricos;
    private javax.swing.JScrollPane tmRecepcionistas;
    // End of variables declaration//GEN-END:variables
}
