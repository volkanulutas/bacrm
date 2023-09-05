package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.user.RoleDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface RoleService {
    RoleDto add(RoleDto roleDto);

    List<RoleDto> getAll();

    RoleDto getBy(Long roleId) throws ResourceNotFoundException;

    RoleDto update(RoleDto roleDto);

    boolean delete(RoleDto roleDto);

}
