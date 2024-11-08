package com.ifcolab.estetify.model;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Recepcionista extends Pessoa{
    private LocalDate dataContratacao;
}
