package ShaharAndYahli;

public class DuplicateAndReverseStringsCommand implements Command {
    private MenuController controller;

    public DuplicateAndReverseStringsCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.duplicateAndReverseStrings();
    }
}
