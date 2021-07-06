package com.example.aybukearpaci.trendyol.controllers;

import com.example.aybukearpaci.trendyol.entities.*;
import com.example.aybukearpaci.trendyol.repositories.AddressRepository;
import com.example.aybukearpaci.trendyol.repositories.CustomerRepository;
import com.example.aybukearpaci.trendyol.repositories.DistrictRepository;
import com.example.aybukearpaci.trendyol.repositories.SellerRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(path = "/addresses")
public class AddressController {
    private final AddressRepository _addressRepository;
    private final CustomerRepository _customerRepository;
    private final SellerRepository _sellerRepository;
    private final DistrictRepository _districtRepository;

    public AddressController(AddressRepository addressRepository, CustomerRepository customerRepository,
                             SellerRepository sellerRepository, DistrictRepository districtRepository)
    {
        _addressRepository = addressRepository;
        _customerRepository = customerRepository;
        _sellerRepository = sellerRepository;
        _districtRepository = districtRepository;
    }

    @PostMapping(path = "/addCustomerAddress")
    public Object addNewCustomerAddress(@RequestBody Address address)
    {
        System.out.println("Aybukeeee");
        //Check the constraints
        if (address.getName() == null || address.getName().trim().isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }

        Customer customer;
        try {
            customer = _customerRepository.findById(address.getCustomer().getId()).orElseThrow(EntityNotFoundException::new);
        }
        catch (EntityNotFoundException e) {
            return HttpStatus.BAD_REQUEST;
        }

        /*District district;
        try {
            System.out.println("**************"+address.getDistrict().getId());
            district = _districtRepository.findById(address.getDistrict().getId()).orElseThrow(EntityNotFoundException::new);
        }
        catch (EntityNotFoundException e) {
            return HttpStatus.BAD_REQUEST;
        }*/

        Address createdAddress = new Address(address.getName(),
                address.getAddressDetail(),
                address.getDescription(),
                address.getPhoneNumber(),
                address.getEmailAddress(),
                address.getRecevierNameSurmane());

        createdAddress.setCustomer(customer);
        createdAddress = _addressRepository.save(createdAddress);

        //createdAddress.setDistrict(district);
        System.out.println("A new Product created with id: " + createdAddress.getId() + "  and name: " + createdAddress.getName());
        return createdAddress;
    }

    @GetMapping(path = "/getAddressListByCustomerId")
    public ResponseEntity<?> getAddressByCustomerId(@RequestParam(value = "customerId") long id) {
        List<Address> addressList = _addressRepository.findByCustomerId(id);
        if (!addressList.isEmpty()) {
            return new ResponseEntity<>(addressList, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "There isn't any Address with customer: " +
                        id, HttpStatus.NOT_FOUND);
    }
}
