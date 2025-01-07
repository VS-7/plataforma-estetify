package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.enums.TipoSexo;

public class ValidatePaciente extends ValidatePessoa {
    
    public Paciente validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String historicoMedico, int avatar) {
        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);
        
        return new Paciente(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, historicoMedico, avatar);
    }
}

