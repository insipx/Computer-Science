import java.lang.reflect.Array;
import java.util.*;
/**
 * Created by insidious on 5/5/16.
 */

//Objective: compress some String using Huffman Trees

public class HuffmanTree implements HuffmanTreeInterface{
    private ArrayList<Node> treeList;
    private Node<Integer> root;
    HashMap<Character,Integer> freqMap;
    private String str;
    public HuffmanTree(String str){
        this.str = str;
        treeList = new ArrayList<Node>();
        root = new Node<Integer>();
        initFrequency();
        initTree();

    }
    @Override
    public String getEncodedSymbol(char c) {
        //count because we don't want to count the root node into encoding
        int count = 0;
        Node temp = root;
        String result = "";
        while(temp.isParentNode){
            if(temp.rightRef.label.indexOf(c) >= 0){
                temp=temp.rightRef;
                result+="1";
            }else{
                temp = temp.leftRef;
                result+="0";
            }
            count++;
        }

        return result;
    }

    private void initTree(){

        ArrayList<Node> nodeList = new ArrayList<Node>();

        for(Map.Entry<Character, Integer> entry : freqMap.entrySet()){
            char c = entry.getKey();
            int freq = entry.getValue();
            Node temp = new Node(c, freq);
            nodeList.add(temp);
        }
        nodeList = getSortedList(nodeList);


        //create the tree
        while(nodeList.size() > 1){
            //combine the two lowest elements

            //first insertion
            if(root.leftRef == null && root.rightRef == null) {
                root.leftRef = nodeList.get(0);
                root.rightRef = nodeList.get(1);
                root.isParentNode = true;
                root.freq = nodeList.get(0).freq + nodeList.get(1).freq;
                root.label = nodeList.get(0).label + nodeList.get(1).label;
                nodeList.remove(0);
                nodeList.remove(0);
                nodeList.add(root);
                nodeList = getSortedList(nodeList);
            //root has already been inserted
            }else{
                Node temp = new Node();
                temp.leftRef = nodeList.get(0);
                temp.rightRef = nodeList.get(1);
                temp.freq = nodeList.get(0).freq + nodeList.get(1).freq;

                temp.label = nodeList.get(0).label + nodeList.get(1).label;
                temp.isParentNode = true;
                root = temp;
                nodeList.remove(0);
                nodeList.remove(0);
                nodeList.add(root);
                nodeList = getSortedList(nodeList);
                //may be a reference problem
            }
        }
    }

    //this method just converts between array and arraylist, calling on sortList and sortChars
    //for sort algorithms
    private ArrayList<Node> getSortedList(ArrayList<Node> list){
        Node[] nodeArr = new Node[list.size()];
        ArrayList<Node> temp = new ArrayList<Node>();
        for(int i = 0; i < list.size(); i++){
            nodeArr[i] = list.get(i);
        }
        sortList(nodeArr, 0, nodeArr.length -1);
        sortChars(nodeArr);
        for(int i = 0; i < nodeArr.length;i++){
            temp.add(nodeArr[i]);
        }

        return temp;
    }

    //recursive quiksort algorithm. Convert ArrayList to Array, then back to ArrayList
    private void sortList(Node[] arr, int low, int high){
        if(arr == null || arr.length == 0) return;
        if(low >= high) return;
        int middle = low + (high - low) / 2;
        int pivot = arr[middle].freq;

        int i = low;
        int j = high;
        while (i <= j){
            while(arr[i].freq < pivot){
                i++;
            }
            while(arr[j].freq > pivot){
                j--;
            }
            if(i<=j){
                Node temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                i++;
                j--;
            }
        }

        if(low < j) sortList(arr, low, j);

        if(high > i) sortList(arr, i, high);

    }

    //in order to favor lesser letters
    //just bubble sort this time
    //probably could have included this in quikSort, though
    //just a rudimentary bubble sort
    private void sortChars(Node[] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = i+1; j < arr.length;j++){
                if(arr[i].freq == arr[j].freq && arr[i].c > arr[j].c){
                    Node temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }

    }

    //puts the frequency of every character in a HashMap
    private void initFrequency(){

        freqMap = new HashMap<Character,Integer>();
        for(int i = 0; i < str.length(); i ++) {
            char c = str.charAt(i);
            Integer freq = freqMap.get(new Character(c));
            if(freq != null){
                freqMap.put(c, new Integer(freq+1));
            }else{
                freqMap.put(c,1);
            }
        }
    }
}
