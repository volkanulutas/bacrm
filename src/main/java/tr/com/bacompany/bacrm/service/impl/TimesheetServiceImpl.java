package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.timesheet.EnumTimesheetStatus;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.TimesheetRepository;
import tr.com.bacompany.bacrm.service.TimesheetService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service(value = "timesheetService")
public class TimesheetServiceImpl implements TimesheetService {
    private final TimesheetRepository timesheetRepository;

    @Autowired
    public TimesheetServiceImpl(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @Override
    public Timesheet saveTimesheet(Timesheet timesheet) {
        timesheet.setStatus(EnumTimesheetStatus.SAVED);
        return this.save(timesheet);
    }

    @Override
    public Timesheet approveTimesheetByUser(Timesheet timesheet) {
        timesheet.setStatus(EnumTimesheetStatus.APPROVED_BY_USER);
        return this.save(timesheet);
    }

    @Override
    public Timesheet approveTimesheetByManager(Timesheet timesheet) {
        timesheet.setStatus(EnumTimesheetStatus.REJECTED);
        return this.save(timesheet);
    }

    @Override
    public Timesheet rejectTimesheetByManager(Timesheet timesheet) {
        timesheet.setStatus(EnumTimesheetStatus.REJECTED);
        return this.save(timesheet);
    }

    @Override
    public List<Timesheet> getAll() {
        return timesheetRepository.findAll();
    }

    @Override
    public Timesheet getBy(Long timesheetId) throws ResourceNotFoundException {
        Optional<Timesheet> timesheetOpt = timesheetRepository.findById(timesheetId);
        if (!timesheetOpt.isPresent()) {
            throw new ResourceNotFoundException("Timesheet", "Timesheet");
        }
        return timesheetOpt.get();
    }

    @Override
    public Timesheet getByUserIdAndWeekStartDate(Long userId, Long weekStartDate) throws ResourceNotFoundException {
        List<Timesheet> timesheetList = timesheetRepository.findByUserIdAndWeekStartDate(userId, weekStartDate);
        if (timesheetList.isEmpty()) {
            throw new ResourceNotFoundException("Timesheet", "Timesheet");
        }
        return timesheetList.get(0);
    }

    @Override
    public Timesheet update(Timesheet timesheet) throws ResourceNotFoundException {
        this.getBy(timesheet.getId());
        return timesheetRepository.save(timesheet);
    }

    @Override
    public boolean delete(Timesheet timesheet) throws ResourceNotFoundException {
        try {
            Optional<Timesheet> optTimesheet = timesheetRepository.findById(timesheet.getId());
            if (!optTimesheet.isPresent()) {
                throw new ResourceNotFoundException("Timesheet is not found.", "Timesheet");
            }
            timesheet = optTimesheet.get();
            timesheetRepository.delete(timesheet);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }


    public Timesheet save(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }
}
