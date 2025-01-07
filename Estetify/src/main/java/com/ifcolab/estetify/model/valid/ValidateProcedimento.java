package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ProcedimentoDAO;
import com.ifcolab.estetify.model.exceptions.ProcedimentoException;
import com.ifcolab.estetify.model.enums.TipoProcedimento;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ValidateProcedimento {
    
    
    public Procedimento validaCamposEntrada(String descricao, String duracao, String valor, String requisitos, String contraindicacoes, TipoProcedimento tipo, int intervaloRetornoDias) {
        if (descricao == null || descricao.isEmpty()) {
            throw new ProcedimentoException("Descrição não pode estar em branco.");
        }
        
        if (descricao.length() > 1000) {
            throw new ProcedimentoException("Descrição muito longa. Máximo de 1000 caracteres.");
        }
        
        if (duracao == null || duracao.isEmpty()) {
            throw new ProcedimentoException("Duração não pode estar em branco.");
        }
        
        isValidDuracao(duracao);
        
        double valorNumerico;
        try {
            NumberFormat format = NumberFormat.getInstance(new Locale("pt", "BR"));
            Number number = format.parse(valor.replace("R$", "").trim());
            valorNumerico = number.doubleValue();
            
            if (valorNumerico <= 0) {
                throw new ProcedimentoException("Valor deve ser maior que zero.");
            }
        } catch (ParseException e) {
            throw new ProcedimentoException("Valor inválido. Use o formato: R$ 0,00");
        }
        
        if (requisitos != null && requisitos.length() > 500) {
            throw new ProcedimentoException("Requisitos muito longos. Máximo de 500 caracteres.");
        }
        
        if (contraindicacoes != null && contraindicacoes.length() > 500) {
            throw new ProcedimentoException("Contraindicações muito longas. Máximo de 500 caracteres.");
        }
        
        return new Procedimento(tipo, descricao, duracao, valorNumerico, requisitos, contraindicacoes, intervaloRetornoDias);
    }
    
    private void isValidDuracao(String duracao) {
        if (!duracao.matches("^([0-9]{1,2}):([0-5][0-9])$")) {
            throw new ProcedimentoException("Duração inválida. Use o formato HH:mm");
        }
        
        String[] partes = duracao.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        
        if (horas == 0 && minutos == 0) {
            throw new ProcedimentoException("Duração deve ser maior que zero.");
        }
        
        if (horas > 12) {
            throw new ProcedimentoException("Duração máxima permitida é 12 horas.");
        }
    }
}
