package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface WorkService {
    Work save(Work work);

    List<Work> getAll();

    Work getBy(Long workId) throws ResourceNotFoundException;

    Work update(Work work);

    boolean delete(Work work);

}
