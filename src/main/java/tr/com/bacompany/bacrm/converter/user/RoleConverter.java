package tr.com.bacompany.bacrm.converter.user;

import tr.com.bacompany.bacrm.data.dto.user.RoleDto;
import tr.com.bacompany.bacrm.data.entity.user.Role;

public class RoleConverter {
    public static Role toEntity(RoleDto source) {
        Role target = new Role();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setCreationDate(source.getCreationDate());
        target.setDeleted(source.isDeleted());
        return target;
    }

    public static RoleDto toDto(Role source) {
        RoleDto target = new RoleDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setCreationDate(source.getCreationDate());
        target.setDeleted(source.isDeleted());
        return target;
    }
}
