/**
 * Created by insidious on 5/5/16.
 */
public class HuffmanMain {
    public static void main(String[] args) {
        HuffmanCompression hcomp = new HuffmanCompression();
        System.out.println(hcomp.compress("just as fire tempers iron into fine steel so does adversity temper ones character into firmness tolerance and determination"));
        System.out.println();
        System.out.println("----------------------------------------------------");
        System.out.println("The Next String");
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println(hcomp.compress("someone is sitting in the shade today because someone planted a tree a long time ago"));

    }
}