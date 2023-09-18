package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.converter.LeaveConverter;
import tr.com.bacompany.bacrm.data.dto.leave.LeaveDto;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.LeaveRepository;
import tr.com.bacompany.bacrm.service.LeaveService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "leaveService")
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository) {this.leaveRepository = leaveRepository;}

    @Override
    public LeaveDto add(LeaveDto leaveDto) {
        Leave leave = leaveRepository.save(LeaveConverter.toEntity(leaveDto));
        return LeaveConverter.toDto(leave);
    }

    @Override
    public List<LeaveDto> getAll() {
        List<Leave> leaves = leaveRepository.findAll();
        return leaves.stream().map(LeaveConverter::toDto).collect(Collectors.toList());
    }

    @Override
    public LeaveDto getByUserId(Long userId) throws ResourceNotFoundException {
        Optional<Leave> optLeave = leaveRepository.findByUserId(userId);
        if (!optLeave.isPresent()) {
            throw new ResourceNotFoundException("Leave is not found.", "Leave");
        }
        Leave leave = optLeave.get();
        return LeaveConverter.toDto(leave);
    }

    @Override
    public boolean delete(Long id) throws ResourceNotFoundException {
        try {
            Optional<Leave> optLeave = leaveRepository.findById(id);
            if (!optLeave.isPresent()) {
                throw new ResourceNotFoundException("Leave is not found.", "Leave");
            }
            Leave leave = optLeave.get();
            leaveRepository.delete(leave);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
