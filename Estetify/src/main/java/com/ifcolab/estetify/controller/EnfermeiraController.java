package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewEnfermeira;
import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.dao.EnfermeiraDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.EnfermeiraException;
import com.ifcolab.estetify.model.valid.ValidateEnfermeira;
import com.ifcolab.estetify.utils.GeradorSenha;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import com.ifcolab.estetify.utils.NotificadorEmail;
import javax.swing.JTable;
import java.util.List;

public class EnfermeiraController {
    
    private EnfermeiraDAO repositorio;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    private ValidateEnfermeira valid;
    
    public EnfermeiraController() {
        repositorio = new EnfermeiraDAO();
        valid = new ValidateEnfermeira();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, 
        String dataNascimento, String telefone, String endereco, String coren, int avatar) {
            
        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        Enfermeira enfermeira = valid.validaCamposEntrada(nome, email, senhaHash, cpf, 
                sexo, dataNascimento, telefone, endereco, coren, avatar);
        
        Enfermeira enfermeiraExistenteCPF = repositorio.findByCPF(cpf);
        if (enfermeiraExistenteCPF != null) {
            throw new EnfermeiraException("CPF já cadastrado");
        }
        
        Enfermeira enfermeiraExistenteEmail = repositorio.findByEmail(email);
        if (enfermeiraExistenteEmail != null) {
            throw new EnfermeiraException("Email já cadastrado");
        }
        
        Enfermeira enfermeiraExistenteCOREN = repositorio.findByCOREN(coren);
        if (enfermeiraExistenteCOREN != null) {
            throw new EnfermeiraException("COREN já cadastrado");
        }
        
        repositorio.save(enfermeira);
        enviarCredenciaisAcesso(enfermeira, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Enfermeira enfermeira, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            enfermeira.getNome(),
            enfermeira.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(enfermeira, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, 
        TipoSexo sexo, String dataNascimento, String telefone, String endereco, 
        String coren, int avatar) {
            
        Enfermeira enfermeira = valid.validaCamposEntrada(nome, email, senha, cpf, 
                sexo, dataNascimento, telefone, endereco, coren, avatar);
        enfermeira.setId(id);
        
        Enfermeira enfermeiraExistenteCPF = repositorio.findByCPF(cpf);
        if (enfermeiraExistenteCPF != null && enfermeiraExistenteCPF.getId() != id) {
            throw new EnfermeiraException("CPF já cadastrado para outra enfermeira");
        }
        
        Enfermeira enfermeiraExistenteEmail = repositorio.findByEmail(email);
        if (enfermeiraExistenteEmail != null && enfermeiraExistenteEmail.getId() != id) {
            throw new EnfermeiraException("Email já cadastrado para outra enfermeira");
        }
        
        Enfermeira enfermeiraExistenteCOREN = repositorio.findByCOREN(coren);
        if (enfermeiraExistenteCOREN != null && enfermeiraExistenteCOREN.getId() != id) {
            throw new EnfermeiraException("COREN já cadastrado para outra enfermeira");
        }
        
        repositorio.update(enfermeira);
    }
    
    public void excluir(Enfermeira enfermeira) {
        if (enfermeira != null) {
            repositorio.delete(enfermeira.getId());
        } else {
            throw new EnfermeiraException("Erro - Enfermeira inexistente.");
        }
    }
    
    public Enfermeira buscarPorCOREN(String coren) {
        return repositorio.findByCOREN(coren);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewEnfermeira tmEnfermeira = new TMViewEnfermeira(repositorio.findAll());
        grd.setModel(tmEnfermeira);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewEnfermeira tmEnfermeira = new TMViewEnfermeira(repositorio.filterByName(nome));
        grd.setModel(tmEnfermeira);
    }

    public Enfermeira find(int id) {
        return repositorio.find(id);
    }
    
    public List<Enfermeira> findAll() {
        return repositorio.findAll();
    }
}
