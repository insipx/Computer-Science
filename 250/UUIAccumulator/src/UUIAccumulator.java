/* CMPS 250 - Spring 2016
   Assignment 01 - Unlimited Unsigned Integers
  
   This is the main program that makes use of the UUI class.
 	 
 	 This is a solution for Assignment 1 in CMPS 250 for Spring 2016
    
     I, Andrew Plaza, am the developer of this work.
 	 Worked Alone
 	 
 	 Not yet aware of any flaws, maybe uses too much memory?
 	 the terminate function may also be a little redundant, but I like
 	 the ability of adding up as many UUI's as my heart desires
 	 
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
         //Value is the value the user types in
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
