import java.util.Scanner;

/* Java application having as its purpose to test the MultChoiceTestScorer
** class.  The user is prompted to enter the test's answer key, after which
** (s)he is prompted to enter each student's answers.  After each student's
** answers are entered, the current state of the MultChoiceTestScorer object is
** displayed, in effect, by calling the observers and printing the values
** returned.
*/

public class MultChoiceTestScorerTester {

   // Global variables referring to a Scanner and to the 
   // MultipleChoiceTestScorer object under test.
   private static Scanner input = new Scanner(System.in);
   private static MultChoiceTestScorer scorer;

   public static void main(String[] args)
   {


      String stuAnswerPrompt = "Enter student's answers (null string to quit):";

      String answerKey = getStringFromUser("Enter answer key:");

      scorer = new MultChoiceTestScorer(answerKey);

      System.out.println("Answer key is " + scorer.answerKey()); 
      System.out.println("# of problems on test is " + scorer.numProblems());

      // Get 0-th student's answers.
      String stuAnswers = getStringFromUser(stuAnswerPrompt);

      while (stuAnswers.length() != 0)
      {
         scorer.scoreTest(stuAnswers);  // score the test

         // Print info describing the state of the scorer object.
         printNumTestsScored();
         printRawScores();     // prints scores of all scored tests
         printCorrectCounts(); // prints distribution of correct answers
         printAverage();       // prints average raw score of scored tests
         printStdDev();        // prints standard deviation of scored tests

         // Remind user of answer key.
         System.out.println("\nAnswer key is " + scorer.answerKey()); 

         // Get next student's answers (empty string to quit).
         stuAnswers = getStringFromUser(stuAnswerPrompt);
      }
      System.out.println("Goodbye.");
   }


   /* Prints the number of tests that have been scored.
   */
   private static void printNumTestsScored()
   {
         System.out.println("# of tests scored is " + scorer.numTestsScored());
   }

   /* Prints the raw scores of all scored tests.
   */
   private static void printRawScores()
   {
      for (int i=0; i != scorer.numTestsScored(); i = i+1) {
         System.out.println("Student #" + i + " scored " + scorer.rawScore(i));
      }
   }

   /* Prints the distribution of correct answers.
   */
   private static void printCorrectCounts()
   {
      for (int i=0; i != scorer.numProblems(); i = i+1) {
         System.out.println("Problem #" + i + " answered correctly " + 
                            scorer.numCorrect(i) + " times.");
      }
   }

   /* Prints the average raw score of already-scored tests.
   */
   private static void printAverage()
   {
      System.out.println("Average raw score on scored tests is " +
                         scorer.averageRawScore());
   }

   /* Prints the standard deviation of the raw scores of already-scored tests.
   */
   private static void printStdDev()
   {
      System.out.println("Standard Deviation of raw scores is " +
                         scorer.stdDevOfRawScores());
   }


   /* Displays the specified prompt and returns the String entered in
   ** response by the user.
   */
   private static String getStringFromUser(String prompt)
   {
      System.out.print(prompt);
      return input.nextLine();
   }

}
