/* CMPS 250 - Spring 2016
   Assignment 01 - Unlimited Unsigned Integers
  
   This is the main program that makes use of the UUI class.
    
   P.M.J.
*/
import java.util.*;
public class UUIAccumulator {
   static Scanner keyboard = new Scanner(System.in);

   public static void main(String args[]) {
      final UUI SENTINEL = new UUI("0");
      UUI value;
      UUI accumulator = new UUI("0");
      System.out.println("SENTINEL is "+SENTINEL.toString());
      System.out.println("Accumulator is "+accumulator.toString());
      do {
         System.out.print("Enter an UUI:");
         value = new UUI(keyboard.nextLine());
         //System.out.println("value is "+value.toString());
         if(value.NE(SENTINEL)) {
            accumulator.add(value);
            System.out.println("Current accumulation is "+accumulator.toString());
            System.out.println("Which is equivalent to  "+accumulator.toStringf());
         }
      } while(value.NE(SENTINEL));
      System.out.println("Final accumulation is   "+accumulator.toString());
      System.out.println("Which is equivalent to  "+accumulator.toStringf());
   }
}
