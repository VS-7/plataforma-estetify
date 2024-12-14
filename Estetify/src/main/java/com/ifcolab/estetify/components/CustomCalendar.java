package com.ifcolab.estetify.components;

import com.toedter.calendar.JCalendar;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import java.lang.reflect.Field;
import javax.swing.JButton;
import java.time.LocalDate;
import java.time.ZoneId;

public class CustomCalendar extends JCalendar {
    
    public CustomCalendar() {
        // Personalização básica
        setBackground(Color.WHITE);
        setForeground(new Color(51, 51, 51));
        setFont(new Font("Fira Sans", Font.PLAIN, 12));
        setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230)));
        
        // Personalizar os dias da semana
        getDayChooser().setDecorationBackgroundColor(new Color(245, 245, 245));
        getDayChooser().setForeground(new Color(51, 51, 51));
        getDayChooser().setWeekdayForeground(new Color(102, 102, 102));
        
        // Personalizar células dos dias
        getDayChooser().setDayBordersVisible(false); // Remove as bordas das células
        getDayChooser().setBackground(Color.WHITE); // Cor de fundo padrão
        getDayChooser().setDecorationBackgroundVisible(true);
        
        // Personalizar cores usando reflexão
        try {
            // Configurar cor de seleção
            Field selectedColorField = getDayChooser().getClass().getDeclaredField("selectedColor");
            selectedColorField.setAccessible(true);
            selectedColorField.set(getDayChooser(), new Color(220, 220, 220));
            
            // Configurar cor de fundo dos dias
            Field daysField = getDayChooser().getClass().getDeclaredField("days");
            daysField.setAccessible(true);
            JButton[] days = (JButton[]) daysField.get(getDayChooser());
            
            for (JButton day : days) {
                if (day != null) {
                    day.setBackground(Color.WHITE);
                    day.setOpaque(true);
                    day.setBorderPainted(false);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Personalizar o seletor de mês
        getMonthChooser().setBackground(Color.WHITE);
        getMonthChooser().setForeground(new Color(51, 51, 51));
        
        // Personalizar o seletor de ano
        getYearChooser().setBackground(Color.WHITE);
        getYearChooser().setForeground(new Color(51, 51, 51));
        
        // Adicionar listener para manter a personalização após seleção
        getDayChooser().addPropertyChangeListener("day", evt -> {
            try {
                // Reconfigurar cor de seleção
                Field selectedColorField = getDayChooser().getClass().getDeclaredField("selectedColor");
                selectedColorField.setAccessible(true);
                selectedColorField.set(getDayChooser(), new Color(220, 220, 220));
                
                // Reconfigurar cor de fundo dos dias
                Field daysField = getDayChooser().getClass().getDeclaredField("days");
                daysField.setAccessible(true);
                JButton[] days = (JButton[]) daysField.get(getDayChooser());
                
                for (JButton day : days) {
                    if (day != null) {
                        day.setBackground(Color.WHITE);
                        day.setOpaque(true);
                        day.setBorderPainted(false);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    
    /**
     * Retorna a data selecionada no calendário como LocalDate
     * @return LocalDate selecionada ou null se nenhuma data estiver selecionada
     */
    public LocalDate getSelectedDate() {
        if (getDate() == null) {
            return null;
        }
        return getDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
    
    /**
     * Define a data selecionada no calendário a partir de um LocalDate
     * @param date LocalDate a ser definida no calendário
     */
    public void setSelectedDate(LocalDate date) {
        if (date == null) {
            return;
        }
        setDate(java.util.Date.from(
            date.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant()
        ));
    }
}
