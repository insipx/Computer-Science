import java.util.Scanner;
import java.io.*;

class FSMLA {
 /*	An extended version of FSM that recognizes "augmentations" in transitions and then outputs them.
		Such augmentations are encolosed in braces, such as {something}.
      
      This version was enhanced 20150228 to provide for a Symbol Table
      
      Copyright 2016 Paul M. Jackowitz
 */
 
   static String fidPrefix;                        //PMJ, 20160227
   
   static final char DELIMITER = '\t';              //PMJ,2016 COMMA changed to DELIMTIER //PMJ,20150305
   final static int LIMIT = 512;
   static String Inputs[] = new String[LIMIT];		// An array of Strings
   static String States[] = new String[LIMIT];
   static int Width = 0;
   static int Widths[] = new int[LIMIT];
   static int Table[][] = new int[LIMIT][LIMIT];
   static String Augmentation[][] = new String[LIMIT][LIMIT];
   final static String TOKEN_NL = "0";             //PMJ,20150406
   final static String NL = "\n";
   final static String BLANK = " ";
	
   //Symbol Table                                  //PMJ,20150228
   static String[] stString = new String[LIMIT];   //PMJ,20150228
   static int stNext = 1;                          //PMJ,20150406
   static String lexeme = "";                      //PMJ,20150228
   
   static int rows,cols;

   public static int MaxWidth(int Previous, String S) {
      int Length;
      Length = S.length();
      if(Length > Previous) {
         return Length;
      } 
      else {
         return Previous;
      }
   }

   public static int ReadInputs(Scanner Input) {
      int result = 0;
      String S;
      int where;
      S = Input.nextLine() + DELIMITER;
      if(S.indexOf(DELIMITER) == 0) {
         S = S.substring(1);
         while (S.length() > 0) {
            Widths[result] = 0;
            result = result + 1;
            where = S.indexOf(DELIMITER);
            Inputs[result-1] = S.substring(0,where);
            Widths[result-1] = MaxWidth(Widths[result-1],Inputs[result-1]);
            S = S.substring(where+1);
         }
         Inputs[result-1] = NL;      //PMJ Spring 2016!
      } 
      else {
         System.out.println("---Error: (ReadInputs) ");
      }
      return result;
   
   }

   public static int ReadTable(Scanner Input, int limit) {
      String S,Target;
      int rows=0;
      int i,j,k;
      int where,target;
   	//Loop to read the entire table into the States array
      do {
         S = Input.nextLine() + DELIMITER;
         if(S.charAt(0) != DELIMITER) {
            States[rows] = S; 
            rows = rows + 1;
         }
      } while (S.charAt(0) != DELIMITER);
   	//Now the states array is used to create the Table array
      for(i=0;i<rows;i++) {
         S=States[i];
         where = S.indexOf(DELIMITER);
         States[i]=S.substring(0,where+1);	//Just the name of the state
         S=S.substring(where); 
         Width=MaxWidth(Width,States[i]);
         for(j=0;j<limit;j++) {
            S=S.substring(1);						//Stips off the leading comma
            where = S.indexOf(DELIMITER);
            Target=S.substring(0,where+1);	//The name of the state to transition to
            Widths[j]=MaxWidth(Widths[j],Target);
            Augmentation[i][j] = "";
            int start = Target.indexOf("{");
            if(start >= 0) {
               int stop = Target.indexOf("}",start);
               if(stop > start) {
                  Augmentation[i][j] = Target.substring(start+1,stop).trim();
                  Target=Target.substring(0,start).trim() + DELIMITER;
               } 
               else {
                  System.out.println("---ERROR: <ReadTable> invalid augmentation '" + Target + "'"); 
               }
            }
            target=-1;
            for(k=0;k<rows;k++) {
               if((States[k].startsWith(Target))) {
                  if(target<0) {
                     target=k;
                  } 
                  else {
                     target = -2;
                  }
               }
            }
            S=S.substring(where);
            if(target >= 0) {
               Table[i][j] = target;
            } 
            else {
               System.out.println("---Error: (ReadTable) state '" + Target + "' not found");
            }
         }
      }         
      return rows;
   }

   public static void printWidth(String S, int length, int width) {   
      printWidth(S,length,width,' ');
   }

   public static void printWidth(String S, int length, int width, char c) {   
      int w;
      System.out.print(S.substring(0,length));
      w = length;
      while(w < width) {
         System.out.print(c);
         w = w + 1;
      }
   }

   public static void printTable() {
      printWidth(" ",1,Width);
      System.out.print("|| ");
      for(int i=0; i<cols; i++) {
         String actual = Inputs[i];
         if(actual.equals(NL)) { actual = BLANK; }
         printWidth(actual, actual.length(),Widths[i]);
         System.out.print("| ");
      }
      System.out.println();
   
      printWidth("-",1,Width,'-');
      System.out.print("|| ");
      for(int i=0; i<cols; i++) {
         printWidth("-", 1,Widths[i],'-');
         System.out.print("| ");
      }
      System.out.println();
   
      for(int i=0; i<rows; i++) {
         printWidth(States[i],States[i].length()-1,Width);
         System.out.print("|| ");
         for(int j=0;j<cols; j++) {
            if((Augmentation[i][j] != null) && (Augmentation[i][j].length() > 0)) {
             //printWidth(States[Table[i][j]]+"{"+Augmentation[i][j]+"}",
               printWidth((States[Table[i][j]].trim())+"{"+Augmentation[i][j]+"}",
                         (States[Table[i][j]].trim()).length()+Augmentation[i][j].length()+2,Widths[j]);
                      //States[Table[i][j]].length()+Augmentation[i][j].length()+2,Widths[j]);
            } 
            else {
               printWidth(States[Table[i][j]],States[Table[i][j]].length()-1,Widths[j]);
            }
            //printWidth(States[Table[i][j]],States[Table[i][j]].length()-1,Widths[j]);
            System.out.print("| ");
         }
         System.out.println();
      }
      System.out.println();
   
   }

   public static String runMachine(String S, BufferedWriter output) throws IOException {   
      int State = 0;
      int index = 0;
      int input = 0;
      boolean valid = true;
      S = S + DELIMITER;                               //PMJ,20150305
      char DELIMITERC = DELIMITER;
      char NLC = "\n".charAt(0);
      while(valid && (S.charAt(index) != DELIMITERC)) {   // '\n')) { //PMJ,20150305
         char current = S.charAt(index);
      //PMJ2016         if(current == NLC) {                         //PMJ,20150305
      //PMJ2016            current = ' ';                            //PMJ,20150305
      //PMJ2016         }                                            //PMJ,20150305
         if(State == 0) { lexeme = ""; }                          //PMJ,20150228
         lexeme = lexeme + current;                       //PMJ,20150228
         valid = false;
         input = 0;
         while(!valid && (input < cols)) {
            if(Inputs[input].indexOf(current) > -1) {
               valid = true;
            } 
            else {
               input = input + 1;
            }
         }
         if(valid) {
            if((Augmentation[State][input] != null) && (Augmentation[State][input].length() > 0)) {
               doPrint(output, Augmentation[State][input]);  
            }
            State = Table[State][input];
            //System.out.print("<" + States[State] + ">");
         } 
         else {
            System.out.println("---Error: (runMachine) invalid input '" + current + "'");
         }
         if((index > 0) && (S.charAt(index) == NLC)) {                 //PMJ,20150305
            doPrintBreak(output);                     //PMJ,20150305         
         }                                            //PMJ,20150305
         index = index + 1;
      }
      doPrintBreak(output);               
      return (States[State]);
   }

   static String STINDEX   = ";";                                    //PMJ,20150228
   static String STINDEX2  = ":";                                    //PMJ,2016
   static String SEPARATOR = "&";                                    //PMJ,20150228
   
   static void doPrint(BufferedWriter output, String s) throws IOException {
      int index = s.indexOf(SEPARATOR);                              //PMJ,20150228
      if(index > 0) {                                                //PMJ,20150228
         doPrint(output,s.substring(0,index));                       //PMJ,20150228
         if(index < s.length()) {                                    //PMJ,20150228
            doPrint(output,s.substring(index+SEPARATOR.length()));   //PMJ,20150228
         }                                                           //PMJ,20150228
      }                                                              //PMJ,20150228
      else {                                                         //PMJ,20150228
         index = s.indexOf(STINDEX);                                 //PMJ,20150228
         int index2 = s.indexOf(STINDEX2);                           //PMJ,2016
         if(index2 >= 0) {index = index2; }                          //PMJ,2016
         if(index >= 0) {                                            //PMJ,20150228
            String token = s.substring(0,index);                     //PMJ,20150228
            doPrintItem(output,token);                               //PMJ,20150228
            doPrintDelimiter(output);                                //PMJ,20150228
            //System.out.print("<"+lexeme+">");
            int length = lexeme.length();                            //PMJ,20150228
            int extent = length;                                     //PMJ,2016
            if(length > 0) {                                         //PMJ,20150228
               if(index != index2) {extent = length - 1;      }     //PMJ,2016
               String stIndex = "" + getIndexOf(token,lexeme.substring(0,extent)); //PMJ,2016
               //String stIndex = "" + getIndexOf(token,lexeme.substring(0,(length-1))); //PMJ,20150228
            //for(int j=0;j<stNext;j++) { System.out.print(j+"["+stString[j]+"],"); } System.out.println();
               if((index+STINDEX.length()) < s.length()) {           //PMJ,20150228
                  doPrint(output,stIndex);                           //PMJ,20150228
                  doPrintDelimiter(output);                          //PMJ,20150228
                  s = s.substring(index+STINDEX.length());           //PMJ,20150228
               }                                                     //PMJ,20150228
               else {                                                //PMJ,20150228
                  s = stIndex;                                       //PMJ,20150228
               }                                                     //PMJ,20150228
            }                                                        //PMJ,20150228
            lexeme = lexeme.substring(extent);                       //PMJ,2016
            //lexeme = "";                                             //PMJ,20150228
         }                                                           //PMJ,20150228
         doPrintItem(output,s);                                      //PMJ,20150228
         doPrintDelimiter(output);                                   //PMJ,20150228
      }                                                              //PMJ,20150228
   }

   static void doPrintItem(BufferedWriter output, String s) throws IOException { //PMJ,20150228
      if(output == null) {                                                       //PMJ,20150228
         System.out.print(s);                                                    //PMJ,20150228
      }                                                                          //PMJ,20150228
      else {                                                                     //PMJ,20150228
         output.write(s);                                                        //PMJ,20150228
      }                                                                          //PMJ,20150228
   }                                                                             //PMJ,20150228

   static void doPrintDelimiter(BufferedWriter output) throws IOException {   //PMJ,20150228
      if(output == null) {                                                    //PMJ,20150228
         System.out.print(DELIMITER);                                               //PMJ,20150228
      }                                                                       //PMJ,20150228
      else {                                                                  //PMJ,20150228
         output.write(" ");                                                  //PMJ,20150228
      }                                                                       //PMJ,20150228
   }                                                                          //PMJ,20150228

   static void doPrintBreak(BufferedWriter output) throws IOException {       //PMJ,20150228
      if(output != null) {                                                    //PMJ,20150228
         output.write(TOKEN_NL); //PMJ,20150406
         output.write("\n");     //PMJ,20150305  output.write("\n");          //PMJ,20150228
      }                                                                       //PMJ,20150228
   }                                                                          //PMJ,20150228

   static int getIndexOf(String token, String lexeme) {                       //PMJ,20150228
      int result = 0;                                                         //PMJ,20150228
      lexeme = lexeme.replace('\n','\\');                                     //PMJ,2016
      while((result < stNext) && !lexeme.equals(stString[result])) {          //PMJ,20150228
         result = result + 1;                                                 //PMJ,20150228
      }                                                                       //PMJ,20150228
      if(result == stNext) {                                                  //PMJ,20150228
         if(stNext < LIMIT) {                                                 //PMJ,20150228
            stString[result] = lexeme;                                        //PMJ,20150228
            stNext = stNext + 1;                                              //PMJ,20150228
         }                                                                    //PMJ,20150228
         else {                                                               //PMJ,20150228
            System.out.println("---Error: (runMachine) Symbol Table Full");   //PMJ,20150228
         }                                                                    //PMJ,20150228
      }                                                                       //PMJ,20150228
      return result;                                                          //PMJ,20150228
   }                                                                          //PMJ,20150228
   
   public static void main(String[] args) throws FileNotFoundException, IOException {
      Scanner input;
      Scanner Keyboard;
      BufferedWriter output = null;
      
      String InputString = "";
      String Result;			
   
      System.out.println("PMJ's FSMLA 2016.03.24 ...");
      stString[0] = lexeme;                           //PMJ,20150406
      if(args.length > 0) {
         input = new Scanner(new File(args[0]));
         //System.out.println(args.length+DELIMITER+args[0]);
      } 
      else {
         input = new Scanner(System.in);
         System.out.println("Enter Machine:");
      }
      cols = ReadInputs(input);
      //System.out.println("cols="+cols);
      rows = ReadTable(input,cols);
      //////////////////////////////////////
      //System.out.println("The FSM is");
      //printTable();
      //////////////////////////////////////
   
      if(args.length > 1) {
         fidPrefix = args[1].substring(0,indexOfDelimiter(args[1]));
         Keyboard = new Scanner(new File(args[1]));
         //System.out.println(args.length+DELIMITER+args[1]);
      } 
      else {
         Keyboard = new Scanner(System.in);
         System.out.println("Enter Input:");
      }
   
      boolean fileOpened = false;
      if(args.length > 1) {                                                               //PMJ,20160227
         InputString = getEntireFile(Keyboard);                                           //PMJ,20150228
         String fidTKN = fidPrefix+".tkn";
         try {
            output = new BufferedWriter(new FileWriter(new File(fidTKN)));
            fileOpened = true;
         } 
         catch(Exception e) {
            System.out.println("---Error: (main) unable to write TKN output file for '"+fidTKN+"'");
         }
      } 
      if(args.length < 2) { InputString = getNextLine(Keyboard); }                           //PMJ,20150228
      while((InputString != null) && (InputString.length()>0) && (InputString.charAt(0) != '$')) {
         if(args.length < 2) { System.out.print("\""+InputString + "\", "); }
         Result = runMachine(InputString,output);
         System.out.println("Final State of " + Result);
         //System.out.println(" is '" + InputString + "' yields Final State of " + Result);
         if(args.length < 2) {                                                               //PMJ,20120227
            InputString = getNextLine(Keyboard);                                             //PMJ,20150228
         }                                                                                   //PMJ,20150228
         else {                                                                              //PMJ,20150228
            InputString = "";                                                                //PMJ,20150228
         }                                                                                   //PMJ,20150228 
      }
      if(fileOpened) {
         output.close();
      }
      if(args.length > 1) {                                                                  //PMJ,20160227
         //for(int j=0;j<stNext;j++) { System.out.print(j+"["+stString[j]+"],"); } System.out.println();
         String fidIST = fidPrefix+".ist";
         try {                                                                               //PMJ,20150228
            output = new BufferedWriter(new FileWriter(new File(fidIST)));         //PMJ,20160227
            for (int i=1; i<stNext; i++) {                                                   //PMJ,20150228
               //System.out.println(i+":<"+stString[i]+">");                                                   
               //output.write(_JavaSTFiler(stString[i])+"\n");                               //PMJ,20150228
               output.write(stString[i]+"\n");                                               //PMJ,20150228
            }                                                                                //PMJ,20150228
            output.close();                                                                  //PMJ,20150228
         }                                                                                   //PMJ,20150228
         catch(Exception e) {                                                                //PMJ,20150228
            System.out.println("---Error: (main) unable to write IST output file for '"+
                                    fidIST+"'");                                            //PMJ,20150228
         }                                                                                   //PMJ,20150228                                                                 //PMJ,20150228
      }                                                                                      //PMJ,20150228
   }
   
   private static int indexOfDelimiter(String fid) {
      int result = fid.length();
      int possible = fid.indexOf(".");
      if(possible > 0) { result = possible; }
      return result;
   }
   
   /* The following is a specialized filter for reviseing the Symbol Table entires for _Java 
   // -----------------------------------------------------------------------------------------
   static String _JavaSTFiler(String s) {
      final String SLprefix = "\"";     final String SLsuffix = "\""; 
      final String CLprefix = "'";      final String CLsuffix = "'";
      final String Cmt2prefix = "/*";   final String Cmt2suffix = "/";
      final char   Cmt2NL = '\n';       final char   Cmt2replace = '\\';
      if(s.startsWith(SLprefix)) {
         s = s + SLsuffix;
      } 
      else if(s.startsWith(CLprefix)) {
         s = s + CLsuffix;
      } 
      else if(s.startsWith(Cmt2prefix)) {
         s = s + Cmt2suffix;
         s = s.replace(Cmt2NL,Cmt2replace);
      }
      return s;
   }
*/   
   static String getNextLine(Scanner Keyboard) {
      String result = "";
      while((result.length() < 1) && Keyboard.hasNextLine()) {
         result = Keyboard.nextLine();
      }
      result = clean(result) + "\n";    //PMJ,20150305
      return result;             //PMJ,20150305 return result.replace('\t',' ') /*+ " "*/;
   }
   
   static String getEntireFile(Scanner FileInput) {      //PMJ,20150228
      String result = "";                                //PMJ,20150228
      while(FileInput.hasNextLine()) {                   //PMJ,20150228
         result = result + "\n" + FileInput.nextLine();  //PMJ,20150406
      //         result = result + "\n" + FileInput.nextLine();   //PMJ,20150228
      }                                                  //PMJ,20150228
      result = result + "\n";                             //PMJ,20150228
      result = clean(result);    //PMJ,20150305
      return result;             //PMJ,20150305 result.replace('\t',' ') /*+ " "*/;         //PMJ,20150228
   }                                                     //PMJ,20150228
   
   static String clean(String s) {                       //PMJ,20150305
      String result = s;                                 //PMJ,20150305
      result = result.replace('\t',' ');                 //PMJ,20150305
      //result = result.replace('\r',' ');                 //PMJ,20150305
      //result = result.replace('\f',' ');                 //PMJ,20150305
      return result;                                     //PMJ,20150305
   }                                                     //PMJ,20150305
}
