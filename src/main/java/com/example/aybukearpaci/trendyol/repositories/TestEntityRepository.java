package com.example.aybukearpaci.trendyol.repositories;


import com.example.aybukearpaci.trendyol.entities.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
}
