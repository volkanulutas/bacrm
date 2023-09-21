package tr.com.bacompany.bacrm.data.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tr.com.bacompany.bacrm.data.entity.user.User;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private long startDate;

    @Column
    private long endDate;

    @Column
    private double workloadHour;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "WORKS_USERS", joinColumns = {@JoinColumn(name = "WORK_ID")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private Set<User> users = new HashSet<>();

    public void addUser(User user){
        //user.addWork(this);
        users.add(user);
    }
    /*
    @OneToMany(mappedBy="work")
    private Set<Timesheet> timesheets;
     */
}
