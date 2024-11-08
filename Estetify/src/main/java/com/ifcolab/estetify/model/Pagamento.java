package com.ifcolab.estetify.model;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pagamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private double valor;
    private String status;
    private String metodoPagamento;
    private LocalDateTime dataPagamento;
    
    @Column(length = 500)
    private String detalhes;
    
    @OneToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;
    
    @OneToOne
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;
}
