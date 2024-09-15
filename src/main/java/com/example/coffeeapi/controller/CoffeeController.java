package com.example.coffeeapi.controller;

import com.example.coffeeapi.model.Order;
import com.example.coffeeapi.model.Payment;
import com.example.coffeeapi.model.Product;
import com.example.coffeeapi.service.JsonFileService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class CoffeeController {

    private final JsonFileService jsonFileService;

    public CoffeeController(JsonFileService jsonFileService) {
        this.jsonFileService = jsonFileService;
    }

    @GetMapping("/pagado")
    public Map<String, Double> getTotalPagado() {
        List<Payment> payments = jsonFileService.getPayments();
        return payments.stream()
            .collect(Collectors.groupingBy(Payment::getUser, Collectors.summingDouble(Payment::getAmount)));
    }

    @GetMapping("/deuda")
    public Map<String, Double> getDeuda() {
        List<Order> orders = jsonFileService.getOrders();
        List<Product> products = jsonFileService.getProducts();
        List<Payment> payments = jsonFileService.getPayments();

        Map<String, Double> totalPedidos = orders.stream()
            .collect(Collectors.groupingBy(Order::getUser, Collectors.summingDouble(order -> {
                return products.stream()
                    .filter(product -> product.getDrinkName().equals(order.getDrink()))
                    .findFirst()
                    .map(product -> product.getPrices().get(order.getSize()))
                    .orElse(0.0);
            })));

        Map<String, Double> totalPagos = payments.stream()
            .collect(Collectors.groupingBy(Payment::getUser, Collectors.summingDouble(Payment::getAmount)));

        return totalPedidos.entrySet().stream()
            .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue() - totalPagos.getOrDefault(entry.getKey(), 0.0)));
    }
}
