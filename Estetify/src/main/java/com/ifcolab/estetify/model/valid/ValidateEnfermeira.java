package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Enfermeira;
import com.ifcolab.estetify.model.dao.EnfermeiraDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.EnfermeiraException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidateEnfermeira {
    
    private EnfermeiraDAO repositorio;
    
    public ValidateEnfermeira() {
        repositorio = new EnfermeiraDAO();
    }
    
    public Enfermeira validaCamposEntrada(
            String nome,
            String email,
            String senha,
            String confirmarSenha,
            String cpf,
            TipoSexo sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String coren,
            int avatar
    ) {
        if (nome == null || nome.isEmpty()) {
            throw new EnfermeiraException("Nome não pode estar em branco.");
        }

        if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new EnfermeiraException("Nome inválido.");
        }

        if (email == null || email.isEmpty()) {
            throw new EnfermeiraException("Email não pode estar em branco.");
        }

        if (!isValidEmail(email)) {
            throw new EnfermeiraException("Email inválido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new EnfermeiraException("Senha não pode estar em branco.");
        }

        if (!senha.equals(confirmarSenha)) {
            throw new EnfermeiraException("Confirmação de senha incorreta.");
        }

        if (cpf == null || !isValidCPF(cpf)) {
            throw new EnfermeiraException("CPF inválido.");
        }

        if (telefone == null || telefone.isEmpty()) {
            throw new EnfermeiraException("Telefone não pode estar em branco.");
        }

        if (!telefone.replaceAll("[^0-9]", "").matches("^\\d+$")) {
            throw new EnfermeiraException("Telefone inválido.");
        }

        if (sexo == null) {
            throw new EnfermeiraException("Sexo não pode estar em branco.");
        }

        if (dataNascimento == null || dataNascimento.isEmpty()) {
            throw new EnfermeiraException("Data de Nascimento não pode estar em branco.");
        }
        
        isValidDataNascimento(dataNascimento);

        if (endereco == null || endereco.isEmpty()) {
            throw new EnfermeiraException("Endereço não pode estar em branco.");
        }
        
        if (coren == null || coren.isEmpty()) {
            throw new EnfermeiraException("COREN não pode estar em branco.");
        }
        
        if (!isValidCOREN(coren)) {
            throw new EnfermeiraException("COREN inválido.");
        }

        return new Enfermeira(
                nome,
                email,
                senha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                coren,
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
                throw new EnfermeiraException("Data fora do intervalo aceitável.");
            }
        } catch (ParseException e) {
            throw new EnfermeiraException("Data inválida. Use o formato dd/MM/yyyy.");
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
    
    private boolean isValidCOREN(String coren) {
        // Remove todos os caracteres não numéricos
        coren = coren.replaceAll("[^0-9]", "");
        
        // Verifica se o COREN tem entre 5 e 7 dígitos
        if (coren.length() < 5 || coren.length() > 7) {
            return false;
        }
        
        // Verifica se contém apenas números
        return coren.matches("^\\d+$");
    }
    
    public void uniqueEmail(String email) {
        Enfermeira aux = repositorio.findByEmail(email);
        if (aux != null) {
            throw new EnfermeiraException("Email já está sendo usado.");
        }
    }
}
