package ShaharAndYahli;

public class AddProductToSellerCommand implements Command {
    private MenuController controller;

    public AddProductToSellerCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addProductToSellerAction();
    }
}
