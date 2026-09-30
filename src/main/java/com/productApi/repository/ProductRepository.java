package com.productApi.repository;

import com.productApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    List<Product> findByNameContaining(String name);

    List<Product> findByNameAndStatus(String name, String status);

    List<Product> findByNameStartingWith(String prefix);

    List<Product> findByNameEndingWith(String suffix);

    List<Product> findByPrice(Double price);
    List<Product> findByPriceGreaterThan(Double price);
    List<Product> findByPriceLessThan(Double price);

    List <Product> findByStatus(String status);

    List<Product> findByStatusIsNull();

    List<Product> findByStatusAndPrice(String status, Double price);

    List<Product> findByAmount(Integer amount);

    List<Product> findByAmountLessThan(Integer amount);

    List<Product> findByAmountGreaterThan(Integer amount);



    @Query("SELECT SUM(p.price) from Product p")
    Double findTotalPrice();




}