package com.ifcolab.estetify.components;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class CustomTextField extends JFormattedTextField {
    
    private static final Color BORDER_COLOR = new Color(217, 217, 217); // Cinza claro
    private String placeholder;
    private Color placeholderColor = new Color(160, 160, 160);
    
    public CustomTextField() {
        super();
        setupTextField();
    }
    
    public CustomTextField(String placeholder) {
        super();
        this.placeholder = placeholder;
        setupTextField();
    }
    
    private void setupTextField() {

        setFont(new Font("Fira Sans", Font.PLAIN, 14));
        
        setBackground(Color.WHITE);
        setForeground(new Color(111, 111, 111)); // Texto em cinza escuro
        
        setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        
        setOpaque(false);
        
        addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(new Color(111, 111, 111));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (getText().isEmpty()) {
                    setText(placeholder);
                    setForeground(placeholderColor);
                }
            }
        });
    }
    
    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        setText(placeholder);
        setForeground(placeholderColor);
    }
    
    public String getPlaceholder() {
        return placeholder;
    }
    
    public void setMask(String mask) {
        try {
            MaskFormatter formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_');
            formatter.install(this);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
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