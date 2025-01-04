package com.ifcolab.estetify.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.ifcolab.estetify.model.enums.TipoUsuario;
import com.ifcolab.estetify.model.enums.TipoSexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;
    
    protected String nome;
    
    @Column(unique = true)
    protected String email;
    
    @Column
    protected String senha;
    
    @Column(unique = true)
    protected String cpf;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    protected TipoSexo sexo;
    
    protected LocalDate dataNascimento;
    protected String telefone;
    protected String endereco;
    @Enumerated(EnumType.STRING)
    protected TipoUsuario tipoUsuario;
    
    @Column
    private String codigoRecuperacao;
    
    @Column
    private LocalDateTime validadeCodigoRecuperacao;
    
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
    
    private int avatar = 1;
    
    public Pessoa(String nome, String email, String senha, String cpf, TipoSexo sexo, LocalDate dataNascimento, String telefone, String endereco, TipoUsuario tipo, int avatar) {

        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.tipo = tipo;
        this.avatar = avatar;
    }   
}
