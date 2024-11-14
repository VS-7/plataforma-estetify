package com.ifcolab.estetify.model;

import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Getter
@Setter
@Entity
public class Enfermeira extends Pessoa implements Serializable {
    
    @Column(unique = true)
    private String coren;
    
    
    public Enfermeira() {
        super();
    }
    
    public Enfermeira(
            String nome,
            String email,
            String senha,
            String cpf,
            String sexo,
            String dataNascimento,
            String telefone,
            String endereco,
            String coren
    ) {
        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo, 
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              "ENFERMEIRA");
        this.coren = coren;
    }
}
