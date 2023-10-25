package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.user.Department;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.DepartmentRepository;
import tr.com.bacompany.bacrm.service.DepartmentService;

import java.util.List;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {this.departmentRepository = departmentRepository;}

    @Override
    public Department add(Department role) {
        return null;
    }

    @Override
    public List<Department> getAll() {
        return null;
    }

    @Override
    public Department getBy(Long departmentId) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public Department update(Department department) {
        return null;
    }

    @Override
    public boolean delete(Department role) {
        return false;
    }
}
