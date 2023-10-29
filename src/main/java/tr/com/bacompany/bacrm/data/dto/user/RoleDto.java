package tr.com.bacompany.bacrm.data.dto.user;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDto {
    private Long id;

    private String name;

    private String description;

    private Long creationDate;

    private boolean deleted;
}
