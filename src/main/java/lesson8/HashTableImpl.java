package lesson8;


public class HashTableImpl<K, V> implements HashTable<K, V> {

    private static class Entry<K, V> implements HashTable.Entry<K, V>{

        private K key;
        private V value;

        private Entry<K, V> next;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }

    private final int DEFAULT_CAPACITY = 8;
    private final double DEFAULT_LOAD_FACTOR = 0.75;

    private Entry<K, V>[] data;
    private int size;
    private int capacity;
    private double loadFactor;

    public HashTableImpl(){
        this.capacity = DEFAULT_CAPACITY;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        data = new Entry[capacity];
    }

    public HashTableImpl(int capacity){
        this.capacity = capacity;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        data = new Entry[capacity];
    }

    public HashTableImpl(int capacity, double loadFactor){
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        data = new Entry[capacity];
    }

    private int hash(K key){
        //System.out.println(key.hashCode());
        return Math.abs(key.hashCode())%capacity;
    }

    @Override
    public boolean put(K key, V value) {
        if (size + 1 >= loadFactor * capacity) resize();

        int index = hash(key);
        Entry<K, V> entry = data[index];

        if (entry == null) {
            data[index] = new Entry<>(key, value);
            size++;
            return true;
        }

        while (entry != null){
            if(entry.getKey().equals(key)) {
                entry.setValue(value);
                return true;
            }
            if(entry.getNext() == null){
                entry.setNext(new Entry<>(key, value));
                size++;
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    private void resize() {
        size = 0;
        capacity *= 2;
        Entry<K, V>[] oldData = data;
        data = new Entry[capacity];
        for (int i = 0; i < oldData.length; i++) {
            Entry<K, V> entry = oldData[i];
            while (entry != null){
                put(entry.getKey(), entry.getValue());
                entry = entry.getNext();
            }
        }
    }

    @Override
    public V get(K key) {
        int index = hash(key);
        Entry<K, V> entry = data[index];

        while (entry != null){
            if (entry.getKey().equals(key)) return entry.getValue();
            entry = entry.next;
        }
        return null;
    }

    @Override
    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> entry = data[index];
        Entry<K, V> prev = null;

        while (entry != null){
            if (entry.getKey().equals(key)) {
                if (prev == null) data[index] = entry.getNext();
                else prev.setNext(entry.getNext());
                size--;
                return entry.getValue();
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    public int capacity(){
        return capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void display() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            Entry<K, V> entry = data[i];
            while (entry != null){
                sb.append("(").append(entry.getKey()).append(", ").append(entry.getValue()).append(")");
                entry = entry.getNext();
            }
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}
