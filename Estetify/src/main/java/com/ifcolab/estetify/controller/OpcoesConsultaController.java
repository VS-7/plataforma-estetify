package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import com.ifcolab.estetify.model.enums.TipoUsuario;
import com.ifcolab.estetify.utils.GeradorPdf;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class OpcoesConsultaController {
    private final ConsultaController consultaController;
    private final AutenticacaoController autenticacaoController;
    private final Consulta consulta;
    private final PagamentoController pagamentoController;
    private final GeradorPdf geradorPdf;
    
    public OpcoesConsultaController(Consulta consulta) {
        this.consultaController = new ConsultaController();
        this.autenticacaoController = new AutenticacaoController();
        this.pagamentoController = new PagamentoController(consulta);
        this.geradorPdf = new GeradorPdf();
        this.consulta = consulta;
    }
    
    public String getStatusFormatado() {
        return consulta.getStatus().toString();
    }
    
    public String getDataHoraFormatada() {
        return consulta.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public String getNomePaciente() {
        return consulta.getPaciente().getNome();
    }
    
    public String getNomeMedico() {
        return consulta.getMedico().getNome();
    }
    
    public String getNomeEnfermeira() {
        return consulta.getEnfermeira().getNome();
    }
    
    public List<String> getProcedimentosFormatados() {
        return consulta.getProcedimentos().stream()
            .map(p -> p.getNome() + " - R$ " + String.format("%.2f", p.getValor()))
            .toList();
    }
    
    public double getValorTotal() {
        return consulta.getValorTotal();
    }
    
    public StatusConsulta getStatus() {
        return consulta.getStatus();
    }
    
    public boolean podeConfirmarConsulta() {
        return consultaController.podeConfirmarConsulta(consulta);
    }
    
    public boolean podeRealizarConsulta() {
        return consultaController.podeRealizarConsulta(consulta);
    }
    
    public boolean podeFazerPagamento() {
        return consulta.isConcluida() && !temPagamentoRegistrado();
    }
    
    public boolean podeEmitirRecibo() {
        return consulta.isConcluida() && temPagamentoRegistrado();
    }
    
    private boolean temPagamentoRegistrado() {
        return !buscarPagamentosConsulta().isEmpty();
    }
    
    public boolean podeVerRelatorios() {
        TipoUsuario tipoUsuario = autenticacaoController.getUsuarioLogado().getTipoUsuario();
        return tipoUsuario == TipoUsuario.ADMIN || 
               tipoUsuario == TipoUsuario.MEDICO || 
               tipoUsuario == TipoUsuario.ENFERMEIRA;
    }
    
    public boolean podeGerenciarPagamentos() {
        TipoUsuario tipoUsuario = autenticacaoController.getUsuarioLogado().getTipoUsuario();
        return tipoUsuario == TipoUsuario.ADMIN|| 
               tipoUsuario == TipoUsuario.RECEPCIONISTA;
    }
    
    public void confirmarConsulta() {
        consultaController.confirmarConsulta(consulta.getId());
    }
    
    public void cancelarConsulta() {
        consultaController.cancelarConsulta(consulta.getId());
    }
    
    public void realizarConsulta() {
        consultaController.realizarConsulta(consulta.getId());
    }
    
    public List<Procedimento> getProcedimentos() {
        return consulta.getProcedimentos();
    }
    
    public Consulta getConsulta() {
        return consulta;
    }
    
    public List<Pagamento> buscarPagamentosConsulta() {
        return pagamentoController.buscarPorConsulta(consulta.getId());
    }
    
    public void gerarReciboPagamento(Pagamento pagamento) throws Exception {
        pagamentoController.emitirRecibo(pagamento);
    }
} 