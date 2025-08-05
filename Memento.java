package ShaharAndYahli;

import java.util.List;
import java.util.ArrayList;

public class Memento {
    private List<Product> savedProducts; // שמירת רשימת המוצרים
    private Seller[] savedSellers; // שמירת מוכרים

    public Memento(List<Product> products, Seller[] sellers) {
        this.savedProducts = new ArrayList<>(products); // יצירת עותק למניעת שינויים חיצוניים
        this.savedSellers = sellers.clone(); // יצירת עותק של המערך
    }

    public List<Product> getSavedProducts() {
        return new ArrayList<>(savedProducts);
    }

    public Seller[] getSavedSellers() {
        return savedSellers.clone();
    }
}
