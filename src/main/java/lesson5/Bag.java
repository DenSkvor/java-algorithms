package lesson5;

import java.util.ArrayList;


public class Bag {

    private int bagSize;

    private ArrayList<Item> items; //исходный перечень вещей

    private ArrayList<Item> tempItems = new ArrayList<>(); //сюда собирается вариант компановки

    private int cursor; //указывает на следующий элемент - кандидат на добавление в компановку

    private ArrayList<ItemsSet> itemsSets = new ArrayList<>(); //набор компановок, из которого будет выбран с наибольшей общей стоимостью

    private ItemsSet completeItemsSet; //итоговая компановка с наибольшей общей стоимостью

    public ItemsSet getCompleteItemsSet() {
        return completeItemsSet;
    }


    public Bag(int bagSize, ArrayList<Item> items) {
        this.bagSize = bagSize;
        this.items = items;
    }

    public Bag collectBag(){

        getItemsSet(bagSize);

        completeItemsSet = itemsSets.stream().sorted((o1, o2) -> o2.getCost() - o1.getCost()).findFirst().get();

        return this;

    }

    private void getItemsSet(int freeBagSize) {

        if(cursor == items.size() || freeBagSize == 0) {
            itemsSets.add(new ItemsSet(tempItems));
            tempItems.remove(tempItems.size() - 1);
            return;
        }

        for (int i = cursor; i < items.size(); i++) {
            Item item = items.get(i); //кандидат на добавление в текующую компановку
            if(item.getSize() <= freeBagSize) {
                tempItems.add(item);
                cursor = 1 + i;
                //для отладки, показывает сколько осталось места в сумке и сколько занимает места текующий вариант компановки
                //System.out.println(tempItems + " " + (freeBagSize - items.get(i).getSize()) + " " + tempItems.stream().map(itm -> itm.getSize()).reduce((integer, integer2) -> integer + integer2).get());
                getItemsSet(freeBagSize - item.getSize());
            }
        }
        if(tempItems.size() > 0) {
            itemsSets.add(new ItemsSet(tempItems));
            tempItems.remove(tempItems.size() - 1);
        }
    }


}
