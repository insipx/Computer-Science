/**
 * Sean Batzel 2/14/2016.
 */

interface QueueInterface<T> {
    @SuppressWarnings("unused")
    void enqueue(T val);

    @SuppressWarnings("unused")
    T dequeue();

    @SuppressWarnings("unused")
    void dumpQueue();
}
