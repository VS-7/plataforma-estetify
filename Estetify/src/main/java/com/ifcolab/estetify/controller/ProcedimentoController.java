package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewProcedimento;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.dao.ProcedimentoDAO;
import com.ifcolab.estetify.model.exceptions.ProcedimentoException;
import com.ifcolab.estetify.model.valid.ValidateProcedimento;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class ProcedimentoController {
    
    private ProcedimentoDAO repositorio;
    
    public ProcedimentoController() {
        repositorio = new ProcedimentoDAO();
    }
    
    public void cadastrar(
            String descricao,
            String duracao,
            String valor,
            String requisitos,
            String contraindicacoes
    ) {
        ValidateProcedimento valid = new ValidateProcedimento();
        Procedimento procedimento = valid.validaCamposEntrada(
                descricao,
                duracao,
                valor,
                requisitos,
                contraindicacoes
        );
        
        repositorio.save(procedimento);
    }
    
    public void atualizar(
            int id,
            String descricao,
            String duracao,
            String valor,
            String requisitos,
            String contraindicacoes
    ) {
        ValidateProcedimento valid = new ValidateProcedimento();
        Procedimento procedimento = valid.validaCamposEntrada(
                descricao,
                duracao,
                valor,
                requisitos,
                contraindicacoes
        );
        
        procedimento.setId(id);
        repositorio.update(procedimento);
    }
    
    public void excluir(Procedimento procedimento) {
        if (procedimento != null) {
            repositorio.delete(procedimento.getId());
        } else {
            throw new ProcedimentoException("Erro - Procedimento inexistente.");
        }
    }
    
    public List<Procedimento> findAll() {
        return repositorio.findAll();
    }
    
    
    public void atualizarTabela(JTable grd) {
        TMViewProcedimento tmProcedimento = new TMViewProcedimento(repositorio.findAll());
        grd.setModel(tmProcedimento);
    }
    
    public void filtrarTabela(JTable grd, String descricao) {
        TMViewProcedimento tmProcedimento = new TMViewProcedimento(repositorio.filterByDescricao(descricao));
        grd.setModel(tmProcedimento);
    }
    
    public void filtrarTabelaPorPaciente(JTable grd, int pacienteId) {
        TMViewProcedimento tmProcedimento = new TMViewProcedimento(repositorio.findByPaciente(pacienteId));
        grd.setModel(tmProcedimento);
    }
    
    public void filtrarTabelaPorEquipe(JTable grd, int medicoId, int enfermeiraId) {
        TMViewProcedimento tmProcedimento = new TMViewProcedimento(repositorio.findByEquipe(medicoId, enfermeiraId));
        grd.setModel(tmProcedimento);
    }
    
    public void filtrarTabelaPorPeriodo(JTable grd, LocalDateTime inicio, LocalDateTime fim) {
        TMViewProcedimento tmProcedimento = new TMViewProcedimento(repositorio.findByPeriodo(inicio, fim));
        grd.setModel(tmProcedimento);
    }
}
