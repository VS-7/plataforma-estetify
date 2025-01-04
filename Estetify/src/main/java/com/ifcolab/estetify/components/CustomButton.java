package com.ifcolab.estetify.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import java.awt.Font;

public class CustomButton extends JButton {
    

    
    private boolean hover;
    private Color backgroundColor = Color.WHITE;
    private Color hoverColor = new Color(230, 230, 230);
    private final int ICON_PADDING = 10; // Padding do ícone em relação à borda esquerda
    
    public CustomButton() {
        setContentAreaFilled(false);
        setForeground(new Color(50, 50, 50));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(null);
        setFocusPainted(false);
        
        // Alinha o texto à esquerda com padding
        setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        
        // Adiciona padding interno
        setIconTextGap(ICON_PADDING); // Espaço entre ícone e texto
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
        
        setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Pinta o fundo do botão
        if (hover) {
            g2.setColor(hoverColor);
        } else {
            g2.setColor(backgroundColor);
        }
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        
        // Ajusta a posição do ícone e texto
        if (getIcon() != null) {
            int iconY = (getHeight() - getIcon().getIconHeight()) / 2;
            getIcon().paintIcon(this, g, ICON_PADDING, iconY);
            
            // Ajusta a posição do texto para depois do ícone
            FontMetrics fm = g2.getFontMetrics();
            int textX = ICON_PADDING * 2 + getIcon().getIconWidth();
            int textY = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
            g2.setColor(getForeground());
            g2.drawString(getText(), textX, textY);
        } else {
            super.paintComponent(g);
        }
    }

    // Getters e Setters para as cores
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        repaint();
    }

    public Color getHoverColor() {
        return hoverColor;
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
        repaint();
    }
} 