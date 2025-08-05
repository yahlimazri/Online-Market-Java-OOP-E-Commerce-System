package ShaharAndYahli;

public class Action2 implements IterationListener {
    @Override
    public void onIterationEnded(String message) {
        System.out.println("Action2 got: " + message);
    }
}
