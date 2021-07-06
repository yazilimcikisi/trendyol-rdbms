package com.example.aybukearpaci.trendyol.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String addressDetail;

    /*@ManyToOne(targetEntity = District.class)
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;*/

    private String description;
    private String phoneNumber;
    private String emailAddress;
    private String recevierNameSurmane;

    @OneToMany(mappedBy = "address")
    private Set<Order> orders;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private Customer customer;

    @ManyToOne(targetEntity = Seller.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id")
    private Seller seller;

    public  Address(){

    }

    public Address(String name, String addressDetail, String description, String phoneNumber, String emailAddress, String recevierNameSurmane)
    {
        this.name = name;
        this.addressDetail = addressDetail;
        this.description = description;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.recevierNameSurmane = recevierNameSurmane;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddressDetail() {
        return addressDetail;
    }

    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }

   /*public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getRecevierNameSurmane() {
        return recevierNameSurmane;
    }

    public void setRecevierNameSurmane(String recevierNameSurmane) {
        this.recevierNameSurmane = recevierNameSurmane;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
