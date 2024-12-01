package net.elmardi.inventoryservice;

import net.elmardi.inventoryservice.entities.Product;
import net.elmardi.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class InventoryServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepository productRepository) {
        return args -> {
            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Computer")
                    .price(3500)
                    .quantity(13)
                    .build());

            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Printer")
                    .price(1299)
                    .quantity(3)
                    .build());

            productRepository.save(Product.builder()
                    .id(UUID.randomUUID().toString())
                    .name("Smart phone")
                    .price(799)
                    .quantity(22)
                    .build());

            productRepository.findAll().forEach(p->{
                System.out.println(p.toString());
            });
        };
    }
}
