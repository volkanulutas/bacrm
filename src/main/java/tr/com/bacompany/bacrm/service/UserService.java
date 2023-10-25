package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User get(Long id) throws ResourceNotFoundException;

    boolean isPresent(Long id);

    User save(User user);

    List<User> getAll();
}
