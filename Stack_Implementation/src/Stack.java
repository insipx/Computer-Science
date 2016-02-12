
public class Stack<T> implements StackInterface<T> {
	//the current node
    Node<T> current;
    //size of the node
    int size;
	
    public void Stack(){
		//when stack is initialized there is nothing the first node will be pointing to
    	current = null;
		//size will be 0
    	size = 0;
	}
    //push method
	public void push (T val){
		//the new node to hold the value
		Node<T> pushNode = new Node<T>();
		//store the value in the node
		pushNode.value=val;
		//connect the node to the rest of the list 
		pushNode.ref = current;
		//the new node is now the current one, or "head" of the list
		current = pushNode;
		//the stack increases by one
		size++;
	}
	public T pop(){
		if(size > 0){
			//make a temporary value to store the value of the node that is being popped
			T temp;
			temp = current.value;
			//remove the popped value from the stack, garbage collection should take care of this.
			current = current.ref;
			//this decreases the size of the stack by one
			size--;
			return temp;
		
	    //if there are no items in the stack to print out,
		}else{
			System.out.println("0 items in stack");
			return null;
		}
	}
	public void dumpstack(){
		//create two temporary nodes, and start them off at the current node. 
		//we don't want dumpstack to change what's in the stack
		Node<T> temp = new Node<T>();
		Node<T> tempCurrent = new Node<T>();
		tempCurrent = current;
		//iterate through the stack, printing every element O(n)
		for(int i = 0; i< size; i++){
			temp = tempCurrent;
			System.out.println(temp.value);
			temp = temp.ref;
			tempCurrent = tempCurrent.ref;
		}
	}
}
