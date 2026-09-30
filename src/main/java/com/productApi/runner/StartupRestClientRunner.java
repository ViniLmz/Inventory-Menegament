package com.productApi.runner;

import com.productApi.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Component
public class StartupRestClientRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(StartupRestClientRunner.class);

    @Override
    public void run(String... args) {
        RestClient restClient = RestClient.create();

        try {
            List<Product> products = restClient.get()
                    .uri("http://localhost:8080/products")
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<Product>>() {});

            System.out.println("================ LIST OF PRODUCT ================");
            if (products != null && !products.isEmpty()) {
                products.stream()
                        .filter(Objects::nonNull)
                        .forEach(p ->
                                System.out.println("ID:" + p.getId() + " | " + p.getName() + " | R$ " + p.getPrice())
                        );
            } else {
                System.out.println("None data found.");
            }
            System.out.println("==================================================");

        } catch (Exception e) {
            logger.warn("It wasn't able to load the products: : {}", e.getMessage());
        }
    }
}