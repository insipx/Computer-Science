package hash;

public class Node<T> {
	//the value held in the node
	T value;
	//the pointer
	public Node ref;
		
	//constructor for node
	Node(){
		ref = null;
	}
}