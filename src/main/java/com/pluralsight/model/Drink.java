package com.pluralsight.model;

import com.pluralsight.pricing.Pricing;

public class Drink extends Item { // Drink is an item
    String flavor; // soda, iced tea, coffee, etc.

    // constructor
    public Drink(String flavor, String size){
        super("Drink - " + flavor, size, 0.0); // this calls the item constructor with the temporary price of 0
        this.flavor = flavor;
        this.basePrice = Pricing.getDrinkPrice(flavor, size); // now set real price
    }

    // Drinks don't have add-ons in my shop so total is base price
    public double getTotal(){
        return basePrice;
    }
}
