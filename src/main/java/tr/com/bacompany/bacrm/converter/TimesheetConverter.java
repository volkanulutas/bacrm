package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.converter.user.UserConverter;
import tr.com.bacompany.bacrm.data.dto.TimesheetDto;
import tr.com.bacompany.bacrm.data.entity.Timesheet;

public class TimesheetConverter {

    public static Timesheet toEntity(TimesheetDto source) {
        Timesheet target = new Timesheet();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setAmountMin(source.getAmountMin());
        target.setSubmitted(source.isSubmitted());
        target.setSaved(source.isSaved());
        target.setApproved(source.isApproved());
        target.setRejected(source.isRejected());
        target.setWork(WorkConverter.toEntity(source.getWork()));
        target.setUser(UserConverter.toEntity(source.getUser()));
        return target;
    }

    public static TimesheetDto toDto(Timesheet source) {
        TimesheetDto target = new TimesheetDto();
        target.setId(source.getId());
        target.setDate(source.getDate());
        target.setAmountMin(source.getAmountMin());
        target.setSubmitted(source.isSubmitted());
        target.setSaved(source.isSaved());
        target.setApproved(source.isApproved());
        target.setRejected(source.isRejected());
        target.setWork(WorkConverter.toDto(source.getWork()));
        target.setUser(UserConverter.toDto(source.getUser()));
        return target;
    }
}
