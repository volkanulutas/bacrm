package tr.com.bacompany.bacrm.data.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bacompany.bacrm.data.dto.DepartmentDto;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String middleName;

    private String surname;

    private boolean enabled;

    private byte[] profilePicture;

    private String cellPhone;

    private String internalPhone;

    private DepartmentDto department;

    private String title;

    private long birthdate;

    private long startDate;

    private String address;

    private boolean deleted;

    private Set<RoleDto> roles = new HashSet<>();
    // private Set<WorkDto> works = new HashSet<>();
    // private Set<UserDto> managers = new HashSet<>();
    // private Set<TimesheetDto> timesheets = new HashSet<>();
    // private Set<LeaveDto> leaves = new HashSet<>();
}
