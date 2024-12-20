package com.ifcolab.estetify.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recepcionista extends Pessoa implements Serializable {
    
    private LocalDate dataContratacao;
    
    public Recepcionista() {
        super();
    }
    
    public Recepcionista(String nome, String email, String senha, String cpf, String sexo, String dataNascimento, String telefone, String endereco, String dataContratacao) {

        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo, 
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              "RECEPCIONISTA");
        
        this.dataContratacao = LocalDate.parse(dataContratacao, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
