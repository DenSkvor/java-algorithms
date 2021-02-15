package lesson4;

public class Test {

    public static void main(String[] args) {

        linkedlistTest();

    }

    private static void linkedlistTest() {
        LinkedListImpl<Integer> ll = new LinkedListImpl<>();
        for (int i = 0; i < 5; i++) {
            ll.insertFirst(4 - i);
            ll.insertLast(5 + i);
        }
        ll.display();
        ll.removeFirst();
        ll.removeLast();
        ll.display();
        System.out.println(ll.remove(6));
        System.out.println(ll.remove(10));
        ll.display();
        System.out.println(ll.getFirst());
        System.out.println(ll.getLast());
        System.out.println(ll.contains(10));

        System.out.println("---- foreach ----");
        for (Integer i : ll) {
            System.out.println(i);
        }

        System.out.println("---- iterator ----");
        LinkedListImpl<Integer> ll2 = new LinkedListImpl<>();
        LinkIterator<Integer> iterator = ll2.iterator();

        for (int i = 0; i < 10; i++) {
            iterator.addNext(i);
        }
        while (iterator.hasNext()) System.out.println(iterator.next());
        System.out.println("--------");
        while (iterator.hasPrevious()) System.out.println(iterator.previous());
        System.out.println("--------");

        for (int i = 0; i < 10; i++) {
            iterator.addPrevious(i);
        }
        while (iterator.hasNext()) System.out.println(iterator.next());
        System.out.println("--------");
        while (iterator.hasPrevious()) System.out.println(iterator.previous());
        System.out.println("--------");

        iterator.reset();
    }
}
