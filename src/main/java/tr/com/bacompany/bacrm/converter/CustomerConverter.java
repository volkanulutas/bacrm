package tr.com.bacompany.bacrm.converter;

import tr.com.bacompany.bacrm.data.dto.CustomerDto;
import tr.com.bacompany.bacrm.data.entity.Customer;

public class CustomerConverter {
    public static Customer toEntity(CustomerDto source) {
        Customer target = new Customer();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDefinition(source.getDefinition());
        target.setAddress(source.getAddress());
        target.setTelephone(source.getTelephone());
        return target;
    }

    public static CustomerDto toDto(Customer source) {
        CustomerDto target = new CustomerDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setDefinition(source.getDefinition());
        target.setAddress(source.getAddress());
        target.setTelephone(source.getTelephone());
        return target;
    }
}
