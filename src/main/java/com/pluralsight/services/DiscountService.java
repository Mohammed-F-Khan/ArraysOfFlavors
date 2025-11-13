package com.pluralsight.services;

// This class handles discount code generation and validation
public class DiscountService {

    // Method to generate a discount code based on an email address
    // Takes in: email (String)
    // Returns: (the discount code)
    public String generateCodeForEmail(String email) {
        // Get the length of the email string
        // length() returns how many characters are in the string
        int emailLength = email.length();

        // Checks if the length is even or odd using operator (%)
        // % 2 gives the remainder when divided by 2
        // If remainder is 0, the number is even
        // If remainder is 1, the number is odd
        if (emailLength % 2 == 0) {
            // If email length is even (2, 4, 6, 8...), give 10% off code
            return "MAR-10-OFF";
        } else {
            // If email length is odd (1, 3, 5, 7...), give 15% off code
            return "DAL-15-OFF";
        }
    }

    // Method to check if a discount code is valid
    public boolean validate(String code) {
        // Checks if the code equals "MAR-10-OFF"
        if ("MAR-10-OFF".equalsIgnoreCase(code)) {
            return true;  // if This is a valid code, return true
        }

        // Check if the code equals "DAL-15-OFF"
        if ("DAL-15-OFF".equalsIgnoreCase(code)) {
            return true;  // This is a valid code, return true
        }

        // If the code doesn't match either valid code, it's invalid
        return false;  // Returns false for invalid codes
    }

    // Method to get the discount percentage for a valid code
    // Returns: double (the discount as a decimal number)
    //          Example: 0.15 means 15% off (because 15 / 100 = 0.15)
    //                   0.10 means 10% off (because 10 / 100 = 0.10)
    public double getPercentOff(String code) {
        // Check if the code is the 15% off code
        if ("DAL-15-OFF".equalsIgnoreCase(code)) {
            // Return 0.15 which represents 15% as a decimal
            // Example: $20.00 * 0.15 = $3.00 discount
            return 0.15;
        }

        // Check if the code is the 10% off code
        if ("MAR-10-OFF".equalsIgnoreCase(code)) {
            // Return 0.10 which represents 10% as a decimal
            // Example: $20.00 * 0.10 = $2.00 discount
            return 0.10;
        }

        // If the code doesn't match any valid code, returns 0 (no discount)
        return 0.0;
    }
}