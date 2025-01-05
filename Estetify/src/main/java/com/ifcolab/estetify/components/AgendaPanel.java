package com.ifcolab.estetify.components;

import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.estetify.view.DlgNovaConsulta;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AgendaPanel extends javax.swing.JPanel {
    private final ConsultaController consultaController;
    private ConfiguracaoSistema config;
    private LocalDate dataAtual;
    private JPanel pnlHeader;
    private JPanel pnlAgenda;
    private JLabel lblMes;
    private SecondaryCustomButton btnAnterior;
    private SecondaryCustomButton btnProximo;
    
    public AgendaPanel() {
        consultaController = new ConsultaController();
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        config = configDAO.getConfiguracao();
        dataAtual = LocalDate.now();
        
        initComponents();
    }
    
    private void initComponents() {
        // Configurar o layout principal
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 0, 1040, 730);
        
        // Adicionar título
        JLabel lblTitle = new JLabel("Agenda");
        lblTitle.setFont(new Font("Fira Sans SemiBold", Font.PLAIN, 18));
        lblTitle.setForeground(new Color(51, 51, 51));
        lblTitle.setBounds(30, 20, 210, 22);
        add(lblTitle);
        
        // Adicionar subtítulo
        JLabel lblSubtitle = new JLabel("Visualize e gerencie as consultas agendadas.");
        lblSubtitle.setFont(new Font("Fira Sans Medium", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(102, 102, 102));
        lblSubtitle.setBounds(30, 40, 720, 17);
        add(lblSubtitle);
        
        // Adicionar botão Nova Consulta
        PrimaryCustomButton btnNovaConsulta = new PrimaryCustomButton("Nova Consulta");
        btnNovaConsulta.setBounds(810, 30, 200, 30);
        btnNovaConsulta.addActionListener(e -> abrirDialogNovaConsulta());
        add(btnNovaConsulta);
        
        // Configurar cabeçalho com fundo branco e borda inferior
        pnlHeader = new JPanel();
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlHeader.setBounds(30, 80, 980, 50); // Ajustado para ficar abaixo do título
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        // Configurar botões e label
        btnAnterior = new SecondaryCustomButton("←");
        btnProximo = new SecondaryCustomButton("→");
        lblMes = new JLabel();
        lblMes.setFont(new Font("Fira Sans", Font.BOLD, 16));
        
        // Adicionar ações aos botões
        btnAnterior.addActionListener(e -> {
            dataAtual = dataAtual.minusDays(1);
            atualizarAgenda();
        });
        
        btnProximo.addActionListener(e -> {
            dataAtual = dataAtual.plusDays(1);
            atualizarAgenda();
        });
        
        // Adicionar componentes ao header
        pnlHeader.add(btnAnterior);
        pnlHeader.add(lblMes);
        pnlHeader.add(btnProximo);
        
        // Configurar painel da agenda com margens
        pnlAgenda = new JPanel();
        pnlAgenda.setLayout(new BoxLayout(pnlAgenda, BoxLayout.Y_AXIS));
        pnlAgenda.setBackground(Color.WHITE);
        
        // Criar ScrollPane para a agenda com margens
        JScrollPane scrollPane = new JScrollPane(pnlAgenda);
        scrollPane.setBounds(30, 150, 980, 550); // Ajustado para nova posição
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        // Adicionar componentes ao painel principal
        add(pnlHeader);
        add(scrollPane);
        
        // Atualizar a agenda inicialmente
        atualizarAgenda();
    }
    
    private void atualizarAgenda() {
        // Atualizar label do mês
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        lblMes.setText(dataAtual.format(formatter));
        
        // Limpar agenda
        pnlAgenda.removeAll();
        
        // Buscar consultas do dia
        List<Consulta> consultas = consultaController.buscarPorData(dataAtual);
        
        // Organizar consultas por hora
        Map<LocalTime, List<Consulta>> consultasPorHorario = new TreeMap<>();
        
        // Inicializar todos os horários com uma lista vazia
        LocalTime hora = config.getHorarioAbertura();
        while (!hora.isAfter(config.getHorarioFechamento())) {
            consultasPorHorario.put(hora, new ArrayList<>());
            hora = hora.plusMinutes(config.getIntervaloConsultaMinutos());
        }
        
        // Agrupar consultas por horário
        for (Consulta consulta : consultas) {
            LocalTime horaConsulta = consulta.getDataHora().toLocalTime();
            consultasPorHorario.computeIfAbsent(horaConsulta, k -> new ArrayList<>()).add(consulta);
        }
        
        // Criar painéis para cada horário
        consultasPorHorario.forEach((horario, consultasDoHorario) -> {
            JPanel pnlHorario = criarPainelHorario(horario);
            JPanel pnlConsultas = (JPanel) ((BorderLayout) pnlHorario.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            
            // Adicionar todas as consultas do horário
            for (Consulta consulta : consultasDoHorario) {
                pnlConsultas.add(criarPainelConsulta(consulta));
            }
            
            pnlAgenda.add(pnlHorario);
        });
        
        // Atualizar interface
        pnlAgenda.revalidate();
        pnlAgenda.repaint();
    }
    
    private JPanel criarPainelHorario(LocalTime hora) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        // Label da hora
        JLabel lblHora = new JLabel(hora.format(DateTimeFormatter.ofPattern("HH:mm")));
        lblHora.setFont(new Font("Fira Sans", Font.BOLD, 14));
        lblHora.setPreferredSize(new Dimension(80, 30));
        lblHora.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        // Painel para consultas com FlowLayout modificado
        JPanel pnlConsultas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlConsultas.setBackground(Color.WHITE);
        
        // Configurar tamanho mínimo do painel de consultas
        panel.setMinimumSize(new Dimension(980, 100));
        panel.setPreferredSize(new Dimension(980, 100));
        
        panel.add(lblHora, BorderLayout.WEST);
        panel.add(pnlConsultas, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelConsulta(Consulta consulta) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        // Definir cores baseadas no status
        Color backgroundColor;
        Color borderColor;
        
        switch (consulta.getStatus()) {
            case AGENDADA:
                backgroundColor = new Color(240, 247, 255); // Azul claro
                borderColor = new Color(200, 220, 255);     // Azul médio
                break;
            case CONFIRMADA:
                backgroundColor = new Color(236, 252, 241); // Verde claro
                borderColor = new Color(183, 235, 193);     // Verde médio
                break;
            case CONCLUIDA:
                backgroundColor = new Color(241, 241, 241); // Cinza claro
                borderColor = new Color(220, 220, 220);     // Cinza médio
                break;
            case CANCELADA:
                backgroundColor = new Color(255, 235, 235); // Vermelho claro
                borderColor = new Color(255, 200, 200);     // Vermelho médio
                break;
            default:
                backgroundColor = new Color(240, 247, 255);
                borderColor = new Color(200, 220, 255);
        }
        
        panel.setBackground(backgroundColor);
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(borderColor, 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Definir tamanho fixo menor para cada painel de consulta
        panel.setPreferredSize(new Dimension(250, 90));
        panel.setMaximumSize(new Dimension(250, 90));
        panel.setMinimumSize(new Dimension(250, 90));
        
        // Informações da consulta
        JLabel lblStatus = new JLabel(consulta.getStatus().toString());
        JLabel lblPaciente = new JLabel("Paciente: " + consulta.getPaciente().getNome());
        JLabel lblMedico = new JLabel("Médico: " + consulta.getMedico().getNome());
        JLabel lblEnfermeira = new JLabel("Enfermeira: " + consulta.getEnfermeira().getNome());
        
        // Configurar fonte e estilo
        Font labelFont = new Font("Fira Sans", Font.PLAIN, 11);
        Font statusFont = new Font("Fira Sans", Font.BOLD, 11);
        
        lblStatus.setFont(statusFont);
        lblPaciente.setFont(labelFont);
        lblMedico.setFont(labelFont);
        lblEnfermeira.setFont(labelFont);
        
        // Definir cor do texto do status
        switch (consulta.getStatus()) {
            case AGENDADA:
                lblStatus.setForeground(new Color(51, 102, 204));  // Azul escuro
                break;
            case CONFIRMADA:
                lblStatus.setForeground(new Color(46, 125, 50));   // Verde escuro
                break;
            case CONCLUIDA:
                lblStatus.setForeground(new Color(88, 88, 88));    // Cinza escuro
                break;
            case CANCELADA:
                lblStatus.setForeground(new Color(211, 47, 47));   // Vermelho escuro
                break;
        }
        
        // Adicionar componentes
        panel.add(lblStatus);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblPaciente);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblMedico);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblEnfermeira);
        
        // Adicionar procedimentos se houver
        if (!consulta.getProcedimentos().isEmpty()) {
            panel.add(Box.createRigidArea(new Dimension(0, 2)));
            JLabel lblProcedimentos = new JLabel("<html>Procedimentos: " + 
                String.join(", ", consulta.getProcedimentos().stream()
                    .map(Procedimento::getNome)
                    .toArray(String[]::new)) + "</html>");
            lblProcedimentos.setFont(labelFont);
            panel.add(lblProcedimentos);
        }
        
        // Adicionar cursor e evento de clique
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirDetalhesConsulta(consulta);
            }
        });
        
        return panel;
    }
    
    // Adicionar classe WrapLayout para organizar os painéis de consulta
    private class WrapLayout extends FlowLayout {
        private Dimension preferredLayoutSize;
        
        public WrapLayout(int align, int hgap, int vgap) {
            super(align, hgap, vgap);
        }
        
        @Override
        public Dimension preferredLayoutSize(Container target) {
            return layoutSize(target, true);
        }
        
        @Override
        public Dimension minimumLayoutSize(Container target) {
            return layoutSize(target, false);
        }
        
        private Dimension layoutSize(Container target, boolean preferred) {
            synchronized (target.getTreeLock()) {
                int targetWidth = target.getWidth();
                if (targetWidth == 0) {
                    targetWidth = Integer.MAX_VALUE;
                }
                
                int hgap = getHgap();
                int vgap = getVgap();
                Insets insets = target.getInsets();
                int horizontalInsetsAndGap = insets.left + insets.right + (hgap * 2);
                int maxWidth = targetWidth - horizontalInsetsAndGap;
                
                Dimension dim = new Dimension(0, 0);
                int rowWidth = 0;
                int rowHeight = 0;
                
                int nmembers = target.getComponentCount();
                for (int i = 0; i < nmembers; i++) {
                    Component m = target.getComponent(i);
                    if (m.isVisible()) {
                        Dimension d = preferred ? m.getPreferredSize() : m.getMinimumSize();
                        if (rowWidth + d.width > maxWidth) {
                            dim.height += rowHeight + vgap;
                            rowWidth = d.width;
                            rowHeight = d.height;
                        } else {
                            rowWidth += d.width + hgap;
                            rowHeight = Math.max(rowHeight, d.height);
                        }
                    }
                }
                dim.height += rowHeight;
                dim.width = maxWidth;
                
                dim.width += horizontalInsetsAndGap;
                dim.height += insets.top + insets.bottom + vgap * 2;
                
                return dim;
            }
        }
    }
    
    private void abrirDetalhesConsulta(Consulta consulta) {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
            DlgNovaConsulta dialog = new DlgNovaConsulta((JFrame) window, true);
            dialog.preencherFormulario(consulta);
            dialog.setLocationRelativeTo(window);
            dialog.setVisible(true);
            
            // Atualizar a agenda após fechar o diálogo
            if (dialog.isConsultaCadastrada() || dialog.isConsultaAlterada()) {
                atualizarVisualizacao();
            }
        }
    }
    
    // Método público para forçar atualização
    public void atualizarVisualizacao() {
        // Recarregar a configuração do sistema
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        config = configDAO.getConfiguracao();
        
        // Atualizar a agenda com a nova configuração
        atualizarAgenda();
    }
    
    // Método para abrir o diálogo de nova consulta
    private void abrirDialogNovaConsulta() {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
            DlgNovaConsulta dialog = new DlgNovaConsulta((JFrame) window, true);
            dialog.setLocationRelativeTo(window);
            dialog.setVisible(true);
            
            // Atualizar a agenda após fechar o diálogo
            if (dialog.isConsultaCadastrada() || dialog.isConsultaAlterada()) {
                atualizarVisualizacao();
            }
        }
    }
}
