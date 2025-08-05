package ShaharAndYahli;

public class Action1 implements IterationListener {

    @Override
    public void onIterationEnded(String message) {
        System.out.println("Action1 got: " + message);
    }
}
