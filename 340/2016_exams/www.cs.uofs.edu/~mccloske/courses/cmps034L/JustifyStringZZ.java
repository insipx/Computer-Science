/* JustifyString.java
** Java class containing methods for performing left, right, and center 
** justification of Strings.
**
** For CMPS 034L, Lab #6, Spring 2017
** Students: ......
** Known Flaws: ...
*/
public class JustifyString {

   /* This main() method is included strictly for the purpose of testing
   ** the other methods in this class.
   */
   public static void main(String[] args) {
      System.out.println('|' + leftJustify("abc",7) + '|');
      System.out.println('|' + leftJustify("abc",10) + '|');
      System.out.println('|' + leftJustify("abc",2) + '|');
      System.out.println('|' + rightJustify("abc",7) + '|');
      System.out.println('|' + rightJustify("abc",10) + '|');
      System.out.println('|' + rightJustify("abc",2) + '|');
      System.out.println('|' + centerJustify("abc",7) + '|');
      System.out.println('|' + centerJustify("abc",6) + '|');
      System.out.println('|' + centerJustify("abc",9) + '|');
      System.out.println('|' + centerJustify("abc",10) + '|');
      System.out.println('|' + centerJustify("abc",2) + '|');
   }


   /* Returns a String identical to the given one (s), but with as many 
   ** spaces "added" to the end so as to reach the specified length (len).
   ** If the length of s is greater than len, just return s.
   ** Example 1: leftJustify("abc", 7) yields "abc    ".
   ** Example 2: leftJustify("abc", 2) yields "abc".
   */
   public static String leftJustify(String s, int len) {

      return "";  // STUB!

   }

   /* Returns a String identical to the given one (s), but with as many
   ** spaces "added" at the beginning so as to reach the specified length
   ** (len).  If the length of s is greater than len, just return s.
   ** Example 1: rightJustify("abc", 7) yields "    abc".
   ** Example 2: rightJustify("abc", 2) yields "abc".
   */
   public static String rightJustify(String s, int len) {

      return "";  // STUB!

   }

   /* Returns a String identical to the given one (s), but with as many
   ** spaces "added" at the beginning and end so that the characters of s
   ** are centered in a String of the specified length (len).  If the 
   ** length of s is greater than len, just return s. 
   ** If the number of spaces needing to be added is odd, put the
   ** "extra" one at the end.
   ** Example 1: centerJustify("abc", 7) yields "  abc  ".
   ** Example 1: centerJustify("abc", 6) yields " abc  ".
   ** Example 3: centerJustify("abc", 2) yields "abc".
   */
   public static String centerJustify(String s, int len) {

      return "";  // STUB!

   }

}
