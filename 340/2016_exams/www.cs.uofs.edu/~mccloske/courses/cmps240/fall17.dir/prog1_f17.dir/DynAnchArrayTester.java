import java.util.Random;
import java.util.Iterator;

public class DynAnchArrayTester {

   /* Uses the provided command line arguments as data items to be placed into a
   ** DynAnchArray object, whose lower index bound is chosen somewhat randomly.
   */
   public static void main(String[] args) {
      final int N = args.length;
      Random rand = new Random(); 
      // Choose lower bound so that it's between -3(N/2) and 3N/2
      int lowerBound = (rand.nextInt(N) - (N / 2)) * (rand.nextInt(3)+1);

      // Create a DynAnchArray daa
      DynAnchArray<String> daa = new DynAnchArray<String>(lowerBound, lowerBound+N);

      // Place each command line argument into a pseudo-randomly chosen.
      // position in daa.
      for (int i = 0; i < N; i++) {
         // Find an unoccupied position in daa at which to place args[i].
         int k = rand.nextInt(N);
         while (daa.get(lowerBound + k) != null) {
            k = (k+1) % N;
         }
         // Place args[i] into position lowerBound+k.
         daa.put(lowerBound+k, args[i]);
      }

      // Having placed the command line arguments into daa, display its
      // contents.
      System.out.println("Contents of a DynAnchArray as provided by " +
                         "its toString() method:\n");
      System.out.println(daa);  // Implicitly uses daa.toString()

      // An alternative way to display the contents of daa:
      //for (int i = daa.indexLowerBound(); i != daa.indexUpperBound(); i++) {
      //   System.out.println(i + ": " + daa.get(i));
      //}

      // Now employ an iterator to access (and display) each element of daa.
      System.out.println("Contents of DynAnchArray as provided by an Iterator:\n");
      Iterator<String> it = daa.iterator();
      while (it.hasNext()) {
         System.out.println(it.next());
      }

      System.out.println("\nGoodbye.");
   }

}
