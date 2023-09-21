package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;

import java.util.List;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {
    List<Timesheet> findByUserIdAndWeekStartDate(Long userId, Long weekStartDate);
}
