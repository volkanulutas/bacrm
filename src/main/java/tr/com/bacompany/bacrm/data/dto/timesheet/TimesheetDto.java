package tr.com.bacompany.bacrm.data.dto.timesheet;

import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.timesheet.EnumTimesheetStatus;

import java.util.HashSet;
import java.util.Set;

@Data
public class TimesheetDto {
    private Long id;

    private long weekStartDate;

    private EnumTimesheetStatus status;

    private UserDto user;

    private Set<TimesheetItemDto> timesheetItems = new HashSet<>();

    public void addTimesheetItem(TimesheetItemDto timesheetItemDto) {
        timesheetItemDto.setTimesheet(this);
        timesheetItems.add(timesheetItemDto);
    }
}
