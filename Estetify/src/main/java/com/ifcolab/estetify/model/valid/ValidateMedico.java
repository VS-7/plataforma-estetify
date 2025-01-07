package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.exceptions.MedicoException;
import com.ifcolab.estetify.model.enums.EspecializacaoMedico;
import com.ifcolab.estetify.model.enums.TipoSexo;

public class ValidateMedico extends ValidatePessoa {
    
    public Medico validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String crm, EspecializacaoMedico especializacao, int avatar) {

        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);
        
        if (crm == null || crm.isEmpty()) {
            throw new MedicoException("CRM não pode estar em branco.");
        }
        
        if (!isValidCRM(crm)) {
            throw new MedicoException("CRM inválido.");
        }
        
        if (especializacao == null) {
            throw new MedicoException("Especialização não pode estar em branco.");
        }

        return new Medico(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, crm, especializacao, avatar);
    }
    
    private boolean isValidCRM(String crm) {
        crm = crm.replaceAll("[^0-9]", "");
        return crm.length() >= 5 && crm.length() <= 6 && crm.matches("^\\d+$");
    }
}
