package com.productApi.repository;

import com.productApi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product,Long>
{


}