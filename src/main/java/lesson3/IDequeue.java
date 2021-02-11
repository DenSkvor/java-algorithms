package lesson3;

public interface IDequeue<E> {

    void insertLeft(E value);
    void insertRight(E value);

    E removeLeft();
    E removeRight();

    E peekLeft();
    E peekRight();
}
