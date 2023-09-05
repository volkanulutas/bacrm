package tr.com.bacompany.bacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.data.dto.TimesheetDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.TimesheetService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/timesheet")
public class TimesheetController {
    private final TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping(value = "/")
    public ResponseEntity<TimesheetDto> addTimesheet(TimesheetDto timesheetDto) {
        try {
            return ResponseEntity.ok(timesheetService.add(timesheetDto));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/")
    public ResponseEntity<TimesheetDto> update(TimesheetDto timesheetDto) {
        try {
            return ResponseEntity.ok(timesheetService.update(timesheetDto));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<TimesheetDto> getWork(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(timesheetService.getBy(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<TimesheetDto>> getAll() {
        try {
            return ResponseEntity.ok(timesheetService.getAll());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
