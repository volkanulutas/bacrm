package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.converter.user.RoleConverter;
import tr.com.bacompany.bacrm.data.dto.user.RoleDto;
import tr.com.bacompany.bacrm.data.entity.user.Role;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.RoleRepository;
import tr.com.bacompany.bacrm.service.RoleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {this.roleRepository = roleRepository;}

    @Override
    public RoleDto add(RoleDto roleDto) {
        Role role = roleRepository.save(RoleConverter.toEntity(roleDto));
        return RoleConverter.toDto(role);
    }

    @Override
    public List<RoleDto> getAll() {
        return roleRepository.findAll().stream().map(RoleConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public RoleDto getBy(Long roleId) throws ResourceNotFoundException {
        Optional<Role> roleOpt = roleRepository.findById(roleId);
        if (!roleOpt.isPresent()) {
            throw new ResourceNotFoundException("Role", "Role");
        }
        return RoleConverter.toDto(roleOpt.get());
    }

    @Override
    public RoleDto update(RoleDto roleDto) {
        this.getBy(roleDto.getId());
        Role newRole = roleRepository.save(RoleConverter.toEntity(roleDto));
        return RoleConverter.toDto(newRole);
    }

    @Override
    public boolean delete(RoleDto roleDto) {
        try {
            roleRepository.delete(RoleConverter.toEntity(roleDto));

        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
