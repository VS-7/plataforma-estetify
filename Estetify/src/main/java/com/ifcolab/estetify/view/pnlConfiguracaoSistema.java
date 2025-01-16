package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ConfiguracaoSistemaController;
import com.ifcolab.estetify.model.ConfiguracaoSistema;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;


public class pnlConfiguracaoSistema extends javax.swing.JPanel {

    private final ConfiguracaoSistemaController controller;
    private FrMenu parentFrame;
    
    public pnlConfiguracaoSistema() {
        initComponents();
        controller = new ConfiguracaoSistemaController();
        
        configurarInterface();
        carregarConfiguracao();
    }

    private void configurarInterface() {
        configurarMascaras();
        configurarSpinners();
    }
    
    private void configurarMascaras() {
        try {
            MaskFormatter maskHora = controller.getHoraMaskFormatter();
            maskHora.install(fEdtHorarioAbertura);
            maskHora.install(fEdtHorarioFechamento);
        } catch (ParseException ex) {
            exibirErro("Erro ao configurar máscaras", ex);
        }
    }
    
    private void configurarSpinners() {
        spnIntervaloConsulta.setModel(controller.getIntervaloConsultaModel());
        spnTempoAntecedencia.setModel(controller.getTempoAntecedenciaModel());
        spnTempoMaxAgendamento.setModel(controller.getTempoMaxAgendamentoModel());
    }

    private void carregarConfiguracao() {
        try {
            ConfiguracaoSistema config = controller.getConfiguracao();
            preencherFormulario(config);
        } catch (Exception ex) {
            exibirErro("Erro ao carregar configurações", ex);
        }
    }
    
    private void preencherFormulario(ConfiguracaoSistema config) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        fEdtHorarioAbertura.setText(config.getHorarioAbertura().format(formatter));
        fEdtHorarioFechamento.setText(config.getHorarioFechamento().format(formatter));
        spnIntervaloConsulta.setValue(config.getIntervaloConsultaMinutos());
        
        chkSegunda.setSelected(config.isFuncionaSegunda());
        chkTerca.setSelected(config.isFuncionaTerca());
        chkQuarta.setSelected(config.isFuncionaQuarta());
        chkQuinta.setSelected(config.isFuncionaQuinta());
        chkSexta.setSelected(config.isFuncionaSexta());
        chkSabado.setSelected(config.isFuncionaSabado());
        chkDomingo.setSelected(config.isFuncionaDomingo());
        
        spnTempoAntecedencia.setValue(config.getTempoMinimoAntecedenciaMinutos());
        spnTempoMaxAgendamento.setValue(config.getTempoMaximoAgendamentoDias());
    }

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            salvarConfiguracoes();
            exibirSucesso("Configurações salvas com sucesso!");
            atualizarInterface();
        } catch (Exception ex) {
            exibirErro("Erro ao salvar configurações", ex);
        }
    }
    
    private void salvarConfiguracoes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        controller.atualizar(
            LocalTime.parse(fEdtHorarioAbertura.getText(), formatter),
            LocalTime.parse(fEdtHorarioFechamento.getText(), formatter),
            (int) spnIntervaloConsulta.getValue(),
            chkSegunda.isSelected(),
            chkTerca.isSelected(),
            chkQuarta.isSelected(),
            chkQuinta.isSelected(),
            chkSexta.isSelected(),
            chkSabado.isSelected(),
            chkDomingo.isSelected(),
            (int) spnTempoAntecedencia.getValue(),
            (int) spnTempoMaxAgendamento.getValue()
        );
    }
    
    private void atualizarInterface() {
        if (parentFrame != null) {
            parentFrame.atualizarAgenda();
            parentFrame.mostrarAgenda();
        }
    }
    
    private void exibirSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Sucesso", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void exibirErro(String mensagem, Exception ex) {
        JOptionPane.showMessageDialog(this, 
            mensagem + ": " + ex.getMessage(),
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }

    public void setParentFrame(FrMenu parent) {
        this.parentFrame = parent;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblHorarioFuncionamento1 = new javax.swing.JLabel();
        lblAbertura = new javax.swing.JLabel();
        fEdtHorarioAbertura = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtHorarioFechamento = new com.ifcolab.estetify.components.CustomFormattedTextField();
        lblFechamento = new javax.swing.JLabel();
        spnIntervaloConsulta = new javax.swing.JSpinner();
        lblDias = new javax.swing.JLabel();
        lblIntervaloConsulta1 = new javax.swing.JLabel();
        lblHorarioFuncionamento2 = new javax.swing.JLabel();
        chkSegunda = new javax.swing.JCheckBox();
        lblMin2 = new javax.swing.JLabel();
        lblMin1 = new javax.swing.JLabel();
        chkTerca = new javax.swing.JCheckBox();
        chkQuarta = new javax.swing.JCheckBox();
        chkQuinta = new javax.swing.JCheckBox();
        chkSexta = new javax.swing.JCheckBox();
        chkSabado = new javax.swing.JCheckBox();
        chkDomingo = new javax.swing.JCheckBox();
        lblHorarioFuncionamento = new javax.swing.JLabel();
        lblIntervaloConsulta2 = new javax.swing.JLabel();
        spnTempoAntecedencia = new javax.swing.JSpinner();
        lblIntervaloConsulta = new javax.swing.JLabel();
        spnTempoMaxAgendamento = new javax.swing.JSpinner();
        btnSalvar = new com.ifcolab.estetify.components.PrimaryCustomButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Configurações");
        add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Controle as informações do Sistemas.");
        add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 720, 17);

        lblHorarioFuncionamento1.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento1.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento1.setText("Horário de Funcionamento");
        add(lblHorarioFuncionamento1);
        lblHorarioFuncionamento1.setBounds(30, 90, 270, 18);

        lblAbertura.setForeground(new java.awt.Color(51, 51, 51));
        lblAbertura.setText("Abertura");
        add(lblAbertura);
        lblAbertura.setBounds(40, 120, 130, 17);

        fEdtHorarioAbertura.setText("Abertura");
        add(fEdtHorarioAbertura);
        fEdtHorarioAbertura.setBounds(30, 140, 450, 38);

        fEdtHorarioFechamento.setText("Fechamento");
        add(fEdtHorarioFechamento);
        fEdtHorarioFechamento.setBounds(510, 140, 460, 38);

        lblFechamento.setForeground(new java.awt.Color(51, 51, 51));
        lblFechamento.setText("Fechamento");
        add(lblFechamento);
        lblFechamento.setBounds(520, 120, 170, 17);
        add(spnIntervaloConsulta);
        spnIntervaloConsulta.setBounds(40, 210, 160, 27);

        lblDias.setForeground(new java.awt.Color(51, 51, 51));
        lblDias.setText("dias");
        add(lblDias);
        lblDias.setBounds(340, 500, 190, 17);

        lblIntervaloConsulta1.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloConsulta1.setText("Intervalo entre consultas");
        add(lblIntervaloConsulta1);
        lblIntervaloConsulta1.setBounds(40, 190, 190, 17);

        lblHorarioFuncionamento2.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento2.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento2.setText("Dias de Funcionamento");
        add(lblHorarioFuncionamento2);
        lblHorarioFuncionamento2.setBounds(40, 270, 270, 18);

        chkSegunda.setText("Segunda");
        add(chkSegunda);
        chkSegunda.setBounds(40, 310, 90, 21);

        lblMin2.setForeground(new java.awt.Color(51, 51, 51));
        lblMin2.setText("min");
        add(lblMin2);
        lblMin2.setBounds(340, 440, 190, 17);

        lblMin1.setForeground(new java.awt.Color(51, 51, 51));
        lblMin1.setText("min");
        add(lblMin1);
        lblMin1.setBounds(210, 220, 190, 17);

        chkTerca.setText("Terça");
        add(chkTerca);
        chkTerca.setBounds(180, 310, 80, 21);

        chkQuarta.setText("Quarta");
        add(chkQuarta);
        chkQuarta.setBounds(310, 310, 100, 21);

        chkQuinta.setText("Quinta");
        add(chkQuinta);
        chkQuinta.setBounds(460, 310, 100, 21);

        chkSexta.setText("Sexta");
        add(chkSexta);
        chkSexta.setBounds(600, 310, 100, 21);

        chkSabado.setText("Sabado");
        add(chkSabado);
        chkSabado.setBounds(720, 310, 100, 21);

        chkDomingo.setText("Domingo");
        add(chkDomingo);
        chkDomingo.setBounds(850, 310, 100, 21);

        lblHorarioFuncionamento.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento.setText("Outras Configurações");
        add(lblHorarioFuncionamento);
        lblHorarioFuncionamento.setBounds(40, 380, 270, 18);

        lblIntervaloConsulta2.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloConsulta2.setText("Antecedência Mínima de Agendamento de Consultas");
        add(lblIntervaloConsulta2);
        lblIntervaloConsulta2.setBounds(40, 410, 440, 17);
        add(spnTempoAntecedencia);
        spnTempoAntecedencia.setBounds(40, 430, 290, 27);

        lblIntervaloConsulta.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloConsulta.setText("Agendamento Máximo");
        add(lblIntervaloConsulta);
        lblIntervaloConsulta.setBounds(40, 470, 230, 17);
        add(spnTempoMaxAgendamento);
        spnTempoMaxAgendamento.setBounds(40, 490, 290, 27);

        btnSalvar.setText("Salvar Alterações");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        add(btnSalvar);
        btnSalvar.setBounds(760, 650, 200, 30);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnSalvar;
    private javax.swing.JCheckBox chkDomingo;
    private javax.swing.JCheckBox chkQuarta;
    private javax.swing.JCheckBox chkQuinta;
    private javax.swing.JCheckBox chkSabado;
    private javax.swing.JCheckBox chkSegunda;
    private javax.swing.JCheckBox chkSexta;
    private javax.swing.JCheckBox chkTerca;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtHorarioAbertura;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtHorarioFechamento;
    private javax.swing.JLabel lblAbertura;
    private javax.swing.JLabel lblDias;
    private javax.swing.JLabel lblFechamento;
    private javax.swing.JLabel lblHorarioFuncionamento;
    private javax.swing.JLabel lblHorarioFuncionamento1;
    private javax.swing.JLabel lblHorarioFuncionamento2;
    private javax.swing.JLabel lblIntervaloConsulta;
    private javax.swing.JLabel lblIntervaloConsulta1;
    private javax.swing.JLabel lblIntervaloConsulta2;
    private javax.swing.JLabel lblMin1;
    private javax.swing.JLabel lblMin2;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JSpinner spnIntervaloConsulta;
    private javax.swing.JSpinner spnTempoAntecedencia;
    private javax.swing.JSpinner spnTempoMaxAgendamento;
    // End of variables declaration//GEN-END:variables
}
