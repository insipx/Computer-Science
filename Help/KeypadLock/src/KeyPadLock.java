/** An object of this class models a device such as an electronic door lock.
**  To open such a lock, one must first enter a prescribed sequence of digits
**  (i.e., the "secret code") on a keypad.
**
**  The particular kind of lock represented here is one having a single
**  secret code that is three digits in length.  There is no mechanism
**  by which to change a lock's secret code.
**
** Author: R. McCloskey and <student name>
** Date: April 2016
*/

public class KeyPadLock {

   // instance variables
   // ------------------

   // the three digits that compose the secret code needed to open the lock
   private int codeDigit1, codeDigit2, codeDigit3;

   // # of digits entered on keypad since the last time memory was "erased"
   private int memoryLength;

   // the (up to) three digits most recently entered on the keypad
   private int enteredDig1, enteredDig2, enteredDig3;

   // Is the lock open?
   private boolean isOpen;


   // constructors
   // ------------

   /* Initializes a KeyPadLock object having the three-digit secret code 
   ** implied by the parameter, which is assumed to be in the interval [0,999].
   **
   ** pre: 0 <= secretCode <= 999
   ** post The newly-constructed lock is closed, has as its secret code the
   **      sequence of digits implied by the parameter, and has no "memory" 
   **      of any digits having been entered on its keypad.
   */
   public KeyPadLock(int secretCode) {

      codeDigit1 = (secretCode / 100) % 10;  // hundred's digit
      codeDigit2 = (secretCode / 10) % 10;   // ten's digit
      codeDigit3 = secretCode % 10;          // one's digit

      memoryLength = 0;     // memory of digits entered is initially empty
      isOpen = false;       // lock is initially closed
   }


   // observers:
   // ----------

   /* Reports whether or not the lock is open.
   */
   public boolean isOpen() {
      return isOpen;
   }



   // mutators
   // --------

   /* Accounts for a digit being entered on the keypad.
   **
   ** pre:  0 <= digit < 10
   ** post: If the lock is open, the digit entered is ignored.
   **       Otherwise it is "remembered" as being the latest digit entered
   **       on the keypad.
   */
   public void enterDigit(int digit)
   {
      if(isOpen){


      }else{
         if(digit < 10 && digit >= 0){
            int tempDigit = memoryLength % 3;
            memoryLength++;
            if(tempDigit == 0){
               enteredDig1 = digit;
            }else if(tempDigit == 1){
               enteredDig2 = digit;
            }else if(tempDigit == 2){
               enteredDig3 = digit;
            }

         }

      }


   }


   /* Attempts to open the lock.
   **
   ** post: If the lock was already open, it remains open.
   **       Otherwise, if the most recently entered digits match the lock's
   **       secret code, the lock becomes open.
   **       Otherwise, it remains closed.
   **       In any case, nothing about the lock changes except for its
   **       open vs. closed status.
   */
   public void open()
   {
      if(isOpen){


      }else{
         if(codeDigit1 == enteredDig1
            && codeDigit2 == enteredDig2
            && codeDigit3 == enteredDig3)
        {
            isOpen = true;
        }

      }
   }


   /* Closes the lock.
   **
   ** post: If the lock is open, it becomes closed and its memory of digits
   **       entered is "erased".  Otherwise, nothing changes.
   */
   public void close() {
      if (isOpen) {
         isOpen = false;
         memoryLength = 0;
      } else {

      }
   }
}

