package heap;

/**
 * Created by insidious on 3/10/2016.
 */
public interface QueueInterface<T>{
    public void enqueue(T val);
    public T dequeue();
    public void dumpqueue();

}
