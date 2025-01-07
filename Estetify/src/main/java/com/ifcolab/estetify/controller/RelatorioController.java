package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewRelatorio;
import com.ifcolab.estetify.model.Relatorio;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.RelatorioDAO;
import com.ifcolab.estetify.model.exceptions.RelatorioException;
import com.ifcolab.estetify.model.valid.ValidateRelatorio;
import com.ifcolab.estetify.utils.GeradorPdf;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;

public class RelatorioController {
    
    private RelatorioDAO repositorio;
    private ValidateRelatorio validate;
    private GeradorPdf geradorPdf;
    
    public RelatorioController() {
        repositorio = new RelatorioDAO();
        validate = new ValidateRelatorio();
        geradorPdf = new GeradorPdf();
    }
    
    
    public void cadastrar(String resultado, String observacoes, Consulta consulta, Procedimento procedimento) throws RelatorioException {
            
        String caminhoPdf = System.getProperty("user.home") + "/Estetify/relatorios/" + 
            consulta.getId() + "_" + procedimento.getId() + "_" + 
            LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        
        new java.io.File(caminhoPdf).mkdirs();
        
        geradorPdf.gerarRelatorioProcedimento(caminhoPdf, consulta, procedimento, resultado, observacoes);
        
        String caminhoCompletoPdf = caminhoPdf + "/relatorio_procedimento.pdf";
            
        Relatorio relatorio = validate.validaCamposEntrada(
            LocalDateTime.now(),
            resultado,
            observacoes,
            caminhoCompletoPdf,
            consulta
        );
        
        repositorio.save(relatorio);
    }
    
    public void atualizar(int id, String resultado, String observacoes, Consulta consulta, Procedimento procedimento) throws RelatorioException {

        String caminhoPdf = System.getProperty("user.home") + "/Estetify/relatorios/" + 
            consulta.getId() + "_" + procedimento.getId() + "_" + 
            LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        

        new java.io.File(caminhoPdf).mkdirs();
        

        geradorPdf.gerarRelatorioProcedimento(caminhoPdf, consulta, procedimento, resultado, observacoes);
        
        String caminhoCompletoPdf = caminhoPdf + "/relatorio_procedimento.pdf";
            
        Relatorio relatorio = validate.validaCamposEntrada(LocalDateTime.now(), resultado, observacoes, caminhoCompletoPdf, consulta);
        
        relatorio.setId(id);
        repositorio.update(relatorio);
    }
    
    public void validarESalvarRelatorio(String resultado, String observacoes, Consulta consulta, Procedimento procedimento) throws RelatorioException {
        if (resultado.isEmpty()) {
            throw new RelatorioException("O campo Resultado é obrigatório!");
        }
        cadastrar(resultado, observacoes, consulta, procedimento);
    }
    
    
    public List<Relatorio> findAll() {
        return repositorio.findAll();
    }

    public void atualizarTabela(JTable grd) {
        List<Relatorio> lst = repositorio.findAll();
        TMViewRelatorio tableModel = new TMViewRelatorio(lst);
        grd.setModel(tableModel);
    }
    
    public void abrirPdf(Relatorio relatorio) throws RelatorioException {
        try {
            java.io.File arquivo = new java.io.File(relatorio.getCaminhoPdf());
            if (arquivo.exists()) {
                java.awt.Desktop.getDesktop().open(arquivo);
            } else {
                throw new RelatorioException("Arquivo PDF não encontrado.");
            }
        } catch (Exception e) {
            throw new RelatorioException("Erro ao abrir PDF: " + e.getMessage());
        }
    }
    
    public void excluir(Relatorio relatorio) throws RelatorioException {
        if (relatorio == null) {
            throw new RelatorioException("Erro - Relatório inexistente.");
        }
        
        boolean deletado = repositorio.delete(relatorio.getId());
        if (!deletado) {
            throw new RelatorioException("Erro - Relatório inexistente.");
        }
    }
    
    public Relatorio buscarPorId(int id) throws RelatorioException {
        Relatorio relatorio = repositorio.find(id);
        if (relatorio == null) {
            throw new RelatorioException("Relatório não encontrado.");
        }
        return relatorio;
    }
    
    public List<Relatorio> buscarTodos() {
        return repositorio.findAll();
    }
    
}