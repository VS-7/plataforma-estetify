package com.ifcolab.estetify.view;

/**
 *
 * @author vitorsrgio
 */
public class FrAgenda extends javax.swing.JDialog {

    /**
     * Creates new form FrAgenda1
     */
    public FrAgenda(java.awt.Frame parent, boolean modal) {
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

        lblFiltrarPaciente = new javax.swing.JLabel();
        customCalendar2 = new com.ifcolab.estetify.components.CustomCalendar();
        lblNome1 = new javax.swing.JLabel();
        lblNome = new javax.swing.JLabel();
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        secondaryCustomButton1 = new com.ifcolab.estetify.components.SecondaryCustomButton();
        customComboBox2 = new com.ifcolab.estetify.components.CustomComboBox();
        customComboBox1 = new com.ifcolab.estetify.components.CustomComboBox();
        customComboBox3 = new com.ifcolab.estetify.components.CustomComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        customTable1 = new com.ifcolab.estetify.components.CustomTable();
        lblLogo = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblEstetify = new javax.swing.JLabel();
        lblSidebar = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
        getContentPane().setLayout(null);

        lblFiltrarPaciente.setForeground(new java.awt.Color(51, 51, 51));
        lblFiltrarPaciente.setText("Filtrar Paciente");
        getContentPane().add(lblFiltrarPaciente);
        lblFiltrarPaciente.setBounds(310, 120, 170, 17);
        getContentPane().add(customCalendar2);
        customCalendar2.setBounds(290, 190, 1030, 310);

        lblNome1.setForeground(new java.awt.Color(51, 51, 51));
        lblNome1.setText("Filtrar Enfermeira");
        getContentPane().add(lblNome1);
        lblNome1.setBounds(890, 120, 170, 17);

        lblNome.setForeground(new java.awt.Color(51, 51, 51));
        lblNome.setText("Filtrar Médico");
        getContentPane().add(lblNome);
        lblNome.setBounds(610, 120, 170, 17);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Novo Agendamento");
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(300, 80, 210, 30);

        secondaryCustomButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/search.png"))); // NOI18N
        secondaryCustomButton1.setText(" Filtrar");
        getContentPane().add(secondaryCustomButton1);
        secondaryCustomButton1.setBounds(530, 78, 200, 30);
        getContentPane().add(customComboBox2);
        customComboBox2.setBounds(880, 140, 270, 44);
        getContentPane().add(customComboBox1);
        customComboBox1.setBounds(300, 140, 270, 44);
        getContentPane().add(customComboBox3);
        customComboBox3.setBounds(590, 140, 270, 44);

        customTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(customTable1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(296, 556, 1020, 250);

        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/logo45x40.png"))); // NOI18N
        getContentPane().add(lblLogo);
        lblLogo.setBounds(10, 10, 50, 50);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCadastro.png"))); // NOI18N
        lblBackgroundTabela.setText("jLabel18");
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(240, 40, 1120, 500);

        lblEstetify.setFont(new java.awt.Font("Fira Sans Condensed Medium", 0, 18)); // NOI18N
        lblEstetify.setForeground(new java.awt.Color(51, 51, 51));
        lblEstetify.setText("Estetify");
        getContentPane().add(lblEstetify);
        lblEstetify.setBounds(60, 30, 90, 22);

        lblSidebar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/sidebar.png"))); // NOI18N
        getContentPane().add(lblSidebar);
        lblSidebar.setBounds(-460, 0, 750, 900);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Visualize e realize novos agendamentos de consultas");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(280, 40, 400, 17);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackgroundCad.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(240, 540, 1100, 280);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Agenda");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(280, 20, 210, 22);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrAgenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrAgenda dialog = new FrAgenda(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.CustomCalendar customCalendar2;
    private com.ifcolab.estetify.components.CustomComboBox customComboBox1;
    private com.ifcolab.estetify.components.CustomComboBox customComboBox2;
    private com.ifcolab.estetify.components.CustomComboBox customComboBox3;
    private com.ifcolab.estetify.components.CustomTable customTable1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblEstetify;
    private javax.swing.JLabel lblFiltrarPaciente;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblNome;
    private javax.swing.JLabel lblNome1;
    private javax.swing.JLabel lblSidebar;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private com.ifcolab.estetify.components.SecondaryCustomButton secondaryCustomButton1;
    // End of variables declaration//GEN-END:variables
}
