package ShaharAndYahli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private Map<Integer, Command> commands = new HashMap<>();

    public void addCommand(int option, Command command) {
        commands.put(option, command);
    }

    public void executeCommand(int option) {
        if (commands.containsKey(option)) {
            commands.get(option).execute();
        } else {
            System.out.println("Invalid option.");
        }
    }

    public void displayMenu() {
        System.out.println("Menu Options:");
        
        // יצירת רשימה ממוינת של המפתחות (מספרי האפשרויות בתפריט)
        List<Integer> sortedKeys = new ArrayList<>(commands.keySet());
        Collections.sort(sortedKeys);

        // מעבר על המפתחות הממוינים והדפסתם
        for (Integer option : sortedKeys) {
            System.out.println(option + ". Execute Command " + option);
        }
    }

}
