/* Java program that prints a cumulative "countdown" of the five face cards
** in a deck of playing cards. 
** As seen in Test #1, CMPS 134 Spring 2017 (Section 1)
** It is similar to sample solution provided for Prog. Assg. #1.
*/

public class CumulativeFaceCardCountDown {

   public static void main(String[] args) {
      joker(); jack(); queen(); king(); ace();
   }

   public static void ace() { System.out.print("Ace, "); king(); }

   public static void king() { System.out.print("King, "); queen(); }

   public static void queen() { System.out.print("Queen, "); jack(); }

   public static void jack() { System.out.print("Jack, "); joker(); }

   public static void joker() { System.out.println("Joker"); }

}

