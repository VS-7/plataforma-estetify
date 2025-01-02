package com.ifcolab.estetify.model.enums;

public enum TipoSexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");
    
    private final String descricao;
    
    TipoSexo(String descricao) {
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