package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Admin;
import com.ifcolab.estetify.model.enums.TipoSexo;

public class ValidateAdmin extends ValidatePessoa {
    
    public Admin validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, int avatar) {

        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);

        return new Admin(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, avatar);
    }
} 