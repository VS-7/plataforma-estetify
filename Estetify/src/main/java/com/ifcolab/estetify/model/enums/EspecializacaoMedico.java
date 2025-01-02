package com.ifcolab.estetify.model.enums;

public enum EspecializacaoMedico {
    DERMATOLOGIA("Dermatologia"),
    CIRURGIA_PLASTICA("Cirurgia Plástica"),
    MEDICINA_ESTETICA("Medicina Estética"),
    ANGIOLOGIA("Angiologia"),
    ENDOCRINOLOGIA("Endocrinologia");
    
    private final String descricao;
    
    EspecializacaoMedico(String descricao) {
        this.descricao = descricao;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
} 