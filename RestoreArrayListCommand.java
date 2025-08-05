package ShaharAndYahli;

import java.util.List;

public class RestoreArrayListCommand implements Command {
    private ArrayListManager arrayListManager;
    private Caretaker caretaker;
    private MarketInterface market;

    public RestoreArrayListCommand(ArrayListManager arrayListManager, Caretaker caretaker, MarketInterface market) { 
        this.arrayListManager = arrayListManager;
        this.caretaker = caretaker;
        this.market = market;
    }
    @Override
    public void execute() {
        Memento memento = caretaker.getLastSavedState();
        if (memento == null) {
            System.out.println("⚠ Warning: No saved state found.");
            return;
        }
        //  מחיקת כל הנתונים לפני השחזור
        market.clearSellers();
        arrayListManager.clearProducts();
        //  שחזור מוכרים
        Seller[] restoredSellers = memento.getSavedSellers();
        for (Seller seller : restoredSellers) {
            market.addSeller(seller.getUsername(), seller.getPassword());
        }
        System.out.println("Sellers list restored with " + restoredSellers.length + " sellers.");
        //  שחזור מוצרים
        List<Product> restoredProducts = memento.getSavedProducts();
        for (Product product : restoredProducts) {
            Seller seller = market.getSeller(product.getSellerUsername());
            if (seller != null) {
                seller.addProduct(product);
            }
        }
        System.out.println("State restored with " + restoredProducts.size() + " products.");
    }
}
