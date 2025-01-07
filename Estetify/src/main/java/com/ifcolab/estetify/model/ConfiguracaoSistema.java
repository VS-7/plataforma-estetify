package com.ifcolab.estetify.model;

import java.time.LocalTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.DayOfWeek;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ConfiguracaoSistema implements Serializable {
    
    @Id
    private int id;
    
    @Column
    private LocalTime horarioAbertura;
    
    @Column
    private LocalTime horarioFechamento;
    
    @Column
    private int intervaloConsultaMinutos;
    
    @Column
    private boolean funcionaSegunda;
    
    @Column
    private boolean funcionaTerca;
    
    @Column
    private boolean funcionaQuarta;
    
    @Column
    private boolean funcionaQuinta;
    
    @Column
    private boolean funcionaSexta;
    
    @Column
    private boolean funcionaSabado;
    
    @Column
    private boolean funcionaDomingo;
    
    @Column
    private int tempoMinimoAntecedenciaMinutos;
    
    @Column
    private int tempoMaximoAgendamentoDias;
    
    public boolean isDiaFuncionamento(DayOfWeek dia) {
        return switch (dia) {
            case MONDAY -> funcionaSegunda;
            case TUESDAY -> funcionaTerca;
            case WEDNESDAY -> funcionaQuarta;
            case THURSDAY -> funcionaQuinta;
            case FRIDAY -> funcionaSexta;
            case SATURDAY -> funcionaSabado;
            case SUNDAY -> funcionaDomingo;
        };
    }
    
    public boolean isHorarioFuncionamento(LocalTime horario) {
        return !horario.isBefore(horarioAbertura) && !horario.isAfter(horarioFechamento);
    }
    
    public boolean isHorarioValido(LocalTime horario) {
        long minutos = horario.getHour() * 60L + horario.getMinute();
        return minutos % intervaloConsultaMinutos == 0;
    }
    

} 