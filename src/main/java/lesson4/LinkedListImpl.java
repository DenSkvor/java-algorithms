package lesson4;


public class LinkedListImpl<E> implements LinkedList<E> {

    private int size;

    private Node first;
    private Node last;


    @Override
    public void insertFirst(E value) {
        first = new Node(value, first, null);
        if (size == 0) last = first;
        else first.next.previous = first;
        size++;
    }

    @Override
    public void insertLast(E value) {
        last = new Node(value,null, last);
        if (size == 0) first = last;
        else last.previous.next = last;
        size++;
    }

    @Override
    public E getFirst() {
        return first == null ? null : first.value;
    }

    @Override
    public E getLast() {
        return last == null ? null : last.value;
    }

    @Override
    public E removeFirst() {
        if (size == 0) return null;
        E returnValue = first.value;
        if (size == 1) first = last = null;
        else {
            first = first.next;
            first.previous = null;
        }
        size--;
        return returnValue;
    }

    @Override
    public E removeLast() {
        if (size == 0) return null;
        E returnValue = last.value;
        if (size == 1) first = last = null;
        else {
            last = last.previous;
            last.next = null;
        }
        size--;
        return returnValue;
    }

    @Override
    public boolean remove(E delValue) {
        if (size == 0) return false;
        Node current = first;
        while (current != null) {
            if (current.value.equals(delValue)) {
                if(size == 1) first = last = null;
                else {
                    current.previous.next = current.next;
                    current.next.previous = current.previous;
                }
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public boolean contains(E srchValue) {
        Node current = first;
        while (current != null) {
            if (current.value.equals(srchValue)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        Node current = first;
        while (current != null) {
            sb.append(current.value.toString()).append(", ");
            current = current.next;
        }
        sb.delete(sb.length() - 2, sb.length()).append("]");
        return sb.toString();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public LinkIterator<E> iterator() {
        return new LinkIteratorImpl<>();
    }

    private class Node {
        private E value;
        private Node next;
        private Node previous;

        Node(E value, Node next, Node previous) {
            this.value = value;
            this.next = next;
            this.previous = previous;
        }
    }

    private class LinkIteratorImpl<C> implements LinkIterator<E> {

        private Node cursorNext;
        private Node cursorPrevious;
        private int index;

        LinkIteratorImpl(){
            cursorNext = first;
        }

        @Override
        public boolean hasNext() {
            return cursorNext != null;
        }

        @Override
        public E next() {
            if(!hasNext()) return null;
            E returnValue = cursorNext.value;
            cursorPrevious = cursorNext;
            cursorNext = cursorNext.next;
            index++;
            return returnValue;
        }

        @Override
        public void addNext(E value) {
            if (!hasNext() || cursorPrevious == cursorNext && cursorNext.next == null) {
                insertLast(value);
                if (size == 1) cursorNext = last;
            }
            else if(!hasPrevious()) {
                insertFirst(value);
                cursorNext = first;
            }
            else {
                Node newNode = new Node(value, cursorPrevious, cursorNext);
                cursorPrevious.next = cursorNext.previous = cursorNext = newNode;
                size++;
            }
        }

        @Override
        public boolean hasPrevious() {
            return cursorPrevious == null ? false : index > 0;
        }

        @Override
        public E previous() {
            cursorPrevious = cursorNext = cursorNext == null ? cursorPrevious : cursorNext.previous;
            if(!hasPrevious()) return null;
            E returnValue = cursorPrevious.value;
            index--;
            return returnValue;
        }

        @Override
        public void addPrevious(E value) {
            if(!hasPrevious() || cursorPrevious == cursorNext && cursorPrevious.previous == null) {
                insertFirst(value);
                if(size == 1) cursorNext = first;
            }
            else {
                Node newNode = new Node(value, cursorPrevious, cursorPrevious.previous);
                cursorPrevious.previous.next = cursorPrevious.previous = newNode;
                size++;
            }
        }

        @Override
        public void set(E newValue) {
            if (cursorPrevious == null) throw new IllegalArgumentException("Некорректный элемент.");
            cursorPrevious.value = newValue;
        }

        @Override
        public void reset(){
            cursorNext = first;
            cursorPrevious = null;
        }
    }
}
