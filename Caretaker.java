package ShaharAndYahli;

import java.util.ArrayList;
import java.util.List;

public class Caretaker {
    private List<Memento> savedStates = new ArrayList<>();

    public void saveState(Memento memento) {
        savedStates.add(memento);
    }

    public Memento restoreState() {
        if (savedStates.isEmpty()) {
            System.out.println("⚠ No saved state to restore!");
            return null;
        }
        return savedStates.remove(savedStates.size() - 1); // מחזיר את המצב האחרון ומסיר אותו מהרשימה
    }

    public Memento getLastSavedState() {
        if (savedStates.isEmpty()) {
            return null;
        }
        return savedStates.get(savedStates.size() - 1);
    }
}
