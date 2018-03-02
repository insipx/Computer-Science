/** Java program that draws four figures, including a rectangle and 
**  three varieties of triangles.
**
**  This version of the program improves the previous one (of the same name,
**  but with "3" at the end rather than "4") in that to each of the drawX() 
**  methods has been added a parameter of type char specifying the character
**  to be used in drawing the figure.
**
**  Here are examples of the four types of figures that the program draws,
**  using various characters.
**
**    *******        $                 (((((               a 
**    *******        $$                ((((               aaa 
**    *******        $$$               (((               aaaaa
**    *******        $$$$              ((               aaaaaaa
**    *******        $$$$$             (               aaaaaaaaa
**
**    5-by-7        size 5            size 5             size 5
**   rectangle   right triangle     upside down          delta
**                                 right triangle
*/

public class DrawFiguresWithLoops4 {

   /* The main() method simply calls the other methods in order to
   ** draw the figures.
   */
   public static void main(String[] args)
   {
      drawRectangle(6, 10,'*');   // draw a 6-by-10 rectangle made of *'s
      System.out.println();

      drawRectangle(12, 5, '%');   // draw a 12-by-5 rectangle made of %'s
      System.out.println();

      drawRightTriangle(9, '$');  // draw a right triangle of size 9 made of $'s
      System.out.println();

      drawRightTriangle(4, '#');  // draw a right triangle of size 4 made of #'s
      System.out.println();

      drawUpsideDownRightTriangle(6, '(');  // draw an upside down right
      System.out.println();                 // trangle of size 6, made of ('s

      drawDelta(5, 'a');           // draw a delta of size 5, made of a's
      System.out.println();

      drawDelta(18, '@');
   }


   /** Draws a rectangle using the specified character (ch) and having the
   **  specified dimensions, all three of which are received via the 
   **  formal parameters 
   **  (numRows, numCols, and ch).
   */
   private static void drawRectangle(int numRows, int numCols, char ch)
   {
      for (int i=1; i<=numRows; i=i+1) {
         printChars(ch, numCols);  
         System.out.println(); 
      }
   }


   /** Draws a right triangle using the specified character (ch), where the
   **  right angle is at the bottom left.  The dimensions are specified by 
   **  the value received via the formal parameter (numRows).
   */
   private static void drawRightTriangle(int numRows, char ch)
   {
      // outer loop iterates with i=1,2,...,numRows, printing i *'s each time
      for (int i=1; i<=numRows; i=i+1)
      {
         printChars(ch, i);
         System.out.println(); 
      }
   }
   

   /** Draws a right triangle made of the specified character (ch), where
   **  the right angle is at the upper left.  (So it is the upside-down
   **  version of the triangle drawn by the method above).  The dimensions
   **  are specified by the value received via the formal parameter (numRows).
   */
   private static void drawUpsideDownRightTriangle(int numRows, char ch)
   {
      for (int i=numRows; i>=1; i=i-1)  // Differs from method above in that i
      {                                 // counts down rather than up
         printChars(ch, i); 
         System.out.println(); 
      }
   }


   /** Draws a triangle using the specified character (ch) that is the 
   **  shape of the uppercase Greek delta.
   **  The dimensions are specified by the value received via the formal
   **  parameter (numRows).
   */
   private static void drawDelta(int numRows, char ch)
   {
      for (int i=1; i<=numRows; i=i+1)
      {
         printChars(' ', numRows-i);
         printChars(ch, 2*i-1);
         System.out.println();
      }
   }


   /** Prints the specified character (ch) the specified # of times (k).
   */
   private static void printChars(char ch, int k)
   {
      for (int i=0; i<k; i=i+1)        // iterates k times, each time
      {                                // printing ch's value
         System.out.print(ch);
      }
   }

}
