package tr.com.bacompany.bacrm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.converter.user.UserConverter;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@Slf4j
@RequestMapping("api/user")
@Api(tags = "Users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {this.userService = userService;}

    @ApiOperation(value = "Get all users.")
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<UserDto> listUser() {
        List<User> userList = userService.findAll();
        return userList.stream().map(UserConverter::toDto).collect(Collectors.toList());
    }

    @ApiOperation(value = "Get user by id.")
    //@Secured("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDto getOne(@PathVariable(value = "id") Long id) {
        User user = userService.get(id);
        return UserConverter.toDto(user);
    }

    @ApiOperation(value = "Signup.")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto userDto) {
        User user = UserConverter.toEntity(userDto);
        user = userService.save(user);
        return UserConverter.toDto(user);
    }

}
