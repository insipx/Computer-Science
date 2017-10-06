/* Date: June 2017
** Author: R. McCloskey
**   Description: 
**   Java subclass of NaturalNumber, instances of which represent 
**   natural numbers.
*/


public class NaturalNumber1 extends NaturalNumber {

   /*  <<<<   P U B L I C   >>>>   */

   // Constructors
   // ------------

   /* Initializes new object to represent the given integer value,
   ** which is assumed to be nonnegative.
   */
   public NaturalNumber1(int n) { }   // STUB

   /* Initializes new object to represent the integer value
   ** described by the given String, which is assumed to be 
   ** composed of digit characters.
   */
   public NaturalNumber1(String str) { }   // STUB


   // Factories
   // ---------

   /* Returns a NaturalNumber object that represents the number described 
   ** by the given int value, which is assumed to be nonnegative. 
   */
   public NaturalNumber1 factory(int n) {
      return new NaturalNumber(n);
   }

   /* Returns a NaturalNumber object that represents the number described 
   ** by the given String, which is assumed to be an unsigned decimal 
   ** integer numeral (i.e., consisting of a sequence of digit characters 
   ** (i.e., characters in the range '0'..'9')).
   */
   public NaturalNumber1 factory(String str) {
      return new NaturalNumber(str);
   }


   // Constants
   // ---------

   /* Returns NaturalNumber objects representing zero and one, respectively.
   */
   public NaturalNumber1 ZERO() { return null; }   // STUB
   public NaturalNumber1 ONE() { return null; }    // STUB


   // Observers
   // ---------

   /* Returns an int value that is negative, zero, or positive, respectively,
   ** according to whether this NaturalNumber is less than, equal to, or
   ** greater than, respectively, the given one.
   */
   //public int compareTo(NaturalNumber n) {
   //   return 0;   // STUB!
   //}


   public String toString() { }   // STUB


   // Generators
   // ----------

   // Each of the methods in this section produces a new NaturalNumber that
   // represents the value produced by applying an arithmetic operation
   // to this NaturalNumber and one given via parameter.

   /* Returns the sum of this NaturalNumber and the given one.
   */
   public NaturalNumber1 plus(NaturalNumber n) {
      return ZERO();    // STUB!!
   }

   /* Returns the difference of this NaturalNumber and the given one,
   ** or zero if this one is the lesser of the two.
   */
   public NaturalNumber1 minus(NaturalNumber n) {
      return ZERO();    // STUB!!
   }

   /* Returns the product of this NaturalNumber and the given one.
   */
   public NaturalNumber1 times(NaturalNumber n) {
      return ZERO();    // STUB!!
   }

   /* Returns the quotient produced by dividing this NaturalNumber by 
   ** the given one.
   */
   public NaturalNumber1 dividedBy(NaturalNumber n) {
      return ZERO();   // STUB!
   }

}
