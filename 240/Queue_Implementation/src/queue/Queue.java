package queue;

public class Queue<T> implements QueueInterface<T> {
	Node<T> firstVal;
	//the front of the queue
    Node<T> front;
    //the end of the queue
    Node<T> end;
    //size of the node
    int size;
	
    public void Queue(){
		//when queue is initialized there is nothing the first node will be pointing to
    	front = null;
    	//when queue initialized there is nothing the end will be pointing to
    	end = null;
		//size will be 0
    	size = 0;
	}

	@Override
	public void enqueue(T val) {
		if(size == 0){
			//temp node
			firstVal = new Node<T>();
			firstVal.value = val;
			front = firstVal;
			end = firstVal;
			end.ref = null;
			size++;
		}else{
			Node<T> qNode = new Node<T>();
			end.ref = qNode;
			qNode.ref = null;
			end = qNode;
			end.value = val;
			size++;
		}
	}

	@Override
	public T dequeue() {
		if(size <= 0){
			return null;
		}else{
			//the temp node
			Node<T> qNode = new Node<T>();
			qNode = front;
			front = front.ref;
			return qNode.value;

		}
	}

	@Override
	public void dumpqueue() {
		//create two temporary nodes, and start them off at the current node. 
		//we don't want dumpstack to change what's in the stack
		Node<T> temp = new Node<T>();
		Node<T> tempCurrent = new Node<T>();
		tempCurrent = front;
		//iterate through the stack, printing every element O(n)
		for(int i = 0; i < size; i++){
			temp = tempCurrent;
			System.out.println(temp.value);
			temp = temp.ref;
			tempCurrent = tempCurrent.ref;
		}

		
	}
}
