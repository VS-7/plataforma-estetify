package com.ifcolab.estetify.model.valid;

import com.ifcolab.estetify.model.Paciente;
import com.ifcolab.estetify.model.dao.PacienteDAO;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.exceptions.PacienteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;


public class ValidatePaciente {
    
    private PacienteDAO repositorio;
    
    public ValidatePaciente() {
        repositorio = new PacienteDAO();
    }
    
    public Paciente validaCamposEntrada(
            String nome,
            String email,
            String senha,
            
            String cpf,
            TipoSexo sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String historicoMedico,
            int avatar
    ) {
        if (nome == null || nome.isEmpty()) {
            throw new PacienteException("Nome não pode estar em branco.");
        }

        if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$")) {
            throw new PacienteException("Nome inválido.");
        }

        if (email == null || email.isEmpty()) {
            throw new PacienteException("Email não pode estar em branco.");
        }

        if (!isValidEmail(email)) {
            throw new PacienteException("Email inválido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new PacienteException("Senha não pode estar em branco.");
        }



        if (cpf == null || !isValidCPF(cpf)) {
            throw new PacienteException("CPF inválido.");
        }

        if (telefone == null || telefone.isEmpty()) {
            throw new PacienteException("Telefone não pode estar em branco.");
        }

        if (!telefone.replaceAll("[^0-9]", "").matches("^\\d+$")) {
            throw new PacienteException("Telefone inválido.");
        }

        if (sexo == null) {
            throw new PacienteException("Sexo não pode estar em branco.");
        }

        if (dataNascimento == null || dataNascimento.isEmpty()) {
            throw new PacienteException("Data de Nascimento não pode estar em branco.");
        }
        
        isValidDataNascimento(dataNascimento);

        if (endereco == null || endereco.isEmpty()) {
            throw new PacienteException("Endereço não pode estar em branco.");
        }

        uniqueEmail(email);
        uniqueCPF(cpf);

        return new Paciente(
                nome,
                email,
                senha,
                cpf,
                sexo,
                dataNascimento,
                telefone,
                endereco,
                historicoMedico,
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
                throw new PacienteException("Data fora do intervalo aceitável.");
            }
        } catch (ParseException e) {
            throw new PacienteException("Data inválida. Use o formato dd/MM/yyyy.");
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
        Paciente aux = repositorio.findByEmail(email);
        if (aux != null) {
            throw new PacienteException("Email já está sendo usado.");
        }
    }
    
    public void uniqueCPF(String cpf) {
        String cpfLimpo = cpf.replaceAll("[^0-9]", "");
        String cpfFormatado = String.format("%s.%s.%s-%s",
            cpfLimpo.substring(0, 3),
            cpfLimpo.substring(3, 6),
            cpfLimpo.substring(6, 9),
            cpfLimpo.substring(9, 11));
        
        Paciente aux = repositorio.findByCPF(cpfFormatado);
        if (aux != null) {
            throw new PacienteException("CPF já está sendo usado.");
        }
    }
}
