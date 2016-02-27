package hashtableimplementation;

public class HashTableImplementation {

    public static void main(String[] args) {
        HashTable hashtable = new HashTable();
        hashtable.insert(1);
        hashtable.insert(5);
        hashtable.insert(28);
        try {
            hashtable.delete(5);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
        hashtable.insert(15);
        hashtable.insert(8);
        hashtable.dumphash();
        try {
            hashtable.delete(1);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
        hashtable.insert(18);
        hashtable.insert(25);
        try {
            hashtable.delete(33);
        } catch (ElementNotFoundException e) {
            System.out.println(e.getMessage());
        }
        hashtable.dumphash();
    }
}
