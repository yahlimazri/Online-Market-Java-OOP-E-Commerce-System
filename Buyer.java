package ShaharAndYahli;

import java.util.ArrayList;
import java.util.List;

public class Buyer {
    private String username;
    private String password;
    private Address address;
    private Cart cart;
    private Order[] orderHistory;
    private int orderCount;
    private List<Cart> cartHistory;

    public Buyer(String username, String password, Address address) {
        this.username = username;
        this.password = password;
        this.address = address;
        // הנחיה #17: יצירת Cart דרך Factory (ראו CartFactory)
        this.cart = CartFactory.createCart();
        this.orderHistory = new Order[10];
        this.orderCount = 0;
        this.cartHistory = new ArrayList<>();
    }

    public void addToCart(Product product) {
        cart.addProduct(product);
    }

    public Cart getCart() {
        return cart;
    }

    public void clearCart() {
        // שומרים את העגלה הקודמת בהיסטוריה
        cartHistory.add(CartFactory.createCart(cart));
        // יוצרים עגלה חדשה
        cart = CartFactory.createCart();
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Address getAddress() {
        return address;
    }

    public void addOrderToHistory(Order order) {
        if (orderCount >= orderHistory.length) {
            expandOrderHistory();
        }
        orderHistory[orderCount++] = order;
    }

    public Order[] getOrderHistory() {
        return orderHistory;
    }

    public List<Cart> getCartHistory() throws EmptyCartException {
        if (cartHistory.isEmpty()) {
            throw new EmptyCartException("Cart history is empty. Please add items to your cart before checking history.");
        }
        return cartHistory;
    }

    private void expandOrderHistory() {
        Order[] newOrderHistory = new Order[orderHistory.length * 2];
        System.arraycopy(orderHistory, 0, newOrderHistory, 0, orderHistory.length);
        orderHistory = newOrderHistory;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Buyer [username=").append(username)
                .append(", address=").append(address)
                .append(", cart=").append(cart)
                .append(", orders=").append(orderHistory.length)
                .append("]");
        return sb.toString();
    }
}
