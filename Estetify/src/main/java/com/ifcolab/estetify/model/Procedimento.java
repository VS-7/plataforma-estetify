package com.ifcolab.estetify.model;

import java.time.LocalDateTime;
import java.time.Duration;
import java.io.Serializable;
import java.time.format.DateTimeFormatter;
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
    
    @ManyToOne
    @JoinColumn(name = "enfermeira_id")
    private Enfermeira enfermeira;
    
    @OneToOne(mappedBy = "procedimento")
    private Pagamento pagamento;
    
    public Procedimento() {
    }
    
    public Procedimento(
            String dataHora,
            String descricao,
            String duracao,
            double valor,
            String requisitos,
            String contraindicacoes,
            Paciente paciente,
            Enfermeira enfermeira
    ) {
        this.dataHora = LocalDateTime.parse(dataHora, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.descricao = descricao;
        this.duracaoEstimada = parseDuracao(duracao);
        this.valor = valor;
        this.requisitos = requisitos;
        this.contraindicacoes = contraindicacoes;
        this.paciente = paciente;
        this.enfermeira = enfermeira;
    }
    
    private Duration parseDuracao(String duracao) {
        // Formato esperado: "HH:mm"
        String[] partes = duracao.split(":");
        int horas = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);
        return Duration.ofHours(horas).plusMinutes(minutos);
    }
    
    public String getDataHoraFormatada() {
        return dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }
    
    public String getDuracaoFormatada() {
        long horas = duracaoEstimada.toHours();
        long minutos = duracaoEstimada.toMinutesPart();
        return String.format("%02d:%02d", horas, minutos);
    }
    
    public String getValorFormatado() {
        return String.format("R$ %.2f", valor);
    }
    
    public boolean isPago() {
        return pagamento != null;
    }
}
