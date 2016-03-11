/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * @author Sean Batzel
 */
class EmptyQueueException extends Exception {

// --Commented out by Inspection START (3/11/2016 1:13):
//    /**
//     * Creates a new instance of <code>EmptyQueueException</code> without detail
//     * message.
//     */
//    public EmptyQueueException() {
//    }
// --Commented out by Inspection STOP (3/11/2016 1:13)

    /**
     * Constructs an instance of <code>EmptyQueueException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    @SuppressWarnings("unused")
    public EmptyQueueException(String msg) {
        super(msg);
    }
}
