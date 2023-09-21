package tr.com.bacompany.bacrm.data.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
