package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Feedback;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.dao.FeedbackDAO;
import com.ifcolab.estetify.model.exceptions.FeedbackException;
import com.ifcolab.estetify.model.valid.ValidateFeedback;
import java.util.List;


public class FeedbackController {
    
    private FeedbackDAO repositorio;
    private ValidateFeedback validate;
    
    public FeedbackController() {
        repositorio = new FeedbackDAO();
        validate = new ValidateFeedback();
    }
    
    public void cadastrar(
            String titulo,
            String avaliacao,
            int numAvaliacaoEstrela,
            Consulta consulta) throws FeedbackException {
            
        Feedback feedback = validate.validaCamposEntrada(
            titulo,
            avaliacao,
            numAvaliacaoEstrela,
            consulta
        );
        
        repositorio.save(feedback);
    }
    
    public void atualizar(
            int id,
            String titulo,
            String avaliacao,
            int numAvaliacaoEstrela,
            Consulta consulta) throws FeedbackException {
            
        Feedback feedback = validate.validaCamposEntrada(
            titulo,
            avaliacao,
            numAvaliacaoEstrela,
            consulta
        );
        
        feedback.setId(id);
        repositorio.update(feedback);
    }
    
    public void excluir(Feedback feedback) throws FeedbackException {
        repositorio.delete(feedback.getId());
    }
    
    public List<Feedback> findAll() {
        return repositorio.findAll();
    }
    
}
