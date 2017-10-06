/* An instance of this class, which implements the RedBlueClassifier
** interface, classifies negative Integers as Red and positive (more
** accurately, nonnegative) Integers as Blue.
*/

public class NegPosClassifier implements RedBlueClassifier<Integer> {

   public boolean isRed(Integer r) { return r < 0; }
   public boolean isBlue(Integer r) { return r >= 0; }
}
