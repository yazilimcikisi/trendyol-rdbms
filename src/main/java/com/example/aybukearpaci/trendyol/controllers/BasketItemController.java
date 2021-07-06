package com.example.aybukearpaci.trendyol.controllers;

import com.example.aybukearpaci.trendyol.entities.Basket;
import com.example.aybukearpaci.trendyol.entities.BasketItem;
import com.example.aybukearpaci.trendyol.entities.Product;
import com.example.aybukearpaci.trendyol.entities.ProductStock;
import com.example.aybukearpaci.trendyol.enums.BasketStatus;
import com.example.aybukearpaci.trendyol.repositories.BasketItemRepository;
import com.example.aybukearpaci.trendyol.repositories.BasketRepository;
import com.example.aybukearpaci.trendyol.repositories.ProductStockRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/basketItems")
public class BasketItemController {
    private final BasketItemRepository _basketItemRepository;
    private final BasketRepository _basketRepository;
    private final ProductStockRepository _productStockRepository;

    public BasketItemController(BasketItemRepository basketItemRepository, BasketRepository basketRepository,
                                ProductStockRepository productStockRepository)
    {
        _basketItemRepository = basketItemRepository;
        _basketRepository = basketRepository;
        _productStockRepository = productStockRepository;
    }

    @PostMapping(path = "/deleteBasketItem")
    public Object deleteBasketItem(@RequestBody BasketItem basketItem)
    {
        if (basketItem.getBasket() == null ) {
            return HttpStatus.BAD_REQUEST;
        }

        Basket basket = _basketRepository.getById(basketItem.getBasket().getId());

        List<BasketItem> basketItems = _basketItemRepository.findByBasketId(basket.getId());
        if(basketItems.size() > 1)
        {
            if (basketItem.getProduct().getCurrentDiscount() > 0)
                basket.setTotalPrice(basket.getTotalPrice() - (basketItem.getProduct().getPrice() - (basketItem.getProduct().getPrice() *basketItem.getProduct().getCurrentDiscount() /100 )));
            else
                basket.setTotalPrice(basket.getTotalPrice() - basketItem.getProduct().getPrice());
            basket.setBasketStatus(BasketStatus.SiparisBaslatilmayiBekliyor);
        }
        else
        {
            basket.setTotalPrice(0);
            basket.setBasketStatus(BasketStatus.TumSepetBosaltildi);
        }
        BasketItem basketItemEntity = _basketItemRepository.getById(basketItem.getId());
        _basketItemRepository.delete(basketItemEntity);
        _basketRepository.save(basket);
        ProductStock productStock = _productStockRepository.findByProductId(basketItem.getProduct().getId());
        _productStockRepository.delete(productStock);
        ProductStock productStockEntity = new ProductStock(basketItem.getProduct(), productStock.getNumberOfStock() - 1);
        _productStockRepository.save(productStockEntity);
        System.out.println("The product deleted from Basket. Basket id: " + basket.getId() + "  Product name:  " + basketItem.getProduct().getName());
        return basket;
    }

}
