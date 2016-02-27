package binary.search.tree;

public class BinarySearchTree implements BinaryTreeInterface{

    Node head;
    
    public BinarySearchTree(){
        head = new Node();
    }
    
    @Override
    public void insert(Node node, int value){
        if (node.value == value){
            pass("Element already exists.");
        }
        else if (node.value < value){
            if (node.right != null){
                insert(node.right, value);
            }
            else{
                node.right = new Node();
                node.right.value = value;
            }
        }
        else{
            if (node.left != null){
                insert(node.left, value);
            }
            else{
                node.left = new Node();
                node.left.value = value;
            }
        }
    }
    
    @Override
    public void delete(Node node, int value){
        if (node.value > value && node.left != null){
            delete(node.left, value);
        }
        else if(node.value < value && node.right != null){
            delete(node.right, value);
        }
        else{
            if (node.right == null && node.left == null){
                if (node == node.parent.right){
                    node.parent.right = null;
                }
                else if (node == node.parent.left){
                    node.parent.left = null;
                }
            }
            else{
                if (node.left != null){
                    Node tmp = node.left;
                    while (tmp.right != null){
                        tmp = tmp.right;
                    }
                    tmp.right = node.right;
                    node.parent.left = node.left;
                }
                else if (node.left == null){
                    node.parent.right = node.right;
                }
                else{
                    pass("Element not found.");
                }
            }
        }
    }
 
    @Override
    public void pass(String msg){
        System.out.println(msg);
    }

    @Override
    public void dumptree(Node node){
        
    }
}
