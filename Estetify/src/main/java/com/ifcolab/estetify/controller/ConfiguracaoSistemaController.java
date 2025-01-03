package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.estetify.model.valid.ValidateConfiguracao;
import java.time.LocalTime;

public class ConfiguracaoSistemaController {
    
    private final ConfiguracaoSistemaDAO repositorio;
    private final ValidateConfiguracao validador;
    
    public ConfiguracaoSistemaController() {
        this.repositorio = new ConfiguracaoSistemaDAO();
        this.validador = new ValidateConfiguracao();
    }
    
    public void atualizar(
            LocalTime horarioAbertura,
            LocalTime horarioFechamento,
            int intervaloConsultaMinutos,
            boolean funcionaSegunda,
            boolean funcionaTerca,
            boolean funcionaQuarta,
            boolean funcionaQuinta,
            boolean funcionaSexta,
            boolean funcionaSabado,
            boolean funcionaDomingo,
            int tamanhoMaximoObservacoes,
            int tempoMinimoAntecedenciaMinutos,
            int tempoMaximoAgendamentoDias
    ) {
        ConfiguracaoSistema config = validador.validaCamposEntrada(
            horarioAbertura,
            horarioFechamento,
            intervaloConsultaMinutos,
            funcionaSegunda,
            funcionaTerca,
            funcionaQuarta,
            funcionaQuinta,
            funcionaSexta,
            funcionaSabado,
            funcionaDomingo,
            tamanhoMaximoObservacoes,
            tempoMinimoAntecedenciaMinutos,
            tempoMaximoAgendamentoDias
        );
        
        repositorio.update(config);
    }
    
    public ConfiguracaoSistema getConfiguracao() {
        return repositorio.getConfiguracao();
    }
} 