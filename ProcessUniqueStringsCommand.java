package ShaharAndYahli;

public class ProcessUniqueStringsCommand implements Command {
    private MenuController controller;

    public ProcessUniqueStringsCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.processStringsWithoutDuplicatesAction();
    }
}
