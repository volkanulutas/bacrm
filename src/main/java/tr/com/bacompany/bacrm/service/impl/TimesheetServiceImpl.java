package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.timesheet.EnumTimesheetStatus;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.TimesheetRepository;
import tr.com.bacompany.bacrm.service.TimesheetService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<Timesheet> timesheetList = timesheetRepository.findAll();
        return timesheetList.stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Timesheet getById(Long timesheetId) throws ResourceNotFoundException {
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
        this.getById(timesheet.getId());
        return timesheetRepository.save(timesheet);
    }

    @Override
    public boolean delete(Long timesheetId) throws ResourceNotFoundException {
        try {
            Timesheet timesheet = this.getById(timesheetId);
            timesheet.setDeleted(true);
            this.save(timesheet);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    public Timesheet save(Timesheet timesheet) {
        return timesheetRepository.save(timesheet);
    }
}
