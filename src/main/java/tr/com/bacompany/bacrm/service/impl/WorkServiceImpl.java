package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.WorkRepository;
import tr.com.bacompany.bacrm.service.WorkService;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service(value = "workService")
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;

    @Autowired
    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public Work save(Work work) {
        return workRepository.save(work);
    }

    @Override
    public List<Work> getAll() {
        return workRepository.findAll();
    }

    @Override
    public Work getBy(Long workId) throws ResourceNotFoundException {
        Optional<Work> workOpt = workRepository.findById(workId);
        if (!workOpt.isPresent()) {
            throw new ResourceNotFoundException("Work", "Work");
        }
        return workOpt.get();
    }

    @Override
    public Work update(Work work) throws ResourceNotFoundException {
        this.getBy(work.getId());
        return workRepository.save(work);
    }

    @Override
    public boolean delete(Work work) {
        try {
            workRepository.delete(work);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
