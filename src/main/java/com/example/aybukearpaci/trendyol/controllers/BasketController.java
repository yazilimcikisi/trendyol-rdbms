package com.example.aybukearpaci.trendyol.controllers;

import com.example.aybukearpaci.trendyol.entities.*;
import com.example.aybukearpaci.trendyol.enums.BasketStatus;
import com.example.aybukearpaci.trendyol.repositories.BasketItemRepository;
import com.example.aybukearpaci.trendyol.repositories.BasketRepository;
import com.example.aybukearpaci.trendyol.repositories.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping(path = "/baskets")
public class BasketController {
    private final BasketRepository _basketRepository;
    private final BasketItemRepository _basketItemRepository;
    private final ProductRepository _productRepository;

    public BasketController(BasketRepository basketRepository, BasketItemRepository basketItemRepository,
                            ProductRepository productRepository)
    {
        _basketRepository = basketRepository;
        _basketItemRepository = basketItemRepository;
        _productRepository = productRepository;
    }

    @GetMapping(path = "/getBasketByCustomerId")
    public ResponseEntity<?> getBasketByCustomerId(@RequestParam(value = "customerId") long id) {
        Basket basket = _basketRepository.findByCustomerId(id);
        if (basket != null) {
            List<BasketItem> basketItemList = _basketItemRepository.findByBasketId(id);
            if (!basketItemList.isEmpty())
            {
                basket.setBasketItems(new HashSet<>(basketItemList));
            }
            return new ResponseEntity<>(basket, HttpStatus.OK);
        }
        return new ResponseEntity<>(
                "There isn't any Basket with customer: " +
                        id, HttpStatus.NOT_FOUND);
    }

    @PostMapping(path = "/addProductToBasket")
    public Object addProductToBasket(@RequestBody BasketItem basketItem)
    {
        if (basketItem.getBasket() == null ) {
            return HttpStatus.BAD_REQUEST;
        }

        Basket basket = _basketRepository.getById(basketItem.getBasket().getId());
        if (basketItem.getProduct().getCurrentDiscount() > 0)
            basket.setTotalPrice(basket.getTotalPrice() + (basketItem.getProduct().getPrice() - (basketItem.getProduct().getPrice() *basketItem.getProduct().getCurrentDiscount() /100 )));
        else
            basket.setTotalPrice(basket.getTotalPrice()+basketItem.getProduct().getPrice());

        basket.setBasketStatus(BasketStatus.SiparisBaslatilmayiBekliyor);
        _basketRepository.save(basket);

        Product product = _productRepository.getById(basketItem.getProduct().getId());

        BasketItem basketItemEntity = new BasketItem(basket, product, basketItem.getNumberOfProduct(), false);
        _basketItemRepository.save(basketItemEntity);

        System.out.println("The product added Basket. Basket id: " + basket.getId() + "  Product name:  " + basketItem.getProduct().getName());
        return basket;
    }
}
