package tr.com.bacompany.bacrm.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Table(name = "WORK")
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String definition;

    @Column
    private long planningDate;

    @Column
    private long startDate;

    @Column
    private long endDate;

    @Column
    private float workloadHour;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "WORKS_USERS", joinColumns = {@JoinColumn(name = "WORK_ID")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private Set<User> users = new HashSet<>();

    public void addUser(User user) {
        //user.addWork(this);
        users.add(user);
    }
    /*
    @OneToMany(mappedBy="work")
    private Set<Timesheet> timesheets;
     */
}
