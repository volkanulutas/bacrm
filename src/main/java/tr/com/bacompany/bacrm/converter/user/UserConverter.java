package tr.com.bacompany.bacrm.converter.user;

import tr.com.bacompany.bacrm.converter.DepartmentConverter;
import tr.com.bacompany.bacrm.data.dto.user.RoleDto;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.user.User;

import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {
    public static User toEntity(UserDto source) {
        User target = new User();
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setName(source.getName());
        target.setMiddleName(source.getMiddleName());
        target.setSurname(source.getSurname());
        target.setEnabled(source.isEnabled());
        target.setProfilePicture(source.getProfilePicture());
        target.setTitle(source.getTitle());
        target.setBirthdate(source.getBirthdate());
        target.setStartDate(source.getStartDate());
        target.setCellPhone(source.getCellPhone());
        target.setInternalPhone(source.getInternalPhone());
        target.setAddress(source.getAddress());
        target.setDepartment(DepartmentConverter.toEntity(source.getDepartment()));
        source.getRoles().forEach(e -> target.addRole(RoleConverter.toEntity(e)));
        target.setDeleted(source.isDeleted());
        return target;
    }

    public static UserDto toDto(User source) {
        UserDto target = new UserDto();
        target.setId(source.getId());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setName(source.getName());
        target.setMiddleName(source.getMiddleName());
        target.setSurname(source.getSurname());
        target.setEnabled(source.isEnabled());
        target.setProfilePicture(source.getProfilePicture());
        target.setTitle(source.getTitle());
        target.setBirthdate(source.getBirthdate());
        target.setStartDate(source.getStartDate());
        target.setCellPhone(source.getCellPhone());
        target.setInternalPhone(source.getInternalPhone());
        target.setAddress(source.getAddress());
        target.setDepartment(DepartmentConverter.toDto(source.getDepartment()));
        Set<RoleDto> roles = source.getRoles().stream().map(RoleConverter::toDto).collect(Collectors.toSet());
        target.setRoles(roles);
        target.setDeleted(source.isDeleted());
        return target;
    }
}
