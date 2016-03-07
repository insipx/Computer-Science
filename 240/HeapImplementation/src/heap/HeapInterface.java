package heap;

/**
 * Created by insidious on 3/6/16.
 */
public interface HeapInterface<T> {

    public T insert(T val);
    public T extract_max();
    public void dumpheap();

}
