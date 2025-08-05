package ShaharAndYahli;

public class AddBuyerCommand implements Command {
    private MenuController controller;

    public AddBuyerCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addBuyerAction();
    }
}
