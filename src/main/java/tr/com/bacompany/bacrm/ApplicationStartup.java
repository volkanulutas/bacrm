package tr.com.bacompany.bacrm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import tr.com.bacompany.bacrm.data.dto.user.RoleDto;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.service.RoleService;
import tr.com.bacompany.bacrm.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Component
public class ApplicationStartup {
    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public ApplicationStartup(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void afterStartup() {
        RoleDto roleDto = new RoleDto();
        roleDto.setName("ADMIN");
        roleDto.setDescription("ADMIN DESC");
        roleDto.setCreationDate(1L);
        roleDto = roleService.add(roleDto);
        UserDto userDto = new UserDto();
        userDto.setEmail("volkan.ulutas@bacompany.com.tr");
        userDto.setName("Volkan");
        userDto.setSurname("Ulutaş");
        userDto.setEnabled(true);
        userDto.setPassword("123");
        userDto.setProfilePicture("profile pic");
        Set<RoleDto> roleDtoSet = new HashSet<>();
        roleDtoSet.add(roleDto);
        userDto.setRoles(roleDtoSet);
        userDto = userService.save(userDto);
        System.err.println(userDto.toString());
    }
}
