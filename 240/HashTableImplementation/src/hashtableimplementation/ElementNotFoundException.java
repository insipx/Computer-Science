package hashtableimplementation;

public class ElementNotFoundException extends Exception {

    /* THE LOGIC BEHIND THIS CLASS
     If this hash table class were to be used in a larger piece of software, this class could be used to throw an exception similar to the NullPointerException. Instead of just not deleting anything at all or printing "Nothing to delete" to stdin, this allows for a tighter control of the structure by the programmer. If they try to delete an element that doesn't exist, the system will handle that by throwing an exception. The main class simply catches those exceptions and prints the message associated with the exception.
    
     "Errors should never pass silently - unless explicitly silenced." - The Zen of Python
     */
    public ElementNotFoundException() {
    }

    public ElementNotFoundException(String msg) {
        super(msg);
    }
}
