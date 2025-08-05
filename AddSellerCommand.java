package ShaharAndYahli;

public class AddSellerCommand implements Command {
    private MenuController controller;

    public AddSellerCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addSellerAction();
    }
}
