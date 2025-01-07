package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Pagamento;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Procedimento;
import com.ifcolab.estetify.model.Pessoa;
import com.ifcolab.estetify.model.exceptions.PagamentoException;
import com.ifcolab.estetify.model.enums.StatusPagamento;
import com.ifcolab.estetify.model.enums.MetodoPagamento;
import java.time.LocalDateTime;

public class ValidatePagamento {
    
    public Pagamento validaCamposEntrada(double valor, StatusPagamento status, MetodoPagamento metodoPagamento, String detalhes, Consulta consulta, Pessoa registrador) {
        if (valor <= 0) {
            throw new PagamentoException("Valor deve ser maior que zero.");
        }
        
        if (status == null) {
            throw new PagamentoException("Status do pagamento não pode estar em branco.");
        }
        
        if (metodoPagamento == null) {
            throw new PagamentoException("Método de pagamento não pode estar em branco.");
        }
        
        if (detalhes != null && detalhes.length() > 500) {
            throw new PagamentoException("Detalhes não podem ter mais que 500 caracteres.");
        }
        
        if (registrador == null) {
            throw new PagamentoException("Registrador do pagamento não pode estar em branco.");
        }
        
        return new Pagamento(0, valor, status, metodoPagamento, LocalDateTime.now(), 
                           detalhes, consulta, registrador);
    }
}
