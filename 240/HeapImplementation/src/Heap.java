/**
 * Sean Batzel 3/6/2016.
 */
public class Heap implements HeapInterface {

    int[] heap;
    int max;
    int size;

    public Heap() {
        this.size = 0;
        this.max = 5;
        this.heap = new int[this.max];
    }

    public void insert(int x) {
        this.size++;
        if (this.size > this.max) {
            this.max++;
            int[] tmp = new int[max];
            for (int i = 0; i < max; i++) {
                tmp[i] = this.heap[i];
            }
            this.heap = tmp;
        }
        this.heap[this.size - 1] = x;
        //this.sortHeap();
    }

    public void sortHeap() {
        int x = 0;
        int y = 0;
        while (x < this.size) {
            while (y < this.size) {

                y++;
            }
            x++;
        }
    }

    public int findParent(int i) {
        return (i / 2);
    }

    public int findLeft(int i) {
        return (i * 2);
    }

    public int findRight(int i) {
        return ((2 * i) + 1);
    }

    public int extractMax() {
        int x = this.heap[0];
        return x;
    }

    public void dumpHeap() {
        System.out.println("Dumpin' the heap.");
        int counter = 1;
        int x = 0;
        for (int i = 0; i < this.size; i++) {
            while (x <= counter) {
                if (x >= size) break;
                System.out.print(this.heap[x] + " ");
                x++;
                if (x == counter) break;
            }
            counter *= 2;
            System.out.println();
        }
    }
}
