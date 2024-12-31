package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Feedback;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.FeedbackException;

public class ValidateFeedback {
    
    public Feedback validaCamposEntrada(
            String titulo,
            String avaliacao,
            int numAvaliacaoEstrela,
            Consulta consulta
    ) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new FeedbackException("Título não pode estar em branco.");
        }
        
        if (avaliacao == null || avaliacao.trim().isEmpty()) {
            throw new FeedbackException("Avaliação não pode estar em branco.");
        }
        
        if (avaliacao.length() > 1000) {
            throw new FeedbackException("Avaliação não pode ter mais que 1000 caracteres.");
        }
        
        if (numAvaliacaoEstrela < 1 || numAvaliacaoEstrela > 5) {
            throw new FeedbackException("Número de estrelas deve estar entre 1 e 5.");
        }
        
        if (consulta == null) {
            throw new FeedbackException("Consulta não pode estar em branco.");
        }
        
        return new Feedback(0, titulo, avaliacao, numAvaliacaoEstrela, consulta);
    }
}
