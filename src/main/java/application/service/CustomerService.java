package application.service;

import application.domain.Customer;

import java.util.List;

/**
 * Service interface to handle {@link Customer} object.
 */
public interface CustomerService {

    /**
     * Register a customer to the library.
     * @param name will be the customer name.
     */
    void registerCustomer(String name);

    /**
     * Get customer from the library's database.
     * @param id is a unique id belongs to each {@link Customer}.
     * @return the customer.
     */
    Customer getCustomerById(Integer id);

    /**
     * Get all customers from the library's database.
     * @return the existing {@link Customer}s.
     */
    List<Customer> getAllCustomer();
}
