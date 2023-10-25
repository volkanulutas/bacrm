package tr.com.bacompany.bacrm.data.dto.leave;

import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveType;

@Data
public class LeaveDto {
    private Long id;

    private EnumLeaveType type;

    private EnumLeaveStatus status;

    private long startDate;

    private long endDate;

    private String definition;

    private boolean deleted;

    private UserDto user;

    private LeaveApproveStatusDto leaveApproveStatus;
}
