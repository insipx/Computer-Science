/* An instance of this class represents a (person's) two- or three-part name.
*/
public class PersonName {

   // instance variables
   // ------------------
   private String firstName, middleName, lastName;


   // constructors
   // ------------

   /* Initializes the name according to the three parameters specified.
   */
   public PersonName(String first, String middle, String last) {

      // STUB!

   }

   /* Initializes the name by extracting the relevant substrings from the 
   ** given String.  Assumed is that the given String contains either a 
   ** first and last name, or a first, middle, and last, separated by single
   ** spaces.  Examples: "Amanda Blake", "Harold Joseph Gunkhead".
   ** If no middle name is given, the corresponding instance variable should
   ** bet set to the empty string.
   */
   public PersonName(String fullName) {
      firstName = extractFirstName(fullName);
      middleName = extractMiddleName(fullName);
      lastName = extractLastName(fullName);
   }

   // private methods in support of the constructor above
   // ---------------------------------------------------
   /* Returns the prefix of the given String up to but not including the
   ** first space.
   */
   private static String extractFirstName(String name) {
      return "";  // STUB!
   }

   /* Returns the substring of the given String in between the first
   ** and last spaces, unless there are fewer than two spaces, in which
   ** case the empty String is returned.
   */
   private static String extractMiddleName(String name) {
      return "";  // STUB!
   }

   /* Returns the suffix of the given String that follows the last space.
   */
   private static String extractLastName(String name) {
      return "";  // STUB!
   }

   // end of private methods in support of constructor
   // ------------------------------------------------


   // observers
   // ---------

   public String getFirstName() { return ""; }   // STUB!

   public String getMiddleName() { return ""; }  // STUB!

   public String getLastName() { return ""; }    // STUB!
}
