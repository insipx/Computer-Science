import java.util.Arrays;

/* An instance of this class is for the purpose of scoring a multiple choice
** test taken by a group of students.
**
** The test's answer key is established via a parameter passed to the
** constructor.
**
** The scoreTest() method scores one student's test, given the student's
** answers (via a parameter).
**
** Among the questions that can be answered (via calls to observers) are these:
**
**   --How many students' tests have been scored already?
**   --Among the tests already scored, on how many was problem k answered
**     correctly?
**   --What raw score was achieved on the k-th test scored?
**   --What is the average of the raw scores of the tests already scored?
**   --What is the standard deviation of the raw scores of the tests already
**     scored?
**
** Author: R. McCloskey and < student's name!!! >
** Solution to Prog. Assg. #7, CMPS 134 Spring 2016
** Date: May 2016
** Aided by: ...
** Known flaws: ...
** 
*/
public class MultChoiceTestScorer {

   // class constant
   // --------------

   // An approximation to the # of students expected to take the test.
   private final int NUM_STUDENTS_DEFAULT = 20;

   // instance constant
   // -----------------

   // ANSWER_KEY.charAt(k) is correct answer for problem #k
   private final String ANSWER_KEY;


   //raw scores :    index = test, problems answered correctly
   //correctCount:   index=problem, tests
   // instance variables
   // ------------------
   private int numTestsScored; // # of tests already scored
   private int[] rawScores;    // rawScores[k] = # problems answered correctly
                               // on the k-th test scored
   private int[] correctCount; // correctCount[k] is # of already-scored tests
                               // in which problem k was answered correctly

                                 ///a list of problems. Problems are the index a[i]
                                 //the number of tests
                                 //Problem:test#correct
                                 //
                                 //rawscores:
                                 //test:problems
                                 //k == a specific test a student took
                                 //whatever rawscores[k] returns is the # of problems that specific test taken
                                 //by that specific student got right

   // constructor
   // -----------

   /* Initializes a test scorer object that can score a test having as its
   ** answer key the String provided via the parameter.
   */
   public MultChoiceTestScorer(String correctAnswers)
   {
      ANSWER_KEY = correctAnswers;
      final int N = correctAnswers.length();
      correctCount = new int[N];
      numTestsScored = 0;
      rawScores = new int[NUM_STUDENTS_DEFAULT];
   }
   // observers
   // ---------

   /* Returns the answer key of the test.
   */
   public String answerKey() { return ANSWER_KEY; }


   /* Returns the # of problems on the test.
   */
   public int numProblems() { return correctCount.length; }


   /* Returns the number of tests that have been scored already.
   */
   public int numTestsScored() { return numTestsScored; }


   /* Returns the raw score of the k-th test scored (counting from zero)
   ** pre-condition: 0 <= k < numTestsScored()
   */
   public int rawScore(int k) { return rawScores[k]; }


   /* Returns the average of the raw scores of the already-scored tests.
   ** pre-condition: numTestsScored() > 0
   */
   public double averageRawScore()
   {
      int sum = 0;
      for(int i = 0; i < rawScores.length;i++){
         sum+=rawScores[i];
      }
      return sum/numTestsScored;
   }


   /* Returns the standard deviation of the raw scores of the already-scored
   ** tests.
   ** pre-condition: numTestsScored() > 0
   */
   public double stdDevOfRawScores() {
      double average = averageRawScore();
      int[] rawScoresCpy = rawScores;

      for (int i = 0; i < rawScoresCpy.length; i++) {
         rawScoresCpy[i] = rawScoresCpy[i] - (int) average;
         rawScoresCpy[i] = rawScoresCpy[i] * rawScoresCpy[i];
      }
      int sum = 0;
      for (int i = 0; i < rawScoresCpy.length; i++) {
         sum += rawScoresCpy[i];
      }
      double average2 = sum / rawScoresCpy.length;
      return Math.sqrt(average2);
   }


   /* Returns the number of already-scored tests in which problem #k 
   ** was answered correctly.  (Problems are numbered starting at zero.)
   ** pre-condition: 0 <= k < numProblems()
   */
   public int numCorrect(int k) { return correctCount[k]; }


   // mutator
   // -------

   /* Given, via the parameter, the answers on a student's test paper,
   ** scores that test (and records information regarding the raw score
   ** and which problems were answered correctly).
   ** pre-condition: stuAnswers.length == numProblems()  &&
   **                numTestsScored() < maxNumberOfStudents()
   */
   public void scoreTest(String stuAnswers)
   {
      // In case the rawScores[] array is "full", double its length
      if (numTestsScored == rawScores.length) {
         rawScores = Arrays.copyOf(rawScores, 2 * rawScores.length);
      }
         int numCrct = 0;
      for(int i = 0; i < ANSWER_KEY.length(); i++){
         if(stuAnswers.charAt(i) == ANSWER_KEY.charAt(i)){
            rawScores[i]++;
         }
         else{

         }
      }


      //RWM: Keep the if-statement above and supply the rest of the
      //     code needed here.  A character-by-character comparison between
      //     ANSWER_KEY and stuAnswers (the formal parameter) is needed in
      //     order to compute the raw score (which should be placed into
      //     the rawScores[] array) and to appropriately update the elements
      //     of the correctCount[] array.

      // STUB!!

   }

}
