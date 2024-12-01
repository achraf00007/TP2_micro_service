package net.elmardi.customerservice;

import net.elmardi.customerservice.entities.Customer;
import net.elmardi.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
        return args -> {
            customerRepository.save(Customer.builder()
                    .name("Achraf").email("achraf@gmail.com").build());

            customerRepository.save(Customer.builder()
                    .name("Anas").email("anas@gmail.com").build());

            customerRepository.save(Customer.builder()
                    .name("reda").email("reda@gmail.com").build());

            customerRepository.findAll().forEach(c->{
                System.out.println("______________________");
                System.out.println(c.getId());
                System.out.println(c.getName());
                System.out.println(c.getEmail());
                System.out.println("______________________");
            });
        };
    }
}
