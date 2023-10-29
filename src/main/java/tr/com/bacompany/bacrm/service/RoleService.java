package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.entity.user.Role;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    Role save(Role role);

    List<Role> getAll();

    Role getBy(Long roleId) throws ResourceNotFoundException;

    Role update(Role role);

    boolean delete(Role role);

}
