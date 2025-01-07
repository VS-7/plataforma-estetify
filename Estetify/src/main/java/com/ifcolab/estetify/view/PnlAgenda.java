package com.ifcolab.estetify.view;

import com.ifcolab.estetify.components.PrimaryCustomButton;
import com.ifcolab.estetify.components.SecondaryCustomButton;
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
import com.ifcolab.estetify.view.DlgOpcoesConsulta;

public class PnlAgenda extends javax.swing.JPanel {
    private final ConsultaController consultaController;
    private ConfiguracaoSistema config;
    private LocalDate dataAtual;
    private JPanel pnlHeader;
    private JPanel pnlAgenda;
    private JLabel lblMes;
    private SecondaryCustomButton btnAnterior;
    private SecondaryCustomButton btnProximo;
    
    public PnlAgenda() {
        consultaController = new ConsultaController();
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        config = configDAO.getConfiguracao();
        dataAtual = LocalDate.now();
        
        initComponents();
    }
    
    private void initComponents() {
        
        setLayout(null);
        setBackground(Color.WHITE);
        setBounds(0, 0, 1040, 730);
        
        JLabel lblTitle = new JLabel("Agenda");
        lblTitle.setFont(new Font("Fira Sans SemiBold", Font.PLAIN, 18));
        lblTitle.setForeground(new Color(51, 51, 51));
        lblTitle.setBounds(30, 20, 210, 22);
        add(lblTitle);
        
        JLabel lblSubtitle = new JLabel("Visualize e gerencie as consultas agendadas.");
        lblSubtitle.setFont(new Font("Fira Sans Medium", Font.PLAIN, 13));
        lblSubtitle.setForeground(new Color(102, 102, 102));
        lblSubtitle.setBounds(30, 40, 720, 17);
        add(lblSubtitle);
        
        PrimaryCustomButton btnNovaConsulta = new PrimaryCustomButton("Nova Consulta");
        btnNovaConsulta.setBounds(810, 30, 200, 30);
        btnNovaConsulta.addActionListener(e -> abrirDialogNovaConsulta());
        add(btnNovaConsulta);
        
        pnlHeader = new JPanel();
        pnlHeader.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        pnlHeader.setBounds(30, 80, 980, 50); // Ajustado para ficar abaixo do título
        pnlHeader.setBackground(Color.WHITE);
        pnlHeader.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(230, 230, 230)));
        
        btnAnterior = new SecondaryCustomButton("←");
        btnProximo = new SecondaryCustomButton("→");
        lblMes = new JLabel();
        lblMes.setFont(new Font("Fira Sans", Font.BOLD, 16));
        
        btnAnterior.addActionListener(e -> {
            dataAtual = dataAtual.minusDays(1);
            atualizarAgenda();
        });
        
        btnProximo.addActionListener(e -> {
            dataAtual = dataAtual.plusDays(1);
            atualizarAgenda();
        });
        
        pnlHeader.add(btnAnterior);
        pnlHeader.add(lblMes);
        pnlHeader.add(btnProximo);
        
        pnlAgenda = new JPanel();
        pnlAgenda.setLayout(new BoxLayout(pnlAgenda, BoxLayout.Y_AXIS));
        pnlAgenda.setBackground(Color.WHITE);
        
        JScrollPane scrollPane = new JScrollPane(pnlAgenda);
        scrollPane.setBounds(30, 150, 980, 550); 
        scrollPane.setBorder(null);
        scrollPane.getViewport().setBackground(Color.WHITE);
        
        add(pnlHeader);
        add(scrollPane);
        
        atualizarAgenda();
    }
    
    private void atualizarAgenda() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
        lblMes.setText(dataAtual.format(formatter));
        
        pnlAgenda.removeAll();
        
        List<Consulta> consultas = consultaController.buscarPorData(dataAtual);
        
        Map<LocalTime, List<Consulta>> consultasPorHorario = new TreeMap<>();
        
        LocalTime hora = config.getHorarioAbertura();
        while (!hora.isAfter(config.getHorarioFechamento())) {
            consultasPorHorario.put(hora, new ArrayList<>());
            hora = hora.plusMinutes(config.getIntervaloConsultaMinutos());
        }
        
        for (Consulta consulta : consultas) {
            LocalTime horaConsulta = consulta.getDataHora().toLocalTime();
            consultasPorHorario.computeIfAbsent(horaConsulta, k -> new ArrayList<>()).add(consulta);
        }
        
        consultasPorHorario.forEach((horario, consultasDoHorario) -> {
            JPanel pnlHorario = criarPainelHorario(horario);
            JPanel pnlConsultas = (JPanel) ((BorderLayout) pnlHorario.getLayout()).getLayoutComponent(BorderLayout.CENTER);
            
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
        
        JLabel lblHora = new JLabel(hora.format(DateTimeFormatter.ofPattern("HH:mm")));
        lblHora.setFont(new Font("Fira Sans", Font.BOLD, 14));
        lblHora.setPreferredSize(new Dimension(80, 30));
        lblHora.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        
        JPanel pnlConsultas = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        pnlConsultas.setBackground(Color.WHITE);
        
        panel.setMinimumSize(new Dimension(980, 100));
        panel.setPreferredSize(new Dimension(980, 100));
        
        panel.add(lblHora, BorderLayout.WEST);
        panel.add(pnlConsultas, BorderLayout.CENTER);
        
        return panel;
    }
    
    private JPanel criarPainelConsulta(Consulta consulta) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
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
        
        panel.setPreferredSize(new Dimension(250, 90));
        panel.setMaximumSize(new Dimension(250, 90));
        panel.setMinimumSize(new Dimension(250, 90));
        
        JLabel lblStatus = new JLabel(consulta.getStatus().toString());
        JLabel lblPaciente = new JLabel("Paciente: " + consulta.getPaciente().getNome());
        JLabel lblMedico = new JLabel("Médico: " + consulta.getMedico().getNome());
        JLabel lblEnfermeira = new JLabel("Enfermeira: " + consulta.getEnfermeira().getNome());
        
        Font labelFont = new Font("Fira Sans", Font.PLAIN, 11);
        Font statusFont = new Font("Fira Sans", Font.BOLD, 11);
        
        lblStatus.setFont(statusFont);
        lblPaciente.setFont(labelFont);
        lblMedico.setFont(labelFont);
        lblEnfermeira.setFont(labelFont);
        
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
        
        panel.add(lblStatus);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblPaciente);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblMedico);
        panel.add(Box.createRigidArea(new Dimension(0, 2)));
        panel.add(lblEnfermeira);
        
        if (!consulta.getProcedimentos().isEmpty()) {
            panel.add(Box.createRigidArea(new Dimension(0, 2)));
            JLabel lblProcedimentos = new JLabel("<html>Procedimentos: " + 
                String.join(", ", consulta.getProcedimentos().stream()
                    .map(Procedimento::getNome)
                    .toArray(String[]::new)) + "</html>");
            lblProcedimentos.setFont(labelFont);
            panel.add(lblProcedimentos);
        }
        
        panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                abrirDetalhesConsulta(consulta);
            }
        });
        
        return panel;
    }

    
    private void abrirDetalhesConsulta(Consulta consulta) {
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window instanceof JFrame) {
            DlgOpcoesConsulta dialog = new DlgOpcoesConsulta((JFrame) window, consulta);
            dialog.setVisible(true);
            
            if (dialog.isAlteracaoRealizada()) {
                atualizarVisualizacao();
            }
        }
    }
    
    public void atualizarVisualizacao() {
        // Recarregar a configuração do sistema
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        config = configDAO.getConfiguracao();
        
      
        atualizarAgenda();
    }
    
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
