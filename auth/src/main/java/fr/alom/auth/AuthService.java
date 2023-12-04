package fr.alom.auth;

import fr.alom.auth.back.AuthRepository;
import fr.alom.auth.back.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;

@Service
public class AuthService {

    @Autowired
    private AuthRepository authRepository;

    public String authenticate(String nickname, String password) throws Exception {
        // Vérification du couple nickname + password
        if (isValidCredentials(nickname, password)) {
            // Génération du token
            return generateToken(nickname);
        } else {
            // Retourner une chaîne indiquant l'échec de l'authentification
            throw new Exception("Le mot de passe ou le nickname n'est pas le bon");
        }
    }

    private boolean isValidCredentials(String nickname, String password) {
        // Recherche un utilisateur par le nickname et le mot de passe
        User user = authRepository.findByNicknameAndPassword(nickname, password);

        // Vérifie si l'utilisateur est trouvé
        return user != null;
    }

    public static String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .signWith(SignatureAlgorithm.HS256, "LA-MIAGE-CEST-LE-PARTAGE")
                .compact();
    }
}
