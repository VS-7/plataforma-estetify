package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.*;
import com.ifcolab.estetify.model.dao.*;
import com.ifcolab.estetify.model.enums.TipoUsuario;
import com.ifcolab.estetify.model.exceptions.AutenticacaoException;
import com.ifcolab.estetify.utils.Autenticacao;
import com.ifcolab.estetify.utils.GeradorSenha;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import com.ifcolab.estetify.utils.NotificadorEmail;
import java.time.LocalDateTime;

public class AutenticacaoController {
    private final MedicoDAO medicoDAO;
    private final EnfermeiraDAO enfermeiraDAO;
    private final PacienteDAO pacienteDAO;
    private final RecepcionistaDAO recepcionistaDAO;
    private final AdminDAO adminDAO;
    private final Autenticacao autenticacao;
    private final GerenciadorCriptografia gerenciadorCriptografia;
    private final NotificadorEmail notificadorEmail;

    public AutenticacaoController() {
        this.medicoDAO = new MedicoDAO();
        this.enfermeiraDAO = new EnfermeiraDAO();
        this.pacienteDAO = new PacienteDAO();
        this.recepcionistaDAO = new RecepcionistaDAO();
        this.adminDAO = new AdminDAO();
        this.autenticacao = Autenticacao.getInstance();
        this.gerenciadorCriptografia = new GerenciadorCriptografia();
        this.notificadorEmail = new NotificadorEmail();
    }

    private void realizarAutenticacao(Pessoa usuario, String senha) {
        if (usuario.getCodigoRecuperacao() != null && usuario.getValidadeCodigoRecuperacao() != null) {
            if (usuario.getCodigoRecuperacao().equals(senha) 
                && usuario.getValidadeCodigoRecuperacao().isAfter(LocalDateTime.now())) {
                autenticacao.setUsuario(usuario);
                return;
            }
        }

        if (!gerenciadorCriptografia.compararSenha(senha, usuario.getSenha())) {
            throw new AutenticacaoException("Senha incorreta");
        }

        autenticacao.setUsuario(usuario);
    }

    public void login(String email, String senha) {

        Pessoa usuario = medicoDAO.findByEmail(email);
        if (usuario == null) {
            usuario = enfermeiraDAO.findByEmail(email);
        }
        if (usuario == null) {
            usuario = pacienteDAO.findByEmail(email);
        }
        if (usuario == null) {
            usuario = recepcionistaDAO.findByEmail(email);
        }
        
        if (usuario == null) {
            usuario = adminDAO.findByEmail(email);
        }
        
        if (usuario == null) {
            throw new AutenticacaoException("Usuário não encontrado");
        }

        realizarAutenticacao(usuario, senha);
    }

    public void logout() {
        autenticacao.setUsuario(null);
    }

    public Pessoa getUsuarioLogado() {
        return autenticacao.getUsuario();
    }

    public boolean isUsuarioLogado() {
        return autenticacao.getUsuario() != null;
    }

    public boolean isAdmin() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.ADMIN;
    }

    public boolean isMedico() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.MEDICO;
    }

    public boolean isEnfermeira() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.ENFERMEIRA;
    }

    public boolean isRecepcionista() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.RECEPCIONISTA;
    }

    public boolean isPaciente() {
        return isUsuarioLogado() && autenticacao.getUsuario().getTipoUsuario() == TipoUsuario.PACIENTE;
    }

    public Medico getMedicoLogado() {
        return isMedico() ? (Medico) autenticacao.getUsuario() : null;
    }

    public Enfermeira getEnfermeiraLogada() {
        return isEnfermeira() ? (Enfermeira) autenticacao.getUsuario() : null;
    }

    public Recepcionista getRecepcionistaLogado() {
        return isRecepcionista() ? (Recepcionista) autenticacao.getUsuario() : null;
    }

    public Paciente getPacienteLogado() {
        return isPaciente() ? (Paciente) autenticacao.getUsuario() : null;
    }

    public void atualizarDadosUsuario(Pessoa usuario, int novoAvatar) {
        try {
            if (novoAvatar > 0) {
                usuario.setAvatar(novoAvatar);
            }
            
            if (usuario instanceof Medico) {
                medicoDAO.update((Medico) usuario);
            } 
            else if (usuario instanceof Enfermeira) {
                enfermeiraDAO.update((Enfermeira) usuario);
            }
            else if (usuario instanceof Paciente) {
                pacienteDAO.update((Paciente) usuario);
            }
            else if (usuario instanceof Recepcionista) {
                recepcionistaDAO.update((Recepcionista) usuario);
            }
            
            else if (usuario instanceof Admin) {
                adminDAO.update((Admin) usuario);
            }
            else {
                throw new RuntimeException("Tipo de usuário não suportado");
            }
            
            autenticacao.setUsuario(usuario);
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar dados: " + ex.getMessage());
        }
    }

    public void atualizarSenhaUsuario(int idUsuario, String novaSenhaHash) {
        try {
            Pessoa usuario = autenticacao.getUsuario();
            
            if (usuario == null || usuario.getId() != idUsuario) {
                throw new AutenticacaoException("Usuário não autorizado para esta operação");
            }
            
            usuario.setSenha(novaSenhaHash);
            
            usuario.setCodigoRecuperacao(null);
            usuario.setValidadeCodigoRecuperacao(null);
            
            if (usuario instanceof Medico) {
                medicoDAO.update((Medico) usuario);
            } 
            else if (usuario instanceof Enfermeira) {
                enfermeiraDAO.update((Enfermeira) usuario);
            }
            else if (usuario instanceof Paciente) {
                pacienteDAO.update((Paciente) usuario);
            }
            else if (usuario instanceof Recepcionista) {
                recepcionistaDAO.update((Recepcionista) usuario);
            }
            else if (usuario instanceof Admin) {
               adminDAO.update((Admin) usuario);
            }
            else {
                throw new RuntimeException("Tipo de usuário não suportado");
            }
            
            autenticacao.setUsuario(usuario);
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar senha: " + ex.getMessage());
        }
    }

    public Pessoa buscarUsuarioPorEmail(String email) {

        Pessoa usuario = medicoDAO.findByEmail(email);
        if (usuario == null) {
            usuario = enfermeiraDAO.findByEmail(email);
        }
        if (usuario == null) {
            usuario = pacienteDAO.findByEmail(email);
        }
        if (usuario == null) {
            usuario = recepcionistaDAO.findByEmail(email);
        }
        if (usuario == null) {
            usuario = adminDAO.findByEmail(email);
        }
        
        
        return usuario;
    }

    public void recuperarSenha(String email) {
        Pessoa usuario = buscarUsuarioPorEmail(email);
        
        if (usuario == null) {
            throw new AutenticacaoException("Email não encontrado no sistema!");
        }

        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        usuario.setSenha(senhaHash);
        atualizarDadosUsuario(usuario, usuario.getAvatar());

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
            usuario.getNome(),
            usuario.getEmail(),
            senhaTemporaria
        );

        if (!notificadorEmail.notificar(usuario, "Nova Senha Temporária - Estetify", mensagem)) {
            throw new AutenticacaoException("Erro ao enviar email de recuperação");
        }
    }
} 