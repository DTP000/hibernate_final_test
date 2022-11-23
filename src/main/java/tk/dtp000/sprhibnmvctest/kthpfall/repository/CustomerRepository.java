package tk.dtp000.sprhibnmvctest.kthpfall.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tk.dtp000.sprhibnmvctest.kthpfall.entity.Customer;

@Repository("customerRepository")
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
