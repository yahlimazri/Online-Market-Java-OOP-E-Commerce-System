package ShaharAndYahli;

public class CheckoutCommand implements Command {
    private MenuController controller;

    public CheckoutCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.checkoutAction();
    }
}
