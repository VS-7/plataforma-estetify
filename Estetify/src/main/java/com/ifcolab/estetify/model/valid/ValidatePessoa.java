package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.PessoaException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public abstract class ValidatePessoa {
    
    protected void validarCamposBasicos(
            String nome,
            String email,
            String senha,
            String cpf,
            TipoSexo sexo,
            String dataNascimento,
            String telefone,
            String endereco
    ) {
        if (nome == null || nome.isEmpty()) {
            throw new PessoaException("Nome não pode estar em branco.");
        }

        if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new PessoaException("Nome inválido.");
        }

        if (email == null || email.isEmpty()) {
            throw new PessoaException("Email não pode estar em branco.");
        }

        if (!isValidEmail(email)) {
            throw new PessoaException("Email inválido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new PessoaException("Senha não pode estar em branco.");
        }

        if (cpf == null || !isValidCPF(cpf)) {
            throw new PessoaException("CPF inválido.");
        }

        if (telefone == null || telefone.isEmpty()) {
            throw new PessoaException("Telefone não pode estar em branco.");
        }

        if (!telefone.replaceAll("[^0-9]", "").matches("^\\d+$")) {
            throw new PessoaException("Telefone inválido.");
        }

        if (sexo == null) {
            throw new PessoaException("Sexo não pode estar em branco.");
        }

        if (dataNascimento == null || dataNascimento.isEmpty()) {
            throw new PessoaException("Data de Nascimento não pode estar em branco.");
        }
        
        isValidDataNascimento(dataNascimento);

        if (endereco == null || endereco.isEmpty()) {
            throw new PessoaException("Endereço não pode estar em branco.");
        }
    }

    protected boolean isValidCPF(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        if (cpf.length() != 11) {
            return false;
        }

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }
        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador > 9) {
            primeiroDigitoVerificador = 0;
        }

        soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }
        soma += primeiroDigitoVerificador * 2;
        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador > 9) {
            segundoDigitoVerificador = 0;
        }

        return (cpf.charAt(9) - '0' == primeiroDigitoVerificador) 
            && (cpf.charAt(10) - '0' == segundoDigitoVerificador);
    }

    protected boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.([A-Za-z]{2,4})$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    protected void isValidDataNascimento(String dataNascimento) {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false);

        try {
            Date data = formatoData.parse(dataNascimento);
            if (!dataValida(data)) {
                throw new PessoaException("Data fora do intervalo aceitável.");
            }
        } catch (ParseException e) {
            throw new PessoaException("Data inválida. Use o formato dd/MM/yyyy.");
        }
    }

    protected static boolean dataValida(Date data) {
        Date dataMinima = parseData("01/01/1900");
        Date dataMaxima = new Date();

        return data.after(dataMinima) && data.before(dataMaxima);
    }

    protected static Date parseData(String dataString) {
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            return formatoData.parse(dataString);
        } catch (ParseException e) {
            return null;
        }
    }
} 