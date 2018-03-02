/* Java class containing methods that are sample solutions to the problems
** for which solutions were to be submitted in Lab #7, CMPS 034L, Spring 2017.
*/

public class Lab7_Solutions {

   // zeroDigits
   // ----------

   /* Returns the number of 0's appearing in (the decimal numeral representing)
   ** the given number.
   ** This version uses a while-loop, necessitating the use of an if-else
   ** statement to separately handle the special case n == 0.
   */
   public static int zeroDigits1(int n) {
      int zeroCntr;
      if (n == 0) {
         zeroCntr = 1; 
      }
      else {
         zeroCntr = 0;
         while (n != 0) {
            if (n % 10 == 0) 
               { zeroCntr++; }
            n = n / 10;
         }
      }
      return zeroCntr;
   }

   /* Returns the number of 0's appearing in (the decimal numeral representing)
   ** the given number.
   ** This version uses a do-while-loop, making it unnecessary to treat the
   ** n == 0 case separately.
   */
   public static int zeroDigits2(int n) {
      int zeroCntr = 0;
      do {
         if (n % 10 == 0) 
            { zeroCntr++; }
         n = n / 10;
      }
      while (n != 0);
      return zeroCntr;
   }


   // isVowel
   // -------

   /* Returns true if the given String has length one and its lone character
   ** is a vowel, in either upper or lower case.  Returns false otherwise.
   ** The approach is to test s for having length one and, if it passes,
   ** to compare its lone character against each of the ten vowels using
   ** a 10-term disjunction.
   */
   public static boolean isVowel1(String s) {
      boolean isVowel;
      if (s.length() == 1) {
         char ch = s.charAt(0);
         isVowel = ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u' ||
                   ch=='A' || ch=='E' || ch=='I' || ch=='O' || ch=='U';
      }
      else {  // s is not of length one
         isVowel = false;
      }
      return isVowel;
   }

   /* Returns true if the given String has length one and its lone character
   ** is a vowel, in either upper or lower case.  Returns false otherwise.
   ** The approach is to test s for having length one and, if it passes,
   ** to take its lone character, find the lower case version thereof,
   ** and compare it against each of the five lower case vowels using
   ** a 5-term disjunction.
   */
   public static boolean isVowel2(String s) {
      boolean isVowel;
      if (s.length() == 1) {
         char ch = Character.toLowerCase(s.charAt(0));
         isVowel = ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
      }
      else {  // s is not of length one
         isVowel = false;
      }
      return isVowel;
   }

   /* Returns true if the given String has length one and its lone character
   ** is a vowel, in either upper or lower case.  Returns false otherwise.
   ** The approach is to test s for having length one and, if it passes,
   ** to search for its lone character in the String containing all vowels
   ** (lower and upper case).  
   */
   public static boolean isVowel3(String s) {
      boolean isVowel;
      if (s.length() == 1) {
         isVowel = "AEIOUaeiou".indexOf(s.charAt(0)) != -1;
      }
      else {  // s is not of length one
         isVowel = false;
      }
      return isVowel;
   }

}
