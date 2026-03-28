package com.livebflow.backend.auth;

import com.livebflow.backend.auth.dto.LoginRequest;
import com.livebflow.backend.auth.dto.LoginResponse;
import com.livebflow.backend.common.BusinessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository repository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UsuarioRepository repository,
                       JwtService jwtService,
                       PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(LoginRequest request) {

        Usuario usuario = repository.findByEmail(request.email())
                .orElseThrow(() -> {
                    return new BusinessException("Email ou senha invalidos");
                });

        boolean senhaValida = passwordEncoder.matches(request.senha(), usuario.getSenha());

        if (!senhaValida) {
            throw new BusinessException("Email ou senha invalidos");
        }

        String token = jwtService.gerarToken(usuario.getEmail(), usuario.getPerfil());
        return new LoginResponse(token, usuario.getPerfil(), usuario.getEmail());
    }
}

