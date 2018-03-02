/* MCTS_Tester.java
** Java application having as its purpose to act as a tester for the
** MultipleChoiceTestScorer class.
**
** Author: R. McCloskey, April 2017
*/

public class MCTS_Tester {

   public static void main(String[] args)
   {
      // Establish a MultipleChoiceTester object.
      String key = "abadcadcdb";  // Answer key.
      MultipleChoiceTestScorer mcts = new MultipleChoiceTestScorer(key);

      // Create arrays containing student IDs (names) and their answers
      // on the test.
      String[] stuNames = { "Marie", "Joan", "Bill", "Mortimer", "Carol" };
      //String[] stuAns = {"caababaaab", "abdedabada", "bbabdebaba",
      //                   "aabbbaedba", "abadcadcdb" };
      String[] stuAns = {"abedaadcdc", "acadcbdada", "bcadcadceb",
                         "aabbbaedba", "abadcadcdb" };

      // Score each student's test, then retrieve and report the raw score,
      // plus the updated average and standard deviation.
      for (int i=0; i != stuAns.length; i++) {
         mcts.scoreTest(stuNames[i], stuAns[i]);
         System.out.println(stuNames[i] + " scored " + 
                            mcts.getRawScore(stuNames[i]));
         System.out.println("Average so far: " + mcts.averageRawScore());
         System.out.println("Standard Deviation so far: " + 
                            mcts.stdDevOfRawScores());
      }
      System.out.println();

      // Report, for each question, how many students answered it correctly.
      for (int i=0; i != mcts.numQuestions(); i++) {
         System.out.println(mcts.getNumCorrect(i) + 
                            " correct answers on Question " + i);
      }
      System.out.println();

      // Retrieve and report each student's raw score.
      for (String s : stuNames) {
         System.out.println("Retrieved score of " + s + " is " +
                            mcts.getRawScore(s));
      }
      System.out.println();

      // Now try to retrieve score of a student who didn't even take the test! 
      System.out.println("Score of Ralph:" + mcts.getRawScore("Ralph"));
      
      System.out.println("\nGoodbye");
   }

}
