package com.productApi.service;

import com.productApi.repository.productRepository;
import com.productApi.model.Product;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService
{
    @Autowired
    private productRepository productRepository;

    public List<Product> listAll()
        {
            return productRepository.findAll();
        }
    public  Product save (Product product)
    {
        return productRepository.save(product);
    }

    public void delete (long id)
    {
          productRepository.deleteById(id);
    }

    public Product update (Long id, Product product )
    {
        if (productRepository.existsById(id))
        {
            product.setId(id);
            return productRepository.save(product);
        }
        else
        {
            throw new RuntimeException("Product not Found");
        }
    }

    public Optional <Product> findById (long id)
    {
       return productRepository.findById(id);
    }



}
