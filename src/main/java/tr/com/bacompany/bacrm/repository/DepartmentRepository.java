package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.user.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {}
