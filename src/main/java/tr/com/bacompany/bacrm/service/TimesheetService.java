package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.TimesheetDto;
import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface TimesheetService {
    TimesheetDto add(TimesheetDto timesheetDto);

    List<TimesheetDto> getAll();

    TimesheetDto getBy(Long timesheetId) throws ResourceNotFoundException;

    TimesheetDto update(TimesheetDto timesheetDto) throws ResourceNotFoundException;

    boolean delete(TimesheetDto timesheetDto);

}
