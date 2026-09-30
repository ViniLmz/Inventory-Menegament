package com.productApi.controller;

import com.productApi.model.Product;
import com.productApi.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> listAll(@RequestParam(defaultValue = "ACTIVE") String status) {
        return ResponseEntity.ok(productService.findByStatus(status));
    }

    @PostMapping
    public ResponseEntity<Product> save(@Valid @RequestBody Product product) {
        Product createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PostMapping("/saveAll")
    public ResponseEntity<List<Product>> saveAll(@RequestBody List<@Valid Product> products) {
        List<Product> allProducts = productService.saveList(products);
        return ResponseEntity.status(HttpStatus.CREATED).body(allProducts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @Valid @RequestBody Product product) {
        Product updatedProduct = productService.update(id, product);
        return ResponseEntity.ok(updatedProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/findByName")
    public ResponseEntity<List<Product>> findByName(@RequestParam String value) {
        return ResponseEntity.ok(productService.findByName(value));
    }

    @GetMapping("/findByNameContaining")
    public ResponseEntity<List<Product>> findByNameContaining(@RequestParam String value) {
        return ResponseEntity.ok(productService.findByNameContaining(value));
    }

    @GetMapping("/findByNameAndStatus")
    public ResponseEntity<List<Product>> findByNameAndStatus(@RequestParam String name, @RequestParam String status) {
        return ResponseEntity.ok(productService.findByNameAndStatus(name, status));
    }

    @GetMapping("/findByStartingWith")
    public ResponseEntity<List<Product>> findByStartingWith(@RequestParam String value) {
        return ResponseEntity.ok(productService.findByNameStartingWith(value));
    }

    @GetMapping("/findByEndingWith")
    public ResponseEntity<List<Product>> findByEndingWith(@RequestParam String value) {
        return ResponseEntity.ok(productService.findByNameEndingWith(value));
    }

    @GetMapping("/findByPrice")
    public ResponseEntity<List<Product>> findByPrice(@RequestParam Double price) {
        return ResponseEntity.ok(productService.findByPrice(price));
    }

    @GetMapping("/findByPriceGreaterThan")
    public ResponseEntity<List<Product>> findByPriceGreaterThan(@RequestParam Double price) {
        return ResponseEntity.ok(productService.findByPriceGreaterThan(price));
    }

    @GetMapping("/findByPriceLessThan")
    public ResponseEntity<List<Product>> findByPriceLessThan(@RequestParam Double price) {
        return ResponseEntity.ok(productService.findByPriceLessThan(price));
    }

    @GetMapping("/findTotalPrice")
    public Double findTotalPrice() {
        return productService.findTotalPrice();
    }

    @GetMapping("/countTotalProducts")
    public Long countTotalProducts() {
        return productService.countTotalProducts();
    }

    @GetMapping("/findByAmount")
    public ResponseEntity<List<Product>> findByAmount(@RequestParam Integer amount) {
        return ResponseEntity.ok(productService.findByAmount(amount));
    }

    @GetMapping("/findByAmountLessThan")
    public ResponseEntity<List<Product>> findByAmountLessThan(@RequestParam Integer amount) {
        return ResponseEntity.ok(productService.findByAmountLessThan(amount));
    }

    @GetMapping("/findByAmountGreaterThan")
    public ResponseEntity<List<Product>> findByAmountGreaterThan(@RequestParam Integer amount) {
        return ResponseEntity.ok(productService.findByAmountGreaterThan(amount));
    }

    @GetMapping("/findByStatus")
    public ResponseEntity<List<Product>> findByStatus(@RequestParam String status) {
        return ResponseEntity.ok(productService.findByStatus(status));
    }

    @GetMapping("/findByStatusIsNull")
    public ResponseEntity<List<Product>> findByStatusIsNull() {
        return ResponseEntity.ok(productService.findByStatusIsNull());
    }

    @GetMapping("/findByStatusAndPrice")
    public ResponseEntity<List<Product>> findByStatusAndPrice(@RequestParam String status, @RequestParam Double price) {
        return ResponseEntity.ok(productService.findByStatusAndPrice(status, price));
    }
}