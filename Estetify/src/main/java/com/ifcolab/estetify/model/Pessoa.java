package com.ifcolab.estetify.model;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Pessoa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    
    protected String nome;
    
    @Column(unique = true)
    protected String email;
    
    protected String senha;
    
    @Column(unique = true)
    protected String cpf;
    
    protected String sexo;
    protected LocalDate dataNascimento;
    protected String telefone;
    protected String endereco;
    protected String tipoUsuario;
    
    /* Construtor sem o atributo ID */
    public Pessoa(
            String nome,
            String email,
            String senha,
            String cpf,
            String sexo,
            LocalDate dataNascimento,
            String telefone,
            String endereco,
            String tipoUsuario
    ) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.tipoUsuario = tipoUsuario;
    }
}
