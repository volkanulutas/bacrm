package tr.com.bacompany.bacrm.service;

import tr.com.bacompany.bacrm.data.entity.Customer;
import tr.com.bacompany.bacrm.data.exception.ResourceNotFoundException;

import java.util.List;

public interface CustomerService {
    Customer add(Customer customer);

    Customer update(Customer customer);

    List<Customer> getAll();

    Customer getById(Long id) throws ResourceNotFoundException;

    boolean delete(Customer customer) throws ResourceNotFoundException;
}
