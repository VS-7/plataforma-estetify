package com.ifcolab.estetify.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class Enfermeira extends Pessoa {
    @Column(unique = true)
    private String coren;
}
