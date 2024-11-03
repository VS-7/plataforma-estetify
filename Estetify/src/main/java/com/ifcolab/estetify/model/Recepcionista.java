package com.ifcolab.estetify.model;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Recepcionista extends Pessoa{
    private LocalDateTime dataContratacao;
}
