/* Date: June 2017
** Author: R. McCloskey
**   Description: 
**   Abstract Java class instances of which represent natural numbers.
*/


public abstract class NaturalNumber implements Comparable<NaturalNumber> {

   /*  <<<<   P U B L I C   >>>>   */

   // Factories
   // ---------

   /* Returns a NaturalNumber object that represents the number described 
   ** by the given int value, which is assumed to be nonnegative. 
   */
   public abstract NaturalNumber factory(int n);

   /* Returns a NaturalNumber object that represents the number described 
   ** by the given String, which is assumed to be an unsigned decimal 
   ** integer numeral (i.e., consist of a sequence of digit characters 
   ** (i.e., characters in the range '0'..'9')).
   */
   public abstract NaturalNumber factory(String str);


   // Constants
   // ---------

   /* Returns NaturalNumber objects representing zero and one, respectively.
   */
   public abstract NaturalNumber ZERO();
   public abstract NaturalNumber ONE();


   // Observers
   // ---------

   /* Returns an int value that is negative, zero, or positive, respectively,
   ** according to whether this NaturalNumber is less than, equal to, or
   ** greater than, respectively, the given one.
   */
   public int compareTo(NaturalNumber n) {
      return 0;   // STUB
   } 

   /* Returns true if this NaturalNumber is less than the given one,
   ** false otherwise. 
   */
   public boolean lessThan(NaturalNumber n) { return this.compareTo(n) < 0; }

   /* Returns true if this NaturalNumber is greater than the given one,
   ** false otherwise. 
   */
   public boolean greaterThan(NaturalNumber n) { return this.compareTo(n) > 0; }

   /* Returns true if this NaturalNumber is equal to the given one,
   ** false otherwise. 
   */
   public boolean equals(NaturalNumber n) { return this.compareTo(n) == 0; }

   /* Returns the decimal (i.e., base 10) numeral, composed of decimal digits,
   ** describing this number.  There should be no leading zeros (except, of
   ** course, for the number zero itself.)
   */
   public abstract String toString();


   // Generators
   // ----------

   // Each of the methods in this section produces a new NaturalNumber that
   // represents the value produced by applying an arithmetic operation
   // to this NaturalNumber and one given via parameter.

   /* Returns, respectively, the sum, difference, product, quotient, 
   ** remainder, minimum, and maximum produced by combining this NaturalNumber 
   ** with the given one.
   */
   /* Returns the sum of this NaturalNumber and the given one.
   */
   public abstract NaturalNumber plus(NaturalNumber n);

   /* Returns the difference of this NaturalNumber and the given one,
   ** or zero if this one is the lesser of the two.
   */
   public abstract NaturalNumber minus(NaturalNumber n);

   /* Returns the product of this NaturalNumber and the given one.
   */
   public abstract NaturalNumber times(NaturalNumber n);

   /* Returns the quotient produced by dividing this NaturalNumber by 
   ** the given one.
   */
   public abstract NaturalNumber dividedBy(NaturalNumber n);

   /* Returns the remainder produced by dividing this NaturalNumber by 
   ** the given one.
   */
   public NaturalNumber modulo(NaturalNumber n) {
      NaturalNumber quotient = this.dividedBy(n);
      NaturalNumber almostThis = quotient.times(n);
      return this.minus(almostThis);
   }

   /* Returns the lesser among this NaturalNumber and the given one.
   */
   public NaturalNumber minimum(NaturalNumber n) {
      if (lessThan(n)) { return this; }
      else { return n; }
   }

   /* Returns the larger among this NaturalNumber and the given one.
   */
   public NaturalNumber maximum(NaturalNumber n) {
      if (lessThan(n)) { return n; }
      else { return this; }
   }
}
