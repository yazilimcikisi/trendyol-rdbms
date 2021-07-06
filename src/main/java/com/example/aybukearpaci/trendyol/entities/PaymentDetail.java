package com.example.aybukearpaci.trendyol.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "paymentDetails")
public class PaymentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Customer customer;

    @ManyToOne(targetEntity = Basket.class)
    @JoinColumn(name = "basket_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Basket basket;

    private String paymentStatus;
    private String paymentDesctiption;
    private String cardInfo;

    @Temporal(TemporalType.DATE)
    private Date date;

    @OneToOne(mappedBy = "paymentDetail")
    private Order order;

    public PaymentDetail(){}
    public  PaymentDetail(Customer customer, Basket basket, String paymentStatus, String paymentDesctiption, String cardInfo, Date date){
        this.customer = customer;
        this.basket = basket;
        this.paymentStatus = paymentStatus;
        this.paymentDesctiption = paymentDesctiption;
        this.cardInfo = cardInfo;
        this.date = date;
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

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentDesctiption() {
        return paymentDesctiption;
    }

    public void setPaymentDesctiption(String paymentDesctiption) {
        this.paymentDesctiption = paymentDesctiption;
    }

    public String getCardInfo() {
        return cardInfo;
    }

    public void setCardInfo(String cardInfo) {
        this.cardInfo = cardInfo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
