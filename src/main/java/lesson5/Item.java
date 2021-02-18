package lesson5;

public class Item {

    private int size;
    private int cost;
    private String name;

    public Item(int size, int cost, String name) {
        this.size = size;
        this.cost = cost;
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Item{" +
                "size=" + size +
                ", cost=" + cost +
                ", name='" + name + '\'' +
                '}';
    }
}
