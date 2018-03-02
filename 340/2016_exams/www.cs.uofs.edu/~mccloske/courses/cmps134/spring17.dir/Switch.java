/** An instance of this class represents a switch, which is a device that
** can be in either of two states: ON or OFF.
*/
public class Switch 
{
   // instance variables
   private boolean isOn;  // true if switch is in the ON state, false otherwise
   private int flipCntr;  // how many times the switch has been flipped

   // Constructor
   public Switch() { isOn = false;  flipCntr = 0; }

   // Observer: Returns String giving switch's current state and flip count. 
   public String toString() { 
      if (isOn) { return "ON; " + flipCntr; }
      else { return "OFF; " + flipCntr; }
   }

   // Mutator: Flips the switch from ON to OFF or vice versa. 
   public void flip() { isOn = !isOn;  flipCntr = flipCntr + 1; }


   //-----------------------------------------------------------
   
   public static void main(String[] args) {
      Switch s1 = new Switch(), s2 = new Switch();
      System.out.println("s1 : " + s1);

      s1.flip();  System.out.println("s1: " + s1);
      s1.flip();  System.out.println("s1: " + s1);

      s2.flip(); s2.flip(); s2.flip(); s2.flip(); s2.flip();
      System.out.println("s2: " + s2);
   }
}
