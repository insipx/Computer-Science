/**
 * Created by insidious on 3/30/16.
 */
/** Java class that has methods for "extracting" each of the three parts of a
 **  String containing a formal full name.  Such a name has the form
 **
 **                 <last_name>, <first_name> <middle_name>
 **
 ** where the middle name could be absent.  That is, a formal full name is
 ** composed of a last name (also known as a "family name"), followed by a
 ** comma, followed by a single space, followed by a first name, followed,
 ** optionally, by a single space and a middle name.  Among the three names,
 ** only the last name may include spaces.  Here are some examples:
 **
 **  +------------------------------+
 **  |Simpson, Orenthal James       |
 **  |Van Helsing, Abraham          |
 **  |Van Helsing, Prudence Rebecca |
 **  |Rumplestiltskin, Jill Ann     |
 **  |Rumplestiltskin, Robert       |
 **  |O'Reilly, William James       |
 **  +------------------------------+
 **
 **  Author: R. McCloskey and <student's name>
 **  Last modified: March 2016
 **  Partial Solution to Prog. Assg. #4, CMPS 134, Spring 2016
 */

public class FormalFullNameParser {

    // symbolic constants
    private static final char SPACE = ' ';
    private static final char COMMA = ',';
    private static final String EMPTY_STRING = "";


    /** Given (via its parameter) a String that is a valid formal full name,
     **  returns the substring containing the last (i.e., family) name.
     **  This is just the prefix up to but not including the comma.
     */
    public static String lastNameOf(String stuName)
    {
        //Simpson, Orenthal James
       int index = stuName.indexOf(',');

        return stuName.substring(0,index);
    }


    /** Given (via its parameter) a String that is a valid formal full name,
     **  returns the substring containing the first name.   This is the
     **  substring beginning two places after the comma and extending either
     **  to the end of the string or up to, but not including, the next space,
     **  whichever comes first.
     */
    public static String firstNameOf(String stuName)
    {

        int commaIndex = stuName.indexOf(',');
        commaIndex = commaIndex + 2;

        int lastSpace = stuName.indexOf(' ', commaIndex);

        if(lastSpace == -1){
            return stuName.substring(commaIndex, stuName.length());
        }else{
             String result = stuName.substring(lastSpace, stuName.length());

            return result.trim();
        }

    }

    /** Given (via its parameter) a String that is a valid formal full name,
     **  returns the substring containing the middle name.  In case there is
     **  only one space following the comma, this is the empty string (as
     **  there is no middle name).  Otherwise it is the suffix beginning at
     **  the position following the last space.
     */
    public static String middleNameOf(String stuName)
    {
        int commaIndex = stuName.indexOf(',');
        commaIndex = commaIndex + 2;

        int lastSpace = stuName.indexOf(' ', commaIndex);

        if(lastSpace == -1){
            return "";
        }else{
             return stuName.substring(lastSpace, stuName.length());
        }

    }

}
