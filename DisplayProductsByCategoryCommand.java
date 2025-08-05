package ShaharAndYahli;

public class DisplayProductsByCategoryCommand implements Command {
    private MenuController controller;

    public DisplayProductsByCategoryCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.displayProductsByCategoryAction();
    }
}
