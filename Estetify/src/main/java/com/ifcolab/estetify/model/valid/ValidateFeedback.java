package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Feedback;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.exceptions.FeedbackException;
import java.time.LocalDateTime;

public class ValidateFeedback {
    
    public Feedback validaCamposEntrada(
            String titulo,
            String descricao,
            int avaliacao,
            Consulta consulta,
            LocalDateTime dataAvaliacao
    ) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new FeedbackException("Título não pode estar em branco.");
        }
        
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new FeedbackException("Avaliação não pode estar em branco.");
        }
        
        if (descricao.length() > 1000) {
            throw new FeedbackException("Avaliação não pode ter mais que 1000 caracteres.");
        }
        
        if (avaliacao < 1 || avaliacao > 5) {
            throw new FeedbackException("Número de estrelas deve estar entre 1 e 5.");
        }
        
        if (consulta == null) {
            throw new FeedbackException("Consulta não pode estar em branco.");
        }
        
        return new Feedback(0, titulo, descricao , avaliacao, dataAvaliacao, consulta);
    }
}
