package lesson7;

import java.util.LinkedList;

public class Test {

    public static void main(String[] args) {

        doTask();

    }

    private static void doTask() {
        Graph cities = new Graph(10);
        cities
                .addVertex("Москва")
                .addVertex("Тула")
                .addVertex("Липецк")
                .addVertex("Воронеж")
                .addVertex("Рязань")
                .addVertex("Тамбов")
                .addVertex("Саратов")
                .addVertex("Калуга")
                .addVertex("Орел")
                .addVertex("Курск");

        cities
                .addEdgesInLine("Москва", "Тула", "Липецк", "Воронеж")
                .addEdgesInLine("Москва", "Рязань", "Тамбов", "Саратов", "Воронеж")
                .addEdgesInLine("Москва", "Калуга", "Орел", "Курск", "Воронеж");

        cities.display();
        System.out.println();

        LinkedList<Vertex> shortPath = cities.bfsShortPath("Москва", "Воронеж");
        System.out.println(shortPath.toString());
    }
}
