package com.ifcolab.estetify.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(columnNames = {"medico_id", "data"})
})
public class Agenda implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @Column(nullable = false)
    private LocalDate data;
    
    public Agenda() {
    }
    
    public Agenda(Medico medico, LocalDate data) {
        this.medico = medico;
        this.data = data;
    }
}