package tr.com.bacompany.bacrm.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.TimesheetService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/timesheet")
@Api(tags = "Timesheets")
public class TimesheetController {
    private final TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @ApiOperation(value = "Save timesheets.")
    @PostMapping(value = "/save")
    public ResponseEntity<List<TimesheetDto>> saveTimesheet(List<TimesheetDto> timesheetDto) {
        try {
            return ResponseEntity.ok(timesheetService.saveTimesheet(timesheetDto));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Approve timesheet by user.")
    @PostMapping(value = "/approve-by-user")
    public ResponseEntity<List<TimesheetDto>> approveTimesheetByUser(List<TimesheetDto> timesheetDto) {
        try {
            return ResponseEntity.ok(timesheetService.approveTimesheetByUser(timesheetDto));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Approve timesheet by manager.")
    @PostMapping(value = "/approve-by-manager")
    public ResponseEntity<List<TimesheetDto>> approveTimesheetByManager(List<TimesheetDto> timesheetDto) {
        try {
            return ResponseEntity.ok(timesheetService.approveTimesheetByManager(timesheetDto));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @ApiOperation(value = "Reject timesheet by manager.")
    @PostMapping(value = "/reject-by-manager")
    public ResponseEntity<List<TimesheetDto>> rejectTimesheetByManager(List<TimesheetDto> timesheetDto) {
        try {
            return ResponseEntity.ok(timesheetService.rejectTimesheetByManager(timesheetDto));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Update timesheet.")
    @PatchMapping(value = "/")
    public ResponseEntity<List<TimesheetDto>> update(List<TimesheetDto> timesheetDtoList) {
        try {
            return ResponseEntity.ok(timesheetService.update(timesheetDtoList));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Get timesheet by id.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<TimesheetDto> get(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(timesheetService.getBy(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Get all timesheet.")
    @GetMapping(value = "/")
    public ResponseEntity<List<TimesheetDto>> getAll() {
        try {
            return ResponseEntity.ok(timesheetService.getAll());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
