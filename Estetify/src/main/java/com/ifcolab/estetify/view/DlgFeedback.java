package com.ifcolab.estetify.view;

import com.ifcolab.estetify.controller.AutenticacaoController;
import com.ifcolab.estetify.controller.ConsultaController;
import com.ifcolab.estetify.controller.FeedbackController;
import com.ifcolab.estetify.controller.tablemodel.TMViewFeedback;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Feedback;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.exceptions.FeedbackException;
import com.ifcolab.estetify.model.exceptions.ValidateException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class DlgFeedback extends javax.swing.JDialog {
    
    private final FeedbackController controller;
    private Consulta selectedConsulta;
    private Feedback feedbackEmEdicao;
    
    /**
     * Creates new form FrFeedback1
     */
    public DlgFeedback(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        controller = new FeedbackController();
        
        if (!controller.usuarioPodeAcessarTela()) {
            JOptionPane.showMessageDialog(this,
                "Apenas pacientes podem cadastrar feedbacks",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            dispose();
            return;
        }
        
        setupComponents();
        setupConsultaComboBox();
        setupListeners();
        controller.atualizarTabela(grdFeedbacks);
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
        btnTodosFeedbacks = new com.ifcolab.estetify.components.PrimaryCustomButton();
        btnSeusFeedbacks = new com.ifcolab.estetify.components.SecondaryCustomButton();
        lblTituloFeedback1 = new javax.swing.JLabel();
        lblTituloFeedback2 = new javax.swing.JLabel();
        cboSelecionarProcedimento = new com.ifcolab.estetify.components.CustomComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        grdFeedbacks = new com.ifcolab.estetify.components.CustomTable();
        scrDescricao = new javax.swing.JScrollPane();
        txtDescricao = new com.ifcolab.estetify.components.CustomTextArea();
        pnlEstrelas = new com.ifcolab.estetify.components.RatingStars();
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
        lblDescricaoFeedback.setBounds(60, 470, 70, 16);

        lblTituloFeedback.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        lblTituloFeedback.setForeground(new java.awt.Color(51, 51, 51));
        lblTituloFeedback.setText("Selecionar Procedimento");
        getContentPane().add(lblTituloFeedback);
        lblTituloFeedback.setBounds(60, 250, 180, 16);

        btnTodosFeedbacks.setText("Todos Feedbacks");
        getContentPane().add(btnTodosFeedbacks);
        btnTodosFeedbacks.setBounds(700, 50, 190, 30);

        btnSeusFeedbacks.setText(" Seus Feedbacks");
        getContentPane().add(btnSeusFeedbacks);
        btnSeusFeedbacks.setBounds(920, 50, 200, 30);

        lblTituloFeedback1.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        lblTituloFeedback1.setForeground(new java.awt.Color(51, 51, 51));
        lblTituloFeedback1.setText("Titulo");
        getContentPane().add(lblTituloFeedback1);
        lblTituloFeedback1.setBounds(60, 390, 110, 16);

        lblTituloFeedback2.setFont(new java.awt.Font("Fira Sans", 0, 12)); // NOI18N
        lblTituloFeedback2.setForeground(new java.awt.Color(51, 51, 51));
        lblTituloFeedback2.setText("Avaliar");
        getContentPane().add(lblTituloFeedback2);
        lblTituloFeedback2.setBounds(60, 330, 110, 16);
        getContentPane().add(cboSelecionarProcedimento);
        cboSelecionarProcedimento.setBounds(60, 270, 460, 44);

        grdFeedbacks.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane3.setViewportView(grdFeedbacks);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(700, 110, 590, 690);

        txtDescricao.setColumns(20);
        txtDescricao.setRows(5);
        scrDescricao.setViewportView(txtDescricao);

        getContentPane().add(scrDescricao);
        scrDescricao.setBounds(60, 490, 450, 170);
        getContentPane().add(pnlEstrelas);
        pnlEstrelas.setBounds(60, 350, 136, 24);

        edtTitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edtTituloActionPerformed(evt);
            }
        });
        getContentPane().add(edtTitulo);
        edtTitulo.setBounds(60, 410, 450, 40);

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

    
    private void setupComponents() {
        // Se não for paciente, desabilita os componentes de cadastro
        boolean podeCadastrar = controller.usuarioPodeCadastrarFeedback();
        
        // Componentes de cadastro
        cboSelecionarProcedimento.setEnabled(podeCadastrar);
        edtTitulo.setEnabled(podeCadastrar);
        txtDescricao.setEnabled(podeCadastrar);
        pnlEstrelas.setEnabled(podeCadastrar);
        btnEnviarFeedback.setEnabled(podeCadastrar);
        
        // Se não for paciente, oculta os componentes de cadastro
        if (!podeCadastrar) {
            lblTituloFeedback.setVisible(false);
            lblDescricaoFeedback.setVisible(false);
            lblTituloFeedback1.setVisible(false);
            lblTituloFeedback2.setVisible(false);
            cboSelecionarProcedimento.setVisible(false);
            edtTitulo.setVisible(false);
            txtDescricao.setVisible(false);
            pnlEstrelas.setVisible(false);
            btnEnviarFeedback.setVisible(false);
        }
    }
    
    private void setupConsultaComboBox() {
        if (controller.usuarioPodeCadastrarFeedback()) {
            List<Consulta> consultas = controller.buscarConsultasDisponiveis();
            DefaultComboBoxModel<Consulta> model = new DefaultComboBoxModel<>();
            consultas.forEach(model::addElement);
            cboSelecionarProcedimento.setModel(model);
        }
    }
    
    private void setupListeners() {
        btnEnviarFeedback.addActionListener((ActionEvent e) -> {
            cadastrarFeedback();
        });
        
        cboSelecionarProcedimento.addActionListener((ActionEvent e) -> {
            selectedConsulta = (Consulta) cboSelecionarProcedimento.getSelectedItem();
            if (selectedConsulta != null) {
                verificarFeedbackExistente();
            }
        });
        
        grdFeedbacks.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2 && controller.usuarioPodeCadastrarFeedback()) {
                    editarFeedbackSelecionado();
                }
            }
        });
        
        btnSeusFeedbacks.addActionListener((ActionEvent e) -> {
            controller.filtrarFeedbacksPaciente(grdFeedbacks);
        });
        
        btnTodosFeedbacks.addActionListener((ActionEvent e) -> {
            controller.atualizarTabela(grdFeedbacks);
        });
    }
    
    private void verificarFeedbackExistente() {
        if (selectedConsulta != null) {
            controller.buscarFeedbackPorConsulta(selectedConsulta)
                     .ifPresentOrElse(
                         this::setFeedbackParaEdicao,
                         this::limparCamposExcetoConsulta
                     );
        }
    }
    
    private void cadastrarFeedback() {
        try {
            String titulo = edtTitulo.getText();
            String descricao = txtDescricao.getText();
            int avaliacao = pnlEstrelas.getRating();
            
            if (feedbackEmEdicao != null) {
                controller.atualizar(
                    titulo,
                    descricao,
                    avaliacao,
                    LocalDateTime.now(),
                    selectedConsulta
                );
                mostrarMensagemSucesso("Feedback atualizado com sucesso!");
            } else {
                controller.cadastrar(
                    titulo,
                    descricao,
                    avaliacao,
                    LocalDateTime.now(),
                    selectedConsulta
                );
                mostrarMensagemSucesso("Feedback cadastrado com sucesso!");
            }
            
            limparCampos();
            controller.atualizarTabela(grdFeedbacks);
            
        } catch (ValidateException ex) {
            mostrarMensagemErro(ex.getMessage());
        }
    }
    
    private void setFeedbackParaEdicao(Feedback feedback) {
        if (!controller.usuarioPodeCadastrarFeedback()) {
            return;
        }
        
        this.feedbackEmEdicao = feedback;
        
        edtTitulo.setText(feedback.getTitulo());
        txtDescricao.setText(feedback.getDescricao());
        pnlEstrelas.setRating(feedback.getAvaliacao());
        
        selecionarConsultaNoCombo(feedback.getConsulta());
        btnEnviarFeedback.setText("Atualizar Feedback");
    }
    
    private void selecionarConsultaNoCombo(Consulta consulta) {
        for (int i = 0; i < cboSelecionarProcedimento.getItemCount(); i++) {
            Consulta item = (Consulta) cboSelecionarProcedimento.getItemAt(i);
            if (item.getId() == consulta.getId()) {
                cboSelecionarProcedimento.setSelectedIndex(i);
                selectedConsulta = item;
                break;
            }
        }
    }
    
    private void editarFeedbackSelecionado() {
        int row = grdFeedbacks.getSelectedRow();
        if (row != -1) {
            Feedback feedback = ((TMViewFeedback)grdFeedbacks.getModel()).getFeedback(row);
            if (controller.podeEditarFeedback(feedback)) {
                setFeedbackParaEdicao(feedback);
            } else {
                mostrarMensagemErro("Você não tem permissão para editar este feedback");
            }
        }
    }
    
    private void limparCampos() {
        edtTitulo.setText("");
        txtDescricao.setText("");
        pnlEstrelas.setRating(0);
        cboSelecionarProcedimento.setSelectedIndex(-1);
        selectedConsulta = null;
        feedbackEmEdicao = null;
        btnEnviarFeedback.setText("Enviar Feedback");
    }
    
    private void limparCamposExcetoConsulta() {
        edtTitulo.setText("");
        txtDescricao.setText("");
        pnlEstrelas.setRating(0);
        feedbackEmEdicao = null;
        btnEnviarFeedback.setText("Enviar Feedback");
    }
    
    private void mostrarMensagemSucesso(String mensagem) {
        JOptionPane.showMessageDialog(this, 
            mensagem,
            "Sucesso",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void mostrarMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this,
            mensagem,
            "Erro",
            JOptionPane.ERROR_MESSAGE);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.ifcolab.estetify.components.PrimaryCustomButton btnEnviarFeedback;
    private com.ifcolab.estetify.components.SecondaryCustomButton btnSeusFeedbacks;
    private com.ifcolab.estetify.components.PrimaryCustomButton btnTodosFeedbacks;
    private com.ifcolab.estetify.components.CustomComboBox cboSelecionarProcedimento;
    private com.ifcolab.estetify.components.CustomTextField edtTitulo;
    private com.ifcolab.estetify.components.CustomTable grdFeedbacks;
    private javax.swing.JScrollPane jScrollPane3;
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
    private javax.swing.JLabel lblTituloFeedback2;
    private com.ifcolab.estetify.components.RatingStars pnlEstrelas;
    private javax.swing.JScrollPane scrDescricao;
    private com.ifcolab.estetify.components.CustomTextArea txtDescricao;
    // End of variables declaration//GEN-END:variables
}
