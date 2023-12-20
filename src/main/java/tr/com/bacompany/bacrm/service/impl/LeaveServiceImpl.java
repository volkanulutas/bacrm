package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveStatus;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.LeaveRepository;
import tr.com.bacompany.bacrm.service.LeaveService;

import java.util.ArrayList;
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
    public List<Leave> getByUserId(Long userId) throws ResourceNotFoundException {
        List<Leave> leaveList = leaveRepository.findByUserId(userId);
        if (leaveList.isEmpty()) {
            throw new ResourceNotFoundException("Leave is not found.", "Leave");
        }
        return leaveList;
    }

    @Override
    public boolean delete(Long id) throws ResourceNotFoundException {
        try {
            Leave leave = this.getById(id);
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

    @Override
    public List<Leave> getApproveLeave(Long managerId) {
        List<Leave> leaveList = leaveRepository.findByStatus(EnumLeaveStatus.WAITING);
        List<Leave> result = new ArrayList<>();
        for (Leave leave : leaveList) {
            List<User> collect = leave.getUser().getManagers().stream().filter(e -> e.getId().equals(managerId)).collect(Collectors.toList());
            if (!collect.isEmpty()) {
                result.add(leave);
            }
        }
        return result;
    }
}
