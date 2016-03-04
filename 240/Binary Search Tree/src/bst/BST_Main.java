package bst;

public class BST_Main {
    
    public static void main(String args[]){
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(tree.head, 20);
        tree.insert(tree.head, 10);
        tree.insert(tree.head, 30);
        tree.insert(tree.head, 5);
        tree.insert(tree.head, 7);
        tree.insert(tree.head, 21);
        tree.dumptree();
        tree.delete(tree.head, 20);
        tree.delete(tree.head, 21);
        tree.dumptree();
        tree.delete(tree.head, 35);
    }
}
