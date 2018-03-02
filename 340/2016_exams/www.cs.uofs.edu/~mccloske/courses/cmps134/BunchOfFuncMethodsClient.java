/* Java class that acts as a "client" of the BunchOfFuncMethods class.
** That is, in this class there appear calls to methods of that class.
** The point is to show that a method call from within a class A to a
** static method in a distinct class B requires that the full name of the
** method be provided (meaning that "B." is needed as a prefix).
*/

public class BunchOfFuncMethodsClient {

   public static void main(String[] args)
   {
      System.out.println("Distance between 3.4 and -5.8 is " + 
                         BunchOfFuncMethods.distanceOneDim(3.4, -5.8));

      System.out.println("Gross pay for 45.5 hours worked at $12.54/hour is " +
                         BunchOfFuncMethods.grossPay(45.5, 12.54));

      System.out.println("Sum of integers 5..14 is " + 
                         BunchOfFuncMethods.rangeSum(5, 14));
   }

}
