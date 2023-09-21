package tr.com.bacompany.bacrm.data.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column
    private String email;

    @Column
    @JsonIgnore
    private String password;

    @Column
    private String name;

    @Column
    private String middleName;

    @Column
    private String surname;

    @Column
    private boolean enabled;

    @Column
    private String profilePicture;

    @ManyToMany(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "USERS_ROLES", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "USERS_WORKS", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "WORK_ID")})
    private Set<Work> works = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER/*, cascade = CascadeType.ALL*/)
    @JoinTable(name = "USERS_MANAGERS", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "WORK_ID")})
    private Set<User> managers = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Timesheet> timesheets = new HashSet<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private Set<Leave> leaves = new HashSet<>();

    public void addRole(Role role) {
        this.getRoles().add(role);
    }
}
