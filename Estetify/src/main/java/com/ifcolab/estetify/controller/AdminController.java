package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewAdmin;
import com.ifcolab.estetify.model.Admin;
import com.ifcolab.estetify.model.dao.AdminDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.AdminException;
import com.ifcolab.estetify.model.valid.ValidateAdmin;
import com.ifcolab.estetify.utils.GeradorSenha;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import com.ifcolab.estetify.utils.NotificadorEmail;
import javax.swing.JTable;
import java.util.List;

public class AdminController {
    
    private AdminDAO repositorio;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    
    public AdminController() {
        repositorio = new AdminDAO();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, int avatar) {

        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        ValidateAdmin valid = new ValidateAdmin();
        Admin admin = valid.validaCamposEntrada(nome, email, senhaHash, cpf, sexo, dataNascimento, telefone, endereco, avatar);
        
        if (repositorio.findByCPF(cpf) != null) {
            throw new AdminException("CPF já cadastrado");
        }

        repositorio.save(admin);

        enviarCredenciaisAcesso(admin, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Admin admin, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            admin.getNome(),
            admin.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(admin, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    public void enviarLembreteCredenciais(Admin admin) {

        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        

        admin.setSenha(senhaHash);
        repositorio.update(admin);
        
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
            admin.getNome(),
            admin.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(admin, "Nova Senha Temporária - Estetify", mensagem);
    }
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, int avatar) {
        ValidateAdmin valid = new ValidateAdmin();
        Admin admin = valid.validaCamposEntrada(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, avatar);
        
        admin.setId(id);
        repositorio.update(admin);
    }
    
    public void excluir(Admin admin) {
        if (admin != null) {
            repositorio.delete(admin.getId());
        } else {
            throw new AdminException("Erro - Administrador inexistente.");
        }
    }
    
    public Admin buscarPorCPF(String cpf) {
        return repositorio.findByCPF(cpf);
    }
    
    public List<Admin> findAll() {
        return repositorio.findAll();
    }
    
    public Admin find(int id) {
        return repositorio.find(id);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewAdmin tmAdmin = new TMViewAdmin(repositorio.findAll());
        grd.setModel(tmAdmin);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewAdmin tmAdmin = new TMViewAdmin(repositorio.filterByName(nome));
        grd.setModel(tmAdmin);
    }
} 