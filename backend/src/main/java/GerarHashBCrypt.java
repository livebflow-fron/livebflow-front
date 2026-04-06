import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//NAO SUBIR PRA MAIN APENAS PRA FINS DE TESTES
public class GerarHashBCrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String senha = "admin123";
        String hash = encoder.encode(senha);
        System.out.println("Senha: " + senha);
        System.out.println("Hash: " + hash);
        
        // Testa se o hash corresponde à senha
        boolean valida = encoder.matches(senha, hash);
        System.out.println("Validação: " + valida);
    }
}

