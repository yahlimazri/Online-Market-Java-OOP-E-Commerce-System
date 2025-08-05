package ShaharAndYahli;

import java.util.ArrayList;
import java.util.List;

public class ArrayListManager {
    private List<Product> items = new ArrayList<>();  

    public Memento saveState() {
        return new Memento(new ArrayList<>(items), new Seller[0]); // שמירת מוצרים בלבד
    }

    public void restoreState(Memento memento) {
        if (memento == null) {
            System.out.println("No saved state to restore!");
            return;
        }
        items = new ArrayList<>(memento.getSavedProducts()); 
        System.out.println("State restored with " + items.size() + " products.");
    }

    public List<Product> getItems() {
        return items;
    }

    public void addItem(Product item) {
        if (item == null) {
            System.out.println("Error: Trying to add null product to ArrayListManager!");
            return;
        }
        items.add(item);
        System.out.println("Added to ArrayListManager: " + item.getName());
    }

    public void clearProducts() {
        items.clear();
        System.out.println("All products removed from ArrayListManager.");
    }
}
