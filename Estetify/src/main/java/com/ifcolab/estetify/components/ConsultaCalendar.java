/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifcolab.estetify.components;

import com.ifcolab.estetify.model.Consulta;
import java.awt.Color;
import java.awt.Graphics;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import java.lang.reflect.Field;
import com.ifcolab.estetify.model.enums.StatusConsulta;

public class ConsultaCalendar extends CustomCalendar {
    
    private Map<LocalDate, List<Consulta>> consultasPorDia;
    
    public ConsultaCalendar() {
        super();
        consultasPorDia = new HashMap<>();
        
        // Sobrescrever a aparência dos dias
        try {
            Field daysField = getDayChooser().getClass().getDeclaredField("days");
            daysField.setAccessible(true);
            JButton[] days = (JButton[]) daysField.get(getDayChooser());
            
            for (JButton day : days) {
                if (day != null) {
                    day.setContentAreaFilled(false);
                    day.setOpaque(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void setConsultas(List<Consulta> consultas) {
        consultasPorDia.clear();
        
        for (Consulta consulta : consultas) {
            LocalDate data = consulta.getDataHora().toLocalDate();
            consultasPorDia.computeIfAbsent(data, k -> new ArrayList<>()).add(consulta);
        }
        
        repaint();
    }
    
    public List<Consulta> getConsultasNaData(LocalDate data) {
        return consultasPorDia.getOrDefault(data, new ArrayList<>());
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        // Desenhar indicadores de consulta
        try {
            Field daysField = getDayChooser().getClass().getDeclaredField("days");
            daysField.setAccessible(true);
            JButton[] days = (JButton[]) daysField.get(getDayChooser());
            
            for (Map.Entry<LocalDate, List<Consulta>> entry : consultasPorDia.entrySet()) {
                LocalDate data = entry.getKey();
                List<Consulta> consultasDoDia = entry.getValue();
                
                if (!consultasDoDia.isEmpty()) {
                    // Encontrar o botão correspondente à data
                    for (JButton day : days) {
                        if (day != null && day.isVisible()) {
                            String dayText = day.getText();
                            if (!dayText.isEmpty()) {
                                try {
                                    int dayNum = Integer.parseInt(dayText);
                                    if (dayNum == data.getDayOfMonth()) {
                                        // Desenhar indicadores
                                        int dotSize = 4;
                                        int dotSpacing = 6;
                                        int x = day.getX();
                                        int y = day.getY();
                                        int width = day.getWidth();
                                        int height = day.getHeight();
                                        
                                        int startX = x + (width - (dotSize * consultasDoDia.size() + 
                                                         dotSpacing * (consultasDoDia.size() - 1))) / 2;
                                        int dotY = y + height - dotSize - 2;
                                        
                                        for (Consulta consulta : consultasDoDia) {
                                            Color dotColor = getStatusColor(consulta.getStatus());
                                            g.setColor(dotColor);
                                            g.fillOval(startX, dotY, dotSize, dotSize);
                                            startX += dotSize + dotSpacing;
                                        }
                                    }
                                } catch (NumberFormatException e) {
                                    // Ignorar se não for um número
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private Color getStatusColor(StatusConsulta status) {
        switch (status) {
            case AGENDADA:
                return new Color(204, 255, 204); // Verde claro
            case CONFIRMADA:
                return new Color(255, 255, 204); // Amarelo claro
            case CONCLUIDA:
                return new Color(204, 204, 255); // Azul claro
            case CANCELADA:
                return new Color(255, 204, 204); // Vermelho claro
            default:
                return Color.WHITE;
        }
    }
}
