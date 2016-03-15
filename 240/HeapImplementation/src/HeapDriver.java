/**
 * Sean Batzel 3/6/2016.
 */
class HeapDriver {
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
        int[] sorted = new int[heap.size];
        //System.out.println("Heap size: " + heap.size);
        for (int i = 0; i < sorted.length; i++){
            sorted[i] = heap.extractMax();
            heap.dumpHeap();
        }
        System.out.println();
        System.out.println("Sorted heap:");
        for (int i = 0; i < sorted.length; i++){
            System.out.println(sorted[i]);
        }
    }
}
