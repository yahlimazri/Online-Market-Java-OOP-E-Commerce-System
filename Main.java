package ShaharAndYahli;

//שחר טטרו- ת״ז 211822648
//יהלי מצרי - ת״ז 324974732
//מרצה- ארנון

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayListManager arrayListManager = new ArrayListManager();
        Caretaker caretaker = new Caretaker();
        
        MarketFacade marketFacade = MarketFacade.getInstance(10, arrayListManager);
        MarketInterface market = marketFacade;

        MenuController controller = new MenuController(market, scanner, arrayListManager);

        // יצירת תפריט חדש
        Menu menu = new Menu();

        // הוספת פקודות לתפריט עבור הפעולות הקיימות
        menu.addCommand(1, new AddSellerCommand(controller));
        menu.addCommand(2, new AddBuyerCommand(controller));
        menu.addCommand(3, new AddProductToSellerCommand(controller));
        menu.addCommand(4, new AddProductToCartCommand(controller));
        menu.addCommand(5, new CheckoutCommand(controller));
        menu.addCommand(6, new DisplayBuyersCommand(market));
        menu.addCommand(7, new DisplaySellersCommand(market));
        menu.addCommand(8, new DisplayProductsByCategoryCommand(controller));
        menu.addCommand(9, new CreateCartFromHistoryCommand(controller));
        menu.addCommand(10, new CreateExampleDataCommand(controller));
        
        // פקודות לפי המטלה הנוכחית (99-103)
        menu.addCommand(99, new DisplayProductNamesCommand(controller));
        menu.addCommand(100, new ProcessUniqueStringsCommand(controller));
        menu.addCommand(101, new CountStringOccurrencesCommand(controller));
        menu.addCommand(102, new DuplicateAndReverseStringsCommand(controller));
        menu.addCommand(103, new SortAndPrintStringsCommand(controller));

        // פקודות חדשות למטלה (104 - שמירה, 105 - שחזור)
        menu.addCommand(104, new SaveArrayListCommand(arrayListManager, caretaker, market));
        menu.addCommand(105, new RestoreArrayListCommand(arrayListManager, caretaker, market));
        
        // לולאת התפריט
        int choice;
        do {
            menu.displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // ניקוי ה-buffer
            menu.executeCommand(choice);
        } while (choice != 0);

        System.out.println("Exiting program...");
        scanner.close();
    }
}
