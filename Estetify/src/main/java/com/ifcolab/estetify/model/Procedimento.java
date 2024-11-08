package com.ifcolab.estetify.model;

import java.time.LocalDateTime;
import java.time.Duration;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Procedimento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDateTime dataHora;
    
    @Column(length = 1000)
    private String descricao;
    
    private Duration duracaoEstimada;
    private double valor;
    
    @Column(length = 500)
    private String requisitos;
    
    @Column(length = 500)
    private String contraindicacoes;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @OneToOne(mappedBy = "procedimento")
    private Pagamento pagamento;
}
