package com.example.aybukearpaci.trendyol.repositories;


import com.example.aybukearpaci.trendyol.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    District findByName(String name);
}
