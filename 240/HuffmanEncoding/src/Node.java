/**
 * Created by insidious on 5/5/16.
 */
public class Node<T> {
    int freq;
    boolean isParentNode = false;
    char c;
    String label;
    public Node<T> rightRef;
    public Node<T> leftRef;
    public Node(){
        this.freq = 0;
        //punctuation is not included, so i'm assuming this is safe
        this.c = '.';
    }
    public Node(char c, int freq){
        this.freq = freq;
        this.c = c;
        this.rightRef = null;
        this.leftRef = null;
        String s = new StringBuilder().append(c).toString();
        this.label = s;
    }
    public Node(char c, int freq, Node rightRef, Node leftRef){
        this.freq = freq;
        this.c = c;
        this.rightRef = rightRef;
        this.leftRef = leftRef;
    }
    public Node(char c, int freq, Node ref, boolean isLeft){
        this.freq = freq;
        this.c = c;
        if(isLeft) {
            this.leftRef = ref;
        }else{
            this.rightRef = ref;
        }
    }
    public int getFreq(){
        return freq;
    }
    public void setRightRef(Node node){
        this.rightRef = node;
    }
    public void setLeftRef(Node node){
        this.leftRef = node;
    }
    public void setFreq(int freq){
        this.freq = freq;
    }
    public void setChar (char c){
        this.c = c;
    }
}
