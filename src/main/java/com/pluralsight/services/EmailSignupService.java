package com.pluralsight.services;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class EmailSignupService { // Simulates sending emails

    // "Send" an email with the discount code
    public void subscribe(String email, String discountCode){
        try{
            // Make the email safe for a filename (remove special characters)
            String safe = email.replaceAll("[^a-zA-Z0-9@._-]","_");

            // Create file path
            String path = "data/outbox/" + safe + ".txt";

            // Create writer
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));

            // Write "email" content
            bw.write("Subject: Your Arrays of Flavors discount code\n");
            bw.write("Hi! Thanks for signing up.\n");
            bw.write("Your code: " + discountCode + "\n");
            bw.write("Use it at checkout to save.\n");

            // Close file
            bw.close();

        } catch(Exception e){ // If file writing fails
            e.printStackTrace();
        }
    }
}