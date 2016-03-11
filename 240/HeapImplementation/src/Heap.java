/**
 * Created by insidious on 3/6/16.
 */
public class Heap implements HeapInterface {
    final int ROOT = 1;
    int size;
    int maxSize;
    int[] heap;
    public Heap(){
       this.size = 1;
       this.maxSize = 8;
       heap = new int[maxSize];
    }
    public void insert(int val){
        //root case
        if(this.size == 1){
            heap[ROOT] = val;
            this.size++;
        }else{
            if(this.size >= this.maxSize ){
                //multiplying by two ensures a full (no null array out of bounds) next level of the heap
                int increment = maxSize * 2;
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
    public int extractMax(){

        if(heap[ROOT] == 0){
            return 0;
        }else{
            int tmp = heap[ROOT];
            int newRoot = size - 1;
            swap(heap, 1, newRoot);
            heap[newRoot] = 0;
            //repair the heap
            size--;
            sortHeap();
            return tmp;
        }

    }
    private int getNextLvlSize(int currLvlSize){
        return currLvlSize * 2;
    }
    private int getPrevLvlSize(int currLvlSize){
        return currLvlSize /2 ;
    }
    public void dumpHeap()
    {

        int currLvlSize, count;
        Queue<Integer> current = new Queue();
        currLvlSize = 1;
        count = 0;
        while(count <= size) {

            //enqueue all the values
            if(currLvlSize == 1){
                current.enqueue(heap[currLvlSize]);
            }else{
                int j = count + 1;
                for(int i = 0; i < currLvlSize; i++){
                    current.enqueue(heap[j]);
                    j++;
                }
            }

            count += currLvlSize;


           //get ready for the next queue
            currLvlSize = getNextLvlSize(currLvlSize);

            int treeCount = current.size;
            while(current.size != 0){
                Integer tmp = current.dequeue();
                System.out.print(" " + tmp + " ");
            }
            System.out.println();
            for(int i = 0; i < treeCount; i ++){
                System.out.print(" / \\ ");
            }
            System.out.println();
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
        //just the operations for findParent from class
        if(parent % 2 != 0) return ((parent-1)/2);
        else return parent/2;
    }
    //opposite of above method, O(1)
    private int findChild(int parent, boolean right){
        if (right) parent = (parent*2) + 1;
        else parent = (parent*2);
        if(parent > size) return 0;
        else{return parent;}
    }
    //look through the levels and make sure the parent nodes are always > than children
    public void sortHeap(){
        int level = 1;
        int lastParent = size-1;
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
