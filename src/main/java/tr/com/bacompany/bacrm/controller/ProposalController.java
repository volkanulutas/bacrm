package tr.com.bacompany.bacrm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.com.bacompany.bacrm.converter.ProposalConverter;
import tr.com.bacompany.bacrm.data.dto.ProposalDto;
import tr.com.bacompany.bacrm.data.entity.Proposal;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.ProposalService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/proposal")
@CrossOrigin(origins = "*", allowedHeaders = {"*"})
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProposalController {
    private final ProposalService proposalService;

    @Autowired
    public ProposalController(ProposalService proposalService) {
        this.proposalService = proposalService;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalDto> add(@RequestBody ProposalDto proposalDto) {
        try {
            Proposal proposal = ProposalConverter.toEntity(proposalDto);
            proposal = proposalService.save(proposal);
            return ResponseEntity.ok(ProposalConverter.toDto(proposal));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalDto> update(@RequestBody ProposalDto proposalDto) {
        try {
            Proposal proposal = ProposalConverter.toEntity(proposalDto);
            proposal = proposalService.save(proposal);
            return ResponseEntity.ok(ProposalConverter.toDto(proposal));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalDto> get(@PathVariable("id") Long id) {
        try {
            Proposal proposal = proposalService.getById(id);
            return ResponseEntity.ok(ProposalConverter.toDto(proposal));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/proposal-id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProposalDto> get(@PathVariable("proposalId") String proposalId) {
        try {
            Proposal proposal = proposalService.getByProposalId(proposalId);
            return ResponseEntity.ok(ProposalConverter.toDto(proposal));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProposalDto>> getAll() {
        try {
            List<Proposal> proposalList = proposalService.getAll();
            List<ProposalDto> workDtoList = proposalList.stream().map(ProposalConverter::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(workDtoList);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) throws ResourceNotFoundException {
        try {
            proposalService.delete(id);
            return ResponseEntity.ok().build();
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
