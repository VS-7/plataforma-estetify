package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewPaciente;
import com.ifcolab.estetify.model.Consulta;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.dao.PacienteDAO;
import com.ifcolab.estetify.model.dao.ConsultaDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.PacienteException;
import com.ifcolab.estetify.model.valid.ValidatePaciente;
import com.ifcolab.estetify.utils.GeradorSenha;
import com.ifcolab.estetify.utils.GerenciadorCriptografia;
import com.ifcolab.estetify.utils.NotificadorEmail;
import javax.swing.JTable;
import java.util.List;


public class PacienteController {
    
    private PacienteDAO repositorio;
    private ConsultaDAO consultaDAO;
    private ValidatePaciente valid;
    private GerenciadorCriptografia gerenciadorCriptografia;
    private NotificadorEmail notificadorEmail;
    
    public PacienteController() {
        this.repositorio = new PacienteDAO();
        this.consultaDAO = new ConsultaDAO();
        valid = new ValidatePaciente();
        gerenciadorCriptografia = new GerenciadorCriptografia();
        notificadorEmail = new NotificadorEmail();
    }
    
    public void cadastrar(String nome, String email, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoMedico, int avatar) {
        
        String senhaTemporaria = GeradorSenha.gerarSenha(8);
        String senhaHash = gerenciadorCriptografia.criptografarSenha(senhaTemporaria);
        
        Paciente paciente = valid.validaCamposEntrada(nome, email, senhaHash, cpf, sexo, dataNascimento, telefone, endereco, historicoMedico, avatar);
        
        Paciente pacienteExistenteCPF = repositorio.findByCPF(cpf);
        if (pacienteExistenteCPF != null) {
            throw new PacienteException("CPF já cadastrado");
        }
        
        Paciente pacienteExistenteEmail = repositorio.findByEmail(email);
        if (pacienteExistenteEmail != null) {
            throw new PacienteException("Email já cadastrado");
        }

        repositorio.save(paciente);
        enviarCredenciaisAcesso(paciente, senhaTemporaria);
    }
    
    private void enviarCredenciaisAcesso(Paciente paciente, String senhaTemporaria) {
        String mensagem = String.format(
            "Olá %s,\n\n" +
            "Suas credenciais de acesso ao sistema Estetify foram criadas:\n\n" +
            "Email: %s\n" +
            "Senha: %s\n\n" +
            "Por favor, altere sua senha no primeiro acesso.\n\n" +
            "Atenciosamente,\n" +
            "Equipe Estetify",
            paciente.getNome(),
            paciente.getEmail(),
            senhaTemporaria
        );

        notificadorEmail.notificar(paciente, "Credenciais de Acesso - Estetify", mensagem);
    }
    
    public void atualizar(int id, String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoMedico, int avatar) {
        
        Paciente paciente = valid.validaCamposEntrada(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, historicoMedico, avatar);
        paciente.setId(id);
        
        Paciente pacienteExistenteCPF = repositorio.findByCPF(cpf);
        if (pacienteExistenteCPF != null && pacienteExistenteCPF.getId() != id) {
            throw new PacienteException("CPF já cadastrado para outro paciente");
        }
        
        Paciente pacienteExistenteEmail = repositorio.findByEmail(email);
        if (pacienteExistenteEmail != null && pacienteExistenteEmail.getId() != id) {
            throw new PacienteException("Email já cadastrado para outro paciente");
        }
        
        repositorio.update(paciente);
    }
    
    public void excluir(Paciente paciente) {
        if (paciente == null) {
            throw new PacienteException("Erro - Paciente inexistente.");
        }
        
        List<Consulta> consultas = consultaDAO.buscarConsultasPorPaciente(paciente.getId());
        if (!consultas.isEmpty()) {
            throw new PacienteException("Não é possível excluir o paciente pois existem consultas associadas a ele.");
        }
        
        boolean deletado = repositorio.delete(paciente.getId());
        if (!deletado) {
            throw new PacienteException("Erro ao excluir o paciente.");
        }
    }
    
    public Paciente buscarPorCPF(String cpf) {
        return repositorio.findByCPF(cpf);
    }
    
    public List<Paciente> findAll() {
        return repositorio.findAll();
    }
    
    public Paciente find(int id) {
        return repositorio.find(id);
    }
    
    public void atualizarTabela(JTable grd) {
        TMViewPaciente tmPaciente = new TMViewPaciente(repositorio.findAll());
        grd.setModel(tmPaciente);
    }
    
    public void filtrarTabela(JTable grd, String nome) {
        TMViewPaciente tmPaciente = new TMViewPaciente(repositorio.filterByName(nome));
        grd.setModel(tmPaciente);
    }
}
