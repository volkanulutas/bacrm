package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface TimesheetService {
    List<TimesheetDto> saveTimesheet(List<TimesheetDto> timesheetDtoList);

    List<TimesheetDto> approveTimesheetByUser(List<TimesheetDto> timesheetDtoList);

    List<TimesheetDto> approveTimesheetByManager(List<TimesheetDto> timesheetDtoList);

    List<TimesheetDto> rejectTimesheetByManager(List<TimesheetDto> timesheetDtoList);

    List<TimesheetDto> getAll();

    TimesheetDto getBy(Long timesheetId) throws ResourceNotFoundException;

    List<TimesheetDto> update(List<TimesheetDto> timesheetDtoList) throws ResourceNotFoundException;

    boolean delete(TimesheetDto timesheetDto) throws ResourceNotFoundException;
}
