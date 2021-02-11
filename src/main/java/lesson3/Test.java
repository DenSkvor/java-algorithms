package lesson3;

public class Test {

    public static void main(String[] args) {
        doTasks();
    }



    private static void doTasks() {
        System.out.println("Stack test:");
        stackTest();
        System.out.println("-----");

        System.out.println("Queue test:");
        queueTest();
        System.out.println("-----");

        System.out.println("Invert string test:");
        System.out.println(stringInvert("Hello, world!"));
        System.out.println("-----");

        System.out.println("DequeueImpl test:");
        dequeueTest();
        System.out.println("-----");
    }

    private static void dequeueTest() {
        DequeueImpl<Integer> dequeue = new DequeueImpl<>(10);
        for (int i = 4, j = 5; i >= 0 && j < 10; i--, j++) {
            dequeue.insertLeft(i);
            dequeue.insertRight(j);
        }
        dequeue.display();

        System.out.println(dequeue.peekLeft());
        System.out.println(dequeue.peekRight());

        dequeue.removeLeft();
        dequeue.removeLeft();
        dequeue.removeLeft();
        dequeue.insertRight(0);
        dequeue.insertRight(1);
        dequeue.insertRight(2);
        dequeue.display();

        dequeue.removeRight();
        dequeue.removeRight();
        dequeue.removeRight();
        dequeue.insertLeft(2);
        dequeue.insertLeft(1);
        dequeue.insertLeft(0);
        dequeue.display();

        for (int i = 0; i < dequeue.getCapacity()/2; i++) {
            System.out.println(dequeue.removeLeft() + " " + dequeue.removeRight());
        }
        dequeue.display();
    }

    private static String stringInvert(String string) {
        StackImpl<Character> charStack = new StackImpl<>(string.length());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < charStack.getCapacity(); i++) {
            charStack.push(string.charAt(i));
        }
        while (!charStack.isEmpty()){
            sb.append(charStack.pop());
        }
        return sb.toString();
    }

    private static void queueTest() {
        QueueImpl<Integer> queue = new QueueImpl<>(5);
        for (int i = 0; i < queue.getCapacity(); i++) {
            queue.insert(i);
        }
        queue.display();

        while (!queue.isEmpty()) System.out.println(queue.remove());
        queue.display();
    }

    private static void stackTest() {
        StackImpl<Integer> stack = new StackImpl<>(5);
        for (int i = 0; i < stack.getCapacity(); i++) {
            stack.push(i);
        }
        stack.display();

        while (!stack.isEmpty()) System.out.println(stack.pop());
        stack.display();
    }
}
