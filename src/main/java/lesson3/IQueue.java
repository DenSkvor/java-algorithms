package lesson3;

public interface IQueue<E> {

    void insert(E value);

    E remove();

    E peekHead();

    int getSize();

    int getCapacity();

    boolean isFull();

    boolean isEmpty();

    void display();

}
