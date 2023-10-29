package tr.com.bacompany.bacrm.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.bacompany.bacrm.data.entity.Customer;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;
import tr.com.bacompany.bacrm.repository.CustomerRepository;
import tr.com.bacompany.bacrm.service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service(value = "customerService")
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> customerList = customerRepository.findAll();
        return customerList.stream().filter(e-> !e.isDeleted()).collect(Collectors.toList());
    }

    @Override
    public Customer getById(Long workId) throws ResourceNotFoundException {
        Optional<Customer> workOpt = customerRepository.findById(workId);
        if (!workOpt.isPresent()) {
            throw new ResourceNotFoundException("Work", "Work");
        }
        return workOpt.get();
    }

    @Override
    public Customer update(Customer work) throws ResourceNotFoundException {
        this.getById(work.getId());
        return customerRepository.save(work);
    }

    @Override
    public boolean delete(Long customerId) {
        try {
            Customer customer = this.getById(customerId);
            customer.setDeleted(true);
            this.save(customer);
        } catch (Exception ex) {
            return false;
        }
        return true;
    }
}
