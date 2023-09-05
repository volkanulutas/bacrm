package tr.com.bacompany.bacrm.repository;

import tr.com.bacompany.bacrm.data.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {}
