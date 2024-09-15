package com.example.coffeeapi.model;

public class Payment {
    private String user;
    private double amount;

    public Payment() {
    }

    public Payment(String user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    // Getters y Setters
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
