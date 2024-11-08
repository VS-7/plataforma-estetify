package com.ifcolab.estetify.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Getter
@Setter

@Entity

public class Medico extends Pessoa implements Serializable{
    
    @Column(unique = true)
    private String crm;
    private String especializacao;
    
    @OneToMany(mappedBy = "medico")
    private List<Consulta> consultas;
    
    @OneToOne(mappedBy = "medico")
    private Agenda agenda;
}
