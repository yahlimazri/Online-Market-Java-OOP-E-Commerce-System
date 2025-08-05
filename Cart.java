package ShaharAndYahli;

public class Cart {
    private Product[] products;
    private int productCount;

    public Cart() {
        products = new Product[10];
        productCount = 0;
    }

    public Cart(Cart other) {
        this.products = new Product[other.products.length];
        System.arraycopy(other.products, 0, this.products, 0, other.products.length);
        this.productCount = other.productCount;
    }

    public void addProduct(Product product) {
        if (productCount >= products.length) {
            extendProducts();
        }
        products[productCount++] = product;
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

    public int getProductCount() {
        return productCount;
    }

    @Override
    public String toString() {
        if (productCount == 0) {
            return "Cart is empty";
        }
        StringBuilder sb = new StringBuilder("Cart [products=");
        for (int i = 0; i < productCount; i++) {
            sb.append(products[i].toString());
            if (i < productCount - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
