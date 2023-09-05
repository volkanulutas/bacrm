package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.converter.WorkConverter;
import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.WorkRepository;
import tr.com.bacompany.bacrm.service.WorkService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "workService")
public class WorkServiceImpl implements WorkService {
    private final WorkRepository workRepository;

    @Autowired
    public WorkServiceImpl(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    @Override
    public WorkDto add(WorkDto workDto) {
        Work work = workRepository.save(WorkConverter.toEntity(workDto));
        return WorkConverter.toDto(work);
    }

    @Override
    public List<WorkDto> getAll() {
        List<Work> works = workRepository.findAll();
        return works.stream().map(WorkConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public WorkDto getBy(Long workId) throws ResourceNotFoundException {
        Optional<Work> workOpt = workRepository.findById(workId);
        if (!workOpt.isPresent()) {
            throw new ResourceNotFoundException("Work", "Work");
        }
        return WorkConverter.toDto(workOpt.get());
    }

    @Override
    public WorkDto update(WorkDto workDto) throws ResourceNotFoundException {
        this.getBy(workDto.getId());
        Work newWork = WorkConverter.toEntity(workDto);
        newWork = workRepository.save(newWork);
        return WorkConverter.toDto(newWork);
    }

    @Override
    public boolean delete(WorkDto work) {
        try {
            workRepository.delete(WorkConverter.toEntity(work));
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
