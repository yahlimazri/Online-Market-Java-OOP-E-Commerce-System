package ShaharAndYahli;

public class SortAndPrintStringsCommand implements Command {
    private MenuController controller;

    public SortAndPrintStringsCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.sortAndPrintStringsAction();
    }
}
