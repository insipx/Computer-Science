/* Java class containing several static methods that carry out (possibly useful)
** operations on Strings.  Also included (at bottom) is a main() method that,
** with the help of some "helper" methods, serves to test the methods of 
** interest.
*/

public class StringUtilitiesF16 {



   /* Returns the number of occurrences of the given character (ch)
   ** within the given String (s).  For example, there are four
   ** occurrences of 's' in "Mississippi".
   */
   public static int charCount(String s, char ch)
   {
      int counter = 0;
      for (int i=0; i != s.length(); i=i+1) {
         if (s.charAt(i) == ch)
            { counter = counter + 1; }
      }
      return counter;
   }

   /* Returns the String obtained by removing every occurrence of the
   ** given character (ch) from the given String (s).  For example,
   ** removing each 's' from "Mississippi" yields "Miiippi"
   */
   public static String removeChar(String s, char ch)
   {
      String result = "";
      for (int i=0; i != s.length(); i=i+1) {
         if (s.charAt(i) == ch)
            { }
         else
            { result = result + s.charAt(i); }
      }
      return result;
   }

   /* Returns the String obtained by replacing, within the given String (s),
   ** every occurrence of one given character (old) by the other (young).
   ** For example, replacing each 's' in "Mississippi" by 'g' yields 
   ** "Miggiggippi".
   */
   public static String replaceChar(String s, char oldChar, char newChar)
   {
      String result = "";
      for (int i=0; i != s.length(); i=i+1) {
         if (s.charAt(i) == oldChar)
            { result = result + newChar; }
         else
            { result = result + s.charAt(i); }
      }
      return result;
   }

   /* Returns the String obtained by replacing each occurrence of a lower case
   ** letter within the given String (s) by the corresponding upper case
   ** letter.
   */
   public static String toUpperCase(String s)
   {
      String result = "";
      for (int i=0; i != s.length(); i=i+1) {
         if (isLowerCaseLetter(s.charAt(i)))
            { result = result + toUpperCaseLetter(s.charAt(i)); }
         else
            { result = result + s.charAt(i); }
      }
      return result;
   }

   /* Reports whether or not the given character (ch) is a lower case letter.
   ** Exploited is the fact that the set of characters in the range 'a'..'z'
   ** includes precisely the 26 lower case letters.
   */
   public static boolean isLowerCaseLetter(char ch) {
      return 'a' <= ch  &&  ch <= 'z';
   }

   /* Returns the upper case letter that corresponds to the given 
   ** character (let), which is assumed to be a lower case letter.
   ** Exploited is the fact that the sets of characters in the ranges
   ** 'a'..'z' and 'A'..'Z', respectively, are precisely the 26 lower case
   ** and upper case letters, respectively.
   */
   public static char toUpperCaseLetter(char let) {
      return (char)('A' + (let - 'a'));
   }


   ///////////////////////////////////////////////////////////////
   // Here begin the methods that are for the purpose of testing the
   // methods of interest, which are above.

   public static void main(String[] args)
   {
      charCountTest("Mississippi", 's');
      charCountTest("Mississippi", 'p');
      charCountTest("Mississippi", 'A');

      removeCharTest("Mississippi", 's');
      removeCharTest("Mississippi", 'p');
      removeCharTest("Mississippi", 'A');

      replaceCharTest("Mississippi", 's', 'g');
      replaceCharTest("Mississippi", 'p', 'g');
      replaceCharTest("Mississippi", 'A', 'g');

      toUpperCaseTest("Mississippi");
      toUpperCaseTest("AaBbCcDdEeFfGf!@#$%^&*()_+0123456789");
   }

   // For testing the charCount() method.
   private static void charCountTest(String str, char c) {
      System.out.println("# of occurrences of " + c + " in |" + str + 
                         "| is " + charCount(str, c));
   }

   // For testing the removeChar() method.
   private static void removeCharTest(String str, char c) {
      System.out.println("Removing occurrences of " + c + " from |" + str + 
                         "| yields |" + removeChar(str, c) + "|");
   }

   // For testing the replaceChar() method.
   private static void replaceCharTest(String str, char x, char y) {
      System.out.println("Replacing occurrences of " + x + 
                         " in |" + str + "| by " + y + " yields |" + 
                         replaceChar(str, x, y) + "|");
   }

   // For testing the toUpperCase() method.
   private static void toUpperCaseTest(String str) {
      System.out.println("Converting |" + str + "| to upper case yields |" +
                         toUpperCase(str) + "|");
   }

}

