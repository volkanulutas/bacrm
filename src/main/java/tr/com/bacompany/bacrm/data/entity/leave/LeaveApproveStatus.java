package tr.com.bacompany.bacrm.data.entity.leave;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LEAVE_APPROVE_STATUS")
public class LeaveApproveStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    // TODO: many to many
    // List<User>

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "leave_id", referencedColumnName = "id")
    private Leave leave;

    private EnumLeaveStatus status;
}
