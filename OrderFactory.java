package ShaharAndYahli;

import java.util.Date;

public class OrderFactory {
    public static Order createOrder(Product[] products, Date date, double totalPrice) {
        return new Order(products, date, totalPrice);
    }
}
