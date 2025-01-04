package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.model.Pessoa;
import com.ifcolab.estetify.model.enums.TipoSexo;
import java.awt.Image;
import java.awt.Dimension;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;
import javax.swing.SwingUtilities;

public class pnlPerfil extends javax.swing.JPanel {

    private final AutenticacaoController autenticacaoController;
    private Pessoa usuario;

     
    public pnlPerfil() {
        initComponents();
        
        
        autenticacaoController = new AutenticacaoController();
        configurarComboBoxes();
        configurarComboBoxAvatar();
        adicionarMascaraNosCampos();
        carregarDadosUsuario();
    }
    
    private void configurarComboBoxAvatar() {
        cboAvatar.removeAllItems();
        // Adiciona opções de avatar (1 a 8 por exemplo)
        for (int i = 1; i <= 10; i++) {
            cboAvatar.addItem(i);
        }
    }

    private void atualizarPreviewAvatar(int avatarId) {
        try {
            String avatarPath = "/avatars/avatar_" + avatarId + ".png";
            java.net.URL imageUrl = getClass().getResource(avatarPath);

            if (imageUrl != null) {
                ImageIcon avatarIcon = new ImageIcon(imageUrl);
                Image img = avatarIcon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                lblAvatar.setIcon(new ImageIcon(img));

                lblAvatar.setPreferredSize(new Dimension(120, 120));
                lblAvatar.setSize(120, 120);

                lblAvatar.revalidate();
                lblAvatar.repaint();
            } else {
                System.err.println("Avatar não encontrado: " + avatarPath);
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar avatar: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private void configurarComboBoxes() {
        cboSexo.removeAllItems();
        for (TipoSexo s : TipoSexo.values()) {
            cboSexo.addItem(s);
        }
    }
    
    private void adicionarMascaraNosCampos() {
        try {
            MaskFormatter maskCPF = new MaskFormatter("###.###.###-##");
            maskCPF.setPlaceholderCharacter('_');
            maskCPF.install(fEdtCPF);
            
            MaskFormatter maskTelefone = new MaskFormatter("(##) #####-####");
            maskTelefone.setPlaceholderCharacter('_');
            maskTelefone.install(fEdtTelefone);
            
            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
            maskData.install(fEdtDataNascimento);
        } catch (ParseException ex) {
            System.err.println("Erro ao adicionar máscaras: " + ex.getMessage());
        }
    }
    
    private void carregarDadosUsuario() {
        usuario = autenticacaoController.getUsuarioLogado();
        if (usuario != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            
            edtNome.setText(usuario.getNome());
            edtEmail.setText(usuario.getEmail());
            fEdtCPF.setText(usuario.getCpf());
            cboSexo.setSelectedItem(usuario.getSexo());
            fEdtDataNascimento.setText(usuario.getDataNascimento().format(formatter));
            fEdtTelefone.setText(usuario.getTelefone());
            edtEndereco.setText(usuario.getEndereco());
            
            // Desabilitar campos que não devem ser editados
            fEdtCPF.setEnabled(false);
            fEdtDataNascimento.setEnabled(false);
            cboSexo.setEnabled(false);
            
            cboAvatar.setSelectedItem(usuario.getAvatar());
            atualizarPreviewAvatar(usuario.getAvatar());
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        edtNome = new com.ifcolab.estetify.components.CustomTextField();
        lblNome = new javax.swing.JLabel();
        edtEmail = new com.ifcolab.estetify.components.CustomTextField();
        lblEmail = new javax.swing.JLabel();
        cboAvatar = new com.ifcolab.estetify.components.CustomComboBox();
        lblAvatar = new javax.swing.JLabel();
        fEdtDataNascimento = new com.ifcolab.estetify.components.CustomFormattedTextField();
        lblDataNascimento = new javax.swing.JLabel();
        edtEndereco = new com.ifcolab.estetify.components.CustomTextField();
        lblEndereco = new javax.swing.JLabel();
        fEdtTelefone = new com.ifcolab.estetify.components.CustomFormattedTextField();
        lblTelefone = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblAvatarTitle = new javax.swing.JLabel();
        fEdtCPF = new com.ifcolab.estetify.components.CustomFormattedTextField();
        cboSexo = new com.ifcolab.estetify.components.CustomComboBox();
        lblCPF = new javax.swing.JLabel();
        lblSexo = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        btnTrocarSenha = new com.ifcolab.estetify.components.SecondaryCustomButton();
        lblAvatarTitle1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(null);

        btnAdicionar.setText(" Salvar Alterações");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        add(btnAdicionar);
        btnAdicionar.setBounds(770, 650, 220, 30);

        edtNome.setText("Nome");
        edtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtNomeActionPerformed(evt);
            }
        });
        add(edtNome);
        edtNome.setBounds(20, 100, 460, 40);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Nome");
        add(lblNome);
        lblNome.setBounds(30, 80, 160, 17);

        edtEmail.setText("E-mail");
        edtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEmailActionPerformed(evt);
            }
        });
        add(edtEmail);
        edtEmail.setBounds(20, 170, 460, 40);

        lblEmail.setForeground(new java.awt.Color(51, 51, 51));
        lblEmail.setText("Email");
        add(lblEmail);
        lblEmail.setBounds(30, 150, 170, 17);

        cboAvatar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboAvatarActionPerformed(evt);
            }
        });
        add(cboAvatar);
        cboAvatar.setBounds(20, 390, 470, 44);

        lblAvatar.setForeground(new java.awt.Color(51, 51, 51));
        add(lblAvatar);
        lblAvatar.setBounds(30, 450, 170, 0);

        fEdtDataNascimento.setText("Data de Nascimento");
        add(fEdtDataNascimento);
        fEdtDataNascimento.setBounds(20, 250, 460, 38);

        lblDataNascimento.setForeground(new java.awt.Color(51, 51, 51));
        lblDataNascimento.setText("Data de Nascimento");
        add(lblDataNascimento);
        lblDataNascimento.setBounds(30, 230, 170, 17);

        edtEndereco.setText("Endereço");
        edtEndereco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtEnderecoActionPerformed(evt);
            }
        });
        add(edtEndereco);
        edtEndereco.setBounds(20, 320, 950, 40);

        lblEndereco.setForeground(new java.awt.Color(51, 51, 51));
        lblEndereco.setText("Endereço");
        add(lblEndereco);
        lblEndereco.setBounds(30, 300, 140, 17);

        fEdtTelefone.setText("Telefone");
        add(fEdtTelefone);
        fEdtTelefone.setBounds(500, 100, 470, 38);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Telefone");
        add(lblTelefone);
        lblTelefone.setBounds(510, 80, 260, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Perfil");
        add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblAvatarTitle.setForeground(new java.awt.Color(51, 51, 51));
        lblAvatarTitle.setText("Trocar senha");
        add(lblAvatarTitle);
        lblAvatarTitle.setBounds(520, 370, 300, 17);

        fEdtCPF.setText("CPF");
        add(fEdtCPF);
        fEdtCPF.setBounds(500, 250, 470, 38);
        add(cboSexo);
        cboSexo.setBounds(500, 170, 470, 44);

        lblCPF.setForeground(new java.awt.Color(51, 51, 51));
        lblCPF.setText("CPF");
        add(lblCPF);
        lblCPF.setBounds(510, 230, 170, 17);

        lblSexo.setForeground(new java.awt.Color(51, 51, 51));
        lblSexo.setText("Sexo");
        add(lblSexo);
        lblSexo.setBounds(510, 150, 170, 17);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Personalize seu perfil ao seu gosto.");
        add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 550, 17);

        btnTrocarSenha.setText("Trocar Senha");
        btnTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarSenhaActionPerformed(evt);
            }
        });
        add(btnTrocarSenha);
        btnTrocarSenha.setBounds(520, 390, 450, 38);

        lblAvatarTitle1.setForeground(new java.awt.Color(51, 51, 51));
        lblAvatarTitle1.setText("Selecionar foto de perfil");
        add(lblAvatarTitle1);
        lblAvatarTitle1.setBounds(30, 370, 300, 17);
    }// </editor-fold>//GEN-END:initComponents

    private void edtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtNomeActionPerformed

    private void edtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEmailActionPerformed

    private void edtEnderecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtEnderecoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtEnderecoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Atualizar dados básicos
            usuario.setNome(edtNome.getText());
            usuario.setEmail(edtEmail.getText());
            usuario.setTelefone(fEdtTelefone.getText());
            usuario.setEndereco(edtEndereco.getText());
            
            int novoAvatar = (int) cboAvatar.getSelectedItem();
            
            // Atualizar no banco de dados através do controller
            autenticacaoController.atualizarDadosUsuario(usuario, novoAvatar);
            
            // Atualizar preview do avatar
            atualizarPreviewAvatar(novoAvatar);
            
            // Atualizar AppBar
            if (SwingUtilities.getWindowAncestor(this) instanceof FrMenu) {
                FrMenu frMenu = (FrMenu) SwingUtilities.getWindowAncestor(this);
                frMenu.atualizarAppBar();
            }
            
            JOptionPane.showMessageDialog(this, "Dados atualizados com sucesso!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar dados: " + ex.getMessage());
        }
    }

    private void cboAvatarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboAvatarActionPerformed
        int selectedAvatar = (int) cboAvatar.getSelectedItem();
        atualizarPreviewAvatar(selectedAvatar);
    }//GEN-LAST:event_cboAvatarActionPerformed

    private void btnTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocarSenhaActionPerformed
        DlgTrocarSenha dialog = new DlgTrocarSenha(null, true);
        dialog.setVisible(true);
    }//GEN-LAST:event_btnTrocarSenhaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnTrocarSenha;
    private com.ifcolab.estetify.components.CustomComboBox cboAvatar;
    private com.ifcolab.estetify.components.CustomComboBox cboSexo;
    private com.ifcolab.estetify.components.CustomTextField edtEmail;
    private com.ifcolab.estetify.components.CustomTextField edtEndereco;
    private com.ifcolab.estetify.components.CustomTextField edtNome;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtCPF;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtDataNascimento;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtTelefone;
    private javax.swing.JLabel lblAvatar;
    private javax.swing.JLabel lblAvatarTitle;
    private javax.swing.JLabel lblAvatarTitle1;
    private javax.swing.JLabel lblCPF;
    private javax.swing.JLabel lblDataNascimento;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblEndereco;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSexo;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    // End of variables declaration//GEN-END:variables
}
