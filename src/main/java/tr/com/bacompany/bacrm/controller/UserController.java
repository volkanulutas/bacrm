package tr.com.bacompany.bacrm.controller;

import io.swagger.annotations.ApiOperation;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return userService.findAll();
    }

    @ApiOperation(value = "Get user by id.")
    //@Secured("ROLE_USER")
    @PreAuthorize("hasRole('USER')")
    ////@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public UserDto getOne(@PathVariable(value = "id") Long id) {
        return userService.get(id);
    }

    @ApiOperation(value = "Signup.")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto user) {
        return userService.save(user);
    }

}
