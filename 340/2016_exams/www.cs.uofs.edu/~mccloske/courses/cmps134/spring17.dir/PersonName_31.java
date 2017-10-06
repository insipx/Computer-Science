/* An instance of this class represents a (person's) two- or three-part name.
*/
public class PersonName_31 {

   // instance variables
   // ------------------
   private String firstName, middleName, lastName;


   // constructors
   // ------------

   // Initializes the name according to the three parameters provided.
   public PersonName_31(String first, String middle, String last) {
      firstName = first;  middleName = middle;  lastName = last;
   }

   /* Initializes the name by extracting the relevant substrings from the given
   ** String (and assigning them to the instance variables).  Assumed is that
   ** the given String contains either a first and last name, or a first, 
   ** middle, and last, separated by single spaces.  
   ** Examples: "Amanda Blake", "Harold Joseph Gunkhead".
   ** If no middle name is given, the corresponding instance variable should be
   ** set to the empty string.
   */
   public PersonName_31(String fullName) {
      // Determine positions of first and last occurrences of a space.
      int posOfFirstSpace = fullName.indexOf(' ');
      int posOfLastSpace = fullName.lastIndexOf(' ');

      // The first name is the prefix preceding the first space.
      firstName = fullName.substring(0, posOfFirstSpace);

      // The last name is the suffix following the last space.
      lastName = fullName.substring(posOfLastSpace+1);

      // The middle name is the substring in between the first and last
      // spaces (exclusive), unless there is only one space, in which case
      // the middle name is the empty string.
      if (posOfFirstSpace != posOfLastSpace) {
         middleName = fullName.substring(posOfFirstSpace+1, posOfLastSpace);
      }
      else {
         middleName = "";
      }
   }


   // observers
   // ---------

   public String getFirstName() { return firstName; }

   public String getMiddleName() { return middleName; }

   public String getLastName() { return lastName; }
}
