package lesson7;

import java.util.*;

public class Graph implements IGraph {

    private final List<Vertex> vertexList;
    private final boolean[][] adjMat;

    public Graph(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMat = new boolean[maxVertexCount][maxVertexCount];
    }

    @Override
    public Graph addVertex(String label) {
        vertexList.add(new Vertex(label));
        return this;
    }

    @Override
    public Graph addEdge(String startLabel, String endLabel) {
        int startIndex  = indexOf(startLabel);
        int endIndex    = indexOf(endLabel);

        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Invalid label for vertex");
        }

        adjMat[startIndex][endIndex] = true;
        adjMat[endIndex][startIndex] = true;
        return this;
    }

    @Override
    public Graph addEdges(String startLabel, String secondLabel, String... others) {
        addEdge(startLabel, secondLabel);
        for (String other : others) {
            addEdge(startLabel, other);
        }
        return this;
    }

    @Override
    public Graph addEdgesInLine(String... labels) {
        for (int i = 0; i < labels.length - 1; i++) {
            addEdge(labels[i], labels[i + 1]);
        }
        return this;
    }

    private int indexOf(String startLabel) {
        for (int index = 0; index < getSize(); index++) {
            if (vertexList.get(index).getLabel().equals(startLabel)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        for (int i = 0; i < getSize(); i++) {
            System.out.print(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMat[i][j]) {
                    System.out.print(" -> " + vertexList.get(j));
                }
            }
            System.out.println();
        }
    }

    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }

        resetVertexState();
    }

    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
            } else {
                queue.remove();
            }
        }

        resetVertexState();
    }

    public LinkedList<Vertex> bfsShortPath(String startLabel, String finishLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Invalid start label");
        }

        int finishIndex = indexOf(finishLabel);
        if (finishIndex == -1) {
            throw new IllegalArgumentException("Invalid finish label");
        }

        Queue<Vertex> queue = new LinkedList<>();
        Vertex vertex = vertexList.get(startIndex);

        muteVisitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                muteVisitVertex(queue, vertex);
                if(finishLabel.equals(vertex.getLabel())){
                    resetVertexState();
                    return buildShortPath(vertex);
                }
            } else {
                queue.remove();
            }
        }
        resetVertexState();
        return null;
    }

    private LinkedList<Vertex> buildShortPath(Vertex finishVertex) {
        LinkedList<Vertex> path = new LinkedList<>();
        Stack<Vertex> stack = new Stack<>();
        Vertex vertex = finishVertex;
        while (vertex != null){
            stack.push(vertex);
            vertex = vertex.getPrevious();
        }
        while (!stack.empty()) path.add(stack.pop());
        resetVertexPathLink();
        return path;
    }

    private void resetVertexState() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    private void resetVertexPathLink() {
        for (Vertex vertex : vertexList) {
            vertex.setPrevious(null);
        }
    }

    private Vertex getNearUnvisitedVertex(Vertex current) {
        Vertex next;
        int currentIndex = vertexList.indexOf(current);
        for (int i = 0; i < getSize(); i++) {
            if (adjMat[currentIndex][i] && !(next = vertexList.get(i)).isVisited()) {
                if (next.getPrevious() == null) next.setPrevious(current);
                return next;
            }
        }
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        System.out.println(vertex);
        vertex.setVisited(true);
        stack.push(vertex);
    }
    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        System.out.println(vertex);
        vertex.setVisited(true);
        queue.add(vertex);
    }

    private void muteVisitVertex(Queue<Vertex> queue, Vertex vertex) {
        vertex.setVisited(true);
        queue.add(vertex);
    }

}
