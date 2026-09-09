package com.productApi.controller;

import com.productApi.model.Product;
import com.productApi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping

public class ProductController
{

    @Autowired
    private  ProductService productService;

    @GetMapping
    public List <Product>  listAll (){
        return productService.listAll();
    }

    @PostMapping
    public Product save (@RequestBody Product product){
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public Product product(@PathVariable Long id, @RequestBody Product product)
    {
        return productService.update(id,product);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        productService.delete(id);
    }

    @GetMapping("/{id}")
    public Optional<Product> findById (@PathVariable Long id)
    {
        return productService.findById(id);
    }
}
