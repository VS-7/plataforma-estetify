package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Medico;
import com.ifcolab.estetify.model.dao.MedicoDAO;
import com.ifcolab.estetify.model.exceptions.MedicoException;
import com.ifcolab.estetify.model.enums.EspecializacaoMedico;
import com.ifcolab.estetify.model.enums.TipoSexo;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class ValidateMedico {
    
    private MedicoDAO repositorio;
    
    public ValidateMedico() {
        repositorio = new MedicoDAO();
    }
    
    public Medico validaCamposEntrada(
            String nome,
            String email,
            String senha,
          
            String cpf,
            TipoSexo sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String crm,
            EspecializacaoMedico especializacao,
            int avatar
    ) {
        if (nome == null || nome.isEmpty()) {
            throw new MedicoException("Nome não pode estar em branco.");
        }

        if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new MedicoException("Nome inválido.");
        }

        if (email == null || email.isEmpty()) {
            throw new MedicoException("Email não pode estar em branco.");
        }

        if (!isValidEmail(email)) {
            throw new MedicoException("Email inválido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new MedicoException("Senha não pode estar em branco.");
        }


        if (cpf == null || !isValidCPF(cpf)) {
            throw new MedicoException("CPF inválido.");
        }

        if (telefone == null || telefone.isEmpty()) {
            throw new MedicoException("Telefone não pode estar em branco.");
        }

        if (!telefone.replaceAll("[^0-9]", "").matches("^\\d+$")) {
            throw new MedicoException("Telefone inválido.");
        }

        if (sexo == null) {
            throw new MedicoException("Sexo não pode estar em branco.");
        }

        if (dataNascimento == null || dataNascimento.isEmpty()) {
            throw new MedicoException("Data de Nascimento não pode estar em branco.");
        }
        
        isValidDataNascimento(dataNascimento);

        if (endereco == null || endereco.isEmpty()) {
            throw new MedicoException("Endereço não pode estar em branco.");
        }
        
        if (crm == null || crm.isEmpty()) {
            throw new MedicoException("CRM não pode estar em branco.");
        }
        
        if (!isValidCRM(crm)) {
            throw new MedicoException("CRM inválido.");
        }
        
        if (especializacao == null) {
            throw new MedicoException("Especialização não pode estar em branco.");
        }

        return new Medico(
                nome,
                email,
                senha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                crm,
                especializacao,
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
                throw new MedicoException("Data fora do intervalo aceitável.");
            }
        } catch (ParseException e) {
            throw new MedicoException("Data inválida. Use o formato dd/MM/yyyy.");
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
    
    private boolean isValidCRM(String crm) {
        // Remove todos os caracteres não numéricos
        crm = crm.replaceAll("[^0-9]", "");
        
        // Verifica se o CRM tem entre 5 e 6 dígitos
        if (crm.length() < 5 || crm.length() > 6) {
            return false;
        }
        
        // Verifica se contém apenas números
        return crm.matches("^\\d+$");
    }
    
    public void uniqueEmail(String email) {
        Medico aux = repositorio.findByEmail(email);
        if (aux != null) {
            throw new MedicoException("Email já está sendo usado.");
        }
    }
}
