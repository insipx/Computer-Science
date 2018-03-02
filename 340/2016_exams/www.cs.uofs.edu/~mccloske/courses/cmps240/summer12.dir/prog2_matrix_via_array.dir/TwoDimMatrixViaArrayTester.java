/* Java application having as its purpose to test the TwoDimMatrixViaArray
** class.
*/
public class TwoDimMatrixViaArrayTester {

   /** Creates a matrix with the row and column index ranges specified by
   **  the expected four command line arguments, fills the matrix with 
   **  Integer values 0, 1, 2, etc., etc., and then retrieves them,
   **  verifying that each retrieved value is correct.
   */
   public static void main(String[] args)
   {
      boolean errorObserved = false;

      int rowBeg = Integer.parseInt(args[0]); 
      int rowEnd = Integer.parseInt(args[1]);
      int colBeg = Integer.parseInt(args[2]); 
      int colEnd = Integer.parseInt(args[3]);

      TwoDimMatrix<Integer> m =
         new TwoDimMatrixViaArray<Integer>(rowBeg, rowEnd, colBeg, colEnd);

      print("Specified row index range: " + rowBeg + ".." + rowEnd + "\n");
      print("Actual row index range:    " + 
            m.rowFirst() + ".." + m.rowLast() + "\n");
      if (rowBeg != m.rowFirst()  ||  rowEnd != m.rowLast()) {
         print("*** Mismatch!! ***\n");
         errorObserved = true;
      }

      print("\n");
      print("Specified col index range: " + colBeg + ".." + colEnd + "\n");
      print("Actual col index range:    " + m.colFirst() + ".." + 
             m.colLast() + "\n");
      if (colBeg != m.colFirst()  ||  colEnd != m.colLast()) {
         print("*** Mismatch!! ***\n");
         errorObserved = true;
      }

      // Note that if rowBeg > rowEnd, it means that there are no rows!
      int rowCount = Math.max(0, rowEnd - rowBeg + 1);

      // Note that if colBeg > colEnd, it means that there are no cols!
      int colCount = Math.max(0, colEnd - colBeg + 1);

      int specSize = rowCount * colCount;

      print("\n");
      print("Specified number of rows: " + rowCount + "\n");
      print("Actual number of rows:    " + m.numRows() + "\n");
      if (rowCount != m.numRows()) {
         print("*** Mismatch!! ***\n");
         errorObserved = true;
      }
     
      print("\n");
      print("Specified number of cols: " + colCount + "\n");
      print("Actual number of cols:    " + m.numCols() + "\n");
      if (colCount != m.numCols()) {
         print("*** Mismatch!! ***\n");
         errorObserved = true;
      }
     
      print("\n");
      print("Specified size:" + specSize + "\n");
      print("Actual size:   " + m.sizeOf()+ "\n");
      if (specSize != m.sizeOf()) {
         print("*** Mismatch!! ***\n");
         errorObserved = true;
      }
 
      print("\n"); 

      // Fill matrix's cells with values 0, 1, ...
      int k = 0;
      for (int i = m.rowFirst(); i <= m.rowLast(); i++) {
         for (int j = m.colFirst(); j <= m.colLast(); j++) {
            try {
               m.put(i,j,k);
            }
            catch (ArrayIndexOutOfBoundsException e) {
               print("ArrayIndexOutOfBoundsException thrown upon attempt " +
                     "to put element (" + i + "," + j + ")\n");
               errorObserved = true;
            }
            catch (IndexOutOfBoundsException e) {
               print("IndexOutOfBoundsException thrown upon attempt " +
                     "to put element (" + i + "," + j + ")\n");
               errorObserved = true;
            }
            k = k+1;
         }
      }

      print("\n");
      print("Done filling matrix with values 0 .. " + k + "\n");

      print("\n");
      print("Now verifying values in matrix match those put into it....\n");

      // Now verify that the values in the cells are 0, 1, ...
      k = 0;
      for (int i = m.rowFirst(); i <= m.rowLast(); i++) {
         for (int j = m.colFirst(); j <= m.colLast(); j++) {
            try {
               int r = m.get(i,j);
               if (r != k) {
                  print("element (" + i + "," + j + ") has value " + r +
                        " but should have value " + k + "\n");
                  errorObserved = true;
               }
            }
            catch (ArrayIndexOutOfBoundsException e) {
               print("ArrayIndexOutOfBoundsException thrown upon attempt " +
                     "to retrieve element (" + i + "," + j + ")\n");
               errorObserved = true;
            }
            catch (IndexOutOfBoundsException e) {
               print("IndexOutOfBoundsException thrown upon attempt " +
                     "to retrieve element (" + i + "," + j + ")\n");
               errorObserved = true;
            }
            k = k+1;
         }
      }

      print("\n");
      if (!errorObserved) 
         { print("No errors observed.\n"); }
      else 
         { print("At least one error observed.\n"); }
   }


   private static void print(String s) { System.out.print(s); }

}
