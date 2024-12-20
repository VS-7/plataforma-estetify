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
import com.ifcolab.estetify.model.enums.TipoUsuario;

@Getter
@Setter
@Entity
public class Medico extends Pessoa implements Serializable {
    
    @Column(unique = true)
    private String crm;
    private String especializacao;
    
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
    
    
    public Medico() {
        super();
    }
    
    public Medico(String nome, String email, String senha, String cpf, String sexo, String dataNascimento, String telefone, String endereco, String crm, String especializacao) {

        super(nome, 
              email, 
              senha, 
              cpf, 
              sexo, 
              LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy")), 
              telefone, 
              endereco, 
              TipoUsuario.MEDICO);  
        this.crm = crm;
        this.especializacao = especializacao;
    }
    
    @Override
    public String toString() {
        return this.getNome() + " (CRM: " + this.getCrm() + ")";
    }
}
