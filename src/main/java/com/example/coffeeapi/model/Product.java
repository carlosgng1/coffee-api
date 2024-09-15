package com.example.coffeeapi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Product {
    @JsonProperty("drink_name")
    private String drinkName;
    private Map<String, Double> prices;

    // Constructor por defecto
    public Product() {
    }

    // Constructor con parámetros
    public Product(String drinkName, Map<String, Double> prices) {
        this.drinkName = drinkName;
        this.prices = prices;
    }

    // Getters y Setters
    public String getDrinkName() {
        return drinkName;
    }

    public void setDrinkName(String drinkName) {
        this.drinkName = drinkName;
    }

    public Map<String, Double> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Double> prices) {
        this.prices = prices;
    }
}
