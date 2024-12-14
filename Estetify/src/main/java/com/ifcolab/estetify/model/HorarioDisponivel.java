package com.ifcolab.estetify.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Entity
public class HorarioDisponivel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalTime horario;
    
    @ManyToOne
    @JoinColumn(name = "agenda_id")
    private Agenda agenda;
    
    public HorarioDisponivel() {
    }
    
    public HorarioDisponivel(LocalTime horario, Agenda agenda) {
        this.horario = horario;
        this.agenda = agenda;
    }
}