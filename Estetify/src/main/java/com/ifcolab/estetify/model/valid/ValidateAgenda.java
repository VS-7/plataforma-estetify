package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.dao.AgendaDAO;
import com.ifcolab.estetify.model.exceptions.AgendaException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class ValidateAgenda {
    
    private AgendaDAO repositorio;
    
    public ValidateAgenda() {
        repositorio = new AgendaDAO();
    }
    
    public void validaData(LocalDate data) {
        if (data == null) {
            throw new AgendaException("Data não pode estar em branco.");
        }
        
        if (data.isBefore(LocalDate.now())) {
            throw new AgendaException("Não é possível visualizar agenda de datas passadas.");
        }
    }
    
    public void validaPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new AgendaException("Datas de início e fim são obrigatórias.");
        }
        
        if (dataInicio.isAfter(dataFim)) {
            throw new AgendaException("Data inicial não pode ser posterior à data final.");
        }
        
        if (dataInicio.isBefore(LocalDate.now())) {
            throw new AgendaException("Período não pode incluir datas passadas.");
        }
    }
    
    public void validaProfissional(Medico medico, Enfermeira enfermeira) {
        if (medico == null && enfermeira == null) {
            throw new AgendaException("Selecione pelo menos um profissional.");
        }
    }
    
    public void validaHorarioDisponivel(LocalDateTime horario, int medicoId, int enfermeiraId) {
        if (horario == null) {
            throw new AgendaException("Horário não pode estar em branco.");
        }
        
        // Verifica se está dentro do horário de funcionamento (8h às 18h)
        int hora = horario.getHour();
        if (hora < 8 || hora >= 18) {
            throw new AgendaException("Horário fora do período de atendimento (8h às 18h).");
        }
        
        // Verifica se o horário já está ocupado
        List<LocalDateTime> horariosDisponiveis = 
            repositorio.getHorariosDisponiveis(horario.toLocalDate(), medicoId, enfermeiraId);
            
        if (!horariosDisponiveis.contains(horario)) {
            throw new AgendaException("Horário não disponível para os profissionais selecionados.");
        }
    }
    
    public void validaQuantidadeConsultasDia(LocalDate data, List<Consulta> consultasDoDia) {
        // Limite máximo de consultas por dia (exemplo: 10 consultas)
        final int LIMITE_CONSULTAS_DIA = 10;
        
        if (consultasDoDia.size() >= LIMITE_CONSULTAS_DIA) {
            throw new AgendaException("Limite máximo de consultas para este dia já foi atingido.");
        }
    }
    
    public void validaConsultasSimultaneas(List<Consulta> consultasHorario) {
        // Limite de consultas simultâneas (exemplo: 2 consultas no mesmo horário)
        final int LIMITE_CONSULTAS_SIMULTANEAS = 2;
        
        if (consultasHorario.size() >= LIMITE_CONSULTAS_SIMULTANEAS) {
            throw new AgendaException("Não é possível agendar mais consultas neste horário.");
        }
    }
    
    public LocalDateTime validaFormatoDataHora(String dataHora) {
        if (dataHora == null || dataHora.isEmpty()) {
            throw new AgendaException("Data e hora não podem estar em branco.");
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            return LocalDateTime.parse(dataHora, formatter);
        } catch (DateTimeParseException e) {
            throw new AgendaException("Data e hora inválidas. Use o formato dd/MM/yyyy HH:mm");
        }
    }
}
