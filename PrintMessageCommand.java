package ShaharAndYahli;

public class PrintMessageCommand implements Command {
    private String message;

    public PrintMessageCommand(String message) {
        this.message = message;
    }

    @Override
    public void execute() {
        System.out.println(message);
    }
}
