package tr.com.bacompany.bacrm.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.user.User;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDto {
    private Long id;

    private String name;

    private String description;

    private Set<UserDto> users = new HashSet<>();

    private boolean deleted;
}
