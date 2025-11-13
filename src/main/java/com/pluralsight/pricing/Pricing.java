package com.pluralsight.pricing;

// stores key-value pairs
import java.util.HashMap;

// This class handles all pricing logic for the ice cream shop
public class Pricing {

    // HashMap to store flavor prices
    // Key = flavor name (String), Value = array of 3 prices (double[])
    // Static means this variable belongs to the class, not individual objects
    static HashMap<String, double[]> flavorPrices = new HashMap<String, double[]>();

    // Static block - runs ONE TIME when the class is first loaded into memory
    // all our flavor prices
    static {
        // For each flavor,array with 3 prices: [Small, Medium, Large]
        // put() method adds a key-value pair to the HashMap

        // Add Classic Vanilla Loop with prices: Small=$3.50, Medium=$5.75, Large=$7.25
        flavorPrices.put("Classic Vanilla Loop", new double[]{3.50, 5.75, 7.25});

        // Add Double Chocolate Crunch with prices: Small=$3.75, Medium=$6.00, Large=$7.75
        flavorPrices.put("Double Chocolate Crunch", new double[]{3.75, 6.00, 7.75});

        // Add Strawberry Shortcode with prices: Small=$3.50, Medium=$5.75, Large=$7.25
        flavorPrices.put("Strawberry Shortcode", new double[]{3.50, 5.75, 7.25});

        // Add Mint Chip Matrix with prices: Small=$3.75, Medium=$6.00, Large=$7.75
        flavorPrices.put("Mint Chip Matrix", new double[]{3.75, 6.00, 7.75});

        // Add Cookie Circuit with prices: Small=$4.00, Medium=$6.50, Large=$8.00
        flavorPrices.put("Cookie Circuit", new double[]{4.00, 6.50, 8.00});

        // Add Java Caramel Swirl with prices: Small=$4.25, Medium=$6.75, Large=$8.25
        flavorPrices.put("Java Caramel Swirl", new double[]{4.25, 6.75, 8.25});

        // Add Midnight Margarita with prices: Small=$4.50, Medium=$7.00, Large=$8.50
        flavorPrices.put("Margarita", new double[]{4.50, 7.00, 8.50});
    }


    // These never change

    public static final double TYPE_WAFFLE_CONE = 1.00;
    public static final double TYPE_CHOC_DIP_CONE = 1.50;
    public static final double TYPE_COOKIE_BOWL = 2.00;
    public static final double TYPE_SUNDAE_DISH = 2.50;

    // Method to get the base price for a specific flavor and size
    public static double getBasePrice(String flavor, String size) {
        // get() method to look up the flavor in our HashMap
        // This returns the array of 3 prices for this flavor
        double[] prices = flavorPrices.get(flavor);

        // if flavor doesn't exist in our HashMap, return 0
        if (prices == null) {
            return 0.0;
        }

        // If size is "Small", return the first price in the array (index 0)
        if (size.equalsIgnoreCase("Small")) {
            return prices[0];
        }

        // If size is "Medium", return the second price in the array (index 1)
        if (size.equalsIgnoreCase("Medium")) {
            return prices[1];
        }

        // Otherwise (Large), return the third price in the array (index 2)
        return prices[2];
    }

    // Method to get the price for the FIRST premium topping or sauce
    // The first one costs more than additional ones
    public static double getPremiumPrice(String size) {
        // For Small size, first premium costs $1.25
        if (size.equalsIgnoreCase("Small")) {
            return 1.25;
        }
        // For Medium size, first premium costs $2.00
        if (size.equalsIgnoreCase("Medium")) {
            return 2.00;
        }
        // For Large size, first premium costs $2.75
        return 2.75;
    }

    // Method to get the price for EXTRA premium toppings/sauces (after the first one)
    // Additional premiums cost less than the first one
    public static double getExtraPremiumPrice(String size) {
        // For Small size, each extra premium costs $0.50
        if (size.equalsIgnoreCase("Small")) {
            return 0.50;
        }
        // For Medium size, each extra premium costs $1.00
        if (size.equalsIgnoreCase("Medium")) {
            return 1.00;
        }
        // For Large size, each extra premium costs $1.50
        return 1.50;
    }

    // Method to get the price for the special "Nitro Chill Finish" option

    public static double getSpecialPrice(String size) {
        // For Small size, special finish costs $1.00
        if (size.equalsIgnoreCase("Small")) {
            return 1.00;
        }
        // For Medium size, special finish costs $1.50
        if (size.equalsIgnoreCase("Medium")) {
            return 1.50;
        }
        // For Large size, special finish costs $2.00
        return 2.00;
    }

    // Method to get the price for a drink based on type and size
    public static double getDrinkPrice(String flavor, String size) {
        // If drink is Bottled Water, it's a flat price of $1.50 (no size options)
        if (flavor.equalsIgnoreCase("Bottled Water")) {
            return 1.50;
        }

        // If drink is Soda, price depends by size
        if (flavor.equalsIgnoreCase("Soda")) {
            // Small soda costs $2.00
            if (size.equalsIgnoreCase("Small")) {
                return 2.00;
            }
            // Medium soda costs $2.50
            if (size.equalsIgnoreCase("Medium")) {
                return 2.50;
            }
            // Large soda costs $3.00
            return 3.00;
        }

        // If drink is Iced Coffee, price depends by size
        if (flavor.equalsIgnoreCase("Iced Coffee")) {
            // Small iced coffee costs $2.25
            if (size.equalsIgnoreCase("Small")) {
                return 2.25;
            }
            // Medium iced coffee costs $2.75
            if (size.equalsIgnoreCase("Medium")) {
                return 2.75;
            }
            // Large iced coffee costs $3.25
            return 3.25;
        }

        // If drink is Milkshake, price depends by size
        if (flavor.equalsIgnoreCase("Milkshake")) {
            // Small milkshake costs $3.50
            if (size.equalsIgnoreCase("Small")) {
                return 3.50;
            }
            // Medium milkshake costs $4.50
            if (size.equalsIgnoreCase("Medium")) {
                return 4.50;
            }
            // Large milkshake costs $5.50
            return 5.50;
        }

        // If the drink type is not recognizable, return 0
        return 0.0;
    }

    // Method to get the price for a side item

    public static double getSidePrice(String side) {
        // Cookie Pair costs $2.25
        if (side.equalsIgnoreCase("Cookie Pair")) {
            return 2.25;
        }
        // Brownie Square costs $2.50
        if (side.equalsIgnoreCase("Brownie Square")) {
            return 2.50;
        }
        // Main Side costs $1.50
        if (side.equalsIgnoreCase("Main Side")) {
            return 1.50;
        }
        // Mini Waffle Bites (default) costs $3.00
        return 3.00;
    }
}