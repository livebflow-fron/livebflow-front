package com.livebflow.backend.imobiliaria.dto;

public record ImobiliariaResponse(
        Integer id,
        String razaoSocial,
        String cnpj,
        String email,
        String telefone
) {
}
