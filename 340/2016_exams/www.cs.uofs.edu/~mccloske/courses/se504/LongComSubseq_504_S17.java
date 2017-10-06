/* An instance of this class represents a solution to one particular instance
** of the Longest Common Subsequence Problem, which is the problem of 
** determining, for two given sequences (here, Java Strings), a longest
** common subsequence (LCS).  (A subsequence of a sequence is obtained by 
** "erasing" zero of more of the elements in the sequence.)
**
** A restricted variant of the problem is to determine only the length of
** a LCS.  Generalized variants are to determine 
** (i) the set of all LCS's,
** (ii) the number of different ways of forming an LCS,
** (iii) the set of all ways of forming an LCS.
**
** Regarding (ii) and (iii), by a "way of forming an LCS", we mean a
** list of index pairs <(i_1,j_1), (i_2,j_2), ..., (i_n,j_n)>, where n is
** the length of an LCS, such that i_1 < i_2 < ... i_n and likewise for
** the j_k's, and such that x[i_k] = y[j_k] for all k, where x and y are
** the sequences of interest.
** Thus, (ii) is just the cardinality of (iii).
**
** Author: R. McCloskey, 2015 (modified in May 2017 for purposes of SE 504 HW
**         problem)
*/

public class LongComSubseq_504_S17 {

   private String x, y;    // the sequences of interest
   private int M, N;       // their lengths
   private int[][] llcs;   // 2-dimensional array storing values of the 
                           // LLCS function.

   /* Creates a solution to the problem described by the two parameters.
   */
   public LongComSubseq_504_S17(String s1, String s2)
   {
      x = s1;  y = s2;
      makeTable();
   }

   /* Constructs and fills llcs[][] so that, for all i in 0..M and j in 0..N
   ** (where M and N are the lengths of x and y), llcs[i][j] = LLCS.i.j, 
   ** which is to say that, upon completion of the method's execution, the 
   ** value of llcs[i][j] is the length of a longest common subsequence of 
   ** x[0..i) and y[0..j).
   */
   private void makeTable()
   {
      M = x.length();
      N = y.length();
      llcs = new int[M+1][N+1];

      // fill row zero and column zero with zeros
      for (int j=0; j != N+1; j++) { llcs[0][j] = 0; }
      for (int i=0; i != M+1; i++) { llcs[i][0] = 0; }
      
      int m=1, n=1;
      // loop invariant: (A i,j | 0<=i<m & 0<=j<=N : llcs[i][j] = LLCS.i.j) &
      //                 (A j | 0<=j<n : llcs[m][j] = LLCS.m.j) & 
      //                 1<=m<=M+1 & 0<=n<=N+1
      // bound function: (M+1)*(N+2) - (m*(N+2) + n). 
      while (m != M+1) 
      {
         if (n == N+1) {
            m = m+1; n = 0;  // row m now complete; go to next row
         }
         else if (x.charAt(m-1) == y.charAt(n-1)) {
            llcs[m][n] = llcs[m-1][n-1] + 1;
         }
         else {
            llcs[m][n] = Math.max(llcs[m][n-1], llcs[m-1][n]);
            //if (llcs[m][n] == llcs[m-1][n-1] + 1) {
            //   System.out.println("warning at " + m + "," + n); 
            //} 
         }
         n = n+1;
      }
   }

   /** Returns a longest common subsequence of x and y.
   */
   public String longestCommonSubsequence() 
   {
      return "";  // STUB!
   }

   /** Returns the number of distinct "common subsequence index sequences"
   **  that describe ways of forming an LCS of x and y.
   **  That is, it returns NLCS.M.N.
   */
   public int numWaysToFormLCS() 
   {
      return 1;  // STUB!
   }


   /* Prints the values of the LLCS function in a table format.
   */
   public void printLLCSTable()
   {
      for (int i=M; i!=-1; i--) {
         System.out.printf("%3d", i);
         System.out.print("|");
         for (int j=0; j != N+1; j++) {
            System.out.printf("%3d", llcs[i][j]);
         }
         System.out.println();
      }
      System.out.print("   +");
      printString("---", N+1);
      System.out.println();
      System.out.print("    ");
      for (int j=0; j != N+1; j++) {
         System.out.printf("%3d", j);
      }
      System.out.println(); 
   }


   /** Prints the specified string the specified # of times
   */
   private void printString(String str, int k)
   {
      for (int j=0; j != k; j++) {
         System.out.print(str);
      }
   }

   /** Verifies that the given string is a subsequence of both x and y.
   */
   public boolean isCommonSubsequence(String s)
   {
      return isSubsequenceOf(s,x) && isSubsequenceOf(s,y);
   }

   /** Reports whether or not s is a subsequence of v.
   */
   private boolean isSubsequenceOf(String s, String v)
   {
      int i=0, j=0;
      // loop invariant: s[0..i) is the longest prefix of s that is
      //                 a subsequence of v[0..j).
      while (i != s.length()  &&  j != v.length()) 
      {
         if (s.charAt(i) == v.charAt(j)) { i = i+1; } 
         j = j+1;
      }
      return i == s.length();
   }


   /* Solves the instance of the LCS problem described by two Strings
   ** provided via command line arguments.
   */
   public static void main(String[] args)
   {
      LongComSubseq_504_S17 lcs;
      //LongestCommonSubsequence lcs;
      lcs = new LongComSubseq_504_S17(args[0], args[1]);
      //lcs = new LongestCommonSubsequence(args[0], args[1]);
      System.out.println("LLCS table for " + args[0] + " and " + 
                         args[1] + ": \n\n");
      lcs.printLLCSTable();
      System.out.println();
      String lcsStr = lcs.longestCommonSubsequence();
      System.out.println("A longest common subsequence is " + lcsStr);
      if (!lcs.isCommonSubsequence(lcsStr)) {
         System.out.println("** Error: Not a common subsequence!! **");
      }
   }
}
