package com.ifcolab.estetify.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.JoinTable;
import javax.persistence.Enumerated;
import javax.persistence.EnumType;
import com.ifcolab.estetify.model.enums.StatusConsulta;
import javax.persistence.FetchType;

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
    
    @Enumerated(EnumType.STRING)
    private StatusConsulta status;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "enfermeira_id")
    private Enfermeira enfermeira;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "Consulta_Procedimento",
        joinColumns = @JoinColumn(name = "Consulta_id"),
        inverseJoinColumns = @JoinColumn(name = "procedimentos_id")
    )
    private List<Procedimento> procedimentos;
    
    @OneToOne(mappedBy = "consulta")
    private Pagamento pagamento;

    
    public Consulta(int id, LocalDateTime dataHora, String observacoes, StatusConsulta status, 
                   Enfermeira enfermeira, Medico medico, Paciente paciente, 
                   List<Procedimento> procedimentos) {
        this.id = id;
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.status = status;
        this.enfermeira = enfermeira;
        this.medico = medico;
        this.paciente = paciente;
        this.procedimentos = procedimentos;
    }
    
    public boolean isAgendada() {
        return StatusConsulta.AGENDADA.equals(status);
    }
    
    public boolean isConfirmada() {
        return StatusConsulta.CONFIRMADA.equals(status);
    }
    
    public boolean isCancelada() {
        return StatusConsulta.CANCELADA.equals(status);
    }
    
    public boolean isConcluida() {
        return StatusConsulta.CONCLUIDA.equals(status);
    }
    
    public double getValorTotal() {
        return procedimentos.stream()
                .mapToDouble(Procedimento::getValor)
                .sum();
    }
    
    @Override
    public String toString() {
        return String.format("Consulta do dia %s - %s",
            dataHora.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
            procedimentos.stream()
                .map(Procedimento::getNome)
                .collect(Collectors.joining(", "))
        );
    }
}
