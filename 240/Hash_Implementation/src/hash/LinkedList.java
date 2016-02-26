package hash;

public class LinkedList<T> implements LinkedList_Interface<T>{
    Node<T> firstVal;
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
			firstVal = new Node<T>();
			firstVal.value = val;
            current = firstVal;
            front = firstVal;
            current.ref = null;
			size++;
		} else {
            if(size == 1){
                Node<T> lNode = new Node<T>();
                current.ref = lNode;
                lNode.ref = null;
                current = lNode;
                current.value = val;
                size++;
            }
            Node<T> lNode = new Node<T>();
            current.ref = lNode;
            lNode.ref = null;
            current = lNode;
            current.value = val;
            size++;
        }
	}
	@Override
	public void delete(T val){
        Node tmp = new Node<T>();
        Node beforeT = new Node<T>();
        beforeT = front;
        tmp = front;
            while(tmp.value != val){
                beforeT = tmp;
                tmp = tmp.ref;
            }
        beforeT.ref = tmp.ref;

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