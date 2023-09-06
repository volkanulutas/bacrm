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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.WorkService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/work")
@Api(tags = "Works")
public class WorkController {
    private final WorkService workService;

    @Autowired
    public WorkController(WorkService workService) {
        this.workService = workService;
    }

    @ApiOperation(value = "Add worksheet.")
    @PostMapping(value = "/")
    public ResponseEntity<WorkDto> add(@RequestBody WorkDto work) {
        try {
            return ResponseEntity.ok(workService.add(work));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Update worksheet.")
    @PatchMapping(value = "/")
    public ResponseEntity<WorkDto> update(@RequestBody WorkDto work) {
        try {
            return ResponseEntity.ok(workService.update(work));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Get worksheet by id.")
    @GetMapping(value = "/{id}")
    public ResponseEntity<WorkDto> get(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(workService.getBy(id));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @ApiOperation(value = "Get all worksheets.")
    @GetMapping(value = "/")
    public ResponseEntity<List<WorkDto>> getAll() {
        try {
            return ResponseEntity.ok(workService.getAll());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
