/* Java application that prints the lyrics of (a modified version of) the 
** well known children's song "There Was an Old Lady Who Swallowed a Fly".
** The program was designed to employ, as far as possible, the related
** techniques of procedural decomposition and code redundancy avoidance.
**
** Author: R. McCloskey
** Date: February 2017
** For CMPS 134, Spring 2017, Prog. Assg. #1
*/

public class LadySwallowedFly {

   /* The main method simply calls the methods corresponding to the verses 
   ** of the song.
   */
   public static void main(String[] args) {
      fly(); spider(); bird(); cat(); owl(); eagle(); horse();
   }

   //----------------------------------------------------------------------
   /* Each in the following group of methods prints one verse of the song,
   ** namely that verse featuring the creature after which the method is
   ** named.
   */

   private static void horse() {
      thereWas();
      System.out.println("a horse...");
      System.out.println("She's dead, of course!");
   }

   private static void eagle() {
      thereWas();
      System.out.println("an eagle;");
      System.out.println("To her it tasted better than a beagle.");
      swallowedEagle();
   }

   private static void owl() {
      thereWas();
      System.out.println("an owl;");
      System.out.println("How will she ever empty her bowel?");
      swallowedOwl();
   }

   private static void cat() {
      thereWas();
      System.out.println("a cat;");
      System.out.println("Fancy that to swallow a cat!");
      swallowedCat();
   }

   private static void bird() {
      thereWas();
      System.out.println("a bird;");
      System.out.println("How absurd to swallow a bird!");
      swallowedBird();
   }

   private static void spider() {
      thereWas();
      System.out.println("a spider;");
      System.out.println("That wriggled and wiggled and tickled inside her!");
      swallowedSpider();
   }

   private static void fly() { 
      thereWas(); 
      System.out.println("a fly;");  
      swallowedFly();
   }

   //---------------------------------------------------------------------


   /* Each in the next group of methods prints the suffix of the verse
   ** beginning, "She swallowed the X to catch the Y", where X is the name
   ** of the creature identified in the method's name.  Notice that
   ** each method calls the next one in sequence in order to achieve the
   ** "cumulative" property of successive verses.
   */

   private static void swallowedEagle() {
      sheSwallowed(); System.out.print("eagle "); toCatch(); 
      System.out.println("owl,");  swallowedOwl();
   }

   private static void swallowedOwl() {
      sheSwallowed(); System.out.print("owl "); toCatch(); 
      System.out.println("cat,"); swallowedCat();
   }

   private static void swallowedCat() {
      sheSwallowed(); System.out.print("cat "); toCatch(); 
      System.out.println("bird,"); swallowedBird();
   }

   private static void swallowedBird() {
      sheSwallowed(); System.out.print("bird "); toCatch(); 
      System.out.println("spider,"); swallowedSpider();
   }

   private static void swallowedSpider() {
      sheSwallowed(); System.out.print("spider "); toCatch(); 
      System.out.println("fly;"); swallowedFly();
   }

   private static void swallowedFly() {
      System.out.print("I don't know why she swallowed a fly - ");
      System.out.println("perhaps she'll die!");
      System.out.println();
   }


   //-----------------------------------------------------------------

   /* Each in the next group of methods simply prints a phrase that
   ** gets repeated several times in the song.
   */

   private static void thereWas() {
      System.out.print("There was an old lady who swallowed ");
   }

   private static void sheSwallowed() {
      System.out.print("She swallowed the ");
   }

   private static void toCatch() {
      System.out.print("to catch the ");
   }

}
