package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.data.dto.DepartmentDto;
import tr.com.bacompany.bacrm.data.entity.user.Department;

public class DepartmentConverter {
    public static Department toEntity(DepartmentDto source) {
        Department target = new Department();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setDeleted(source.isDeleted());
        return target;
    }

    public static DepartmentDto toDto(Department source) {
        if (source == null) {
            return null;
        }
        DepartmentDto target = new DepartmentDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setDeleted(source.isDeleted());
        return target;
    }
}
