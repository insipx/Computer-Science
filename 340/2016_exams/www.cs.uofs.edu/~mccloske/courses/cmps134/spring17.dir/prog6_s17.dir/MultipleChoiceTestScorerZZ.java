import java.util.Arrays;

/* An instance of this class is for the purpose of scoring a multiple choice
** test taken by a group of students.
**
** The test's answer key is established when an object is created, using
** a parameter passed to the constructor.
**
** The scoreTest() method scores a student's test, given, via parameters,
** the student's ID (a String) and the answers that the student provided
** when taking the test.
**
** Among the questions that can be answered (via calls to observers) 
** about scored tests are these:
**
**   --How many students' tests have been scored?
**   --How many students answered the k-th question correctly?
**   --What raw score was achieved by a particular student?
**   --What is the average of the students' raw scores?
**   --What is the standard deviation of the students' raw scores?
**
** By "raw score" is meant the number of questions answered correctly.
*/
public class MultipleChoiceTestScorer {

   // class constant
   // --------------

   // An approximation to the # of students expected to take the test.
   private final int NUM_STUDENTS_DEFAULT = 20;

   // instance constant
   // -----------------

   // ANSWER_KEY.charAt(k) is the correct answer for problem k.
   private final String ANSWER_KEY; 

   // instance variables
   // ------------------
   private int numTestsScored ; // # of students whose tests were scored
   private String[] stuIDs;     // stuIDs[k] = ID of the k-th student
   private int[] rawScores;     // rawScores[k] = # questions answered correctly
                                // by the k-th student
   private int[] correctCounts; // correctCounts[k] = # of students who answered
                                // question k correctly

   // constructor
   // -----------

   /* Initializes a test scorer object that can score a test having as its
   ** answer key the String provided via the parameter.
   */
   public MultipleChoiceTestScorer(String answerKey)
   {
      ANSWER_KEY = answerKey;
      correctCounts = new int[answerKey.length()];
      numTestsScored = 0;
      stuIDs = new String[NUM_STUDENTS_DEFAULT];
      rawScores = new int[NUM_STUDENTS_DEFAULT];
   }


   // observers
   // ---------

   /* Returns the answer key of the test.
   */
   public String answerKey() { return ""; }    // STUB!


   /* Returns the # of questions on the test.
   */
   public int numQuestions() { return -1; }    // STUB!


   /* Returns the number of students whose tests have been scored.
   */
   public int numTestsScored() { return numTestsScored; }


   /* Returns the raw score of the student whose ID matches the given
   ** String, or -1 if none of the scored tests is associated to a student
   ** with that ID.
   */
   public int getRawScore(String studentId)
   { 
      int k = indexOf(studentId);

      // RWM: Now make use of k and the rawScores[] array to
      //      return the appropriate value.

      return -1;  // STUB!
   }


   /* Returns the average of the raw scores of the scored tests.
   ** pre-condition: numTestsScored() > 0
   */
   public double averageRawScore()
   {
      return -1.0;      // STUB!
   }


   /* Returns the standard deviation of the raw scores of the scored tests.
   ** pre-condition: numTestsScored() > 0
   */
   public double stdDevOfRawScores()
   {
      return -1.0;      // STUB!
   }


   /* Returns the number of students who answered question k correctly.
   ** pre-condition: 0 <= k < numQuestions()
   */
   public int getNumCorrect(int k) { return correctCounts[k]; }


   // mutator
   // -------

   /* Given, via parameters, a student's ID and her/his answers on a 
   ** test paper, scores that test.
   ** pre-condition: stuAnswers.length == numQuestions() &&
   **                getRawScore(studentId) == -1
   */
   public void scoreTest(String studentId, String stuAnswers)
   {
      // In case the rawScores[] and stuIDs[] arrays are "full",
      // effectively double their lengths.
      if (numTestsScored == rawScores.length) {
         rawScores = Arrays.copyOf(rawScores, 2 * rawScores.length);
         stuIDs = Arrays.copyOf(stuIDs, 2 * stuIDs.length);
      }

      //RWM: Keep the if-statement above and supply the rest of the code
      //     needed below.  A character-by-character comparison between
      //     ANSWER_KEY and stuAnswers (the formal parameter) is needed in
      //     order to compute the student's raw score (which should be placed
      //     into the rawScores[] array) and to increment the appropriate
      //     elements of the correctCounts[] array.  Of course, the studentId
      //     should be placed into the stuIDs[] array.

      // STUB!

   }


   // private methods
   // ---------------

   /* Returns k such that stuIDs[k].equals(s), or -1 if no such k exists.
   */
   private int indexOf(String s) {
      final int N = numTestsScored();
      int i = 0;
      while (i != N  &&  !stuIDs[i].equals(s)) { 
         i++;
      }
      if (i == N) 
         { return -1; }
      else 
         { return i; }
   }

}
