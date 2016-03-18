package dijkstra;

/**
 * Created by insidious on 3/12/16.
 */
public class Graph<T> {
    int size;
    Node[] graph;
    int[][] distances;
    // """ Infinity """
    private final int INFINITY = 2147483647;

    Graph(){
        size = 0;
        graph = null;
        distances = null;
        graph = null;
        distances = null;
    }

    Graph(Node[] graph, int[][] distances, int size){
        this.graph = graph;
        this.distances = distances;
        this.size = size;
    }

    public void dumpGraph(){
        for(int i = 0; i < size; i++){
            System.out.print( " | " + "LABEL: " + graph[i].label + " DISTANCE: "+ graph[i].distance + " | ");
        }
        System.out.println();
    }

    //djisktras!
    public void findDjikstrasPath(){

        initToInfinity();
        //setNodesToUnfinished();
        int start = 0;
        //find the start node
        for(int i =0; i < size; i++) if (graph[i].start) start = i;

        //lets just hope start is initialized
        graph[start].distance = 0;
        Node minDistance = graph[start];
        while(checkNodes(graph)){
           //get minimum distance node
            minDistance = getMinDistNode(minDistance);
            //for each arc from node
            if(minDistance.refs.length == 0){
                minDistance.finished = true;
            }else {
                for (int i = 0; i < minDistance.refs.length; i++) {
                    //reeellaaaaxx
                    relax(i, minDistance, minDistance.refs[i]);
                }
            }

        }

    }
    private boolean checkNodes(Node[] graph){
        int count = 0;
        for(int i = 0; i <graph.length;i++){
            if(graph[i].finished){
                count++;
            }
        }
        if(count == graph.length){
            return false;
        }else{
            return true;
        }
    }
    private void initToInfinity(){
        for(int i = 0; i < size; i++){
            graph[i].distance = INFINITY;
        }
    }
    private Node getMinDistNode(Node minDistance){

        if(minDistance.finished){
            for(int j = 0; j < size; j++){
                if(!graph[j].finished){
                    minDistance = graph[j];
                    break;
                }
            }
        }
        for(int i = 0; i < size; i++){
            if (minDistance.distance > graph[i].distance && !graph[i].finished) {
                minDistance = graph[i];
            }
        }
        return minDistance;
    }
    private void relax(int w, Node u, Node v){
        if(v.distance > (u.distance + distances[u.label][w])){
            v.distance = u.distance + distances[u.label][w];
            u.finished = true;
        }else{
            u.finished = true;
        }
    }
    private void setNodesToUnfinished(){
        for(int i = 0; i < size; i++){
            graph[i].finished = false;
        }
    }
    //end of djisktras main function and associated functions
}
