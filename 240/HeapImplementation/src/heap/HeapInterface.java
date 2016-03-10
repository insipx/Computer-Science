package heap;

/**
 * Created by insidious on 3/6/16.
 */
public interface HeapInterface<T> {

    public void insert(int val);
    public int extract_max();
    public void dumpheap();

}
