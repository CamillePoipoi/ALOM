package fr.alom.auth.back;

import fr.alom.auth.common.User;

import java.util.ArrayList;

public final class AuthRepository {
    private static final AuthRepository instance = new AuthRepository();

    private ArrayList<User> users = new ArrayList<User>();

    public static AuthRepository getInstance() {
        return instance;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public boolean credentialMatches(String nickname, String password) {
        // TODO : Supprimer ces deux lignes, c'est juste pour les tests
		User felix = new User("Felix", "LeChat");
        this.addUser(felix);

        for(User user : users) {
            if(nickname.equals(user.getNickname()) && (user.getPassword().equals(password))) {
                return true;
            }
        }
        return false;
    }
}
