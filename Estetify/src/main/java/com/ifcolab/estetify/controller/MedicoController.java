package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewMedico;
import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.dao.MedicoDAO;
import com.ifcolab.estetify.model.exceptions.MedicoException;
import com.ifcolab.estetify.model.valid.ValidateMedico;
import javax.swing.JTable;

public class MedicoController {
    
    private MedicoDAO repositorio;
    
    public MedicoController() {
        repositorio = new MedicoDAO();
    }
    
    public void cadastrar(
            String nome,
            String email,
            String senha,
            String confirmarSenha,
            String cpf,
            String sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String crm,
            String especializacao
    ) {
        ValidateMedico valid = new ValidateMedico();
        Medico medico = valid.validaCamposEntrada(
                nome,
                email,
                senha,
                confirmarSenha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                crm,
                especializacao
        );
        
        if (repositorio.findByCRM(crm) != null) {
            throw new MedicoException("CRM já cadastrado");
        }
        
        repositorio.save(medico);
    }
    
    public void atualizar(
            int id,
            String nome,
            String email,
            String senha,
            String confirmarSenha,
            String cpf,
            String sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String crm,
            String especializacao
    ) {
        ValidateMedico valid = new ValidateMedico();
        Medico medico = valid.validaCamposEntrada(
                nome,
                email,
                senha,
                confirmarSenha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                crm,
                especializacao
        );
        
        medico.setId(id);
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
}
