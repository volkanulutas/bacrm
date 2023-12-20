package tr.com.bacompany.bacrm.data.dto.leave;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;

@Data
public class LeaveApproveStatusDto {
    private Long id;

    private UserDto manager;

    @JsonIgnore
    private Leave leave;

    private EnumLeaveStatus status;
}
