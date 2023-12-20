package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.converter.user.UserConverter;
import tr.com.bacompany.bacrm.data.dto.leave.LeaveDto;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;

public class LeaveConverter {
    public static Leave toEntity(LeaveDto source) {
        Leave target = new Leave();
        target.setId(source.getId());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setDefinition(source.getDefinition());
        target.setDeleted(source.isDeleted());
        // NOTE: user should set after call
        target.setLeaveApproveStatus(LeaveApproveStatusConverter.toEntity(source.getLeaveApproveStatus()));
        return target;
    }

    public static LeaveDto toDto(Leave source) {
        LeaveDto target = new LeaveDto();
        target.setId(source.getId());
        target.setType(source.getType());
        target.setStatus(source.getStatus());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setDefinition(source.getDefinition());
        target.setDeleted(source.isDeleted());
        target.setUserId(source.getUser().getId());
        target.setLeaveApproveStatus(LeaveApproveStatusConverter.toDto(source.getLeaveApproveStatus()));
        return target;
    }
}
