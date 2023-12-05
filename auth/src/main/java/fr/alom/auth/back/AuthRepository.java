package fr.alom.auth.back;

import fr.alom.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {


    User findByNicknameAndPassword(String nickname, String password);
}
