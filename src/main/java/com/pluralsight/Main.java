package com.pluralsight;

// this Imports all classes under model
// These classes represent items, drinks, sides, and orders
import com.pluralsight.model.*;

// Import all service classes
// These classes handle receipts, discounts, and email signups
import com.pluralsight.services.*;

// Import the MenuPrinter class
import com.pluralsight.ui.MenuPrinter;

// Import Scanner to read user input from the keyboard
import java.util.Scanner;

// Main class
public class Main {

    // Main method
    public static void main(String[] args) {

        // read input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // service objects
        // These objects help us with specific tasks
        DiscountService discountService = new DiscountService();
        EmailSignupService emailService = new EmailSignupService();

        // Variable to remember the last coupon code that we generated
        // Starts as null
        String lastCouponCode = null;

        // Show the beautiful welcome screen when program starts
        // This is a static method, so we call it on the class name
        MenuPrinter.printWelcome();

        // MAIN APPLICATION LOOP
        // This while loop keeps the program running until user chooses to exit
        while (true) {

            // Display the home screen menu with all options
            MenuPrinter.printHome();

            // Prompt user to make a choice
            System.out.print("Choose: ");

            // Read the user's input as a String
            String choice = scanner.nextLine().trim();

            // Check if user chose "1" - Start a new order
            // We use equals() to compare strings (not ==)
            if (choice.equals("1")) {

                // Creates a fresh new Order object to store the customer's order
                Order order = new Order();

                // ORDER LOOP
                // This inner loop keeps running until user checks out or cancels
                while (true) {

                    // Display the order menu
                    MenuPrinter.printOrderMenu();

                    // Shows current subtotal so user knows how much they're spending
                    // money() is a helper method
                    // It formats a double as a nice money string like "5.50"
                    System.out.println("💰 Subtotal: $" + money(order.getSubtotal()));
                    System.out.print("Choose: ");

                    // Reads user's order choice
                    String orderChoice = scanner.nextLine().trim();

                    // Checks which option user selected

                    if (orderChoice.equals("1")) {
                        // User chose "1" - Add an ice cream item
                        // Calls our helper method that guides user through creating ice cream
                        addItemFlow(scanner, order);

                    } else if (orderChoice.equals("2")) {
                        // User chose "2" - Add a drink
                        addDrinkFlow(scanner, order);

                    } else if (orderChoice.equals("3")) {
                        // User chose "3" - Add a side
                        addMainSideFlow(scanner, order);

                    } else if (orderChoice.equals("4")) {
                        // User chose "4" - Checkout
                        // Call checkout method which handles payment and receipt
                        checkoutFlow(scanner, order, discountService);

                        break;

                    } else if (orderChoice.equals("5")) {
                        // User chose "5" - Apply coupon during ordering
                        applyCouponFlow(scanner, order, discountService, lastCouponCode);

                    } else if (orderChoice.equals("6")) {
                        // User chose "6" - Quick add signature item
                        addSignatureItem(order);

                    } else if (orderChoice.equals("0")) {
                        // User chose "0" - Cancel order
                        System.out.println("\n❌ Order canceled.\n");
                        break; // Exit order loop

                    } else {
                        // User entered something invalid
                        System.out.println("\n❌ Invalid option. Please try again.\n");
                    }
                }

            } else if (choice.equals("9")) {
                // User chose "9" - Show full menu
                MenuPrinter.printMenuArt();

            } else if (choice.equals("8")) {
                // User chose "8" - Email signup for discount

                // Asks user for their email address
                System.out.print("\n📧 Enter email to sign up: ");
                String email = scanner.nextLine().trim();

                // Generate a discount code based on their email
                // This uses the DiscountService to create a code
                String code = discountService.generateCodeForEmail(email);

                // Saves the email signup to a file
                // This creates a text file in data/outbox/ folder
                emailService.subscribe(email, code);

                // Displays confirmation message showing their code
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════╗");
                System.out.println("║                                                                ║");
                System.out.println("║               🎉 COUPON CREATED - SAVE THIS! 🎉                ║");
                System.out.println("║                                                                ║");
                System.out.println("╠════════════════════════════════════════════════════════════════╣");
                System.out.println("║                                                                ║");
                System.out.println("║  Your discount code: " + code + "                              ║");
                System.out.println("║                                                                ║");
                System.out.println("║  Email confirmation saved to outbox folder                     ║");
                System.out.println("║                                                                ║");
                System.out.println("╚════════════════════════════════════════════════════════════════╝");
                System.out.println();

                // Remembers this code so we can show it to user later if they want to apply it
                lastCouponCode = code;

            } else if (choice.equals("0")) {
                // User chose "0" - Exit program

                // Display beautiful goodbye message
                System.out.println();
                System.out.println("╔════════════════════════════════════════════════════════════════╗");
                System.out.println("║                                                                ║");
                System.out.println("║                👋 THANK YOU FOR VISITING! 👋                   ║");
                System.out.println("║                                                                ║");
                System.out.println("║                     ARRAYS OF FLAVORS                          ║");
                System.out.println("║               Where Every Scoop Tells a Story                  ║");
                System.out.println("║                                                                ║");
                System.out.println("║                    Have a sweet day! 🍦                        ║");
                System.out.println("║                                                                ║");
                System.out.println("╚════════════════════════════════════════════════════════════════╝");
                System.out.println();

                // Break - his ends the program
                break;

            } else {
                // User entered something invalid
                System.out.println("\n❌ Invalid option. Please choose 1, 8, 9, or 0.\n");
            }
        }

        // Close the scanner
        scanner.close();
    }

    // HELPER METHODS

    // Method to guide user through creating an ice cream item
    private static void addItemFlow(Scanner scanner, Order order) {

        // Print beautiful header
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                  🍦 CREATE YOUR ICE CREAM 🍦                  ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();


        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 1: Select Base Flavor                                │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();


        String[] flavors = {
                "Classic Vanilla Loop",
                "Double Chocolate Crunch",
                "Strawberry Shortcode",
                "Mint Chip Matrix",
                "Cookie Circuit",
                "Java Caramel Swirl",
                "Midnight Margarita"
        };


        int i;

        // Loop through the flavors array and display each one with a number
        // for loop format: for (initialize; condition; increment)
        for (i = 0; i < flavors.length; i++) {
            // Display the flavor number (i+1 because humans count from 1, not 0)
            System.out.println("    " + (i + 1) + ") " + flavors[i]);
        }
        System.out.println();

        // Get user's flavor choice (a number from 1 to 7)
        // readIndex is our helper method that validates the input
        int flavorIndex = readIndex(scanner, flavors.length);
        System.out.println();


        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 2: Select Container Type                             │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // Array of container options with prices displayed
        String[] types = {
                "Cup",
                "Waffle Cone (+$1.00)",
                "Chocolate-Dipped Cone (+$1.50)",
                "Cookie Bowl (+$2.00)",
                "Sundae Dish (+$2.50)"
        };

        // Display all container options
        for (i = 0; i < types.length; i++) {
            System.out.println("    " + (i + 1) + ") " + types[i]);
        }
        System.out.println();

        // Gets user's container choice
        int typeIndex = readIndex(scanner, types.length);

        // Array of actual container names (without the price labels)
        String[] typeNames = {"Cup", "Waffle Cone", "Chocolate-Dipped Cone", "Cookie Bowl", "Sundae Dish"};
        System.out.println();


        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 3: Select Size                                       │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // Array of size options
        String[] sizes = {"Small", "Medium", "Large"};

        // Displays all sizes
        for (i = 0; i < sizes.length; i++) {
            System.out.println("    " + (i + 1) + ") " + sizes[i]);
        }
        System.out.println();

        // Get user's size choice
        int sizeIndex = readIndex(scanner, sizes.length);

        // Convert from 1-based index (what user sees) to 0-based index (array index)
        // User selects 1, 2, or 3, but array uses 0, 1, or 2
        String size = sizes[sizeIndex - 1];
        System.out.println();


        // Now we have all the info we need, so create the IceCreamItem object
        // The constructor needs: flavor, container type, and size
        IceCreamItem item = new IceCreamItem(
                flavors[flavorIndex - 1],      // Get flavor from array (subtract 1 for 0-based index)
                typeNames[typeIndex - 1],      // Get container type from array
                size                           // Size we already converted above
        );


        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 4: Select Regular Toppings (FREE)                    │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        String[] regularToppings = {
                "sprinkles",
                "crushed cookies",
                "brownie bits",
                "mini marshmallows",
                "coconut flakes",
                "chocolate chips",
                "cherries",
                "candy pieces"
        };

        // Loop through each regular topping and ask if user wants it
        for (i = 0; i < regularToppings.length; i++) {
            // Get the current topping name
            String topping = regularToppings[i];

            // Ask user yes or no
            System.out.print("    Add " + topping + "? (y/n): ");

            // Read their answer
            String answer = scanner.nextLine().trim();

            // Check if they said yes
            if (answer.equalsIgnoreCase("y")) {
                // Add this topping to the ice cream item
                item.addRegularTopping(topping);
            }
        }
        System.out.println();



        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 5: Select Premium Toppings (Extra Cost)              │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // Array of premium toppings (these cost extra)
        String[] premiumToppings = {
                "brownie chunks",
                "cookie dough bites",
                "peanut butter cups",
                "caramel swirl",
                "fudge sauce",
                "cheesecake bites"
        };

        // Loop through each premium topping and ask how many user wants
        for (i = 0; i < premiumToppings.length; i++) {
            String topping = premiumToppings[i];

            // Ask how many (0, 1, 2, or more)
            System.out.print("    How many " + topping + "? (0, 1, 2+): ");

            // Read their input as a string
            String countInput = scanner.nextLine().trim();

            // Convert string to integer using our helper method
            // parseInt will return 0 if they enter something invalid
            int count = parseInt(countInput, 0);

            // If they want at least 1, add it to the item
            if (count > 0) {
                item.addPremiumTopping(topping, count);
            }
        }
        System.out.println();


        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 6: Select Premium Sauces (Extra Cost)                │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // Array of premium sauces
        String[] premiumSauces = {
                "hot fudge",
                "salted caramel",
                "cookie butter",
                "espresso drizzle"
        };

        // Loop through each sauce and ask how many
        for (i = 0; i < premiumSauces.length; i++) {
            String sauce = premiumSauces[i];
            System.out.print("    How many " + sauce + "? (0, 1, 2+): ");
            String countInput = scanner.nextLine().trim();
            int count = parseInt(countInput, 0);

            if (count > 0) {
                // Add sauce to item with count
                item.addPremiumSauce(sauce, count);
            }
        }
        System.out.println();



        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 7: Select Condiments (FREE)                          │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // Array of free condiments (drizzles and sauces)
        String[] condiments = {
                "Chocolate Syrup",
                "Strawberry Sauce",
                "Caramel Drizzle",
                "Marshmallow Cream",
                "Honey Drizzle"
        };

        // Ask about each condiment
        for (i = 0; i < condiments.length; i++) {
            String condiment = condiments[i];
            System.out.print("    Add " + condiment + "? (y/n): ");
            String answer = scanner.nextLine().trim();

            if (answer.equalsIgnoreCase("y")) {
                item.addCondiment(condiment);
            }
        }
        System.out.println();


        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │  STEP 8: Add Special Finish?                               │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();
        System.out.print("    Add Nitro Chill Finish? (y/n): ");
        String specialAnswer = scanner.nextLine().trim();

        // If yes, apply the special finish
        if (specialAnswer.equalsIgnoreCase("y")) {
            item.applySpecial();
        }


        order.addItem(item);

        // Show confirmation message
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                  ✅ ITEM ADDED TO ORDER! ✅                    ║");
        System.out.println("║                                                                ║");
        System.out.println("║  " + item.toString());
        System.out.println("║  Price: $" + money(item.getTotal()));
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // Method to guide user through adding a drink
    private static void addDrinkFlow(Scanner scanner, Order order) {

        // header
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                      🥤 ADD A DRINK 🥤                        ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Array of drink types
        String[] drinkTypes = {
                "Soda",
                "Iced Coffee",
                "Milkshake",
                "Bottled Water"
        };

        // Display all drink options
        int i;
        for (i = 0; i < drinkTypes.length; i++) {
            System.out.println("    " + (i + 1) + ") " + drinkTypes[i]);
        }
        System.out.println();

        // Get user's drink choice
        int typeIndex = readIndex(scanner, drinkTypes.length);

        // Get the drink name from array (subtract 1 for 0-based index)
        String flavor = drinkTypes[typeIndex - 1];

        // Default size is Small
        String size = "Small";

        // Check if drink is NOT bottled water
        // Bottled water comes in one size only, others have size options
        // ! means "not" - reverses the boolean
        if (!flavor.equalsIgnoreCase("Bottled Water")) {
            // Ask for size
            System.out.println();
            System.out.println("  Select size:");
            System.out.println("  ─────────────────────────────────────────────");

            String[] sizes = {"Small", "Medium", "Large"};

            for (i = 0; i < sizes.length; i++) {
                System.out.println("    " + (i + 1) + ") " + sizes[i]);
            }
            System.out.println();

            int sizeIndex = readIndex(scanner, 3);
            size = sizes[sizeIndex - 1];
        }

        // Creates the Drink object and add it to the order
        Drink drink = new Drink(flavor, size);
        order.addDrink(drink);

        // Show confirmation
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                 ✅ DRINK ADDED TO ORDER! ✅                    ║");
        System.out.println("║                                                                ║");
        System.out.println("║  " + drink.toString());
        System.out.println("║  Price: $" + money(drink.getTotal()));
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // Method to guide user through adding a side
    private static void addMainSideFlow(Scanner scanner, Order order) {

        // Print header
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                      🍪 ADD A SIDE 🍪                         ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Array of side options with prices shown
        String[] sideOptions = {
                "Cookie Pair ($2.25)",
                "Brownie Square ($2.50)",
                "Mini Waffle Bites ($3.00)",
                "Main Side ($1.50)"
        };

        // Displays all side options
        int i;
        for (i = 0; i < sideOptions.length; i++) {
            System.out.println("    " + (i + 1) + ") " + sideOptions[i]);
        }
        System.out.println();

        // Gets user's choice
        int sideIndex = readIndex(scanner, sideOptions.length);

        // Array of actual side names (without prices)
        String[] sideNames = {
                "Cookie Pair",
                "Brownie Square",
                "Mini Waffle Bites",
                "Main Side"
        };

        // Get the side name
        String sideName = sideNames[sideIndex - 1];

        // Creates Side object and add to order
        Side side = new Side(sideName);
        order.addSide(side);

        // Show confirmation
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                  ✅ SIDE ADDED TO ORDER! ✅                   ║");
        System.out.println("║                                                                ║");
        System.out.println("║  " + side.toString());
        System.out.println("║  Price: $" + money(side.getTotal()));
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // Method to apply a coupon code during ordering
    private static void applyCouponFlow(Scanner scanner, Order order,
                                        DiscountService discountService,
                                        String lastCouponCode) {

        // header
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                   🎟️  APPLY COUPON CODE 🎟️                  ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // If we remember a code from email signup, show it to help user
        // != means "not equal to" - checks if lastCouponCode is not null
        if (lastCouponCode != null) {
            System.out.println("  💡 Last generated code: " + lastCouponCode);
            System.out.println();
        }

        // Asks user to enter coupon code
        System.out.print("  Enter coupon code (or press ENTER to cancel): ");
        String code = scanner.nextLine().trim();

        // Checks if user just pressed ENTER without typing anything
        // length() returns 0 if string is empty
        if (code.length() == 0) {
            System.out.println();
            System.out.println("  ❌ No code entered. Returning to order menu.");
            System.out.println();
            return; // Exits this method early
        }

        // Validates the coupon code using DiscountService
        if (discountService.validate(code)) {
            // Code is valid! Get the discount percentage
            double percent = discountService.getPercentOff(code);

            // Apply the discount to the order
            order.setDiscount(code, percent);

            // success message
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                ║");
            System.out.println("║                  ✅ COUPON APPLIED! ✅                        ║");
            System.out.println("║                                                                ║");
            System.out.println("║  Code: " + code);
            System.out.println("║  Discount: " + (int)(percent * 100) + "% off");
            System.out.println("║                                                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
            System.out.println();
        } else {
            // Code is invalid
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                ║");
            System.out.println("║                 ❌ INVALID COUPON CODE ❌                     ║");
            System.out.println("║                                                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
            System.out.println();
        }
    }

    // Method to handle the checkout process

    private static void checkoutFlow(Scanner scanner, Order order,
                                     DiscountService discountService) {

        // RULE: If order has 0 ice cream items, must have at least a drink or side
        // && means "and" - both conditions must be true
        if (order.hasNoItems() && !order.hasDrinkOrSide()) {
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                ║");
            System.out.println("║                ❌ CANNOT CHECKOUT ❌                          ║");
            System.out.println("║                                                                ║");
            System.out.println("║  You have 0 items. You must add a drink or side first.         ║");
            System.out.println("║                                                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
            System.out.println();
            return; // Exits method early - so the user can't  checkout
        }

        // Asks if user has a discount code one more time (in case they forgot)
        System.out.println();
        System.out.print("🎟️  Have a discount code? (enter code or press ENTER to skip): ");
        String code = scanner.nextLine().trim();

        // If they entered something, this trys to apply it
        if (code.length() > 0) {
            if (discountService.validate(code)) {
                order.setDiscount(code, discountService.getPercentOff(code));
                System.out.println("✅ Discount applied!");
            } else {
                System.out.println("❌ Invalid code. Proceeding without discount.");
            }
        }

        // Display order summary header
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                     📋 ORDER SUMMARY 📋                        ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // Print all items, drinks, sides, discount, and total
        // summary() returns a formatted string with all order info
        System.out.print(order.summary());
        System.out.println();

        // Print detailed toppings information
        System.out.println("Item Details:");
        System.out.println("─────────────────────────────────────────────────────────────────");

        // Gets details block (shows all customizations)
        String details = order.detailsBlock();

        // Check if there are any details to show
        if (details.length() > 0) {
            System.out.print(details);
        } else {
            System.out.println("  (No items with customizations)");
        }
        System.out.println();
        System.out.println("═════════════════════════════════════════════════════════════════");
        System.out.println();

        // Asks user to confirm checkout
        System.out.print("💳 Confirm checkout? (y/n): ");
        String answer = scanner.nextLine().trim();

        // Check if they said yes
        if (answer.equalsIgnoreCase("y")) {

            // Saves receipt to file using ReceiptWriter
            // write() returns the file path where receipt was saved
            String receiptPath = ReceiptWriter.write(order);

            // Shows success message
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                ║");
            System.out.println("║              ✅ THANK YOU FOR YOUR ORDER! ✅                  ║");
            System.out.println("║                                                                ║");
            System.out.println("║  Receipt saved to: " + receiptPath);
            System.out.println("║                                                                ║");
            System.out.println("║                   Enjoy your treats! 🍦                       ║");
            System.out.println("║                                                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
            System.out.println();

        } else {
            // User canceled checkout
            System.out.println();
            System.out.println("╔════════════════════════════════════════════════════════════════╗");
            System.out.println("║                                                                ║");
            System.out.println("║                  ❌ CHECKOUT CANCELED ❌                      ║");
            System.out.println("║                                                                ║");
            System.out.println("╚════════════════════════════════════════════════════════════════╝");
            System.out.println();
        }
    }

    // this Method to quickly add  signature item
    private static void addSignatureItem(Order order) {

        // Create a pre-configured Margarita Sundae (Large)
        // This is our special signature item with preset customizations
        IceCreamItem signature = new IceCreamItem(
                "Margarita",  // Flavor
                "Sundae Dish",         // Container
                "Large"                // Size
        );

        // Add pre-selected premium toppings
        signature.addPremiumTopping("brownie chunks", 1);

        // Add pre-selected premium sauce
        signature.addPremiumSauce("fudge sauce", 1);

        // Add pre-selected condiment
        signature.addCondiment("Chocolate Syrup");

        // Apply special finish
        signature.applySpecial();

        // Add to order
        order.addItem(signature);

        // Show confirmation with all the details
        System.out.println();
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║              ⭐ SIGNATURE ITEM ADDED! ⭐                      ║");
        System.out.println("║                                                                ║");
        System.out.println("║  Midnight Margarita Sundae (Large)                             ║");
        System.out.println("║  - Sundae Dish                                                 ║");
        System.out.println("║  - Brownie Chunks                                              ║");
        System.out.println("║  - Fudge Sauce                                                 ║");
        System.out.println("║  - Chocolate Syrup                                             ║");
        System.out.println("║  - Nitro Chill Finish                                          ║");
        System.out.println("║                                                                ║");
        System.out.println("║  Price: $" + money(signature.getTotal()));
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    // Helper method to read a valid index from user (between 1 and max)
    // This keeps asking until user enters a valid number
    private static int readIndex(Scanner scanner, int max) {
        // Loop forever until we get valid input
        while (true) {
            System.out.print("  Enter choice (1-" + max + "): ");

            // Reads user input as string
            String input = scanner.nextLine().trim();

            // Try to convert string to integer using our helper method
            // If it fails, parseInt will return -1
            int value = parseInt(input, -1);

            // Check if value is in valid range (1 to max)
            if (value >= 1 && value <= max) {
                return value; // Valid! Return it
            }

            // If we get here, input was invalid - show error and loops again
            System.out.println("  ❌ Invalid input. Please enter a number from 1 to " + max + ".");
        }
    }

    // Helper method to safely convert a string to an integer
    // If conversion fails, returns the default value instead of crashing
    private static int parseInt(String text, int defaultValue) {
        // Try-catch block to handle potential errors
        // We learned about try-catch in Workbook 3a (pages 6-7)
        try {
            // Try to parse (convert) the string to an integer
            return Integer.parseInt(text);

        } catch (Exception e) {
            // If parsing fails (user entered letters or symbols), return default
            // catch block catches any errors that happen in try block
            return defaultValue;
        }
    }

    // Helper method to format a dollar amount as a nice string
    private static String money(double value) {
        // Multiply by 100 and round to get total cents
        // Math.round() rounds to nearest whole number
        long cents = Math.round(value * 100.0);

        // Divide by 100 to get dollar part
        // Example: 557 cents / 100 = 5 dollars
        long dollars = cents / 100;

        // Example: 557 cents % 100 = 57 cents
        long remainder = cents % 100;

        // Convert remainder to string
        // "" + remainder converts number to string
        String remainderString = "" + remainder;

        // If remainder is less than 10, add leading zero
        // This ensures we get "5.05" instead of "5.5"
        if (remainder < 10) {
            remainderString = "0" + remainder;
        }

        // Concatenate (join) dollars, decimal point, and cents
        // Example: "5" + "." + "57" = "5.57"
        return dollars + "." + remainderString;
    }
}