package com.ifcolab.estetify.utils;

import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Procedimento;
import java.util.List;

public interface IGeradorDocumento {
    public void gerarDocumento(String caminho, String... conteudo);
    public void combinarDocumentos(String caminhoSaida, List<String> caminhosEntrada);
    public void gerarReciboPagamento(String caminho, Consulta consulta, Pagamento pagamento);
    public void gerarRelatorioProcedimento(String caminho, Consulta consulta, Procedimento procedimento, String resultado, String observacoes);
} 