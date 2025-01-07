package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class ValidateConsulta {
    
    private final ConsultaDAO repositorio;
    
    public ValidateConsulta() {
        this.repositorio = new ConsultaDAO();
    }
    
    private void validarHorariosConflitantes(LocalDateTime dataHora, Medico medico, Enfermeira enfermeira, Paciente paciente, Integer idConsultaAtual) {
        ConfiguracaoSistemaDAO configDAO = new ConfiguracaoSistemaDAO();
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        int duracaoConsulta = config.getIntervaloConsultaMinutos();
        
        // Define o período da consulta
        LocalDateTime fimConsulta = dataHora.plusMinutes(duracaoConsulta);
        
        List<Consulta> consultasConflitantes = repositorio.buscarConsultasNoPeriodo(
            dataHora, 
            fimConsulta
        );
        
        if (idConsultaAtual != null) {
            consultasConflitantes.removeIf(c -> c.getId() == idConsultaAtual);
        }
        
        if (consultasConflitantes.stream().anyMatch(c -> c.getMedico().equals(medico))) {
            throw new ConsultaException("Médico já possui consulta agendada neste horário");
        }
        
        if (consultasConflitantes.stream().anyMatch(c -> c.getEnfermeira().equals(enfermeira))) {
            throw new ConsultaException("Enfermeira já possui consulta agendada neste horário");
        }

        if (consultasConflitantes.stream().anyMatch(c -> c.getPaciente().equals(paciente))) {
            throw new ConsultaException("Paciente já possui consulta agendada neste horário");
        }
    }
    
    public void validaCamposEntrada(LocalDateTime dataHora, String observacoes, Paciente paciente, Medico medico, Enfermeira enfermeira, List<Procedimento> procedimentos) {
        
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
        
        validarHorariosConflitantes(dataHora, medico, enfermeira, paciente, null);
    }
    
    public void validaCamposEntrada(int id, LocalDateTime dataHora, String observacoes, Paciente paciente, Medico medico, Enfermeira enfermeira, List<Procedimento> procedimentos) {
        validaCamposEntrada(dataHora, observacoes, paciente, medico, enfermeira, procedimentos);
        
        validarHorariosConflitantes(dataHora, medico, enfermeira, paciente, id);
    }
}
