package com.ifcolab.estetify.view;

import com.ifcolab.estetify.components.PrimaryCustomButton;
import com.ifcolab.estetify.components.SecondaryCustomButton;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.util.List;
import javax.swing.JFileChooser;
import com.ifcolab.estetify.controller.OpcoesConsultaController;

public class DlgOpcoesConsulta extends javax.swing.JDialog {
    private final OpcoesConsultaController controller;
    private boolean alteracaoRealizada = false;
    
    public DlgOpcoesConsulta(JFrame parent, Consulta consulta) {
        super(parent, "Opções da Consulta", true);
        this.controller = new OpcoesConsultaController(consulta);
        
        initComponents();
        configurarJanela();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        JLabel lblTitulo = new JLabel("Detalhes da Consulta");
        lblTitulo.setFont(new Font("Fira Sans SemiBold", Font.PLAIN, 18));
        lblTitulo.setForeground(new Color(51, 51, 51));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        JPanel cardPanel = criarPainelInformacoes();
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        

        JPanel botoesPanel = criarPainelBotoes();
        botoesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(botoesPanel);
        
        setContentPane(mainPanel);
    }
    
    private JPanel criarPainelInformacoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 247, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 220, 255), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        JLabel lblStatus = new JLabel("Status: " + controller.getStatusFormatado());
        lblStatus.setFont(new Font("Fira Sans", Font.BOLD, 14));
        definirCorStatus(lblStatus, controller.getStatus());
        panel.add(lblStatus);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        adicionarLabel(panel, "Data/Hora: " + controller.getDataHoraFormatada());
        adicionarLabel(panel, "Paciente: " + controller.getNomePaciente());
        adicionarLabel(panel, "Médico: " + controller.getNomeMedico());
        adicionarLabel(panel, "Enfermeira: " + controller.getNomeEnfermeira());
        
        List<String> procedimentos = controller.getProcedimentosFormatados();
        if (!procedimentos.isEmpty()) {
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JLabel lblProcedimentos = new JLabel("<html><b>Procedimentos:</b><br/>" + 
                String.join("<br/>• ", procedimentos) + "</html>");
            lblProcedimentos.setFont(new Font("Fira Sans", Font.PLAIN, 14));
            panel.add(lblProcedimentos);
        }
        
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        JLabel lblValorTotal = new JLabel("Valor Total: R$ " + 
            String.format("%.2f", controller.getValorTotal()));
        lblValorTotal.setFont(new Font("Fira Sans", Font.BOLD, 14));
        panel.add(lblValorTotal);
        
        return panel;
    }
    
    private void definirCorStatus(JLabel label, StatusConsulta status) {
        switch (status) {
            case AGENDADA:
                label.setForeground(new Color(51, 102, 204));  // Azul
                break;
            case CONFIRMADA:
                label.setForeground(new Color(46, 125, 50));   // Verde
                break;
            case CONCLUIDA:
                label.setForeground(new Color(88, 88, 88));    // Cinza
                break;
            case CANCELADA:
                label.setForeground(new Color(211, 47, 47));   // Vermelho
                break;
        }
    }
    
    private void adicionarLabel(JPanel panel, String texto) {
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Fira Sans", Font.PLAIN, 14));
        panel.add(label);
    }
    
    private JPanel criarPainelBotoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.WHITE);
        
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(Color.WHITE);
        
        if (controller.podeConfirmarConsulta()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Confirmar Consulta"), 
                          e -> confirmarConsulta());
            adicionarBotao(innerPanel, new SecondaryCustomButton("Cancelar Consulta"), 
                          e -> cancelarConsulta());
        }
        
        if (controller.podeRealizarConsulta()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Realizar Consulta"), 
                          e -> realizarConsulta());
        }
        
        if (controller.podeFazerPagamento() && controller.podeGerenciarPagamentos()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Realizar Pagamento"), 
                          e -> realizarPagamento());
        }
        
        if (controller.podeEmitirRecibo() && controller.podeGerenciarPagamentos()) {
            adicionarBotao(innerPanel, new SecondaryCustomButton("Emitir Recibo"), 
                          e -> emitirRecibo());
        }
        
        if (controller.podeVerRelatorios()) {
            adicionarBotao(innerPanel, new SecondaryCustomButton("Relatório"), 
                          e -> gerarRelatorio());
        }
        
        adicionarBotao(innerPanel, new SecondaryCustomButton("Editar Consulta"), 
                      e -> editarConsulta());
        
        panel.add(innerPanel);
        return panel;
    }
    
    private void adicionarBotao(JPanel panel, JButton botao, ActionListener listener) {
        botao.setAlignmentX(Component.CENTER_ALIGNMENT);
        botao.setMaximumSize(new Dimension(200, 30));
        botao.addActionListener(listener);
        panel.add(botao);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
    }
    
    private void configurarJanela() {
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setSize(550, 650);
        setLocationRelativeTo(getParent());
        setResizable(false);
    }
    
    private void confirmarConsulta() {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja realmente confirmar esta consulta?",
            "Confirmar Consulta",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                controller.confirmarConsulta();
                alteracaoRealizada = true;
                JOptionPane.showMessageDialog(this,
                    "Consulta confirmada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao confirmar consulta: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void cancelarConsulta() {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja realmente cancelar esta consulta?\n\n" +
            "Esta ação não poderá ser desfeita.",
            "Cancelar Consulta",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                controller.cancelarConsulta();
                alteracaoRealizada = true;
                JOptionPane.showMessageDialog(this,
                    "Consulta cancelada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao cancelar consulta: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void realizarConsulta() {
        int opcao = JOptionPane.showConfirmDialog(this,
            "Deseja marcar esta consulta como realizada?",
            "Realizar Consulta",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        
        if (opcao == JOptionPane.YES_OPTION) {
            try {
                controller.realizarConsulta();
                alteracaoRealizada = true;
                JOptionPane.showMessageDialog(this,
                    "Consulta realizada com sucesso!\n" +
                    "Não se esqueça de registrar o pagamento.",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao realizar consulta: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void realizarPagamento() {
       DlgPagamento dialog = new DlgPagamento(null, true, controller.getConsulta());
        dialog.setVisible(true);
        if (dialog.isPagamentoRealizado()) {
            alteracaoRealizada = true;
            dispose();
        } 
    }
    
    private void emitirRecibo() {
        try {
            List<Pagamento> pagamentos = controller.buscarPagamentosConsulta();

            if (pagamentos.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Não há pagamento registrado para esta consulta.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
                return;
            }

            Pagamento pagamento = pagamentos.get(0);
            
            try {
                controller.gerarReciboPagamento(pagamento);
                
                JOptionPane.showMessageDialog(this,
                    "Recibo gerado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    "Erro ao gerar recibo: " + e.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                "Erro ao buscar pagamentos: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void editarConsulta() {
        DlgNovaConsulta dialog = new DlgNovaConsulta(null, true);
        dialog.preencherFormulario(controller.getConsulta());
        dialog.setVisible(true);
        if (dialog.isConsultaAlterada()) {
            alteracaoRealizada = true;
            dispose();
        }
    }
    
    private void gerarRelatorio() {
    if (controller.getConsulta().getProcedimentos().isEmpty()) {
        JOptionPane.showMessageDialog(this,
            "Não há procedimentos registrados nesta consulta.",
            "Aviso",
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (controller.getConsulta().getProcedimentos().size() == 1) {
        Procedimento procedimento = controller.getConsulta().getProcedimentos().get(0);
        DlgRelatorio dialog = new DlgRelatorio(null, true, controller.getConsulta(), procedimento);
        dialog.setVisible(true);
        if (dialog.isRelatorioSalvo()) {
            alteracaoRealizada = true;
            dispose();
        }
    } else {

        Procedimento procedimento = (Procedimento) JOptionPane.showInputDialog(
            this,
            "Selecione o procedimento:",
            "Gerar Relatório",
            JOptionPane.QUESTION_MESSAGE,
            null,
            controller.getConsulta().getProcedimentos().toArray(),
            controller.getConsulta().getProcedimentos().get(0)
        );
        
        if (procedimento != null) {
            DlgRelatorio dialog = new DlgRelatorio(null, true, controller.getConsulta(), procedimento);
            dialog.setVisible(true);
            if (dialog.isRelatorioSalvo()) {
                alteracaoRealizada = true;
                dispose();
            }
        }
    }
    }
    
    public boolean isAlteracaoRealizada() {
        return alteracaoRealizada;
    }
} 