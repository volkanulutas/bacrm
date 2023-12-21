package tr.com.bacompany.bacrm.data.dto.leave;

import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;

@Data
public class LeaveDto {
    private Long id;

    private LeaveTypeDto type;

    private EnumLeaveStatus status;

    private Long startDate;

    private Long endDate;

    private String definition;

    private boolean deleted;

    private UserDto user;

    private String rejectMessage;
}
