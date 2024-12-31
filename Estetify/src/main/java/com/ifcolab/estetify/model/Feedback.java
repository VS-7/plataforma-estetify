package com.ifcolab.estetify.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private String titulo;
    
    @Column(length = 1000)
    private String avaliacao;
    
    private int numAvaliacaoEstrela;
    
    @OneToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;
}
