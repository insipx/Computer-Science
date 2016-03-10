package heap;

/**
 * Created by insidious on 3/6/16.
 */
public class Heap_Main {

    public static void main(String [ ] args) {
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
        heap.insert(21);
        heap.insert(32);
        //heap.dumpheap();
        heap.extract_max();
        heap.dumpheap();


    }
}
