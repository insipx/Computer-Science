// Found some different existing data structures to handle the weird graph-y stuff...

import java.util.HashMap;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Sean Batzel 3/17/2016.
 */
class Graph {
    private Map<String, Vertex> graph; //Map a String to a Vertex object.

    public Graph(Edge[] edges) {
        graph = new HashMap<>(edges.length);

        for (Edge e : edges) {
            if (!graph.containsKey(e.a)) {
                graph.put(e.a, new Vertex(e.a));
            }
            if (!graph.containsKey(e.b)) {
                graph.put(e.b, new Vertex(e.b));
            }
        }

        for (Edge e : edges) {
            graph.get(e.a).neighbours.put(graph.get(e.b), e.dist);
        }
    }

    public void dijkstra(String startName) {
        System.out.println("Finding the shortest path starting at node " + startName + ".");
        if (!graph.containsKey(startName)) { //I'm liking these jdk-provided data structures.
            System.out.println("Graph doesn't contain start vertex " + startName + ".");
            return;
        }
        Vertex source = graph.get(startName); //Source is where we start.
        NavigableSet<Vertex> q = new TreeSet<>(); //TreeSet. I don't know how this works but it's really cool. It's, like, a list, but a tree.

        for (Vertex v : graph.values()) { // for v in graph.values()
            v.previous = (v == source) ? source : null; //v.previous gets source if v == source. If not, it gets null.
            v.dist = (v == source) ? 0 : Integer.MAX_VALUE; //v.dist is assigned 0 if v==source, otherwise it's infinity.
            q.add(v); //Add this vertex to the set.
        }

        Vertex u, v;
        while (!q.isEmpty()) {

            u = q.pollFirst();
            if (u.dist == Integer.MAX_VALUE) { //I really appreciate that the Integer class includes something that can easily be used to stand in for infinity.
                break; //That's not a neighbor- ignore it.
            }

            for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
                v = a.getKey();

                int updatedDistance = u.dist + a.getValue();
                if (updatedDistance < v.dist) {
                    q.remove(v);
                    v.dist = updatedDistance;
                    v.previous = u;
                    q.add(v);
                }
            }
        }
    }

    public void printThatPath(String endName) {
        System.out.println();
        System.out.println("Shortest path to vertex " + endName + ".");
        if (!graph.containsKey(endName)) {
            System.out.println("Graph doesn't contain end vertex " + endName + ".");
            return;
        }
        graph.get(endName).printPath();
        System.out.println();
    }

    public void printAllPaths() {
        System.out.println();
        System.out.println("Shortest paths to ALL vertices.");
        for (Vertex v : graph.values()) {
            v.printPath();
            System.out.println();
        }
    }

    public static class Edge { //Weird nested class thing. It seems to work pretty well.
        public String a, b; //The name of the edge.
        public int dist;

        public Edge(String a, String b, int dist) {
            this.a = a;
            this.b = b;
            this.dist = dist;
        }
    }

    public static class Vertex implements Comparable<Vertex> {
        public String name;
        public Map<Vertex, Integer> neighbours = new HashMap<>();
        public int dist = Integer.MAX_VALUE;
        public Vertex previous = null;

        public Vertex(String name) {
            //Vertex name literally is just how we're referring to each individual vertex. And this comment is only here to stop the IDE from collapsing this method.
            this.name = name;
        }

        private void printPath() {
            if (this == this.previous) {
                System.out.print(this.name);
            } else if (this.previous == null) {
                System.out.print(this.name + " is unreachable.");
            } else {
                this.previous.printPath();
                System.out.print(" -> " + this.name + ":[" + this.dist + "]");
            }
        }

        public int compareTo(Vertex b) {
            return Integer.compare(dist, b.dist); //I was going to do this wild "if this then that but if not this" thing but one line of code seemed so much nicer.
        }
    }
}

