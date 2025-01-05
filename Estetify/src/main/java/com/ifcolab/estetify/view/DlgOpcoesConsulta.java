package com.ifcolab.estetify.view;

import com.ifcolab.estetify.components.PrimaryCustomButton;
import com.ifcolab.estetify.components.SecondaryCustomButton;
import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.time.format.DateTimeFormatter;

public class DlgOpcoesConsulta extends javax.swing.JDialog {
    private final Consulta consulta;
    private final ConsultaController controller;
    private boolean alteracaoRealizada = false;
    
    public DlgOpcoesConsulta(JFrame parent, Consulta consulta) {
        super(parent, "Opções da Consulta", true);
        this.consulta = consulta;
        this.controller = new ConsultaController();
        
        initComponents();
        configurarJanela();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Título
        JLabel lblTitulo = new JLabel("Detalhes da Consulta");
        lblTitulo.setFont(new Font("Fira Sans SemiBold", Font.PLAIN, 18));
        lblTitulo.setForeground(new Color(51, 51, 51));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(lblTitulo);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Card com informações
        JPanel cardPanel = criarPainelInformacoes();
        cardPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(cardPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Botões de ação
        JPanel botoesPanel = criarPainelBotoes();
        botoesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(botoesPanel);
        
        setContentPane(mainPanel);
    }
    
    private JPanel criarPainelInformacoes() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 247, 255));  // Fundo azul claro
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 220, 255), 1),
            BorderFactory.createEmptyBorder(20, 25, 20, 25)
        ));
        
        // Formatador de data
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        // Status com cor especial
        JLabel lblStatus = new JLabel("Status: " + consulta.getStatus());
        lblStatus.setFont(new Font("Fira Sans", Font.BOLD, 14));
        definirCorStatus(lblStatus, consulta.getStatus());
        panel.add(lblStatus);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        
        // Outras informações
        adicionarLabel(panel, "Data/Hora: " + consulta.getDataHora().format(formatter));
        adicionarLabel(panel, "Paciente: " + consulta.getPaciente().getNome());
        adicionarLabel(panel, "Médico: " + consulta.getMedico().getNome());
        adicionarLabel(panel, "Enfermeira: " + consulta.getEnfermeira().getNome());
        
        // Procedimentos
        if (!consulta.getProcedimentos().isEmpty()) {
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            JLabel lblProcedimentos = new JLabel("<html><b>Procedimentos:</b><br/>" + 
                String.join("<br/>• ", consulta.getProcedimentos().stream()
                    .map(p -> p.getNome() + " - R$ " + String.format("%.2f", p.getValor()))
                    .toArray(String[]::new)) + "</html>");
            lblProcedimentos.setFont(new Font("Fira Sans", Font.PLAIN, 14));
            panel.add(lblProcedimentos);
        }
        
        // Valor total
        panel.add(Box.createRigidArea(new Dimension(0, 15)));
        JLabel lblValorTotal = new JLabel("Valor Total: R$ " + String.format("%.2f", consulta.getValorTotal()));
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
        panel.setLayout(new GridBagLayout());  // Usar GridBagLayout para centralizar
        panel.setBackground(Color.WHITE);
        
        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BoxLayout(innerPanel, BoxLayout.Y_AXIS));
        innerPanel.setBackground(Color.WHITE);
        
        // Botões de status
        if (consulta.isAgendada()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Confirmar Consulta"), e -> confirmarConsulta());
            adicionarBotao(innerPanel, new SecondaryCustomButton("Cancelar Consulta"), e -> cancelarConsulta());
        }
        
        if (consulta.isConfirmada()) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Realizar Consulta"), e -> realizarConsulta());
        }
        
        if (consulta.isConcluida() && consulta.getPagamento() == null) {
            adicionarBotao(innerPanel, new PrimaryCustomButton("Realizar Pagamento"), e -> realizarPagamento());
        }
        
        if (consulta.isConcluida()) {
            adicionarBotao(innerPanel, new SecondaryCustomButton("Emitir Relatório"), e -> emitirRelatorio());
        }
        
        // Botão editar sempre disponível
        adicionarBotao(innerPanel, new SecondaryCustomButton("Editar Consulta"), e -> editarConsulta());
        
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
        setSize(450, 600);
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
                controller.confirmarConsulta(consulta.getId());
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
                controller.cancelarConsulta(consulta.getId());
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
                controller.realizarConsulta(consulta.getId());
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
       DlgPagamento dialog = new DlgPagamento(null, true, consulta);
        dialog.setVisible(true);
        if (dialog.isPagamentoRealizado()) {
            alteracaoRealizada = true;
            dispose();
        } 
    }
    
    private void emitirRelatorio() {
        // Implementar geração de relatório
        JOptionPane.showMessageDialog(this, "Funcionalidade em desenvolvimento");
    }
    
    private void editarConsulta() {
        DlgNovaConsulta dialog = new DlgNovaConsulta(null, true);
        dialog.preencherFormulario(consulta);
        dialog.setVisible(true);
        if (dialog.isConsultaAlterada()) {
            alteracaoRealizada = true;
            dispose();
        }
    }
    
    public boolean isAlteracaoRealizada() {
        return alteracaoRealizada;
    }
} 