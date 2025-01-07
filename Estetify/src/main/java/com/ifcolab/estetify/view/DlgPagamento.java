package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.PagamentoController;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.enums.MetodoPagamento;
import com.ifcolab.estetify.model.enums.StatusPagamento;
import com.ifcolab.estetify.model.exceptions.PagamentoException;
import com.ifcolab.estetify.utils.NotificadorEmail;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;


public class DlgPagamento extends javax.swing.JDialog {
    
    private final Consulta consulta;
    private final PagamentoController controller;
    private boolean pagamentoRealizado = false;
    
    public DlgPagamento(java.awt.Frame parent, boolean modal, Consulta consulta) {
        super(parent, modal);
        initComponents();
        
        this.consulta = consulta;
        this.controller = new PagamentoController();
        configurarComponentes();
        
        this.setLocationRelativeTo(null);
    }
    
    private void configurarComponentes() {
        // Configurar o card de detalhes
        JPanel cardDetalhes = criarCardDetalhes();
        cardDetalhes.setSize(460, 150);
        cardDetalhes.setLocation(160, 80);
        pnlBackground.add(cardDetalhes);

        // Configurar máscara para o campo de valor
        DecimalFormat format = new DecimalFormat("#,##0.00");
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Double.class);
        formatter.setMinimum(0.0);
        formatter.setAllowsInvalid(false);

        // Aplicar formatação ao campo de valor
        edtValorPagamento.setFormatterFactory(new DefaultFormatterFactory(formatter));
        edtValorPagamento.setValue(consulta.getValorTotal());

        // Preencher métodos de pagamento
        for (MetodoPagamento metodo : MetodoPagamento.values()) {
            cboMetodoPagamento.addItem(metodo);
        }
    }
    
    private JPanel criarCardDetalhes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 247, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 220, 255), 1),
            BorderFactory.createEmptyBorder(15, 20, 15, 20)
        ));
        
        // Formatador de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        // Labels com informações
        adicionarLabel(panel, "Paciente: " + consulta.getPaciente().getNome());
        adicionarLabel(panel, "Data: " + consulta.getDataHora().format(formatter));
        adicionarLabel(panel, "Médico: " + consulta.getMedico().getNome());
        adicionarLabel(panel, "Valor Total: R$ " + String.format("%.2f", consulta.getValorTotal()));
        
        // Adicionar procedimentos
        if (!consulta.getProcedimentos().isEmpty()) {
            adicionarLabel(panel, "Procedimentos:");
            for (Procedimento proc : consulta.getProcedimentos()) {
                adicionarLabel(panel, "• " + proc.getNome() + 
                    " - R$ " + String.format("%.2f", proc.getValor()));
            }
        }
        
        return panel;
    }
    
    private void adicionarLabel(JPanel panel, String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Fira Sans", Font.PLAIN, 12));
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblValorPagamento = new javax.swing.JLabel();
        lblMetodoPagamento = new javax.swing.JLabel();
        lblDetalhes = new javax.swing.JLabel();
        btnSalvar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnCancelar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        cboMetodoPagamento = new com.ifcolab.estetify.components.CustomComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetalhes = new com.ifcolab.estetify.components.CustomTextArea();
        edtValorPagamento = new com.ifcolab.estetify.components.CustomTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 700));
        setResizable(false);
        getContentPane().setLayout(null);

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground.setLayout(null);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Escolha a forma de pagamento, visualize e confirme.");
        pnlBackground.add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 550, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Realizar Pagamento");
        pnlBackground.add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblValorPagamento.setForeground(new java.awt.Color(51, 51, 51));
        lblValorPagamento.setText("Valor do Pagamento");
        pnlBackground.add(lblValorPagamento);
        lblValorPagamento.setBounds(170, 280, 160, 17);

        lblMetodoPagamento.setForeground(new java.awt.Color(51, 51, 51));
        lblMetodoPagamento.setText("Método de Pagamento");
        pnlBackground.add(lblMetodoPagamento);
        lblMetodoPagamento.setBounds(170, 350, 260, 17);

        lblDetalhes.setForeground(new java.awt.Color(51, 51, 51));
        lblDetalhes.setText("Detalhes (opcional)");
        pnlBackground.add(lblDetalhes);
        lblDetalhes.setBounds(170, 420, 260, 17);

        btnSalvar.setText("Confirmar Pagamento");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        pnlBackground.add(btnSalvar);
        btnSalvar.setBounds(420, 580, 200, 38);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlBackground.add(btnCancelar);
        btnCancelar.setBounds(160, 580, 200, 38);
        pnlBackground.add(cboMetodoPagamento);
        cboMetodoPagamento.setBounds(160, 370, 460, 44);

        txtDetalhes.setColumns(20);
        txtDetalhes.setRows(5);
        jScrollPane1.setViewportView(txtDetalhes);

        pnlBackground.add(jScrollPane1);
        jScrollPane1.setBounds(160, 440, 460, 116);
        pnlBackground.add(edtValorPagamento);
        edtValorPagamento.setBounds(160, 298, 460, 50);

        getContentPane().add(pnlBackground);
        pnlBackground.setBounds(0, 0, 800, 650);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            double valor = ((Number) edtValorPagamento.getValue()).doubleValue();
            MetodoPagamento metodoPagamento = (MetodoPagamento) cboMetodoPagamento.getSelectedItem();
            String detalhes = txtDetalhes.getText();

            controller.cadastrar(
                valor,
                StatusPagamento.PAGO,
                metodoPagamento,
                detalhes,
                consulta,
                consulta.getMedico()
            );

            pagamentoRealizado = true;
            JOptionPane.showMessageDialog(this,
                "Pagamento registrado com sucesso!\nUm email de confirmação foi enviado para o paciente.",
                "Sucesso",
                JOptionPane.INFORMATION_MESSAGE);
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao registrar pagamento: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    public boolean isPagamentoRealizado() {
        return pagamentoRealizado;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.SecondaryCustomButton btnCancelar;
    private com.ifcolab.estetify.components.PrimaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomComboBox cboMetodoPagamento;
    private com.ifcolab.estetify.components.CustomTextField edtValorPagamento;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDetalhes;
    private javax.swing.JLabel lblMetodoPagamento;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JLabel lblValorPagamento;
    private javax.swing.JPanel pnlBackground;
    private com.ifcolab.estetify.components.CustomTextArea txtDetalhes;
    // End of variables declaration//GEN-END:variables
}
