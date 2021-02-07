package lesson2;

import java.util.Arrays;

public class ArrayImpl<E extends Comparable<? super E>> implements Array<E> {

    protected E[] data;
    protected int size;

    protected static final int DEFAULT_CAPACITY = 8;

    public ArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public ArrayImpl(int capacity) {
        data = (E[]) new Comparable[capacity];
    }

    @Override
    public void add(E element) {
        if(size == data.length){
            data = Arrays.copyOf(data, size == 0 ? 1 : size * 2);
        }
        data[size++] = element;
    }

    @Override
    public E get(int index) {
        checkIndex(index);

        return data[index];
    }

    @Override
    public int indexOf(E value) {
        for (int i = 0; i < size; i++) {
            if(data[i].equals(value)) return i;
        }
        return -1;
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf(value);
        if(index == -1) return false;
        if(size > 1) System.arraycopy(data, index + 1, data, index, size - (index + 1));
        data[--size] = null;
        return true;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        E value = data[index];
        if(size > 1) System.arraycopy(data, index + 1, data, index, size - (index + 1));
        data[--size] = null;
        return value;
    }

    @Override
    public boolean contains(E value) {
        return indexOf(value) != -1;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < size - 1; i++) {
            sb.append(data[i]).append(", ");
        }
        sb.append(data[size - 1]).append("]");
        return sb.toString();
    }

    public void bubbleSort(){
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if(data[j].compareTo(data[j + 1]) > 0) swap(j, j + 1);
            }
        }
    }

    public void selectSort(){
        int minIndex;
        for (int i = 0; i < size - 1; i++) {
            minIndex = i;
            for (int j = i + 1; j < size; j++) {
                if(data[j].compareTo(data[minIndex]) < 0) minIndex = j;
            }
            if(i != minIndex) swap(i, minIndex);
        }
    }

    //моя реализация
    public void insertSort(){
        for (int i = 1; i < size; i++) {
            int index = i;
            E tempValue = data[i];
            for (int j = i; j > 0; j--) {
                if (data[j - 1].compareTo(tempValue) >= 0) { //не эквивалентно по времени выполнения записи в виде if (tempValue.compareTo(data[j - 1]) <= 0); закомменченный вариант медленнее ~в 2,5 раза
                    data[j] = data[j - 1];
                    index = j - 1;
                }
                else break;
            }
            if(index != i) data[index] = tempValue;
        }
    }

    //моя реализация (упрощенный for)
    public void insertSort2(){
        for (int i = 1; i < size; i++) {
            int index = i;
            E tempValue = data[i];
            for (; index > 0 && tempValue.compareTo(data[index - 1]) <= 0;) {
                data[index] = data[index - 1];
                index--;
            }
            data[index] = tempValue;
        }
    }

    //реализация с лекции
    public void sortInsert() {
        for (int i = 1; i < size; i++) {
            E temp = data[i];
            int in = i;
            while (in > 0 && data[in - 1].compareTo(temp) >= 0) {
                data[in] = data[in - 1];
                in--;
            }
            data[in] = temp;
        }
    }

    private void swap(int indexA, int indexB){
        E temp;

        temp = data[indexA];
        data[indexA] = data[indexB];
        data[indexB] = temp;
    }

    private void checkIndex(int index) {
        if(index < 0 || index >= size) throw new IndexOutOfBoundsException(String.format("Неверный индекс - %d. Max индекс = %d", index, size - 1));
    }
}
