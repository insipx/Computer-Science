/** Java program that draws four figures made from *'s, including a
**  rectangle and three varieties of triangles.
**  The point of it all is to develop skill in constructing for-loops and, in
**  particular, pairs of for-loops in which one loop nested inside the other.
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

public class DrawFiguresWithLoops {

   /** The main() method simply calls the other methods that draw the figures.
   */
   public static void main(String[] args)
   {
      drawRectangle();
      System.out.println();

      drawRightTriangle();
      System.out.println();

      drawUpsideDownRightTriangle();   
      drawUpsideDownRightTriangle2();  // alternative method
      System.out.println();

      drawDelta();
   }


   /** Draws a rectangle made of *'s.  The dimensions are determined by 
   **  the values assigned to "symbolic constants" M and N.
   */
   private static void drawRectangle()
   {
      final int M = 6;   // # of rows in the rectangle
      final int N = 13;  // # of columns

      // the outer loop iterates M times, each time printing a row of N *'s
      for (int i=1; i<=M; i=i+1) {
         for (int j=1; j<=N; j=j+1) {     // the nested loop iterates N times
            System.out.print('*');        // each time printing one *
         }
         System.out.println(); // skips to next line after printing a row of *'s
      }
   }

   /** Draws a right triangle made of *'s, where the right angle is at the
   **  bottom left.  The dimensions are determined by the value assigned to 
   **  "symbolic constant" M, which is the number of rows in the figure.
   **  The intuition leading to the code here is that if the rows of the 
   **  triangle are numbered 1 to M from top to bottom, then, for each i
   **  in the range 1..M, row i must have i *'s in it.
   */
   private static void drawRightTriangle()
   {
      final int M = 7;  // # of rows

      // outer loop iterates with i=1,2,...,M, printing i *'s on each iteration 
      for (int i=1; i<=M; i=i+1) {
         for (int j=1; j<=i; j=j+1) {  // the nested loop iterates i times
            System.out.print("*");     // each time printing one *
         }
         System.out.println();  // skip to next line after printing a row of *'s
      }
   }
   

   /** Draws a right triangle made of *'s, where the right angle is at the 
   **  upper left.  (So it is the upside-down version of the triangle
   **  drawn by the method above).  The dimensions are determined by the 
   **  value assigned to "symbolic constant" M.
   **  The intuition leading to the code here is that if the rows of the 
   **  triangle are numbered M down to 1 from top to bottom, then, for each i
   **  in the range 1..M, row i must have i *'s in it.
   */
   private static void drawUpsideDownRightTriangle()
   {
      final int M = 7;  // # of rows

      for (int i=M; i>=1; i=i-1) {     // Differs from method above in that i
         for (int j=1; j<=i; j=j+1) {  // varies from M down to 1 rather than
            System.out.print("*");     // from 1 up to M.
         }
         System.out.println(); // skip to next line after printing a row of *'s
      }
   }

   /** Draws a right triangle made of *'s, where the right angle is at the 
   **  upper left.  (So it has the same effect as the method above.)  The 
   **  dimensions are determined by the value assigned to "symbolic constant" M.
   **  The intuition leading to the code here is that if the rows of the 
   **  triangle are numbered 0 to M-1 from top to bottom, then, for each i
   **  in the range 0..M-1, row i should have M-i *'s in it.
   */
   private static void drawUpsideDownRightTriangle2()
   {
      final int M = 7;  // # of rows

      for (int i=0; i < M; i=i+1) {     
         for (int j=1; j <= M-i; j=j+1) {   // print M-i *'s in row i
            System.out.print("*"); 
         }
         System.out.println(); // skip to next line after printing a row of *'s
      }
   }


   /** Draws a triangle made of *'s that is the shape of the uppercase
   **  Greek delta.  The dimensions are determined by the value assigned 
   **  to "symbolic constant" M, which is the number of rows in the figure.
   */
   private static void drawDelta()
   {
      final int M = 9;  // # of rows

      for (int i=1; i<=M; i=i+1)
      {
         // print M-i spaces
         for (int j=1; j<=M-i; j=j+1) {
            System.out.print(' ');
         }

         // print 2i-1 *'s
         for (int j=1; j<=2*i-1; j=j+1) {
            System.out.print('*');
         }

         System.out.println();  // skip to next line after printing a row
      }
   }
}
