/**
 * Created by insidious on 5/5/16.
 */
public class HuffmanMain {
    public static void main(String[] args) {
        HuffmanCompression hcomp = new HuffmanCompression();
        String str1 = "just as fire tempers iron into fine steel so does adversity temper ones character into firmness tolerance and determination";
        String str2 = "someone is sitting in the shade today because someone planted a tree a long time ago";
        String compStr1 = hcomp.compress(str1);
        String compStr2 = hcomp.compress(str2);
        System.out.println();
        System.out.println();
        System.out.println(compStr1);
        System.out.println("The Size Is: " + hcomp.getSize(compStr1));
        System.out.println();
        System.out.println("----------------------------------------------------");
        System.out.println("The Next String");
        System.out.println("----------------------------------------------------");
        System.out.println();
        System.out.println(compStr2);
        System.out.println("The Size Is: " + hcomp.getSize(compStr2));

    }
}