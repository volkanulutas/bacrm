package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.data.dto.leave.LeaveApproveStatusDto;
import tr.com.bacompany.bacrm.data.entity.leave.LeaveApproveStatus;

public class LeaveApproveStatusConverter {
    public static LeaveApproveStatus toEntity(LeaveApproveStatusDto source) {
        LeaveApproveStatus target = new LeaveApproveStatus();
        target.setId(source.getId());
        target.setStatus(source.getStatus());
        target.setLeave(source.getLeave());
        return target;
    }

    public static LeaveApproveStatusDto toDto(LeaveApproveStatus source) {
        LeaveApproveStatusDto target = new LeaveApproveStatusDto();
        target.setId(source.getId());
        target.setStatus(source.getStatus());
        target.setLeave(source.getLeave());
        return target;
    }
}
