package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.model.Agenda;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.dao.AgendaDAO;
import com.ifcolab.estetify.model.exceptions.AgendaException;
import com.ifcolab.estetify.view.FrNovaConsulta;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JTable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class AgendaController {
    
    private AgendaDAO repositorio;
    private ConsultaController consultaController;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    
    public AgendaController() {
        repositorio = new AgendaDAO();
        consultaController = new ConsultaController();
    }
    
    public void atualizarTabelaPorData(JTable grd, LocalDate data) {
        if (data == null) {
            throw new AgendaException("Data não selecionada.");
        }
        
        List<Consulta> consultas = repositorio.findConsultasByData(data);
        TMViewConsulta tmConsulta = new TMViewConsulta(consultas);
        grd.setModel(tmConsulta);
    }
    
    public void atualizarTabelaPorPeriodo(JTable grd, LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new AgendaException("Período não selecionado corretamente.");
        }
        
        if (dataInicio.isAfter(dataFim)) {
            throw new AgendaException("Data inicial não pode ser posterior à data final.");
        }
        
        List<Consulta> consultas = repositorio.findConsultasByPeriodo(dataInicio, dataFim);
        TMViewConsulta tmConsulta = new TMViewConsulta(consultas);
        grd.setModel(tmConsulta);
    }
    
    public void filtrarConsultasPorProfissional(JTable grd, int profissionalId, boolean isMedico, LocalDate data) {
        if (profissionalId <= 0) {
            throw new AgendaException("Profissional não selecionado.");
        }
        
        if (data == null) {
            throw new AgendaException("Data não selecionada.");
        }
        
        List<Consulta> consultas = repositorio.findConsultasPorProfissionalEData(profissionalId, isMedico, data);
        TMViewConsulta tmConsulta = new TMViewConsulta(consultas);
        grd.setModel(tmConsulta);
    }
    
    public void preencherComboHorariosDisponiveis(JComboBox<String> comboBox, LocalDate data, int medicoId, int enfermeiraId) {
        if (data == null) {
            throw new AgendaException("Data não selecionada.");
        }
        
        if (medicoId <= 0 || enfermeiraId <= 0) {
            throw new AgendaException("Profissionais não selecionados corretamente.");
        }
        
        List<LocalDateTime> horariosDisponiveis = repositorio.getHorariosDisponiveis(data, medicoId, enfermeiraId);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        
        // Adiciona horários formatados ao ComboBox
        horariosDisponiveis.stream()
            .map(horario -> horario.format(TIME_FORMATTER))
            .forEach(model::addElement);
        
        comboBox.setModel(model);
    }
    
    public List<Consulta> getConsultasAgendadasPorData(LocalDate data) {
        if (data == null) {
            throw new AgendaException("Data não selecionada.");
        }
        return repositorio.findConsultasAgendadasPorData(data);
    }
    
    public String formatarDataParaExibicao(LocalDate data) {
        return data != null ? data.format(DATE_FORMATTER) : "";
    }
    
    public String formatarHorarioParaExibicao(LocalDateTime horario) {
        return horario != null ? horario.format(TIME_FORMATTER) : "";
    }
    
    public LocalDate parseData(String dataStr) {
        try {
            return LocalDate.parse(dataStr, DATE_FORMATTER);
        } catch (Exception e) {
            throw new AgendaException("Formato de data inválido. Use dd/MM/yyyy");
        }
    }
    
    public void novaConsulta() {
        // Abre o formulário de nova consulta
        FrNovaConsulta frm = new FrNovaConsulta(null, true);
        frm.setVisible(true);
    }
}