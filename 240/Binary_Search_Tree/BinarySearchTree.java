public class BinarySearchTree implements BinarySearchTreeInterface {

	int size;
	Node head;

	public BinarySearchTree() {
		head = new Node();
	}

	public void insert(Node node, int element) {
		if (node.value == null) {
			node.value = element;
		} else if (node.value == element) {
			pass("Element is already on the tree.");
		} else if (node.value > element) {
			if (node.right == null) {
				node.right = new Node();
				node.right.parent = node;
				node.right.value = element;
				this.size++;
			} else {
				insert(node.right, element);
			}
		} else if (node.value < element) {
			if (node.left == null) {
				node.left = new Node();
				node.left.parent = node;
				node.left.value = element;
				this.size++;
			} else {
				insert(node.left, element);
			}
		}
	}

	public void delete(Node node, int element) {
		if (node.value > element && node.left != null) {
			delete(node.left, element);
		} else if (node.value < element && node.right != null) {
			delete(node.right, element);
		} else if (node.right == null && node.left == null) {
			if (node.parent.value < element) {
				node.parent.right = null;
				this.size--;
			} else if (node.parent.value > element) {
				node.parent.left = null;
				this.size--;
			}

			else {
				if (node.left != null) {
					Node tmp = node.left;
					while (tmp.right != null) {
						tmp = tmp.right;
					}
					tmp.right = node.right;
					node.parent.left = node.left;
				}
				if (node.left == null) {
					node.parent.right = node.right;
				}
			}
		} else {
			pass("Element does not exist.");
		}
	}

	public void dumptree(Node node) {
		dumptree(node.left);
		System.out.println(node);
		dumptree(node.right);
	}

	public void pass(String msg) {
		System.out.println(msg);
	}
}
