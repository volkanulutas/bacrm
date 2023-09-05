package tr.com.bacompany.bacrm.repository;

import tr.com.bacompany.bacrm.data.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String username);
}
