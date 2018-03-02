/* An instance of this class represents a person, via a limited profile.
*/
public class Person {

   // instance variables
   // ------------------
   public String name;
   public int age;       // in years
   public double height; // in feet


   // constructor
   // -----------

   public Person(String name, int age, double height) {
      this.name = name;
      this.age = age;
      this.height = height;
   }


   // observer
   // --------

   public String toString() {
      return "<name: " + name + "; age: " + age + "; height: " + height + ">";
   }

}
