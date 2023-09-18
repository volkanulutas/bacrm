package tr.com.bacompany.bacrm.data.dto.leave;

import lombok.Data;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;

import java.io.Serializable;

@Data
public class LeaveApproveStatusDto implements Serializable {
    private Long id;
    //TODO: many to many
    // List<User>

    private Leave leave;

    private EnumLeaveStatus status;
}
