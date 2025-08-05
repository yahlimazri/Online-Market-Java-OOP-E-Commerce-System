package ShaharAndYahli;

public class AddProductToCartCommand implements Command {
    private MenuController controller;

    public AddProductToCartCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.addProductToBuyersCartAction();
    }
}
