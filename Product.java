package ShaharAndYahli;

public class Product {
    private static int serialCounter = 0;
    private int serialNumber;
    private String name;
    private double price;
    private ProductCategory category;
    private boolean specialPackaging;
    private double packagingPrice;
    private String sellerUsername; 

    public Product(String name, double price, ProductCategory category, boolean specialPackaging, double packagingPrice, String sellerUsername) {
        this.serialNumber = ++serialCounter;
        this.name = name;
        this.price = price;
        this.category = category;
        this.specialPackaging = specialPackaging;
        this.packagingPrice = specialPackaging ? packagingPrice : 0;
        this.sellerUsername = sellerUsername; 
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price + packagingPrice;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public boolean hasSpecialPackaging() {
        return specialPackaging;
    }

    public double getPackagingPrice() {
        return packagingPrice;
    }

    public String getSellerUsername() {
        return sellerUsername;
    }

    @Override
    public String toString() {
        return String.format("%s: serialNumber - %d, packaging price - %.1f, product price - %.1f, total price - %.1f, category - %s, seller - %s.",
                name, serialNumber, packagingPrice, price, getPrice(), category, sellerUsername);
    }
}
