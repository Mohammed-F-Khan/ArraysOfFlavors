package com.pluralsight.model;

import com.pluralsight.pricing.Pricing;

import java.util.ArrayList;
import java.util.HashMap;

// this class extends item adds all the ice cream - specific features.
public class IceCreamItem extends Item { // Ice cream is an item
    String type; // cup, waffle cone, etc.
    String baseFlavor; // which flavor

    // store lists - regular toppings are free, so just track names
    ArrayList<String> regularToppings = new ArrayList<String>();

    // stores key value pairs - tracks premium toppings and their counts
    HashMap<String,Integer> premiumToppings = new HashMap<String,Integer>();
    HashMap<String,Integer> premiumSauces = new HashMap<String,Integer>();

    // More regular items (condiments are free sauces)
    ArrayList<String> condiments = new ArrayList<String>();

    // Boolean - true or false - did they add the special?
    boolean specialApplied = false;

    // Constructor to create a new ice cream item
    public IceCreamItem(String baseFlavor, String type, String size){
        // calls the parent (item) constructor using super
        super("Ice Cream - " + baseFlavor + " - " + type, size,
                Pricing.getBasePrice(baseFlavor, size)); // gets price from Pricing

        this.type = type; // save container type
        this.baseFlavor = baseFlavor; // save flavor
    }

    // Add a regular topping (free)
    public void addRegularTopping(String name){
        regularToppings.add(name); // Adds to the end of the list
    }

    // Add a premium topping with a count already(paid)
    public void addPremiumTopping(String name, int count){
        premiumToppings.put(name, count); // Stores name and count
    }

    // Add a premium sauce with a count already(paid)
    public void addPremiumSauce(String name, int count){
        premiumSauces.put(name, count); // Stores name and count
    }

    // Add a condiment (free sauce)
    public void addCondiment(String name){
        condiments.add(name); // Adds to list
    }

    // Turn on the special feature
    public void applySpecial(){
        specialApplied = true; // Changes boolean to true
    }

    // get total - calculates the final price with all the add ons
    public double getTotal(){
        double total = basePrice; // starts with the base flavor price

        // add container upgraded costs
        if (type.equalsIgnoreCase("Waffle Cone"))
            total += Pricing.TYPE_WAFFLE_CONE;
        else if (type.equalsIgnoreCase("Chocolate-Dipped Cone"))
            total += Pricing.TYPE_CHOC_DIP_CONE;
        else if (type.equalsIgnoreCase("Cookie Bowl"))
            total += Pricing.TYPE_COOKIE_BOWL;
        else if (type.equalsIgnoreCase("Sundae Dish"))
            total += Pricing.TYPE_SUNDAE_DISH;

        // Premium pricing is where first unit costs more, extras cost less
        double firstPremium = Pricing.getPremiumPrice(size); // first unit price
        double extraPremium = Pricing.getExtraPremiumPrice(size); // extra unit price

        // loops through all premium toppings
        for(String key : premiumToppings.keySet()){
            int count = premiumToppings.get(key); // how many of  THIS topping?
            if(count >= 1) total += firstPremium;
            if(count > 1) total += extraPremium * (count-1);
        }

        // this adds special class if the user chooses it.
        if(specialApplied) total += Pricing.getSpecialPrice(size);

        return total; // returns the final price.
    }

    // A detailed description of all choices
    public String details(){
        return baseFlavor + " [" + type + "] " +
                "Regular=" + regularToppings +
                ", Premium=" + premiumToppings +
                ", Sauces=" + premiumSauces +
                ", Condiments=" + condiments +
                ", Special=" + specialApplied;
    }
}
