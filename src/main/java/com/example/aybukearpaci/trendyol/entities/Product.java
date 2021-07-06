package com.example.aybukearpaci.trendyol.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products",indexes = @Index(columnList = "name"))
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;
    @NotNull
    private float price;
    private float currentDiscount;

    @ElementCollection
    @CollectionTable(name = "Product_Images", joinColumns = @JoinColumn(name = "product_id", nullable = false))
    @Column(name = "image_URL", nullable = false)
    @Size(min = 1)
    @NotNull
    private List<String> images;

    @ManyToOne(targetEntity = Seller.class)
    @JoinColumn(name = "seller_id", referencedColumnName = "id", nullable = false)
    @NotNull
    private Seller seller;


    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
    @Size(min = 1)
    @NotNull
    private Set<Category> fallIntoCategories;

    @OneToOne(mappedBy = "product")
    private ProductStock productStock;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<BasketItem> basketItems;

    public Product(){}
    public Product(String name, String description, float price, float currentDiscount, List<String> images, Seller seller, Set<Category> fallIntoCategories) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.currentDiscount = currentDiscount;
        this.images = images;
        this.seller = seller;
        this.fallIntoCategories = fallIntoCategories;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getCurrentDiscount() {
        return currentDiscount;
    }

    public void setCurrentDiscount(float currentDiscount) {
        this.currentDiscount = currentDiscount;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public Set<Category> getFallIntoCategories() {
        return fallIntoCategories;
    }

    public void setFallIntoCategories(Set<Category> fallIntoCategories) {
        this.fallIntoCategories = fallIntoCategories;
    }

    public ProductStock getProductStock() {
        return productStock;
    }

    public void setProductStock(ProductStock productStock) {
        this.productStock = productStock;
    }

    public Set<BasketItem> getBasketItems() {
        return basketItems;
    }

    public void setBasketItems(Set<BasketItem> basketItems) {
        this.basketItems = basketItems;
    }
}
