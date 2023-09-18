package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.converter.TimesheetConverter;
import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetDto;
import tr.com.bacompany.bacrm.data.entity.timesheet.EnumTimesheetStatus;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.TimesheetRepository;
import tr.com.bacompany.bacrm.service.TimesheetService;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public List<TimesheetDto> saveTimesheet(List<TimesheetDto> timesheetDtoList) {
        timesheetDtoList.forEach(e -> e.setStatus(EnumTimesheetStatus.SAVED));
        return this.save(timesheetDtoList);
    }

    @Override
    public List<TimesheetDto> approveTimesheetByUser(List<TimesheetDto> timesheetDtoList) {
        timesheetDtoList.forEach(e -> e.setStatus(EnumTimesheetStatus.APPROVED_BY_USER));
        return this.save(timesheetDtoList);
    }

    @Override
    public List<TimesheetDto> approveTimesheetByManager(List<TimesheetDto> timesheetDtoList) {
        timesheetDtoList.forEach(e -> e.setStatus(EnumTimesheetStatus.APPROVED_BY_MANAGER));
        return this.save(timesheetDtoList);
    }

    @Override
    public List<TimesheetDto> rejectTimesheetByManager(List<TimesheetDto> timesheetDtoList) {
        timesheetDtoList.forEach(e -> e.setStatus(EnumTimesheetStatus.REJECTED));
        return this.save(timesheetDtoList);
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
    public List<TimesheetDto> update(List<TimesheetDto> timesheetDtoList) throws ResourceNotFoundException {
        List<TimesheetDto> resultTimesheetDtoList = new ArrayList<>();
        for (TimesheetDto timesheetDto : timesheetDtoList) {
            this.getBy(timesheetDto.getId());
            Timesheet newTimesheet = TimesheetConverter.toEntity(timesheetDto);
            TimesheetDto newTimesheetDto = TimesheetConverter.toDto(timesheetRepository.save(newTimesheet));
            resultTimesheetDtoList.add(newTimesheetDto);
        }
        return resultTimesheetDtoList;
    }

    @Override
    public boolean delete(TimesheetDto timesheetDto) throws ResourceNotFoundException {
        try {
            Optional<Timesheet> optTimesheet = timesheetRepository.findById(timesheetDto.getId());
            if (!optTimesheet.isPresent()) {
                throw new ResourceNotFoundException("Timesheet is not found.", "Timesheet");
            }
            Timesheet timesheet = optTimesheet.get();
            timesheetRepository.delete(timesheet);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Transactional
    private List<TimesheetDto> save(List<TimesheetDto> timesheetDtoList) {
        List<TimesheetDto> resultTimesheetDtoList = new ArrayList<>();
        for (TimesheetDto timesheetDto : timesheetDtoList) {
            Timesheet timesheet = timesheetRepository.save(TimesheetConverter.toEntity(timesheetDto));
            TimesheetDto savedTimesheet = TimesheetConverter.toDto(timesheet);
            resultTimesheetDtoList.add(savedTimesheet);
        }
        return resultTimesheetDtoList;
    }
}
