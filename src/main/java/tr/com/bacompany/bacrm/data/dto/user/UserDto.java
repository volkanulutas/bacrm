package tr.com.bacompany.bacrm.data.dto.user;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {
    private Long id;

    private String email;

    private String password;

    private String name;

    private String middleName;

    private String surname;

    private boolean enabled;

    private String profilePicture;

    private Set<RoleDto> roles = new HashSet<>();
}
