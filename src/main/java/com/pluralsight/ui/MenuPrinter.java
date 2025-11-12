package com.pluralsight.ui;

public class MenuPrinter { // Handles printing menus to console

    // Print the menu pop-up (like a "modal window" in text)
    public static void printMenuArt(){
        System.out.println("\n\n===============================================");
        System.out.println("          ARRAYS OF FLAVORS  —  MENU           ");
        System.out.println("===============================================");
        System.out.println("   [Cup] [Waffle Cone] [Choc-Dipped Cone]");
        System.out.println("   [Cookie Bowl] [Sundae Dish]");
        System.out.println();
        System.out.println("   FLAVORS & DESCRIPTIONS:");
        System.out.println("   • Classic Vanilla Loop – smooth vanilla with creamy sweetness.");
        System.out.println("   • Double Chocolate Crunch – rich chocolate base with cookie crunch.");
        System.out.println("   • Strawberry Shortcode – fruity strawberry with little cake bits.");
        System.out.println("   • Mint Chip Matrix – cool mint with chocolate chips.");
        System.out.println("   • Cookie Circuit – cookie dough with chocolate swirls.");
        System.out.println("   • Java Caramel Swirl – coffee ice cream with caramel ribbons.");
        System.out.println("   • Midnight Margarita – dark chocolate with a citrus twist.");
        System.out.println();
        System.out.println("   Regular Toppings: sprinkles, crushed cookies, brownie bits,");
        System.out.println("   mini marshmallows, coconut flakes, chocolate chips, cherries, candy pieces");
        System.out.println();
        System.out.println("   Premium: brownie chunks, cookie dough bites, PB cups, caramel swirl,");
        System.out.println("   fudge sauce, cheesecake bites");
        System.out.println("   Special: Nitro Chill Finish");
        System.out.println("===============================================");
        System.out.println("Press ENTER to return...");

        // Wait for user to press ENTER
        new java.util.Scanner(System.in).nextLine();

        System.out.println("\n\n"); // Add spacing after closing menu
    }

    // Print the home screen options
    public static void printHome(){
        System.out.println("Home");
        System.out.println("1) New Order");
        System.out.println("9) Show Menu");
        System.out.println("8) Email Signup (get discount code)");
        System.out.println("7) Build Demo Website");
        System.out.println("0) Exit");
    }

    // Print the order screen options
    public static void printOrderMenu(){
        System.out.println("Order");
        System.out.println("1) Add Item");
        System.out.println("2) Add Drink");
        System.out.println("3) Add Main Side");
        System.out.println("4) Checkout");
        System.out.println("5) Apply Coupon Now");
        System.out.println("6) Add Signature Item (Margarita Special)");
        System.out.println("0) Cancel Order");
    }
}