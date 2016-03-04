package bst;

public interface BST_Interface<T> {
    void insert(BSTNode<T> node, int value);
    void delete(BSTNode<T> node, int value);
    void dumptree();
}
