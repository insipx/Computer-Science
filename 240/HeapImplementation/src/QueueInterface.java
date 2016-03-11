/**
 * Sean Batzel 2/14/2016.
 */

interface QueueInterface<T> {
    void enqueue(T val);

    T dequeue();

    void dumpQueue();
}
