package hashtableimplementation;

public interface HashTableInterface {

    void insert(int value);

    void delete(int value) throws ElementNotFoundException;

    int locate(int value);

    void dumphash();
}
