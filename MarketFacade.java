package ShaharAndYahli;

import java.util.List;

public class MarketFacade implements MarketInterface {
    private static MarketFacade instance = null;
    private MarketInterface market;

    private MarketFacade(int initialCapacity, ArrayListManager arrayListManager) {
        this.market = Market.getInstance(initialCapacity, arrayListManager);
    }

    public static MarketFacade getInstance(int initialCapacity, ArrayListManager arrayListManager) {
        if (instance == null) {
            instance = new MarketFacade(initialCapacity, arrayListManager);
        }
        return instance;
    }

    @Override
    public boolean addSeller(String username, String password) {
        return market.addSeller(username, password);
    }

    @Override
    public boolean addBuyer(String username, String password, Address address) {
        return market.addBuyer(username, password, address);
    }

    @Override
    public boolean addProductToSeller(String sellerUsername, Product product) {
        return market.addProductToSeller(sellerUsername, product);
    }

    @Override
    public boolean addProductToBuyersCart(String buyerUsername, String sellerUsername, String productName) {
        return market.addProductToBuyersCart(buyerUsername, sellerUsername, productName);
    }

    @Override
    public boolean checkout(String buyerUsername) throws EmptyCartException {
        return market.checkout(buyerUsername);
    }

    @Override
    public void listAllSellers() {
        market.listAllSellers();
    }

    @Override
    public void listAllBuyers() {
        market.listAllBuyers();
    }

    @Override
    public boolean isBuyerExist(String username) {
        return market.isBuyerExist(username);
    }

    @Override
    public boolean isSellerExist(String username) {
        return market.isSellerExist(username);
    }

    @Override
    public Product[] getSellerProducts(String sellerUsername) {
        return market.getSellerProducts(sellerUsername);
    }

    @Override
    public void displayBuyerDetails() {
        market.displayBuyerDetails();
    }

    @Override
    public void displaySellerDetails() {
        market.displaySellerDetails();
    }

    @Override
    public boolean isUsernameTaken(String username, String userType) {
        return market.isUsernameTaken(username, userType);
    }

    @Override
    public boolean isProductExist(String sellerUsername, String productName) {
        return market.isProductExist(sellerUsername, productName);
    }

    @Override
    public void displayProductsByCategory(String category) {
        market.displayProductsByCategory(category);
    }

    @Override
    public void createNewCartFromHistory(Buyer buyer) throws EmptyCartException {
        market.createNewCartFromHistory(buyer);
    }

    @Override
    public Buyer getBuyerByUsername(String username) {
        return market.getBuyerByUsername(username);
    }

    @Override
    public Seller[] getAllSellers() {
        return market.getAllSellers();
    }
    
    @Override
    public boolean hasSeller(String sellerName) {
        return market.hasSeller(sellerName);
    }

    @Override
    public boolean hasBuyer(String buyerName) {
        return market.hasBuyer(buyerName);
    }
    
    @Override
    public Seller getSeller(String sellerName) {
        return market.getSeller(sellerName);
    }
    
    @Override
    public void saveProductList() {
        market.saveProductList();
    }

    @Override
    public void restoreProductList(List<Product> restoredProducts) {
        market.restoreProductList(restoredProducts);
    }
    
    @Override
    public void restoreSellers() {
        market.restoreSellers(); 
    }

    @Override
    public void loadSellers() {
        market.loadSellers();
    }
    
    @Override
    public void clearSellers() {
        market.clearSellers();
    }

    @Override
    public void clearProducts() {
        market.clearProducts();
    }

}
