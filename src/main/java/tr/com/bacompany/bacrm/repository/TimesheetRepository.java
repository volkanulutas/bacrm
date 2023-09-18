package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;

public interface TimesheetRepository extends JpaRepository<Timesheet, Long> {}
