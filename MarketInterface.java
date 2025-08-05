package ShaharAndYahli;

import java.util.List;

public interface MarketInterface {
    boolean addSeller(String username, String password);
    boolean addBuyer(String username, String password, Address address);
    boolean addProductToSeller(String sellerUsername, Product product);
    boolean addProductToBuyersCart(String buyerUsername, String sellerUsername, String productName);
    boolean checkout(String buyerUsername) throws EmptyCartException;
    void listAllSellers();
    void listAllBuyers();
    boolean isBuyerExist(String username);
    boolean isSellerExist(String username);
    Product[] getSellerProducts(String sellerUsername);
    void displayBuyerDetails();
    void displaySellerDetails();
    boolean isUsernameTaken(String username, String userType);
    boolean isProductExist(String sellerUsername, String productName);
    void displayProductsByCategory(String category);
    void createNewCartFromHistory(Buyer buyer) throws EmptyCartException;
    Buyer getBuyerByUsername(String username);
    Seller[] getAllSellers();
    boolean hasBuyer(String buyerName);
    boolean hasSeller(String sellerName);
    Seller getSeller(String sellerName);
    void saveProductList();
    void restoreProductList(List<Product> restoredProducts);
    void restoreSellers();
    void loadSellers();
    void clearSellers();  
    void clearProducts(); 
}
