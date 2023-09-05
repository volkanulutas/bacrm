package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.converter.TimesheetConverter;
import tr.com.bacompany.bacrm.data.dto.TimesheetDto;
import tr.com.bacompany.bacrm.data.entity.Timesheet;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.TimesheetRepository;
import tr.com.bacompany.bacrm.service.TimesheetService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "timesheetService")
public class TimesheetServiceImpl implements TimesheetService {
    private final TimesheetRepository timesheetRepository;

    @Autowired
    public TimesheetServiceImpl(TimesheetRepository timesheetRepository) {
        this.timesheetRepository = timesheetRepository;
    }

    @Override
    public TimesheetDto add(TimesheetDto timesheetDto) {
        Timesheet timesheet = timesheetRepository.save(TimesheetConverter.toEntity(timesheetDto));
        return TimesheetConverter.toDto(timesheet);
    }

    @Override
    public List<TimesheetDto> getAll() {
        List<Timesheet> timesheets = timesheetRepository.findAll();
        return timesheets.stream().map(TimesheetConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public TimesheetDto getBy(Long timesheetId) throws ResourceNotFoundException {
        Optional<Timesheet> timesheetOpt = timesheetRepository.findById(timesheetId);
        if (!timesheetOpt.isPresent()) {
            throw new ResourceNotFoundException("Timesheet", "Timesheet");
        }
        return TimesheetConverter.toDto(timesheetOpt.get());
    }

    @Override
    public TimesheetDto update(TimesheetDto workDto) throws ResourceNotFoundException {
        this.getBy(workDto.getId());
        Timesheet newTimesheet = TimesheetConverter.toEntity(workDto);
        return TimesheetConverter.toDto(timesheetRepository.save(newTimesheet));
    }

    @Override
    public boolean delete(TimesheetDto timesheetDto) {
        try {
            Timesheet timesheet = TimesheetConverter.toEntity(timesheetDto);
            timesheetRepository.delete(timesheet);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
