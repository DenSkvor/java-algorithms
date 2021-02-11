package lesson3;

public interface IStack<E> {

    void push(E value);

    E pop();

    E peek();

    int getSize();

    int getCapacity();

    boolean isFull();

    boolean isEmpty();

    void display();
}
