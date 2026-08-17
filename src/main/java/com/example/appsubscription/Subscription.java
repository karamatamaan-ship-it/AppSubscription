package com.example.appsubscription;

public class Subscription {
    private String name;
    private double price;

    // Constructor: Name first, Price second
    public Subscription(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
}