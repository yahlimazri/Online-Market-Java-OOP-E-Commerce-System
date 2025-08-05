package ShaharAndYahli;

import java.util.Date;

public class Order {
    private Product[] products;
    private Date date;
    private double totalPrice;

    public Order(Product[] products, Date date, double totalPrice) {
        this.products = products;
        this.date = date;
        this.totalPrice = totalPrice;
    }

    public Product[] getProducts() {
        return products;
    }

    public Date getDate() {
        return date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        if (products.length == 0) {
            return "Order is empty";
        }
        StringBuilder sb = new StringBuilder("Order [products=");
        for (int i = 0; i < products.length; i++) {
            sb.append(products[i].toString());
            if (i < products.length - 1) {
                sb.append(", ");
            }
        }
        sb.append(", date=").append(date).append("]");
        return sb.toString();
    }
}
