package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.controller.EnfermeiraController;
import com.ifcolab.estetify.controller.MedicoController;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Medico;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;


public class DlgAgenda extends javax.swing.JDialog {

    private ConsultaController consultaController;
    private MedicoController medicoController;
    private EnfermeiraController enfermeiraController;
    private Map<Date, List<Consulta>> consultasPorDia;
    
    public DlgAgenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new MedicoController();
        idMedicoEditando = -1;
 
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        // Adicionar listener de duplo clique
        grdMedicos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdMedicosMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdMedicos);
    }


    private void habilitarFormulario(boolean habilitar) {
        edtNome.setEnabled(habilitar);
        edtEmail.setEnabled(habilitar);
        fEdtCPF.setEnabled(habilitar);
        edtSexo.setEnabled(habilitar);
        fEdtDataNascimento.setEnabled(habilitar);
        fEdtTelefone.setEnabled(habilitar);
        edtEndereco.setEnabled(habilitar);
        edtCRM.setEnabled(habilitar);
        edtEspecializacao.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }

    private void limparFormulario() {
        edtNome.setText("");
        edtEmail.setText("");
        fEdtCPF.setText("");
        edtSexo.setText("");
        fEdtDataNascimento.setText("");
        fEdtTelefone.setText("");
        edtEndereco.setText("");
        edtCRM.setText("");
        edtEspecializacao.setText("");
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdMedicos.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdMedicos.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }




    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        customCalendar1 = new com.ifcolab.estetify.components.CustomCalendar();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 850));
        getContentPane().setLayout(null);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(60, 550, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(250, 550, 170, 30);
        getContentPane().add(customCalendar1);
        customCalendar1.setBounds(50, 70, 1260, 430);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Adicione, edite ou remova informações dos médicos, incluindo especialidades e dados de contato.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 780, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Agenda");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(0, 530, 1330, 290);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(0, 40, 1390, 500);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
        this.limparFormulario();
        this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Medico medicoEditando = (Medico) this.getObjetoSelecionadoNaGrid();

        if (medicoEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(medicoEditando);
            this.idMedicoEditando = medicoEditando.getId();
        }        
    }//GEN-LAST:event_btnEditarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.CustomCalendar customCalendar1;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    // End of variables declaration//GEN-END:variables
}
