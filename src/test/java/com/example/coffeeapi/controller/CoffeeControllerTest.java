package com.example.coffeeapi.controller;

import com.example.coffeeapi.model.Order;
import com.example.coffeeapi.model.Payment;
import com.example.coffeeapi.model.Product;
import com.example.coffeeapi.service.JsonFileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CoffeeControllerTest {

    @Mock
    private JsonFileService jsonFileService;

    @InjectMocks
    private CoffeeController coffeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testGetTotalPagado() {
        List<Payment> payments = Arrays.asList(
            new Payment("user1", 10.0),
            new Payment("user2", 5.0),
            new Payment("user1", 2.5)
        );
        when(jsonFileService.getPayments()).thenReturn(payments);

        Map<String, Double> result = coffeeController.getTotalPagado();
        Map<String, Double> expected = new HashMap<>();
        expected.put("user1", 12.5);
        expected.put("user2", 5.0);

        assertEquals(expected, result);
    }

    @Test
    void testGetDeuda() {

        List<Order> orders = Arrays.asList(
            new Order("user1", "Cappuccino", "medium"),
            new Order("user2", "Latte", "small")
        );
        List<Product> products = Arrays.asList(
            new Product("Cappuccino", Map.of("small", 3.0, "medium", 4.0)),
            new Product("Latte", Map.of("small", 2.5))
        );
        List<Payment> payments = Collections.singletonList(
            new Payment("user1", 2.0)
        );

        when(jsonFileService.getOrders()).thenReturn(orders);
        when(jsonFileService.getProducts()).thenReturn(products);
        when(jsonFileService.getPayments()).thenReturn(payments);

        Map<String, Double> result = coffeeController.getDeuda();
        Map<String, Double> expected = new HashMap<>();
        expected.put("user1", 2.0);  
        expected.put("user2", 2.5);  

        assertEquals(expected, result);
    }
}
