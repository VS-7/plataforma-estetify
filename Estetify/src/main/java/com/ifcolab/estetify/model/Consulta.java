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
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.List;
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
    
    @Column(length = 1000)
    private String observacoes;
    
    @Column(length = 20)
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
    
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;
    
    @ManyToOne
    @JoinColumn(name = "enfermeira_id")
    private Enfermeira enfermeira;
    
    @ManyToMany
    private List<Procedimento> procedimentos;
    
    @OneToOne(mappedBy = "consulta")
    private Pagamento pagamento;
    
    public Consulta(
            LocalDateTime dataHora,
            String observacoes,
            Paciente paciente,
            Medico medico,
            Enfermeira enfermeira,
            List<Procedimento> procedimentos
    ) {
        this.dataHora = dataHora;
        this.observacoes = observacoes;
        this.paciente = paciente;
        this.medico = medico;
        this.enfermeira = enfermeira;
        this.procedimentos = procedimentos;
        this.status = "AGENDADA";
    }
    
    public boolean isAgendada() {
        return "AGENDADA".equals(status);
    }
    
    public boolean isConfirmada() {
        return "CONFIRMADA".equals(status);
    }
    
    public boolean isCancelada() {
        return "CANCELADA".equals(status);
    }
    
    public boolean isConcluida() {
        return "CONCLUIDA".equals(status);
    }
    
    public double getValorTotal() {
        return procedimentos.stream()
                .mapToDouble(Procedimento::getValor)
                .sum();
    }
}
