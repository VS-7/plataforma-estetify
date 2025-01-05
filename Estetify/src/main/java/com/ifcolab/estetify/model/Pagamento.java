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
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.ifcolab.estetify.model.enums.StatusPagamento;
import com.ifcolab.estetify.model.enums.MetodoPagamento;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pagamento implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private double valor;
    
    @Enumerated(EnumType.STRING)
    private StatusPagamento status;
    
    @Enumerated(EnumType.STRING)
    private MetodoPagamento metodoPagamento;
    
    private LocalDateTime dataPagamento;
    
    @Column(length = 500)
    private String detalhes;
    
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;
    
    @ManyToOne
    @JoinColumn(name = "registradopor_id")
    private Pessoa registradoPor;
}
