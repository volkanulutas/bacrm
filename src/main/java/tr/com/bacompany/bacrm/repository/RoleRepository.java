package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.user.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
