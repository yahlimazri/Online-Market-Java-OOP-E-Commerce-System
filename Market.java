package ShaharAndYahli;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Market implements MarketInterface {

    private static Market instance = null;
    private Buyer[] buyers;
    private Seller[] sellers;
    private int numOfBuyers;
    private int numOfSellers;
    private Scanner scanner;
    private Caretaker caretaker = new Caretaker();
    private ArrayListManager arrayListManager;
    private List<Seller> savedSellers = new ArrayList<>();
    private Map<String, List<Product>> savedProductsBySeller = new HashMap<>();
    
    public Market(int initialCapacity, ArrayListManager arrayListManager) {
        this.arrayListManager = arrayListManager;
        buyers = new Buyer[initialCapacity];
        sellers = new Seller[initialCapacity];
        numOfBuyers = 0;
        numOfSellers = 0;
        scanner = new Scanner(System.in);
    }
    
    public static Market getInstance(int initialCapacity, ArrayListManager arrayListManager) {
        if (instance == null) {
            instance = new Market(initialCapacity, arrayListManager);
        }
        return instance;
    }
    
    @Override
    public boolean addSeller(String username, String password) {
        if (isUsernameTaken(username, "seller")) {
            return false;
        }
        Seller seller = SellerFactory.createSeller(username, password);
        if (numOfSellers >= sellers.length) {
            extendSellers();
        }
        sellers[numOfSellers++] = seller;
        return true;
    }
    
    @Override
    public boolean addBuyer(String username, String password, Address address) {
        if (isUsernameTaken(username, "buyer")) {
            return false;
        }
        Buyer buyer = BuyerFactory.createBuyer(username, password, address);
        if (numOfBuyers >= buyers.length) {
            extendBuyers();
        }
        buyers[numOfBuyers++] = buyer;
        return true;
    }
    
    @Override
    public boolean addProductToSeller(String sellerUsername, Product product) {
        Seller seller = getSellerByUsername(sellerUsername);
        if (seller != null) {
            seller.addProduct(product);
            arrayListManager.addItem(product);
            return true;
        }
        return false;
    }
    
    @Override
    public void listAllSellers() {
        System.out.println("List of all sellers:");
        for (int i = 0; i < numOfSellers; i++) {
            System.out.println("- " + sellers[i].getUsername());
        }
    }
    
    @Override
    public void listAllBuyers() {
        System.out.println("List of all buyers:");
        for (int i = 0; i < numOfBuyers; i++) {
            System.out.println("- " + buyers[i].getUsername());
        }
    }
    
    @Override
    public void displayBuyerDetails() {
        for (Buyer buyer : buyers) {
            if (buyer != null) {
                System.out.println(buyer);
            }
        }
    }
    
    @Override
    public void displaySellerDetails() {
        for (Seller seller : sellers) {
            if (seller != null) {
                System.out.println(seller);
            }
        }
    }
    
    @Override
    public boolean isUsernameTaken(String username, String userType) {
        return ("buyer".equalsIgnoreCase(userType) && isBuyerExist(username)) ||
               ("seller".equalsIgnoreCase(userType) && isSellerExist(username));
    }
    
    @Override
    public boolean isProductExist(String sellerUsername, String productName) {
        Seller seller = getSellerByUsername(sellerUsername);
        return seller != null && seller.findProductByName(productName) != null;
    }
    
    @Override
    public void displayProductsByCategory(String category) {
        ProductCategory productCategory;
        try {
            productCategory = ProductCategory.valueOf(category.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid category.");
        }
        for (Seller seller : sellers) {
            if (seller != null) {
                for (Product product : seller.getProducts()) {
                    if (product.getCategory() == productCategory) {
                        System.out.println(product);
                    }
                }
            }
        }
    }
    
    @Override
    public Seller[] getAllSellers() {
        return Arrays.copyOf(sellers, numOfSellers);
    }
    
    @Override
    public boolean hasSeller(String sellerName) {
        return isSellerExist(sellerName);
    }
    
    @Override
    public boolean hasBuyer(String buyerName) {
        return isBuyerExist(buyerName);
    }
    
    @Override
    public Seller getSeller(String sellerName) {
        return getSellerByUsername(sellerName);
    }
    
    @Override
    public void saveProductList() {
        savedProductsBySeller.clear();
        for (Seller seller : sellers) {
            if (seller != null) {
                savedProductsBySeller.put(seller.getUsername(), Arrays.asList(seller.getProducts()));
            }
        }
    }
    
    @Override
    public void restoreProductList(List<Product> restoredProducts) {
        for (Product product : restoredProducts) {
            Seller seller = getSellerByUsername(product.getSellerUsername());
            if (seller != null) {
                seller.addProduct(product);
            }
        }
    }
    
    @Override
    public void restoreSellers() {
        if (savedSellers.isEmpty()) {
            System.out.println("No saved sellers found to restore.");
            return;
        }

        System.out.println("Restoring sellers from backup...");

        if (numOfSellers + savedSellers.size() > sellers.length) {
            extendSellers();  
        }

        for (Seller seller : savedSellers) {
            addSeller(seller.getUsername(), seller.getPassword());
        }
    }
    
    @Override
    public void loadSellers() {
        restoreSellers();
    }
    
    public Seller getSellerByUsername(String username) {
        for (Seller seller : sellers) {
            if (seller != null && seller.getUsername().equalsIgnoreCase(username)) {
                return seller;
            }
        }
        return null;
    }
    
    public void extendBuyers() {
        buyers = Arrays.copyOf(buyers, buyers.length * 2);
    }
    
    public boolean isBuyerExist(String username) {
        for (Buyer buyer : buyers) {
            if (buyer != null && buyer.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isSellerExist(String username) {
        return getSellerByUsername(username) != null;
    }
    
    public void extendSellers() {
        sellers = Arrays.copyOf(sellers, sellers.length * 2);
    }
    
    @Override
    public Buyer getBuyerByUsername(String username) {
        for (Buyer buyer : buyers) {
            if (buyer != null && buyer.getUsername().equalsIgnoreCase(username)) {
                return buyer;
            }
        }
        return null;
    }
    
    @Override
    public boolean addProductToBuyersCart(String buyerUsername, String sellerUsername, String productName) {
        Buyer buyer = getBuyerByUsername(buyerUsername);
        Seller seller = getSellerByUsername(sellerUsername);
        
        if (buyer != null && seller != null) {
            Product product = seller.findProductByName(productName);
            if (product != null) {
                buyer.addToCart(product);
                return true;
            }
        }
        return false;
    }
    
    @Override
    public Product[] getSellerProducts(String sellerUsername) {
        Seller seller = getSellerByUsername(sellerUsername);
        if (seller != null) {
            return seller.getProducts();
        }
        return new Product[0]; 
    }
    
    @Override
    public void createNewCartFromHistory(Buyer buyer) throws EmptyCartException {
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer cannot be null.");
        }
        List<Cart> cartHistory = buyer.getCartHistory();
        if (cartHistory.isEmpty()) {
            throw new EmptyCartException("Cannot create a new cart from an empty cart history.");
        }
        Cart lastCart = cartHistory.get(cartHistory.size() - 1);
        buyer.setCart(new Cart(lastCart));
        System.out.println("New cart created from buyer's history.");
    }
    
    @Override
    public boolean checkout(String buyerUsername) throws EmptyCartException {
        Buyer buyer = getBuyerByUsername(buyerUsername);
        if (buyer == null) {
            System.out.println("Buyer not found: " + buyerUsername);
            return false;
        }
        Cart cart = buyer.getCart();
        if (cart == null || cart.getProductCount() == 0) {
            throw new EmptyCartException("Cannot place an order with an empty cart.");
        }
        double total = 0;
        for (Product product : cart.getProducts()) {
            if (product != null) {
                total += product.getPrice();
            }
        }
        Order order = OrderFactory.createOrder(cart.getProducts(), new Date(), total);
        buyer.addOrderToHistory(order);
        buyer.clearCart();
        System.out.println("Checkout complete for buyer " + buyerUsername + ". Total: $" + total);
        return true;
    }
    
    @Override
    public void clearSellers() {
        numOfSellers = 0;
        sellers = new Seller[10]; //  מאתחל מחדש את רשימת המוכרים
        System.out.println("All sellers have been cleared.");
    }

    @Override
    public void clearProducts() {
        arrayListManager.clearProducts();
        System.out.println("All products have been cleared.");
    }

}
