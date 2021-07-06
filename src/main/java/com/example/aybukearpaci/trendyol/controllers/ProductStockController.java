package com.example.aybukearpaci.trendyol.controllers;

import com.example.aybukearpaci.trendyol.entities.ProductStock;
import com.example.aybukearpaci.trendyol.repositories.ProductStockRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/productStocks")
public class ProductStockController {
    private final ProductStockRepository _productStockRepository;

    public ProductStockController(ProductStockRepository productStockRepository)
    {
        _productStockRepository = productStockRepository;
    }

    @GetMapping(path = "/checkProducStockByProductId")
    public boolean getAddressByCustomerId(@RequestParam(value = "productId") long id) {
        ProductStock productStock = _productStockRepository.findByProductId(id);
        if (productStock != null) {
            if (productStock.getNumberOfStock() > 0)
                return true;
            else
                return false;
        }
        return false;
    }
}
