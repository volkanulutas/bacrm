package tr.com.bacompany.bacrm.data.dto.leave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveType;

@Data
public class LeaveDto {
    private Long id;

    private EnumLeaveType type;

    private EnumLeaveStatus status;

    private Long startDate;

    private Long endDate;

    private String definition;

    private boolean deleted;

    private UserDto user;

    private LeaveApproveStatusDto leaveApproveStatus;
}
