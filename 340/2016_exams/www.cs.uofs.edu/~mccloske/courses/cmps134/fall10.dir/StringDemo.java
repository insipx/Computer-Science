import java.util.Scanner;

/** Java program whose purpose is simply to demonstrate several of the
**  string manipulation capabilities found in the class java.lang.String.
**
**  Repeatedly, the program prompts the user to enter a string and, in
**  response to each one, displays several pieces of information about
**  the string (e.g., its length, its first and last characters, the first
**  and last halves of the string, etc.)
**  The program terminates in response to the user entering the empty string.
**
**  Author: R. McCloskey
**  Last modified: Oct. 4, 2010
*/
public class StringDemo {

   private static Scanner input = new Scanner(System.in);

   public static void main(String[] args) {

      String str = getInputString();

      while (str.length() != 0) {

         System.out.println("length: " + str.length());
         System.out.println("first character: " + str.charAt(0));
         System.out.println("last character: " + str.charAt(str.length()-1));
         System.out.println("in all upper case: " + str.toUpperCase());
         System.out.println("first half: " + str.substring(0, str.length()/2));
         System.out.println("last half: " + str.substring(str.length()/2));
         System.out.println("reverse: " + reverse(str));
         System.out.println("halfway rotation: " + rotateLeft(str, str.length()/2));
         System.out.println("# of occurrences of spaces: " + charCount(str, ' '));
         System.out.println();
         str = getInputString();
      }
      
      
   }

   /** Prompts user to enter a string, accepts the response, and returns it
   */
   private static String getInputString() {
      System.out.print("Enter a string (empty string to quit):");
      return input.nextLine();
   }

   /** Returns the reverse of the specified string.
   */
   private static String reverse(String s) {

      String result = "";
      for (int i=0; i < s.length(); i = i+1) {
         result = s.charAt(i) + result;
      }
      return result;
   }

   /** Returns the String obtained by rotating the specified String (s) 
   **  the specified number of places (k) to the left, which corresponds
   **  to the concatenation of the suffix of s beginning at position k
   **  with the prefix of s ending just before position k.
   */
   private static String rotateLeft(String s, int k)
   {
      return s.substring(k) + s.substring(0,k);
   }


   /** Returns the # of occurrences of the specified character within the 
   **  specified String.  A 'for' loop is employed, even though a more appropriate
   **  choice would be a 'while' loop.
   */
   private static int charCount(String s, char ch) {

      int result = 0;
      for (int k = s.indexOf(ch); k != -1; k = s.indexOf(ch,k+1)) {
         result = result + 1;
      }
      return result;
   }


   /** Does same thing as method above --returns the # of occurrences of the 
   **  specified character within the specified String-- but this one uses, 
   **  more appropriately, a 'while' loop rather than a 'for' loop.
   */
   private static int charCountWhile(String s, char ch) {

      int result = 0;
      int k = s.indexOf(ch,0);
      while (k != -1) {
         result = result + 1;
         k = s.indexOf(ch,k+1);
      }
      return result;
   }

   /** Yet another method that returns the # of occurrences of the specified
   **  character within the specified String.  This one uses an if-else statement
   **  in conjuction with a 'for' loop.
   */
   private static int charCountAlt(String s, char ch) {

      int result = 0;
      for (int i=0; i != s.length(); i = i+1)
      {
         if (s.charAt(i) == ch)       // if the i-th character of s
         {                            // matches ch, increment the counter
            result = result + 1;
         }
      }
      return result;
   }

}
