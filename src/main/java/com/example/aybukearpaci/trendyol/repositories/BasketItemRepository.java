package com.example.aybukearpaci.trendyol.repositories;


import com.example.aybukearpaci.trendyol.entities.BasketItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
    List<BasketItem> findByBasketId(long basketId);
}
