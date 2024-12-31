/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Feedback;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.dao.FeedbackDAO;
import com.ifcolab.estetify.model.exceptions.FeedbackException;
import com.ifcolab.estetify.model.valid.ValidateFeedback;
import java.util.List;
import javax.swing.JTable;

public class FeedbackController {
    
    private FeedbackDAO dao;
    private ValidateFeedback validate;
    
    public FeedbackController() {
        dao = new FeedbackDAO();
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
        
        dao.save(feedback);
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
        dao.update(feedback);
    }
    
    public void excluir(Feedback feedback) throws FeedbackException {
        dao.delete(feedback.getId());
    }
    
    public List<Feedback> findAll() {
        return dao.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        List<Feedback> lst = dao.findAll();
        TMFeedback tableModel = new TMFeedback(lst);
        grd.setModel(tableModel);
    }
}
