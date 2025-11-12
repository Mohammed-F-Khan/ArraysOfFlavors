package com.pluralsight.pricing;

import java.util.HashMap;

public class Pricing { // Hold all prices
    static HashMap<String, double[]> flavorPrices = new HashMap<String, double[]>();
    // this is the map of flavors to all the prices

    static {// this block runs once when this class used, so it loads prices only once.
        flavorPrices.put("Classic Vanilla Loop", new double[]{3.50, 5.75, 7.25}); // Base prices per size
        flavorPrices.put("Double Chocolate Crunch", new double[]{3.75, 6.00, 7.75}); // base prices per size
        flavorPrices.put("Strawberry Shortcode",  new double[]{3.50, 5.75, 7.25}); // base prices per size
        flavorPrices.put("Mint Chip Matrix",      new double[]{3.75, 6.00, 7.75}); // base prices per size
        flavorPrices.put("Cookie Circuit",        new double[]{4.00, 6.50, 8.00}); // base prices per size
        flavorPrices.put("Java Caramel Swirl",    new double[]{4.25, 6.75, 8.25}); // base prices per size
        flavorPrices.put("Margarita",    new double[]{4.50, 7.00, 8.50}); // special flavor base prices per size
    }

    public static final double TYPE_WAFFLE_CONE = 1.00; // extra cost if user picks waffle cone
    public static final double TYPE_CHOC_DIP_CONE = 1.50; // extra cost if user picks chocolate-dipped cone
    public static final double TYPE_COOKIE_BOWL   = 2.00; // extra cost if user picks cookie bowl
    public static final double TYPE_SUNDAE_DISH   = 2.50; // extra cost if user picks sundae dish

    public static double getBasePrice(String flavor, String size) { // this returns the base price using the flavor and sizes
        double[] prices = flavorPrices.get(flavor); // look up the [small, medium, large] array
        if (size.equalsIgnoreCase("Small")) return prices[0]; // this returns the small price
        if (size.equalsIgnoreCase("Medium")) return prices[1]; // this returns medium price.\
        return prices[2]; // otherwise returns the large price
    }
    public static double getPremiumPrice(String size) { // price for the first unit of any premium topping/sauce
        if (size.equalsIgnoreCase("Small"))  return 1.25; // small item premium cost
        if (size.equalsIgnoreCase("Medium")) return 2.00; // medium item premium cost
        return 2.75; // large item premium cost
    }

    public static double getExtraPremiumPrice(String size) { // price for each extra (beyond the first) unit of a premium
        if (size.equalsIgnoreCase("Small"))  return 0.50; // small item extra cost per extra unit
        if (size.equalsIgnoreCase("Medium")) return 1.00; // medium item extra cost per extra unit
        return 1.50; // large item extra cost per extra unit
    }

    public static double getSpecialPrice(String size) { // price to add the "special" option
        if (size.equalsIgnoreCase("Small"))  return 1.00; // small special price
        if (size.equalsIgnoreCase("Medium")) return 1.50; // medium special price
        return 2.00; // large special price
    }

    public static double getDrinkPrice(String flavor, String size) { // returns drink price using type and size
        if (flavor.equalsIgnoreCase("Bottled Water")) return 1.50; // water is a flat price
        if (flavor.equalsIgnoreCase("Soda")) { // soda uses sizes
            if (size.equalsIgnoreCase("Small"))  return 2.00; // small soda price
            if (size.equalsIgnoreCase("Medium")) return 2.50; // medium soda price
            return 3.00; // large soda price
        }
        if (flavor.equalsIgnoreCase("Iced Coffee")) { // iced coffee uses sizes
            if (size.equalsIgnoreCase("Small"))  return 2.25; // small iced coffee price
            if (size.equalsIgnoreCase("Medium")) return 2.75; // medium iced coffee price
            return 3.25; // large iced coffee price
        }
        if (flavor.equalsIgnoreCase("Milkshake")) { // milkshake uses sizes
            if (size.equalsIgnoreCase("Small"))  return 3.50; // small milkshake price
            if (size.equalsIgnoreCase("Medium")) return 4.50; // medium milkshake price
            return 5.50; // large milkshake price
        }
        return 0.0; // unknown drink flavor goes back to 0
    }

    public static double getSidePrice(String side) { // returns side price using its name
        if (side.equalsIgnoreCase("Cookie Pair")) return 2.25; // price for cookie pair
        if (side.equalsIgnoreCase("Brownie Square")) return 2.50; // price for brownie square
        if (side.equalsIgnoreCase("Main Side")) return 1.50; // required filler side price
        return 3.00; // default price fore mini waffle bites.

    }
}
