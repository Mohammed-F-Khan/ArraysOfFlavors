package com.pluralsight.model;

// Import the Pricing class to access side prices
import com.pluralsight.pricing.Pricing;

// Side class extends Item - it inherits from the Item parent class
public class Side extends Item {

    // Additional variable that is specific to sides
    String sideType;  // The type of side (e.g., "Cookie Pair", "Brownie Square")

    // Constructor - creates a new Side object
    // Takes in: sideType
    // Note: sides don't have sizes, so we hard-code "Small" as the size
    public Side(String sideType) {
        // Call parent Item constructor using super
        // Name is created as "Side - " + sideType (e.g., "Side - Cookie Pair")
        // Base price starts at 0.0 (we'll set it on the next line)
        super("Side - " + sideType, "Small", 0.0);

        // Stores the side type
        this.sideType = sideType;

        // Look up the actual price using Pricing class and store it in basePrice
        this.basePrice = Pricing.getSidePrice(sideType);
    }

    // For sides, total is just the base price (no additions)
    // Returns: (the total price)
    public double getTotal() {
        return basePrice;
    }
}