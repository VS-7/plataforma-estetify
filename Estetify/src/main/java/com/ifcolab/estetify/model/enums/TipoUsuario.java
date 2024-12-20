package com.ifcolab.estetify.model.enums;

public enum TipoUsuario {
    ADMIN("Administrador"),
    MEDICO("Médico"),
    ENFERMEIRA("Enfermeira"),
    RECEPCIONISTA("Recepcionista"),
    PACIENTE("Paciente");

    private final String descricao;

    TipoUsuario(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
    
    public static TipoUsuario fromString(String texto) {
        for (TipoUsuario tipo : TipoUsuario.values()) {
            if (tipo.name().equalsIgnoreCase(texto)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Tipo de usuário inválido: " + texto);
    }
}