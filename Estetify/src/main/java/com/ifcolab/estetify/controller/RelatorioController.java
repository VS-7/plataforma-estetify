package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewRelatorio;
import com.ifcolab.estetify.model.Relatorio;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.dao.RelatorioDAO;
import com.ifcolab.estetify.model.enums.TipoProcedimento;
import com.ifcolab.estetify.model.exceptions.RelatorioException;
import com.ifcolab.estetify.model.valid.ValidateRelatorio;
import com.ifcolab.estetify.utils.GeradorPdf;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.JTable;
import java.io.File;
import java.awt.Desktop;
import java.time.format.DateTimeFormatter;
import javax.swing.JFileChooser;

public class RelatorioController {
    
    private final RelatorioDAO repositorio;
    private final ValidateRelatorio validate;
    private final GeradorPdf geradorPdf;
    private Consulta consulta;
    private Procedimento procedimento;
    
    public RelatorioController(Consulta consulta, Procedimento procedimento) {
        this.repositorio = new RelatorioDAO();
        this.validate = new ValidateRelatorio();
        this.geradorPdf = new GeradorPdf();
        this.consulta = consulta;
        this.procedimento = procedimento;
    }
    
    public void cadastrar(String resultado, String observacoes, Consulta consulta, Procedimento procedimento) throws RelatorioException {
        String caminhoPdf = System.getProperty("user.home") + "/Estetify/relatorios/" + 
            String.format("%d_%d_%s",
                consulta.getId(),
                procedimento.getId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            );
        
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
            String.format("%d_%d_%s",
                consulta.getId(),
                procedimento.getId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"))
            );
        
        geradorPdf.gerarRelatorioProcedimento(caminhoPdf, consulta, procedimento, resultado, observacoes);
        
        String caminhoCompletoPdf = caminhoPdf + "/relatorio_procedimento.pdf";
        
        Relatorio relatorio = validate.validaCamposEntrada(
            LocalDateTime.now(),
            resultado,
            observacoes,
            caminhoCompletoPdf,
            consulta
        );
        
        relatorio.setId(id);
        repositorio.update(relatorio);
    }
    
    public void validarESalvarRelatorio(String resultado, String observacoes) throws RelatorioException {
        if (resultado.isEmpty()) {
            throw new RelatorioException("O campo Resultado é obrigatório!");
        }
        cadastrar(resultado, observacoes, this.consulta, this.procedimento);
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
    
    public TipoProcedimento getTipoProcedimento() {
        return procedimento.getTipo();
    }
    
    public String getNomePaciente() {
        return consulta.getPaciente().getNome();
    }
    
    public String getNomeMedico() {
        return consulta.getMedico().getNome();
    }
    
    public String getDataHoraFormatada() {
        return consulta.getDataHora().format(
            java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public Relatorio buscarRelatorioDaConsulta() {
        List<Relatorio> relatorios = repositorio.findAll();
        return relatorios.stream()
            .filter(r -> r.getConsulta().getId() == consulta.getId())
            .findFirst()
            .orElse(null);
    }
    
    public void gerarRelatorio(String resultado, String observacoes) throws RelatorioException {
        if (resultado.isEmpty()) {
            throw new RelatorioException("O campo Resultado é obrigatório!");
        }
        
        try {
            // Abre diálogo para escolher onde salvar
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Salvar Relatório");
            
            // Define nome sugerido do arquivo
            String nomeArquivo = String.format("relatorio_%d_%d_%s.pdf", 
                consulta.getId(),
                procedimento.getId(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"))
            );
            fileChooser.setSelectedFile(new File(nomeArquivo));
            
            // Mostra diálogo de salvar
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                String caminhoCompleto = fileChooser.getSelectedFile().getAbsolutePath();
                
                // Adiciona extensão .pdf se não houver
                if (!caminhoCompleto.toLowerCase().endsWith(".pdf")) {
                    caminhoCompleto += ".pdf";
                }
                
                // Gera o PDF
                geradorPdf.gerarRelatorioProcedimento(caminhoCompleto, consulta, procedimento, resultado, observacoes);
                
                // Abre o arquivo PDF
                Desktop.getDesktop().open(new File(caminhoCompleto));
            }
            
        } catch (Exception e) {
            throw new RelatorioException("Erro ao gerar relatório: " + e.getMessage());
        }
    }
    
}