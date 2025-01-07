package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.time.LocalDateTime;
import java.util.List;

public class ValidateConsulta {
    
    public void validaCamposEntrada(LocalDateTime dataHora, String observacoes, 
            Paciente paciente, Medico medico, Enfermeira enfermeira, 
            List<Procedimento> procedimentos) {
        
        if (dataHora == null) {
            throw new ConsultaException("Data e hora não podem estar em branco");
        }
        
        if (paciente == null) {
            throw new ConsultaException("Paciente não selecionado");
        }
        
        if (medico == null) {
            throw new ConsultaException("Médico não selecionado");
        }
        
        if (enfermeira == null) {
            throw new ConsultaException("Enfermeira não selecionada");
        }
        
        if (procedimentos == null || procedimentos.isEmpty()) {
            throw new ConsultaException("Selecione pelo menos um procedimento");
        }
        
        if (observacoes != null && observacoes.length() > 1000) {
            throw new ConsultaException("Observações muito longas. Máximo de 1000 caracteres.");
        }
    }
    
    public void validarHorarioConflitante(LocalDateTime dataHoraConsulta, 
            LocalDateTime dataHoraExistente, int duracaoMinutos) {
            
        LocalDateTime fimConsultaExistente = dataHoraExistente.plusMinutes(duracaoMinutos);
        
        if ((dataHoraConsulta.isEqual(dataHoraExistente) || 
             dataHoraConsulta.isAfter(dataHoraExistente)) && 
            dataHoraConsulta.isBefore(fimConsultaExistente)) {
            throw new ConsultaException("Existe conflito de horário com outra consulta");
        }
    }
    
    public void validarHorarioFuncionamento(LocalDateTime dataHora, 
            LocalDateTime horarioAbertura, LocalDateTime horarioFechamento) {
            
        if (dataHora.isBefore(horarioAbertura) || dataHora.isAfter(horarioFechamento)) {
            throw new ConsultaException("Horário fora do período de funcionamento");
        }
    }
    
    public void validarAntecedencia(LocalDateTime dataHora, 
            int tempoMinimoAntecedenciaMinutos, int tempoMaximoAgendamentoDias) {
            
        LocalDateTime agora = LocalDateTime.now();
        long minutosAteConsulta = java.time.Duration.between(agora, dataHora).toMinutes();
        long diasAteConsulta = java.time.Duration.between(agora, dataHora).toDays();
        
        if (minutosAteConsulta < tempoMinimoAntecedenciaMinutos) {
            throw new ConsultaException("É necessário agendar com no mínimo " + 
                tempoMinimoAntecedenciaMinutos + " minutos de antecedência");
        }
        
        if (diasAteConsulta > tempoMaximoAgendamentoDias) {
            throw new ConsultaException("Não é possível agendar consultas com mais de " + 
                tempoMaximoAgendamentoDias + " dias de antecedência");
        }
    }
}
