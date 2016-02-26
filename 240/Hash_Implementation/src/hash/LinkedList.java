package hash;

public class LinkedList<T> implements LinkedList_Interface<T>{
	
	Node<T> current;
	Node<T> front;
	int size;
	public void LinkedList(){
		current = null;
		size = 0;
	}
	@Override
	public void insert(T val) {
		if(size <= 0){
			Node<T> lNode = new Node<T>();
			lNode.value = val;
			front = lNode;
			current = lNode;
			lNode.ref = null;
			size++;
		} else {
			Node<T> lNode = new Node<T>();
			lNode.value = val;
			current.ref = lNode;
			current = lNode;
			lNode.ref = null;
			size++;
		}
	}
	@Override
	public void delete(T val){
		Node<T> tmp = new Node<T>();
		Node<T> behindTmp = new Node<T>();
		while(tmp.value != val){
			tmp = front;
			behindTmp = tmp;
			tmp = tmp.ref;
		}
		behindTmp.ref = tmp.ref;
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
			System.out.print(tmp.value);
			tmp = tmp.ref;
		}
	}


	
	
}