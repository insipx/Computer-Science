/**
 * Sean Batzel 3/6/2016.
 */
public class HeapDriver {
    public static void main(String[] args) {
        Heap heap = new Heap();
	heap.insert(10);
	heap.insert(2);
	heap.insert(8);
	heap.insert(4);
	heap.insert(18);
	heap.insert(20);
	heap.insert(3);
	heap.insert(16);
	heap.insert(5);
        heap.dumpHeap();
	heap.extractMax();
	heap.dumpHeap();
	heap.extractMax();
	heap.dumpHeap();
	heap.extractMax();
	heap.dumpHeap();
	heap.extractMax();
	heap.dumpHeap();
	heap.extractMax();
	heap.dumpHeap();
	heap.extractMax();
	heap.dumpHeap();    
	}
}
