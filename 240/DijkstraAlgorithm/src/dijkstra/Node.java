package dijkstra;

/**
 * Created by insidious on 3/12/16.
 */
public class Node {
    int distance;
    int label;
    Node[] refs;
    int[][] distances;
    boolean start;
    //FINISH HIM *raaawwrr*
    boolean finished;


    Node(){
        finished = false;
    }

}
