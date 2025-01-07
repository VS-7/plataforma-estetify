package com.ifcolab.estetify.utils;

import com.ifcolab.estetify.model.Pessoa;
import org.apache.commons.mail.SimpleEmail;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class NotificadorEmail implements INotificador {
    private static final String EMAIL_REMETENTE = "plataformaestetify@gmail.com";
    private static final String SENHA_EMAIL = "fnno fbby qcqa qwzv";

    @Override
    public boolean notificar(Pessoa pessoa, String titulo, String mensagem) {
        CompletableFuture.runAsync(() -> {
            SimpleEmail email = new SimpleEmail();
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthentication(EMAIL_REMETENTE, SENHA_EMAIL);
            email.setSSLOnConnect(true);

            try {
                email.setFrom(EMAIL_REMETENTE);
                email.setSubject(titulo);
                email.setMsg(mensagem);
                email.addTo(pessoa.getEmail());
                email.send();
                System.out.println("Email enviado com sucesso para: " + pessoa.getEmail());
            } catch (Exception e) {
                System.err.println("Falha em enviar email para " + pessoa.getEmail() + ": " + e.getMessage());
                e.printStackTrace();
            }
        }).orTimeout(1, TimeUnit.MINUTES);
        return true;
    }
} 