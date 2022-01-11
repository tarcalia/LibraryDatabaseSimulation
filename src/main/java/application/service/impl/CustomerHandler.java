package application.service.impl;

import application.repository.CustomerRepository;
import application.service.CustomerService;
import org.springframework.stereotype.Service;
import application.domain.Customer;

import java.util.List;

/**
 * Service class to handle {@link Customer} object.
 */
@Service
public class CustomerHandler implements CustomerService {
    private CustomerRepository customerRepository;

    public CustomerHandler(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void registerCustomer(String name) {
        if (!isNameValid(name)) {
            throw new IllegalArgumentException("Given name is not valid");
        } else {
            customerRepository.save(new Customer(name));
        }
    }

    @Override
    public Customer getCustomerById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("id parameter at getCustomerById is null");
        }
        return customerRepository.findByCustomerId(id);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public boolean isNameValid(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Null value added at name when registering customer");
        }
        return name.matches("([A-Za-z\\.\\s]){3,30}");
    }
}
