package com.ifcolab.estetify.controller;


import com.ifcolab.estetify.model.Pessoa;
import com.ifcolab.estetify.model.exceptions.ValidateException;

public class PerfilController {
    private final AutenticacaoController autenticacaoController;
    
    public PerfilController() {
        autenticacaoController = new AutenticacaoController();
    }
    
    public Pessoa getUsuarioLogado() {
        return autenticacaoController.getUsuarioLogado();
    }
    
    public void atualizarPerfil(String nome, String email, String telefone, 
                               String endereco, int novoAvatar) throws Exception {
        Pessoa usuario = getUsuarioLogado();
        if (usuario == null) {
            throw new Exception("Usuário não encontrado");
        }
        
        // Validações
        if (nome == null || nome.trim().isEmpty()) {
            throw new ValidateException("Nome é obrigatório");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new ValidateException("Email é obrigatório");
        }
        
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(telefone);
        usuario.setEndereco(endereco);
        
        autenticacaoController.atualizarDadosUsuario(usuario, novoAvatar);
    }
    
    public void atualizarSenha(int idUsuario, String novaSenhaHash) {
        autenticacaoController.atualizarSenhaUsuario(idUsuario, novaSenhaHash);
    }
} 