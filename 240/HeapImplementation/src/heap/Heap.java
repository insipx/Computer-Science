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
    int HP;
    public void Heap{

       this.size = 0;
       this.maxSize = 8;
        heap = new int[maxSize -1];
        HP = ROOT;
    }
    public void insert(int val){
        //first parent
        if(this.size == 0){
            heap[0] = val;
            this.size++;
        }else{
            if(this.size > this.maxSize ){
                int increment = maxSize/4 * 2;
                int[] tmp = new int[maxSize + increment];
                copyArr(heap, tmp);
                heap = tmp;
            }
            else{
                int tmpHP = HP;
                heap[tmpHP] = val;
                int parent = findParent(tmpHP);
                if(heap[tmpHP] > heap[parent]) {
                    exchange(heap, parent, tmpHP);
                }
                this.size++;
                this.HP++;
            }
        }

    }
    public T extract_max(){
        int max = heap[ROOT-1];
        return null;
    }
    public void dumpheap(){

    }
    //swap array value in index i with array value in index j
    private void swap(int[] arr, int i, int j){


    }
    private void exchange(int[] heap, int parent, int HP){
        while(parent != ROOT){
            if(heap[HP] > heap[parent]) {
                //swap HP with parent
                swap(heap, HP, parent);
            }
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
            parent=parent/2;
        }else{
            parent = parent/2;
        }
    return parent;
    }
    private int findChild(int parent, boolean right){
        if (right) {
            return (parent*2) + 1;
        }else{
            return (parent*2);
        }

    }
}
