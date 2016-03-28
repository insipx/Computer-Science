package dijkstra;

import java.util.Scanner;

/**
 * Created by insidious on 3/13/16.
 */

public class DijkGraphInput {
    Scanner keyboard;
    //this is what describes a graph
    Node[] adjList;
    int[][] distList;
    Graph graph;

    DijkGraphInput(){
        keyboard = new Scanner(System.in);
    }

    public Graph getGraph() {
        boolean exit = false;
        System.out.println("Welcome to Grapher (but mostly just Dijkstras Shortest Path Finder)");
        System.out.println("Lets start off by creating the Graph");

        createGraph();

        System.out.println("What do you want to do with the Graph?");
        System.out.println("a to add a node to the graph, d to delete, s to show the graph, fd to find Dijkstras Shortest Path and :q to quit \n" +
                "c is needed as the first option in order to initialize the graph");
        System.out.print("Enter Input: ");

        while (exit != true) {

            String option = keyboard.nextLine();
            option = option.toLowerCase();

            //ADD
            if (option.equals("a")) {
                int[] newDists;
                Node tmp = new Node();
                System.out.print("Enter data for new Node: ");
                String newNode = keyboard.nextLine();
                String[] newData = newNode.split("\\s+");
                newDists = new int[newData.length / 2];
                int j = 0;
                for(int i = 0; i < newData.length /2; i = i + 2){
                    newDists[j] = Integer.parseInt(newData[i]);
                    j++;
                }
                //set the refs for the new node
                tmp.refs = new Node[newData.length/2];
                j = 0;
                for(int i = 1; i < newData.length/2; i = i + 2){
                    tmp.refs[j] = graph.graph[Integer.valueOf(newData[i])];
                }
                if(startFromNode()){
                    startNode();
                }else{

                }
               graph.insert(tmp, newDists);


            //DELETE
            }else if(option.equals("d")){
                System.out.println("What Node do you want to delete?: ");
                String del = keyboard.nextLine();
                String[] delNode = del.split("\\s+");
                if(delNode.length > 1){
                    System.out.print("Enter only one node. Nothing deleted.");
                    endOfInput();
                }else{
                    graph.delete(graph.graph[Integer.parseInt(delNode[0])]);
                    endOfInput();
                }


            //SHOW GRAPH
            } else if (option.equals("s")) {
                graph.dumpGraph();
                endOfInput();


            //FIND DIJKSTRAS PATH
            } else if(option.equals("fd")){
                graph.findDjikstrasPath();
                System.out.println("Djikstras Shortest Path is Found. Press 's' to show the Graph");
                endOfInput();


            //EXIT
            } else if (option.equals(":q")) {
                System.out.print("CYA!");
                exit = true;

            //DEFAULT
            } else {
                System.out.println("Sorry, I didn't recognize what you typed. Try Again.");
            }
        }
        return graph;
    }
    private void endOfInput(){
        System.out.println("a to add a node to the graph, d to delete, s to show the graph, fd to show Dijkstras Path and :q to quit");
        System.out.print("Input: ");
    }
    private void createGraph(){

        int graphSize;
        System.out.print("Enter the number of nodes in the graph: ");
        graphSize = Integer.parseInt(keyboard.nextLine());
        //this.size = graphSize;

        adjList = new Node[graphSize];
        //initialize every node
        for(int i = 0; i < graphSize; i++) adjList[i] = new Node();

        //initialize distance list
        distList = new int[graphSize][];

        //set a label for each node
        for(int i = 0; i < graphSize; i ++){ adjList[i] = new Node(); adjList[i].label = i;}

        System.out.println("Enter the distance to a node, and then that node. Seperate each by a space.\n" +
                "EX: ( 30 2 12 3) 30 is the distance, 2 is the node. 30 is the distance from node 1 to node 2, etc");

        for (int i = 0; i < graphSize; i++) {

            System.out.print("Enter data for Node " + i + ": ");

            String data = keyboard.nextLine();

            //if the user doesn't enter anything, don't worry about it
            if(!data.equals("")) {
                String[] dataList = data.split("\\s+");
                int k = 0;

                //initialize the reference nodes
                adjList[i].refs = new Node[(dataList.length / 2)];
                for (int j = 0; j < adjList[i].refs.length; j++) adjList[i].refs[j] = new Node();
                //set the reference nodes
                for (int j = 1; j < dataList.length; j = j + 2) {
                    adjList[i].refs[k] = adjList[Integer.parseInt(dataList[j])];
                    k++;
                }
                //get the distances, too
                k = 0;
                distList[i] = new int[dataList.length / 2];
                for (int j = 0; j < dataList.length; j = j + 2) {
                    distList[i][k] = Integer.valueOf(dataList[j]);
                    k++;
                }
            }else{
                adjList[i].refs = new Node[0];
            }
        }
        graph = new Graph(adjList, distList, graphSize);
        //make em' enter the start node
        startNode();
        //end of input
        endOfInput();
    }
    private boolean startFromNode(){
        System.out.println("Did you want to start from a new node?");
        String output = keyboard.nextLine();
        output = output.toLowerCase();

        if(output.equals("y")){
            return true;
        }else if (output.equals("n")){
            return false;
        }else{
            System.out.println("Sorry, I didn't understand what you said");
            startFromNode();
        }
        return false;
    }
    private void startNode(){
        System.out.print("Which node is the start node?");
        String start = keyboard.nextLine();
        if( 0 < Integer.parseInt(start) && Integer.parseInt(start) < graph.size && isInteger(start)) {
            graph.graph[Integer.parseInt(start)].start = true;
        }else{
            System.out.println("Sorry, I did not understand what you typed.");
        }
    }

    private boolean isInteger(String str){
        for(int i = 0; i < str.length(); i++){
            if(!Character.isDigit(str.charAt(i))){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }
}
