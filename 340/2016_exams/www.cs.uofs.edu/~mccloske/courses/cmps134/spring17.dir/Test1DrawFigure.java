/* Java program that includes a method that can draw figures of this form:
**
**    ********            ************
**    \      /            \          /
**    \\    //            \\        //
**    \\\  ///            \\\      ///
**    \\\\////            \\\\    ////
**                        \\\\\  /////
**   (size 4)             \\\\\\//////
**                          (size 6)
**
** It is a solution to Problem 6 of CMPS 134, Test #1 Spring 2017 (Section 1).
*/
public class Test1DrawFigure {

   public static void main(String[] args) {
      drawFig(4); System.out.println(); drawFig(7);
      System.out.println();
      drawFig(4,'+','(','-',')'); System.out.println(); 
      drawFig(7,'&','#','A','@');
   }

   /* Prints the specified character (ch) the specified number (k) of times.
   */
   private static void printChars(char ch, int k)
      { for (int i=1; i <=k; i++) { System.out.print(ch); } }


   /* Draws a figure of the kind illustrated above and having the specified
   ** size.
   */
   private static void drawFig(int size) {

      printChars('*', 2*size);  // print 2*size asterisks
      System.out.println();     // advance to next line

      for (int i = 1; i <= size; i = i+1) {

         printChars('\\', i);            // print i backslashes
         printChars(' ', 2*(size - i));  // print 2(size-i) spaces
         printChars('/', i);             // print i slashes
         System.out.println();           // advance to next line
      }
   }


   /* Draws a figure of the kind illustrated above and having the specified
   ** size.  This version of the method has parameters by which the caller
   ** can specify which four characters are used in the drawing.  The first
   ** version uses asterisk, backslash, space, and slash characters.
   */
   private static void drawFig(int size, char ch1, char ch2, char ch3, char ch4)
   {
      printChars(ch1, 2*size);  // print 2*size ch1's
      System.out.println();     // advance to next line

      for (int i = 1; i <= size; i = i+1) {

         printChars(ch2, i);            // print i ch2's
         printChars(ch3, 2*(size - i));  // print 2(size-i) ch3's
         printChars(ch4, i);             // print i ch4's
         System.out.println();           // advance to next line
      }
   }

}
