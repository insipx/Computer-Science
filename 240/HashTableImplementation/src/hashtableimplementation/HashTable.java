package hashtableimplementation;

public class HashTable implements HashTableInterface{

    // We'll treat this data structure as a 10-element array of unsorted linked lists.
    
    Node hash[];

    public HashTable() {
        hash = new Node[10];
        for (int i = 0; i < 10; i++) {
            hash[i] = new Node(); //Make sure there are nodes to have references to.
        }
    }

    @Override
    public void insert(int value) {
        //System.out.println("Inserting " + value);
        Node tmp = hash[locate(value)]; // Refer to the hash function and assign as the current array index.
        while (tmp.ref != null) {
            //System.out.println(tmp.value);
            tmp = tmp.ref; // Move into the next node.
        }
        tmp.ref = new Node(); // Create a new empty node to refer to.
        tmp.value = value; // Populate the node.
    }

    @Override
    public void delete(int value) throws ElementNotFoundException { // Refer to ElementNotFoundException.java
        //System.out.println("Deleting " + value);
        Node tmp = hash[locate(value)]; // Use the hash function to determine which key we're working with.
        while (tmp.ref != null) {
            if (tmp.value == value) { // This is the droid you're looking for,
                tmp.ref = tmp.ref.ref; // skip the next element- reset the reference on the node to the one after it and pretend it doesn't even exist. The GC will take care of it. 
                return;
            } else { // This is not the droid you're looking for.
                tmp = tmp.ref; // Move along.
            }
        }
        throw new ElementNotFoundException("Specified element does not exist - " + value ); // Refer to ElementNotFoundException.java.
    }

    @Override
    public int locate(int value) {
        // We'll refer to this as the hash function. Just returns whatever key we're assigning this value to. In this case, it's just the digit in the one's place.
        return value % 10; 
    }

    public void dumphash() {
        // Iterates through the keys,
        for (int i = 0; i < 10; i++) {
            // Prints the current key,
            System.out.print("[" + i + "]");
            // Assigns us to the first node in the linked list,
            Node tmp = hash[i];
            // Iterate through the nodes in the linked list.
            while (tmp.ref != null) {
                // Print out the value of the node,
                System.out.print(": " + tmp.value);
                // Move into the next node.
                tmp = tmp.ref;
            }
            // Finish up the key by putting a space between it and the next key.
            System.out.println();
        }
        // Wrap up the structure and put a space between it and the next output.
        System.out.println();
    }
}
