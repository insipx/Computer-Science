package binary.search.tree;

public class BinarySearchTree implements BinaryTreeInterface {

    Node head;

    public BinarySearchTree() {
        head = new Node();
    }

    @Override
    public void insert(Node node, int value) {
        if (node.value == 0) {
            node.value = value;
        } else if (node.value == value) {
            pass("Element already exists.");
        } else if (node.value < value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                node.right = new Node();
                node.right.value = value;
            }
        } else {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new Node();
                node.left.value = value;
            }
        }
    }

    @Override
    public void delete(Node node, int value) {
        if (node.right == null && node.left == null && node.value == value) {
            if (node.value > value) {
                node.parent.left = node.left;
            } else if (node.value < value) {
                node.parent.right = node.right;
            }
        } else if (node.right != null && node.left == null && node.value == value) {
            node.parent.right = node.right;
        } else if (node.right == null && node.left != null && node.value == value) {
            node.parent.left = node.left;
        } else if (node.right != null && node.left != null && node.value == value) {
            node.parent.left = node.left;
            node.parent.right = node.right;
        } else if (node.value > value && node.left != null) {
            delete(node.left, value);
        } else if (node.value < value && node.right != null) {
            delete(node.right, value);
        }
    }

    public Node minValue(Node node) {
        if (node.left == null) {
            return node.left;
        } else {
            return minValue(node.left);
        }
    }

    @Override
    public void pass(String msg) {
        System.out.println(msg);
    }

    @Override
    public void dumptree(Node node) {
        if (node == null) {
            return;
        }
        dumptree(node.left);
        pass(Integer.toString(node.value));
        dumptree(node.right);
    }
}
