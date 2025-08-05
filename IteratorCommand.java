package ShaharAndYahli;

import java.util.Iterator;
import java.util.List;

public class IteratorCommand implements Command {
    private List<String> items;

    public IteratorCommand(List<String> items) {
        this.items = items;
    }

    @Override
    public void execute() {
        Iterator<String> iterator = items.iterator(); // יצירת איטרטור חדש בכל הפעלה
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
