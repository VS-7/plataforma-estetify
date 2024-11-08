package com.ifcolab.estetify.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Feedback implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String avaliacao;
    private int numAvaliacaoEstrela;
    
    @Column(length = 1000)
    private String comentarios;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
