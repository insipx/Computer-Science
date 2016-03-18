// Sean Batzel

// We're going to drive the program by taking input from stdin to form an adjacency matrix. If the input is 0, there's no connection. Otherwise, the input is the distance.

class GraphDriver {
    static int nodes;
    static int[][] adjmat;
    static int[] nodeList;
    static boolean[] done;
    static Scanner scan;
    static int[] finalPath;

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        System.out.println("Djikstra's Algorithm");

        System.out.print("Number of Nodes: ");
        nodes = scan.nextInt();

        adjmat = new int[nodes][nodes];

        System.out.println("As each vertex is prompted, type the distance from the first node to the next node. 0 distance indicates that the two vertices aren't connected.");

        for (int i = 0; i < nodes; i++) {
            for (int j = 0; j < nodes; j++) {
                if (i == j) {
                    adjmat[i][j] = 0;
                } else {
                    System.out.print("From " + i + " to " + j + ": ");
                    adjmat[i][j] = scan.nextInt();
                }
            }
        }

        nodeList = new int[nodes];
        nodeList[0] = 0;
        for (int i = 1; i < nodes; i++) {
            nodeList[i] = 99999;
        }

        int start = 0;
        relax(start);
    }

    private static void relax(int vertex) {

        int closestNeighbor = 99999;
        done = new boolean[node];
        boolean finished = true;

        //Find vertex's closest neighbor.
        for (int i = 0; i < nodes; i +) {
            for (int j = 0; j < nodes; j++) {
                if (adjMat[start][j] < closestNeighbor) {
                    closestNeighbor = adjMat[start][j];
                }
            }
        }

        //Assign that vertex's nodeList value to vertex's plus the distance.
        nodeList[closestNeighbor] = nodeList[vertex] + adjMat[vertex][closestNeighbor];

        System.out.print(vertex + "[" + nodeList[vertex] + "]" + "-" + adjMat[vertex][closestNeighbor] + "->");

        //Add the current vertex to the 'done' list.
        done[vertex] = true;

        //If all nodes aren't done,
        for (int i = 0; i < nodes; i++) {
            if (!done[i]) {
                finished = false;
            }
        }
        if (!finished) {
            //Relax that vertex.
            relax(closestNeighbor);
        }
        //Otherwise, we can just return to the main subroutine.
        System.out.println()
    }
}