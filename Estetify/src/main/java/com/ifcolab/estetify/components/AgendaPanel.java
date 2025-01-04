package com.ifcolab.estetify.components;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.view.DlgNovaConsulta;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AgendaPanel extends javax.swing.JPanel {

    private LocalDate dataAtual;
    private final List<Consulta> consultas;
    private final int HORA_INICIO = 8;
    private final int HORA_FIM = 18;
    private final Color COR_CONSULTA = new Color(66, 133, 244);
    private final Color COR_GRADE = new Color(218, 220, 224);
    private final DateTimeFormatter DATA_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private final DateTimeFormatter HORA_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public AgendaPanel() {
        initComponents();
        dataAtual = LocalDate.now();
        consultas = new ArrayList<>();
        configurarComponentes();
    }

    private void configurarComponentes() {
        // Configurar painel de controles
        pnlControles.setBackground(Color.WHITE);
        lblMesAno.setFont(new Font("Fira Sans", Font.BOLD, 16));
        
        // Configurar painel de grade
        pnlGrade.setBackground(Color.WHITE);
        pnlGrade.setLayout(null); // Importante: layout nulo para posicionamento absoluto
        
        // Configurar scroll
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        scrollPane.setViewportView(pnlGrade);
        
        // Atualizar label do mês/ano
        atualizarLabelMesAno();
        
        // Adicionar listener de redimensionamento
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                atualizarAgenda();
            }
        });
    }

    private void atualizarLabelMesAno() {
        lblMesAno.setText(dataAtual.format(
            DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"))));
    }

    private void atualizarAgenda() {
        pnlGrade.removeAll();
        
        String dataAtualStr = dataAtual.format(DATA_FORMATTER);
        System.out.println("\nAtualizando agenda para: " + dataAtualStr);
        System.out.println("Total de consultas carregadas: " + consultas.size());
        
        // Filtrar consultas do dia atual
        List<Consulta> consultasDoDia = new ArrayList<>();
        for (Consulta consulta : consultas) {
            String dataConsultaStr = consulta.getDataHora().format(DATA_FORMATTER);
            System.out.println("Comparando datas - Consulta: " + dataConsultaStr 
                + " com Atual: " + dataAtualStr + " = " + dataConsultaStr.equals(dataAtualStr));
            
            if (dataConsultaStr.equals(dataAtualStr)) {
                consultasDoDia.add(consulta);
            }
        }
        
        System.out.println("Consultas encontradas para " + dataAtualStr + ": " + consultasDoDia.size());
        
        // Configurar tamanho do painel
        int alturaHora = 60;
        int larguraTotal = scrollPane.getViewport().getWidth();
        if (larguraTotal < 500) larguraTotal = 500;
        int alturaTotal = (HORA_FIM - HORA_INICIO + 1) * alturaHora;
        
        pnlGrade.setPreferredSize(new Dimension(larguraTotal, alturaTotal));
        
        // Desenhar linhas de hora
        for (int hora = HORA_INICIO; hora <= HORA_FIM; hora++) {
            JLabel lblHora = new JLabel(String.format("%02d:00", hora));
            lblHora.setBounds(10, (hora - HORA_INICIO) * alturaHora, 50, 20);
            lblHora.setForeground(Color.GRAY);
            pnlGrade.add(lblHora);
            
            JSeparator sep = new JSeparator();
            sep.setBounds(60, (hora - HORA_INICIO) * alturaHora, larguraTotal - 70, 1);
            sep.setForeground(COR_GRADE);
            pnlGrade.add(sep);
        }
        
        // Adicionar consultas do dia
        for (Consulta consulta : consultasDoDia) {
            System.out.println("Adicionando consulta à grade:");
            System.out.println("Data/Hora: " + consulta.getDataHora().format(DATA_FORMATTER) 
                + " " + consulta.getDataHora().format(HORA_FORMATTER));
            System.out.println("Paciente: " + consulta.getPaciente().getNome());
            adicionarCardConsulta(consulta);
        }
        
        pnlGrade.revalidate();
        pnlGrade.repaint();
    }

    private void adicionarCardConsulta(Consulta consulta) {
        try {
            LocalDateTime dataHora = consulta.getDataHora();
            int hora = dataHora.getHour();
            int minuto = dataHora.getMinute();
            
            // Calcular posição Y
            int alturaHora = 60;
            int y = (hora - HORA_INICIO) * alturaHora + (minuto * alturaHora / 60);
            
            // Criar card
            JPanel card = new JPanel();
            card.setLayout(new BorderLayout());
            card.setBackground(COR_CONSULTA);
            
            // Ajustar posição e tamanho do card
            int larguraCard = pnlGrade.getWidth() - 90; // Margem de 70 + 20
            if (larguraCard < 400) larguraCard = 400; // Largura mínima
            
            card.setBounds(70, y, larguraCard, 50);
            
            // Título do card
            String horaFormatada = dataHora.format(HORA_FORMATTER);
            String titulo = horaFormatada + " - " + consulta.getPaciente().getNome();
            
            JLabel lblTitulo = new JLabel(titulo);
            lblTitulo.setForeground(Color.WHITE);
            lblTitulo.setBorder(new EmptyBorder(5, 10, 5, 10));
            card.add(lblTitulo, BorderLayout.CENTER);
            
            // Adicionar hover effect
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    card.setBackground(COR_CONSULTA.brighter());
                }
                
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    card.setBackground(COR_CONSULTA);
                }
                
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    System.out.println("Consulta selecionada: " + consulta.getId());
                    // Aqui você pode abrir o DlgNovaConsulta para edição
                    if (evt.getClickCount() == 2) { // Duplo clique
                        DlgNovaConsulta dialog = new DlgNovaConsulta(null, true);
                        dialog.preencherFormulario(consulta);
                        dialog.setVisible(true);
                    }
                }
            });
            
            pnlGrade.add(card);
            card.setVisible(true); // Garantir que o card está visível
            
        } catch (Exception e) {
            System.err.println("Erro ao adicionar card da consulta: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setConsultas(List<Consulta> novasConsultas) {
        if (novasConsultas != null) {
            this.consultas.clear();
            this.consultas.addAll(novasConsultas);
            System.out.println("\nNovas consultas recebidas: " + consultas.size());
            for (Consulta c : consultas) {
                System.out.println("Consulta: " + c.getDataHora().format(DATA_FORMATTER) 
                    + " " + c.getDataHora().format(HORA_FORMATTER) 
                    + " - " + c.getPaciente().getNome());
            }
        }
        atualizarAgenda();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        pnlControles = new javax.swing.JPanel();
        btnHoje = new javax.swing.JButton();
        btnAnterior = new javax.swing.JButton();
        btnProximo = new javax.swing.JButton();
        lblMesAno = new javax.swing.JLabel();
        scrollPane = new javax.swing.JScrollPane();
        pnlGrade = new javax.swing.JPanel();

        setLayout(new BorderLayout());

        pnlControles.setLayout(new FlowLayout(FlowLayout.LEFT));

        btnHoje.setText("Hoje");
        btnHoje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHojeActionPerformed(evt);
            }
        });
        pnlControles.add(btnHoje);

        btnAnterior.setText("<");
        btnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnteriorActionPerformed(evt);
            }
        });
        pnlControles.add(btnAnterior);

        btnProximo.setText(">");
        btnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProximoActionPerformed(evt);
            }
        });
        pnlControles.add(btnProximo);

        lblMesAno.setText("Mês Ano");
        pnlControles.add(lblMesAno);

        add(pnlControles, BorderLayout.NORTH);

        pnlGrade.setLayout(null);
        scrollPane.setViewportView(pnlGrade);

        add(scrollPane, BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHojeActionPerformed(java.awt.event.ActionEvent evt) {
        dataAtual = LocalDate.now();
        atualizarLabelMesAno();
        atualizarAgenda();
    }

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {
        dataAtual = dataAtual.minusDays(1);
        atualizarLabelMesAno();
        atualizarAgenda();
    }

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {
        dataAtual = dataAtual.plusDays(1);
        atualizarLabelMesAno();
        atualizarAgenda();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnHoje;
    private javax.swing.JButton btnProximo;
    private javax.swing.JLabel lblMesAno;
    private javax.swing.JPanel pnlControles;
    private javax.swing.JPanel pnlGrade;
    private javax.swing.JScrollPane scrollPane;
    // End of variables declaration//GEN-END:variables
}
