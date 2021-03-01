package lesson7;

public interface IGraph {

    Graph addVertex(String label);

    Graph addEdge(String startLabel, String endLabel);

    Graph addEdges(String startLabel, String secondLabel, String... others);

    Graph addEdgesInLine(String... labels);

    int getSize();

    void display();

    /**
     * англ. Depth-first search, DFS
     */
    void dfs(String startLabel);

    /**
     * англ. breadth-first search, BFS
     */
    void bfs(String startLabel);
}
