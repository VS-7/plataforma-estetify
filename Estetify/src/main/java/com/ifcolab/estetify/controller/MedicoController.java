package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewMedico;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.dao.MedicoDAO;
import com.ifcolab.estetify.model.exceptions.MedicoException;
import com.ifcolab.estetify.model.valid.ValidateMedico;
import javax.swing.JTable;
import java.util.List;
import com.ifcolab.estetify.model.enums.EspecializacaoMedico;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.utils.GeradorSenha;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import com.ifcolab.estetify.utils.NotificadorEmail;

public class MedicoController {
    
    private MedicoDAO repositorio;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    private ValidateMedico valid;
    
    public MedicoController() {
        repositorio = new MedicoDAO();
        valid = new ValidateMedico();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String crm, EspecializacaoMedico especializacao, int avatar) {
        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        Medico medico = valid.validaCamposEntrada(nome, email, senhaHash, cpf, sexo, dataNascimento, telefone, endereco, crm, especializacao, avatar);
        
    if (repositorio.findByCPF(cpf) != null) {
        throw new MedicoException("CPF já cadastrado no sistema");
    }
    
    if (repositorio.findByEmail(email) != null) {
        throw new MedicoException("Email já cadastrado no sistema");
    }
    
    if (repositorio.findByCRM(crm) != null) {
        throw new MedicoException("CRM já cadastrado no sistema");
    }
        
        repositorio.save(medico);
        enviarCredenciaisAcesso(medico, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Medico medico, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            medico.getNome(),
            medico.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(medico, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String crm, EspecializacaoMedico especializacao, int avatar) {
        
        Medico medico = valid.validaCamposEntrada(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, crm, especializacao, avatar);
        medico.setId(id);
        
        Medico medicoExistenteCPF = repositorio.findByCPF(cpf);
        if (medicoExistenteCPF != null && medicoExistenteCPF.getId() != id) {
            throw new MedicoException("CPF já cadastrado para outro médico");
        }
        
        Medico medicoExistenteEmail = repositorio.findByEmail(email);
        if (medicoExistenteEmail != null && medicoExistenteEmail.getId() != id) {
            throw new MedicoException("Email já cadastrado para outro médico");
        }
        
        Medico medicoExistenteCRM = repositorio.findByCRM(crm);
        if (medicoExistenteCRM != null && medicoExistenteCRM.getId() != id) {
            throw new MedicoException("CRM já cadastrado para outro médico");
        }
        
        repositorio.update(medico);
    }
    
    public void excluir(Medico medico) {
        if (medico != null) {
            repositorio.delete(medico.getId());
        } else {
            throw new MedicoException("Erro - Médico inexistente.");
        }
    }
    
    public Medico buscarPorCRM(String crm) {
        return repositorio.findByCRM(crm);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewMedico tmMedico = new TMViewMedico(repositorio.findAll());
        grd.setModel(tmMedico);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewMedico tmMedico = new TMViewMedico(repositorio.filterByName(nome));
        grd.setModel(tmMedico);
    }
    
    public Medico find(int id) {
        return repositorio.find(id);
    }
    
    public List<Medico> findAll() {
        return repositorio.findAll();
    }
}
