package tr.com.bacompany.bacrm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.com.bacompany.bacrm.data.entity.Customer;
import tr.com.bacompany.bacrm.data.entity.Proposal;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
