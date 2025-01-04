package com.ifcolab.estetify.utils;

import com.ifcolab.estetify.model.Pessoa;

public interface INotificador {
    boolean notificar(Pessoa pessoa, String titulo, String mensagem);
} 