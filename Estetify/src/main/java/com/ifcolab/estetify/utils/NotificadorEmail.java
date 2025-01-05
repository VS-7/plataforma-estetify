package com.ifcolab.estetify.utils;

import com.ifcolab.estetify.model.*;
import com.ifcolab.estetify.model.dao.*;
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

    public boolean enviarCredenciais(Pessoa pessoa, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            pessoa.getNome(),
            pessoa.getEmail(),
            senhaTemporaria
        );

        return notificar(pessoa, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    public boolean enviarLembreteCredenciais(Pessoa pessoa) {
        try {
            // Gera uma nova senha temporária
            String senhaTemporaria = GeradorSenha.gerarSenha(8);
            
            // Criptografa a senha
            GerenciadorCriptografia gerenciadorCriptografia = new GerenciadorCriptografia();
            String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
            
            // Atualiza a senha da pessoa
            pessoa.setSenha(senhaHash);
            
            // Atualiza no banco através do DAO apropriado
            if (pessoa instanceof Medico) {
                new MedicoDAO().update((Medico) pessoa);
            } 
            else if (pessoa instanceof Enfermeira) {
                new EnfermeiraDAO().update((Enfermeira) pessoa);
            }
            else if (pessoa instanceof Paciente) {
                new PacienteDAO().update((Paciente) pessoa);
            }
            else if (pessoa instanceof Recepcionista) {
                new RecepcionistaDAO().update((Recepcionista) pessoa);
            }
            
            // Envia o email com a nova senha temporária
            String mensagem = String.format(
                "Olá %s,\n\n" +
                "Você solicitou a recuperação de sua senha do sistema Estetify.\n" +
                "Uma nova senha temporária foi gerada:\n\n" +
                "Email: %s\n" +
                "Nova Senha: %s\n\n" +
                "Por favor, altere esta senha após realizar o login.\n" +
                "Caso não tenha solicitado esta recuperação, entre em contato com o suporte.\n\n" +
                "Atenciosamente,\n" +
                "Equipe Estetify",
                pessoa.getNome(),
                pessoa.getEmail(),
                senhaTemporaria
            );

            return notificar(pessoa, "Nova Senha Temporária - Estetify", mensagem);
            
        } catch (Exception e) {
            System.err.println("Erro ao processar recuperação de senha: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

} 