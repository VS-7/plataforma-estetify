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
        Map<LocalTime, JPanel> horarios = new TreeMap<>();
        
        // Criar slots de horário
        LocalTime hora = config.getHorarioAbertura();
        while (!hora.isAfter(config.getHorarioFechamento())) {
            JPanel pnlHorario = criarPainelHorario(hora);
            horarios.put(hora, pnlHorario);
            hora = hora.plusMinutes(config.getIntervaloConsultaMinutos());
        }
        
        // Adicionar consultas nos horários
        for (Consulta consulta : consultas) {
            LocalTime horaConsulta = consulta.getDataHora().toLocalTime();
            JPanel pnlHorario = horarios.get(horaConsulta);
            
            if (pnlHorario != null) {
                pnlHorario.add(criarPainelConsulta(consulta));
            }
        }
        
        // Adicionar todos os horários na agenda
        horarios.values().forEach(pnlAgenda::add);
        
        // Atualizar interface
        pnlAgenda.revalidate();
        pnlAgenda.repaint();
    }
    
    private JPanel criarPainelHorario(LocalTime hora) {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        panel.setMaximumSize(new Dimension(980, 80)); // Ajustado para a nova largura
        panel.setPreferredSize(new Dimension(980, 80));
        
        // Label da hora
        JLabel lblHora = new JLabel(hora.format(DateTimeFormatter.ofPattern("HH:mm")));
        lblHora.setFont(new Font("Fira Sans", Font.BOLD, 14));
        lblHora.setPreferredSize(new Dimension(80, 30));
        lblHora.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        // Painel para consultas
        JPanel pnlConsultas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlConsultas.setBackground(Color.WHITE);
        
        panel.add(lblHora, BorderLayout.WEST);
        panel.add(pnlConsultas, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelConsulta(Consulta consulta) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(240, 247, 255));
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(200, 220, 255), 1),
            BorderFactory.createEmptyBorder(8, 12, 8, 12)
        ));
        
        // Ajustar tamanho do painel de consulta
        panel.setPreferredSize(new Dimension(200, 100)); // Reduzido para caber melhor
        
        // Informações da consulta com fonte e espaçamento adequados
        JLabel lblPaciente = new JLabel("Paciente: " + consulta.getPaciente().getNome());
        JLabel lblMedico = new JLabel("Médico: " + consulta.getMedico().getNome());
        JLabel lblEnfermeira = new JLabel("Enfermeira: " + consulta.getEnfermeira().getNome());
        
        // Configurar fonte
        Font labelFont = new Font("Fira Sans", Font.PLAIN, 12);
        lblPaciente.setFont(labelFont);
        lblMedico.setFont(labelFont);
        lblEnfermeira.setFont(labelFont);
        
        // Adicionar componentes com espaçamento
        panel.add(lblPaciente);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));
        panel.add(lblMedico);
        panel.add(Box.createRigidArea(new Dimension(0, 4)));
        panel.add(lblEnfermeira);
        
        // Adicionar procedimentos se houver
        if (!consulta.getProcedimentos().isEmpty()) {
            panel.add(Box.createRigidArea(new Dimension(0, 4)));
            JLabel lblProcedimentos = new JLabel("<html>Procedimentos: " + 
                String.join(", ", consulta.getProcedimentos().stream()
                    .map(Procedimento::getNome)
                    .toArray(String[]::new)) + "</html>");
            lblProcedimentos.setFont(labelFont);
            panel.add(lblProcedimentos);
        }
        
        return panel;
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
