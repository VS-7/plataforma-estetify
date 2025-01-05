package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Pessoa;
import com.ifcolab.estetify.model.dao.PagamentoDAO;
import com.ifcolab.estetify.model.enums.StatusPagamento;
import com.ifcolab.estetify.model.enums.MetodoPagamento;
import com.ifcolab.estetify.model.valid.ValidatePagamento;
import java.util.List;

public class PagamentoController {
    
    private final PagamentoDAO repositorio;
    private final ValidatePagamento validador;
    
    public PagamentoController() {
        this.repositorio = new PagamentoDAO();
        this.validador = new ValidatePagamento();
    }
    
    public void cadastrar(
            double valor,
            StatusPagamento status,
            MetodoPagamento metodoPagamento,
            String detalhes,
            Consulta consulta,
            Pessoa registrador
    ) {
        Pagamento pagamento = validador.validaCamposEntrada(
            valor,
            status,
            metodoPagamento,
            detalhes,
            consulta,
            registrador
        );
        
        repositorio.save(pagamento);
    }
    
    public List<Pagamento> buscarPorConsulta(int consultaId) {
        return repositorio.buscarPorConsulta(consultaId);
    }
    
    public void excluir(int id) {
        repositorio.delete(id);
    }
    
    public Pagamento buscar(int id) {
        return repositorio.find(id);
    }
    
    public List<Pagamento> buscarTodos() {
        return repositorio.findAll();
    }
}
