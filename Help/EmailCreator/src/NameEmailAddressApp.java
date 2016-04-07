/**
 * Created by insidious on 3/30/16.
 */

import java.util.Scanner;
import java.io.File;
import java.io.IOException;

/** Java application that processes a file containing students' formal full
 **  names, which are in the form
 **
 **            <last_name>, <first_name> <middle_name>
 **
 **  and, for each one, displays the student's casual semi-full name, which is
 **  in the form
 **
 **            <first_name> <middle_initial> <last_name>
 **
 **  followed by a colon, a space, and the student's University of Scranton
 **  e-mail address, which is to be derived from the name.
 **
 **  A last (i.e., family) name may include spaces (as in "Van Helsing") or
 **  apostrophes (as in "O'Neill") and the middle name may be absent.
 **
 **  An e-mail address is of the form
 **
 **    <last_name_prefix><first_initial><middle_initial>@scranton.edu
 **
 **  where the <last_name_prefix> component is chosen to be the longest prefix
 **  of the last name ensuring that the number of characters preceding the
 **  @-sign is at most 12.  (Of course, if the student has no middle name the
 **  <middle_initial> component will be absent.)
 **
 **  A file of student names might contain, for example:
 **
 **  +-----------------------------+
 **  |Simpson, Orenthal James      |
 **  |Van Helsing, Abraham         |
 **  |Van Helsing, Prudence Rebecca|
 **  |Rumplestiltskin, Jill Ann    |
 **  |Rumplestiltskin, Robert      |
 **  |O'Reilly, William James      |
 **  +-----------------------------+
 **
 **  The output corresponding to the above would be:
 **
 **  +--------------------------------------------------+
 **  |Orenthal J. Simpson: simpsonoj@scranton.edu       |
 **  |Abraham Van Helsing: van_helsinga@scranton.edu    |
 **  |Prudence R. Van Helsing: van_helsinpr@scranton.edu|
 **  |Jill A. Rumplestiltskin: rumplestilja@scranton.edu|
 **  |Robert Rumplestiltskin: rumplestiltr@scranton.edu |
 **  |William J. O'Reilly: oreillywj@scranton.edu       |
 **  +--------------------------------------------------+
 **
 **  Note that e-mail addresses have no uppercase letters, any space occurring
 **  in a last name appears as an underscore in the e-mail address, and any
 **  apostrophe in a last name is omitted in the e-mail address.
 **
 **  If a command-line argument is provided when the program is executed,
 **  that argument is taken to be the name of the file containing input
 **  data.  If no such argument is provided, the program prompts the user
 **  to enter input data.  In either case, the program terminates when it
 **  reads a line of input of length zero.  This corresponds to the user
 **  pressing ENTER in response to the prompt or to the place in the input
 **  file where two consecutive newline characters occur.
 **
 **  Author: R. McCloskey and <student's (YOUR) name>
 **  Last modified: March 2016
 **  Partial solution to Prog. Assg. #4, CMPS 134, Spring 2016
 */

public class NameEmailAddressApp {

    // symbolic constants for specified strings
    private static final String SPACE = " ";
    private static final String PERIOD = ".";
    private static final String COLON = ":";
    private static final String APOSTROPHE = "'";
    private static final String AT_SIGN = "@";
    private static final String UNDERSCORE = "_";
    private static final String DOMAIN = "scranton.edu";
    private static final String EMPTY_STRING = "";


    /** Main program.
     */
    public static void main(String[] args) throws IOException
    {

        System.out.println(FormalFullNameParser.firstNameOf("Simpson, Orenthal James"));
        Scanner input;
        boolean inputFromKeyboard;
        final String USER_PROMPT = "Enter a name: ";

        // If a command line argument is provided, that argument is taken
        // to be the name of the file containing input data; otherwise"
        // input data will be read from the keyboard.
        if (args.length == 0) {
            input = new Scanner(System.in);
            inputFromKeyboard = true;
        }
        else {
            input = new Scanner(new File(args[0]));
            inputFromKeyboard = false;
        }

        if (inputFromKeyboard) { System.out.print(USER_PROMPT); }

        // read the first student's formal full name
        String name = input.nextLine();

        while (name.length() != 0)
        {
            // Fetch each of the three parts of the student's name by calling
            // methods from the FormalFullNameParser class.
            String last = FormalFullNameParser.lastNameOf(name);
            String first = FormalFullNameParser.firstNameOf(name);
            String middle = FormalFullNameParser.middleNameOf(name);

            // form the casual semi-full name
            String semiFullName = semiFullNameOf(first, middle, last);

            // form the e-mail address
            String emailAddr = emailAddressOf(first, middle, last);

            // display them
            System.out.println(semiFullName + COLON + SPACE + emailAddr);

            // prompt user for next name (if appropriate)
            if (inputFromKeyboard) { System.out.print('\n' + USER_PROMPT); }

            // read the next student's formal full name
            name = input.nextLine();
        }

        System.out.println("\n*** Program terminating normally. ***");
    }


    /** Given a student's first, middle, and last names (via the three formal
     **  parameters), this method returns the corresponding casual semi-full
     **  name, in the form
     **
     **         <first_name> <middle_initial>. <last_name>
     **
     **  If the middle name provided is the empty string, no middle initial
     **  (or period) appears in the result (and the first and last names are
     **  separated by a single space).  For example, given "Chris", "Jamie",
     **  and "Smith" as the three names, respectively, the string returned will
     **  be "Chris J. Smith".  If the given middle name is "", the string
     **  returned will be "Chris Smith".
     */
    private static String semiFullNameOf(String firstName,
                                         String middleName,
                                         String lastName)
    {
        if(middleName.equals(EMPTY_STRING)){

            return firstName + SPACE + lastName;

        }else{

            return firstName + SPACE + middleName.charAt(0) + PERIOD + SPACE + lastName;

        }
    }


    /** Given a student's first, middle, and last names (via the three formal
     **  arguments), this method returns the corresponding U of Scranton
     **  e-mail address in the form
     **
     **         <last_name_prefix><first_init><mid_init>@scranton.edu
     **
     **  For example, given "Chris", "Jamie", and "Smith", the string returned
     **  would be "smithcj@scranton.edu".  If the middle name provided is the
     **  empty string, no middle initial appears in the address.  The maximum
     **  number of characters preceding the @-sign is 12.  If necessary, the
     **  last name is truncated to achieve this upper bound.  Any spaces in the
     **  last name are replaced by underscores, any apostrophes are omitted,
     **  and all letters are put into lower case.
     */
    private static String emailAddressOf(String firstName,
                                         String middleName,
                                         String lastName)
    {
       String result;

       if(middleName.equals("")){

           lastName = lastName.replace(" ", UNDERSCORE);
           lastName = lastName.replace(APOSTROPHE, "");

           result = lastName + firstName.charAt(0) + AT_SIGN + DOMAIN;

       }else{
          return lastName + middleName + firstName.charAt(0) + AT_SIGN + DOMAIN;

       }


       if(result.length() > 12){
            int length = result.length() + 1;
            length = length - 12;
            length = result.length() - length;

            lastName = lastName.substring(0, length);

       }else{

       }
        return result;
    }

}
