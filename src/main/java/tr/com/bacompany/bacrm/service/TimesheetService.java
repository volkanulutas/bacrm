package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface TimesheetService {
    Timesheet saveTimesheet(Timesheet timesheet);

    Timesheet approveTimesheetByUser(Timesheet timesheet);

    Timesheet approveTimesheetByManager(Timesheet timesheet);

    Timesheet rejectTimesheetByManager(Timesheet timesheet);

    List<Timesheet> getAll();

    Timesheet getById(Long timesheetId) throws ResourceNotFoundException;

    Timesheet update(Timesheet timesheet) throws ResourceNotFoundException;

    boolean delete(Long timesheetId) throws ResourceNotFoundException;

    Timesheet getByUserIdAndWeekStartDate(Long userId, Long startDate);
}
