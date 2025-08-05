package ShaharAndYahli;

public class DisplayProductNamesCommand implements Command {
    private MenuController controller;

    public DisplayProductNamesCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.displayProductNamesInOrderAction();
    }
}
