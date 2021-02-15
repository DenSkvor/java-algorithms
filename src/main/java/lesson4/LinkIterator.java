package lesson4;

import java.util.Iterator;

public interface LinkIterator<E> extends Iterator<E> {

    void addNext(E value);

    void addPrevious(E value);

    void set(E value);

    boolean hasPrevious();

    E previous();

    void reset();
}
