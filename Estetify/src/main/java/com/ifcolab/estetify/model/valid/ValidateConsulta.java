package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ValidateConsulta {
    
    private final ConfiguracaoSistema config;
    
    public ValidateConsulta() {
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        this.config = configDAO.getConfiguracao();
    }
    
    public Consulta validaCamposEntrada(
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        // Validação da data e hora
        if (dataHora == null) {
            throw new ConsultaException("Data e hora não podem estar em branco.");
        }
        
        LocalDateTime agora = LocalDateTime.now();
        LocalTime horario = dataHora.toLocalTime();
        
        // Valida se está no passado
        if (dataHora.isBefore(agora)) {
            throw new ConsultaException("Data e hora não podem ser anteriores ao momento atual.");
        }
        
        // Valida antecedência mínima
        if (dataHora.isBefore(agora.plusMinutes(config.getTempoMinimoAntecedenciaMinutos()))) {
            throw new ConsultaException(String.format(
                "A consulta deve ser agendada com pelo menos %d minutos de antecedência.",
                config.getTempoMinimoAntecedenciaMinutos()));
        }
        
        // Valida período máximo de agendamento
        if (dataHora.isAfter(agora.plusDays(config.getTempoMaximoAgendamentoDias()))) {
            throw new ConsultaException(String.format(
                "Não é possível agendar consultas com mais de %d dias de antecedência.",
                config.getTempoMaximoAgendamentoDias()));
        }
        
        // Valida dia de funcionamento
        if (!config.isDiaFuncionamento(dataHora.getDayOfWeek())) {
            throw new ConsultaException("Não é possível agendar consultas neste dia da semana.");
        }
        
        // Valida horário de funcionamento
        if (horario.isBefore(config.getHorarioAbertura()) || 
            horario.isAfter(config.getHorarioFechamento())) {
            throw new ConsultaException(String.format(
                "Horário deve estar entre %s e %s.",
                config.getHorarioAbertura(),
                config.getHorarioFechamento()));
        }
        
        // Valida intervalo de consulta
        long minutos = horario.getHour() * 60L + horario.getMinute();
        if (minutos % config.getIntervaloConsultaMinutos() != 0) {
            throw new ConsultaException(String.format(
                "Os horários devem ser agendados em intervalos de %d minutos.",
                config.getIntervaloConsultaMinutos()));
        }
        
        
        // Outras validações
        if (enfermeira == null) {
            throw new ConsultaException("Enfermeira não pode estar em branco.");
        }
        
        if (medico == null) {
            throw new ConsultaException("Médico não pode estar em branco.");
        }
        
        if (paciente == null) {
            throw new ConsultaException("Paciente não pode estar em branco.");
        }
        
        if (procedimentos == null || procedimentos.isEmpty()) {
            throw new ConsultaException("É necessário selecionar pelo menos um procedimento.");
        }
        
        return new Consulta(0, dataHora, observacoes, StatusConsulta.AGENDADA, 
                          enfermeira, medico, paciente, procedimentos);
    }
}
