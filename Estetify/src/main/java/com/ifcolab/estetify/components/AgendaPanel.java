package com.ifcolab.estetify.components;

import com.ifcolab.estetify.model.Consulta;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class AgendaPanel extends javax.swing.JPanel {

    private LocalDate dataAtual;
    private List<Consulta> consultas;
    private final int HORA_INICIO = 8;
    private final int HORA_FIM = 18;
    private final Color COR_CONSULTA = new Color(66, 133, 244);
    private final Color COR_GRADE = new Color(218, 220, 224);

    public AgendaPanel() {
        initComponents();
        dataAtual = LocalDate.now();
        consultas = new ArrayList<>();
        configurarComponentes();
        atualizarAgenda();
    }

    private void configurarComponentes() {
        // Configurar painel de controles
        pnlControles.setBackground(Color.WHITE);
        lblMesAno.setFont(new Font("Fira Sans", Font.BOLD, 16));
        
        // Configurar painel de grade
        pnlGrade.setBackground(Color.WHITE);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        
        // Atualizar label do mês/ano
        atualizarLabelMesAno();
    }

    private void atualizarLabelMesAno() {
        lblMesAno.setText(dataAtual.format(
            DateTimeFormatter.ofPattern("MMMM yyyy", new Locale("pt", "BR"))));
    }

    private void atualizarAgenda() {
        pnlGrade.removeAll();
        
        System.out.println("Atualizando agenda para: " + dataAtual);
        System.out.println("Total de consultas: " + consultas.size());
        
        // Configurar tamanho do painel
        int alturaHora = 60;
        int larguraTotal = pnlGrade.getWidth();
        int alturaTotal = (HORA_FIM - HORA_INICIO + 1) * alturaHora;
        pnlGrade.setPreferredSize(new Dimension(larguraTotal, alturaTotal));
        
        // Desenhar linhas de hora
        for (int hora = HORA_INICIO; hora <= HORA_FIM; hora++) {
            // Label da hora
            JLabel lblHora = new JLabel(String.format("%02d:00", hora));
            lblHora.setBounds(10, (hora - HORA_INICIO) * alturaHora, 50, 20);
            lblHora.setForeground(Color.GRAY);
            pnlGrade.add(lblHora);
            
            // Linha horizontal
            JSeparator sep = new JSeparator();
            sep.setBounds(60, (hora - HORA_INICIO) * alturaHora, larguraTotal - 70, 1);
            sep.setForeground(COR_GRADE);
            pnlGrade.add(sep);
        }
        
        // Adicionar consultas do dia
        for (Consulta consulta : consultas) {
            System.out.println("Verificando consulta: " + consulta.getDataHora());
            if (consulta.getDataHora().toLocalDate().equals(dataAtual)) {
                System.out.println("Adicionando consulta à grade");
                adicionarCardConsulta(consulta);
            }
        }
        
        pnlGrade.revalidate();
        pnlGrade.repaint();
    }

    private void adicionarCardConsulta(Consulta consulta) {
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
        card.setBounds(70, y, pnlGrade.getWidth() - 80, 50);
        
        // Título do card
        JLabel lblTitulo = new JLabel(consulta.getPaciente().getNome());
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
        });
        
        pnlGrade.add(card);
    }

    public void setConsultas(List<Consulta> consultas) {
        System.out.println("Definindo consultas: " + consultas.size());
        this.consultas = consultas;
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

        setLayout(new java.awt.BorderLayout());

        pnlControles.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

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

        add(pnlControles, java.awt.BorderLayout.NORTH);

        pnlGrade.setLayout(null);
        scrollPane.setViewportView(pnlGrade);

        add(scrollPane, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHojeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHojeActionPerformed
        dataAtual = LocalDate.now();
        atualizarLabelMesAno();
        atualizarAgenda();
    }//GEN-LAST:event_btnHojeActionPerformed

    private void btnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnteriorActionPerformed
        dataAtual = dataAtual.minusDays(1);
        atualizarLabelMesAno();
        atualizarAgenda();
    }//GEN-LAST:event_btnAnteriorActionPerformed

    private void btnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProximoActionPerformed
        dataAtual = dataAtual.plusDays(1);
        atualizarLabelMesAno();
        atualizarAgenda();
    }//GEN-LAST:event_btnProximoActionPerformed

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
