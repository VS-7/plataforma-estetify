package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.dao.ProcedimentoDAO;
import com.ifcolab.estetify.model.exceptions.ProcedimentoException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateProcedimento {
    
    private ProcedimentoDAO repositorio;
    
    public ValidateProcedimento() {
        repositorio = new ProcedimentoDAO();
    }
    
    public Procedimento validaCamposEntrada(
            String dataHora,
            String descricao,
            String duracao,
            String valor,
            String requisitos,
            String contraindicacoes,
            Paciente paciente,
            Enfermeira enfermeira,
            Medico medico
    ) {
        if (dataHora == null || dataHora.isEmpty()) {
            throw new ProcedimentoException("Data e hora não podem estar em branco.");
        }
        
        isValidDataHora(dataHora);
        
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
            valorNumerico = Double.parseDouble(valor.replace("R$", "").trim().replace(",", "."));
            if (valorNumerico <= 0) {
                throw new ProcedimentoException("Valor deve ser maior que zero.");
            }
        } catch (NumberFormatException e) {
            throw new ProcedimentoException("Valor inválido.");
        }
        
        if (requisitos != null && requisitos.length() > 500) {
            throw new ProcedimentoException("Requisitos muito longos. Máximo de 500 caracteres.");
        }
        
        if (contraindicacoes != null && contraindicacoes.length() > 500) {
            throw new ProcedimentoException("Contraindicações muito longas. Máximo de 500 caracteres.");
        }
        
        if (paciente == null) {
            throw new ProcedimentoException("Paciente não pode estar em branco.");
        }
        
        if (enfermeira == null) {
            throw new ProcedimentoException("Enfermeira não pode estar em branco.");
        }
        
        if (medico == null) {
            throw new ProcedimentoException("Médico não pode estar em branco.");
        }
        
        return new Procedimento(
                dataHora,
                descricao,
                duracao,
                valorNumerico,
                requisitos,
                contraindicacoes,
                paciente,
                enfermeira,
                medico
        );
    }
    
    private void isValidDataHora(String dataHora) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dataHora, 
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            
            LocalDateTime agora = LocalDateTime.now();
            if (dateTime.isBefore(agora)) {
                throw new ProcedimentoException("Data e hora não podem ser no passado.");
            }
        } catch (DateTimeParseException e) {
            throw new ProcedimentoException("Data e hora inválidas. Use o formato dd/MM/yyyy HH:mm");
        }
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
