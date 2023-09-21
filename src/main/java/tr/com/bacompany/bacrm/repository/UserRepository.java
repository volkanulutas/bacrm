package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
