/* An instance of this class, which implements the RedBlueClassifier
** interfact, classifies even integers as Red and odd integers as Blue.
**
** Author: R. McCloskey, Sept. 2017
*/
public class EvenOddClassifier implements RedBlueClassifier<Integer> {

   public boolean isRed(Integer r) { return r % 2 == 0; }
   public boolean isBlue(Integer r) { return r % 2 != 0; }
}
