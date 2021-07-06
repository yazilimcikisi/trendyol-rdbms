package com.example.aybukearpaci.trendyol.entities;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BasketItems")
public class BasketItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = Basket.class)
    @JoinColumn(name = "basket_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Basket basket;

    @ManyToOne(targetEntity = Product.class)
    @JoinColumn(name = "product_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Product product;

    private int numberOfProduct;
    private boolean isDeleted;

    public BasketItem(){

    }
    public BasketItem(Basket basket, Product product, int numberOfProduct, boolean isDeleted) {
        this.basket = basket;
        this.product = product;
        this.numberOfProduct = numberOfProduct;
        this.isDeleted = isDeleted;
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
