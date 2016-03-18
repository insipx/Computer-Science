package dijkstra;

/**
 * Created by insidious on 3/13/16.
 */
public interface Graph_Interface<T> {
    public void insert(T Node);
    public void delete(T Node);
    public void dumpGraph();
    public void findDjikstrasPath();
}
