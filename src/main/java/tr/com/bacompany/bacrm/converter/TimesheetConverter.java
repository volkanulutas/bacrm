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
        List<TimesheetItem> sourceTimesheetItems =
                source.getTimesheetItems().stream().map(e -> TimesheetItemConverter.toEntity(e)).collect(Collectors.toList());
        for (TimesheetItem sourceTimesheetItem : sourceTimesheetItems) {
            target.addTimesheetItem(sourceTimesheetItem);
        }
        return target;
    }

    public static TimesheetDto toDto(Timesheet source) {
        TimesheetDto target = new TimesheetDto();
        target.setId(source.getId());
        target.setWeekStartDate(source.getWeekStartDate());
        target.setStatus(source.getStatus());
        target.setUser(UserConverter.toDto(source.getUser()));
        List<TimesheetItemDto> sourceTimesheetItems =
                source.getTimesheetItems().stream().map(e -> TimesheetItemConverter.toDto(e)).collect(Collectors.toList());
        for (TimesheetItemDto sourceTimesheetItem : sourceTimesheetItems) {
            target.addTimesheetItem(sourceTimesheetItem);
        }
        return target;
    }
}
