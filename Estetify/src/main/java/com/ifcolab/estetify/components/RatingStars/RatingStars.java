package com.ifcolab.estetify.components;

import com.ifcolab.estetify.utils.Colors;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.io.Serializable;

public class RatingStars extends JPanel implements Serializable {
    
    private static final int STAR_SIZE = 24;
    private static final int STAR_GAP = 4;
    private static final Color STAR_FILLED = new Color(255, 196, 0); // Cor dourada
    private static final Color STAR_EMPTY = Colors.GRAY_200;
    
    private int rating = 0;
    private int hoverRating = -1;
    private boolean isEnabled = true;
    
    public RatingStars() {
        setupRatingStars();
    }
    
    private void setupRatingStars() {
        setOpaque(false);
        setPreferredSize(new Dimension((STAR_SIZE * 5) + (STAR_GAP * 4), STAR_SIZE));
        
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!isEnabled) return;
                updateRating(getStarFromPosition(e.getX()));
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isEnabled) return;
                hoverRating = -1;
                repaint();
            }

            private void updateRating(int starFromPosition) {
                throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
            }
        });
        
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (!isEnabled) return;
                int star = getStarFromPosition(e.getX());
                if (star != hoverRating) {
                    hoverRating = star;
                    repaint();
                }
            }
        });
    }
    
    private int getStarFromPosition(int x) {
        int starWidth = STAR_SIZE + STAR_GAP;
        int star = (x / starWidth) + 1;
        return Math.min(Math.max(star, 1), 5);
    }
    
    public void setRating(int rating) {
        this.rating = Math.min(Math.max(rating, 0), 5);
        repaint();
    }
    
    public int getRating() {
        return rating;
    }
    
    public void setEnabled(boolean enabled) {
        this.isEnabled = enabled;
        repaint();
    }
    
    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        for (int i = 0; i < 5; i++) {
            int x = i * (STAR_SIZE + STAR_GAP);
            boolean filled = (hoverRating != -1) ? (i < hoverRating) : (i < rating);
            drawStar(g2, x, 0, filled);
        }
        
        g2.dispose();
    }
    
    private void drawStar(Graphics2D g2, int x, int y, boolean filled) {
        // Criar o caminho da estrela
        Path2D star = new Path2D.Float();
        
        // Calcular pontos da estrela
        float centerX = x + STAR_SIZE / 2f;
        float centerY = y + STAR_SIZE / 2f;
        float outerRadius = STAR_SIZE / 2f;
        float innerRadius = outerRadius * 0.382f; // Proporção áurea
        
        for (int i = 0; i < 10; i++) {
            double angle = Math.PI / 2 - (2 * Math.PI * i / 10);
            float radius = (i % 2 == 0) ? outerRadius : innerRadius;
            float px = centerX + (float) (Math.cos(angle) * radius);
            float py = centerY - (float) (Math.sin(angle) * radius);
            
            if (i == 0) {
                star.moveTo(px, py);
            } else {
                star.lineTo(px, py);
            }
        }
        star.closePath();
        
        // Desenhar a estrela
        g2.setColor(filled ? STAR_FILLED : STAR_EMPTY);
        g2.fill(star);
    }
}