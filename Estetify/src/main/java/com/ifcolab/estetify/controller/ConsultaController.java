package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.controller.tablemodel.TMViewMinhasConsultas;
import com.ifcolab.estetify.controller.tablemodel.TMViewHistoricoProcedimento;
import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.valid.ValidateConsulta;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import javax.swing.JTable;

public class ConsultaController {
    
    private final ConsultaDAO repositorio;
    private final ConfiguracaoSistemaDAO configDAO;
    private final ValidateConsulta validador;
    
    public ConsultaController() {
        this.repositorio = new ConsultaDAO();
        this.configDAO = new ConfiguracaoSistemaDAO();
        this.validador = new ValidateConsulta();
    }
    
    public ConfiguracaoSistema getConfiguracao() {
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        if (config == null) {
            throw new ConsultaException("Erro ao carregar configurações do sistema");
        }
        return config;
    }
    
    public void cadastrar(LocalDateTime dataHora, String observacoes, 
            Paciente paciente, Medico medico, Enfermeira enfermeira, 
            List<Procedimento> procedimentos) {
            
        validador.validaCamposEntrada(dataHora, observacoes, paciente, medico, 
            enfermeira, procedimentos);
            
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        
        validador.validarHorarioFuncionamento(dataHora, 
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioAbertura()),
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioFechamento()));
            
        validador.validarAntecedencia(dataHora, 
            config.getTempoMinimoAntecedenciaMinutos(),
            config.getTempoMaximoAgendamentoDias());
            
        List<Consulta> consultasExistentes = repositorio.buscarConsultasNoPeriodo(
            dataHora, dataHora.plusMinutes(config.getIntervaloConsultaMinutos()));
            
        for (Consulta consulta : consultasExistentes) {
            validador.validarHorarioConflitante(dataHora, 
                consulta.getDataHora(), 
                config.getIntervaloConsultaMinutos());
        }
        
        Consulta consulta = new Consulta(0, dataHora, observacoes, 
            StatusConsulta.AGENDADA, enfermeira, medico, paciente, procedimentos);
            
        repositorio.save(consulta);
    }
    
    public void atualizar(int id, LocalDateTime dataHora, String observacoes, 
            Paciente paciente, Medico medico, Enfermeira enfermeira, 
            List<Procedimento> procedimentos) {
            
        
        validador.validaCamposEntrada(dataHora, observacoes, paciente, medico, 
            enfermeira, procedimentos);
            
        
        Consulta consultaAtual = repositorio.find(id);
        if (consultaAtual == null) {
            throw new ConsultaException("Consulta não encontrada");
        }
        
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        
        validador.validarHorarioFuncionamento(dataHora, 
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioAbertura()),
            LocalDateTime.of(dataHora.toLocalDate(), config.getHorarioFechamento()));
            
        validador.validarAntecedencia(dataHora, 
            config.getTempoMinimoAntecedenciaMinutos(),
            config.getTempoMaximoAgendamentoDias());
            
        List<Consulta> consultasExistentes = repositorio.buscarConsultasNoPeriodo(
            dataHora, dataHora.plusMinutes(config.getIntervaloConsultaMinutos()));
            
        for (Consulta consulta : consultasExistentes) {
            if (consulta.getId() != id) {
                validador.validarHorarioConflitante(dataHora, 
                    consulta.getDataHora(), 
                    config.getIntervaloConsultaMinutos());
            }
        }
        
        Consulta consulta = new Consulta(id, dataHora, observacoes, 
            consultaAtual.getStatus(), enfermeira, medico, paciente, procedimentos);
            
        repositorio.update(consulta);
    }
    
    public void excluir(int id) {
        repositorio.delete(id);
    }
    
    public List<Consulta> findAll() {
        return repositorio.findAll();
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findAll());
        grd.setModel(tmConsulta);
    }
    
    public List<Consulta> buscarPorData(LocalDate data) {
        if (data == null) {
            throw new ConsultaException("Data não pode ser nula");
        }
        return repositorio.buscarPorData(data);
    }
    
    public List<Consulta> buscarConsultasPorPaciente(int idPaciente) {
        return repositorio.buscarConsultasPorPaciente(idPaciente);
    }
    
    public void atualizarTabelaMinhasConsultas(JTable grd, int idPaciente) {
        TMViewMinhasConsultas tmMinhasConsultas = new TMViewMinhasConsultas(
            repositorio.buscarConsultasPorPaciente(idPaciente)
        );
        grd.setModel(tmMinhasConsultas);
    }
    
    public void confirmarConsulta(int id) {
        Consulta consulta = repositorio.find(id);
        if (consulta == null) {
            throw new ConsultaException("Consulta não encontrada");
        }
        
        if (!consulta.isAgendada()) {
            throw new ConsultaException("Apenas consultas agendadas podem ser confirmadas");
        }
        
        consulta.setStatus(StatusConsulta.CONFIRMADA);
        repositorio.update(consulta);
    }
    
    public void cancelarConsulta(int id) {
        Consulta consulta = repositorio.find(id);
        if (consulta == null) {
            throw new ConsultaException("Consulta não encontrada");
        }
        
        if (!consulta.isAgendada()) {
            throw new ConsultaException("Apenas consultas agendadas podem ser canceladas");
        }
        
        consulta.setStatus(StatusConsulta.CANCELADA);
        repositorio.update(consulta);
    }
    
    public void realizarConsulta(int id) {
        Consulta consulta = repositorio.find(id);
        if (consulta == null) {
            throw new ConsultaException("Consulta não encontrada");
        }
        
        if (!consulta.isConfirmada()) {
            throw new ConsultaException("Apenas consultas confirmadas podem ser realizadas");
        }
        
        consulta.setStatus(StatusConsulta.CONCLUIDA);
        repositorio.update(consulta);
    }
    
    public void atualizarTabelaHistorico(JTable grd, int idPaciente) {
        List<Consulta> consultas = buscarConsultasPorPaciente(idPaciente);
        TMViewHistoricoProcedimento tmHistorico = new TMViewHistoricoProcedimento(consultas);
        grd.setModel(tmHistorico);
    }
    
    public Map<LocalTime, List<Consulta>> getConsultasOrganizadasPorHorario(LocalDate data) {
        ConfiguracaoSistema config = getConfiguracao();
        List<Consulta> consultas = buscarPorData(data);
        
        Map<LocalTime, List<Consulta>> consultasPorHorario = new TreeMap<>();
        
        LocalTime hora = config.getHorarioAbertura();
        while (!hora.isAfter(config.getHorarioFechamento())) {
            consultasPorHorario.put(hora, new ArrayList<>());
            hora = hora.plusMinutes(config.getIntervaloConsultaMinutos());
        }
        
        for (Consulta consulta : consultas) {
            LocalTime horaConsulta = consulta.getDataHora().toLocalTime();
            consultasPorHorario.computeIfAbsent(horaConsulta, k -> new ArrayList<>())
                              .add(consulta);
        }
        
        return consultasPorHorario;
    }
    
    // Métodos para DlgOpcoesConsulta

    public boolean podeConfirmarConsulta(Consulta consulta) {
        return consulta != null && consulta.isAgendada();
    }

    public boolean podeCancelarConsulta(Consulta consulta) {
        return consulta != null && consulta.isAgendada();
    }

    public boolean podeRealizarConsulta(Consulta consulta) {
        return consulta != null && consulta.isConfirmada();
    }

    public void validarHorarioConsulta(LocalDateTime dataHora) {
        ConfiguracaoSistema config = getConfiguracao();
        
        if (!config.isDiaFuncionamento(dataHora.getDayOfWeek())) {
            throw new ConsultaException("A clínica não funciona neste dia da semana");
        }
        
        LocalTime horario = dataHora.toLocalTime();
        if (horario.isBefore(config.getHorarioAbertura()) || 
            horario.isAfter(config.getHorarioFechamento())) {
            throw new ConsultaException("Horário fora do período de funcionamento");
        }
        
        if (horario.getMinute() % config.getIntervaloConsultaMinutos() != 0) {
            throw new ConsultaException("Horário inválido para agendamento");
        }
        
        LocalDateTime agora = LocalDateTime.now();
        long minutosAteConsulta = java.time.Duration.between(agora, dataHora).toMinutes();
        long diasAteConsulta = java.time.Duration.between(agora, dataHora).toDays();
        
        if (minutosAteConsulta < config.getTempoMinimoAntecedenciaMinutos()) {
            throw new ConsultaException("Antecedência mínima não respeitada");
        }
        
        if (diasAteConsulta > config.getTempoMaximoAgendamentoDias()) {
            throw new ConsultaException("Prazo máximo para agendamento excedido");
        }
    }
}
