package com.ifcolab.estetify.components;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.model.Pessoa;

/**
 *
 * @author vitorsrgio
 */
public class AppBar extends javax.swing.JPanel {

    private final AutenticacaoController authController;
    private final Color BACKGROUND_COLOR = new Color(255, 255, 255);
    private final Font NAME_FONT = new Font("Fira Sans", Font.BOLD, 14);
    private final Font TYPE_FONT = new Font("Fira Sans", Font.PLAIN, 12);
    
    /**
     * Creates new form AppBar
     */
    public AppBar() {
        initComponents();
        authController = new AutenticacaoController();
        configurarComponentes();
        atualizarInformacoesUsuario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void configurarComponentes() {
        setBackground(BACKGROUND_COLOR);
        setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(218, 220, 224)));
        setPreferredSize(new Dimension(getWidth(), 50));
        setLayout(new BorderLayout());
        
        // Painel para informações do usuário (direita)
        JPanel pnlUsuario = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlUsuario.setBackground(BACKGROUND_COLOR);
        
        // Label para o avatar
        lblAvatar = new JLabel();
        lblAvatar.setPreferredSize(new Dimension(40, 40));
        
        // Labels para nome e tipo
        lblNome = new JLabel();
        lblNome.setFont(NAME_FONT);
        
        lblTipo = new JLabel();
        lblTipo.setFont(TYPE_FONT);
        lblTipo.setForeground(Color.GRAY);
        
        // Painel vertical para nome e tipo
        JPanel pnlInfo = new JPanel();
        pnlInfo.setLayout(new BoxLayout(pnlInfo, BoxLayout.Y_AXIS));
        pnlInfo.setBackground(BACKGROUND_COLOR);
        pnlInfo.add(lblNome);
        pnlInfo.add(lblTipo);
        
        // Adicionar componentes
        pnlUsuario.add(pnlInfo);
        pnlUsuario.add(lblAvatar);
        
        add(pnlUsuario, BorderLayout.EAST);
    }
    
    private void atualizarInformacoesUsuario() {
        Pessoa usuarioLogado = authController.getUsuarioLogado();
        if (usuarioLogado != null) {
            try {
                // Configurar avatar com o caminho correto
                String avatarPath = "/avatars/avatar_" + usuarioLogado.getAvatar() + ".png";
                
                java.net.URL imageUrl = getClass().getResource(avatarPath);
                
                if (imageUrl != null) {
                    ImageIcon avatarIcon = new ImageIcon(imageUrl);
                    Image img = avatarIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    lblAvatar.setIcon(new ImageIcon(img));
                } else {
                    java.net.URL defaultImageUrl = getClass().getResource("/avatars/avatar_1.png");
                    
                    if (defaultImageUrl != null) {
                        ImageIcon defaultIcon = new ImageIcon(defaultImageUrl);
                        Image img = defaultIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                        lblAvatar.setIcon(new ImageIcon(img));
                    } else {
                        lblAvatar.setIcon(null);
                    }
                }
                
                // Configurar nome e tipo com verificação de nulo
                lblNome.setText(usuarioLogado.getNome() != null ? usuarioLogado.getNome() : "");
                
                // Verificar se o tipo não é nulo antes de chamar toString()
                if (usuarioLogado.getTipoUsuario() != null) {
                    lblTipo.setText(usuarioLogado.getTipoUsuario().toString());
                } else {
                    lblTipo.setText("Usuário");  
                }
                
            } catch (Exception e) {
                System.err.println("Erro ao carregar avatar: " + e.getMessage());
                e.printStackTrace();
                lblAvatar.setIcon(null);
            }
        }
    }
    
    public void atualizarInterface() {
        atualizarInformacoesUsuario();
    }
    
    // Variables declaration - do not modify
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblTipo;
    // End of variables declaration
}