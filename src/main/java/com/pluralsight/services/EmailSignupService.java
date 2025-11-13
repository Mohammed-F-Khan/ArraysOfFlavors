package com.pluralsight.services;


import java.io.BufferedWriter;

import java.io.FileWriter;

import java.io.IOException;

// This class handles saving email signup information to files
public class EmailSignupService {

    // Method to save email and discount code to a file
    // "subscribe" is just the name I chose for this method

    public void subscribe(String email, String discountCode) {
        // Try-catch block to handle  file writing errors
        try {
            // email directly as the filename
            // Build file path: data/outbox/[email].txt
            // Example: "data/outbox/user@email.com.txt"
            String filePath = "data/outbox/" + email + ".txt";

            // FileWriter to open/create the file
            FileWriter fileWriter = new FileWriter(filePath);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // this Writes the email contents line by line
            // write() method writes a string to the file
            bufferedWriter.write("Subject: Your Arrays of Flavors discount code\n");
            bufferedWriter.write("Hi! Thanks for signing up.\n");
            bufferedWriter.write("Your code: " + discountCode + "\n");
            bufferedWriter.write("Use it at checkout to save.\n");

            // Closed the writer to save the file
            bufferedWriter.close();

        } catch (IOException e) {
            // If error occurs, print error message to console
            System.out.println("ERROR: Could not write email file");

            // printStackTrace() shows exactly what went wrong and where
            e.printStackTrace();
        }
    }
}