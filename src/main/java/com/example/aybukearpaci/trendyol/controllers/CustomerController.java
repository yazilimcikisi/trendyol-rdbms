package com.example.aybukearpaci.trendyol.controllers;

import com.example.aybukearpaci.trendyol.entities.Address;
import com.example.aybukearpaci.trendyol.entities.Customer;
import com.example.aybukearpaci.trendyol.entities.Profile;
import com.example.aybukearpaci.trendyol.repositories.CustomerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
    private final CustomerRepository _customerRepository;

    public CustomerController(CustomerRepository customerRepository)
    {
        _customerRepository = customerRepository;
    }

    @PostMapping(path = "/addNewCustomer")
    public Object addNewCustomer(@RequestBody Customer customer)
    {
        //Check the constraints
        if (customer.getUserName() == null || customer.getUserName().trim().isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }

        Profile profile = new Profile(customer.getProfile().getFirstName(),
                customer.getProfile().getLastName(),
                customer.getProfile().getBirthday(),
                customer.getProfile().getEmailAddress(),
                customer.getProfile().getGender(),
                customer);

        Customer customerEntity = new Customer(customer.getTckn(), customer.getUserName(), customer.getPassword(), new Date());
        customerEntity.setProfile(profile);

        _customerRepository.save(customerEntity);

        System.out.println("A new Customer created with id: " + customerEntity.getId() + "  and name: " + customerEntity.getUserName());
        return customerEntity;
    }

}
