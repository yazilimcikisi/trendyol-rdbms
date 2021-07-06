package com.example.aybukearpaci.trendyol.repositories;


import com.example.aybukearpaci.trendyol.entities.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BasketRepository extends JpaRepository<Basket, Long> {
    Basket findByCustomerId(long customerId);
}
