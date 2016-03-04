/**
 * Sean Batzel 2/14/2016.
 */

package binary.search.tree;

interface QueueInterface<T> {
    void enqueue(T val);

    T dequeue();

    void dumpQueue();
}
