# Arrays of Flavors - Ice Cream Shop

## About This Project

This is my custom ice cream shop point-of-sale system that I built for my Year Up Capstone 2 project. I wanted to create something fun and different, so I made an ice cream shop where customers can build their own custom ice cream with tons of toppings and options.

The app lets customers order ice cream, pick their flavors and toppings, add drinks and sides, and then it prints out a receipt. I also added some bonus features like discount codes and a signature item.

---

## What It Does

- Build custom ice cream orders with different flavors, sizes, and containers
- Add regular toppings for free and premium toppings that cost extra
- Order drinks and sides to go with your ice cream
- Apply discount codes for 10% or 15% off
- Quick-order a signature item (Midnight Margarita Sundae)
- Generates a receipt file for each order

---

## Screenshots

### Welcome Screen
<img width="541" height="494" alt="image" src="https://github.com/user-attachments/assets/8dd50fb5-70ff-4f45-843e-20f164b94655" />


### Main Menu
<img width="578" height="395" alt="image" src="https://github.com/user-attachments/assets/98d98fe3-8960-462b-9bcb-d6b470c601c6" />


### Building Your Ice Cream
<img width="554" height="584" alt="image" src="https://github.com/user-attachments/assets/4eaffe21-4fa4-4ec0-964c-5f1ea4f8a34f" />
<img width="541" height="431" alt="image" src="https://github.com/user-attachments/assets/51915c8d-d24e-4039-8d8f-a83888c5c7ee" />


### Full Menu
<img width="558" height="858" alt="image" src="https://github.com/user-attachments/assets/1bca0ae5-e6c5-4a40-8091-f98031e600d3" />


### Order Summary at Checkout
<img width="1107" height="425" alt="image" src="https://github.com/user-attachments/assets/d5d18dda-d2af-4f7b-a3f0-b7ba9038b500" />


### Receipt File
<img width="1088" height="720" alt="image" src="https://github.com/user-attachments/assets/8687a15a-48ba-4121-928d-229593d64fe5" />


---

## How to Run

1. Clone or download this project
2. Open it in IntelliJ IDEA
3. Make sure you have these folders:
   - `data/receipts/`
   - `data/outbox/`
4. Run the `Main.java` file
5. Follow the menus to create an order

---

## Code I'm Proud Of

One part of the code I really like is how I handled the pricing for ice cream items. It was tricky because prices change based on the size, container type, and how many premium toppings you add. The first premium topping costs more than extra ones, which makes sense from a business perspective.

Here's the method from my `IceCreamItem` class:
java
public double getTotal() {
    // Start with the base price
    double total = basePrice;

    // Add extra cost if they picked a fancy container
    if (type.equalsIgnoreCase("Waffle Cone")) {
        total = total + Pricing.TYPE_WAFFLE_CONE;
    } else if (type.equalsIgnoreCase("Chocolate-Dipped Cone")) {
        total = total + Pricing.TYPE_CHOC_DIP_CONE;
    } else if (type.equalsIgnoreCase("Cookie Bowl")) {
        total = total + Pricing.TYPE_COOKIE_BOWL;
    } else if (type.equalsIgnoreCase("Sundae Dish")) {
        total = total + Pricing.TYPE_SUNDAE_DISH;
    }

    // Get the pricing for premium items based on size
    double firstPremium = Pricing.getPremiumPrice(size);
    double extraPremium = Pricing.getExtraPremiumPrice(size);

    // Go through each premium topping and calculate cost
    // First one costs more, extras are cheaper
    String[] toppingKeys = premiumToppings.keySet().toArray(new String[0]);
    for (int i = 0; i < toppingKeys.length; i++) {
        String key = toppingKeys[i];
        int count = premiumToppings.get(key);
        
        if (count >= 1) {
            total = total + firstPremium;  // First topping
        }
        if (count > 1) {
            // Extra toppings at a reduced price
            total = total + (extraPremium * (count - 1));
        }
    }

    // Same thing for premium sauces
    String[] sauceKeys = premiumSauces.keySet().toArray(new String[0]);
    for (int i = 0; i < sauceKeys.length; i++) {
        String key = sauceKeys[i];
        int count = premiumSauces.get(i);
        
        if (count >= 1) {
            total = total + firstPremium;
        }
        if (count > 1) {
            total = total + (extraPremium * (count - 1));
        }
    }

    // Add the special finish cost if they want it
    if (specialApplied) {
        total = total + Pricing.getSpecialPrice(size);
    }

    return total;
}

I like this code because it uses HashMaps to keep track of how many of each topping the customer wants, and it automatically calculates everything correctly. It also uses my Pricing class to keep all the prices in one place, which makes it easy to update prices later if needed.



## How I Organized My Code

![Class Diagram](diagrams/class-diagram.png)

I organized my project into different packages:
- **model** - Classes for items (IceCreamItem, Drink, Side, Order)
- **pricing** - Pricing class that handles all the price calculations
- **services** - Helper classes (ReceiptWriter, DiscountService, EmailSignupService)
- **ui** - MenuPrinter class that displays all the menus

I used inheritance for my items - there's a base `Item` class, and then `IceCreamItem`, `Drink`, and `Side` all extend it. This made sense because they all have similar properties like name, size, and price, but each one calculates its total differently.

---

## What I Learned

Building this project taught me a lot about:
- How to design classes and think about what should be in each class
- Using inheritance and polymorphism 
- Working with ArrayLists and HashMaps to store data
- Reading and writing files in Java
- Formatting output to make it look nice
- Breaking big problems into smaller pieces

The hardest part was figuring out how to track all the different toppings and their quantities. I ended up using a HashMap where the key is the topping name and the value is how many times the customer added it. That made it much easier to calculate the total price.

---

## Requirements I Met

This project meets all the capstone requirements:
- Custom food shop (ice cream) with 3 sizes ✓
- Different types of items (7 flavors, 5 containers) ✓
- Regular and premium toppings ✓
- Extra toppings cost extra ✓
- Special option (Nitro Chill Finish) ✓
- Drinks and sides ✓
- Can order 0 items if you get a drink/side ✓
- Receipt saved to file with timestamp name ✓
- All the required screens (Home, Order, Checkout, etc.) ✓

I also added some bonus features:
- Email signup that generates discount codes
- Signature item you can quick-order
- Nice-looking menus with ASCII art

---

## Project Structure
<img width="312" height="537" alt="image" src="https://github.com/user-attachments/assets/03db0400-c9e6-4788-b0e7-be118395cd79" />


## Future Ideas

If I had more time, I'd add:
- Ability to edit items after adding them to the order
- Save order history
- Let customers customize the signature item
- Add more payment options

---

## Author

Mohammed F Khan 
Year Up - Java Development  
January 2025
```

---

## **📐 SIMPLE CLASS DIAGRAM (Hand-Drawn Style)**

I'll give you a super simple text-based diagram you can recreate by hand or in any tool:
```
PROJECT STRUCTURE - Arrays of Flavors

<img width="234" height="427" alt="image" src="https://github.com/user-attachments/assets/bf5d0fee-bd54-47d8-8117-35d98702ec80" />


HELPER CLASSES:

Pricing
  - stores all prices
  - calculates costs by size

ReceiptWriter
  - writes receipt files
  - saves to data/receipts/

DiscountService
  - creates discount codes
  - validates codes

EmailSignupService
  - saves email signups
  - generates confirmation files

MenuPrinter
  - prints welcome screen
  - prints menus
  - prints order screen


KEY RELATIONSHIPS:

Main uses → Order
Order contains → IceCreamItem, Drink, Side
IceCreamItem extends → Item
IceCreamItem uses → Pricing
Main uses → ReceiptWriter
Main uses → DiscountService
Main uses → EmailSignupService
Main uses → MenuPrinter
```

---

## **HOW TO MAKE IT LOOK HAND-DRAWN:**

### **Option 1: Actually Hand-Draw It (BEST for looking natural)**

1. Get a blank piece of paper
2. Draw boxes for each class
3. Write class names at the top
4. Draw lines between classes that are connected
5. Take a picture with your phone
6. Save as `diagrams/class-diagram.png`

**Example sketch:**
```
<img width="390" height="333" alt="image" src="https://github.com/user-attachments/assets/c95ef455-ca5f-496f-a857-ec56088e893b" />


