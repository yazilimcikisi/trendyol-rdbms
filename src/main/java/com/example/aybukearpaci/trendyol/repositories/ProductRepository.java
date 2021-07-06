package com.example.aybukearpaci.trendyol.repositories;

import com.example.aybukearpaci.trendyol.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByName(String name);
}
