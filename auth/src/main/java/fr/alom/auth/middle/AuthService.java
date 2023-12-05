package fr.alom.auth.middle;

import fr.alom.auth.back.AuthRepository;

import java.util.UUID;

public class AuthService {

    private final AuthRepository authRepository = AuthRepository.getInstance();

    public String authenticate(String nickname, String password) throws Exception {
        if(!isValidCredentials(nickname, password)) {
            throw new Exception("CREDENTIALS INVALIDE APPAREMMENT !");
        }
        return generateToken();
    }

    private boolean isValidCredentials(String nickname, String password) {
        return authRepository.credentialMatches(nickname, password);
    }

    public static String generateToken() {
        return UUID.randomUUID().toString();
    }
}