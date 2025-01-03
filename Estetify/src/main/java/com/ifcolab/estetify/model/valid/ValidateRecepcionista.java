package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Recepcionista;
import com.ifcolab.estetify.model.dao.RecepcionistaDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.RecepcionistaException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class ValidateRecepcionista {
    
    private RecepcionistaDAO repositorio;
    
    public ValidateRecepcionista() {
        repositorio = new RecepcionistaDAO();
    }
    
    public Recepcionista validaCamposEntrada(
            String nome,
            String email,
            String senha,
            String confirmarSenha,
            String cpf,
            TipoSexo sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String dataContratacao,
            int avatar
    ) {
        if (nome == null || nome.isEmpty()) {
            throw new RecepcionistaException("Nome não pode estar em branco.");
        }

        if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new RecepcionistaException("Nome inválido.");
        }

        if (email == null || email.isEmpty()) {
            throw new RecepcionistaException("Email não pode estar em branco.");
        }

        if (!isValidEmail(email)) {
            throw new RecepcionistaException("Email inválido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new RecepcionistaException("Senha não pode estar em branco.");
        }

        if (!senha.equals(confirmarSenha)) {
            throw new RecepcionistaException("Confirmação de senha incorreta.");
        }

        if (cpf == null || !isValidCPF(cpf)) {
            throw new RecepcionistaException("CPF inválido.");
        }

        if (telefone == null || telefone.isEmpty()) {
            throw new RecepcionistaException("Telefone não pode estar em branco.");
        }

        if (!telefone.replaceAll("[^0-9]", "").matches("^\\d+$")) {
            throw new RecepcionistaException("Telefone inválido.");
        }

        if (sexo == null) {
            throw new RecepcionistaException("Sexo não pode estar em branco.");
        }


        if (dataNascimento == null || dataNascimento.isEmpty()) {
            throw new RecepcionistaException("Data de Nascimento não pode estar em branco.");
        }
        
        isValidDataNascimento(dataNascimento);

        if (endereco == null || endereco.isEmpty()) {
            throw new RecepcionistaException("Endereço não pode estar em branco.");
        }
        
        if (dataContratacao == null || dataContratacao.isEmpty()) {
            throw new RecepcionistaException("Data de Contratação não pode estar em branco.");
        }
        
        isValidDataContratacao(dataContratacao);

        return new Recepcionista(
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
    }

    private boolean isValidCPF(String cpf) {
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

    private boolean isValidEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+)\\.([A-Za-z]{2,4})$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    private void isValidDataNascimento(String dataNascimento) {
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        formatoData.setLenient(false);

        try {
            Date data = formatoData.parse(dataNascimento);
            if (!dataValida(data)) {
                throw new RecepcionistaException("Data de nascimento fora do intervalo aceitável.");
            }
        } catch (ParseException e) {
            throw new RecepcionistaException("Data de nascimento inválida. Use o formato dd/MM/yyyy.");
        }
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

    private static boolean dataValida(Date data) {
        Date dataMinima = parseData("01/01/1900");
        Date dataMaxima = new Date(); // Data atual

        return data.after(dataMinima) && data.before(dataMaxima);
    }

    private static Date parseData(String dataString) {
        try {
            SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
            return formatoData.parse(dataString);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public void uniqueEmail(String email) {
        Recepcionista aux = repositorio.findByEmail(email);
        if (aux != null) {
            throw new RecepcionistaException("Email já está sendo usado.");
        }
    }
}
