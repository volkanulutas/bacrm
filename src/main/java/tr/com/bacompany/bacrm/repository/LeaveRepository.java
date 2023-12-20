package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave, Long> {
    List<Leave> findByUserId(Long userId);

    List<Leave> findByStatus(EnumLeaveStatus enumLeaveStatus);
}
