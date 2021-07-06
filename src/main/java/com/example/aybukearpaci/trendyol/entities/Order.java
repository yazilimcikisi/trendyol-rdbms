package com.example.aybukearpaci.trendyol.entities;
import com.example.aybukearpaci.trendyol.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "basket_id", referencedColumnName = "id")
    @JsonIgnore
    private Basket basket;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    @JsonIgnore
    private PaymentDetail paymentDetail;

    @ManyToOne(targetEntity = Address.class)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Address address;

    private float price;

    @Temporal(TemporalType.DATE)
    private Date date;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order(){}
    public Order(Basket basket, PaymentDetail paymentDetail, Address address, float price, Date date, OrderStatus orderStatus) {
        this.basket = basket;
        this.paymentDetail = paymentDetail;
        this.address = address;
        this.price = price;
        this.date = date;
        this.orderStatus = orderStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public PaymentDetail getPaymentDetail() {
        return paymentDetail;
    }

    public void setPaymentDetail(PaymentDetail paymentDetail) {
        this.paymentDetail = paymentDetail;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
