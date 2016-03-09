/**
 * Sean Batzel 3/6/2016.
 */
public class Heap implements HeapInterface {

    int[] heap;
    int max;
    int size;

    public Heap() {
        this.size = 0;
        this.max = 25;
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
        this.sortHeap();
    }

    public void sortHeap() {
        for(int x = 0; x < this.size; x++) {
            for(int y = 0; y < this.size; y++) {
		if (this.heap[y] > this.heap[this.findParent(y)]){
			int tmp = this.heap[y];
			this.heap[y] = this.heap[this.findParent(y)];
			this.heap[this.findParent(y)] = tmp;		
		}
            }
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
	this.sortHeap();
        return x;
    }

    public void dumpHeap() {
	tree(0, 0);
    }

    private void format(String ch, int n){
	for (int i = 0; i < n; i++){
	    System.out.print(ch);
	}
    }

    private void tree(int root, int level){
	tree(this.findRight(root), level+1);
	format("  ", level);
	System.out.println(this.heap[root]);
	tree(this.findLeft(root), level+1);
    }
}
