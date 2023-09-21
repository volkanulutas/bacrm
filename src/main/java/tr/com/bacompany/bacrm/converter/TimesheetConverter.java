package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.converter.user.UserConverter;
import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetDto;
import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetItemDto;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.entity.timesheet.TimesheetItem;

import java.util.List;
import java.util.stream.Collectors;

public class TimesheetConverter {
    public static Timesheet toEntity(TimesheetDto source) {
        Timesheet target = new Timesheet();
        target.setId(source.getId());
        target.setWeekStartDate(source.getWeekStartDate());
        target.setStatus(source.getStatus());
        target.setUser(UserConverter.toEntity(source.getUser()));
        return target;
    }

    public static TimesheetDto toDto(Timesheet source) {
        TimesheetDto target = new TimesheetDto();
        target.setId(source.getId());
        target.setWeekStartDate(source.getWeekStartDate());
        target.setStatus(source.getStatus());
        target.setUser(UserConverter.toDto(source.getUser()));
        return target;
    }
}
