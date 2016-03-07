/**
 * Sean Batzel 3/6/2016.
 */
public class HeapDriver {
    public static void main(String[] args) {
        Heap heap = new Heap();
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);
        heap.dumpHeap();
    }
}
