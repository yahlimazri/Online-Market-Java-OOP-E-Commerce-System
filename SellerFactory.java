package ShaharAndYahli;

public class SellerFactory {
    public static Seller createSeller(String username, String password) {
        return new Seller(username, password);
    }
}
