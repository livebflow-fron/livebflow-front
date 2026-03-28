package com.livebflow.backend.auth.dto;

public record LoginResponse(
        String token,
        String perfil,
        String email
) {}

