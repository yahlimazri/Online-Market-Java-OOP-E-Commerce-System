package ShaharAndYahli;

public class ProductFactory {
    public static Product createProduct(String name, double price, ProductCategory category, boolean specialPackaging, double packagingPrice, String sellerUsername) {
        return new Product(name, price, category, specialPackaging, packagingPrice, sellerUsername);
    }
}
