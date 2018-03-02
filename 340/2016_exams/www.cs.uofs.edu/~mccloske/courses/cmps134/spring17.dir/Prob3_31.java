/* Java application based on Problem 3 from CMPS 134, Spring 2017, Test #2, 
** Section 31.  
*/
public class Prob3_31 {

   public static void main(String[] args) {
      String result = stringThing("Cow jumped over the moon"); 
      System.out.println("\nValue returned by method is |" + result + "|");
   }

   public static String stringThing(String str)
   {
      final char SPACE = ' ';
      String result = "";
      int aft = 0;
      int fore = str.indexOf(SPACE);
      int iterNum = 0;
      report(iterNum, fore, aft, result);
      while (fore != -1) {
         result = str.substring(aft,fore+1) + result;
         aft = fore+1;
         fore = str.indexOf(SPACE, aft);
         iterNum++;
         report(iterNum, fore, aft, result);
      }
      return str.substring(aft) + SPACE + result;
   }


   private static void report(int iter, int fore, int aft, String result) {
      System.out.println("After " + iter + " iterations: fore = " + fore + 
                         "  aft = " + aft + "  result = " + result);
   }

}
