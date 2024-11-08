package com.ifcolab.estetify.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consulta implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private LocalDateTime dataHora;
    private String observacoes;
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @OneToOne(mappedBy = "consulta")
    private Pagamento pagamento;
}
