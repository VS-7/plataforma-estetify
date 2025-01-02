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
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.ifcolab.estetify.model.enums.TipoUsuario;
import com.ifcolab.estetify.model.enums.TipoSexo;
import com.ifcolab.estetify.model.enums.EspecializacaoMedico;

@Getter
@Setter
@Entity
public class Medico extends Pessoa implements Serializable {
    
    @Column(unique = true)
    private String crm;
    
    @Enumerated(EnumType.STRING)
    private EspecializacaoMedico especializacao;
    
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
    
    public Medico() {
        super();
    }
    
    public Medico(String nome, String email, String senha, String cpf, TipoSexo sexo, 
                 String dataNascimento, String telefone, String endereco, 
                 String crm, EspecializacaoMedico especializacao) {
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
        return this.getNome() + " (" + this.especializacao.getDescricao() + " - CRM: " + this.getCrm() + ")";
    }
}
