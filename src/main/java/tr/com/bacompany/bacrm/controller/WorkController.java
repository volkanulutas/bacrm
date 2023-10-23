package tr.com.bacompany.bacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.converter.WorkConverter;
import tr.com.bacompany.bacrm.data.dto.WorkDto;
import tr.com.bacompany.bacrm.data.dto.user.UserDto;
import tr.com.bacompany.bacrm.data.entity.Work;
import tr.com.bacompany.bacrm.data.entity.user.User;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.UserService;
import tr.com.bacompany.bacrm.service.WorkService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/work")
@CrossOrigin(origins = "*", allowedHeaders = {"*"})
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class WorkController {
    private final WorkService workService;

    private final UserService userService;

    @Autowired
    public WorkController(WorkService workService, UserService userService) {
        this.workService = workService;
        this.userService = userService;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkDto> add(@RequestBody WorkDto workDto) {
        try {
            Work work = WorkConverter.toEntity(workDto);
            Set<UserDto> users = workDto.getUsers();


            for (UserDto userDto : users) {
                User user = userService.get(userDto.getId());
                if (user != null) {
                    work.getUsers().add(user);
                }
            }
            work = workService.save(work);
            return ResponseEntity.ok(WorkConverter.toDto(work));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkDto> update(@RequestBody WorkDto workDto) {
        try {
            Work work = WorkConverter.toEntity(workDto);
            Set<UserDto> users = workDto.getUsers();
            for (UserDto userDto : users) {
                User user = userService.get(userDto.getId());
                if (user != null) {
                    work.getUsers().add(user);
                }
            }
            work = workService.save(work);
            return ResponseEntity.ok(WorkConverter.toDto(work));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<WorkDto> get(@PathVariable("id") Long id) {
        try {
            Work work = workService.getBy(id);
            return ResponseEntity.ok(WorkConverter.toDto(work));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<WorkDto>> getAll() {
        try {
            List<Work> works = workService.getAll();
            List<WorkDto> workDtoList = works.stream().map(WorkConverter::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(workDtoList);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
