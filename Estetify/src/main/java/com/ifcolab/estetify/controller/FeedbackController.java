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
import java.util.Collections;
import java.util.Optional;


public class FeedbackController {
    
    private final FeedbackDAO repositorio;
    private ValidateFeedback validate;
    private AutenticacaoController autenticacaoController;
    private ConsultaController consultaController;
    
    public FeedbackController() {
        repositorio = new FeedbackDAO();
        validate = new ValidateFeedback();
        autenticacaoController = new AutenticacaoController();
        consultaController = new ConsultaController();
    }
    
    public boolean usuarioPodeCadastrarFeedback() {
        return autenticacaoController.isPaciente();
    }
    
    public void cadastrar(String titulo, String descricao, int avaliacao, LocalDateTime dataAvaliacao, Consulta consulta) {
        if (!usuarioPodeCadastrarFeedback()) {
            throw new FeedbackException("Apenas pacientes podem cadastrar feedbacks");
        }
        
        Feedback feedback = validate.validaCamposEntrada(titulo, descricao, avaliacao, consulta, dataAvaliacao);
        
        repositorio.save(feedback);
    }
    
    public void atualizar(String titulo, String descricao, int avaliacao, LocalDateTime dataAvaliacao, Consulta consulta) {
        Feedback feedback = validate.validaCamposEntrada(titulo, descricao, avaliacao, consulta, dataAvaliacao);
        
        repositorio.update(feedback);
    }
    
    public void excluir(Feedback feedback) throws FeedbackException {
        repositorio.delete(feedback.getId());
    }
    
    public List<Feedback> findAll() {
        return repositorio.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewFeedback tmFeedback = new TMViewFeedback(repositorio.findAll());
        grd.setModel(tmFeedback);
    }
    
    public void filtrarTabela(JTable grd, String titulo) {
        TMViewFeedback tmFeedback = new TMViewFeedback(
            repositorio.findAll().stream()
                .filter(f -> f.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .collect(Collectors.toList())
        );
        grd.setModel(tmFeedback);
    }
    
    public boolean usuarioPodeAcessarTela() {
        return autenticacaoController.isPaciente();
    }
    
    public List<Consulta> buscarConsultasDisponiveis() {
        Paciente pacienteLogado = autenticacaoController.getPacienteLogado();
        if (pacienteLogado == null) {
            return Collections.emptyList();
        }
        
        return consultaController.buscarConsultasPorPaciente(pacienteLogado.getId())
                               .stream()
                               .filter(Consulta::isConcluida)
                               .collect(Collectors.toList());
    }
    
    public Optional<Feedback> buscarFeedbackPorConsulta(Consulta consulta) {
        return findAll().stream()
                       .filter(f -> f.getConsulta().getId() == consulta.getId())
                       .findFirst();
    }
    
    public boolean podeEditarFeedback(Feedback feedback) {
        if (autenticacaoController.isAdmin()) {
            return true;
        }
        
        if (autenticacaoController.isPaciente()) {
            return feedback.getConsulta().getPaciente().getId() == 
                   autenticacaoController.getPacienteLogado().getId();
        }
        
        return false;
    }
    
    public void filtrarFeedbacksPaciente(JTable grd) {
        Paciente pacienteLogado = autenticacaoController.getPacienteLogado();
        if (pacienteLogado == null) {
            return;
        }

        List<Feedback> feedbacksFiltrados = findAll().stream()
            .filter(f -> f.getConsulta().getPaciente().getId() == pacienteLogado.getId())
            .collect(Collectors.toList());

        TMViewFeedback tmFeedback = new TMViewFeedback(feedbacksFiltrados);
        grd.setModel(tmFeedback);
    }
}
