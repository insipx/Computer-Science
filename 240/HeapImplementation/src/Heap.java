import static java.lang.System.arraycopy;
import static java.lang.System.out;

/**
 * Sean Batzel
 */
class Heap {
    private final int root = 1;
    public int size;
    @SuppressWarnings("CanBeFinal")
    private
    int max;
    private int[] heap;

    public Heap() {
        this.size = 1;
        this.max = 8;
        heap = new int[max];
    }

    public void insert(int val) {
        if (this.size == 1) {
            heap[root] = val;
            this.size++;
        } else {
            if (this.size >= this.max) {
                int level = max * 2;
                int[] tmp = new int[max + level];
                arraycopy(heap, 0, tmp, 0, heap.length);
                heap = tmp;
                max = max + level;
                heap[size] = val;
                int parent = findParent(size);
                if (heap[size] > heap[parent]) {
                    int current = size;
                    swap(current, parent);
                    while (parent != root) {
                        current = parent;
                        parent = findParent(parent);
                        if (heap[current] > heap[parent]) {
                            swap(current, parent);
                        }
                    }
                }
                this.size++;
            } else {
                heap[size] = val;
                int parent = findParent(size);
                if (heap[size] > heap[parent]) {
                    int current = size;
                    swap(current, parent);
                    while (parent != root) {
                        current = parent;
                        parent = findParent(parent);
                        if (heap[current] > heap[parent]) {
                            swap(current, parent);
                        }
                    }
                }
                this.size++;
            }
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public int extractMax() {
        if (heap[root] == 0) {
            out.println("Empty heap.");
            return 0;
        } else {
            int tmp = heap[root];
            int newRoot = size - 1;
            swap(1, newRoot);
            heap[newRoot] = 0;
            size--;
            sortHeap();
            return tmp;
        }
    }

    private int nextSize(int levelSize) {
        return levelSize * 2;
    }

    public void dumpHeap() {
        int levelSize, count;
        //noinspection unchecked
        Queue<Integer> queue = new Queue();
        levelSize = 1;
        count = 0;
        while (count <= size) {
            if (levelSize == 1) {
                queue.enqueue(heap[levelSize]);
            } else {
                int j = count + 1;
                for (int i = 0; i < levelSize; i++) {
                    queue.enqueue(heap[j]);
                    j++;
                }
            }
            count += levelSize;
            levelSize = nextSize(levelSize);
            while (queue.size != 0) {
                Integer tmp = queue.dequeue();
                out.print(tmp + " ");
            }
            out.println();
        }
        out.println();
    }

    private void swap(int y, int z) {
        int tmp = this.heap[y];
        this.heap[y] = this.heap[z];
        this.heap[z] = tmp;
    }

    private int findParent(int parent) {
        return parent / 2;
    }

    private int findRightChild(int parent) {
        parent = (parent * 2) + 1;
        if (parent > size) {
            return 0;
        } else {
            return parent;
        }
    }

    private int findLeftChild(int parent) {
        parent = (parent * 2);
        if (parent > size) {
            return 0;
        } else {
            return parent;
        }
    }

    private void sortHeap() {
        int level = 1;
        int lastParent = size - 1;
        lastParent = findParent(lastParent);
        while (level <= lastParent) {
            if (heap[level] < heap[findLeftChild(level)] || heap[level] < heap[findRightChild(level)]) {
                int newParent = (heap[findLeftChild(level)] > heap[findRightChild(level)]) ? findLeftChild(level) : findRightChild(level);
                swap(newParent, level);
            }
            level++;
        }
    }
}
