/**
 * Created by insidious on 3/14/16.
 */




public class Heap<T> {
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

        if(this.size == 1){
            heap[ROOT] = val;
            this.size++;
        }else{
            if(this.size >= this.maxSize){
                int increment = maxSize * 2;
                int[] tmp = new int[maxSize + increment];
                copyArr(heap, tmp);
                heap = tmp;
            }
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
        }
    }
    public int extract_max(){

        if(heap[ROOT] == 0){
            return 0;
        }else{
            int tmp = heap[ROOT];
            int newRoot = size - 1;
            swap(heap, 1, newRoot);
            heap[newRoot] = 0;
            size--;
            repairHeap();
            return tmp;
        }

    }
    private int getNextLvlSize(int currLvlSize){
        return currLvlSize * 2;
    }
    private void makeSpaces(int spaceCount){
        for(int i =0; i < spaceCount; i ++){
            System.out.print(" ");
        }
    }
    public void dumpheap()
    {
        System.out.println("><><><><><><><><><><><><><><>Commence Heap Dump ><><><><><><><><><><><><><><>");

        int currLvlSize, count;
        int spaceCount = 32;
        Queue<Integer> current = new Queue<Integer>();
        currLvlSize = 1;
        count = 0;
        while(count <= size)
        {
            if(currLvlSize == 1){
                current.Enqueue(heap[currLvlSize]);
            }else{
                int j = count + 1;
                for(int i = 0; i < currLvlSize; i++){
                    current.Enqueue(heap[j]);
                    j++;
                }
            }

            count += currLvlSize;

            currLvlSize = getNextLvlSize(currLvlSize);

            int dashCount = 0;
            makeSpaces(spaceCount);
            while(current.size != 0){
                Integer tmp = current.Dequeue();
                if(tmp != 0) {
                    if(current.size % 2 != 0){
                        System.out.print( tmp + "  ");
                    }else{
                        System.out.print( tmp + " ");
                    }
                    dashCount ++;
                }else{
                }
            }
            System.out.println();
            makeSpaces(spaceCount);
            for(int i = 0; i < dashCount; i ++){
                if(dashCount % 2 != 0){
                    System.out.print("/\\ ");
                }else {
                    System.out.print("/\\  ");
                }
            }
            spaceCount = spaceCount - 2;
            System.out.println();
        }
        System.out.println("><><><><><><><><><><><><><><>End Heap Dump ><><><><><><><><><><><><><><>");
    }
    private void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    private int[] copyArr(int[] arr1, int[] arr2){
        for (int i = 0; i < arr1.length; i ++){
            arr2[i] = arr1[i];
        }
        return arr2;
    }
    private int findParent(int parent){
        if(parent % 2 != 0) return ((parent-1)/2);
        else return parent/2;
    }
    private int findChild(int parent, boolean right){
        if (right) parent = (parent*2) + 1;
        else parent = (parent*2);
        if(parent > size) return 0;
        else{return parent;}
    }
    private void repairHeap(){
        int level = 1;
        int lastParent = size-1;
        lastParent = findParent(lastParent);
        while(level <= lastParent){
            if(heap[level] < heap[findChild(level, false)] || heap[level] < heap[findChild(level, true)]){
                int newParent = (heap[findChild(level,false)] > heap[findChild(level, true)]) ? findChild(level, false) : findChild(level,true);
                swap(heap, newParent, level);
            }
            level++;
        }

    }
    public static void main(String args[])
    {
        Heap<Integer> newHeap = new Heap<Integer>();

        newHeap.insert(10);
        newHeap.insert(2);
        newHeap.insert(8);
        newHeap.insert(4);
        newHeap.insert(18);
        newHeap.insert(20);
        newHeap.insert(3);
        newHeap.insert(16);
        newHeap.insert(5);
        newHeap.dumpheap();
    }
}







public class Queue<Z>
{
    Node<Z> head = null, tail = null;
    public int size;

    static class Node<Z>
    {
        Z value;
        Node<Z> next;
        public Node<Z> ref;

        Node(Z value, Node<Z> next)
        {
            this.value= value;
            this.next= next;
        }

    }

    public Queue()
    {

    }

    public void Enqueue(Z value)
    {
        Node<Z> newNode= new Node<Z>(value, null);

        if(head == null)
        {
            head= newNode;
        }

        else
        {
            tail.next = newNode;
        }

        tail= newNode;
    }

    public Z Dequeue()
    {
        if(head == null)
        {
            return null;
        }

        Z retVal= head.value;
        head= head.next;
        return retVal;
    }

    public void dumpqueue()
    {
        System.out.println("Queue Order: ");
        Node<Z> tmp = head;
        while (tmp != null)
        {
            System.out.println(tmp.value);
            tmp = tmp.next;
        }
    }


}



