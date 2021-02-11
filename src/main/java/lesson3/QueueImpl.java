package lesson3;


public class QueueImpl<E> implements IQueue<E> {

    protected E[] data;

    protected int size;

    protected int head;

    protected int tail;

    protected static final int DEFAULT_HEAD = 0;
    protected static final int DEFAULT_TAIL = -1;

    public QueueImpl(int capacity){
        data = (E[]) new Object[capacity];
        head = DEFAULT_HEAD;
        tail = DEFAULT_TAIL;
    }

    @Override
    public void insert(E value) {
        if(isFull()) throw new IndexOutOfBoundsException("Очередь переполнена.");

        if(tail == data.length - 1) tail = DEFAULT_TAIL;
        data[++tail] = value;
        size++;
    }

    @Override
    public E remove() {
        if(isEmpty()) return null;

        if(head == data.length) head = DEFAULT_HEAD;

        E value = data[head];
        data[head++] = null;
        size--;
        return value;
    }

    @Override
    public E peekHead() {
        return head == data.length ? data[DEFAULT_HEAD] : data[head];
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return data.length;
    }

    @Override
    public boolean isFull() {
        return size == data.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        if(size == 0) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0, j = head; i < size; i++, j++) {
            if(j == data.length) j = 0;
            sb.append(data[j]).append(", ");
        }
        sb.delete(sb.length() - 2, sb.length()).append("]");
        return sb.toString();
    }
}
