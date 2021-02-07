package lesson2;

public interface Array<E> {

    void add(E element);

    E get(int index);

    int indexOf(E value);

    boolean remove(E value);

    E remove(int index);

    boolean contains(E value);

    int size();

    void display();
}
