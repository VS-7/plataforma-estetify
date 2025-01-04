package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.model.*;
import com.ifcolab.estetify.model.dao.*;
import com.ifcolab.estetify.model.enums.TipoUsuario;
import com.ifcolab.estetify.model.exceptions.AutenticacaoException;
import com.ifcolab.estetify.utils.Autenticacao;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import java.time.LocalDateTime;

public class AutenticacaoController {
    private final MedicoDAO medicoDAO;
    private final EnfermeiraDAO enfermeiraDAO;
    private final PacienteDAO pacienteDAO;
    private final RecepcionistaDAO recepcionistaDAO;
    private final Autenticacao autenticacao;
    private final GerenciadorCriptografia gerenciadorCriptografia;

    public AutenticacaoController() {
        this.medicoDAO = new MedicoDAO();
        this.enfermeiraDAO = new EnfermeiraDAO();
        this.pacienteDAO = new PacienteDAO();
        this.recepcionistaDAO = new RecepcionistaDAO();
        this.autenticacao = Autenticacao.getInstance();
        this.gerenciadorCriptografia = new GerenciadorCriptografia();
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
        // Tenta encontrar o usuário em cada repositório
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
            // Atualiza o avatar se foi fornecido um novo
            if (novoAvatar > 0) {
                usuario.setAvatar(novoAvatar);
            }
            
            // Atualiza no banco de dados apropriado
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
            else {
                throw new RuntimeException("Tipo de usuário não suportado");
            }
            
            // Atualiza o usuário na sessão
            autenticacao.setUsuario(usuario);
            
        } catch (Exception ex) {
            throw new RuntimeException("Erro ao atualizar dados: " + ex.getMessage());
        }
    }
} 