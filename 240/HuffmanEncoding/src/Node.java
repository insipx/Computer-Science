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

}
