package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.ProcedimentoController;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.exceptions.PacienteException;
import com.ifcolab.estetify.model.exceptions.ProcedimentoException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;


public class DlgGerenciaProcedimento extends javax.swing.JDialog {

    private ProcedimentoController controller;
    private int idProcedimentoEditando;
    
    public DlgGerenciaProcedimento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new ProcedimentoController();
        idProcedimentoEditando = -1;
 
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        // Adicionar listener de duplo clique
        grdProcedimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdProcedimentosMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdProcedimentos);
    }
    
    private void adicionarMascaraNosCampos() {
        try {
            // Máscara para duração: HH:mm
            MaskFormatter maskDuracao = new MaskFormatter("##:##");
            maskDuracao.setPlaceholderCharacter('_');
            maskDuracao.install(fEdtDuracaoEstimada);
            
            // Máscara para valor: R$ ###.###,##
            MaskFormatter maskValor = new MaskFormatter("R$ ###.###,##");
            maskValor.setPlaceholderCharacter('_');
            maskValor.install(fEdtValor);
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgGerenciaPaciente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void habilitarFormulario(boolean habilitar) {
        edtDescricao.setEnabled(habilitar);
        fEdtDuracaoEstimada.setEnabled(habilitar);
        fEdtValor.setEnabled(habilitar);
        edtRequisitos.setEnabled(habilitar);
        edtContraIndicacoes.setEnabled(habilitar);
        btnSalvar.setEnabled(habilitar);
    }

    private void limparFormulario() {
        edtDescricao.setText("");
        fEdtDuracaoEstimada.setText("");
        fEdtValor.setText("");
        edtRequisitos.setText("");
        edtContraIndicacoes.setText("");
    }

    private void preencherFormulario(Procedimento procedimento) {
        DecimalFormat decimalFormat = new DecimalFormat("R$ ##,##0.00");
        
        edtDescricao.setText(procedimento.getDescricao());
        
        // Formata duração HH:mm
        long horas = procedimento.getDuracaoEstimada().toHours();
        long minutos = procedimento.getDuracaoEstimada().toMinutesPart();
        fEdtDuracaoEstimada.setText(String.format("%02d:%02d", horas, minutos));
        
        // Formata valor em reais
        fEdtValor.setText(decimalFormat.format(procedimento.getValor()));
        
        edtRequisitos.setText(procedimento.getRequisitos());
        edtContraIndicacoes.setText(procedimento.getContraindicacoes());
    }

    private Object getObjetoSelecionadoNaGrid() {
        int rowCliked = grdProcedimentos.getSelectedRow();
        Object obj = null;
        if (rowCliked >= 0) {
            obj = grdProcedimentos.getModel().getValueAt(rowCliked, -1);
        }
        return obj;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDuracaoEstimada = new javax.swing.JLabel();
        lblDescricao = new javax.swing.JLabel();
        lblRequisitos = new javax.swing.JLabel();
        lblValor = new javax.swing.JLabel();
        fEdtDuracaoEstimada = new com.ifcolab.estetify.components.CustomFormattedTextField();
        fEdtValor = new com.ifcolab.estetify.components.CustomFormattedTextField();
        edtDescricao = new com.ifcolab.estetify.components.CustomTextField();
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnSalvar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.estetify.components.SecondaryCustomButton();
        edtRequisitos = new com.ifcolab.estetify.components.CustomTextField();
        edtContraIndicacoes = new com.ifcolab.estetify.components.CustomTextField();
        lblContraIndicacoes = new javax.swing.JLabel();
        tmProcedimentos = new javax.swing.JScrollPane();
        grdProcedimentos = new com.ifcolab.estetify.components.CustomTable();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lblDuracaoEstimada.setForeground(new java.awt.Color(51, 51, 51));
        lblDuracaoEstimada.setText("Duração Estimada");
        getContentPane().add(lblDuracaoEstimada);
        lblDuracaoEstimada.setBounds(700, 140, 190, 17);

        lblDescricao.setForeground(new java.awt.Color(51, 51, 51));
        lblDescricao.setText("Descrição");
        getContentPane().add(lblDescricao);
        lblDescricao.setBounds(70, 140, 310, 17);

        lblRequisitos.setForeground(new java.awt.Color(51, 51, 51));
        lblRequisitos.setText("Requisitos");
        getContentPane().add(lblRequisitos);
        lblRequisitos.setBounds(60, 210, 200, 17);

        lblValor.setForeground(new java.awt.Color(51, 51, 51));
        lblValor.setText("Valor");
        getContentPane().add(lblValor);
        lblValor.setBounds(980, 140, 230, 17);

        fEdtDuracaoEstimada.setText("Duração Estimada");
        getContentPane().add(fEdtDuracaoEstimada);
        fEdtDuracaoEstimada.setBounds(690, 160, 260, 38);

        fEdtValor.setText("Valor");
        getContentPane().add(fEdtValor);
        fEdtValor.setBounds(970, 160, 290, 38);

        edtDescricao.setText("Descrição");
        edtDescricao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtDescricaoActionPerformed(evt);
            }
        });
        getContentPane().add(edtDescricao);
        edtDescricao.setBounds(50, 160, 610, 40);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(60, 80, 170, 30);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnSalvar.setText(" Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(690, 80, 170, 30);

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/editsquare.png"))); // NOI18N
        btnEditar.setText(" Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(btnEditar);
        btnEditar.setBounds(270, 80, 170, 30);

        btnRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/trash.png"))); // NOI18N
        btnRemover.setText(" Remover");
        btnRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoverActionPerformed(evt);
            }
        });
        getContentPane().add(btnRemover);
        btnRemover.setBounds(480, 80, 170, 30);

        edtRequisitos.setText("Requisitos");
        getContentPane().add(edtRequisitos);
        edtRequisitos.setBounds(50, 230, 610, 38);

        edtContraIndicacoes.setText("Contraindicações");
        getContentPane().add(edtContraIndicacoes);
        edtContraIndicacoes.setBounds(690, 230, 570, 38);

        lblContraIndicacoes.setForeground(new java.awt.Color(51, 51, 51));
        lblContraIndicacoes.setText("Contraindicações");
        getContentPane().add(lblContraIndicacoes);
        lblContraIndicacoes.setBounds(700, 210, 400, 17);

        grdProcedimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        grdProcedimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdProcedimentosMouseClicked(evt);
            }
        });
        tmProcedimentos.setViewportView(grdProcedimentos);

        getContentPane().add(tmProcedimentos);
        tmProcedimentos.setBounds(40, 370, 1250, 420);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 330, 1350, 500);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Configure e mantenha a lista de procedimentos disponíveis, detalhando preços, duração, requisitos outros.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 750, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Gerenciar Procedimentos");
        getContentPane().add(lblTitleGerenciaMedicos);
        lblTitleGerenciaMedicos.setBounds(30, 20, 210, 22);

        lblBackgroundCadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundCrud.png"))); // NOI18N
        getContentPane().add(lblBackgroundCadastro);
        lblBackgroundCadastro.setBounds(-10, 60, 1330, 290);

        lblBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/background.png"))); // NOI18N
        lblBackground.setText("jLabel3");
        getContentPane().add(lblBackground);
        lblBackground.setBounds(0, 0, 1350, 850);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void edtDescricaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edtDescricaoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edtDescricaoActionPerformed

    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarActionPerformed
       this.limparFormulario();
       this.habilitarFormulario(true);
    }//GEN-LAST:event_btnAdicionarActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (idProcedimentoEditando > 0) {
                controller.atualizar(
                    idProcedimentoEditando,
                    edtDescricao.getText(),
                    fEdtDuracaoEstimada.getText(),
                    fEdtValor.getText(),
                    edtRequisitos.getText(),
                    edtContraIndicacoes.getText()
                );
            } else {
                controller.cadastrar(
                    edtDescricao.getText(),
                    fEdtDuracaoEstimada.getText(),
                    fEdtValor.getText(),
                    edtRequisitos.getText(),
                    edtContraIndicacoes.getText()
                );
            }

            this.idProcedimentoEditando = -1;
            controller.atualizarTabela(grdProcedimentos);
            this.habilitarFormulario(false);
            this.limparFormulario();

        } catch (ProcedimentoException e) {
            System.err.println(e.getMessage());
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        Procedimento procedimentoEditando = (Procedimento) this.getObjetoSelecionadoNaGrid();

        if (procedimentoEditando == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(procedimentoEditando);
            this.idProcedimentoEditando = procedimentoEditando.getId();
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Procedimento procedimentoExcluido = (Procedimento) this.getObjetoSelecionadoNaGrid();

        if (procedimentoExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir o Procedimento \n("
                + procedimentoExcluido.getDescricao()
                +  ") ?",
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(procedimentoExcluido);
                    controller.atualizarTabela(grdProcedimentos);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (ProcedimentoException ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void grdProcedimentosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grdProcedimentosMouseClicked
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }
    }//GEN-LAST:event_grdProcedimentosMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomTextField edtContraIndicacoes;
    private com.ifcolab.estetify.components.CustomTextField edtDescricao;
    private com.ifcolab.estetify.components.CustomTextField edtRequisitos;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtDuracaoEstimada;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtValor;
    private com.ifcolab.estetify.components.CustomTable grdProcedimentos;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblContraIndicacoes;
    private javax.swing.JLabel lblDescricao;
    private javax.swing.JLabel lblDuracaoEstimada;
    private javax.swing.JLabel lblRequisitos;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JLabel lblValor;
    private javax.swing.JScrollPane tmProcedimentos;
    // End of variables declaration//GEN-END:variables
}