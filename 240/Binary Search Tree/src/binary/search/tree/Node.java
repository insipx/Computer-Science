package binary.search.tree;

public class Node {
    Node parent;
    Node right;
    Node left;
    int value;
    
    public Node(){
        this.right = null;
        this.left = null;
        this.parent = null;
    }
}
