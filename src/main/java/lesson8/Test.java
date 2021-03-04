package lesson8;

public class Test {

    public static void main(String[] args) {
        doTask();
    }

    private static void doTask() {
        HashTableImpl<String, Integer> fruits = new HashTableImpl<>();

        fruits.put("apple", 100);
        fruits.put("apple", 90);
        fruits.put("orange", 110);
        fruits.put("banana", 70);
        fruits.put("kiwi", 120);
        fruits.put("mango", 150);

        fruits.display();

        System.out.println("До перемасштабирования (порог 0,75): ");
        System.out.println("Размер: " + fruits.size());
        System.out.println("Емкость: " + fruits.capacity());

        fruits.put("peach", 160);
        fruits.display();

        System.out.println("После перемасштабирования (порог 0,75): ");
        System.out.println("Размер: " + fruits.size());
        System.out.println("Емкость: " + fruits.capacity());


        System.out.println("Удалили апельсин. Его цена: " + fruits.remove("orange"));

        System.out.println("Размер: " + fruits.size());
        System.out.println("Емкость: " + fruits.capacity());
        fruits.display();

        System.out.println("Банан: " + fruits.get("banana"));
    }
}
