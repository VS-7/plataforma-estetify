package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewRecepcionista;
import com.ifcolab.estetify.model.Recepcionista;
import com.ifcolab.estetify.model.dao.RecepcionistaDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.RecepcionistaException;
import com.ifcolab.estetify.model.valid.ValidateRecepcionista;
import com.ifcolab.estetify.utils.GeradorSenha;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import com.ifcolab.estetify.utils.NotificadorEmail;
import javax.swing.JTable;

public class RecepcionistaController {
    
    private RecepcionistaDAO repositorio;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    private ValidateRecepcionista valid;
           
    public RecepcionistaController() {
        repositorio = new RecepcionistaDAO();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        valid = new ValidateRecepcionista();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, 
            String dataNascimento, String telefone, String endereco, String dataContratacao, int avatar) {
            
        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        Recepcionista recepcionista = valid.validaCamposEntrada(nome, email, senhaHash, 
                cpf, sexo, dataNascimento, telefone, endereco, dataContratacao, avatar);
        
        Recepcionista recepcionistaExistenteCPF = repositorio.findByCPF(cpf);
        if (recepcionistaExistenteCPF != null) {
            throw new RecepcionistaException("CPF já cadastrado");
        }
        
        Recepcionista recepcionistaExistenteEmail = repositorio.findByEmail(email);
        if (recepcionistaExistenteEmail != null) {
            throw new RecepcionistaException("Email já cadastrado");
        }
        
        repositorio.save(recepcionista);
        enviarCredenciaisAcesso(recepcionista, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Recepcionista recepcionista, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            recepcionista.getNome(),
            recepcionista.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(recepcionista, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, 
            TipoSexo sexo, String dataNascimento, String telefone, String endereco, String dataContratacao, int avatar) {
            
 
        Recepcionista recepcionista = valid.validaCamposEntrada(nome, email, senha, 
                cpf, sexo, dataNascimento, telefone, endereco, dataContratacao ,avatar);
        recepcionista.setId(id);
        
        Recepcionista recepcionistaExistenteCPF = repositorio.findByCPF(cpf);
        if (recepcionistaExistenteCPF != null && recepcionistaExistenteCPF.getId() != id) {
            throw new RecepcionistaException("CPF já cadastrado para outra recepcionista");
        }
        
        Recepcionista recepcionistaExistenteEmail = repositorio.findByEmail(email);
        if (recepcionistaExistenteEmail != null && recepcionistaExistenteEmail.getId() != id) {
            throw new RecepcionistaException("Email já cadastrado para outra recepcionista");
        }
        
        repositorio.update(recepcionista);
    }
    
    public void excluir(Recepcionista recepcionista) {
        if (recepcionista != null) {
            repositorio.delete(recepcionista.getId());
        } else {
            throw new RecepcionistaException("Erro - Recepcionista inexistente.");
        }
    }
    
    public Recepcionista buscarPorCPF(String cpf) {
        return repositorio.findByCPF(cpf);
    }
    
    public Recepcionista find(int id) {
        return repositorio.find(id);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewRecepcionista tmRecepcionista = new TMViewRecepcionista(repositorio.findAll());
        grd.setModel(tmRecepcionista);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewRecepcionista tmRecepcionista = new TMViewRecepcionista(repositorio.filterByName(nome));
        grd.setModel(tmRecepcionista);
    }
}
