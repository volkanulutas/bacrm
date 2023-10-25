package tr.com.bacompany.bacrm.data.dto.timesheet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bacompany.bacrm.data.dto.WorkDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimesheetItemDto {
    private Long id;

    private long date;

    private double amountMin;

    private TimesheetDto timesheet;

    private WorkDto work;
}
