/* Java program that prints a silly message ("Glorp!") 15 times.
**
** Author: R. McCloskey 
** Date: February 2017
** For CMPS 034L, Lab #2
*/
public class Glorp15 {

   /* Calls a method to print "Glorp!" fifteen times.
   */
   public static void main(String[] args) { glorp15(); }


   /* Prints "Glorp!" fifteen times.
   */
   private static void glorp15() {
      glorp3(); glorp3(); glorp3(); glorp3(); glorp3(); 
   }


   /* Prints "Glorp!" three times.
   */
   private static void glorp3() {
      System.out.println("Glorp!");
      System.out.println("Glorp!");
      System.out.println("Glorp!");
   }

}
