package ShaharAndYahli;

public class CreateCartFromHistoryCommand implements Command {
    private MenuController controller;

    public CreateCartFromHistoryCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.createNewCartFromHistoryAction();
    }
}
