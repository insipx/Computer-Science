/** Implements the TwoDimMatrix interface using a one-dimensional array to
**  store the matrix's elements.
*/

public class TwoDimMatrixViaArray<T> implements TwoDimMatrix<T> {

   // Constructors:
   // -------------

   /** Initializes the matrix to have the row and column index ranges
   **  specified by the arguments.
   */
   public TwoDimMatrixViaArray(int rowFirst, int rowLast,
                               int colFirst, int colLast)
   {
      // STUB!
   }


   /** Initializes the matrix to have the specified number of rows and
   **  columns, with each index range beginning at at zero.
   */
   public TwoDimMatrixViaArray(int numRows, int numCols)
   {
      // STUB!
   }


   // Observers:
   // ----------

   /** Returns the size of the matrix (i.e., the number of cells)
   public int sizeOf() { return 0; }  // STUB!

   /** Returns the lower bound of the row index range. */
   public int rowFirst() { return 0; }  // STUB!

   /** Returns the upper bound of row index range. */
   public int rowLast()  { return 0; }  // STUB!

   /** Returns the number of rows. */
   public int numRows() { return 0; }   // STUB!

   /** Returns the lower bound of the column index range. */
   public int colFirst() { return 0; }  // STUB!

   /** Returns the upper bound of the column index range. */
   public int colLast() { return 0; }  // STUB!

   /** Returns the number of columns. */
   public int numCols() { return 0; }  // STUB

   /** Returns the item in the cell with the specified index (row, col). */
   public T get(int row, int col) { return null; }  // STUB!


   // Mutator:
   // --------

   /** Places the specified object (item) in the cell with the specified
   **  index (row, col).
   */
   public void put(int row, int col, T item) { }  // STUB!

}
