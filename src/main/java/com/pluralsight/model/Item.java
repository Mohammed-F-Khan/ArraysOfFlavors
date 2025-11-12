package com.pluralsight.model;

public class Item { // its like a general thing that can be put on an order
    String name; // what the user sees, e.g., "Drink - Soda"
    String size;
    double basePrice; // starting price before any add ons

    public Item(String name, String size, double basePrice) { // basically builds the item with given values
        this.name = name; // stores the name
        this.size = size; // stores the size
        this.basePrice = basePrice; // store the starting price
    }

    public String getName(){ return name; } // gives back the name when asked
    public String getSize(){ return size; } // give back the size when asked
    public double getTotal() { return basePrice; } // basic items just return base price
    public String toString() { return name + " (" + size + ")"; } // example "Ice cream - Vanilla (medium)
}
