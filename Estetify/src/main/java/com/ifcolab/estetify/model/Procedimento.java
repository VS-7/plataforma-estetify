package com.ifcolab.estetify.model;

import java.time.Duration;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.ifcolab.estetify.model.enums.TipoProcedimento;


@Getter
@Setter
@Entity
public class Procedimento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Enumerated(EnumType.STRING)
    private TipoProcedimento tipo;
    
    @Column(length = 1000)
    private String descricao;
    
    private Duration duracaoEstimada;
    private double valor;
    
    @Column(length = 500)
    private String requisitos;
    
    @Column(length = 500)
    private String contraindicacoes;
    
    private int intervaloRetornoDias;
    
    public Procedimento() {
    }
    
    public Procedimento(TipoProcedimento tipo, String descricao, String duracao, 
            double valor, String requisitos, String contraindicacoes, 
            int intervaloRetornoDias) {
        this.tipo = tipo;
        this.descricao = descricao;
        this.duracaoEstimada = parseDuracao(duracao);
        this.valor = valor;
        this.requisitos = requisitos;
        this.contraindicacoes = contraindicacoes;
        this.intervaloRetornoDias = intervaloRetornoDias;
    }
    
    private Duration parseDuracao(String duracao) {
        // Formato esperado: "HH:mm"
        String[] partes = duracao.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return Duration.ofHours(horas).plusMinutes(minutos);
    }
    
    @Override
    public String toString() {
        return this.getDescricao();
    }

    public String getNome() {
        return this.tipo.toString();
    }
}
