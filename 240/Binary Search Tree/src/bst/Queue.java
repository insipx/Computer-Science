package bst;

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
			//temp node just for the first value
			firstVal = new Node<T>();
			//store the first value the user gives us
			firstVal.value = val;
			//since it's the first, it gets to be the front and end
			front = firstVal;
			end = firstVal;
			//but the end will point to nothing
			end.ref = null;
			//and we have to keep track of the size
			size++;
		}else{
			//make another temp node
			Node<T> qNode = new Node<T>();
			//the end will point to this
			end.ref = qNode;
			//our temp node will now point to nothing
			qNode.ref = null;
			//it becomes the end
			end = qNode;
			//make sure to get the val and increment size
			end.value = val;
			size++;
		}
	}

	@Override
	public T dequeue() {
		if(size > 0 && front != null){
			Node<T> qNode = new Node<T>();
			//store the current front in a temp
			qNode = front;
			//make the next node our new front, the old one will get taken care of by garbage collectiong
			front = front.ref;
			//don't forget to decrement
			size--;
			return qNode.value;
		}else{
			//if there is nothing left
			System.out.println("nothing left :'(");
			return null;
		}
	}

	@Override
	public void dumpqueue() {
		//create a temporary node, and start them off at the front 
		//we don't want dumpstack to change what's in the stack
		Node<T> temp = new Node<T>();
		temp = front;
		//iterate through the queue, printing every element O(n)
		System.out.println("---------- DumpQueue------------");
		for(int i = 0; i < size; i++){
			
			System.out.println(temp.value);
			temp = temp.ref;

		}
		System.out.println("---------- DumpQueue------------");

	}
}
