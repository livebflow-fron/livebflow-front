package com.livebflow.backend.imobiliaria.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ImobiliariaRequest(
        @NotBlank(message = "Razao social e obrigatoria")
        @Size(max = 150, message = "Razao social deve ter no maximo 150 caracteres")
        String razaoSocial,

        @NotBlank(message = "CNPJ e obrigatorio")
        @Size(max = 20, message = "CNPJ deve ter no maximo 20 caracteres")
        String cnpj,

        @Email(message = "Email invalido")
        @Size(max = 100, message = "Email deve ter no maximo 100 caracteres")
        String email,

        @Size(max = 20, message = "Telefone deve ter no maximo 20 caracteres")
        String telefone
) {
}
