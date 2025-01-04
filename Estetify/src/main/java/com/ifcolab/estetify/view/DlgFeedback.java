package com.ifcolab.estetify.view;

public class DlgFeedback extends javax.swing.JDialog {

    /**
     * Creates new form FrFeedback1
     */
    public DlgFeedback(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        lblTitulo = new javax.swing.JLabel();
        lblSubtitulo = new javax.swing.JLabel();
        lblDescricaoFeedback = new javax.swing.JLabel();
        lblTituloFeedback = new javax.swing.JLabel();
        primaryCustomButton1 = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnSeusFeedbacks = new com.ifcolab.estetify.components.SecondaryCustomButton();
        lblTituloFeedback1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        customTextArea1 = new com.ifcolab.estetify.components.CustomTextArea();
        ratingStars1 = new com.ifcolab.estetify.components.RatingStars();
        edtTitulo = new com.ifcolab.estetify.components.CustomTextField();
        btnEnviarFeedback = new com.ifcolab.estetify.components.PrimaryCustomButton();
        lblLine = new javax.swing.JLabel();
        lblBackgrundFeedback = new javax.swing.JLabel();
        lblBackgrundFeedback1 = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo100x88.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(60, 40, 100, 90);

        lblTitulo.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 30)); // NOI18N
        lblTitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblTitulo.setText("Deixe seu Feedback");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(70, 130, 640, 88);

        lblSubtitulo.setFont(new java.awt.Font("Fira Sans", 0, 16)); // NOI18N
        lblSubtitulo.setForeground(new java.awt.Color(0, 0, 0));
        lblSubtitulo.setText("Cadastre aqui seu feedback");
        getContentPane().add(lblSubtitulo);
        lblSubtitulo.setBounds(70, 180, 660, 40);

        lblDescricaoFeedback.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        lblDescricaoFeedback.setForeground(new java.awt.Color(51, 51, 51));
        lblDescricaoFeedback.setText("Descrição");
        getContentPane().add(lblDescricaoFeedback);
        lblDescricaoFeedback.setBounds(60, 370, 70, 16);

        lblTituloFeedback.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        lblTituloFeedback.setForeground(new java.awt.Color(51, 51, 51));
        lblTituloFeedback.setText("Avaliar");
        getContentPane().add(lblTituloFeedback);
        lblTituloFeedback.setBounds(60, 230, 110, 16);

        primaryCustomButton1.setText("Todos Feedbacks");
        getContentPane().add(primaryCustomButton1);
        primaryCustomButton1.setBounds(700, 50, 190, 30);

        btnSeusFeedbacks.setText(" Seus Feedbacks");
        getContentPane().add(btnSeusFeedbacks);
        btnSeusFeedbacks.setBounds(920, 50, 200, 30);

        lblTituloFeedback1.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        lblTituloFeedback1.setForeground(new java.awt.Color(51, 51, 51));
        lblTituloFeedback1.setText("Titulo");
        getContentPane().add(lblTituloFeedback1);
        lblTituloFeedback1.setBounds(60, 290, 110, 16);

        customTextArea1.setColumns(20);
        customTextArea1.setRows(5);
        jScrollPane1.setViewportView(customTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 390, 450, 310);
        getContentPane().add(ratingStars1);
        ratingStars1.setBounds(60, 250, 136, 24);

        edtTitulo.setText("Insira um titulo...");
        edtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtTituloActionPerformed(evt);
            }
        });
        getContentPane().add(edtTitulo);
        edtTitulo.setBounds(60, 310, 450, 40);

        btnEnviarFeedback.setText("Enviar Feedback");
        getContentPane().add(btnEnviarFeedback);
        btnEnviarFeedback.setBounds(160, 750, 230, 30);

        lblLine.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Line.png"))); // NOI18N
        getContentPane().add(lblLine);
        lblLine.setBounds(90, 700, 390, 40);

        lblBackgrundFeedback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTmFeedback.png"))); // NOI18N
        getContentPane().add(lblBackgrundFeedback);
        lblBackgrundFeedback.setBounds(640, 30, 670, 800);

        lblBackgrundFeedback1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidebar.png"))); // NOI18N
        getContentPane().add(lblBackgrundFeedback1);
        lblBackgrundFeedback1.setBounds(-50, 0, 670, 850);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtTituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtTituloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtTituloActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnEnviarFeedback;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSeusFeedbacks;
    private com.ifcolab.estetify.components.CustomTextArea customTextArea1;
    private com.ifcolab.estetify.components.CustomTextField edtTitulo;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgrundFeedback;
    private javax.swing.JLabel lblBackgrundFeedback1;
    private javax.swing.JLabel lblDescricaoFeedback;
    private javax.swing.JLabel lblLine;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblSubtitulo;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTituloFeedback;
    private javax.swing.JLabel lblTituloFeedback1;
    private com.ifcolab.estetify.components.PrimaryCustomButton primaryCustomButton1;
    private com.ifcolab.estetify.components.RatingStars ratingStars1;
    // End of variables declaration//GEN-END:variables
}
