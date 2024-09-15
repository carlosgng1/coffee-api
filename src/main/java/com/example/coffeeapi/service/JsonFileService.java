package com.example.coffeeapi.service;

import com.example.coffeeapi.model.Order;
import com.example.coffeeapi.model.Payment;
import com.example.coffeeapi.model.Product;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonFileService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final String productsFilePath = "src/main/resources/products.json";
    private final String ordersFilePath = "src/main/resources/orders.json";
    private final String paymentsFilePath = "src/main/resources/payments.json";

    private List<Product> products;
    private List<Order> orders;
    private List<Payment> payments;

    public JsonFileService() {
        try {
            loadProducts(productsFilePath);
            loadOrders(ordersFilePath);
            loadPayments(paymentsFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Error loading JSON files", e);
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    private void loadProducts(String filePath) throws IOException {
        this.products = readJsonFile(filePath, Product[].class);
    }

    private void loadOrders(String filePath) throws IOException {
        this.orders = readJsonFile(filePath, Order[].class);
    }

    private void loadPayments(String filePath) throws IOException {
        this.payments = readJsonFile(filePath, Payment[].class);
    }

    private <T> List<T> readJsonFile(String filePath, Class<T[]> clazz) throws IOException {
        Path path = Path.of(filePath);
        String jsonContent = Files.readString(path);
        T[] array = objectMapper.readValue(jsonContent, clazz);
        return List.of(array);
    }

}
