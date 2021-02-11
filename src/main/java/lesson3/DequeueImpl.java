package lesson3;

public class DequeueImpl<E> extends QueueImpl<E> implements IDequeue<E> {

    private final int DEFAULT_REVERSED_HEAD = data.length;
    private final int DEFAULT_REVERSED_TAIL = data.length - 1;

    public DequeueImpl(int capacity) {
        super(capacity);
    }

    @Override
    public void insertLeft(E value) {
        if(isFull()) throw new IndexOutOfBoundsException("Очередь переполнена.");

        if(head == 0) head = DEFAULT_REVERSED_HEAD;

        data[--head] = value;
        size++;
    }

    @Override
    public void insertRight(E value) {
        super.insert(value);
    }

    @Override
    public E removeLeft() {
        return super.remove();
    }

    @Override
    public E removeRight() {
        if(isEmpty())return null;

        if(tail == -1) tail = DEFAULT_REVERSED_TAIL;

        E value = data[tail];
        data[tail--] = null;
        size--;
        return value;
    }

    @Override
    public E peekLeft() {
        return super.peekHead();
    }

    @Override
    public E peekRight() {
        return tail == -1 ? data[DEFAULT_REVERSED_TAIL] : data[tail];
    }

}
