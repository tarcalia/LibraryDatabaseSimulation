package application.service;

import application.domain.Customer;

import java.util.List;

/**
 * Service interface to handle {@link Customer} object.
 */
public interface CustomerService {

    void registerCustomer(String name);

    Customer getCustomerById(Integer id);

    List<Customer> getAllCustomer();
}
