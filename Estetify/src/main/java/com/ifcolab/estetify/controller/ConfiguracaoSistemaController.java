package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.dao.ConfiguracaoSistemaDAO;
import com.ifcolab.estetify.model.exceptions.ConfiguracaoException;
import com.ifcolab.estetify.model.valid.ValidateConfiguracao;
import java.time.LocalTime;
import javax.swing.SpinnerNumberModel;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;

public class ConfiguracaoSistemaController {
    
    private final ConfiguracaoSistemaDAO repositorio;
    private final ValidateConfiguracao validador;
    
    public ConfiguracaoSistemaController() {
        this.repositorio = new ConfiguracaoSistemaDAO();
        this.validador = new ValidateConfiguracao();
    }
    
    public void atualizar(LocalTime horarioAbertura, LocalTime horarioFechamento, int intervaloConsultaMinutos, boolean funcionaSegunda, boolean funcionaTerca, boolean funcionaQuarta, boolean funcionaQuinta, boolean funcionaSexta, boolean funcionaSabado, boolean funcionaDomingo, int tempoMinimoAntecedenciaMinutos, int tempoMaximoAgendamentoDias) {
        ConfiguracaoSistema config = validador.validaCamposEntrada(horarioAbertura, horarioFechamento, intervaloConsultaMinutos, funcionaSegunda, funcionaTerca, funcionaQuarta, funcionaQuinta, funcionaSexta, funcionaSabado, funcionaDomingo, tempoMinimoAntecedenciaMinutos, tempoMaximoAgendamentoDias);
        
        config.setId(1);
        repositorio.update(config);
    }
    
    public ConfiguracaoSistema getConfiguracao() {
        ConfiguracaoSistema config = repositorio.getConfiguracao();
        if (config == null) {
            throw new ConfiguracaoException("Erro ao carregar configurações do sistema");
        }
        return config;
    }
    
    public SpinnerNumberModel getIntervaloConsultaModel() {
        return new SpinnerNumberModel(30, 15, 120, 15);
    }
    
    public SpinnerNumberModel getTempoAntecedenciaModel() {
        return new SpinnerNumberModel(60, 0, 1440, 30);
    }
    
    public SpinnerNumberModel getTempoMaxAgendamentoModel() {
        return new SpinnerNumberModel(60, 1, 365, 1);
    }
    
    public MaskFormatter getHoraMaskFormatter() throws ParseException {
        MaskFormatter maskHora = new MaskFormatter("##:##");
        maskHora.setPlaceholderCharacter('_');
        return maskHora;
    }
} 