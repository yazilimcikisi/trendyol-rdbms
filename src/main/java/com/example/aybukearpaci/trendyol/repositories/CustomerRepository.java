package com.example.aybukearpaci.trendyol.repositories;

import com.example.aybukearpaci.trendyol.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByUserName(String userName);
}
