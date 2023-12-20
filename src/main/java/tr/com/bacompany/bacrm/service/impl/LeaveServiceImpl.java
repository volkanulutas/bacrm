package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public Leave save(Leave leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public List<Leave> getAll() {
        List<Leave> leaveList = leaveRepository.findAll();
        return leaveList.stream().filter(e -> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Leave getByUserId(Long userId) throws ResourceNotFoundException {
        Optional<Leave> optLeave = leaveRepository.findByUserId(userId);
        if (!optLeave.isPresent()) {
            throw new ResourceNotFoundException("Leave is not found.", "Leave");
        }
        return optLeave.get();
    }

    @Override
    public boolean delete(Long id) throws ResourceNotFoundException {
        try {
            Leave leave = this.getByUserId(id);
            leave.setDeleted(true);
            this.save(leave);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    @Override
    public Leave getById(Long id) {
        Optional<Leave> optLeave = leaveRepository.findById(id);
        if (!optLeave.isPresent()) {
            throw new ResourceNotFoundException("Leave is not found.", "Leave");
        }
        return optLeave.get();
    }
}
