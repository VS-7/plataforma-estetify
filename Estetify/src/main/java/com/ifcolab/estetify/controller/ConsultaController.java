package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.valid.ValidateConsulta;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class ConsultaController {
    
    private ConsultaDAO repositorio;
    private ValidateConsulta validador;
    
    public ConsultaController() {
        repositorio = new ConsultaDAO();
        validador = new ValidateConsulta();
    }
    
    public void cadastrar(
            String dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        Consulta consulta = validador.validaCamposEntrada(
                dataHora,
                observacoes,
                paciente,
                medico,
                enfermeira,
                procedimentos
        );
        
        repositorio.save(consulta);
    }
    
    public void atualizar(
            int id,
            String dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        Consulta consulta = validador.validaCamposEntrada(
                dataHora,
                observacoes,
                paciente,
                medico,
                enfermeira,
                procedimentos
        );
        
        consulta.setId(id);
        repositorio.update(consulta);
    }
    
    public void alterarStatus(Consulta consulta, String novoStatus) {
        if (consulta == null) {
            throw new ConsultaException("Consulta não encontrada.");
        }
        
        validador.validaAlteracaoStatus(consulta, novoStatus);
        consulta.setStatus(novoStatus.toUpperCase());
        repositorio.update(consulta);
    }
    
    public void excluir(Consulta consulta) {
        if (consulta == null) {
            throw new ConsultaException("Consulta não encontrada.");
        }
        
        validador.validaExclusao(consulta);
        repositorio.delete(consulta.getId());
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findAll());
        grd.setModel(tmConsulta);
    }
    
    public void filtrarTabelaPorPaciente(JTable grd, int pacienteId) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findByPaciente(pacienteId));
        grd.setModel(tmConsulta);
    }
    
    public void filtrarTabelaPorMedico(JTable grd, int medicoId) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findByMedico(medicoId));
        grd.setModel(tmConsulta);
    }
    
    public void filtrarTabelaPorEnfermeira(JTable grd, int enfermeiraId) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findByEnfermeira(enfermeiraId));
        grd.setModel(tmConsulta);
    }
    
    public void filtrarTabelaPorPeriodo(JTable grd, LocalDateTime inicio, LocalDateTime fim) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findByPeriodo(inicio, fim));
        grd.setModel(tmConsulta);
    }
    
    public void filtrarTabelaPorStatus(JTable grd, String status) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findByStatus(status));
        grd.setModel(tmConsulta);
    }
    
    public void filtrarTabelaPorProcedimento(JTable grd, int procedimentoId) {
        TMViewConsulta tmConsulta = new TMViewConsulta(repositorio.findByProcedimento(procedimentoId));
        grd.setModel(tmConsulta);
    }
    
    public boolean verificarDisponibilidade(String dataHora, int medicoId, int enfermeiraId, int consultaId) {
        try {
            LocalDateTime dataHoraObj = LocalDateTime.parse(dataHora, 
                java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            return repositorio.verificarDisponibilidade(dataHoraObj, medicoId, enfermeiraId, consultaId);
        } catch (Exception e) {
            throw new ConsultaException("Data e hora inválidas.");
        }
    }

    // Manter o método original para novos agendamentos
    public boolean verificarDisponibilidade(String dataHora, int medicoId, int enfermeiraId) {
        return verificarDisponibilidade(dataHora, medicoId, enfermeiraId, -1);
    }
}
