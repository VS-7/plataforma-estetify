package com.ifcolab.estetify.utils;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Procedimento;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class GeradorPdf implements IGeradorDocumento {
    
    private static final Font TITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
    private static final Font SUBTITULO = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
    private static final Font NORMAL = FontFactory.getFont(FontFactory.HELVETICA, 12);
    private static final Font DESTAQUE = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
    private static final Font RODAPE = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10);

    @Override
    public void gerarDocumento(String caminho, String... conteudo) {
        Document documento = new Document();
        try {
            String caminhoArquivo = caminho + "/recibo.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(caminhoArquivo));
            documento.open();

            for (String texto : conteudo) {
                documento.add(new Paragraph(texto));
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }

    @Override
    public void combinarDocumentos(String caminhoSaida, List<String> caminhosEntrada) {
        Document documento = new Document();
        try {
            if (new File(caminhoSaida).isDirectory()) {
                caminhoSaida = caminhoSaida + File.separator + "relatorio.pdf";
            }

            File arquivoSaida = new File(caminhoSaida);
            arquivoSaida.getParentFile().mkdirs();

            PdfCopy copia = new PdfCopy(documento, new FileOutputStream(arquivoSaida));
            documento.open();

            for (String inputPath : caminhosEntrada) {
                PdfReader reader = new PdfReader(inputPath);
                copia.addDocument(reader);
                reader.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
    
    public void gerarReciboPagamento(String caminho, Consulta consulta, Pagamento pagamento) {
        Document documento = new Document();
        try {
            String caminhoArquivo = caminho + "/recibo_pagamento.pdf";
            PdfWriter.getInstance(documento, new FileOutputStream(caminhoArquivo));
            documento.open();
            
            // Cabeçalho
            Paragraph cabecalho = new Paragraph("ESTETIFY", TITULO);
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            documento.add(cabecalho);
            
            Paragraph subtitulo = new Paragraph("Sistema de Gestão de Clínica Estética", RODAPE);
            subtitulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(subtitulo);
            
            // CNPJ
            Paragraph cnpj = new Paragraph("CNPJ: 12.345.678/0001-90", RODAPE);
            cnpj.setAlignment(Element.ALIGN_CENTER);
            documento.add(cnpj);
            documento.add(new Paragraph("\n"));
            
            // Número do Recibo
            Paragraph numeroRecibo = new Paragraph(
                "Recibo Nº: " + String.format("%06d", pagamento.getId()), DESTAQUE);
            numeroRecibo.setAlignment(Element.ALIGN_RIGHT);
            documento.add(numeroRecibo);
            documento.add(new Paragraph("\n"));
            
            // Título do Recibo
            Paragraph titulo = new Paragraph("RECIBO DE PAGAMENTO", TITULO);
            titulo.setAlignment(Element.ALIGN_CENTER);
            documento.add(titulo);
            documento.add(new Paragraph("\n"));
            
            // Informações do Paciente
            documento.add(new Paragraph("Dados do Paciente", SUBTITULO));
            adicionarLinha(documento, "Nome:", consulta.getPaciente().getNome());
            adicionarLinha(documento, "CPF:", consulta.getPaciente().getCpf());
            documento.add(new Paragraph("\n"));
            
            // Informações da Consulta
            documento.add(new Paragraph("Dados da Consulta", SUBTITULO));
            adicionarLinha(documento, "Data/Hora:", 
                consulta.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            adicionarLinha(documento, "Médico:", "Dr(a). " + consulta.getMedico().getNome());
            documento.add(new Paragraph("\n"));
            
            // Procedimentos
            documento.add(new Paragraph("Procedimentos Realizados", SUBTITULO));
            double totalProcedimentos = 0;
            for (Procedimento proc : consulta.getProcedimentos()) {
                adicionarLinha(documento, "• " + proc.getNome() + ":",
                    "R$ " + String.format("%.2f", proc.getValor()));
                totalProcedimentos += proc.getValor();
            }
            documento.add(new Paragraph("\n"));
            
            // Informações do Pagamento
            documento.add(new Paragraph("Dados do Pagamento", SUBTITULO));
            adicionarLinha(documento, "Valor Total:", "R$ " + String.format("%.2f", pagamento.getValor()));
            adicionarLinha(documento, "Método de Pagamento:", pagamento.getMetodoPagamento().toString());
            adicionarLinha(documento, "Data do Pagamento:",
                pagamento.getDataPagamento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
            adicionarLinha(documento, "Registrado por:", pagamento.getRegistradoPor().getNome());
            
            if (pagamento.getDetalhes() != null && !pagamento.getDetalhes().isEmpty()) {
                documento.add(new Paragraph("\nObservações:", DESTAQUE));
                documento.add(new Paragraph(pagamento.getDetalhes(), NORMAL));
            }
            
            // Rodapé
            documento.add(new Paragraph("\n"));
            Paragraph rodape = new Paragraph(
                "Este documento é um recibo oficial do pagamento realizado.", RODAPE);
            rodape.setAlignment(Element.ALIGN_CENTER);
            documento.add(rodape);
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            documento.close();
        }
    }
    
    private void adicionarLinha(Document documento, String label, String valor) {
        try {
            Paragraph p = new Paragraph();
            p.add(new Paragraph(label, DESTAQUE));
            p.add(new Paragraph(" " + valor, NORMAL));
            documento.add(p);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
} 