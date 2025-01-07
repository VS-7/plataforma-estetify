package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewFeedback;
import com.ifcolab.estetify.model.Feedback;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.dao.FeedbackDAO;
import com.ifcolab.estetify.model.exceptions.FeedbackException;
import com.ifcolab.estetify.model.valid.ValidateFeedback;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JTable;
import java.util.Optional;


public class FeedbackController {
    
    private final FeedbackDAO repositorio;
    private final ValidateFeedback validate;
    private final ConsultaController consultaController;
    private final AutenticacaoController autenticacaoController;
    
    public FeedbackController() {
        repositorio = new FeedbackDAO();
        validate = new ValidateFeedback();
        consultaController = new ConsultaController();
        autenticacaoController = new AutenticacaoController();
    }
    
    public void cadastrar(String titulo, String descricao, int avaliacao, LocalDateTime dataAvaliacao, Consulta consulta) {
        Feedback feedback = validate.validaCamposEntrada(titulo, descricao, avaliacao, consulta, dataAvaliacao);
        repositorio.save(feedback);
    }
    
    public void atualizar(String titulo, String descricao, int avaliacao, LocalDateTime dataAvaliacao, Consulta consulta) {
        Feedback feedback = validate.validaCamposEntrada(titulo, descricao, avaliacao, consulta, dataAvaliacao);
        repositorio.update(feedback);
    }
    
    public void excluir(Feedback feedback) {
        if (feedback != null) {
            repositorio.delete(feedback.getId());
        } else {
            throw new FeedbackException("Erro - Feedback inexistente.");
        }
    }
    
    public List<Feedback> findAll() {
        return repositorio.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewFeedback tmFeedback = new TMViewFeedback(repositorio.findAll());
        grd.setModel(tmFeedback);
    }
    
    public List<Consulta> buscarConsultasDisponiveis() {
        return consultaController.findAll().stream()
                               .filter(Consulta::isConcluida)
                               .collect(Collectors.toList());
    }
    
    public Optional<Feedback> buscarFeedbackPorConsulta(Consulta consulta) {
        return findAll().stream()
                       .filter(f -> f.getConsulta().getId() == consulta.getId())
                       .findFirst();
    }
    
    public void atualizarTabelaTodosFeedbacks(JTable grd) {
        TMViewFeedback tmFeedback = new TMViewFeedback(findAll());
        grd.setModel(tmFeedback);
    }
    
    public void atualizarTabelaFeedbacksUsuario(JTable grd) {
        Paciente pacienteLogado = autenticacaoController.getPacienteLogado();
        if (pacienteLogado != null) {
            List<Feedback> feedbacksUsuario = findAll().stream()
                .filter(f -> f.getConsulta().getPaciente().getId() == pacienteLogado.getId())
                .collect(Collectors.toList());
            
            TMViewFeedback tmFeedback = new TMViewFeedback(feedbacksUsuario);
            grd.setModel(tmFeedback);
        }
    }
    
    public boolean isUserAdmin() {
        return autenticacaoController.isAdmin();
    }
    
    public void excluirFeedbackSelecionado(JTable grd, int selectedRow) {
        TMViewFeedback model = (TMViewFeedback) grd.getModel();
        Feedback feedback = model.getFeedback(selectedRow);
        
        if (feedback != null) {
            excluir(feedback);
        } else {
            throw new FeedbackException("Não foi possível excluir o feedback selecionado.");
        }
    }
}
