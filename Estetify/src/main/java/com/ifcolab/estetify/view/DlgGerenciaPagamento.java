package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.PagamentoController;
import com.ifcolab.estetify.controller.tablemodel.TMViewPagamento;
import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.enums.MetodoPagamento;
import com.ifcolab.estetify.model.enums.StatusPagamento;
import com.ifcolab.estetify.model.exceptions.PagamentoException;
import com.ifcolab.estetify.model.exceptions.ValidateException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class DlgGerenciaPagamento extends javax.swing.JDialog {

    private final PagamentoController controller;
    private int idPagamentoEditando;
    

    public DlgGerenciaPagamento(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        this.controller = new PagamentoController();
        this.idPagamentoEditando = -1;
        
        this.configurarComboBoxes();
        this.adicionarMascaraNosCampos();
        this.habilitarFormulario(false);
        this.limparFormulario();
        
        grdPagamentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grdPagamentosMouseClicked(evt);
            }
        });
        
        controller.atualizarTabela(grdPagamentos);
    }
    
    private void configurarComboBoxes() {
        cboMetodoPagamento.removeAllItems();
        for (MetodoPagamento metodo : MetodoPagamento.values()) {
            cboMetodoPagamento.addItem(metodo);
        }
        
        cboStatus.removeAllItems();
        for (StatusPagamento status : StatusPagamento.values()) {
            cboStatus.addItem(status);
        }
    }
    
    private void adicionarMascaraNosCampos() {
        try {
            // Máscara para valor monetário
            MaskFormatter maskValor = new MaskFormatter("R$ ###,###.##");
            maskValor.setPlaceholderCharacter('0');
            maskValor.install(fEdtValor);
            
        } catch (ParseException ex) {
            Logger.getLogger(DlgGerenciaPagamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void habilitarFormulario(boolean habilitar) {
        fEdtValor.setEnabled(habilitar);
        cboStatus.setEnabled(habilitar);
        cboStatus.setEnabled(habilitar);
        txtDetalhes.setEnabled(habilitar);
        btnEmitirRecibo.setEnabled(habilitar);
    }
    
    private void limparFormulario() {
        fEdtValor.setText("");
        cboStatus.setSelectedItem(null);
        cboStatus.setSelectedItem(null);
        txtDetalhes.setText("");
    }
    
    private void preencherFormulario(Pagamento pagamento) {
        try {
            
            DecimalFormat df = new DecimalFormat("R$ #,##0.00");
            fEdtValor.setValue(pagamento.getValor());
            
            cboMetodoPagamento.setSelectedItem(pagamento.getMetodoPagamento());
            cboStatus.setSelectedItem(pagamento.getStatus());
            
            txtDetalhes.setText(pagamento.getDetalhes() != null ? pagamento.getDetalhes() : "");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao preencher formulário: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void grdPagamentosMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            btnEditarActionPerformed(null);
        }
    }
    
    private Pagamento getObjetoSelecionadoNaGrid() {
        int linhaSelecionada = grdPagamentos.getSelectedRow();
        if (linhaSelecionada >= 0) {
            TMViewPagamento modelo = (TMViewPagamento) grdPagamentos.getModel();
            return modelo.getPagamento(linhaSelecionada);
        }
        return null;
    }
    
    private void btnAdicionarActionPerformed(java.awt.event.ActionEvent evt) {
        this.limparFormulario();
        this.habilitarFormulario(true);
    }
    
    
    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        Pagamento pagamentoEditando = this.getObjetoSelecionadoNaGrid();

        if (pagamentoEditando == null) {
            JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
            return;
        }

        try {
            this.limparFormulario();
            this.habilitarFormulario(true);
            this.preencherFormulario(pagamentoEditando);
            this.idPagamentoEditando = pagamentoEditando.getId();
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao editar pagamento: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void btnEmitirReciboActionPerformed(java.awt.event.ActionEvent evt) {
        Pagamento pagamento = (Pagamento) this.getObjetoSelecionadoNaGrid();

        if (pagamento == null) {
            JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
            return;
        }

        try {
            controller.emitirRecibo(pagamento);
            JOptionPane.showMessageDialog(this, "Recibo emitido com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao emitir recibo: " + e.getMessage(),
                "Erro",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblDetalhes = new javax.swing.JLabel();
        lblStatus = new javax.swing.JLabel();
        btnAdicionar = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnEmitirRecibo = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnEditar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        btnRemover = new com.ifcolab.estetify.components.SecondaryCustomButton();
        cboStatus = new com.ifcolab.estetify.components.CustomComboBox();
        lblValor = new javax.swing.JLabel();
        cboMetodoPagamento = new com.ifcolab.estetify.components.CustomComboBox();
        lblMetodoPagamento = new javax.swing.JLabel();
        fEdtValor = new com.ifcolab.estetify.components.CustomFormattedTextField();
        btnSalvar = new com.ifcolab.estetify.components.SecondaryCustomButton();
        txtObservacoesScrollPane = new javax.swing.JScrollPane();
        txtDetalhes = new com.ifcolab.estetify.components.CustomTextArea();
        tmPagamentos = new javax.swing.JScrollPane();
        grdPagamentos = new com.ifcolab.estetify.components.CustomTable();
        lblBackgroundTabela = new javax.swing.JLabel();
        lblSubtituloGerenciaMedicos = new javax.swing.JLabel();
        lblTitleGerenciaMedicos = new javax.swing.JLabel();
        lblBackgroundCadastro = new javax.swing.JLabel();
        lblBackground = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1350, 870));
        getContentPane().setLayout(null);

        lblDetalhes.setForeground(new java.awt.Color(51, 51, 51));
        lblDetalhes.setText("Detalhes (opcional)");
        getContentPane().add(lblDetalhes);
        lblDetalhes.setBounds(680, 140, 160, 17);

        lblStatus.setForeground(new java.awt.Color(51, 51, 51));
        lblStatus.setText("Status");
        getContentPane().add(lblStatus);
        lblStatus.setBounds(360, 220, 530, 17);

        btnAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/addsquare.png"))); // NOI18N
        btnAdicionar.setText(" Adicionar");
        btnAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAdicionar);
        btnAdicionar.setBounds(60, 80, 170, 30);

        btnEmitirRecibo.setText("Emitir Recibo");
        btnEmitirRecibo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmitirReciboActionPerformed(evt);
            }
        });
        getContentPane().add(btnEmitirRecibo);
        btnEmitirRecibo.setBounds(900, 80, 170, 30);

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
        getContentPane().add(cboStatus);
        cboStatus.setBounds(350, 240, 260, 44);

        lblValor.setForeground(new java.awt.Color(51, 51, 51));
        lblValor.setText("Valor");
        getContentPane().add(lblValor);
        lblValor.setBounds(70, 140, 160, 17);
        getContentPane().add(cboMetodoPagamento);
        cboMetodoPagamento.setBounds(60, 240, 260, 44);

        lblMetodoPagamento.setForeground(new java.awt.Color(51, 51, 51));
        lblMetodoPagamento.setText("Metodo de Pagamento");
        getContentPane().add(lblMetodoPagamento);
        lblMetodoPagamento.setBounds(70, 220, 530, 17);

        fEdtValor.setText("Valor");
        getContentPane().add(fEdtValor);
        fEdtValor.setBounds(70, 170, 540, 38);

        btnSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/save.png"))); // NOI18N
        btnSalvar.setText(" Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(690, 80, 170, 30);

        txtDetalhes.setColumns(20);
        txtDetalhes.setRows(5);
        txtObservacoesScrollPane.setViewportView(txtDetalhes);

        getContentPane().add(txtObservacoesScrollPane);
        txtObservacoesScrollPane.setBounds(670, 160, 590, 130);

        grdPagamentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tmPagamentos.setViewportView(grdPagamentos);

        getContentPane().add(tmPagamentos);
        tmPagamentos.setBounds(40, 380, 1260, 420);

        lblBackgroundTabela.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/backgroundTableModel.png"))); // NOI18N
        getContentPane().add(lblBackgroundTabela);
        lblBackgroundTabela.setBounds(-10, 340, 1390, 500);

        lblSubtituloGerenciaMedicos.setFont(new java.awt.Font("Fira Sans Medium", 0, 13)); // NOI18N
        lblSubtituloGerenciaMedicos.setForeground(new java.awt.Color(102, 102, 102));
        lblSubtituloGerenciaMedicos.setText("Administre os dados dos pagamentos, incluindo valor, método de pagamento e detalhes.");
        getContentPane().add(lblSubtituloGerenciaMedicos);
        lblSubtituloGerenciaMedicos.setBounds(30, 40, 760, 17);

        lblTitleGerenciaMedicos.setFont(new java.awt.Font("Fira Sans SemiBold", 0, 18)); // NOI18N
        lblTitleGerenciaMedicos.setForeground(new java.awt.Color(51, 51, 51));
        lblTitleGerenciaMedicos.setText("Gerenciar Pagamentos");
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

    private void btnRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoverActionPerformed
        Pagamento pagamentoExcluido = (Pagamento) this.getObjetoSelecionadoNaGrid();

        if (pagamentoExcluido == null)
        JOptionPane.showMessageDialog(this, "Primeiro selecione um registro na tabela.");
        else {
            int response = JOptionPane.showConfirmDialog(null,
                "Deseja excluir o Pagamento \n("
                + pagamentoExcluido.getValor() + ", "
                + pagamentoExcluido.getMetodoPagamento() + ", "
                + pagamentoExcluido.getStatus() + ") ?",
                "Confirmar exclusão",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.OK_OPTION) {
                try {
                    controller.excluir(pagamentoExcluido);
                    controller.atualizarTabela(grdPagamentos);
                    JOptionPane.showMessageDialog(this, "Exclusão feita com sucesso!");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
    }//GEN-LAST:event_btnRemoverActionPerformed

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        try {
            if (idPagamentoEditando > 0) {
                String valorTexto = fEdtValor.getText()
                    .replace("R$ ", "")
                    .replace(".", "")
                    .replace(",", ".");
                
                double valor = Double.parseDouble(valorTexto);
                
                // Corrigindo os casts dos ComboBoxes
                Object metodoPagamentoSelecionado = cboMetodoPagamento.getSelectedItem();
                Object statusSelecionado = cboStatus.getSelectedItem();
                
                if (!(metodoPagamentoSelecionado instanceof MetodoPagamento)) {
                    throw new PagamentoException("Selecione um método de pagamento válido.");
                }
                
                if (!(statusSelecionado instanceof StatusPagamento)) {
                    throw new PagamentoException("Selecione um status de pagamento válido.");
                }
                
                MetodoPagamento metodoPagamento = (MetodoPagamento) metodoPagamentoSelecionado;
                StatusPagamento status = (StatusPagamento) statusSelecionado;
                String detalhes = txtDetalhes.getText();
                
                controller.atualizar(
                    idPagamentoEditando,
                    valor,
                    metodoPagamento,
                    status,
                    detalhes
                );
                
                JOptionPane.showMessageDialog(this, 
                    "Pagamento atualizado com sucesso!", 
                    "Sucesso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                this.idPagamentoEditando = -1;
                controller.atualizarTabela(grdPagamentos);
                this.habilitarFormulario(false);
                this.limparFormulario();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                "Valor inválido. Digite um número válido.", 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        } catch (ValidateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Erro ao salvar pagamento: " + e.getMessage(), 
                "Erro", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnSalvarActionPerformed

    /**
     * @param args the command line arguments
     */


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnAdicionar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEditar;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnEmitirRecibo;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnRemover;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSalvar;
    private com.ifcolab.estetify.components.CustomComboBox cboMetodoPagamento;
    private com.ifcolab.estetify.components.CustomComboBox cboStatus;
    private com.ifcolab.estetify.components.CustomFormattedTextField fEdtValor;
    private com.ifcolab.estetify.components.CustomTable grdPagamentos;
    private javax.swing.JLabel lblBackground;
    private javax.swing.JLabel lblBackgroundCadastro;
    private javax.swing.JLabel lblBackgroundTabela;
    private javax.swing.JLabel lblDetalhes;
    private javax.swing.JLabel lblMetodoPagamento;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblSubtituloGerenciaMedicos;
    private javax.swing.JLabel lblTitleGerenciaMedicos;
    private javax.swing.JLabel lblValor;
    private javax.swing.JScrollPane tmPagamentos;
    private com.ifcolab.estetify.components.CustomTextArea txtDetalhes;
    private javax.swing.JScrollPane txtObservacoesScrollPane;
    // End of variables declaration//GEN-END:variables
}
