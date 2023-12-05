package fr.alom.auth.middle;

import fr.alom.auth.back.AuthRepository;
import fr.alom.auth.model.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthRepository authRepository;
    @Autowired
    public AuthService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    public String authenticate(String nickname, String password) throws Exception {
        if(!isValidCredentials(nickname, password)) {
            throw new Exception();
        }
        return generateToken(nickname);
    }

    private boolean isValidCredentials(String nickname, String password) {
        User user = authRepository.findByNicknameAndPassword(nickname, password);
        return user != null;
    }

    public static String generateToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .signWith(SignatureAlgorithm.HS256, "LA-MIAGE-CEST-LE-PARTAGE")
                .compact();
    }
}