package com.productApi.service;

import com.productApi.model.Product;
import com.productApi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> listAll() {
        return productRepository.findAll();
    }

    public List<Product> saveList(List<Product> products) {
        return productRepository.saveAll(products);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public void delete(long id) {
        productRepository.deleteById(id);
    }

    public Product update(Long id, Product product) {
        if (productRepository.existsById(id)) {
            product.setId(id);
            return productRepository.save(product);
        } else {
            throw new RuntimeException("Product not Found");
        }
    }

    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    public List<Product> findByNameContaining(String name) {
        return productRepository.findByNameContaining(name);
    }

    public List<Product> findByNameAndStatus(String name, String status) {
        return productRepository.findByNameAndStatus(name, status);
    }

    public List<Product> findByNameStartingWith(String prefix) {
        return productRepository.findByNameStartingWith(prefix);
    }

    public List<Product> findByNameEndingWith(String suffix) {
        return productRepository.findByNameEndingWith(suffix);
    }

    public List<Product> findByPrice(Double price) {
        return  productRepository.findByPrice(price);
    }

    public List<Product> findByPriceGreaterThan(Double price){
        return productRepository.findByPriceGreaterThan(price);
    }

    public List<Product> findByPriceLessThan(Double price){
        return productRepository.findByPriceLessThan(price);

    }

    public Double findTotalPrice(){
        return productRepository.findTotalPrice();
    }

    public  List<Product> findByAmount(Integer amount){
        return  productRepository.findByAmount(amount);

    }

    public List<Product> findByAmountLessThan(Integer amount){
        return productRepository.findByAmountLessThan(amount);
    }

    public List<Product> findByAmountGreaterThan(Integer amount){
        return productRepository.findByAmountGreaterThan(amount);
    }

    public  List <Product> findByStatus(String status){
        return productRepository.findByStatus(status);
    }

    public List<Product> findByStatusIsNull(){
        return productRepository.findByStatusIsNull();
    }

    public List<Product> findByStatusAndPrice(String status, Double price) {
        return productRepository.findByStatusAndPrice(status, price);
    }

    public Long countTotalProducts() {
        return productRepository.count();
    }

}