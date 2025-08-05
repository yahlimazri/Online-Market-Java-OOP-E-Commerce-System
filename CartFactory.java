package ShaharAndYahli;

public class CartFactory {
    public static Cart createCart() {
        return new Cart();
    }

    public static Cart createCart(Cart existingCart) {
        return new Cart(existingCart);
    }
}
