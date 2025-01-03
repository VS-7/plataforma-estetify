package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.ConfiguracaoSistema;
import com.ifcolab.estetify.model.exceptions.ConfiguracaoException;
import java.time.LocalTime;

public class ValidateConfiguracao {
    
    public ConfiguracaoSistema validaCamposEntrada(
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
        if (horarioAbertura == null) {
            throw new ConfiguracaoException("Horário de abertura não pode estar em branco.");
        }
        
        if (horarioFechamento == null) {
            throw new ConfiguracaoException("Horário de fechamento não pode estar em branco.");
        }
        
        if (horarioFechamento.isBefore(horarioAbertura) || horarioFechamento.equals(horarioAbertura)) {
            throw new ConfiguracaoException("Horário de fechamento deve ser posterior ao horário de abertura.");
        }
        
        if (intervaloConsultaMinutos < 15 || intervaloConsultaMinutos > 120) {
            throw new ConfiguracaoException("Intervalo de consulta deve estar entre 15 e 120 minutos.");
        }
        
        if (!funcionaSegunda && !funcionaTerca && !funcionaQuarta && 
            !funcionaQuinta && !funcionaSexta && !funcionaSabado && !funcionaDomingo) {
            throw new ConfiguracaoException("Pelo menos um dia da semana deve estar selecionado.");
        }
        
        if (tamanhoMaximoObservacoes < 100 || tamanhoMaximoObservacoes > 1000) {
            throw new ConfiguracaoException("Tamanho máximo de observações deve estar entre 100 e 1000 caracteres.");
        }
        
        if (tempoMinimoAntecedenciaMinutos < 0 || tempoMinimoAntecedenciaMinutos > 1440) {
            throw new ConfiguracaoException("Tempo mínimo de antecedência deve estar entre 0 e 24 horas.");
        }
        
        if (tempoMaximoAgendamentoDias < 1 || tempoMaximoAgendamentoDias > 365) {
            throw new ConfiguracaoException("Tempo máximo de agendamento deve estar entre 1 e 365 dias.");
        }
        
        return new ConfiguracaoSistema(
            1, // ID fixo
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
            tempoMinimoAntecedenciaMinutos,
            tempoMaximoAgendamentoDias
        );
    }

} 