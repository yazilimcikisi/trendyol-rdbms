package com.example.aybukearpaci.trendyol.entities;
import javax.persistence.*;

@Entity
@Table(name = "TestEntities")
public class TestEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    public void setId(long id) {
        this.id = id;
    }

    public TestEntity(String name){
        this.name = name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
