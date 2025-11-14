package com.pluralsight.services;

// Import Order class so we can save order information
import com.pluralsight.model.Order;
// Import BufferedWriter - efficient way to write text to files
import java.io.BufferedWriter;
// Import FileWriter - writes to files
import java.io.FileWriter;
// Import IOException - handles file errors
import java.io.IOException;
// Import LocalDateTime - gets current date and time
import java.time.LocalDateTime;
// Import DateTimeFormatter - formats date and time as strings
import java.time.format.DateTimeFormatter;

// This class writes receipts to text files
public class ReceiptWriter {

    // Static method to write an order to a receipt file
    public static String write(Order order) {
        // Try-catch block - handles  file writing errors
        try {
            // formatter to format date/time as "yyyyMMdd-HHmmss"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss");

            // Get current date and time
            // format() converts it to a string using our formatter
            String timestamp = LocalDateTime.now().format(formatter);

            // Build the file path: data/receipts/[timestamp].txt
            String filePath = "data/receipts/" + timestamp + ".txt";

            // Create FileWriter object - opens the file for writing
            // This creates the file if it doesn't exist
            FileWriter fileWriter = new FileWriter(filePath);

            // BufferedWriter writes text in chunks instead of one character at a time
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write("╔════════════════════════════════════════════════════════════════╗\n");
            bufferedWriter.write("║                                                                ║\n");
            bufferedWriter.write("║                    ARRAYS OF FLAVORS                           ║\n");
            bufferedWriter.write("║              Where Every Scoop Tells a Story                   ║\n");
            bufferedWriter.write("║                                                                ║\n");
            bufferedWriter.write("╚════════════════════════════════════════════════════════════════╝\n");
            bufferedWriter.write("\n");

            // Write order time
            bufferedWriter.write("Order Date/Time: " + timestamp + "\n");
            bufferedWriter.write("\n");

            // Write divider
            bufferedWriter.write("================================================================\n");
            bufferedWriter.write("                        ORDER SUMMARY                            \n");
            bufferedWriter.write("================================================================\n");
            bufferedWriter.write("\n");

            // Write the order summary (all items, prices, total)
            bufferedWriter.write(order.summary());
            bufferedWriter.write("\n");

            // Write detailed toppings information
            String details = order.detailsBlock();
            if (details.length() > 0) {
                bufferedWriter.write("----------------------------------------------------------------\n");
                bufferedWriter.write("                      ITEM DETAILS                              \n");
                bufferedWriter.write("----------------------------------------------------------------\n");
                bufferedWriter.write("\n");
                bufferedWriter.write(order.detailsBlock());
                bufferedWriter.write("\n");
            }

            // Write footer
            bufferedWriter.write("================================================================\n");
            bufferedWriter.write("           Thank you for visiting Arrays of Flavors!            \n");
            bufferedWriter.write("                    Have a sweet day! 🍦                        \n");
            bufferedWriter.write("================================================================\n");

            // Close the writer to save the file
            bufferedWriter.close();

            // Returns the file path
            return filePath;

        } catch (IOException e) {
            // If there's an error, print error message
            System.out.println("ERROR: Could not write receipt");

            // Print detailed error information
            e.printStackTrace();

            // Return null for failure
            return null;
        }
    }
}