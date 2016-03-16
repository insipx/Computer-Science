/**
 * Created by insidious on 3/13/16.
 */
/* Java application that illustrates how flow-of-control (i.e., the order in
** which the statements in a program are executed) is influenced by calls to
** methods.
*/

public class FlowOfControl {

    public static void main(String[] args) {

        int height = 10;
        int val = 0;

        for(int i = 1; i <= height; i++ ){
            val = val + 1
            System.out.println(val * height);
        }



    }

    private static void method1() {
        System.out.println("Inside method1().");
    }

    private static void method2() {
        System.out.println("Inside method2(); about to call method1().");
        method1();
        System.out.println("Inside method2(); back from method1().");
        System.out.println("About to call method1() a second time!.");
        method1();
        System.out.println("Inside method2(); back from method1() again.");
    }
}
