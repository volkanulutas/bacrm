package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto get(Long id) throws ResourceNotFoundException;

    boolean isPresent(Long id);

    UserDto save(UserDto user);
}
