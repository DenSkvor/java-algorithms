package lesson5;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        doTasks();
    }

    private static void doTasks() {
        System.out.println("Task 1");
        System.out.println(exponentiation(2, -3));

        System.out.println("Task 2*");
        Bag bag = new Bag(5, new ArrayList<Item>(){{
            add(new Item(1, 4, "s: 1 | c: 4"));
            add(new Item(2, 3, "s: 2 | c: 3"));
            add(new Item(3, 5, "s: 3 | c: 5"));
            add(new Item(4, 2, "s: 4 | c: 2"));
            add(new Item(1, 1, "s: 1 | c: 1"));
        }});
        ItemsSet is = bag.collectBag().getCompleteItemsSet();

        System.out.println("Суммарная стоимость: " + is.getCost() +"\n" + "Суммарный вес: " + is.getSize() + "\n" + "Перечень вещей: " + is.getItems());
    }


    static double exponentiation(double value, int deg){
        if (value == 0) return 0;
        if (deg == 0) return 1;
        if(deg < 0) {
            value = 1/value;
            deg *= -1;
        }
        if (deg == 1) return value;
        return value * exponentiation(value, deg - 1);
    }


}
