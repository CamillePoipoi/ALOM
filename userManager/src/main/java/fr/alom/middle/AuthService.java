package fr.alom.middle;

import fr.alom.back.AuthRepository;

import java.util.UUID;

public class AuthService {
    private final AuthRepository authRepository = AuthRepository.getInstance();

    public UUID authenticate(String nickname, String password) {
        if (!authRepository.userExists(nickname, password)) {
            authRepository.registerUser(nickname, password);
        }
        UUID token = generateToken();
        authRepository.registerTokenNickname(token, nickname);
        return token;
    }

    public String getNickname(UUID token) {
        String nickname = authRepository.getNickname(token);
        if (nickname == null)
            return "Aucun utilisateur lié à ce token";
        return authRepository.getNickname(token);
    }

    public UUID generateToken() {
        return UUID.randomUUID();
    }
}
