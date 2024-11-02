package com.ifcolab.estetify.components;

import com.ifcolab.estetify.utils.Colors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class SecondaryCustomButton extends JButton implements Serializable {
    
    private static final int BORDER_RADIUS = 8;
    private boolean isHovered = false;
    
    public SecondaryCustomButton() {
        this("Button");
        setupButton();
    }
    
    public SecondaryCustomButton(String text) {
        super(text);
        setupButton();
    }
    
    private void setupButton() {
        // Configurar fonte
        setFont(new Font("Fira Sans", Font.PLAIN, 14));
        
        // Configurar cores
        setBackground(new Color(0, 0, 0, 0)); // Transparente
        setForeground(Colors.GRAY_500); // Texto em cinza escuro
        
        // Remover borda padrão e adicionar padding
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Configurar aparência
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        
        // Adicionar efeito hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isHovered = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                isHovered = false;
                repaint();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Desenhar background com bordas levemente arredondadas
        if (isHovered) {
            g2.setColor(new Color(0, 0, 0, 10)); // Cinza muito claro para hover
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS));
        }
        
        // Desenhar borda
        g2.setColor(Colors.GRAY_300);
        g2.setStroke(new BasicStroke(1));
        g2.draw(new RoundRectangle2D.Float(0, 0, getWidth() - 1, getHeight() - 1, BORDER_RADIUS, BORDER_RADIUS));
        
        g2.dispose();
        
        super.paintComponent(g);
    }
}