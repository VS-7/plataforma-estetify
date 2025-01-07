package com.ifcolab.estetify.components;

import com.ifcolab.estetify.utils.Colors;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class CustomTextArea extends JTextArea {
    
    public CustomTextArea() {
        super();
        setupTextArea();
    }
    
    public CustomTextArea(int rows, int columns) {
        super(rows, columns);
        setupTextArea();
    }
    
    private void setupTextArea() {
 
        setFont(new Font("Fira Sans", Font.PLAIN, 14));
        
        setBackground(Color.WHITE);
        setForeground(new Color(111, 111, 111)); // Texto em cinza escuro
        
        setLineWrap(true);
        setWrapStyleWord(true);
        
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Usar bordas arredondadas
        int radius = 20; // Valor fixo para bordas arredondadas
        
        // Desenhar background
        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius));
        
        // Desenhar borda
        g2.setColor(Colors.GRAY_300);
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, radius, radius));
        
        g2.dispose();
        super.paintComponent(g);
    }
}