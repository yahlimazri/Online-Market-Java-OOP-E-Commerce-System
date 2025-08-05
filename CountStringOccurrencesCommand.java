package ShaharAndYahli;

public class CountStringOccurrencesCommand implements Command {
    private MenuController controller;

    public CountStringOccurrencesCommand(MenuController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        controller.countStringOccurrencesAction();
    }
}
