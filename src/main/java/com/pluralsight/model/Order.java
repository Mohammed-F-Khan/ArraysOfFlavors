package com.pluralsight.model;



import java.util.ArrayList;

// This class represents a complete order with multiple items, drinks, and sides
public class Order {

    // variables - collections to store all parts of the order
    ArrayList<Item> items;      // List to hold all ice cream items
    ArrayList<Drink> drinks;    // List to hold all drinks
    ArrayList<Side> sides;      // List to hold all sides
    String discountCode;        // The coupon code (null if no discount)
    double discountPercent;     // The discount percentage (0.0 to 1.0, e.g., 0.10 = 10% off)

    // Constructor - creates a new empty order
    // Takes in: nothing
    public Order() {
        // all ArrayLists as empty lists
        items = new ArrayList<Item>();      // Create empty list for items
        drinks = new ArrayList<Drink>();    // Create empty list for drinks
        sides = new ArrayList<Side>();      // Create empty list for sides

        // change discount fields to "no discount"
        discountCode = null;        // No code yet
        discountPercent = 0.0;      // 0% discount
    }

    // Method to add an ice cream item to the order
    public void addItem(Item i) {
        // add() method to append the item to the end of the list
        items.add(i);
    }

    // Method to add a drink to the order
    public void addDrink(Drink d) {
        // add() method to append the drink to the end of the list
        drinks.add(d);
    }

    // Method to add a side to the order
    public void addSide(Side s) {
        // add() method to append the side to the end of the list
        sides.add(s);
    }

    // Method to apply a discount code to the order
    // Takes in: code (String) and percent (e.g., 0.15 for 15% off)
    public void setDiscount(String code, double percent) {
        // Stores the discount code
        discountCode = code;
        // Stores the discount percentage
        discountPercent = percent;
    }

    // Method to check if order has no ice cream items
    // Returns: boolean (true if items list is empty, false otherwise)
    public boolean hasNoItems() {
        // isEmpty() returns true if the ArrayList has 0 elements
        return items.isEmpty();
    }

    // Method to check if order has at least one drink or side
    // This is used to enforce the rule: if no ice cream, must have drink/side
    // Returns: true if there's at least one drink or side)
    public boolean hasDrinkOrSide() {
        // Uses ! to flip the result
        // If drinks is NOT empty OR sides is NOT empty, returns true
        return !drinks.isEmpty() || !sides.isEmpty();
    }

    // Method to calculate the subtotal (before discount)
    // Returns: (the subtotal price)
    public double getSubtotal() {
        // Starts with 0
        double total = 0.0;

        // the loop counter
        int i;

        // Loop through all ice cream items and add their totals
        for (i = 0; i < items.size(); i++) {
            // get(i) returns the item at index i
            // getTotal() returns the price of that item
            // Add it to the running total
            total = total + items.get(i).getTotal();
        }

        // Loop through all drinks and add their totals
        for (i = 0; i < drinks.size(); i++) {
            total = total + drinks.get(i).getTotal();
        }

        // Loop through all sides and add their totals
        for (i = 0; i < sides.size(); i++) {
            total = total + sides.get(i).getTotal();
        }

        // Returns the complete subtotal
        return total;
    }

    // Method to calculate the discount amount in dollars
    // Returns: (the discount amount)
    public double getDiscountAmount() {
        // Multiply subtotal by discount percentage
        // Example: subtotal=$20, discountPercent=0.10 -> returns $2.00
        return getSubtotal() * discountPercent;
    }

    // Method to calculate the final total (after discount)
    // Returns: (the final total)
    public double getTotal() {
        // Subtract discount amount from subtotal
        return getSubtotal() - getDiscountAmount();
    }

    // Private helper method to format a dollar amount as a string
    private String money(double value) {
        // Multiply by 100 and round to get total cents
        // Math.round() rounds to nearest whole number
        // Example: 5.567 * 100 = 556.7 -> rounds to 557 cents
        long cents = Math.round(value * 100.0);

        // Divide by 100 to get dollar part
        // Example: 557 / 100 = 5 dollars
        long dollars = cents / 100;

        // (%) to get remainder (cents part)
        // Example: 557 % 100 = 57 cents
        long remainder = cents % 100;

        // Converts remainder to string
        String remainderString = "" + remainder;

        // If remainder is less than 10, this adds leading zero
        // Example: 5 becomes "05" so we get "5.05" not "5.5"
        if (remainder < 10) {
            remainderString = "0" + remainder;
        }

        // Concatenate dollars, decimal point, and cents
        // Example: "5" + "." + "57" = "5.57"
        return dollars + "." + remainderString;
    }

    // Method to create a summary string for the order
    // This is used in receipts and checkout screens
    // Returns: String (formatted order summary)
    public String summary() {
        // Starts with empty string
        String text = "";

        // Declare loop counter
        int i;

        // Loop through items BACKWARDS (newest first)
        for (i = items.size() - 1; i >= 0; i--) {
            Item it = items.get(i);

            // Format: "ITEM: [name] ............... $[price]"
            // Use String.format for nice alignment
            text = text + String.format("ITEM:  %-40s $%6s\n",
                    it.toString(),
                    money(it.getTotal()));
        }

        // Loop through drinks backwards
        for (i = drinks.size() - 1; i >= 0; i--) {
            Drink d = drinks.get(i);
            text = text + String.format("DRINK: %-40s $%6s\n",
                    d.toString(),
                    money(d.getTotal()));
        }

        // Loop through sides backwards
        for (i = sides.size() - 1; i >= 0; i--) {
            Side s = sides.get(i);
            text = text + String.format("SIDE:  %-40s $%6s\n",
                    s.toString(),
                    money(s.getTotal()));
        }

        // Add a blank line before totals
        text = text + "\n";
        text = text + "                                            ----------------\n";

        // If there's a discount code, add discount line
        if (discountCode != null) {
            text = text + String.format("SUBTOTAL: %38s $%6s\n",
                    "",
                    money(getSubtotal()));
            text = text + String.format("DISCOUNT (%s): %28s -$%6s\n",
                    discountCode,
                    "",
                    money(getDiscountAmount()));
            text = text + "                                            ----------------\n";
        }

        // Add final total line
        text = text + String.format("TOTAL: %41s $%6s\n",
                "",
                money(getTotal()));
        text = text + "                                            ================\n";

        // Return the complete summary
        return text;
    }

    // Method to create a detailed block showing toppings for each item
    // This is used in receipts to show customization details
    // Returns: (formatted details)
    public String detailsBlock() {
        // Start with empty string
        String text = "";

        // loop counter
        int i;

        // Loop through items backwards
        for (i = items.size() - 1; i >= 0; i--) {
            // Gets the item
            Item it = items.get(i);

            // Check if this item is an IceCreamItem (using instanceof)
            // instanceof returns true if the object is of the specified type
            if (it instanceof IceCreamItem) {
                // (convert) the Item to IceCreamItem so we can call details()
                // This is safe because we checked with instanceof first
                IceCreamItem ic = (IceCreamItem) it;

                // Adds the detailed info with " -> " prefix
                text = text + " -> " + ic.details() + "\n";
            }
        }

        // Return the complete details block
        return text;
    }
}