package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewConsulta;
import com.ifcolab.estetify.controller.tablemodel.TMViewEnfermeira;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.exceptions.ConsultaException;
import com.ifcolab.estetify.model.valid.ValidateConsulta;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class ConsultaController {
    
    private final ConsultaDAO repositorio;
    private final ValidateConsulta validador;
    
    public ConsultaController() {
        this.repositorio = new ConsultaDAO();
        this.validador = new ValidateConsulta();
    }
    
    public void cadastrar(
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        // Valida os campos incluindo verificação de conflitos
        validador.validaCamposEntrada(
            dataHora,
            observacoes,
            paciente,
            medico,
            enfermeira,
            procedimentos
        );
        
        // Cria e salva a consulta
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
    
    public void atualizar(
            int id,
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        // Valida os campos incluindo verificação de conflitos, considerando o ID da consulta atual
        validador.validaCamposEntrada(
            id,
            dataHora,
            observacoes,
            paciente,
            medico,
            enfermeira,
            procedimentos
        );
        
        // Atualiza a consulta
        Consulta consulta = new Consulta(
            id,
            dataHora,
            observacoes,
            StatusConsulta.AGENDADA,
            enfermeira,
            medico,
            paciente,
            procedimentos
        );
        
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
}
