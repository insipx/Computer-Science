package hash;

public class LinkedList<T> implements LinkedList_Interface<T>{
	Node<T> current;
	Node<T> front;

	int size;
	public void LinkedList(){
		current = null;
		front = null;
		size = 0;
	}

	@Override
	public void insert(T val) {
		if(size == 0){
			Node<T> lNode = new Node<T>();
			lNode.value = val;
			lNode.ref = null;
			current = lNode;
			front = lNode;
		} else {
           	Node<T> lNode = new Node<T>();
            current.ref = lNode;
            current = lNode;
            current.value = val;
            size++;
        }
	}
	@Override
	public void delete(T val){
        Node tmp = new Node<T>();
        Node beforeT = new Node<T>();
		tmp = front;
		beforeT = tmp;
		while(tmp.value != val){
			beforeT = tmp;
			tmp = tmp.ref;
		}
		beforeT.ref = tmp.ref;
		tmp.ref = null;
		size--;

		//garbage collection now takes care of tmp because nothing is pointing to it
		//and nothin points to ref

	}
	@Override
	public void dumpList() {
		Node<T> tmp = new Node<T>();
		tmp = front;
		for(int i = 0; i<size;i++){
			System.out.print(" | " + tmp.value + " | ");
			tmp = tmp.ref;
		}
	}


	
	
}