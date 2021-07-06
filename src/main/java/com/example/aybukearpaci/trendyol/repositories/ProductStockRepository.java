package com.example.aybukearpaci.trendyol.repositories;


import com.example.aybukearpaci.trendyol.entities.ProductStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStockRepository extends JpaRepository<ProductStock, Long> {
    ProductStock findByProductId(long productId);
}
