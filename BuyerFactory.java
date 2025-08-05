package ShaharAndYahli;

public class BuyerFactory {
    public static Buyer createBuyer(String username, String password, Address address) {
        return new Buyer(username, password, address);
    }
}
