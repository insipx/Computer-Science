import java.io.*;
import java.util.*;

public class RandomTestDriver {

   public static void main(String[] arg)throws IOException{
      
      Integer range = 10;
      RandomTest Test = new RandomTest(range);
   
      for(Integer Repeat = 0; Repeat<5; Repeat++){  
         Test.InitUniform();    
         Test.Uniform(4000);
         Integer[] UResults = Test.GetUniform();
         for(Integer i=0; i<range; i++){
            System.out.print(UResults[i]+"\t");
         }
         System.out.println();
         System.out.println();
      }
      for(Integer Repeat = 0; Repeat<2; Repeat++){  
         Test.InitSerial();    
         Test.Serial(40000);
         Integer[][] SResults = Test.GetSerial();
         for(Integer i=0; i<range; i++){
            for(Integer j=0; j<range; j++){
               System.out.print(SResults[i][j]+"\t");
            }
            System.out.println();
         }
         System.out.println();
      }
   
   }
   
}
