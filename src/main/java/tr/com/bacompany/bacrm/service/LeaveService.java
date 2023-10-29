package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface LeaveService {
    Leave save(Leave leave);

    List<Leave> getAll();

    Leave getByUserId(Long userId) throws ResourceNotFoundException;

    boolean delete(Long id) throws ResourceNotFoundException;
}
