package com.ifcolab.estetify.model.enums;

public enum TipoProcedimento {
    BOTOX("Botox"),
    PREENCHIMENTO_FACIAL("Preenchimento Facial"),
    LIMPEZA_DE_PELE("Limpeza de Pele"),
    PEELING_QUIMICO("Peeling Químico"),
    MICROAGULHAMENTO("Microagulhamento"),
    RADIOFREQUENCIA("Radiofrequência"),
    DEPILACAO_LASER("Depilação a Laser"),
    CARBOXITERAPIA("Carboxiterapia"),
    DRENAGEM_LINFATICA("Drenagem Linfática"),
    MASSAGEM_MODELADORA("Massagem Modeladora"),
    CRIOLIPÓLISE("Criolipólise"),
    ULTRASSOM_FOCADO("Ultrassom Focado"),
    LIFTING_NAO_CIRURGICO("Lifting Não Cirúrgico"),
    HIDRATACAO_FACIAL("Hidratação Facial"),
    TRATAMENTO_ACNE("Tratamento para Acne");

    private final String descricao;

    TipoProcedimento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}