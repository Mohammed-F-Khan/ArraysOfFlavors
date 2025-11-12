package com.pluralsight.model;

import java.util.ArrayList;

public class Order { // This represents one customer's complete order

    // Three separate lists for the three types of products
    ArrayList<Item> items  = new ArrayList<Item>();   // Ice cream items
    ArrayList<Drink> drinks = new ArrayList<Drink>(); // Drinks
    ArrayList<Side> sides  = new ArrayList<Side>();   // Sides

    // discount tracking
    String discountCode = null;
    double discountPercent = 0.0; // The discount amount (0.10 = 10% off)

    // Adds items to the order
    public void addItem(Item i){ items.add(i); }
    public void addDrink(Drink d){ drinks.add(d); }
    public void addSide(Side s){ sides.add(s); }

    // set a discount code and a percentage
    public void setDiscount(String code, double percent){
        discountCode = code; // remembers the code
        discountPercent = percent; // remebers the percentage
    }

    // Checks if order has no items
    public boolean hasNoItems(){
        return items.isEmpty(); // isEmpty() returns true if list has 0 elements
    }

    // Check if order has at least one drink or side
    public boolean hasDrinkOrSide(){
        return !drinks.isEmpty() || !sides.isEmpty(); //
    }

    // Calculate subtotal (before discount)
    public double getSubtotal(){
        double t = 0.0; // Starts at zero
        int i; // Loop counter

        // Add up all item prices
        for(i=0; i<items.size(); i++)
            t += items.get(i).getTotal(); // += basically means "add to"

        // Adds up all drink prices
        for(i=0; i<drinks.size(); i++)
            t += drinks.get(i).getTotal();

        // Adds up all side prices
        for(i=0; i<sides.size(); i++)
            t += sides.get(i).getTotal();

        return t; // Return the final sum
    }

    // this formats a dollar amount nicely (its a private helper method)
    private String money(double v){
        long cents = Math.round(v * 100.0); // Converts to cents (avoids decimal errors)
        long dollars = cents / 100;          // Get dollar part
        long rem = cents % 100;              // Get cents part (remainder)

        String remStr = "" + rem;            // Convert cents to string
        if(rem < 10){
            remStr = "0" + rem;              // Add leading zero if needed (09 not 9)
        }

        return dollars + "." + remStr;       // Combine: like this "5.09"
    }

    // calculate discount amount in dollars
    public double getDiscountAmount(){
        return getSubtotal() * discountPercent; // multiply subtotal by percent
    }

    // Calculate final total (after discount)
    public double getTotal(){
        return getSubtotal() - getDiscountAmount(); // Subtract discount from subtotal
    }

    // Create a summary text showing all items
    public String summary(){
        String text = ""; // start with empty string
        int i; // Loop counter

        // Loop through items Backwards so (newest comes first)
        for(i=items.size()-1; i>=0; i--){ // Start at last index, go down to 0
            Item it = items.get(i);
            text = text + "ITEM  " + it.toString() + " $" + money(it.getTotal()) + "\n";
        }

        // Loop through drinks BACKWARDS
        for(i=drinks.size()-1; i>=0; i--){
            Drink d = drinks.get(i);
            text = text + "DRINK " + d.toString() + " $" + money(d.getTotal()) + "\n";
        }

        // Loop through sides BACKWARDS
        for(i=sides.size()-1; i>=0; i--){
            Side s = sides.get(i);
            text = text + "SIDE  " + s.toString() + " $" + money(s.getTotal()) + "\n";
        }

        // Adds discount line if there is one
        if(discountCode != null){
            text = text + "DISCOUNT (" + discountCode + ") -$" + money(getDiscountAmount()) + "\n";
        }

        // Adds total line
        text = text + "TOTAL: $" + money(getTotal()) + "\n";

        return text; // Returns the complete summary
    }

    // detailed toppings block for ice cream items
    public String detailsBlock(){
        String text = ""; // Starts empty
        int i;

        // loop through items backwards
        for(i = items.size() - 1; i >= 0; i--){
            Item it = items.get(i);

            // this checks if this item is actually and IceCreamItem (USING INSTANCEOF)
            if(it instanceof com.pluralsight.model.IceCreamItem){
                // casts it to IceCremitem so we can call ice cream methods
                com.pluralsight.model.IceCreamItem ic = (com.pluralsight.model.IceCreamItem) it;
                text = text + " -> " +  ic.details() + "\n" // adds the details line
            }
        }
        return text; // finally returns all the details.
    }
}
