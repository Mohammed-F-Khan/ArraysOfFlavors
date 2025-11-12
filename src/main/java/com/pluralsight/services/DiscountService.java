package com.pluralsight.services;

public class DiscountService { // Handles discount code logic

    // Generates a code based on an email address
    public String generateCodeForEmail(String email){
        int n = Math.abs(email.hashCode()); // get a number from the email (same email = same number)

        // if the number is even, give 10% off
        if(n % 2 == 0) return  "AF-10-OFF";

        // if the number is odd, give 15% off
        return "AF-15-OFF";
    }

    // check if a code is valid
    public boolean validate(String code){
        // Check if code matches either valid option
        if("AF-10-OFF".equalsIgnoreCase(code)) return true;
        if("AF-15-OFF".equalsIgnoreCase(code)) return true;
        return false; // Not valid
    }

    // Gets the discount percentage for a code
    public double getPercentOff(String code) {
        if ("AF-15-OFF".equalsIgnoreCase(code)) return 0.15; // 15% = 0.15
        if ("AF-10-OFF".equalsIgnoreCase(code)) return 0.10; // 10% = 0.10
        return 0.0; // No discount if code doesn't match
    }
}
