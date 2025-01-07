package com.ifcolab.estetify.components;

import com.ifcolab.estetify.components.CustomTextArea;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class CustomTextAreaScrollPane extends JScrollPane {
    
    private final CustomTextArea textArea;
    
    public CustomTextAreaScrollPane() {
        this(new CustomTextArea());
    }
    
    public CustomTextAreaScrollPane(CustomTextArea textArea) {
        super(textArea);
        this.textArea = textArea;
        setupScrollPane();
    }
    
    private void setupScrollPane() {

        setBorder(new EmptyBorder(0, 0, 0, 0));
        
        // Configurar a barra de rolagem
        getVerticalScrollBar().setUnitIncrement(16);
        
        // Customizar aparência da barra de rolagem
        getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(217, 217, 217);
                this.trackColor = Color.WHITE;
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
    
    public CustomTextArea getTextArea() {
        return textArea;
    }
}