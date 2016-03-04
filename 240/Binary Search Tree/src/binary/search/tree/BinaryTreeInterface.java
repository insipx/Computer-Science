package binary.search.tree;

public interface BinaryTreeInterface {
    void insert(Node node, int value);
    void delete(Node node, int value);
    void dumptree(Node node);
    void pass(String msg);
    void passln(String msg);
}
