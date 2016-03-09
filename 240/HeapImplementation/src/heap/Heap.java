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
    public T extract_max(){
        return null;
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
    private void exchange(int[] heap, int parent, int curr){
        while(parent != ROOT){
            if(heap[curr] > heap[parent]) {
                //swap HP with parent
                swap(heap, curr, parent);
            }
            curr = parent;
            parent = findParent(parent);
        }
    }
    private int[] copyArr(int[] arr1, int[] arr2){
        for(int i = 0; i < arr1.length; i ++){
            arr2[i] = arr1[i];
        }
        return arr2;
    }
    private int findParent(int parent){
        if(parent % 2 != 0){
            parent--;
            return parent/2;
        }else{
            return parent/2;
        }
    }
    private int findChild(int parent, boolean right){
        if (right) {
            return (parent*2) + 1;
        }else{
            return (parent*2);
        }

    }
}
