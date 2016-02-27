public class BinarySearchTree implements BinarySearchTreeInterface{
	public void insert(Node node, int element){
		if (node.value == element){
			pass("Element is already on the tree.");
		}
		if (node.value > element){
			if (node.right == null){
				node.right = new Node();
				node.right.value = element;
			}
			else{
				insert(node.right, element);
			}
		}
		if (node.value < element){
			if (node.left == null){
				node.left = new Node();
				node.left.value = element;
			}
			else{
				insert(node.left, element);
			}
		}
	}

	public void delete(Node node, int element){
	}

	public void dumptree(Node node, int element){
	}

	public void pass(String msg){
		System.out.println(msg);
	}
}
