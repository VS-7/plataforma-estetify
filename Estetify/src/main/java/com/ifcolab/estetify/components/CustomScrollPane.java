package com.ifcolab.estetify.components;

import com.ifcolab.estetify.utils.Colors;
import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomScrollPane extends JScrollPane implements Serializable {
    
    public CustomScrollPane(Component view) {
        super(view);
        setupScrollPane();
    }
    
    private void setupScrollPane() {
        // Remover bordas
        setBorder(BorderFactory.createEmptyBorder());
        
        // Customizar a barra de rolagem
        JScrollBar verticalBar = getVerticalScrollBar();
        verticalBar.setBackground(Colors.WHITE);
        verticalBar.setUnitIncrement(16);
        
        // Customizar o design da barra de rolagem
        verticalBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Colors.GRAY_200;
                this.trackColor = Colors.WHITE;
            }
            
            @Override
            protected JButton createDecreaseButton(int orientation) {
                return createZeroButton();
            }
            
            @Override
            protected JButton createIncreaseButton(int orientation) {
                return createZeroButton();
            }
            
            private JButton createZeroButton() {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(0, 0));
                button.setMinimumSize(new Dimension(0, 0));
                button.setMaximumSize(new Dimension(0, 0));
                return button;
            }
        });
    }
}