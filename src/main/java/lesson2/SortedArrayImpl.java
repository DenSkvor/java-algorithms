package lesson2;

public class SortedArrayImpl<E extends Comparable<? super E>> extends ArrayImpl<E>{

    public SortedArrayImpl() {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public SortedArrayImpl(int capacity) {
        data = (E[]) new Comparable[capacity];
    }

    @Override
    public void add(E element) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if(element.compareTo(data[i]) < 0) {
                index = i;
                System.arraycopy(data, index, data, index + 1, size);
                break;
            }
        }
        data[index] = element;
        size++;
    }

    @Override
    public int indexOf(E value) {
        int index = -1;
        int min = 0;
        int max = size - 1;

        while (min <= max) {
            if (value.compareTo(data[(min + max) / 2]) < 0) max = ((min + max) / 2) - 1;
            else if (value.compareTo(data[(min + max) / 2]) > 0) min = ((min + max) / 2) + 1;
            else if (value.compareTo(data[(min + max) / 2]) == 0){
                index = (min + max) / 2;
                break;
            }
        }
        return index;
    }
}
