package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.controller.tablemodel.TMViewMinhasConsultas;
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
import javax.swing.JTable;

public class ConsultaController {
    
    private final ConsultaDAO repositorio;
    private final ValidateConsulta validador;
    private final ConfiguracaoSistemaDAO configDAO;
    
    public ConsultaController() {
        this.repositorio = new ConsultaDAO();
        this.validador = new ValidateConsulta();
        this.configDAO = new ConfiguracaoSistemaDAO();
    }
    
    public ConfiguracaoSistema getConfiguracao() {
        ConfiguracaoSistema config = configDAO.getConfiguracao();
        if (config == null) {
            throw new ConsultaException("Erro ao carregar configurações do sistema");
        }
        return config;
    }
    
    public LocalDateTime calcularProximoHorarioDisponivel() {
        ConfiguracaoSistema config = getConfiguracao();
        LocalDateTime agora = LocalDateTime.now();
        LocalDateTime proximaData = agora;

        while (!config.isDiaFuncionamento(proximaData.getDayOfWeek())) {
            proximaData = proximaData.plusDays(1);
        }

        LocalTime horario;
        if (proximaData.toLocalDate().equals(agora.toLocalDate())) {
            horario = agora.toLocalTime();

            if (horario.isBefore(config.getHorarioAbertura())) {
                horario = config.getHorarioAbertura();
            } else if (horario.isAfter(config.getHorarioFechamento())) {
                proximaData = proximaData.plusDays(1);
                while (!config.isDiaFuncionamento(proximaData.getDayOfWeek())) {
                    proximaData = proximaData.plusDays(1);
                }
                horario = config.getHorarioAbertura();
            } else {
                horario = config.proximoHorarioDisponivel(horario);

                if (horario.isAfter(config.getHorarioFechamento())) {
                    proximaData = proximaData.plusDays(1);
                    while (!config.isDiaFuncionamento(proximaData.getDayOfWeek())) {
                        proximaData = proximaData.plusDays(1);
                    }
                    horario = config.getHorarioAbertura();
                }
            }
        } else {
            horario = config.getHorarioAbertura();
        }

        return proximaData.with(horario);
    }
    
    public void cadastrar(LocalDateTime dataHora, String observacoes, Paciente paciente, Medico medico, Enfermeira enfermeira, List<Procedimento> procedimentos) {
        validador.validaCamposEntrada(dataHora, observacoes, paciente, medico, enfermeira, procedimentos);
        
        Consulta consulta = new Consulta(
            0,
            dataHora,
            observacoes,
            StatusConsulta.AGENDADA,
            enfermeira,
            medico,
            paciente,
            procedimentos
        );
        
        repositorio.save(consulta);
    }
    
    public void atualizar(int id, LocalDateTime dataHora, String observacoes, Paciente paciente, Medico medico, Enfermeira enfermeira, List<Procedimento> procedimentos) {
        validador.validaCamposEntrada(id, dataHora, observacoes, paciente, medico, enfermeira, procedimentos);
        
        Consulta consulta = new Consulta(id, dataHora, observacoes, StatusConsulta.AGENDADA, enfermeira, medico, paciente, procedimentos);
        
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
    
}
