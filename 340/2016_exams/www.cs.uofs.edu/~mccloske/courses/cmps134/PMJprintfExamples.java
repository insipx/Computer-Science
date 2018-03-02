import java.util.Scanner;

public class PMJprintfExamples   {	

   public static void main( String [] args ) {
      Scanner iso = new Scanner(System.in);
      double someNumber;
      System.out.println("PMJ's printfExamples ...");
      System.out.print("Enter a real number:");
      someNumber = iso.nextDouble();
      System.out.println("         1         2         3  ");
      System.out.println("12345678901234567890123456789012");
      System.out.println("--------------------------------");
      System.out.printf("%10.0f\n",someNumber);
      System.out.printf("%10.1f\n",someNumber);
      System.out.printf("%10.2f\n",someNumber);
      System.out.printf("%10.3f\n",someNumber);
      System.out.printf("%10.4f\n",someNumber);
      System.out.printf("%10.5f\n",someNumber);
      System.out.printf("%20.0f\n",someNumber);
      System.out.printf("%20.1f\n",someNumber);
      System.out.printf("%20.2f\n",someNumber);
      System.out.printf("%30.3f\n",someNumber);
      System.out.println("Done!!!");
   }
   
}
