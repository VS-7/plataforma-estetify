package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.EnfermeiraException;


public class ValidateEnfermeira extends ValidatePessoa {

    public Enfermeira validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String coren, int avatar) {

        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);
        
        if (coren == null || coren.isEmpty()) {
            throw new EnfermeiraException("COREN não pode estar em branco.");
        }
        
        if (!isValidCOREN(coren)) {
            throw new EnfermeiraException("COREN inválido.");
        }

        return new Enfermeira(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, coren, avatar);
    }
    
    private boolean isValidCOREN(String coren) {
        coren = coren.replaceAll("[^0-9]", "");
        return coren.length() >= 5 && coren.length() <= 7 && coren.matches("^\\d+$");
    }
}
