package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.dto.leave.LeaveDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface LeaveService {
    LeaveDto add(LeaveDto leaveDto);

    List<LeaveDto> getAll();

    LeaveDto getByUserId(Long userId) throws ResourceNotFoundException;

    boolean delete(Long id) throws ResourceNotFoundException;
}
