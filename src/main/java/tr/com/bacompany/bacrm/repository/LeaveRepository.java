package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;

import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    Optional<Leave> findByUserId(Long userId);
}
