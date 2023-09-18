package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetItemDto;
import tr.com.bacompany.bacrm.data.entity.timesheet.TimesheetItem;

public class TimesheetItemConverter {
    public static TimesheetItem toEntity(TimesheetItemDto source) {
        TimesheetItem target = new TimesheetItem();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setAmountMin(source.getAmountMin());
        target.setTimesheet(TimesheetConverter.toEntity(source.getTimesheet()));
        target.setWork(WorkConverter.toEntity(source.getWork()));
        return target;
    }

    public static TimesheetItemDto toDto(TimesheetItem source) {
        TimesheetItemDto target = new TimesheetItemDto();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setAmountMin(source.getAmountMin());
        target.setTimesheet(TimesheetConverter.toDto(source.getTimesheet()));
        target.setWork(WorkConverter.toDto(source.getWork()));
        return target;
    }
}
