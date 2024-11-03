package com.ifcolab.estetify.components;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomComboBox extends JComboBox<String> {
    
    private static final Color BORDER_COLOR = new Color(217, 217, 217); // Mesmo cinza claro
    
    public CustomComboBox() {
        super();
        setupComboBox();
    }
    
    private void setupComboBox() {
        // Configurar fonte
        setFont(new Font("Fira Sans", Font.PLAIN, 14));
        
        // Configurar cores
        setBackground(Color.WHITE);
        setForeground(new Color(111, 111, 111)); // Mesmo cinza escuro
        
        // Remover borda padrão
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        // Tornar o componente opaco
        setOpaque(false);
        
        // Customizar UI
        setUI(new BasicComboBoxUI() {
            @Override
            protected JButton createArrowButton() {
                return new JButton() {
                    @Override
                    public void paint(Graphics g) {
                        // Desenhar seta customizada
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                          RenderingHints.VALUE_ANTIALIAS_ON);
                        
                        int width = getWidth();
                        int height = getHeight();
                        
                        // Desenhar seta
                        g2.setColor(new Color(111, 111, 111));
                        int[] xPoints = {width/4, width/2, width*3/4};
                        int[] yPoints = {height/3, height*2/3, height/3};
                        g2.fillPolygon(xPoints, yPoints, 3);
                        
                        g2.dispose();
                    }
                };
            }
        });
        
        // Customizar o renderer da lista
        setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value,
                    int index, boolean isSelected, boolean cellHasFocus) {
                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus);
                
                label.setFont(new Font("Fira Sans", Font.PLAIN, 14));
                
                if (isSelected) {
                    label.setBackground(new Color(240, 240, 240));
                    label.setForeground(new Color(111, 111, 111));
                } else {
                    label.setBackground(Color.WHITE);
                    label.setForeground(new Color(111, 111, 111));
                }
                
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                return label;
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Usar a altura do componente como raio para bordas 100% redondas
        int radius = getHeight();
        
        // Desenhar background
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
        
        // Desenhar borda
        g2.setColor(BORDER_COLOR);
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
        
        g2.dispose();
        super.paintComponent(g);
    }
}