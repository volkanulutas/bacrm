package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.user.Department;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.DepartmentRepository;
import tr.com.bacompany.bacrm.service.DepartmentService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {this.departmentRepository = departmentRepository;}

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> getAll() {
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList.stream().filter(e-> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Department getById(Long departmentId) throws ResourceNotFoundException {
        Optional<Department> optionDepartment = departmentRepository.findById(departmentId);
        if (!optionDepartment.isPresent()) {
            throw new ResourceNotFoundException("Department is not found", "Department");
        }
        return optionDepartment.get();
    }

    @Override
    public Department update(Department department) {
        this.getById(department.getId());
        return departmentRepository.save(department);
    }

    @Override
    public boolean delete(Long id) {
        try {
            Department department = this.getById(id);
            department.setDeleted(true);
            this.save(department);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
