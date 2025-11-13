package com.pluralsight.ui;

// Import Scanner to read user input
import java.util.Scanner;

// This class handles printing all the beautiful menus
public class MenuPrinter {

    // method to display the welcome screen
    public static void printWelcome() {
        // Prints 3 blank lines to clear space
        System.out.println("\n\n\n");

        // Print the top border of the box
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║         █████╗ ██████╗ ██████╗  █████╗ ██╗   ██╗███████╗       ║");
        System.out.println("║        ██╔══██╗██╔══██╗██╔══██╗██╔══██╗╚██╗ ██╔╝██╔════╝       ║");
        System.out.println("║        ███████║██████╔╝██████╔╝███████║ ╚████╔╝ ███████╗       ║");
        System.out.println("║        ██╔══██║██╔══██╗██╔══██╗██╔══██║  ╚██╔╝  ╚════██║       ║");
        System.out.println("║        ██║  ██║██║  ██║██║  ██║██║  ██║   ██║   ███████║       ║");
        System.out.println("║        ╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   ╚══════╝       ║");
        System.out.println("║                                                                ║");
        System.out.println("║                   ╔═══════════════════════╗                    ║");
        System.out.println("║                   ║     OF FLAVORS  🍦    ║                    ║");
        System.out.println("║                   ╚═══════════════════════╝                    ║");
        System.out.println("║                                                                ║");
        System.out.println("║               Where Every Scoop Tells a Story                  ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        // blank line
        System.out.println();

        // Prints welcome messages
        System.out.println("              🌟 Welcome to our ice cream shop! 🌟");
        System.out.println("          Create your perfect treat, one scoop at a time.");
        System.out.println();

        // Prompt user to press ENTER
        System.out.println("                  Press ENTER to continue...");

        // read keyboard input
        Scanner scanner = new Scanner(System.in);

        // Wait for user to press ENTER
        scanner.nextLine();

        // 3 blank lines to clear screen
        System.out.println("\n\n\n");
    }

    // Static method to display the full menu with all flavors and options

    public static void printMenuArt() {
        // Clear screen with blank lines
        System.out.println("\n\n\n");

        // header box with title
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                      🍦  FULL MENU  🍦                         ║");
        System.out.println("║                     ARRAYS OF FLAVORS                          ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // CONTAINERS section

        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │                    🥤 CONTAINERS 🥤                        │");
        System.out.println("  ├────────────────────────────────────────────────────────────┤");
        System.out.println("  │                                                            │");
        System.out.println("  │  ☕ Cup ................................. (Base Price)     │");
        System.out.println("  │  🧇 Waffle Cone ..................................  +$1.00 │");
        System.out.println("  │  🍫 Chocolate-Dipped Cone ........................  +$1.50 │");
        System.out.println("  │  🍪 Cookie Bowl ..................................  +$2.00 │");
        System.out.println("  │  🍨 Sundae Dish ..................................  +$2.50 │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // FLAVORS section
        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │                    🍦 OUR FLAVORS 🍦                       │");
        System.out.println("  ├────────────────────────────────────────────────────────────┤");
        System.out.println("  │                                                            │");
        System.out.println("  │  🤍 CLASSIC VANILLA LOOP                                   │");
        System.out.println("  │     Smooth vanilla with creamy sweetness                   │");
        System.out.println("  │                                                            │");
        System.out.println("  │  🍫 DOUBLE CHOCOLATE CRUNCH                                │");
        System.out.println("  │     Rich chocolate base with cookie crunch                 │");
        System.out.println("  │                                                            │");
        System.out.println("  │  🍓 STRAWBERRY SHORTCODE                                   │");
        System.out.println("  │     Fruity strawberry with little cake bits                │");
        System.out.println("  │                                                            │");
        System.out.println("  │  🌿 MINT CHIP MATRIX                                       │");
        System.out.println("  │     Cool mint with chocolate chips                         │");
        System.out.println("  │                                                            │");
        System.out.println("  │  🍪 COOKIE CIRCUIT                                         │");
        System.out.println("  │     Cookie dough with chocolate swirls                     │");
        System.out.println("  │                                                            │");
        System.out.println("  │  ☕ JAVA CARAMEL SWIRL                                     │");
        System.out.println("  │     Coffee ice cream with caramel ribbons                  │");
        System.out.println("  │                                                            │");
        System.out.println("  │  ⭐ MARGARITA (SIGNATURE FLAVOR)                           │");
        System.out.println("  │     Dark chocolate ice cream with a citrus twist           │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // REGULAR TOPPINGS section (free toppings)
        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │              ✨ REGULAR TOPPINGS (FREE!) ✨                │");
        System.out.println("  ├────────────────────────────────────────────────────────────┤");
        System.out.println("  │                                                            │");
        System.out.println("  │  • Sprinkles           • Crushed Cookies                   │");
        System.out.println("  │  • Brownie Bits        • Mini Marshmallows                 │");
        System.out.println("  │  • Coconut Flakes      • Chocolate Chips                   │");
        System.out.println("  │  • Cherries            • Candy Pieces                      │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // PREMIUM TOPPINGS section (paid toppings)
        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │            💎 PREMIUM TOPPINGS (Extra Cost) 💎             │");
        System.out.println("  ├────────────────────────────────────────────────────────────┤");
        System.out.println("  │                                                            │");
        System.out.println("  │  • Brownie Chunks         • Cookie Dough Bites             │");
        System.out.println("  │  • Peanut Butter Cups     • Caramel Swirl                  │");
        System.out.println("  │  • Fudge Sauce            • Cheesecake Bites               │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // FREE CONDIMENTS section
        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │            🍯 FREE CONDIMENTS (No Extra Cost!) 🍯          │");
        System.out.println("  ├────────────────────────────────────────────────────────────┤");
        System.out.println("  │                                                            │");
        System.out.println("  │  • Chocolate Syrup        • Strawberry Sauce               │");
        System.out.println("  │  • Caramel Drizzle        • Marshmallow Cream              │");
        System.out.println("  │  • Honey Drizzle                                           │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // SPECIAL OPTION section
        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │                   🌟 SPECIAL OPTION 🌟                     │");
        System.out.println("  ├────────────────────────────────────────────────────────────┤");
        System.out.println("  │                                                            │");
        System.out.println("  │  ❄️  NITRO CHILL FINISH                                    │");
        System.out.println("  │      Smooth, smoky, and cool finish                        │");
        System.out.println("  │      (Extra cost varies by size)                           │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();

        // footer box with instruction
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║              Press ENTER to return to home menu...             ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");

        // Scanner to read input
        Scanner scanner = new Scanner(System.in);

        // Waits for user to press ENTER
        scanner.nextLine();

        // Clear screen
        System.out.println("\n\n\n");
    }

    // method to display home menu
    public static void printHome() {
        // Print blank line
        System.out.println();

        // header box
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                         🏠 HOME 🏠                            ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // menu options box
        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │                                                            │");
        System.out.println("  │    1️⃣   New Order                                          │");
        System.out.println("  │                                                            │");
        System.out.println("  │    9️⃣   Show Full Menu                                     │");
        System.out.println("  │                                                            │");
        System.out.println("  │    8️⃣   Email Signup (Get Discount Code!)                 │");
        System.out.println("  │                                                            │");
        System.out.println("  │    0️⃣   Exit                                               │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();
    }

    // method to display order menu
    public static void printOrderMenu() {
        // Print blank line
        System.out.println();

        // header box
        System.out.println("╔════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                                                ║");
        System.out.println("║                      🛒 ORDER MENU 🛒                          ║");
        System.out.println("║                                                                ║");
        System.out.println("╚════════════════════════════════════════════════════════════════╝");
        System.out.println();

        // menu options box
        System.out.println("  ┌────────────────────────────────────────────────────────────┐");
        System.out.println("  │                                                            │");
        System.out.println("  │    1️⃣   Add Ice Cream Item                                 │");
        System.out.println("  │                                                            │");
        System.out.println("  │    2️⃣   Add Drink                                          │");
        System.out.println("  │                                                            │");
        System.out.println("  │    3️⃣   Add Side                                           │");
        System.out.println("  │                                                            │");
        System.out.println("  │    4️⃣   Checkout                                           │");
        System.out.println("  │                                                            │");
        System.out.println("  │    5️⃣   Apply Coupon Code                                  │");
        System.out.println("  │                                                            │");
        System.out.println("  │    6️⃣   Add Signature Item (Margarita Special)             │");
        System.out.println("  │                                                            │");
        System.out.println("  │    0️⃣   Cancel Order                                       │");
        System.out.println("  │                                                            │");
        System.out.println("  └────────────────────────────────────────────────────────────┘");
        System.out.println();
    }
}