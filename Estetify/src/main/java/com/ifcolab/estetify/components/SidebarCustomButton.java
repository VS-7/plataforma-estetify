package com.ifcolab.estetify.components;

import com.ifcolab.estetify.utils.Colors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

public class SidebarCustomButton extends JButton implements Serializable {
    
    private static final int BORDER_RADIUS = 8;
    private boolean isHovered = false;
    private boolean isSelected = false;
    private Icon icon;
    
    public SidebarCustomButton() {
        this("Menu Item");
        setupButton();
    }
    
    public SidebarCustomButton(String text) {
        super(text);
        setupButton();
    }
    
    public SidebarCustomButton(String text, Icon icon) {
        super(text);
        this.icon = icon;
        setIcon(icon);
        setupButton();
    }
    
    private void setupButton() {
        // Configurar fonte
        setFont(new Font("Fira Sans", Font.PLAIN, 14));
        
        // Configurar cores
        setForeground(Colors.GRAY_500);
        
        // Configurar layout
        setHorizontalAlignment(SwingConstants.LEFT);
        
        // Remover borda padrão e adicionar padding
        setBorder(BorderFactory.createEmptyBorder(12, 20, 12, 20));
        
        // Configurar aparência
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        
        // Adicionar efeito hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) {
                    isHovered = true;
                    repaint();
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    isHovered = false;
                    repaint();
                }
            }
        });
    }
    
    public void setSelected(boolean selected) {
        this.isSelected = selected;
        repaint();
    }
    
    public boolean isSelected() {
        return isSelected;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Definir cores para diferentes estados
        if (isSelected) {
            g2.setColor(new Color(0, 0, 0, 25)); // Cinza mais escuro para selecionado
        } else if (isHovered) {
            g2.setColor(new Color(0, 0, 0, 15)); // Cinza claro para hover
        }
        
        // Desenhar background se hover ou selecionado
        if (isHovered || isSelected) {
            g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), BORDER_RADIUS, BORDER_RADIUS));
        }
        
        g2.dispose();
        super.paintComponent(g);
    }
}