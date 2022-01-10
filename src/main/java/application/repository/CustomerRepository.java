package application.repository;

import application.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface for JPA operators for {@link Customer} objects.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByCustomerId(Integer customerId);
}
