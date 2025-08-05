package ShaharAndYahli;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.List;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.LinkedHashMap;
import java.util.TreeSet;
import java.util.Map;


public class MenuController {
    private MarketInterface market;
    private Scanner scanner;
    private List<IterationListener> iterationListeners;
    private ArrayListManager arrayListManager;

    public MenuController(MarketInterface market, Scanner scanner, ArrayListManager arrayListManager) {
        this.market = market;
        this.scanner = scanner;
        this.iterationListeners = new ArrayList<>();
        this.arrayListManager = arrayListManager;
    }

    public void addIterationListener(IterationListener listener) {
        this.iterationListeners.add(listener);
    }

    private void notifyIterationEnded(String message) {
        for (IterationListener listener : iterationListeners) {
            listener.onIterationEnded(message);
        }
    }   
    
    public void addSellerAction() {
        boolean added = false;
        while (!added) {
            System.out.println("Enter seller username:");
            String sellerUsername = scanner.nextLine();
            if (market.isUsernameTaken(sellerUsername, "seller")) {
                System.out.println("Seller already exists. Please enter a different username.");
                continue;
            }
            System.out.println("Enter seller password:");
            String sellerPassword = scanner.nextLine();
            added = market.addSeller(sellerUsername, sellerPassword);
        }
    }

    public void addBuyerAction() {
        boolean added = false;
        while (!added) {
            System.out.println("Enter buyer username:");
            String buyerUsername = scanner.nextLine();
            if (market.isUsernameTaken(buyerUsername, "buyer")) {
                System.out.println("Buyer already exists. Please enter a different username.");
                continue;
            }
            System.out.println("Enter buyer password:");
            String buyerPassword = scanner.nextLine();

            System.out.println("Enter buyer street name:");
            String streetName = scanner.nextLine();
            System.out.println("Enter buyer building number:");
            String buildingNumber = scanner.nextLine();
            System.out.println("Enter buyer city:");
            String city = scanner.nextLine();
            System.out.println("Enter buyer state:");
            String state = scanner.nextLine();

            // הנחיה #17: באמצעות Factory במקום new Address
            Address address = AddressFactory.createAddress(streetName, buildingNumber, city, state);

            added = market.addBuyer(buyerUsername, buyerPassword, address);
        }
    }

    public void addProductToSellerAction() {
        boolean sellerFound = false;
        while (!sellerFound) {
            market.listAllSellers();
            System.out.print("Enter seller username from the list above: ");
            String sellerUsername = scanner.nextLine();
            if (market.isSellerExist(sellerUsername)) {
                System.out.print("Enter product name: ");
                String productName = scanner.nextLine();
                double productPrice;
                while (true) {
                    System.out.print("Enter product price: ");
                    try {
                        productPrice = scanner.nextDouble();
                        scanner.nextLine();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid price. Please enter a valid decimal number.");
                        scanner.next(); 
                    }
                }

                ProductCategory productCategory = null;
                while (productCategory == null) {
                    System.out.print("Enter product category (KIDS, ELECTRICITY, OFFICE, CLOTHING): ");
                    String productCategoryStr = scanner.nextLine().toUpperCase();
                    try {
                        productCategory = ProductCategory.valueOf(productCategoryStr);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid category. Please choose a valid category.");
                        System.out.println("Available categories: KIDS, ELECTRICITY, OFFICE, CLOTHING");
                    }
                }

                System.out.print("Does this product require special packaging? (yes/no): ");
                String isSpecial = scanner.nextLine();
                double packagingPrice = 0;
                boolean hasSpecialPackaging = false;
                if (isSpecial.equalsIgnoreCase("yes")) {
                    while (true) {
                        System.out.print("Enter packaging price: ");
                        try {
                            packagingPrice = scanner.nextDouble();
                            scanner.nextLine(); 
                            hasSpecialPackaging = true;
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid price. Please enter a valid decimal number.");
                            scanner.next(); 
                        }
                    }
                }

             // הנחיה #17: יצירת product דרך ProductFactory
                Product product = ProductFactory.createProduct(
                    productName, 
                    productPrice, 
                    productCategory, 
                    hasSpecialPackaging, 
                    packagingPrice,
                    sellerUsername 
                );
                sellerFound = market.addProductToSeller(sellerUsername, product);
                if (sellerFound) {
                    System.out.println(productName + " added for seller " + sellerUsername);
                }
            } else {
                System.out.println("Seller not found. Please enter a valid seller username.");
            }
        }
    }

    public void addProductToBuyersCartAction() {
        market.listAllBuyers();
        System.out.print("Enter buyer username from the list above: ");
        String buyerUsername = scanner.nextLine();

        if (!market.isBuyerExist(buyerUsername)) {
            System.out.println("Buyer not found. Exiting add product to buyer's cart.");
            return;
        }

        boolean sellerFound = false;
        while (!sellerFound) {
            market.listAllSellers();
            System.out.print("Enter seller username (from the list above) from whom you want to buy: ");
            String sellerUsername = scanner.nextLine();

            if (!market.isSellerExist(sellerUsername)) {
                System.out.println("Seller not found. Please enter a valid seller username.");
                continue;
            }

            Product[] sellerProducts = market.getSellerProducts(sellerUsername);
            if (sellerProducts.length == 0) {
                System.out.println("Products available from seller " + sellerUsername + ": No products available");
                System.out.print("Do you want to choose a different seller? (yes/no): ");
                String response = scanner.nextLine();
                if (response.equalsIgnoreCase("no")) {
                    return;
                }
            } else {
                sellerFound = true;
                boolean productAdded = false;
                while (!productAdded) {
                    System.out.println("Products available from seller " + sellerUsername + ":");
                    for (Product product : sellerProducts) {
                        System.out.println("- " + product.getName() + " (" + product.getCategory() + "): $" + product.getPrice());
                    }
                    System.out.print("Enter the product name you want to add to the cart: ");
                    String productName = scanner.nextLine();
                    productAdded = market.addProductToBuyersCart(buyerUsername, sellerUsername, productName);
                    if (productAdded) {
                        System.out.println("Product added to buyer's cart.");
                    } else {
                        System.out.println("Product not found. Please choose a different product from the seller " + sellerUsername + ".");
                    }
                }
            }
        }
    }

    public void checkoutAction() {
        boolean validBuyer = false;
        while (!validBuyer) {
            market.listAllBuyers();
            System.out.print("Enter buyer username from the list above: ");
            String buyerUsername = scanner.nextLine();
            try {
                if (market.checkout(buyerUsername)) {
                    System.out.println("Checkout complete for buyer " + buyerUsername);
                    validBuyer = true;
                } else {
                    System.out.println("Buyer not found. Please enter a valid buyer username.");
                }
            } catch (EmptyCartException e) {
                System.out.println("Cannot place an order with an empty cart.");
            }
        }
    }

    public void displayProductsByCategoryAction() {
        boolean validCategory = false;
        while (!validCategory) {
            System.out.print("Enter product category to display (KIDS, ELECTRICITY, OFFICE, CLOTHING): ");
            String category = scanner.nextLine().toUpperCase();
            try {
                market.displayProductsByCategory(category);
                validCategory = true;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid category. Please choose a valid category.");
                System.out.println("Available categories: KIDS, ELECTRICITY, OFFICE, CLOTHING");
            }
        }
    }

    public void createNewCartFromHistoryAction() {
        boolean validBuyer = false;
        while (!validBuyer) {
            market.listAllBuyers();
            System.out.print("Enter buyer username from the list above: ");
            String buyerUsername = scanner.nextLine();
            Buyer buyer = market.getBuyerByUsername(buyerUsername);
            if (buyer != null) {
                try {
                    Cart currentCart = buyer.getCart();
                    int currentCartProductCount = currentCart.getProductCount();

                    if (currentCartProductCount > 0) {
                        System.out.print("Your current cart has items. Do you want to replace it? (yes/no): ");
                        String response = scanner.nextLine();
                        if (response.equalsIgnoreCase("yes")) {
                            System.out.println("Select a cart from history to replace your current cart:");
                            List<Cart> cartHistory = buyer.getCartHistory();
                            for (int i = 0; i < cartHistory.size(); i++) {
                                System.out.println((i + 1) + ": " + cartHistory.get(i));
                            }
                            System.out.print("Enter your choice: ");
                            int choice = scanner.nextInt();
                            scanner.nextLine();

                            if (choice > 0 && choice <= cartHistory.size()) {
                                Cart selectedCart = cartHistory.get(choice - 1);
                                buyer.setCart(new Cart(selectedCart));
                                System.out.println("Cart replaced successfully.");
                            } else {
                                System.out.println("Invalid choice.");
                            }
                        }
                    } else {
                        System.out.println("Current cart is empty.");
                        System.out.println("Select a cart from history to replace your current cart:");
                        List<Cart> cartHistory = buyer.getCartHistory();
                        for (int i = 0; i < cartHistory.size(); i++) {
                            System.out.println((i + 1) + ": " + cartHistory.get(i));
                        }
                        System.out.print("Enter your choice: ");
                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        if (choice > 0 && choice <= cartHistory.size()) {
                            Cart selectedCart = cartHistory.get(choice - 1);
                            buyer.setCart(new Cart(selectedCart));
                            System.out.println("Cart replaced successfully.");
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    }
                    validBuyer = true;
                } catch (EmptyCartException e) {
                    System.out.println("Cannot create a new cart from an empty cart history.");
                }
            } else {
                System.out.println("Buyer not found. Please enter a valid buyer username.");
            }
        }
    }

    public void createExampleDataAction() {
        // Add buyers (only if they don't exist)
        if (!market.hasBuyer("buyer1")) {
            market.addBuyer("buyer1", "password1", AddressFactory.createAddress("Main St", "1", "Tel Aviv", "Israel"));
        }
        if (!market.hasBuyer("buyer2")) {
            market.addBuyer("buyer2", "password2", AddressFactory.createAddress("Second St", "2", "Haifa", "Israel"));
        }
        if (!market.hasBuyer("buyer3")) {
            market.addBuyer("buyer3", "password3", AddressFactory.createAddress("Third St", "3", "Jerusalem", "Israel"));
        }
        if (!market.hasBuyer("buyer4")) {
            market.addBuyer("buyer4", "password4", AddressFactory.createAddress("Fourth St", "4", "Beer Sheva", "Israel"));
        }

        // Add sellers (only if they don't exist)
        if (!market.hasSeller("seller1")) {
            market.addSeller("seller1", "password1");
        }
        if (!market.hasSeller("seller2")) {
            market.addSeller("seller2", "password2");
        }
        if (!market.hasSeller("seller3")) {
            market.addSeller("seller3", "password3");
        }
        if (!market.hasSeller("seller4")) {
            market.addSeller("seller4", "password4");
        }

        // Add products only if they don't already exist
        addProductIfNotExists("seller1", "Toy Car", 50.0, ProductCategory.KIDS, false, 0);
        addProductIfNotExists("seller1", "Puzzle", 40.0, ProductCategory.KIDS, true, 5);
        addProductIfNotExists("seller1", "Laptop", 2000.0, ProductCategory.ELECTRICITY, true, 50);
        addProductIfNotExists("seller1", "Smartphone", 1200.0, ProductCategory.ELECTRICITY, false, 0);

        addProductIfNotExists("seller2", "bAmBi Doll", 150.0, ProductCategory.KIDS, false, 0);
        addProductIfNotExists("seller2", "Notebook", 20.0, ProductCategory.OFFICE, false, 0);
        addProductIfNotExists("seller2", "Pen", 5.0, ProductCategory.OFFICE, false, 0);
        addProductIfNotExists("seller2", "Action Figure", 70.0, ProductCategory.KIDS, true, 8);

        addProductIfNotExists("seller3", "BAMBI Jacket", 180.0, ProductCategory.CLOTHING, true, 15);
        addProductIfNotExists("seller3", "Winter Coat", 250.0, ProductCategory.CLOTHING, true, 20);
        addProductIfNotExists("seller3", "Electric Heater", 300.0, ProductCategory.ELECTRICITY, false, 0);
        addProductIfNotExists("seller3", "Kids Book", 25.0, ProductCategory.KIDS, false, 0);

        addProductIfNotExists("seller4", "Color Pencils", 15.0, ProductCategory.OFFICE, false, 0);
        addProductIfNotExists("seller4", "Laptop Case", 100.0, ProductCategory.ELECTRICITY, true, 10);
        addProductIfNotExists("seller4", "Toy Train", 60.0, ProductCategory.KIDS, false, 0);
        addProductIfNotExists("seller4", "Office Chair", 400.0, ProductCategory.OFFICE, true, 30);

        // Add new examples to test option 100 (without duplication)
        addProductIfNotExists("seller1", "Toy Car", 55.0, ProductCategory.KIDS, false, 0);
        addProductIfNotExists("seller1", "TOY car", 60.0, ProductCategory.KIDS, false, 0);
        addProductIfNotExists("seller2", "toy CAR", 65.0, ProductCategory.KIDS, false, 0);
        addProductIfNotExists("seller2", "Puzzle", 45.0, ProductCategory.KIDS, true, 5);
        addProductIfNotExists("seller3", "LAPTOP", 2100.0, ProductCategory.ELECTRICITY, true, 50);

        System.out.println("Example data created successfully!");
    }

    //  פונקציה לבדיקה האם המוצר כבר קיים לפני הוספה
    private void addProductIfNotExists(String sellerName, String name, double price, ProductCategory category, boolean specialPackaging, int discount) {
        Seller seller = market.getSeller(sellerName);
        if (seller != null) {
            if (seller.hasProduct(name)) {  // אם המוצר כבר קיים, נדפיס הודעה במקום להוסיף
                System.out.println("Product '" + name + "' already exists for seller " + sellerName + ".");
            } else {
                market.addProductToSeller(sellerName, ProductFactory.createProduct(name, price, category, specialPackaging, discount, sellerName));
                System.out.println("Added new product '" + name + "' to seller " + sellerName + ".");
            }
        } else {
            System.out.println("Seller " + sellerName + " not found!");
        }
    }

    public void displayProductNamesInOrderAction() {
        System.out.println("Product names in order:");
        for (Seller seller : market.getAllSellers()) {
            for (Product product : seller.getProducts()) {
                System.out.println(product.getName());
            }
        }
    }

    public void processStringsWithoutDuplicatesAction() {
        LinkedHashMap<String, Integer> productCounts = new LinkedHashMap<>();
        for (Seller seller : market.getAllSellers()) {
            for (Product product : seller.getProducts()) {
                String productName = product.getName().toLowerCase();
                productCounts.put(productName, productCounts.getOrDefault(productName, 0) + 1);
            }
        }
        System.out.println("Unique product names with counts:");
        for (Map.Entry<String, Integer> entry : productCounts.entrySet()) {
            System.out.printf("%s ..........%d\n", entry.getKey(), entry.getValue());
        }
    }
  
    public void countStringOccurrencesAction() {
        System.out.println("Please enter the string:");
        String input = scanner.nextLine().toLowerCase();
        int count = 0;
        for (Seller seller : market.getAllSellers()) {
            for (Product product : seller.getProducts()) {
                if (product.getName().toLowerCase().contains(input)) {
                    count++;
                }
            }
        }
        System.out.println("The number of times '" + input + "' appears in the products is " + count + ".");
    }

    public void duplicateAndReverseStrings() {
        ArrayList<String> duplicatedStrings = new ArrayList<>();
        for (Seller seller : market.getAllSellers()) {
            for (Product product : seller.getProducts()) {
                String lowerCaseName = product.getName().toLowerCase();
                if (!duplicatedStrings.contains(lowerCaseName)) {
                    duplicatedStrings.add(lowerCaseName);
                    duplicatedStrings.add(lowerCaseName);
                }
            }
        }

        System.out.println("Strings in reverse order:");
        ListIterator<String> listIt = duplicatedStrings.listIterator(duplicatedStrings.size());
        while (listIt.hasPrevious()) {
            System.out.println(listIt.previous());
        }
        System.out.print("\nDo you want to see the output of my self-implemented iterators (Y/y or any other key to skip)? ");
        String choice = scanner.nextLine().trim();

        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("You chose YES. Demonstrating iterator output...");
            Iterator<String> it = duplicatedStrings.iterator();
            while (it.hasNext()) {
                String element = it.next();
            }
            System.out.println("My Iterator ended!");
            notifyIterationEnded("My Iterator ended!");
            ListIterator<String> secondListIt = duplicatedStrings.listIterator();
            while (secondListIt.hasNext()) {
                secondListIt.next();
            }
            System.out.println("My ListIterator ended!");
            notifyIterationEnded("My ListIterator ended!");

        } else {
            System.out.println("Skipping iterator demonstration...");
        }
    }


    private void demoIterator(ArrayList<String> data) {
        System.out.println("\n--- Demo: Iterator (forward) ---");
        Iterator<String> it = data.iterator();
        while (it.hasNext()) {
            String element = it.next();
            System.out.println("Iterator next => " + element);
        }
    }

    private void demoListIterator(ArrayList<String> data) {
        System.out.println("\n--- Demo: ListIterator (forward & backward) ---");
        ListIterator<String> listIt = data.listIterator();
        while (listIt.hasNext()) {
            String element = listIt.next();
            System.out.println("ListIterator next => " + element);
        }
        System.out.println("--- Going backward ---");
        while (listIt.hasPrevious()) {
            String element = listIt.previous();
            System.out.println("ListIterator previous => " + element);
        }
    }

    public void sortAndPrintStringsAction() {
        TreeSet<String> sortedStrings = new TreeSet<>((s1, s2) -> {
            int lengthComparison = Integer.compare(s1.length(), s2.length());
            if (lengthComparison != 0) {
                return lengthComparison;
            }
            return s1.compareToIgnoreCase(s2);
        });

        for (Seller seller : market.getAllSellers()) {
            for (Product product : seller.getProducts()) {
                sortedStrings.add(product.getName().toLowerCase());
            }
        }
        System.out.println("Sorted strings in uppercase:");
        sortedStrings.forEach(s -> System.out.println(s.toUpperCase()));
    }
    
    public void handleUserSelection(int choice) {
        switch (choice) {
            case 104:
                System.out.println("Saving product list...");
                market.saveProductList();
                break;
            case 105:
                System.out.println("Restoring product list...");
                if (arrayListManager != null) {
                    List<Product> restoredProducts = arrayListManager.getItems(); 
                    if (!restoredProducts.isEmpty()) {
                        market.restoreProductList(restoredProducts);
                        System.out.println("Product list restored successfully.");
                    } else {
                        System.out.println("Warning: No saved products to restore.");
                    }
                } else {
                    System.out.println("Error: arrayListManager is not initialized!");
                }
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

}
