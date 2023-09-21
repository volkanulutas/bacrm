package tr.com.bacompany.bacrm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.converter.LeaveConverter;
import tr.com.bacompany.bacrm.data.dto.leave.LeaveDto;
import tr.com.bacompany.bacrm.data.entity.leave.Leave;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.LeaveService;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/leave")
@Api(tags = "Leaves")
public class LeaveController {
    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {this.leaveService = leaveService;}

    @ApiOperation(value = "Add a leave.")
    @PostMapping(value = "/")
    public ResponseEntity<LeaveDto> addLeave(LeaveDto leaveDto) {
        try {
            Leave leave = LeaveConverter.toEntity(leaveDto);
            return ResponseEntity.ok(LeaveConverter.toDto(leaveService.add(leave)));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Get leave by userId.")
    @GetMapping(value = "/{userId}")
    public ResponseEntity<LeaveDto> getByUserId(@PathVariable("userId") Long userId) {
        try {
            Leave leave = leaveService.getByUserId(userId);
            return ResponseEntity.ok(LeaveConverter.toDto(leave));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Get all leaves.")
    @GetMapping(value = "/")
    public ResponseEntity<List<LeaveDto>> getAll() {
        try {
            List<Leave> leaveList = leaveService.getAll();
            List<LeaveDto> leaveDtoList = leaveList.stream().map(LeaveConverter::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(leaveDtoList);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Delete leave by id.")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        try {
            leaveService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
