package binary.search.tree;

public class BinarySearchTree implements BinaryTreeInterface {

    TreeNode head;

    public BinarySearchTree() {
        head = new TreeNode();
        head.value = 0;
    }

    @Override
    public void insert(TreeNode node, int value) {
        if (node.value == 0) {
            node.value = value;
        } else if (node.value == value) {
            System.out.println(value + " is already in this tree.");
        } else if (node.value < value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                node.right = new TreeNode();
                node.right.value = value;
            }
        } else {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new TreeNode();
                node.left.value = value;
            }
        }
    }

    @Override
    public void delete(TreeNode node, int value) {
        if (head.value == value) {
            TreeNode tmp1 = head.right;
            while (tmp1.left != null) {
                tmp1 = tmp1.left;
            }
            tmp1.left = head.left;
            head = node.right;
            node.right = null;
            node.left = null;
        } else if ((node.right != null && node.right.value == value) || (node.left != null && node.left.value == value)) {
            TreeNode tmp2;
            if (node.right != null && node.right.value == value) {
                tmp2 = node.right;
            } else {
                tmp2 = node.left;
            }
            if (tmp2.left == null && tmp2.right == null) {
                if (node.value > value) {
                    node.left = null;
                } else {
                    node.right = null;
                }
            } else {
                if (tmp2.right != null) {
                    TreeNode tmp1 = tmp2.right;
                    while (tmp1.left != null) {
                        tmp1 = tmp1.left;
                    }
                    node.left = tmp1;
                    if (node.value > value) {
                        node.left = tmp2.right;
                    } else {
                        node.right = tmp2.right;
                    }
                } else {
                    node.left = tmp2.left;
                }
            }

        } else if (node.value < value && node.right != null) {
            delete(node.right, value);
        } else if (node.value > value && node.left != null) {
            delete(node.left, value);
        } else {
            System.out.println("Delete failed: " + value + " is not in this tree.");
        }
    }

    @Override
    public void dumptree(TreeNode node) {
        //The internet says it might be my best bet is to use a queue,
        //so I just dug up the queue I wrote earlier in the semester.
        //This just prints by levels.
        if (node == null) return;
        Queue<TreeNode> currentLevel = new Queue<>();
        Queue<TreeNode> nextLevel = new Queue<>();
        currentLevel.enqueue(node);
        while (currentLevel.size != 0) {
            TreeNode currNode = currentLevel.dequeue();
            if (currNode != null) {
                System.out.print(currNode.value + " ");
                nextLevel.enqueue(currNode.left);
                nextLevel.enqueue(currNode.right);
            }
            if (currentLevel.size == 0) {
                System.out.println();
                currentLevel = nextLevel;
                nextLevel = new Queue<>();
            }
        }
    }
}
