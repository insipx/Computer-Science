package heap;

/**
 * Created by insidious on 3/6/16.
 */
public class Heap<T> implements HeapInterface<T> {
    final int ROOT = 1;
    int size;
    int maxSize;
    int[] heap;
    //heap pointer
    //int HP;
    public Heap(){
       this.size = 1;
       this.maxSize = 10;
       heap = new int[maxSize];
     //  HP = ROOT;
    }
    public void insert(int val){
        //root case
        if(this.size == 1){
            heap[ROOT] = val;
            this.size++;
        }else{
            if(this.size >= this.maxSize ){
                int increment = maxSize/4 * 2;
                int[] tmp = new int[maxSize + increment];
                copyArr(heap, tmp);
                heap = tmp;
            }
            else{
                heap[size] = val;
                int parent = findParent(size);
                if(heap[size] > heap[parent]) {
                    int curr = size;
                    swap(heap, curr, parent);
                    while(parent != ROOT){
                        curr = parent;
                        parent = findParent(parent);
                        if(heap[curr] > heap[parent]) {
                            swap(heap, curr, parent);
                        }
                    }
                }
                this.size++;
                //this.HP++;
            }
        }

    }
    public int extract_max(){
        int tmp = heap[ROOT];
        int newRoot = size;
        while(heap[newRoot] == 0){
            newRoot--;
        }
        swap(heap, 1, newRoot);
        heap[newRoot] = 0;
        //repair the heap
        repairHeap();

        return tmp;
    }
    public void dumpheap()
    {
        for(int i = 0; i < heap.length; i ++){
            System.out.print(heap[i] + " , ");
        }
    }
    //swap array value in index i with array value in index j
    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //System.copyarr works too but I like my methods
    private int[] copyArr(int[] arr1, int[] arr2){

        for (int i = 0; i < arr1.length; i ++){
            arr2[i] = arr1[i];
        }
        return arr2;
    }

    //a find parent method is useful, O(1);
    private int findParent(int parent){
        if(parent % 2 != 0){
            parent--;
            return parent/2;
        }else{
            return parent/2;
        }
    }
    //opposite of above method, O(1)
    private int findChild(int parent, boolean right){
        if (right) {
            return (parent*2) + 1;
        }else{
            return (parent*2);
        }
    }
    //look through the levels and make sure the parent nodes are always > than children
    private void repairHeap(){
        int level = 1;
        int lastParent = size;
        while (heap[lastParent] == 0){
           lastParent --;
        }
        lastParent = findParent(lastParent);
        while(level <= lastParent){
                                //leftchild                                 right child
            if(heap[level] < heap[findChild(level, false)] || heap[level] < heap[findChild(level, true)]){
                //set parent to the larger of the childs
                int newParent = (heap[findChild(level,false)] > heap[findChild(level, true)]) ? findChild(level, false) : findChild(level,true);
                //swap the newparent with level
                swap(heap, newParent, level);
            }
            level++;
        }

    }
}
