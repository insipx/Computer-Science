/** Program (from page 34 of Reges & Stepp textbook (3rd ed.)) that draws two
**  boxes separated by a blank line.  This version is an improvement over the
**  original in its use of the drawBox() method and is intended to be a simple
**  demonstration of "procedural decomposition".
*/

public class DrawBoxes2 {

   public static void main(String[] args)
   {
      drawBox();
      System.out.println();
      drawBox();
   }

   /** Draws a box.
   */
   private static void drawBox()
   {
      System.out.println("+------+");
      System.out.println("|      |");
      System.out.println("|      |");
      System.out.println("+------+");
   }

}
