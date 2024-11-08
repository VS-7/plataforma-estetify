package com.ifcolab.estetify.model;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.Set;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ElementCollection;
import javax.persistence.CollectionTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agenda implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate data;
    @ElementCollection
    @CollectionTable(name = "horarios_disponiveis")
    private Set<LocalDateTime> horariosDisponiveis;
    @ElementCollection
    @CollectionTable(name = "horarios_ocupados")
    private Set<LocalDateTime> horariosOcupados;
    @OneToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
}
