package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.model.Pessoa;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import javax.swing.JOptionPane;

public class DlgTrocarSenha extends javax.swing.JDialog {

    
    private final GerenciadorCriptografia gerenciadorCriptografia;
    private final AutenticacaoController autenticacaoController;
    private final Pessoa usuario;
    
    public DlgTrocarSenha(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.setLocationRelativeTo(null);
        this.setTitle("Trocar Senha");
        gerenciadorCriptografia = new GerenciadorCriptografia();
        autenticacaoController = new AutenticacaoController();
        usuario = autenticacaoController.getUsuarioLogado();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlBackground = new javax.swing.JPanel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        lblTelefone = new javax.swing.JLabel();
        lblTelefone1 = new javax.swing.JLabel();
        btnTrocarSenha = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnCancelar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        edtConfirmarSenha = new com.ifcolab.estetify.components.CustomPasswordField();
        edtNovaSenha = new com.ifcolab.estetify.components.CustomPasswordField();
        edtSenhaAtual = new com.ifcolab.estetify.components.CustomPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setMinimumSize(new java.awt.Dimension(800, 550));
        getContentPane().setLayout(null);

        pnlBackground.setBackground(new java.awt.Color(255, 255, 255));
        pnlBackground.setLayout(null);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Troque sua senha e fique ainda mais seguro.");
        pnlBackground.add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 550, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Modifique sua senha");
        pnlBackground.add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Senha Atual");
        pnlBackground.add(lblNome);
        lblNome.setBounds(170, 120, 160, 17);

        lblTelefone.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone.setText("Nova Senha");
        pnlBackground.add(lblTelefone);
        lblTelefone.setBounds(170, 190, 260, 17);

        lblTelefone1.setForeground(new java.awt.Color(51, 51, 51));
        lblTelefone1.setText("Confirme sua Senha");
        pnlBackground.add(lblTelefone1);
        lblTelefone1.setBounds(170, 260, 260, 17);

        btnTrocarSenha.setText("Trocar Senha");
        btnTrocarSenha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTrocarSenhaActionPerformed(evt);
            }
        });
        pnlBackground.add(btnTrocarSenha);
        btnTrocarSenha.setBounds(420, 370, 200, 38);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        pnlBackground.add(btnCancelar);
        btnCancelar.setBounds(160, 370, 200, 38);
        pnlBackground.add(edtConfirmarSenha);
        edtConfirmarSenha.setBounds(160, 280, 460, 40);
        pnlBackground.add(edtNovaSenha);
        edtNovaSenha.setBounds(160, 210, 460, 40);
        pnlBackground.add(edtSenhaAtual);
        edtSenhaAtual.setBounds(160, 140, 460, 40);

        getContentPane().add(pnlBackground);
        pnlBackground.setBounds(0, 0, 800, 550);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnTrocarSenhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTrocarSenhaActionPerformed
        try {
            String senhaAtual = new String(edtSenhaAtual.getPassword());
            String novaSenha = new String(edtNovaSenha.getPassword());
            String confirmarSenha = new String(edtConfirmarSenha.getPassword());

            // Validações
            if (senhaAtual.isEmpty() || novaSenha.isEmpty() || confirmarSenha.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos os campos são obrigatórios!");
                return;
            }

            if (!novaSenha.equals(confirmarSenha)) {
                JOptionPane.showMessageDialog(this, "A nova senha e a confirmação não coincidem!");
                return;
            }

            // Verifica se a senha atual está correta usando o método compararSenha
            if (!gerenciadorCriptografia.compararSenha(senhaAtual, usuario.getSenha())) {
                JOptionPane.showMessageDialog(this, "Senha atual incorreta!");
                return;
            }

            // Criptografa e atualiza a nova senha
            String novaSenhaHash = gerenciadorCriptografia.criptografarSenha(novaSenha);

            // Atualiza no banco de dados
            autenticacaoController.atualizarSenhaUsuario(usuario.getId(), novaSenhaHash);

            JOptionPane.showMessageDialog(this, "Senha alterada com sucesso!");
            this.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao alterar senha: " + ex.getMessage());
        }
    }//GEN-LAST:event_btnTrocarSenhaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.SecondaryCustomButton btnCancelar;
    private com.ifcolab.estetify.components.PrimaryCustomButton btnTrocarSenha;
    private com.ifcolab.estetify.components.CustomPasswordField edtConfirmarSenha;
    private com.ifcolab.estetify.components.CustomPasswordField edtNovaSenha;
    private com.ifcolab.estetify.components.CustomPasswordField edtSenhaAtual;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTelefone;
    private javax.swing.JLabel lblTelefone1;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JPanel pnlBackground;
    // End of variables declaration//GEN-END:variables
}
