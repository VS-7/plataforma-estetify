package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Relatorio;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.dao.RelatorioDAO;
import com.ifcolab.estetify.model.exceptions.RelatorioException;
import com.ifcolab.estetify.model.valid.ValidateRelatorio;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class RelatorioController {
    
    private RelatorioDAO repositorio;
    private ValidateRelatorio validate;
    
    public RelatorioController() {
        repositorio = new RelatorioDAO();
        validate = new ValidateRelatorio();
    }
    
    public void cadastrar(
            String resultado,
            String observacoes,
            String caminhoPdf,
            Consulta consulta) throws RelatorioException {
            
        Relatorio relatorio = validate.validaCamposEntrada(
            LocalDateTime.now(),
            resultado,
            observacoes,
            caminhoPdf,
            consulta
        );
        
        repositorio.save(relatorio);
    }
    
    public void atualizar(
            int id,
            String resultado,
            String observacoes,
            String caminhoPdf,
            Consulta consulta) throws RelatorioException {
            
        Relatorio relatorio = validate.validaCamposEntrada(
            LocalDateTime.now(),
            resultado,
            observacoes,
            caminhoPdf,
            consulta
        );
        
        relatorio.setId(id);
        repositorio.update(relatorio);
    }
    
    public void excluir(Relatorio relatorio) throws RelatorioException {
        repositorio.delete(relatorio.getId());
    }
    
    public List<Relatorio> findAll() {
        return repositorio.findAll();
    }
 /*   
    public void atualizarTabela(JTable grd) {
        List<Relatorio> lst = dao.findAll();
        TMRelatorio tableModel = new TMRelatorio(lst);
        grd.setModel(tableModel);
    }*/
}