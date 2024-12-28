package com.example.billingservice.web;

import com.example.billingservice.entities.Bill;
import com.example.billingservice.feign.CustomerRestClient;
import com.example.billingservice.feign.ProductRestClient;
import com.example.billingservice.repository.BillRepository;
import com.example.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillRestController {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private ProductItemRepository productItemRepository;

    @Autowired
    private CustomerRestClient customerRestClient;

    @Autowired
    private ProductRestClient productRestClient;

    @GetMapping(path = "/bills/{id}")
    public Bill getBill(@PathVariable Long id) {
        // Fetch the bill from the repository
        Bill bill = billRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bill not found with id: " + id));

        // Fetch customer details
        try {
            bill.setCustomer(customerRestClient.getCustomerById(bill.getCustomerId()));
        } catch (Exception e) {
            throw new RuntimeException("Error fetching customer details for customerId: " + bill.getCustomerId(), e);
        }

        // Fetch product details for each product item
        bill.getProductItems().forEach(productItem -> {
            try {
                productItem.setProduct(productRestClient.getProductById(productItem.getProductId()));
            } catch (Exception e) {
                throw new RuntimeException("Error fetching product details for productId: " + productItem.getProductId(), e);
            }
        });

        return bill;
    }
}

