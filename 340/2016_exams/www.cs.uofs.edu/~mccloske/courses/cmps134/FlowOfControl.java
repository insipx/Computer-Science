/* Java application that illustrates how flow-of-control (i.e., the order in
** which the statements in a program are executed) is influenced by calls to
** methods.
*/

public class FlowOfControl {

   public static void main(String[] args) {
      System.out.println("Starting main(); about to call method1().");
      method1();
      System.out.println("Inside main(); back from method1().");
      System.out.println("About to call method2().");
      method2();
      System.out.println("Inside main(); back from method2().");
      System.out.println("Ending main().");
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
