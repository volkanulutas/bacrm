package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.converter.user.UserConverter;
import tr.com.bacompany.bacrm.data.dto.leave.LeaveDto;
import tr.com.bacompany.bacrm.data.dto.leave.LeaveTypeDto;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveType;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;

public class LeaveConverter {
    public static Leave toEntity(LeaveDto source) {
        Leave target = new Leave();
        target.setId(source.getId());
        target.setType(EnumLeaveType.valueOf(source.getType().getValue()));
        target.setStatus(source.getStatus());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setDefinition(source.getDefinition());
        target.setDeleted(source.isDeleted());
        target.setUser(UserConverter.toEntity(source.getUser()));
        target.setRejectMessage(source.getRejectMessage());
        return target;
    }

    public static LeaveDto toDto(Leave source) {
        LeaveDto target = new LeaveDto();
        target.setId(source.getId());
        target.setType(new LeaveTypeDto(source.getType().getLabel(), source.getType().name()));
        target.setStatus(source.getStatus());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setDefinition(source.getDefinition());
        target.setDeleted(source.isDeleted());
        target.setUser(UserConverter.toDto(source.getUser()));
        target.setRejectMessage(source.getRejectMessage());
        return target;
    }
}
