package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Pessoa;
import com.ifcolab.estetify.model.dao.PagamentoDAO;
import com.ifcolab.estetify.model.enums.StatusPagamento;
import com.ifcolab.estetify.model.enums.MetodoPagamento;
import com.ifcolab.estetify.model.exceptions.PagamentoException;
import com.ifcolab.estetify.model.valid.ValidatePagamento;
import com.ifcolab.estetify.controller.tablemodel.TMViewPagamento;
import com.ifcolab.estetify.utils.GeradorPdf;
import com.ifcolab.estetify.utils.NotificadorEmail;
import java.util.List;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.awt.Desktop;
import javax.swing.JTable;
import javax.swing.JFileChooser;

public class PagamentoController {
    private final PagamentoDAO repositorio;
    private final ValidatePagamento validador;
    private final NotificadorEmail notificadorEmail;
    private final Consulta consulta;
    
    public PagamentoController(Consulta consulta) {
        this.repositorio = new PagamentoDAO();
        this.validador = new ValidatePagamento();
        this.notificadorEmail = new NotificadorEmail();
        this.consulta = consulta;
    }
    
    public PagamentoController() {
        this(null);
    }
    
    public void realizarPagamento(double valor, MetodoPagamento metodoPagamento, String detalhes) {
        if (metodoPagamento == null) {
            throw new PagamentoException("Selecione um método de pagamento.");
        }
        
        cadastrar(valor, StatusPagamento.PAGO, metodoPagamento, detalhes, 
                 this.consulta, this.consulta.getMedico());
    }
    
    private void cadastrar(double valor, StatusPagamento status, MetodoPagamento metodoPagamento, 
                         String detalhes, Consulta consulta, Pessoa registrador) {
        if (metodoPagamento == null) {
            throw new PagamentoException("Selecione um método de pagamento.");
        }
        
        Pagamento pagamento = validador.validaCamposEntrada(valor, status, metodoPagamento, 
                                                          detalhes, consulta, registrador);
        
        repositorio.save(pagamento);
        enviarConfirmacaoPagamento(valor, metodoPagamento);
    }
    
    private void enviarConfirmacaoPagamento(double valor, MetodoPagamento metodoPagamento) {
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
    
    public String getNomePaciente() {
        return consulta.getPaciente().getNome();
    }
    
    public String getDataHoraFormatada() {
        return consulta.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public String getNomeMedico() {
        return consulta.getMedico().getNome();
    }
    
    public double getValorTotal() {
        return consulta.getValorTotal();
    }
    
    public List<String> getProcedimentosFormatados() {
        return consulta.getProcedimentos().stream()
            .map(proc -> String.format("• %s - R$ %.2f", 
                proc.getNome(), proc.getValor()))
            .toList();
    }
    
    public MetodoPagamento[] getMetodosPagamento() {
        return MetodoPagamento.values();
    }
    
    public List<Pagamento> buscarPorConsulta(int consultaId) {
        return repositorio.buscarPorConsulta(consultaId);
    }
    
    public void excluir(Pagamento pagamento) throws Exception {
        if (pagamento == null) {
            throw new PagamentoException("Pagamento não encontrado.");
        }
        
        repositorio.delete(pagamento.getId());
    }
    
    public Pagamento buscar(int id) {
        return repositorio.find(id);
    }
    
    public List<Pagamento> buscarTodos() {
        return repositorio.findAll();
    }
    
    public void atualizar(int id, double valor, MetodoPagamento metodoPagamento, 
        StatusPagamento status, String detalhes) {
        Pagamento pagamento = buscar(id);
        if (pagamento == null) {
            throw new PagamentoException("Pagamento não encontrado.");
        }
        
        pagamento = validador.validaCamposEntrada(
            valor, 
            status, 
            metodoPagamento, 
            detalhes, 
            pagamento.getConsulta(), 
            pagamento.getConsulta().getMedico()
        );
        
        pagamento.setId(id);
        repositorio.update(pagamento);
    }
    
    public void atualizarTabela(JTable tabela) {
        List<Pagamento> pagamentos = buscarTodos();
        TMViewPagamento modelo = new TMViewPagamento(pagamentos);
        tabela.setModel(modelo);
    }
    
    public void emitirRecibo(Pagamento pagamento) throws Exception {
        if (pagamento == null) {
            throw new PagamentoException("Pagamento não encontrado.");
        }
        
        // Criar caminho base
        String caminhoPdf = System.getProperty("user.home") + "/Estetify/recibos/" + 
            String.format("%d_%s",
                pagamento.getId(),
                pagamento.getDataPagamento().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            );
        
        // O GeradorPdf já cuida da criação do diretório e uso correto do separador
        GeradorPdf geradorPdf = new GeradorPdf();
        geradorPdf.gerarReciboPagamento(caminhoPdf, pagamento.getConsulta(), pagamento);
        
        // Abre o arquivo PDF gerado
        String caminhoCompletoPdf = caminhoPdf + "/recibo_pagamento.pdf";
        Desktop.getDesktop().open(new File(caminhoCompletoPdf));
    }
}
