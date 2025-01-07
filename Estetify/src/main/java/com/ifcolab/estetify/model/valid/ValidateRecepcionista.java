package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Recepcionista;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.RecepcionistaException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ValidateRecepcionista extends ValidatePessoa {

    public Recepcionista validaCamposEntrada(String nome, String email, String senha, String cpf, TipoSexo sexo, String dataNascimento, String telefone, String endereco, String dataContratacao, int avatar) {
        validarCamposBasicos(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco);
        
        if (dataContratacao == null || dataContratacao.isEmpty()) {
            throw new RecepcionistaException("Data de Contratação não pode estar em branco.");
        }
        
        isValidDataContratacao(dataContratacao);

        return new Recepcionista(nome, email, senha, cpf, sexo, dataNascimento, telefone, endereco, dataContratacao, avatar);
    }
    
    private void isValidDataContratacao(String dataContratacao) {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false);

        try {
            Date data = formatoData.parse(dataContratacao);
            Date hoje = new Date();
            if (data.after(hoje)) {
                throw new RecepcionistaException("Data de contratação não pode ser futura.");
            }
        } catch (ParseException e) {
            throw new RecepcionistaException("Data de contratação inválida. Use o formato dd/MM/yyyy.");
        }
    }
    
}
