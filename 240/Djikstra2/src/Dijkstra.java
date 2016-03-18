//Sean Batzel
//Dijkstra's Algorithm Solution
//Test Driver


class Dijkstra {
    private static Graph.Edge[] GRAPH = {
            new Graph.Edge("0", "1", 1),
            new Graph.Edge("0", "3", 6),
            new Graph.Edge("0", "4", 10),
            new Graph.Edge("1", "3", 4),
            new Graph.Edge("1", "2", 2),
            new Graph.Edge("2", "3", 1),
            new Graph.Edge("3", "5", 3),
            new Graph.Edge("5", "4", 3),
    };
    private static String START = "0";
    private static String END = "4";

    public static void main(String[] args) {
        Graph graph = new Graph(GRAPH);
        graph.dijkstra(START);
        graph.printAllPaths();
        graph.printThatPath(END);
    }
}