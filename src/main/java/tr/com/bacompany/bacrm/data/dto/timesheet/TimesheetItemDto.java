package tr.com.bacompany.bacrm.data.dto.timesheet;

import lombok.Data;
import tr.com.bacompany.bacrm.data.dto.WorkDto;

import java.io.Serializable;

@Data
public class TimesheetItemDto implements Serializable {
    private Long id;

    private long date;

    private double amountMin;

    private TimesheetDto timesheet;

    private WorkDto work;
}
