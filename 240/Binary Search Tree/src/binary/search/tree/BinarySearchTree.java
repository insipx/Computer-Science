package binary.search.tree;

public class BinarySearchTree implements BinaryTreeInterface {

    Node head;

    public BinarySearchTree() {
        head = new Node();
        head.value = 0;
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
            } else { //else it's gotta be null
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
    public void delete(Node parent, int value) {
        if(head.value == value){ // because we dealing with head
            Node tmp = head.right;
            while(tmp.left != null){
                tmp = tmp.left;
            }
            tmp.left = head.left;
            head = parent.right;
            parent.right = null;
            parent.left = null;
        }
        else if ((parent.right != null && parent.right.value == value)
                || //OR
            (parent.left != null && parent.left.value == value)) {
            Node node;
            if (parent.right != null && parent.right.value == value) node = parent.right; else node = parent.left;

            if(node.left == null && node.right == null ){//its a leaf node
               if (parent.value > value){
                   parent.left = null;
               }else{
                  parent.right = null;
               }
            }else{
                if(node.right != null){
                    Node tmp  = node.right;
                    while(tmp.left != null){
                        tmp = tmp.left;
                    }
                    tmp.left = node.left;
                    parent.left = node.right;

                }else{
                    parent.left = node.left;
                }
            }

        } else if (parent.value < value && parent.right != null) {
            delete(parent.right, value);
        } else if (parent.value > value && parent.left != null) {
            delete(parent.left, value);
        } else {
            pass("Specified value does not exist - " + value);
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
    public void passln(String msg) {
        System.out.println(msg);
    }
    public void pass(String msg){
        System.out.print(msg);
    }

    @Override
    public void dumptree(Node node) {


        passln("===");
        pass("Root Level: ");
        passln(Integer.toString(node.value));
        dumptreer(node);
        passln("");
        passln("====");
    }
    private void dumptreer(Node node){
        
        dumptreer(node.left);
        dumptreer(node.right);


    }

}
