package tr.com.bacompany.bacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.converter.LeaveConverter;
import tr.com.bacompany.bacrm.data.dto.leave.LeaveDto;
import tr.com.bacompany.bacrm.data.dto.leave.LeaveTypeDto;
import tr.com.bacompany.bacrm.data.entity.leave.EnumLeaveType;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.LeaveService;
import tr.com.bacompany.bacrm.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = {"*"})
@RequestMapping("/api/leave")
public class LeaveController {
    private final LeaveService leaveService;

    private final UserService userService;

    @Autowired
    public LeaveController(LeaveService leaveService, UserService userService) {
        this.leaveService = leaveService;
        this.userService = userService;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LeaveDto> addLeave(@RequestBody LeaveDto leaveDto) {
        try {
            Leave leave = LeaveConverter.toEntity(leaveDto);
            return ResponseEntity.ok(LeaveConverter.toDto(leaveService.save(leave)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/leave-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveTypeDto>> getLeaveTypes() {
        return ResponseEntity.ok(Arrays.asList(new LeaveTypeDto(EnumLeaveType.MAIN_FREE.getLabel(), EnumLeaveType.MAIN_FREE.name(), false),
                new LeaveTypeDto(EnumLeaveType.MAIN_PAID.getLabel(), EnumLeaveType.MAIN_PAID.name(), false)));
    }

    @GetMapping(value = "/paid-leave-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveTypeDto>> getPaidLeaveTypes() {
        List<LeaveTypeDto> paidLeaves = Arrays.stream(EnumLeaveType.values()).filter(enumLeaveType -> !enumLeaveType.isFree())
                .map(e -> new LeaveTypeDto(e.getLabel(), e.name())).collect(Collectors.toList());
        return ResponseEntity.ok(paidLeaves);
    }

    @GetMapping(value = "/free-leave-type", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveTypeDto>> getFreeLeaveType() {
        List<LeaveTypeDto> freeLeaves = Arrays.stream(EnumLeaveType.values()).filter(enumLeaveType -> enumLeaveType.isFree())
                .map(e -> new LeaveTypeDto(e.getLabel(), e.name())).collect(Collectors.toList());
        return ResponseEntity.ok(freeLeaves);
    }

    // http://localhost:8080/api/leave/userId/7
    @GetMapping(value = "/userId/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveDto>> getLeavesByUserId(@PathVariable("userId") Long userId) {
        try {
            List<Leave> leaveList = leaveService.getByUserId(userId);
            List<LeaveDto> leaveDtoList = leaveList.stream().map(e -> LeaveConverter.toDto(e)).collect(Collectors.toList());
            return ResponseEntity.ok(leaveDtoList);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    // http://localhost:8080/api/leave/id/7
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LeaveDto> getById(@PathVariable("id") Long id) {
        try {
            Leave leave = leaveService.getById(id);
            return ResponseEntity.ok(LeaveConverter.toDto(leave));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveDto>> getAll() {
        try {
            List<Leave> leaveList = leaveService.getAll();
            List<LeaveDto> leaveDtoList = leaveList.stream().map(LeaveConverter::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(leaveDtoList);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        try {
            leaveService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/approve-leave/{managerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<LeaveDto>> getApproveLeaves(@PathVariable("managerId") Long managerId) throws ResourceNotFoundException {
        try {
            List<Leave> leaveList = leaveService.getApproveLeave(managerId);
            List<LeaveDto> leaveDtoList = leaveList.stream().map(LeaveConverter::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(leaveDtoList);
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

}
