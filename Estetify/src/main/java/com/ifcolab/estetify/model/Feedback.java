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
import java.time.LocalDateTime;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(nullable = false)
    private String titulo;
    
    @Column(nullable = false, length = 1000)
    private String descricao;
    
    @Column(nullable = false)
    private int avaliacao; // 1 a 5 estrelas
    
    @Column(nullable = false)
    private LocalDateTime dataAvaliacao;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id", nullable = false)
    private Consulta consulta;
    
    
    public Feedback(String titulo, String descricao, int avaliacao, Consulta consulta, LocalDateTime dataAvaliacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.avaliacao = avaliacao;
        this.consulta = consulta;
        this.dataAvaliacao = LocalDateTime.now();
    }
}
