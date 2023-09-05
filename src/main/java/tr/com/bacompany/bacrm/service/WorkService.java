package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface WorkService {
    WorkDto add(WorkDto workDto);

    List<WorkDto> getAll();

    WorkDto getBy(Long workId) throws ResourceNotFoundException;

    WorkDto update(WorkDto workDto);

    boolean delete(WorkDto workDto);

}
