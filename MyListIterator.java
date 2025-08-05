package ShaharAndYahli;

import java.util.ListIterator;

public class MyListIterator<T> implements ListIterator<T> {
    private final ListIterator<T> originalIterator;

    public MyListIterator(ListIterator<T> iterator) {
        this.originalIterator = iterator;
    }

    // שינוי שם המתודות על ידי הוספת "my"

    public boolean myHasNext() {
        return originalIterator.hasNext();
    }

    public T myNext() {
        return originalIterator.next();
    }

    public boolean myHasPrevious() {
        return originalIterator.hasPrevious();
    }

    public T myPrevious() {
        return originalIterator.previous();
    }

    public int myNextIndex() {
        return originalIterator.nextIndex();
    }

    public int myPreviousIndex() {
        return originalIterator.previousIndex();
    }

    public void myRemove() {
        originalIterator.remove();
    }

    public void mySet(T e) {
        originalIterator.set(e);
    }

    public void myAdd(T e) {
        originalIterator.add(e);
    }

    // המתודות המקוריות של ListIterator (שאינן בשימוש)
    @Override
    public boolean hasNext() {
        return myHasNext();
    }

    @Override
    public T next() {
        return myNext();
    }

    @Override
    public boolean hasPrevious() {
        return myHasPrevious();
    }

    @Override
    public T previous() {
        return myPrevious();
    }

    @Override
    public int nextIndex() {
        return myNextIndex();
    }

    @Override
    public int previousIndex() {
        return myPreviousIndex();
    }

    @Override
    public void remove() {
        myRemove();
    }

    @Override
    public void set(T e) {
        mySet(e);
    }

    @Override
    public void add(T e) {
        myAdd(e);
    }
}
