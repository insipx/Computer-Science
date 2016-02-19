package queue;

public class Queue_Main {

	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.enqueue(2);
		queue.enqueue(1);
		queue.dumpqueue();
		queue.enqueue(3);
		queue.enqueue(10);
		queue.dequeue();
		queue.enqueue(15);
		queue.dumpqueue();
		queue.dequeue();
		queue.dequeue();
		queue.dumpqueue();
		queue.dequeue();
		queue.dequeue();
		queue.dequeue();
		queue.dumpqueue();
	}

}

