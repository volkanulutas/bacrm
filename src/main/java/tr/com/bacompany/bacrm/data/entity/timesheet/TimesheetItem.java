package tr.com.bacompany.bacrm.data.entity.timesheet;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bacompany.bacrm.data.entity.Work;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TIMESHEET_ITEM")
public class TimesheetItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private long date;

    @Column
    private double amountMin;

    @ManyToOne
    @JoinColumn(name = "timesheet_id")
    private Timesheet timesheet;

    @ManyToOne
    @JoinColumn(name = "work_id", nullable = false)
    private Work work;
}
