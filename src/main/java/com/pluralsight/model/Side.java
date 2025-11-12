package com.pluralsight.model;

import com.pluralsight.pricing.Pricing;

public class Side extends Item { // side is an item
    String sideType; // cookie pair, brownie square, etc.

    // contructor
    public Side(String sideType){
        super("Side - " + sideType, "Small", 0.0); // sides are automatically named small size
        this.sideType = sideType; // saves which side
        this.basePrice = Pricing.getSidePrice(sideType); // looks up price.
    }

    // Sides don't have add-ons either
    public double getTotal(){
        return basePrice;
    }
}
