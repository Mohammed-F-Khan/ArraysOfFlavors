package com.pluralsight;


import com.pluralsight.model.*;
import com.pluralsight.services.*;
import com.pluralsight.ui.MenuPrinter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Create service objects
        DiscountService discountService = new DiscountService();
        EmailSignupService emailService = new EmailSignupService();

        // Remember last generated coupon code
        String lastCouponCode = null;

        // Main application loop
        while (true) {

            // Display home screen
            MenuPrinter.printHome();
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();

            if (choice.equals("1")) {
                // New Order
                Order order = new Order();

                // Order loop
                while (true) {

                    // Display order menu
                    MenuPrinter.printOrderMenu();
                    System.out.println("Subtotal: $" + money(order.getSubtotal()));
                    System.out.print("Choose: ");
                    String orderChoice = scanner.nextLine().trim();

                    if (orderChoice.equals("1")) {
                        // Add ice cream item
                        addItemFlow(scanner, order);

                    } else if (orderChoice.equals("2")) {
                        // Add drink
                        addDrinkFlow(scanner, order);

                    } else if (orderChoice.equals("3")) {
                        // Add side
                        addMainSideFlow(scanner, order);

                    } else if (orderChoice.equals("4")) {
                        // Checkout
                        checkoutFlow(scanner, order, discountService);
                        break;

                    } else if (orderChoice.equals("5")) {
                        // Apply coupon now
                        applyCouponFlow(scanner, order, discountService, lastCouponCode);

                    } else if (orderChoice.equals("6")) {
                        // Add signature item
                        addSignatureItem(order);

                    } else if (orderChoice.equals("0")) {
                        // Cancel order
                        System.out.println("Order canceled.");
                        break;

                    } else {
                        System.out.println("Invalid option.");
                    }
                }

            } else if (choice.equals("9")) {
                // Show menu
                MenuPrinter.printMenuArt();

            } else if (choice.equals("8")) {
                // Email signup
                System.out.print("Enter email to sign up: ");
                String email = scanner.nextLine().trim();

                // Generate code
                String code = discountService.generateCodeForEmail(email);

                // Save email to file
                emailService.subscribe(email, code);

                // Display coupon popup
                System.out.println();
                System.out.println("#########################################");
                System.out.println("#      Coupon Created (Save This)       #");
                System.out.println("#########################################");
                System.out.println("#  Your code: " + code);
                System.out.println("#  Email saved to:");
                System.out.println("#  data/outbox/" + email.replaceAll("[^a-zA-Z0-9@._-]", "_") + ".txt");
                System.out.println("#########################################");
                System.out.println();

                // Remember this code
                lastCouponCode = code;

            } else if (choice.equals("0")) {
                // Exit
                System.out.println("Thank you! Goodbye!");
                break;

            } else {
                System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // Add ice cream item - guides user through all customization
    private static void addItemFlow(Scanner scanner, Order order) {

        // Step 1: Choose flavor
        System.out.println("Select base flavor:");
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
        for (i = 0; i < flavors.length; i++) {
            System.out.println((i + 1) + ") " + flavors[i]);
        }
        int flavorIndex = readIndex(scanner, flavors.length);

        // Step 2: Choose container type
        System.out.println("Select type:");
        String[] types = {
                "Cup",
                "Waffle Cone",
                "Chocolate-Dipped Cone",
                "Cookie Bowl",
                "Sundae Dish"
        };

        for (i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ") " + types[i]);
        }
        int typeIndex = readIndex(scanner, types.length);

        // Step 3: Choose size
        System.out.println("Select size:");
        String[] sizes = {"Small", "Medium", "Large"};

        for (i = 0; i < sizes.length; i++) {
            System.out.println((i + 1) + ") " + sizes[i]);
        }
        int sizeIndex = readIndex(scanner, sizes.length);
        String size = sizes[sizeIndex - 1];

        // Create ice cream item with base choices
        IceCreamItem item = new IceCreamItem(
                flavors[flavorIndex - 1],
                types[typeIndex - 1],
                size
        );

        // Step 4: Regular toppings (free)
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

        for (i = 0; i < regularToppings.length; i++) {
            String topping = regularToppings[i];
            System.out.print("Add regular topping '" + topping + "'? (y/n): ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("y")) {
                item.addRegularTopping(topping);
            }
        }

        // Step 5: Premium toppings (paid)
        String[] premiumToppings = {
                "brownie chunks",
                "cookie dough bites",
                "peanut butter cups",
                "caramel swirl",
                "fudge sauce",
                "cheesecake bites"
        };

        for (i = 0; i < premiumToppings.length; i++) {
            String topping = premiumToppings[i];
            System.out.print("How many '" + topping + "'? (0,1,2+): ");
            int count = parseInt(scanner.nextLine().trim(), 0);
            if (count > 0) {
                item.addPremiumTopping(topping, count);
            }
        }

        // Step 6: Premium sauces (paid)
        String[] premiumSauces = {
                "hot fudge",
                "salted caramel",
                "cookie butter",
                "espresso drizzle"
        };

        for (i = 0; i < premiumSauces.length; i++) {
            String sauce = premiumSauces[i];
            System.out.print("How many '" + sauce + "'? (0,1,2+): ");
            int count = parseInt(scanner.nextLine().trim(), 0);
            if (count > 0) {
                item.addPremiumSauce(sauce, count);
            }
        }

        // Step 7: Condiments (free)
        String[] condiments = {
                "Chocolate Syrup",
                "Strawberry Sauce",
                "Caramel Drizzle",
                "Marshmallow Cream",
                "Honey Drizzle"
        };

        for (i = 0; i < condiments.length; i++) {
            String condiment = condiments[i];
            System.out.print("Add condiment '" + condiment + "'? (y/n): ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("y")) {
                item.addCondiment(condiment);
            }
        }

        // Step 8: Special option
        System.out.print("Add Special (Nitro Chill Finish)? (y/n): ");
        String specialAnswer = scanner.nextLine().trim();
        if (specialAnswer.equalsIgnoreCase("y")) {
            item.applySpecial();
        }

        // Add item to order
        order.addItem(item);
        System.out.println("Added: " + item.toString());
    }

    // Add drink - guides user through drink selection
    private static void addDrinkFlow(Scanner scanner, Order order) {

        // Choose drink type
        System.out.println("Drink:");
        String[] drinkTypes = {
                "Soda",
                "Iced Coffee",
                "Milkshake",
                "Bottled Water"
        };

        int i;
        for (i = 0; i < drinkTypes.length; i++) {
            System.out.println((i + 1) + ") " + drinkTypes[i]);
        }
        int typeIndex = readIndex(scanner, drinkTypes.length);
        String flavor = drinkTypes[typeIndex - 1];

        // Choose size (unless bottled water)
        String size = "Small";
        if (!flavor.equalsIgnoreCase("Bottled Water")) {
            System.out.println("Size:");
            String[] sizes = {"Small", "Medium", "Large"};

            for (i = 0; i < sizes.length; i++) {
                System.out.println((i + 1) + ") " + sizes[i]);
            }
            int sizeIndex = readIndex(scanner, 3);
            size = sizes[sizeIndex - 1];
        }

        // Add drink to order
        order.addDrink(new Drink(flavor, size));
        System.out.println("Added drink: " + flavor + " (" + size + ")");
    }

    // Add side - guides user through side selection
    private static void addMainSideFlow(Scanner scanner, Order order) {

        System.out.println("Main Side:");
        String[] sideOptions = {
                "Cookie Pair",
                "Brownie Square",
                "Mini Waffle Bites",
                "Main Side ($1.50)"
        };

        int i;
        for (i = 0; i < sideOptions.length; i++) {
            System.out.println((i + 1) + ") " + sideOptions[i]);
        }
        int sideIndex = readIndex(scanner, sideOptions.length);

        // Determine side name
        String sideName;
        if (sideIndex == 1) {
            sideName = "Cookie Pair";
        } else if (sideIndex == 2) {
            sideName = "Brownie Square";
        } else if (sideIndex == 3) {
            sideName = "Mini Waffle Bites";
        } else {
            sideName = "Main Side";
        }

        // Add side to order
        order.addSide(new Side(sideName));
        System.out.println("Added side: " + sideName);
    }

    // Apply coupon during ordering
    private static void applyCouponFlow(Scanner scanner, Order order,
                                        DiscountService discountService,
                                        String lastCouponCode) {
        System.out.println();
        System.out.println("------ Apply Coupon ------");

        // Show last code if available
        if (lastCouponCode != null) {
            System.out.println("Last code we generated: " + lastCouponCode);
        }

        System.out.print("Enter coupon code (or press ENTER to cancel): ");
        String code = scanner.nextLine().trim();

        if (code.length() == 0) {
            System.out.println("No code entered.");
            return;
        }

        // Validate and apply code
        if (discountService.validate(code)) {
            double percent = discountService.getPercentOff(code);
            order.setDiscount(code, percent);
            System.out.println("Coupon applied: " + code);
        } else {
            System.out.println("Invalid code.");
        }

        System.out.println("--------------------------");
        System.out.println();
    }

    // Handle checkout process
    private static void checkoutFlow(Scanner scanner, Order order,
                                     DiscountService discountService) {

        // Enforce rule: 0 items requires drink or side
        if (order.hasNoItems() && !order.hasDrinkOrSide()) {
            System.out.println("You have 0 items. You must add a drink or a main side before checkout.");
            return;
        }

        // Ask for discount code
        System.out.print("Have a discount code? (enter code or press ENTER): ");
        String code = scanner.nextLine().trim();

        if (code.length() > 0) {
            if (discountService.validate(code)) {
                order.setDiscount(code, discountService.getPercentOff(code));
                System.out.println("Discount applied.");
            } else {
                System.out.println("Invalid code.");
            }
        }

        // Display order summary
        System.out.println("\n--- ORDER SUMMARY ---");
        System.out.print(order.summary());
        System.out.print(order.detailsBlock());

        // Confirm checkout
        System.out.print("Confirm checkout? (y/n): ");
        String answer = scanner.nextLine().trim();

        if (answer.equalsIgnoreCase("y")) {
            // Save receipt
            String receiptPath = ReceiptWriter.write(order);
            System.out.println("Receipt saved to: " + receiptPath);
            System.out.println("Thank you for your order!");
        } else {
            System.out.println("Checkout canceled.");
        }
    }

    // Quick-add signature item
    private static void addSignatureItem(Order order) {
        // Create pre-configured Midnight Margarita Sundae
        IceCreamItem signature = new IceCreamItem(
                "Midnight Margarita",
                "Sundae Dish",
                "Large"
        );
        signature.addPremiumTopping("brownie chunks", 1);
        signature.addPremiumSauce("fudge sauce", 1);
        signature.addCondiment("Chocolate Syrup");
        signature.applySpecial();

        order.addItem(signature);
        System.out.println("Added Signature Item: Midnight Margarita Sundae (Large)");
    }

    // Helper: read valid index from user
    private static int readIndex(Scanner scanner, int max) {
        while (true) {
            String input = scanner.nextLine().trim();
            int value = parseInt(input, -1);
            if (value >= 1 && value <= max) {
                return value;
            }
            System.out.print("Enter 1-" + max + ": ");
        }
    }

    // Helper: safely parse string to integer
    private static int parseInt(String text, int defaultValue) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    // Helper: format money as dollars.cents
    private static String money(double value) {
        long cents = Math.round(value * 100.0);
        long dollars = cents / 100;
        long remainder = cents % 100;
        String remainderString = "" + remainder;
        if (remainder < 10) {
            remainderString = "0" + remainder;
        }
        return dollars + "." + remainderString;
    }
}