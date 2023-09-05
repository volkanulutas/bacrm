package tr.com.bacompany.bacrm.data.dto;

import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.entity.user.User;

import java.io.Serializable;

@Data
public class TimesheetDto implements Serializable {
    private Long id;

    private long date;

    private double amountMin;

    private boolean isSubmitted;

    private boolean isSaved;

    private boolean isApproved;

    boolean isRejected;

    private UserDto user;

    private WorkDto work;
}
