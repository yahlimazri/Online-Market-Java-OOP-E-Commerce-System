package ShaharAndYahli;

public class SaveArrayListCommand implements Command {
    private ArrayListManager arrayListManager;
    private Caretaker caretaker;
    private MarketInterface market;

    public SaveArrayListCommand(ArrayListManager arrayListManager, Caretaker caretaker, MarketInterface market) { 
        this.arrayListManager = arrayListManager;
        this.caretaker = caretaker;
        this.market = market;
    }

    @Override
    public void execute() {
        Memento memento = new Memento(arrayListManager.getItems(), market.getAllSellers());  
        caretaker.saveState(memento);
        System.out.println("Save Command: State saved successfully! " 
            + arrayListManager.getItems().size() + " products and " 
            + market.getAllSellers().length + " sellers saved.");
    }

}
