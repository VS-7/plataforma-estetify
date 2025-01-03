package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ConfiguracaoSistemaController;
import com.ifcolab.estetify.model.ConfiguracaoSistema;
import java.text.ParseException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;

public class DlgConfiguracaoSistema extends javax.swing.JDialog {

    private final ConfiguracaoSistemaController controller;
    private ConfiguracaoSistema config;

    public DlgConfiguracaoSistema(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new ConfiguracaoSistemaController();

 
        this.configurarComponentes();
        this.carregarConfiguracao();

    }
    
    private void configurarComponentes() {
        try {
            // Configurar máscaras para horários
            MaskFormatter maskHora = new MaskFormatter("##:##");
            maskHora.setPlaceholderCharacter('_');
            maskHora.install(fEdtHorarioAbertura);
            maskHora.install(fEdtHorarioFechamento);
            
            // Configurar spinners
            spnIntervaloConsulta.setModel(new SpinnerNumberModel(30, 15, 120, 15));
            spnTempoAntecedencia.setModel(new SpinnerNumberModel(60, 0, 1440, 30));
            spnTempoMaxAgendamento.setModel(new SpinnerNumberModel(60, 1, 365, 1));
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgConfiguracaoSistema.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void carregarConfiguracao() {
        config = controller.getConfiguracao();
        
        // Carregar horários
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        fEdtHorarioAbertura.setText(config.getHorarioAbertura().format(formatter));
        fEdtHorarioFechamento.setText(config.getHorarioFechamento().format(formatter));
        
        // Carregar intervalo
        spnIntervaloConsulta.setValue(config.getIntervaloConsultaMinutos());
        
        // Carregar dias de funcionamento
        chkSegunda.setSelected(config.isFuncionaSegunda());
        chkTerca.setSelected(config.isFuncionaTerca());
        chkQuarta.setSelected(config.isFuncionaQuarta());
        chkQuinta.setSelected(config.isFuncionaQuinta());
        chkSexta.setSelected(config.isFuncionaSexta());
        chkSabado.setSelected(config.isFuncionaSabado());
        chkDomingo.setSelected(config.isFuncionaDomingo());
        
        // Carregar outras configurações
        spnTempoAntecedencia.setValue(config.getTempoMinimoAntecedenciaMinutos());
        spnTempoMaxAgendamento.setValue(config.getTempoMaximoAgendamentoDias());
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMin = new javax.swing.JLabel();
        lblFechamento = new javax.swing.JLabel();
        lblHorarioFuncionamento = new javax.swing.JLabel();
        btnSalvar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        lblAbertura = new javax.swing.JLabel();
        lblIntervaloConsulta = new javax.swing.JLabel();
        chkDomingo = new javax.swing.JCheckBox();
        spnTempoAntecedencia = new javax.swing.JSpinner();
        lblHorarioFuncionamento1 = new javax.swing.JLabel();
        lblHorarioFuncionamento2 = new javax.swing.JLabel();
        btnCancelar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        chkSegunda = new javax.swing.JCheckBox();
        chkTerca = new javax.swing.JCheckBox();
        chkQuarta = new javax.swing.JCheckBox();
        chkQuinta = new javax.swing.JCheckBox();
        chkSexta = new javax.swing.JCheckBox();
        chkSabado = new javax.swing.JCheckBox();
        lblIntervaloConsulta1 = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblIntervaloConsulta2 = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        fEdtHorarioFechamento = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtHorarioAbertura = new com.ifcolab.estetify.components.CustomFormattedTextField();
        spnTempoMaxAgendamento = new javax.swing.JSpinner();
        spnIntervaloConsulta = new javax.swing.JSpinner();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(690, 800));
        getContentPane().setLayout(null);

        lblMin.setForeground(new java.awt.Color(51, 51, 51));
        lblMin.setText("min");
        getContentPane().add(lblMin);
        lblMin.setBounds(240, 270, 190, 17);

        lblFechamento.setForeground(new java.awt.Color(51, 51, 51));
        lblFechamento.setText("Fechamento");
        getContentPane().add(lblFechamento);
        lblFechamento.setBounds(370, 160, 130, 17);

        lblHorarioFuncionamento.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento.setText("Outras Configurações");
        getContentPane().add(lblHorarioFuncionamento);
        lblHorarioFuncionamento.setBounds(60, 420, 270, 18);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(60, 80, 170, 30);

        lblAbertura.setForeground(new java.awt.Color(51, 51, 51));
        lblAbertura.setText("Abertura");
        getContentPane().add(lblAbertura);
        lblAbertura.setBounds(70, 160, 130, 17);

        lblIntervaloConsulta.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloConsulta.setText("Agendamento Máximo");
        getContentPane().add(lblIntervaloConsulta);
        lblIntervaloConsulta.setBounds(80, 520, 230, 17);

        chkDomingo.setText("Domingo");
        getContentPane().add(chkDomingo);
        chkDomingo.setBounds(170, 380, 100, 21);
        getContentPane().add(spnTempoAntecedencia);
        spnTempoAntecedencia.setBounds(80, 470, 160, 27);

        lblHorarioFuncionamento1.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento1.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento1.setText("Horário de Funcionamento");
        getContentPane().add(lblHorarioFuncionamento1);
        lblHorarioFuncionamento1.setBounds(60, 130, 270, 18);

        lblHorarioFuncionamento2.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 14)); // NOI18N
        lblHorarioFuncionamento2.setForeground(new java.awt.Color(51, 51, 51));
        lblHorarioFuncionamento2.setText("Dias de Funcionamento");
        getContentPane().add(lblHorarioFuncionamento2);
        lblHorarioFuncionamento2.setBounds(60, 310, 270, 18);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(270, 80, 170, 30);

        chkSegunda.setText("Segunda");
        getContentPane().add(chkSegunda);
        chkSegunda.setBounds(60, 340, 90, 21);

        chkTerca.setText("Terça");
        getContentPane().add(chkTerca);
        chkTerca.setBounds(170, 340, 80, 21);

        chkQuarta.setText("Quarta");
        getContentPane().add(chkQuarta);
        chkQuarta.setBounds(280, 340, 100, 21);

        chkQuinta.setText("Quinta");
        getContentPane().add(chkQuinta);
        chkQuinta.setBounds(400, 340, 100, 21);

        chkSexta.setText("Sexta");
        getContentPane().add(chkSexta);
        chkSexta.setBounds(540, 340, 100, 21);

        chkSabado.setText("Sabado");
        getContentPane().add(chkSabado);
        chkSabado.setBounds(60, 380, 100, 21);

        lblIntervaloConsulta1.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloConsulta1.setText("Intervalo entre consultas");
        getContentPane().add(lblIntervaloConsulta1);
        lblIntervaloConsulta1.setBounds(70, 240, 190, 17);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Controle as informações das enfermeiras e dados pessoais.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 720, 17);

        lblIntervaloConsulta2.setForeground(new java.awt.Color(51, 51, 51));
        lblIntervaloConsulta2.setText("Antecedência Mínima");
        getContentPane().add(lblIntervaloConsulta2);
        lblIntervaloConsulta2.setBounds(80, 450, 230, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Configurações");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        fEdtHorarioFechamento.setText("Fechamento");
        getContentPane().add(fEdtHorarioFechamento);
        fEdtHorarioFechamento.setBounds(360, 180, 270, 38);

        fEdtHorarioAbertura.setText("Abertura");
        getContentPane().add(fEdtHorarioAbertura);
        fEdtHorarioAbertura.setBounds(60, 180, 270, 38);
        getContentPane().add(spnTempoMaxAgendamento);
        spnTempoMaxAgendamento.setBounds(80, 540, 160, 27);
        getContentPane().add(spnIntervaloConsulta);
        spnIntervaloConsulta.setBounds(70, 260, 160, 27);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTmFeedback.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 60, 690, 730);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 690, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
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
            
            JOptionPane.showMessageDialog(this,
                "Configurações salvas com sucesso!",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            
            this.dispose();
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                "Erro ao salvar configurações: " + ex.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.SecondaryCustomButton btnCancelar;
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
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblFechamento;
    private javax.swing.JLabel lblHorarioFuncionamento;
    private javax.swing.JLabel lblHorarioFuncionamento1;
    private javax.swing.JLabel lblHorarioFuncionamento2;
    private javax.swing.JLabel lblIntervaloConsulta;
    private javax.swing.JLabel lblIntervaloConsulta1;
    private javax.swing.JLabel lblIntervaloConsulta2;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JSpinner spnIntervaloConsulta;
    private javax.swing.JSpinner spnTempoAntecedencia;
    private javax.swing.JSpinner spnTempoMaxAgendamento;
    // End of variables declaration//GEN-END:variables
}
