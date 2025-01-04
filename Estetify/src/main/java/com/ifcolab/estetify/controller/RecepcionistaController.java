package com.ifcolab.estetify.controller;

import com.ifcolab.estetify.controller.tablemodel.TMViewRecepcionista;
import com.ifcolab.estetify.model.Recepcionista;
import com.ifcolab.estetify.model.dao.RecepcionistaDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.RecepcionistaException;
import com.ifcolab.estetify.model.valid.ValidateRecepcionista;
import javax.swing.JTable;

public class RecepcionistaController {
    
    private RecepcionistaDAO repositorio;
    
    public RecepcionistaController() {
        repositorio = new RecepcionistaDAO();
    }
    
    public void cadastrar(
            String nome,
            String email,
            String senha,
            String cpf,
            TipoSexo sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String dataContratacao,
            int avatar
    ) {
        ValidateRecepcionista valid = new ValidateRecepcionista();
        Recepcionista recepcionista = valid.validaCamposEntrada(
                nome,
                email,
                senha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                dataContratacao,
                avatar
        );
        
        if (repositorio.findByCPF(cpf) != null) {
            throw new RecepcionistaException("CPF já cadastrado");
        }
        
        repositorio.save(recepcionista);
    }
    
    public void atualizar(
            int id,
            String nome,
            String email,
            String senha,
            String cpf,
            TipoSexo sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String dataContratacao,
            int avatar
    ) {
        ValidateRecepcionista valid = new ValidateRecepcionista();
        Recepcionista recepcionista = valid.validaCamposEntrada(
                nome,
                email,
                senha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                dataContratacao,
                avatar
        );
        
        recepcionista.setId(id);
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
