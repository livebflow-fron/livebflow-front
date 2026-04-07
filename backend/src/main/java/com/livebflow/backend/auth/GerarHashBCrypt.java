package com.livebflow.backend.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Classe utilitária para geração de Hash para o MVP (utilizando SHA-256)
 */
public class GerarHashBCrypt {

    /**
     * Gera um hash da senha utilizando SHA-256 para simulação no MVP.
     */
    public static String gerar(String senha) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder(2 * encodedhash.length);
            for (int i = 0; i < encodedhash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedhash[i]);
                if(hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao gerar hash", e);
        }
    }

    public static boolean verificar(String senhaMascara, String hashArmazenado) {
        if (senhaMascara == null || hashArmazenado == null) return false;
        String hashTentativa = gerar(senhaMascara);
        return hashTentativa.equals(hashArmazenado);
    }

    // Método main prático para testar e demonstrar o funcionamento rapidamente no terminal
    public static void main(String[] args) {
        String inputSenha = "MinhaSenhaSuperSegura123!";
        
        System.out.println("========== SISTEMA DE PROTEÇÃO LIVEB FLOW ==========");
        System.out.println("Iniciando geração de Hash...");
        System.out.println("Senha de Entrada: " + inputSenha);
        
        String resultado = gerar(inputSenha);
        
        System.out.println("Hash Gerado: " + resultado);
        System.out.println("Status: PRONTO PARA GRAVAÇÃO NO BANCO DE DADOS");
        System.out.println("====================================================");
    }
}
