package queue;

public interface QueueInterface<T>{
	public void enqueue(T val);
	public T dequeue();
	public void dumpqueue();
	
}
