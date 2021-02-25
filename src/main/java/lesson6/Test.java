package lesson6;

public class Test {

    public static void main(String[] args) {

        doTask();

    }

    private static void doTask() {
        int treeCount = 20;
        int balancedTreeCount = 0;

        for(int i = 0; i < treeCount; i++) {
            Tree<Integer> randomTree = new TreeImpl<>();

            for(int height = 0; height < 5; height = TreeImpl.height(randomTree.root())) {
                Integer randomValue = rndSign() * (int)(Math.random() * 26);
                randomTree.add(randomValue);
            }

            System.out.println("Дерево " + (i + 1));
            randomTree.display();
            if (TreeImpl.isBalanced(randomTree.root())) {
                balancedTreeCount++;
                System.out.println("Сбалансировано.\n");
            } else {
                System.out.println("Несбалансировано.\n");
            }
        }

        System.out.println("Процент сбалансированных деревье: " + (double) balancedTreeCount/treeCount * 100);
        System.out.println("Процент несбалансированных деревье: " + (100 - (double) balancedTreeCount/treeCount * 100));
    }

    public static int rndSign() {
        return (int)(Math.random() * 10) <= 4 ? -1 : 1;
    }
}
