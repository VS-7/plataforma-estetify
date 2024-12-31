package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.time.LocalDateTime;
import java.util.List;

public class ValidateConsulta {
    
    public Consulta validaCamposEntrada(
            LocalDateTime dataHora,
            String observacoes,
            Enfermeira enfermeira,
            Medico medico,
            Paciente paciente,
            List<Procedimento> procedimentos
    ) {
        if (dataHora == null) {
            throw new ConsultaException("Data e hora não podem estar em branco.");
        }
        
        if (dataHora.isBefore(LocalDateTime.now())) {
            throw new ConsultaException("Data e hora não podem ser anteriores ao momento atual.");
        }
        
        if (observacoes != null && observacoes.length() > 500) {
            throw new ConsultaException("Observações não podem ter mais que 500 caracteres.");
        }
        
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
