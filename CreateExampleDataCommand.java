package ShaharAndYahli;

public class CreateExampleDataCommand implements Command {
    private MenuController controller;

    public CreateExampleDataCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.createExampleDataAction();
    }
}
