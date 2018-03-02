/** Java program that draws four figures made from *'s, including a
**  rectangle and three varieties of triangles.
**
**  This version of the program improves the previous one (of the same name,
**  but with "2" at the end rather than "3") in that the drawX() methods
**  have been parameterized to receive the desired size of the figure via
**  a formal parameter (or two in the case of drawRectangle()).
**
**  Here are examples of the four types of figures that the program draws:
**
**    *******        *                 *****               *
**    *******        **                ****               ***
**    *******        ***               ***               *****
**    *******        ****              **               *******
**    *******        *****             *               *********
**
**    5-by-7        size 5            size 5             size 5
**   rectangle   right triangle     upside down          delta
**                                 right triangle
*/

public class DrawFiguresWithLoops3 {

   /* The main() method simply calls the other methods in order to
   ** draw the figures.
   */
   public static void main(String[] args)
   {
      drawRectangle(6, 10);   // draw a 6-by-10 rectangle
      System.out.println();

      drawRectangle(12, 5);   // draw a 12-by-5 rectangle
      System.out.println();

      drawRightTriangle(9);   // draw a right triangle of size 9
      System.out.println();

      drawRightTriangle(4);   // draw a right triangle of size 4
      System.out.println();

      drawUpsideDownRightTriangle(6);  // size 6
      System.out.println();

      drawDelta(5);
      System.out.println();

      drawDelta(18);
   }


   /** Draws a rectangle of *'s having the specified dimensions, which are
   **  received via the formal parameters (numRows and numCols).
   */
   private static void drawRectangle(int numRows, int numCols)
   {
      for (int i=1; i<=numRows; i=i+1) {
         printChars('*', numCols);  
         System.out.println(); 
      }
   }


   /** Draws a right triangle of *'s, where the right angle is at the
   **  bottom left.  The dimensions are specified by the value received via
   **  the formal parameter (numRows).
   */
   private static void drawRightTriangle(int numRows)
   {
      // outer loop iterates with i=1,2,...,numRows, printing i *'s each time
      for (int i=1; i<=numRows; i=i+1)
      {
         printChars('*', i);
         System.out.println(); 
      }
   }
   

   /** Draws a right triangle made of *'s, where the right angle is at the 
   **  upper left.  (So it is the upside-down version of the triangle
   **  drawn by the method above).  The dimensions are specified by the 
   **  value received via the formal parameter (numRows).
   */
   private static void drawUpsideDownRightTriangle(int numRows)
   {
      for (int i=numRows; i>=1; i=i-1)  // Differs from method above in that i
      {                                 // counts down rather than up
         printChars('*', i); 
         System.out.println(); 
      }
   }


   /** Draws a triangle of *'s that is the shape of the uppercase Greek delta.
   **  The dimensions are specified by the value received via the formal
   **  parameter (numRows).
   */
   private static void drawDelta(int numRows)
   {
      for (int i=1; i<=numRows; i=i+1)
      {
         printChars(' ', numRows-i);
         printChars('*', 2*i-1);
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
