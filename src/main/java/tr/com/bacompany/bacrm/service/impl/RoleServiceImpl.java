package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.user.Role;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.RoleRepository;
import tr.com.bacompany.bacrm.service.RoleService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {this.roleRepository = roleRepository;}

    @Override
    public Role add(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role getBy(Long roleId) throws ResourceNotFoundException {
        Optional<Role> roleOpt = roleRepository.findById(roleId);
        if (!roleOpt.isPresent()) {
            throw new ResourceNotFoundException("Role", "Role");
        }
        return roleOpt.get();
    }

    @Override
    public Role update(Role role) {
        this.getBy(role.getId());
        return roleRepository.save(role);
    }

    @Override
    public boolean delete(Role role) {
        try {
            roleRepository.delete(role);

        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
