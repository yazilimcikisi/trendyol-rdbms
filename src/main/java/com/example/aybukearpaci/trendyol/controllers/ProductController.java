package com.example.aybukearpaci.trendyol.controllers;
import com.example.aybukearpaci.trendyol.entities.Category;
import com.example.aybukearpaci.trendyol.entities.Product;
import com.example.aybukearpaci.trendyol.entities.ProductStock;
import com.example.aybukearpaci.trendyol.entities.Seller;
import com.example.aybukearpaci.trendyol.repositories.CategoryRepository;
import com.example.aybukearpaci.trendyol.repositories.ProductRepository;
import com.example.aybukearpaci.trendyol.repositories.ProductStockRepository;
import com.example.aybukearpaci.trendyol.repositories.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;


@RestController
@RequestMapping(path = "/products")
public class ProductController {
    private final SellerRepository _sellerRepository;
    private final CategoryRepository _categoryRepository;
    private final ProductRepository _productRepository;
    private final ProductStockRepository _productStockRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, SellerRepository sellerRepository,
                             CategoryRepository categoryRepository, ProductStockRepository productStockRepository) {
        this._productRepository = productRepository;
        this._sellerRepository = sellerRepository;
        this._categoryRepository = categoryRepository;
        this._productStockRepository = productStockRepository;
    }

    @GetMapping(path = "/")
    public List<Product> getAllProducts()
    {
        return _productRepository.findAll();
    }

    @PostMapping(path = "/addNewProduct")
    public Object addNewProduct(@RequestBody Product product)
    {
        //Check the constraints
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            return HttpStatus.BAD_REQUEST;
        }
        if (product.getImages() == null || product.getImages().size() == 0) {
            return HttpStatus.BAD_REQUEST;

        }

        Seller seller;
        try {
            seller = _sellerRepository.findById(product.getSeller().getId()).orElseThrow(EntityNotFoundException::new);
        }
        catch (EntityNotFoundException e) {
            return HttpStatus.BAD_REQUEST;
        }

        HashSet<Category> categories = new HashSet<>();
        try {
            for (Category category : product.getFallIntoCategories()) {
                categories.add(_categoryRepository.findById(category.getId()).orElseThrow(EntityNotFoundException::new));
            }
        }
        catch (EntityNotFoundException e) {
            return HttpStatus.BAD_REQUEST;
        }

        if (!categories.isEmpty()) {
            Product createdProduct = new Product(product.getName(),
                    product.getDescription(),
                    product.getPrice(),0,
                    product.getImages(),
                    seller,
                    categories);
            createdProduct = _productRepository.save(createdProduct);
            System.out.println("A new Product created with id: " + createdProduct.getId() + "  and name: " + createdProduct.getName());

            ProductStock productStock = new ProductStock(createdProduct,1);
            _productStockRepository.save(productStock);

            createdProduct.setProductStock(productStock);

            return createdProduct;
        }
        else {
            return HttpStatus.BAD_REQUEST;
        }
    }

    @PutMapping(path = "/")
    public ResponseEntity<String> updateProduct(@Valid @RequestBody Product product)
    {
        Product productEntity;
        Seller seller;
        try
        {
            productEntity = _productRepository.getOne(product.getId());
            System.out.println("The product " + productEntity.getName() + " with id " + productEntity.getId() + " is updating...");
        }
        catch (EntityNotFoundException e)
        {
            return new ResponseEntity<>("This product does not exists.", HttpStatus.NOT_FOUND);
        }
        try {
            seller = _sellerRepository.getOne(product.getSeller().getId());
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>("The seller does not exists", HttpStatus.NOT_FOUND);
        }
        HashSet<Category> categories = new HashSet<>();
        for (Category category : product.getFallIntoCategories()) {
            _categoryRepository.findById(category.getId()).ifPresent(categories::add);
        }
        if (!categories.isEmpty()) {
            productEntity.setName(product.getName());
            productEntity.setDescription(product.getDescription());
            productEntity.setPrice(product.getPrice());
            productEntity.setImages(product.getImages());
            productEntity.setSeller(seller);
            productEntity.setFallIntoCategories(categories);
            _productRepository.save(productEntity);
            return new ResponseEntity<>("The product updated", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("The product must belongs to at least one category!", HttpStatus.BAD_REQUEST);
        }
    }
}
