/* Generic Java interface whose implementing classes include methods that
** classify an element (of the reference type used in instantiating the
** class) as being in either of two categories, which are whimsically 
** referred to here as Red and Blue.
**
** Author: R. McCloskey, Sept. 2017
*/
public interface RedBlueClassifier<T> {

   // Returns true if the given item is classified as Red, false otherwise.
   boolean isRed(T item);

   // Returns true if the given item is classified as Blue, false otherwise.
   boolean isBlue(T item);
}
