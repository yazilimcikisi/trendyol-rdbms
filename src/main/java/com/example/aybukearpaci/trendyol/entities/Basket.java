package com.example.aybukearpaci.trendyol.entities;
import com.example.aybukearpaci.trendyol.enums.BasketStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.aspectj.weaver.ast.Or;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "Baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private BasketStatus basketStatus;

    private float totalPrice;

    @OneToOne(mappedBy = "basket")
    private Order order;

    @OneToMany(mappedBy = "basket")
    private Set<PaymentDetail> paymentDetails;

    @OneToMany(mappedBy = "basket")
    @JsonIgnore
    private Set<BasketItem> basketItems;

    public  Basket(){

    }
    public Basket(Customer customer, BasketStatus basketStatus, float totalPrice) {
        this.customer = customer;
        this.basketStatus = basketStatus;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BasketStatus getBasketStatus() {
        return basketStatus;
    }

    public void setBasketStatus(BasketStatus basketStatus) {
        this.basketStatus = basketStatus;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<PaymentDetail> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Set<PaymentDetail> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public Set<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(Set<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }
}
