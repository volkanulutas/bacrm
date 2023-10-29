package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.entity.user.Department;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface DepartmentService {
    Department save(Department role);

    List<Department> getAll();

    Department getById(Long departmentId) throws ResourceNotFoundException;

    Department update(Department department);

    boolean delete(Long id);
}
