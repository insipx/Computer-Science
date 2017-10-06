/* An instance of this class represents a (person's) two- or three-part name.
*/
public class PersonName_1 {

   // instance variables
   // ------------------
   private String firstName, middleName, lastName;


   // constructors
   // ------------

   // Initializes the instance variables to the three parameters provided.
   public PersonName_1(String first, String middle, String last) {
      firstName = first;  middleName = middle;  lastName = last;
   }

   /* Initializes the instance variables to the appropriate substrings of the 
   ** given String.  Assumed is that the given String contains either a first
   ** and last name, or a first, middle, and last name, separated by single 
   ** spaces.
   ** Examples: "Amanda Blake", "Harold Joseph Gunkhead".  
   ** If no middle name appears, the corresponding instance variable should be
   ** set to the empty string.
   */
   public PersonName_1(String fullName) {
      firstName = extractFirstName(fullName);  
      middleName = extractMiddleName(fullName);
      lastName = extractLastName(fullName);
   }


   /* Returns the prefix of the given String that precedes the first
   ** occurrence of a space.
   */
   private String extractFirstName(String name) {
      int posOfFirstSpace = name.indexOf(' ');
      return name.substring(0, posOfFirstSpace);
   }

   /* Returns the substring of the given String in between the first and
   ** last occurrences of a space (exclusive), or the empty string in case
   ** there are fewer than two spaces.
   */
   private String extractMiddleName(String name) {
      int posOfFirstSpace = name.indexOf(' ');
      int posOfLastSpace = name.lastIndexOf(' ');

      if (posOfFirstSpace != posOfLastSpace) {
         return name.substring(posOfFirstSpace+1, posOfLastSpace);
      }
      else {
         return "";
      }
   }

   /* Returns the suffix of the given String that follows the last
   ** occurrence of a space.
   */
   private String extractLastName(String name) {
      int posOfLastSpace = name.lastIndexOf(' ');
      return name.substring(posOfLastSpace+1);
   }


   // observers
   // ---------

   public String getFirstName() { return firstName; }

   public String getMiddleName() { return middleName; }

   public String getLastName() { return lastName; }
}
