package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ValidateConsulta {
    
    public Consulta validaCamposEntrada(
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos,
            Agenda agenda
    ) {
        if (dataHora == null) {
            throw new ConsultaException("Data/hora não pode estar em branco.");
        }
        
        if (paciente == null) {
            throw new ConsultaException("Paciente não pode estar em branco.");
        }
        
        if (enfermeira == null) {
            throw new ConsultaException("Enfermeira não pode estar em branco.");
        }
        
        if (procedimentos == null || procedimentos.isEmpty()) {
            throw new ConsultaException("Selecione pelo menos um procedimento.");
        }
        
        if (agenda == null) {
            throw new ConsultaException("Agenda não pode estar em branco.");
        }
        
        // Validar se o horário está dentro do expediente
        LocalTime horario = dataHora.toLocalTime();
        if (horario.isBefore(LocalTime.of(8, 0)) || horario.isAfter(LocalTime.of(18, 0))) {
            throw new ConsultaException("Horário fora do expediente (8:00 - 18:00)");
        }
        
        return new Consulta(
            dataHora,
            observacoes,
            paciente,
            agenda.getMedico(),
            enfermeira,
            procedimentos,
            agenda
        );
    }
}
