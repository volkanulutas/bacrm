package tr.com.bacompany.bacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.converter.TimesheetConverter;
import tr.com.bacompany.bacrm.data.dto.timesheet.TimesheetDto;
import tr.com.bacompany.bacrm.data.entity.timesheet.Timesheet;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.TimesheetService;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.MissingResourceException;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/timesheet")
public class TimesheetController {
    private final TimesheetService timesheetService;

    @Autowired
    public TimesheetController(TimesheetService timesheetService) {
        this.timesheetService = timesheetService;
    }

    @PostMapping(value = "/save", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimesheetDto> saveTimesheet(TimesheetDto timesheetDto) {
        try {
            Timesheet timesheet = TimesheetConverter.toEntity(timesheetDto);
            timesheet = timesheetService.saveTimesheet(timesheet);
            return ResponseEntity.ok(TimesheetConverter.toDto(timesheet));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/approve-by-user", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimesheetDto> approveTimesheetByUser(TimesheetDto timesheetDto) {
        try {
            Timesheet timesheet = TimesheetConverter.toEntity(timesheetDto);
            timesheet = timesheetService.approveTimesheetByUser(timesheet);
            return ResponseEntity.ok(TimesheetConverter.toDto(timesheet));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/approve-by-manager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimesheetDto> approveTimesheetByManager(TimesheetDto timesheetDto) {
        try {
            Timesheet timesheet = TimesheetConverter.toEntity(timesheetDto);
            timesheet = timesheetService.approveTimesheetByManager(timesheet);
            return ResponseEntity.ok(TimesheetConverter.toDto(timesheet));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping(value = "/reject-by-manager", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimesheetDto> rejectTimesheetByManager(TimesheetDto timesheetDto) {
        try {
            Timesheet timesheet = TimesheetConverter.toEntity(timesheetDto);
            timesheet = timesheetService.rejectTimesheetByManager(timesheet);
            return ResponseEntity.ok(TimesheetConverter.toDto(timesheet));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimesheetDto> update(TimesheetDto timesheetDto) {
        try {
            Timesheet timesheet = TimesheetConverter.toEntity(timesheetDto);
            timesheet = timesheetService.update(timesheet);
            return ResponseEntity.ok(TimesheetConverter.toDto(timesheet));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimesheetDto> get(@PathVariable("id") Long id) {
        try {
            Timesheet timesheet = timesheetService.getBy(id);
            return ResponseEntity.ok(TimesheetConverter.toDto(timesheet));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TimesheetDto> getByUserId(@PathVariable("userId") Long userId, @PathVariable("weekStartDate") Long weekStartDate) {
        try {
            Timesheet timesheet = timesheetService.getByUserIdAndWeekStartDate(userId, weekStartDate);
            return ResponseEntity.ok(TimesheetConverter.toDto(timesheet));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<TimesheetDto>> getAll() {
        try {
            List<Timesheet> timesheetList = timesheetService.getAll();
            List<TimesheetDto> timesheetDtoList = timesheetList.stream().map(TimesheetConverter::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(timesheetDtoList);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
