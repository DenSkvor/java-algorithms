package lesson3;

public class StackImpl<E> implements IStack<E> {

    private E[] data;

    private int size;

    public StackImpl(int capacity){
        data = (E[]) new Object[capacity];
    }

    @Override
    public void push(E value) {
        if(isFull()) throw new StackOverflowError("Стек переполнен.");

        data[size++] = value;
    }

    @Override
    public E pop() {
        if(isEmpty()) return null;

        E value = data[size - 1];
        data[size - 1] = null;
        size--;
        return value;
    }

    @Override
    public E peek() {
        if(isEmpty()) return null;

        return data[size - 1];
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
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        sb.append(data[size - 1]).append("]");
        return sb.toString();
    }
}
