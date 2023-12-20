package tr.com.bacompany.bacrm.data.entity.leave;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bacompany.bacrm.data.entity.user.User;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LEAVE")
public class Leave {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumLeaveType type;

    @Column
    @Enumerated(EnumType.STRING)
    private EnumLeaveStatus status;

    private long startDate;

    private long endDate;

    private String definition;

    private boolean deleted;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String rejectMessage;
}
