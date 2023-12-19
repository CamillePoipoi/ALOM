package fr.alom.back;


import java.util.HashMap;
import java.util.UUID;

public class AuthRepository {
    HashMap<UUID, String> nicknameToken = new HashMap<>();
    HashMap<String, String> nicknamePassword = new HashMap<>();
    private static AuthRepository authRepository;

    public static AuthRepository getInstance() {
        if (authRepository == null) {
            authRepository = new AuthRepository();
        }
        return authRepository;
    }

    public void registerTokenNickname(UUID token, String nickname) {
        nicknameToken.put(token, nickname);
    }

    public String getNickname(UUID token) {
        return nicknameToken.get(token);
    }

    public void registerUser(String nickname, String password) {
        nicknamePassword.put(nickname, password);
    }

    public boolean userExists(String nickname, String password) {
        return password.equals(nicknamePassword.get(nickname));
    }
}
