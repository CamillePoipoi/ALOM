package fr.alom.auth.back;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<User, Long> {

    Optional<User> findByNickname(String nickname);

    User findByNicknameAndPassword(String nickname, String password);
}
