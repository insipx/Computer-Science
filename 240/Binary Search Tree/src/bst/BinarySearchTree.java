package bst;

public class BinarySearchTree<T> implements BST_Interface<T> {

    BSTNode<T> head;

    public BinarySearchTree() {
        head = new BSTNode<T>();
        head.value = 0;
    }

    @Override
    public void insert(BSTNode<T> node, int value) {
        //the root case
        //we set the root value to 0 in the constructor
        if (node.value == 0) {
            node.value = value;
        //if the value already exists
        } else if (node.value == value) {
            System.out.println("This element already exists in the tree");

         //put elements greater than root on the right
        } else if (node.value < value) {
            //keep recursively iterating until we hit a place in the tree which is free, (not null)
            //put the element in that place
            if (node.right != null) {
                insert(node.right, value);
            } else { //else it's gotta be null
                node.right = new BSTNode<T>();
                node.right.value = value;
            }

          //elements less than root go on the left, same methodology as above
        } else {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new BSTNode();
                node.left.value = value;
            }
        }
    }

    @Override
    public void delete(BSTNode parentNode, int value) {
        // if we are deleting the root node

        if(head.value == value)
        {
            //we have to change the head in the BST Object, otherwise
            //everything gets screwed up
            BSTNode tmp = head.right;
            //find the least element of the right node (greater node)
            while(tmp.left != null){
                tmp = tmp.left;
            }
            //attach the least element of the right side to the left side
            tmp.left = head.left;
            //make the right node the new root
            head = parentNode.right;
            parentNode.right = null;
            parentNode.left = null;
        }
        //if the right node is not null and equals the value or vice versa for left node
        //we stop recursion one node before our value so that we can play with parent, too
        else if ((parentNode.right != null && parentNode.right.value == value)
                || //OR
            (parentNode.left != null && parentNode.left.value == value))
        {
            //set node to correct node
            BSTNode node;
            if (parentNode.right != null && parentNode.right.value == value) node = parentNode.right; else node = parentNode.left;

            //it's a leaf node, easy case, we just kill the parent references
            if(node.left == null && node.right == null ){
               if (parentNode.value > value){
                   parentNode.left = null;
               }else{
                  parentNode.right = null;
               }
            }else{
                //if the right element is not null, we have to tack that on to the tree
                if(node.right != null){
                    BSTNode tmp  = node.right;
                    while(tmp.left != null){
                        tmp = tmp.left;
                    }
                    //attach the least element of the right node to the element at the left of the node we are deleting,
                    // preserving the right side of our tree
                    tmp.left = node.left;
                    parentNode.left = node.right;

                }else{
                    //if there is no right element, easy case, just set the parent node ref to point to the left element
                    // of node we are deleting
                    parentNode.left = node.left;
                }
            }
            //recursive function which walks through the tree
        } else if (parentNode.value < value && parentNode.right != null) {
            delete(parentNode.right, value);
        } else if (parentNode.value > value && parentNode.left != null) {
            delete(parentNode.left, value);
        } else {
            //a warning if the element does not exist
            System.out.println();
            System.out.println("*WARNING ↓ Value Does not exist*");
            System.out.println("        " + value);
            System.out.println("*WARNING ↑ Value Does not exist*");
        }
    }

    @Override
    public void dumptree() {
        //#programming in freenode irc chat suggested Queues was one way of easily printing the BST
        //after exhaustively playing with a recursive function that included 16 if cases for null, and not even printing right, I agree
        //You could probably modify this to print out nicely in a tree format, but I ran out of time
        //used my own Queue implementation, yay for generics!
        System.out.println();
        System.out.println("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/ Commence DumpTree  /\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/ ");

        //Two Queues which are going to hold the current level and next level
        //of the tree
        Queue<BSTNode> current = new Queue<>();
        Queue<BSTNode> next = new Queue<>();
        BSTNode node = head;
        //enqueue the root node which will be the first lvl, to start
        current.enqueue(node);
        //a count so we can printout what level we are on
        int count = 0;
        System.out.print("Level: " + count + "  = ");

        //while the current level size still has stuff to print out, keep dequeueing and printing on that level
        while(current.size != 0){
            BSTNode currentNode = current.dequeue();
            if(currentNode != null) {
                System.out.print(currentNode.value + ",");
                next.enqueue(currentNode.left);
                next.enqueue(currentNode.right);
            }
            //if the size reaches 0 print a new line for the next level and queue the next level
            if(current.size == 0){
                System.out.println();
                current = next;
                next = new Queue<>();
                count++;
                System.out.print("Level: " + count + " = ");
            }
        }
           System.out.println();
           System.out.println("/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/ End DumpTree  /\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/\\/ ");
           System.out.println();
    }
}
