package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.HorarioDisponivel;
import com.ifcolab.estetify.model.dao.AgendaDAO;
import com.ifcolab.estetify.model.exceptions.AgendaException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

public class ValidateAgenda {
    
    private AgendaDAO repositorio;
    
    public ValidateAgenda() {
        repositorio = new AgendaDAO();
    }
    
    public Agenda validaCamposEntrada(
            Medico medico,
            LocalDate data,
            Set<LocalTime> horariosDisponiveis
    ) {
        if (medico == null) {
            throw new AgendaException("Médico não pode estar em branco.");
        }
        
        if (data == null) {
            throw new AgendaException("Data não pode estar em branco.");
        }
        
        validaData(data);
        
        if (horariosDisponiveis == null || horariosDisponiveis.isEmpty()) {
            throw new AgendaException("Horários disponíveis não podem estar vazios.");
        }
        
        validaHorarios(horariosDisponiveis);
        
        // Verifica se já existe uma agenda para este médico nesta data
        Agenda agendaExistente = repositorio.findByMedicoAndData(medico, data);
        if (agendaExistente != null) {
            throw new AgendaException("Já existe uma agenda cadastrada para este médico nesta data.");
        }
        
        // Cria a agenda básica
        Agenda agenda = new Agenda(medico, data);
        
        // Salva a agenda e seus horários
        repositorio.save(agenda);
        horariosDisponiveis.forEach(horario -> {
            HorarioDisponivel disponivel = new HorarioDisponivel(horario, agenda);
            repositorio.salvarHorario(disponivel);
        });
        
        return agenda;
    }
    
    private void validaData(LocalDate data) {
        LocalDate hoje = LocalDate.now();
        
        if (data.isBefore(hoje)) {
            throw new AgendaException("Não é possível criar agenda para datas passadas.");
        }
        
        // Limite máximo de 6 meses para agendamento futuro
        if (data.isAfter(hoje.plusMonths(6))) {
            throw new AgendaException("Não é possível criar agenda com mais de 6 meses de antecedência.");
        }
    }
    
    private void validaHorarios(Set<LocalTime> horarios) {
        LocalTime inicioExpediente = LocalTime.of(8, 0); // 8:00
        LocalTime fimExpediente = LocalTime.of(18, 0);   // 18:00
        
        for (LocalTime horario : horarios) {
            if (horario == null) {
                throw new AgendaException("Horário inválido encontrado.");
            }
            
            if (horario.isBefore(inicioExpediente) || horario.isAfter(fimExpediente)) {
                throw new AgendaException("Horário fora do expediente (8:00 - 18:00): " + horario);
            }
            
            // Verifica se os minutos são múltiplos de 30 (consultas de 30 em 30 minutos)
            if (horario.getMinute() % 30 != 0) {
                throw new AgendaException("Os horários devem ser marcados em intervalos de 30 minutos.");
            }
        }
    }
    
    public void validaHorarioDisponivel(Agenda agenda, LocalTime horario) {
        List<HorarioDisponivel> horarios = repositorio.buscarHorarios(agenda);
        boolean disponivel = horarios.stream()
            .anyMatch(h -> h.getHorario().equals(horario));
            
        if (!disponivel) {
            throw new AgendaException("Horário não disponível: " + horario);
        }
    }
    
    public void validaHorarioOcupado(Agenda agenda, LocalTime horario) {
        List<HorarioDisponivel> horarios = repositorio.buscarHorarios(agenda);
        boolean disponivel = horarios.stream()
            .anyMatch(h -> h.getHorario().equals(horario));
            
        if (disponivel) {
            throw new AgendaException("Horário não está ocupado: " + horario);
        }
    }
}