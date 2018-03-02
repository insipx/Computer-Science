/** An instance of an implementing class is intended to represent a
**  two-dimensional matrix (containing items of reference type T (the
**  generic type parameter)) each cell of which is indexed by a pair
**  of integers (i,j), where i is drawn from the row index range 
**  (rowFirst()..rowLast()) and j is drawn from the column index range
**  (colFirst()..colLast()).
*/

public interface TwoDimMatrix<T> {

   // Observers:
   // ----------

   /** Returns the size of the matrix (i.e., the number of cells)
   */
   int sizeOf();

   /** Returns the lower bound of the row index range. */
   int rowFirst();

   /** Returns the upper bound of row index range. */
   int rowLast();

   /** Returns the number of rows. */
   int numRows();

   /** Returns the lower bound of the column index range. */
   int colFirst();

   /** Returns the upper bound of the column index range. */
   int colLast();

   /** Returns the number of columns. */
   int numCols();

   /** Returns the item in the cell with the specified index (row, col). */
   T get(int row, int col);


   // Mutator:
   // --------

   /** Places the specified object (item) in the cell with the specified
   **  index (row, col).
   */
   void put(int row, int col, T item);

}
