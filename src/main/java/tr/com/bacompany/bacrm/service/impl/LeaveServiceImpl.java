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

@Slf4j
@Service(value = "leaveService")
public class LeaveServiceImpl implements LeaveService {
    private final LeaveRepository leaveRepository;

    @Autowired
    public LeaveServiceImpl(LeaveRepository leaveRepository) {this.leaveRepository = leaveRepository;}

    @Override
    public Leave add(Leave leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public List<Leave> getAll() {
        return leaveRepository.findAll();
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
            Optional<Leave> optLeave = leaveRepository.findById(id);
            if (optLeave.isEmpty()) {
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
