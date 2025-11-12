package com.pluralsight.services;

import com.pluralsight.model.Order;
import java.io.BufferedWriter;  // For writing to files efficiently
import java.io.FileWriter;      // For creating file connections
import java.time.LocalDateTime; // For getting current date/time
import java.time.format.DateTimeFormatter; // For formatting dates

public class ReceiptWriter { // Service that writes the receipt files

    // Static method - can call without creating a ReceiptWriter object
    public static String write(Order order){
        try { // to catch if anything goes wrong

            // creates a date formatter for the filename
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

            // Gets current time and format it
            String stamp = LocalDateTime.now().format(fmt);

            // builds the file path
            String path = "data/receipts/" + stamp + ".txt";

            // Creates a writer to be able to write to the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(path));

            // Writes the Receipt header
            bw.write("Arrays of Flavors - Receipt\n");
            bw.write("Order time: " + stamp + "\n\n");

            // Write order summary and details
            bw.write(order.summary()); // items and the totals
            bw.write(order.detailsBlock()); // toppings details

            // closes the file
            bw.close();

            return path; // this returns the file path so we can tell the user!

    } catch(Exception e){ // if anything goes wrong above
        e.printStackTrace(); // prints the error.
        return null; // returns null to indicate that there was a failure
        }
    }
}
