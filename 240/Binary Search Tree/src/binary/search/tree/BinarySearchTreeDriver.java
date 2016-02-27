package binary.search.tree;

public class BinarySearchTreeDriver {
    
    public static void main(String args[]){
        BinarySearchTree t = new BinarySearchTree();
        t.insert(t.head, 20);
        t.insert(t.head, 10);
        t.insert(t.head, 30);
        t.insert(t.head, 5);
        t.insert(t.head, 7);
        t.insert(t.head, 21);
        t.dumptree(t.head);
        t.delete(t.head, 20);
        t.delete(t.head, 21);
        t.dumptree(t.head);
        t.delete(t.head, 35);
    }
}
