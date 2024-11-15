package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ValidateConsulta {
    
    private ConsultaDAO repositorio;
    
    public ValidateConsulta() {
        repositorio = new ConsultaDAO();
    }
    
    public Consulta validaCamposEntrada(
            String dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        // Validação da data e hora
        LocalDateTime dataHoraObj = validaDataHora(dataHora);
        
        // Validação das observações
        if (observacoes != null && observacoes.length() > 1000) {
            throw new ConsultaException("Observações muito longas. Máximo de 1000 caracteres.");
        }
        
        // Validação do paciente
        if (paciente == null) {
            throw new ConsultaException("Paciente não selecionado.");
        }
        
        // Validação do médico
        if (medico == null) {
            throw new ConsultaException("Médico não selecionado.");
        }
        
        // Validação da enfermeira
        if (enfermeira == null) {
            throw new ConsultaException("Enfermeira não selecionada.");
        }
        
        // Validação dos procedimentos
        if (procedimentos == null || procedimentos.isEmpty()) {
            throw new ConsultaException("Nenhum procedimento selecionado.");
        }
        
        // Verifica disponibilidade da equipe
        if (!verificarDisponibilidadeEquipe(dataHoraObj, medico.getId(), enfermeira.getId())) {
            throw new ConsultaException("Médico ou enfermeira não disponível neste horário.");
        }
        
        return new Consulta(
                dataHoraObj,
                observacoes,
                paciente,
                medico,
                enfermeira,
                procedimentos
        );
    }
    
    private LocalDateTime validaDataHora(String dataHora) {
        if (dataHora == null || dataHora.isEmpty()) {
            throw new ConsultaException("Data e hora não podem estar em branco.");
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime dataHoraObj = LocalDateTime.parse(dataHora, formatter);
            
            // Verifica se a data é futura
            if (dataHoraObj.isBefore(LocalDateTime.now())) {
                throw new ConsultaException("A data da consulta deve ser futura.");
            }
            
            // Verifica se está dentro do horário de funcionamento (8h às 18h)
            int hora = dataHoraObj.getHour();
            if (hora < 8 || hora >= 18) {
                throw new ConsultaException("Horário fora do período de atendimento (8h às 18h).");
            }
            
            return dataHoraObj;
            
        } catch (DateTimeParseException e) {
            throw new ConsultaException("Data e hora inválidas. Use o formato dd/MM/yyyy HH:mm");
        }
    }
    
    private boolean verificarDisponibilidadeEquipe(LocalDateTime dataHora, int medicoId, int enfermeiraId) {
        return repositorio.verificarDisponibilidade(dataHora, medicoId, enfermeiraId);
    }
    
    public void validaAlteracaoStatus(Consulta consulta, String novoStatus) {
        if (consulta == null) {
            throw new ConsultaException("Consulta não encontrada.");
        }
        
        String statusAtual = consulta.getStatus();
        
        switch (novoStatus.toUpperCase()) {
            case "CONFIRMADA":
                if (!statusAtual.equals("AGENDADA")) {
                    throw new ConsultaException("Só é possível confirmar consultas agendadas.");
                }
                break;
                
            case "CANCELADA":
                if (statusAtual.equals("CONCLUIDA")) {
                    throw new ConsultaException("Não é possível cancelar consultas concluídas.");
                }
                break;
                
            case "CONCLUIDA":
                if (!statusAtual.equals("CONFIRMADA")) {
                    throw new ConsultaException("Só é possível concluir consultas confirmadas.");
                }
                break;
                
            default:
                throw new ConsultaException("Status inválido.");
        }
    }
    
    public void validaExclusao(Consulta consulta) {
        if (consulta == null) {
            throw new ConsultaException("Consulta não encontrada.");
        }
        
        if (consulta.isConcluida()) {
            throw new ConsultaException("Não é possível excluir consultas concluídas.");
        }
        
        if (consulta.isConfirmada()) {
            throw new ConsultaException("Não é possível excluir consultas confirmadas. Cancele primeiro.");
        }
    }
}
