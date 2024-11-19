package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.dao.AgendaDAO;
import com.ifcolab.estetify.model.exceptions.AgendaException;
import com.ifcolab.estetify.model.valid.ValidateAgenda;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class AgendaController {
    
    private AgendaDAO repositorio;
    private ValidateAgenda validator;
    
    public AgendaController() {
        repositorio = new AgendaDAO();
        validator = new ValidateAgenda();
    }
    
    public void atualizarTabelaPorData(JTable grd, LocalDate data) {
        validator.validaData(data);
        List<Consulta> consultas = repositorio.findConsultasByData(data);
        TMViewConsulta tmConsulta = new TMViewConsulta(consultas);
        grd.setModel(tmConsulta);
    }
    
    public void filtrarPorPeriodo(JTable grd, LocalDate dataInicio, LocalDate dataFim) {
        validator.validaPeriodo(dataInicio, dataFim);
        List<Consulta> consultas = repositorio.findConsultasByPeriodo(dataInicio, dataFim);
        TMViewConsulta tmConsulta = new TMViewConsulta(consultas);
        grd.setModel(tmConsulta);
    }
    
    public void filtrarPorProfissional(JTable grd, Medico medico, Enfermeira enfermeira, LocalDate data) {
        validator.validaProfissional(medico, enfermeira);
        validator.validaData(data);
        
        List<Consulta> consultas;
        if (medico != null) {
            consultas = repositorio.findConsultasPorProfissionalEData(medico.getId(), true, data);
        } else {
            consultas = repositorio.findConsultasPorProfissionalEData(enfermeira.getId(), false, data);
        }
        
        TMViewConsulta tmConsulta = new TMViewConsulta(consultas);
        grd.setModel(tmConsulta);
    }
    
    public List<LocalDateTime> getHorariosDisponiveis(LocalDate data, int medicoId, int enfermeiraId) {
        validator.validaData(data);
        return repositorio.getHorariosDisponiveis(data, medicoId, enfermeiraId);
    }
    
    public void verificarDisponibilidadeHorario(LocalDateTime horario, int medicoId, int enfermeiraId) {
        validator.validaHorarioDisponivel(horario, medicoId, enfermeiraId);
    }
    
    public void verificarLimiteConsultasDia(LocalDate data) {
        List<Consulta> consultasDoDia = repositorio.findConsultasByData(data);
        validator.validaQuantidadeConsultasDia(data, consultasDoDia);
    }
    
    public void verificarConsultasSimultaneas(LocalDateTime horario) {
        List<Consulta> consultasHorario = repositorio.findConsultasByData(horario.toLocalDate())
            .stream()
            .filter(c -> c.getDataHora().equals(horario))
            .toList();
            
        validator.validaConsultasSimultaneas(consultasHorario);
    }
    
    public LocalDateTime validarFormatoDataHora(String dataHora) {
        return validator.validaFormatoDataHora(dataHora);
    }
    
    public List<Consulta> getConsultasAgendadasPorData(LocalDate data) {
        validator.validaData(data);
        return repositorio.findConsultasAgendadasPorData(data);
    }
    
    public void excluir(Agenda agenda) {
        if (agenda != null) {
            repositorio.delete(agenda.getId());
        } else {
            throw new AgendaException("Erro - Agenda inexistente.");
        }
    }
    
    public List<Agenda> findAll() {
        return repositorio.findAll();
    }
}