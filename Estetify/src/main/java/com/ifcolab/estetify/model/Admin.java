package com.ifcolab.estetify.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.ifcolab.estetify.model.enums.TipoUsuario;
import com.ifcolab.estetify.model.enums.TipoSexo;

@Getter
@Setter
@Entity
public class Admin extends Pessoa {
    
    public Admin() {
        super();
    }
    
    public Admin(String nome, String email, String senha, String cpf, TipoSexo sexo, 
                String dataNascimento, String telefone, String endereco, int avatar) {
        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo, 
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              TipoUsuario.ADMIN,
              avatar);
    }
    
    @Override
    public String toString() {
        return this.getNome();
    }
} 