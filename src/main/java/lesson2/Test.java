package lesson2;

public class Test {

    public static void main(String[] args) {
        arrayTest();
        //System.out.println("----------");
        //sortedArrayTest();
        //System.out.println("----------");
        sortTimingTest();
        
    }

    public static void arrayTest(){
        ArrayImpl<Integer> array = new ArrayImpl<>();

        array.add(9);
        array.add(0);
        array.add(5);
        array.add(4);
        array.add(1);
        array.add(2);
        array.add(6);
        array.add(8);
        array.add(7);
        array.add(3);

        array.display();
        //array.bubbleSort();
        //array.selectSort();
        array.insertSort();
        array.display();

        System.out.println(array.size());

        System.out.println(array.contains((Integer) 4));

        System.out.println(array.get(4));

        System.out.println(array.indexOf((Integer) 4));

        array.remove(4);

        array.display();

        array.remove((Integer) 3);

        array.display();

        System.out.println(array.contains((Integer) 3));
    }

    public static void sortedArrayTest(){
        SortedArrayImpl<Integer> array = new SortedArrayImpl<>();

        array.add(5);
        array.add(4);
        array.add(3);
        array.add(2);
        array.add(1);

        array.display();

        System.out.println(array.size());

        System.out.println(array.contains((Integer) 4));

        System.out.println(array.get(4));

        System.out.println(array.indexOf((Integer) 4));

        array.remove(4);

        array.display();

        array.remove((Integer) 3);

        array.display();

        System.out.println(array.contains((Integer) 3));
    }

    public static void sortTimingTest(){
        final int capacity = 100_000;
        ArrayImpl<Integer> bubbleArr = new ArrayImpl<>(capacity);
        ArrayImpl<Integer> selectArr = new ArrayImpl<>(capacity);
        ArrayImpl<Integer> insertArr = new ArrayImpl<>(capacity);

        for (int i = 0; i < capacity; i++) {
            int rndValue = (int)(Math.random() * 10);
            bubbleArr.add(rndValue);
            selectArr.add(rndValue);
            insertArr.add(rndValue);
        }

        long startTime = System.currentTimeMillis();
        bubbleArr.bubbleSort();
        long stopTime = System.currentTimeMillis();
        System.out.println("Время сортировки пузырьком: " + (stopTime - startTime) + "мc");

        startTime = System.currentTimeMillis();
        selectArr.selectSort();
        stopTime = System.currentTimeMillis();
        System.out.println("Время сортировки выборкой: " + (stopTime - startTime) + "мc");

        startTime = System.currentTimeMillis();
        insertArr.insertSort();
        //insertArr.insertSort2();
        //insertArr.sortInsert();
        stopTime = System.currentTimeMillis();
        System.out.println("Время сортировки вставкой: " + (stopTime - startTime) + "мc");

    }
}
