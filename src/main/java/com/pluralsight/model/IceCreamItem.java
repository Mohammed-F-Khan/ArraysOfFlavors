package com.pluralsight.model;

// Pricing class so we can use its pricing methods
import com.pluralsight.pricing.Pricing;
// list (like an array that can grow)
import java.util.ArrayList;
// dictionary-like structure with key-value pairs
import java.util.HashMap;

// IceCreamItem extends Item - this means it inherits all variables and methods from Item
public class IceCreamItem extends Item {

    // Additional instance variables specific to ice cream items
    String type;                               // Container type (e.g., "Cup", "Waffle Cone")
    String baseFlavor;                         // Ice cream flavor (e.g., "Vanilla", "Chocolate")
    ArrayList<String> regularToppings;         // List of free toppings (e.g., ["sprinkles", "cherries"])
    HashMap<String, Integer> premiumToppings;  // Map of premium toppings to their counts (e.g., {"brownie chunks" -> 2})
    HashMap<String, Integer> premiumSauces;    // Map of premium sauces to their counts (e.g., {"fudge sauce" -> 1})
    ArrayList<String> condiments;              // List of free condiments (e.g., ["Chocolate Syrup"])
    boolean specialApplied;                    // True if Nitro Chill Finish was added, false otherwise

    // Constructor - creates a new IceCreamItem
    // Takes in: baseFlavor (String), type (String), size (String)
    public IceCreamItem(String baseFlavor, String type, String size) {
        // Call the parent class (Item) constructor using "super"
        // This changes name, size, and basePrice from the Item class
        // creates the name by combining "Ice Cream - " + flavor + " - " + type
        // We get the basePrice by calling Pricing.getBasePrice() method
        super("Ice Cream - " + baseFlavor + " - " + type, size, Pricing.getBasePrice(baseFlavor, size));

        // change the ice cream specific variables
        this.type = type;                                      // Store container type
        this.baseFlavor = baseFlavor;                          // Store flavor
        this.regularToppings = new ArrayList<String>();        // Create empty list for regular toppings
        this.premiumToppings = new HashMap<String, Integer>(); // Create empty map for premium toppings
        this.premiumSauces = new HashMap<String, Integer>();   // Create empty map for premium sauces
        this.condiments = new ArrayList<String>();             // Create empty list for condiments
        this.specialApplied = false;                           // Start with no special finish
    }

    // Method to add a regular topping (free)
    public void addRegularTopping(String name) {
        // add() method to append the topping to the end of the list
        regularToppings.add(name);
    }

    // Method to add a premium topping with a count
    public void addPremiumTopping(String name, int count) {
        // Use put() method to add or update the topping in the map
        // Key = topping name, Value = how many of this topping
        premiumToppings.put(name, count);
    }

    // Method to add a premium sauce with a count
    // Takes in: name of the sauce (String) and count (int)
    // Returns: nothing (void)
    public void addPremiumSauce(String name, int count) {
        // Use put() method to add or update the sauce in the map
        premiumSauces.put(name, count);
    }

    // Method to add a condiment (free)
    // Takes in: name of the condiment (String)
    // Returns: nothing (void)
    public void addCondiment(String name) {
        // Use add() method to append the condiment to the list
        condiments.add(name);
    }

    // Method to apply the special Nitro Chill Finish
    // Takes in: nothing
    // Returns: nothing (void)
    public void applySpecial() {
        // Set it to true, indicating special was added
        specialApplied = true;
    }

    // Overrides the getTotal() method from the parent Item class
    // This calculates the complete price including all additions
    // Returns: (total price)
    public double getTotal() {
        // Starts with the base price (from parent class)
        double total = basePrice;

        // Adds container upgrade cost based on type
        // heck which container type was chosen

        if (type.equalsIgnoreCase("Waffle Cone")) {
            // If Waffle Cone, add $1.00
            total = total + Pricing.TYPE_WAFFLE_CONE;

        } else if (type.equalsIgnoreCase("Chocolate-Dipped Cone")) {
            // If Chocolate-Dipped Cone, add $1.50
            total = total + Pricing.TYPE_CHOC_DIP_CONE;

        } else if (type.equalsIgnoreCase("Cookie Bowl")) {
            // If Cookie Bowl, add $2.00
            total = total + Pricing.TYPE_COOKIE_BOWL;

        } else if (type.equalsIgnoreCase("Sundae Dish")) {
            // If Sundae Dish, add $2.50
            total = total + Pricing.TYPE_SUNDAE_DISH;
        }
        // If type is "Cup", we don't add anything (it's the base price)

        // Get the pricing for first and extra premiums based on size
        double firstPremium = Pricing.getPremiumPrice(size);      // Price for first premium
        double extraPremium = Pricing.getExtraPremiumPrice(size); // Price for each additional premium

        // loop counter variable
        int i;

        // Calculate cost for premium toppings
        // convert the HashMap (topping names) to an array so we can loop through them
        String[] toppingKeys = premiumToppings.keySet().toArray(new String[0]);

        // Loops through each premium topping
        for (i = 0; i < toppingKeys.length; i++) {
            // Gets the current topping name
            String key = toppingKeys[i];

            // Looks up how many of this topping the customer wants
            int count = premiumToppings.get(key);

            // If they want at least 1, this adds the first premium price
            if (count >= 1) {
                total = total + firstPremium;
            }

            // If they want more than 1, add extra premium price for each additional one
            if (count > 1) {
                total = total + (extraPremium * (count - 1));
            }
        }

        // Calculate cost for premium sauces (same logic as toppings)
        // Converts HashMap keys to array
        String[] sauceKeys = premiumSauces.keySet().toArray(new String[0]);

        // Loop through each premium sauce
        for (i = 0; i < sauceKeys.length; i++) {
            // Gets sauce name
            String key = sauceKeys[i];

            // Looks up count
            int count = premiumSauces.get(key);

            // Add first premium price if count >= 1
            if (count >= 1) {
                total = total + firstPremium;
            }

            // Adds extra premium price for each additional sauce
            if (count > 1) {
                total = total + (extraPremium * (count - 1));
            }
        }

        // Add special finish cost if it was applied
        if (specialApplied) {
            // Get special price based on size and add it to total
            total = total + Pricing.getSpecialPrice(size);
        }

        // Return the final total price
        return total;
    }

    // Method that creates a detailed string showing all customizations
    // This is used in receipts to show what toppings/options were selected
    // Returns: (detailed description)
    public String details() {
        // Builds a string with all the details
        // We concatenate baseFlavor, type, and all the collections
        return baseFlavor + " [" + type + "] " +
                "Regular=" + regularToppings + ", " +
                "Premium=" + premiumToppings + ", " +
                "Sauces=" + premiumSauces + ", " +
                "Condiments=" + condiments + ", " +
                "Special=" + specialApplied;
    }
}