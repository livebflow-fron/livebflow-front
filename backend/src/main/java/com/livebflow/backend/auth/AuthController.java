package com.livebflow.backend.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Endpoint responsável por validar credenciais e devolver o Token JWT.
     * Adaptado para validar no banco de dados para os testes.
     */
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest authParams) {
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(authParams.email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuarioDb = usuarioOpt.get();
            // Verifica se a senha confere batendo o hash utilizando nossa classe de MVP
            boolean senhaValida = GerarHashBCrypt.verificar(authParams.senha, usuarioDb.getSenha());
            
            if (senhaValida) {
                String fakeJwtToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJ1c3VhcmlvIiwibmFtZSI6IkxpdmViIEZsb3cifQ.Assinatura";
                return ResponseEntity.ok("{\"token\": \"" + fakeJwtToken + "\", \"status\": \"Autenticado\"}");
            }
        }
        
        return ResponseEntity.status(401).body("{\"erro\": \"Não autorizado, verifique suas credenciais\"}");
    }

    /**
     * Endpoint responsável por cadastrar um novo usuário criptografando a senha em Hash.
     * Registro direto sem confirmação de e-mail (para fins de teste homologação/produção).
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest newUser) {
        
        if (usuarioRepository.findByEmail(newUser.email).isPresent()) {
            return ResponseEntity.status(400).body("{\"erro\": \"Usuário já cadastrado\"}");
        }

        String hashDb = GerarHashBCrypt.gerar(newUser.senha);
        
        Usuario usuario = new Usuario();
        usuario.setEmail(newUser.email);
        usuario.setSenha(hashDb);
        usuario.setPerfil("CONSULTOR");
        
        usuarioRepository.save(usuario);
        
        System.out.println("Gravando no Banco -> Usuário: " + newUser.email + " | Hash: " + hashDb);
        return ResponseEntity.ok("{\"status\": \"Usuário cadastrado com sucesso e senha encriptada!\"}");
    }

    // Classe auxiliar simplificada para mapear o JSON de entrada
    public static class LoginRequest {
        public String email;
        public String senha;
    }
}
