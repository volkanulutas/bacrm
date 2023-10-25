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
import tr.com.bacompany.bacrm.converter.CustomerConverter;
import tr.com.bacompany.bacrm.converter.ProposalConverter;
import tr.com.bacompany.bacrm.data.dto.CustomerDto;
import tr.com.bacompany.bacrm.data.dto.ProposalDto;
import tr.com.bacompany.bacrm.data.entity.Customer;
import tr.com.bacompany.bacrm.data.entity.Proposal;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.service.CustomerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*", allowedHeaders = {"*"})
//@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> add(@RequestBody CustomerDto customerDto) {
        try {
            Customer customer = CustomerConverter.toEntity(customerDto);
            Set<ProposalDto> proposalDtoList = customerDto.getProposals();
            Set<Proposal> proposalSet = new HashSet<>();
            for (ProposalDto proposalDto : proposalDtoList) {
                proposalSet.add(ProposalConverter.toEntity(proposalDto));
            }
            customer.setProposals(proposalSet);
            customer = customerService.add(customer);
            return ResponseEntity.ok(CustomerConverter.toDto(customer));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto customerDto) {
        try {
            Customer customer = CustomerConverter.toEntity(customerDto);
            customer = customerService.add(customer);
            return ResponseEntity.ok(CustomerConverter.toDto(customer));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDto> get(@PathVariable("id") Long id) {
        try {
            Customer customer = customerService.getById(id);
            return ResponseEntity.ok(CustomerConverter.toDto(customer));
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDto>> getAll() {
        try {
            List<Customer> customerList = customerService.getAll();
            List<CustomerDto> workDtoList = customerList.stream().map(CustomerConverter::toDto).collect(Collectors.toList());
            return ResponseEntity.ok(workDtoList);
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
