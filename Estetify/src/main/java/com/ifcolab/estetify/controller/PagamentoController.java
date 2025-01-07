package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Pessoa;
import com.ifcolab.estetify.model.dao.PagamentoDAO;
import com.ifcolab.estetify.model.enums.StatusPagamento;
import com.ifcolab.estetify.model.enums.MetodoPagamento;
import com.ifcolab.estetify.model.exceptions.PagamentoException;
import com.ifcolab.estetify.model.valid.ValidatePagamento;
import com.ifcolab.estetify.utils.NotificadorEmail;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class PagamentoController {
    
    private final PagamentoDAO repositorio;
    private final ValidatePagamento validador;
    private final NotificadorEmail notificadorEmail;
    
    public PagamentoController() {
        this.repositorio = new PagamentoDAO();
        this.validador = new ValidatePagamento();
        this.notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(double valor, StatusPagamento status, MetodoPagamento metodoPagamento, String detalhes, Consulta consulta, Pessoa registrador) {
        if (metodoPagamento == null) {
            throw new PagamentoException("Selecione um método de pagamento.");
        }
        
        Pagamento pagamento = validador.validaCamposEntrada(valor, status, metodoPagamento, detalhes, consulta, registrador);
        
        repositorio.save(pagamento);
        enviarConfirmacaoPagamento(consulta, valor, metodoPagamento);
    }
    
    private void enviarConfirmacaoPagamento(Consulta consulta, double valor, MetodoPagamento metodoPagamento) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "O pagamento da sua consulta foi confirmado:\n\n" +
            "Data: %s\n" +
            "Médico: %s\n" +
            "Valor: R$ %.2f\n" +
            "Forma de Pagamento: %s\n\n" +
            "Agradecemos a preferência!\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            consulta.getPaciente().getNome(),
            consulta.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            consulta.getMedico().getNome(),
            valor,
            metodoPagamento.toString()
        );

        notificadorEmail.notificar(
            consulta.getPaciente(), 
            "Confirmação de Pagamento - Estetify", 
            mensagem
        );
    }
    
    public List<Pagamento> buscarPorConsulta(int consultaId) {
        return repositorio.buscarPorConsulta(consultaId);
    }
    
    public void excluir(int id) {
        boolean deletado = repositorio.delete(id);
        if (!deletado) {
            throw new PagamentoException("Erro - Pagamento inexistente.");
        }
    }
    
    public Pagamento buscar(int id) {
        return repositorio.find(id);
    }
    
    public List<Pagamento> buscarTodos() {
        return repositorio.findAll();
    }
}
