package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewAgenda;
import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.dao.AgendaDAO;
import com.ifcolab.estetify.model.exceptions.AgendaException;
import com.ifcolab.estetify.model.valid.ValidateAgenda;
import javax.swing.JTable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.HorarioDisponivel;
import java.time.LocalDateTime;

public class AgendaController {
    
    private AgendaDAO repositorio;
    private ValidateAgenda validador;
    
    public AgendaController() {
        repositorio = new AgendaDAO();
        validador = new ValidateAgenda();
    }
    
    public void cadastrar(Medico medico, String data, Set<LocalTime> horariosDisponiveis) {
        LocalDate dataAgenda = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        
        Agenda agenda = validador.validaCamposEntrada(
            medico,
            dataAgenda,
            horariosDisponiveis
        );
        
        repositorio.save(agenda);
    }
    
    public void cadastrarComHorariosDefault(Medico medico, String data) {
        LocalDate dataAgenda = LocalDate.parse(data, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Agenda agenda = new Agenda(medico, dataAgenda);
        repositorio.save(agenda);
        
        // Adiciona horários default
        LocalTime horarioInicial = LocalTime.of(8, 0);
        LocalTime horarioFinal = LocalTime.of(18, 0);
        
        LocalTime horarioAtual = horarioInicial;
        while (!horarioAtual.isAfter(horarioFinal)) {
            liberarHorario(agenda, horarioAtual);
            horarioAtual = horarioAtual.plusMinutes(30);
        }
    }
    
    public void excluir(Agenda agenda) {
        if (agenda != null) {
            repositorio.delete(agenda.getId());
        } else {
            throw new AgendaException("Erro - Agenda inexistente.");
        }
    }
    
    public void ocuparHorario(Agenda agenda, LocalTime horario) {
        List<HorarioDisponivel> horarios = repositorio.buscarHorarios(agenda);
        horarios.stream()
            .filter(h -> h.getHorario().equals(horario))
            .findFirst()
            .ifPresent(h -> repositorio.removerHorario(h));
    }
    
    public void liberarHorario(Agenda agenda, LocalTime horario) {
        List<HorarioDisponivel> horarios = repositorio.buscarHorarios(agenda);
        boolean horarioJaExiste = horarios.stream()
            .anyMatch(h -> h.getHorario().equals(horario));
            
        if (!horarioJaExiste) {
            HorarioDisponivel novoHorario = new HorarioDisponivel(horario, agenda);
            repositorio.salvarHorario(novoHorario);
        }
    }
    
    public Agenda buscarPorMedicoEData(Medico medico, LocalDate data) {
        return repositorio.findByMedicoAndData(medico, data);
    }
    
    public List<Agenda> buscarPorMedico(Medico medico) {
        return repositorio.findByMedico(medico);
    }
    
    public List<Agenda> buscarPorData(LocalDate data) {
        return repositorio.findByData(data);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewAgenda tmAgenda = new TMViewAgenda(repositorio.findAll());
        grd.setModel(tmAgenda);
    }
    
    public void filtrarTabelaPorData(JTable grd, LocalDate data) {
        TMViewAgenda tmAgenda = new TMViewAgenda(repositorio.findByData(data));
        grd.setModel(tmAgenda);
    }
    
    public void filtrarTabelaPorMedico(JTable grd, Medico medico) {
        TMViewAgenda tmAgenda = new TMViewAgenda(repositorio.findByMedico(medico));
        grd.setModel(tmAgenda);
    }
    
    public List<Agenda> findAll() {
        return repositorio.findAll();
    }
    
    public List<LocalTime> getHorariosDisponiveis(Agenda agenda) {
        return repositorio.buscarHorarios(agenda).stream()
            .map(HorarioDisponivel::getHorario)
            .collect(Collectors.toList());
    }
    
    public void cadastrarConsulta(
            Agenda agenda,
            LocalTime horario,
            Paciente paciente,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos,
            String observacoes
    ) {
        LocalDateTime dataHora = LocalDateTime.of(agenda.getData(), horario);
        
        ConsultaController consultaController = new ConsultaController();
        consultaController.cadastrar(
            dataHora,
            observacoes,
            paciente,
            enfermeira,
            procedimentos,
            agenda
        );
        
        ocuparHorario(agenda, horario);
    }
    
    public List<Consulta> listarConsultasDoDia(Agenda agenda) {
        return repositorio.buscarConsultas(agenda);
    }
}