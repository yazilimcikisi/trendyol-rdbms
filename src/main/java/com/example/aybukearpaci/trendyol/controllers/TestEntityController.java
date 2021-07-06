package com.example.aybukearpaci.trendyol.controllers;

import com.example.aybukearpaci.trendyol.entities.TestEntity;
import com.example.aybukearpaci.trendyol.repositories.TestEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/testEntities")
public class TestEntityController {
    private final TestEntityRepository _testEntityRepository;

    public TestEntityController(TestEntityRepository testEntityRepository)
    {
        _testEntityRepository = testEntityRepository;
    }

    @PostMapping(path = "/")
    public Object addNewCategory(@RequestParam(value = "name") String name) {
        if (name == null || name.trim().isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }
        TestEntity createdTestEntity = new TestEntity(name);
        createdTestEntity = _testEntityRepository.save(createdTestEntity);
        return createdTestEntity;
    }

}
