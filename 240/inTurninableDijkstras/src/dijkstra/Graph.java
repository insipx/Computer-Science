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

    public void insert(Node newNode, int[] nodeDists){
        size++;
        int newNodeI= size-1;
        Node[] tmp = new Node[size];

        for(int i = 0; i<size;i++) tmp[i] = new Node();

        int[][] newDists = new int[size][nodeDists.length];

        graph = nodeCopyArr(graph, tmp);
        distances = copyArr(distances, newDists);

        distances[newNodeI] = nodeDists;
        graph[newNodeI] = newNode;
        graph[newNodeI].distance = 0;
        graph[newNodeI].label = newNodeI;
    }

    public void delete(Node delNode){
        size--;
        Node[] tmp = new Node[size];

        for(int i = 0; i<size; i++) tmp[i] = new Node();
        int[][] newDists = new int[size][];

        //make a new array without delNode
        for(int i = 0; i <delNode.label; i++){
            tmp[i] = graph[i];
        }
        int j = delNode.label + 1;
        for(int i = delNode.label; i < size; i++){
            tmp[i] = graph[j];
            j++;
        }
        //this should remove all references
        removeRefs(delNode);


    }

    public void dumpGraph(){
        for(int i = 0; i < size; i++){
            System.out.println( " | " + "LABEL: " + graph[i].label + " DISTANCE: "+ graph[i].distance + " | ");
        }
    }
    private Node[] nodeCopyArr(Node[] arr1, Node[] arr2){
        for(int i = 0; i< arr1.length; i++) {
            arr2[i] = arr1[i];
        }
        return arr2;
    }
    private int[][] copyArr(int[][] arr1, int[][] arr2){
        for(int i = 0; i<arr1.length;i++){
            for(int j = 0; j<arr1[i].length; j++){
                arr2[i][j] = arr1[i][j];
            }
        }
        return arr2;
    }
    //djisktras!
    public void findDjikstrasPath(){

        initToInfinity();
        setNodesToUnfinished();
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
    //end of djisktras main function and associated subprograms

//    removes all the  references to a certain node. should only be called if node is
    //certainly being deleted

    //there is definitely room for improvement in this method
    //it's like I took a knife to a wooden log
   private void removeRefs(Node n){

       //iterate through every reference, looking if any of them is the node we need to remove
       for(int i = 0; i < size; i++){
           for(int j = 0; j < graph[i].refs.length; j++){
                //if we find that node
               if(graph[i].refs[j] == n) {
                   //make a new node list
                   Node[] tmp = new Node[graph[i].refs.length - 1];
                   //everything before the node is OK
                   int k;
                   for(k = 0; k < j; k++){
                       tmp[k] = graph[i].refs[k];
                   }
                   int cont = k;
                   //everything after needs to GOOOO
                   if( graph[i].refs[j+1] != null){
                       for(int x = j+1; x < graph[i].refs.length; x++){
                           tmp[cont] = graph[i].refs[x];
                           x++;
                       }
                   }

                   graph[i].refs = tmp;
               }
               break;
           }
       }
   }

    private void removeDists(int )
}
