/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewPaciente;
import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.dao.PacienteDAO;
import com.ifcolab.estetify.model.exceptions.PacienteException;
import com.ifcolab.estetify.model.valid.ValidatePaciente;
import javax.swing.JTable;
import java.util.List;

/**
 *
 * @author vitorsrgio
 */
public class PacienteController {
    
    private PacienteDAO repositorio;
    
    public PacienteController() {
        repositorio = new PacienteDAO();
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
            String historicoMedico
    ) {
        ValidatePaciente valid = new ValidatePaciente();
        Paciente paciente = valid.validaCamposEntrada(
                nome,
                email,
                senha,
                confirmarSenha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                historicoMedico
        );
        
        if (repositorio.findByCPF(cpf) != null) {
            throw new PacienteException("CPF já cadastrado");
        }
        
        repositorio.save(paciente);
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
            String historicoMedico
    ) {
        ValidatePaciente valid = new ValidatePaciente();
        Paciente paciente = valid.validaCamposEntrada(
                nome,
                email,
                senha,
                confirmarSenha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                historicoMedico
        );
        
        paciente.setId(id);
        repositorio.update(paciente);
    }
    
    public void excluir(Paciente paciente) {
        if (paciente != null) {
            repositorio.delete(paciente.getId());
        } else {
            throw new PacienteException("Erro - Paciente inexistente.");
        }
    }
    
    public Paciente buscarPorCPF(String cpf) {
        return repositorio.findByCPF(cpf);
    }
    
    public List<Paciente> findAll() {
        return repositorio.findAll();
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
