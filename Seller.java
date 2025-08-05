package ShaharAndYahli;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Seller {
    private String username;
    private String password;
    private Product[] products;
    private int productCount;

    public Seller(String username, String password) {
        this.username = username;
        this.password = password;
        this.products = new Product[10];
        this.productCount = 0;
    }

    public void addProduct(Product product) {
        for (Product existingProduct : getProducts()) {
            if (existingProduct.getName().equalsIgnoreCase(product.getName())) {
                System.out.println("Product with the same name already exists for this seller.");
                return;
            }
        }
        if (productCount >= products.length) {
            extendProducts();
        }
        products[productCount++] = product;
    }

    public Product findProductByName(String productName) {
        for (Product product : products) {
            if (product != null && product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }


    public boolean hasProduct(String productName) {
        for (Product product : products) {
            if (product != null && product.getName().equals(productName)) {  
                return true; // רק אם השם זהה בדיוק
            }
        }
        return false;
    }

    private void extendProducts() {
        Product[] newProducts = new Product[products.length * 2];
        System.arraycopy(products, 0, newProducts, 0, products.length);
        products = newProducts;
    }

    public Product[] getProducts() {
        Product[] result = new Product[productCount];
        System.arraycopy(products, 0, result, 0, productCount);
        return result;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    @Override
    public String toString() {
        return "Seller [username=" + username + ", products sold=" + productCount + "]";
    }
    
    public void printProducts() {
        if (productCount == 0) {
            System.out.println("No products available.");
            return;
        }

        List<Product> productList = Arrays.asList(products); // המרה לרשימה
        ListIterator<Product> iterator = new MyListIterator<>(productList.listIterator(productList.size()));

        System.out.println("Seller's products in reverse order:");
        while (iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
    }
    
    public void setProductsArray(Product[] newProducts) {
        System.out.println("Setting new product list for seller: " + this.getUsername());
        for (Product p : newProducts) {
            System.out.println(" - " + p.getName());
        }
        this.products = newProducts;
        this.productCount = newProducts.length;
    }

    
    public void clearProducts() {
        System.out.println("Clearing products for seller: " + this.getUsername());
        this.products = new Product[0]; // מאפסים את המערך
        this.productCount = 0; // מאפסים את כמות המוצרים
    }


}
