
public class Stack_main {

	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(2);
		stack.push(1);
		stack.dumpstack();
		stack.push(3);
		stack.push(10);
		stack.pop();
		stack.push(15);
		stack.dumpstack();
		stack.pop();
		stack.pop();
		stack.dumpstack();
		stack.pop();
		stack.pop();
		stack.pop();
		stack.dumpstack();
		
	}

}
