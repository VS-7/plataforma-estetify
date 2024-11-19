package com.ifcolab.estetify.model;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
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
    
    @OneToMany(mappedBy = "agenda")
    private List<Consulta> consultas;
    
    @ManyToOne
    private Medico medico;
    
    @ManyToOne
    private Enfermeira enfermeira;
    
    private LocalDate data;
    
    public List<Consulta> getConsultasDoDia(LocalDate data) {
        return consultas.stream()
            .filter(c -> c.getDataHora().toLocalDate().equals(data))
            .toList();
    }
    
    public List<Consulta> getConsultasPorProfissional(int profissionalId, boolean isMedico) {
        return consultas.stream()
            .filter(c -> isMedico ? 
                c.getMedico().getId() == profissionalId :
                c.getEnfermeira().getId() == profissionalId)
            .toList();
    }
    
    public List<Consulta> getConsultasPorPaciente(int pacienteId) {
        return consultas.stream()
            .filter(c -> c.getPaciente().getId() == pacienteId)
            .toList();
    }
    
    public boolean isHorarioDisponivel(LocalDateTime horario, int medicoId, int enfermeiraId) {
        return consultas.stream()
            .noneMatch(c -> 
                c.getDataHora().equals(horario) &&
                (c.getMedico().getId() == medicoId || 
                 c.getEnfermeira().getId() == enfermeiraId)
            );
    }
}
