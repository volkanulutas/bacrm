package tr.com.bacompany.bacrm.data.entity.timesheet;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bacompany.bacrm.data.entity.user.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TIMESHEET")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private long weekStartDate;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumTimesheetStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "timesheet", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TimesheetItem> timesheetItems = new HashSet<>();

    public void addTimesheetItem(TimesheetItem timesheetItem) {
        timesheetItem.setTimesheet(this);
        timesheetItems.add(timesheetItem);
    }
}
