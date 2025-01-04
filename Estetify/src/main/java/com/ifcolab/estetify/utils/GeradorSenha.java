package com.ifcolab.estetify.utils;

import java.security.SecureRandom;

public class GeradorSenha {
    private static final String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    
    public static String gerarSenha(int tamanho) {
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder();
        
        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(CHARS.length());
            senha.append(CHARS.charAt(index));
        }
        
        return senha.toString();
    }
} 