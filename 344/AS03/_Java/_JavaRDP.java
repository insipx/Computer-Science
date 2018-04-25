/* _JavaRDP.java, 2016-03-25
   Copyright 2016 Paul M. Jackowitz
*/
import java.util.Scanner;
import java.io.*;

public class _JavaRDP {

   static final int TOKEN_NL=0;
// --------------------------------------------------
   static final int TOKEN_COMMENT1 = -99;
   static final int TOKEN_COMMENT2 = -98;
// --------------------------------------------------
   static final int TOKEN_ERROR_ID = -19;
   static final int TOKEN_ERROR_EXTRANEOUSCHAR = -18;
   static final int TOKEN_ERROR_REAL = -17;
   static final int TOKEN_ERROR_INTEGER_BOH = -16;
   static final int TOKEN_ERROR_INTEGER_H = -15;
   static final int TOKEN_ERROR_INTEGER_O = -14;
   static final int TOKEN_ERROR_INTEGER_B = -13;
   static final int TOKEN_ERROR_EXTRANEOUS = -12;
   static final int TOKEN_ERROR_CHARACTER = -11;
// --------------------------------------------------
   static final int[] COMMENTS = {TOKEN_COMMENT1,TOKEN_COMMENT2};
   static final int[] ERRORS   = {TOKEN_ERROR_ID, TOKEN_ERROR_EXTRANEOUSCHAR, TOKEN_ERROR_REAL, 
                                  TOKEN_ERROR_INTEGER_BOH, TOKEN_ERROR_INTEGER_H, TOKEN_ERROR_INTEGER_O, 
                                  TOKEN_ERROR_INTEGER_B, TOKEN_ERROR_EXTRANEOUS, TOKEN_ERROR_CHARACTER};
   static final String[] ERRORS_MESSAGES = {
                                  "Malformed Synonymous Identifier",
                                  "Extraneous Character, ~!@#$%^&|*=[]:,./?",
                                  "Malformed Real Literal",
                                  "Malformed Binary, Octal or Hex Integer Literal",
                                  "Malformed Hex Integer Literal",
                                  "Malformed Octal Integer Literal",
                                  "Malformed Binary Integer Literal",
                                  "Extraneous \\",
                                  "Malformed Character Literal"
                                  };
                                           
   static final int TOKEN_ID = -1;
   static final int TOKEN_IDG = -2;
   static final int TOKEN_IDSR = -3;
   static final int TOKEN_IDSD = -4;
   static final int TOKEN_CL = -5;
   static final int TOKEN_SL = -6;
   static final int TOKEN_IL = -7;
   static final int TOKEN_RL = -8;
// --------------------------------------------------
   static final int TOKEN_CLASS = 1;         static final String STRING_CLASS = "class";
   static final int TOKEN_ELSE = 2;          static final String STRING_ELSE = "else";
   static final int TOKEN_EXTENDS = 3;       static final String STRING_EXTENDS = "extends";
   static final int TOKEN_FALSE = 4;         static final String STRING_FALSE = "false";
   static final int TOKEN_FINAL = 5;         static final String STRING_FINAL = "final";
   static final int TOKEN_IF = 6;            static final String STRING_IF = "if";
   static final int TOKEN_IMPORT = 7;        static final String STRING_IMPORT = "import";
   static final int TOKEN_NEW = 8;           static final String STRING_NEW = "new";
   static final int TOKEN_PRIVATE = 9;       static final String STRING_PRIVATE = "private";
   static final int TOKEN_PROGRAM = 10;      static final String STRING_PROGRAM = "program";
   static final int TOKEN_PROTECTED = 11;    static final String STRING_PROTECTED = "protected";
   static final int TOKEN_PUBLIC = 12;       static final String STRING_PUBLIC = "public";
   static final int TOKEN_RETURN = 13;       static final String STRING_RETURN = "return";
   static final int TOKEN_SET = 14;          static final String STRING_SET = "set";
   static final int TOKEN_STATIC = 15;       static final String STRING_STATIC = "static";
   static final int TOKEN_TRUE = 16;         static final String STRING_TRUE = "true";
   static final int TOKEN_WHILE = 17;        static final String STRING_WHILE = "while";
// --------------------------------------------------
   static final int TOKEN_LP = 18;           static final String STRING_LP = "(";
   static final int TOKEN_RP = 19;           static final String STRING_RP = ")";
   static final int TOKEN_LB = 20;           static final String STRING_LB = "{";
   static final int TOKEN_RB = 21;           static final String STRING_RB = "}";
   static final int TOKEN_C = 22;            static final String STRING_C = ",";
   static final int TOKEN_SC = 23;           static final String STRING_SC = ";";
   static final int TOKEN_P = 24;            static final String STRING_P = ".";
// --------------------------------------------------
   static final String[] STRING = {"",STRING_CLASS, STRING_ELSE, STRING_EXTENDS, STRING_FALSE, 
                                   STRING_FINAL, STRING_IF, STRING_IMPORT, STRING_NEW, 
                                   STRING_PRIVATE, STRING_PROGRAM, STRING_PROTECTED, 
                                   STRING_PUBLIC, STRING_RETURN, STRING_SET, STRING_STATIC, 
                                   STRING_TRUE, STRING_WHILE, STRING_LP, STRING_RP, 
                                   STRING_LB, STRING_RB, STRING_C, STRING_SC, STRING_P
                                  };

   static final int TOKEN_LIMIT = 4096;
   static int[] token = new int[TOKEN_LIMIT];
   static int n;
   static final int SOURCE_LIMIT = 512;
   static String[] source = new String[SOURCE_LIMIT];
   static int sourceN;
   static int[] tokenLine = new int[TOKEN_LIMIT];
   static final int IST_LIMIT = 1024;
   static String[] ist = new String[IST_LIMIT];
   static int istN;

   static boolean rdpDebug = false;
   static boolean echoDebug = false;
   static boolean charDebug = false;
   static int level = 0;
   
   static final String EXTENSION_SOURCE = "._java";
   static final String EXTENSION_TOKENS = ".tkn";
   static final String EXTENSION_IST = ".ist";
   static final String EXTENSION_LISTING = ".lst";
   static final String EXTENSION_CODE = ".java";
   
   static BufferedWriter code;

///////////////////////////////////////////////////////////////////////////////////////////////////
// main and preliminaries
///////////////////////////////////////////////////////////////////////////////////////////////////
  
   private static int indexOfDelimiter(String fid) {
      int result = fid.length();
      int possible = fid.indexOf(".");
      if(possible > 0) { result = possible; }
      return result;
   }

   public static void main(String[] args) throws FileNotFoundException, IOException {
      System.out.println("PMJ's _JavaRDP 2016.03.25 ...");
      Scanner tokenInput;
      Scanner sourceInput;
      Scanner istInput;
      if(args.length > 0) {
         args[0] = args[0].substring(0,indexOfDelimiter(args[0]));
         tokenInput = new Scanner(new File(args[0]+EXTENSION_TOKENS.trim()));
         sourceInput = new Scanner(new File(args[0]+EXTENSION_SOURCE.trim()));
         istInput = new Scanner(new File(args[0]+EXTENSION_IST.trim()));
         n = readInput(tokenInput,token);
         sourceN = readSourceInput(sourceInput,source);
         istN = readInternalSymbolTable(istInput,ist);
      
      //GENERATE         try {
      //GENERATE            code = new BufferedWriter(new FileWriter(new File(args[0]+EXTENSION_CODE)));
      //GENERATE         } 
      //GENERATE         catch(Exception e) {
      //GENERATE            System.out.println("---Error: unable to create output file'"+args[0]+EXTENSION_CODE+"'");
      //GENERATE         }
      
         if(args.length > 1) {
            rdpDebug = Boolean.parseBoolean(args[1]);
            if(args.length > 2) {
               echoDebug = Boolean.parseBoolean(args[2]);
               if(args.length > 3) {
                  charDebug = Boolean.parseBoolean(args[3]);
               }
            }
         }
         echo(args[0]);  System.out.println();
         System.out.println(recognize());
      //GENERATE code.close();
      } 
      else {
         System.out.println("---ERROR: missing filename argument");
      }
   }
   
   public static int readInput(Scanner input, int[] token) {
      int result = 0;
      int sourceLine = 0;
      while(input.hasNextInt() && (result < token.length)) {
         token[result] = input.nextInt();  tokenLine[result] = sourceLine;
         if(token[result] == TOKEN_NL) {
            sourceLine = sourceLine + 1;
         } 
         result = result + 1;
      }
      if(input.hasNextInt()) {
         System.out.println("---ERROR: token memory filled");
      } 
      else {
         //Loop to strip off trailing TOKEN_NL's
         while((result > 0) && (token[result-1] == TOKEN_NL)) {
            result = result - 1;
         }
      }
      return result;
   }
   
   static int maxSourceWidth = 0;
   public static int readSourceInput(Scanner input, String[] source) {
      int result = 0;
      while(input.hasNextLine() && (result < source.length)) {
         source[result] = input.nextLine();
         maxSourceWidth = Math.max(maxSourceWidth,source[result].length());
         result = result + 1;
      }
      if(input.hasNextInt()) {
         System.out.println("---ERROR: source memory filled");
      } 
      return result;
   }

   static int maxISTWidth = 0;
   public static int readInternalSymbolTable(Scanner input, String[] ist) {
      int result = 0;
      ist[0] = ""; result = 1;
      while(input.hasNextLine() && (result < ist.length)) {
         ist[result] = input.nextLine();
         maxISTWidth = Math.max(maxISTWidth,ist[result].length());
         result = result + 1;
      }
      if(input.hasNextInt()) {
         System.out.println("---ERROR: internal symbol table memory filled");
      }
      // Debugging: print the Internal Symbol Table
      //for(int i=0;i<result;i++) {
      //   System.out.println("ist,"+i+": <"+ist[i]+">");
      //}
      return result;
   }

   
   
   public static void echo(String fid) {
      int nextToken = 0;
      BufferedWriter output;
      try {
         output = new BufferedWriter(new FileWriter(new File(fid+EXTENSION_LISTING)));
         for(int nextSource=0; nextSource<sourceN; nextSource++) {
            int width = source[nextSource].length();
            output.write(intWidth(nextSource,4)+": ");
            output.write(source[nextSource] + blanks(width,maxSourceWidth)+" |");
            output.write(nextToken+"| ");
            while((nextToken < n) && (token[nextToken] != TOKEN_NL)) {
               output.write(token[nextToken] + ",");
               nextToken = nextToken + 1;
            }
            if(nextToken < n) {
               output.write(""+token[nextToken]);
               nextToken = nextToken + 1;
            }
            output.write("\n");
         }
         output.close();
      } 
      catch(Exception e) {
         System.out.println("---Error: (echo) invalid output file'"+fid+"'");
      }
   }
   
   public static String intWidth(int i, int width) {
      String result = "" + i;
      while(result.length() < width) {
         result = " " + result;
      }
      return result;
   }
   
   public static String blanks(int width, int widthLimit) {
      String result = "";
      while(width < widthLimit) {
         result = result + " ";  width = width + 1;
      }
      return result;
   }
   
///////////////////////////////////////////////////////////////////////////////////////////////////
// Recursive Descent Parsing Methods
///////////////////////////////////////////////////////////////////////////////////////////////////
   
   public static boolean recognize() {
      int recognized = CompilationUnit(0);
      return (recognized == n);      
   }
   
   public static int CompilationUnit(int startIndex) {
      final String IDENTITY = "CompilationUnit";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nImports = Imports(startIndex) ;
      int nUnit = Unit(startIndex+nImports);
      result = nImports + nUnit;
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Imports(int startIndex) {
      final String IDENTITY = "Imports";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nImport = 0;
      do {
         nImport = Import(startIndex+result);
         if(nImport > 0) {
            result = result + nImport;
         }
      } while(nImport > 0);
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Import(int startIndex) {
      final String IDENTITY = "Import";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nIMPORT = match(startIndex,TOKEN_IMPORT);
      if(nIMPORT > 0) {
         int nID = match((startIndex+nIMPORT),TOKEN_ID);
         if(nID > 0) {
            int nIDMore = IDMore(startIndex+nIMPORT+nID);
            int nSC = match((startIndex+nIMPORT+nID+nIDMore),TOKEN_SC);
            if(nSC > 0) {
               result = nIMPORT + nID + nIDMore + nSC;
            } 
            else {
               reportSyntaxError(IDENTITY,"; expected", 
                                 startIndex,(startIndex+nIMPORT+nID+nIDMore));
            }
         } 
         else {
            reportSyntaxError(IDENTITY,"identifier expected", 
                              startIndex,(startIndex+nIMPORT));         
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }

   public static int IDMore(int startIndex) {
      final String IDENTITY = "IDMore";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nP;
      int nID;
      do {
         nP = 0;
         nID = 0;
         nP = match((startIndex+result),TOKEN_P);
         if(nP > 0) {
            nID = match((startIndex+result+nP),TOKEN_ID);
            if(nID > 0) {
               result = result + nP + nID;
            } 
            else {
               reportSyntaxError(IDENTITY,"identifier expected", 
                                 startIndex,(startIndex+result+nP));
            }
         }
      } while((nP > 0) && (nID > 0));
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Unit(int startIndex) {
      final String IDENTITY = "Unit";
      displayStart(IDENTITY,startIndex);
      int result = Program(startIndex);
      if(result == 0) {
         result = Class(startIndex);
      }
      return displayStop(result,IDENTITY,startIndex);
   }

///////////////////////////////////////////////////////////////////////////////////////////////////
   
   public static int Program(int startIndex) {
      final String IDENTITY = "Program";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nPROGRAM = match(startIndex,TOKEN_PROGRAM);
      //DEBUG System.out.print("[nPROGRAM:"+nPROGRAM+"]");
      if(nPROGRAM > 0) {
         int nID = match((startIndex+nPROGRAM),TOKEN_ID);
         //DEBUG System.out.print("[nID:"+nID+"]");
         if(nID > 0) {
            int nBody = Body(startIndex+nPROGRAM+nID);
            //DEBUG System.out.print("[nBody:"+nBody+"]");
            if(nBody > 0) {
               result = nPROGRAM+nID+nBody;
            }
         } 
         else {
            reportSyntaxError(IDENTITY,"identifier expected after program", 
                              startIndex,(startIndex+nPROGRAM));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Class(int startIndex) {
      final String IDENTITY = "Class";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nCLASS = match(startIndex,TOKEN_CLASS);
      if(nCLASS > 0) {
         int nIDClass = IDClass(startIndex+nCLASS);
         if(nIDClass > 0) {
            int nEXTENDS = match((startIndex+nCLASS+nIDClass),TOKEN_EXTENDS);
            if(nEXTENDS > 0) {
               int nIDClass2 = IDClass(startIndex+nCLASS+nIDClass+nEXTENDS);
               if(nIDClass2 > 0) {
                  int nBody = Body(startIndex+nCLASS+nIDClass+nEXTENDS+nIDClass2);
                  if(nBody > 0) {
                     result = nCLASS+nIDClass+nEXTENDS+nIDClass2+nBody;
                  }
               }         
               else {
                  reportSyntaxError(IDENTITY,"identifier expected after extends", 
                                    startIndex,(startIndex+nCLASS+nIDClass+nEXTENDS));
               }            
            }               
            else {
               reportSyntaxError(IDENTITY,"extends expected", 
                                 startIndex,(startIndex+nCLASS+nIDClass));
            }            
         }               
         else {
            reportSyntaxError(IDENTITY,"identifier expected after class", 
                              startIndex,(startIndex+nCLASS));
         }            
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Body(int startIndex) {
      final String IDENTITY = "Body";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nLB = match(startIndex,TOKEN_LB);
      if(nLB > 0) {
         int nDeclarations = Declarations(startIndex+nLB);
         int nBlock = Block(startIndex+nLB+nDeclarations);
         if(nBlock > 0) {
            int nDeclarations2 = Declarations(startIndex+nLB+nDeclarations+nBlock);
            int nRB = match((startIndex+nLB+nDeclarations+nBlock+nDeclarations2),TOKEN_RB);
            if(nRB > 0) {
               result = nLB+nDeclarations+nBlock+nDeclarations2+nRB;
            }                
            else {
               reportSyntaxError(IDENTITY,(STRING_RB+" expected"), 
                                 startIndex,(startIndex+nLB+nDeclarations+nBlock+nDeclarations2));
            }                       
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Declarations(int startIndex) {
      final String IDENTITY = "Declarations";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nDeclaration;
      do {
         nDeclaration = Declaration(startIndex+result);
         result = result + nDeclaration;
      } while(nDeclaration > 0);
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Declaration(int startIndex) {
      final String IDENTITY = "Declaration";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nQualification = Qualification(startIndex);
      int nMethodDeclaration = MethodDeclaration(startIndex+nQualification);
      if(nMethodDeclaration > 0) {
         result = nQualification + nMethodDeclaration;        
      } 
      else {
         int nIntent = Intent(startIndex);
         int nBinding = Binding(startIndex+nIntent);
         int nObjectDeclaration = ObjectDeclaration(startIndex+nIntent+nBinding);
         if(nObjectDeclaration > 0) {
            result = nIntent + nBinding + nObjectDeclaration;
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Qualification(int startIndex) {
      final String IDENTITY = "Qualification";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nAccess = Access(startIndex);
      int nIntent = Intent(startIndex+nAccess);
      result = nAccess + nIntent;
      return displayStop(result,IDENTITY,startIndex);
   }
   
   static final int[] ACCESS_OPTIONS = {TOKEN_PUBLIC,TOKEN_PROTECTED,TOKEN_PRIVATE};
   public static int Access(int startIndex) {
      final String IDENTITY = "Access";
      displayStart(IDENTITY,startIndex);
      int result = matchOneOf(startIndex,ACCESS_OPTIONS);
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Intent(int startIndex) {
      final String IDENTITY = "Intent";
      displayStart(IDENTITY,startIndex);
      int result = match(startIndex,TOKEN_STATIC);
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Binding(int startIndex) {
      final String IDENTITY = "Binding";
      displayStart(IDENTITY,startIndex);
      int result = match(startIndex,TOKEN_FINAL);
      return displayStop(result,IDENTITY,startIndex);
   }
   
///////////////////////////////////////////////////////////////////////////////////////////////////

   public static int MethodDeclaration(int startIndex) {
      final String IDENTITY = "MethodDeclaration";
      displayStart(IDENTITY,startIndex);
      int result = Function(startIndex);
      if(result == 0) {
         result = Procedure(startIndex);
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int ObjectDeclaration(int startIndex) {
      final String IDENTITY = "ObjectDeclaration";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nFormal = Formal(startIndex);
      if(nFormal > 0) {
         int nInitialization = Initialization(startIndex+nFormal);
         int nSC = match((startIndex+nFormal+nInitialization),TOKEN_SC);
         if(nSC > 0) {
            result = nFormal + nInitialization + nSC;
         }            
         else {
            reportSyntaxError(IDENTITY,(STRING_SC+" expected"), 
                              startIndex,(startIndex+nFormal+nInitialization));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Block(int startIndex) {
      final String IDENTITY = "Block";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nLB = match(startIndex,TOKEN_LB);
      if(nLB > 0) {
         int nStatementList = StatementList(startIndex+nLB);
         int nRB = match((startIndex+nLB+nStatementList),TOKEN_RB);
         if(nRB > 0) {
            result = nLB + nStatementList + nRB;
         }         
         else {
            reportSyntaxError(IDENTITY,(STRING_RB+" expected"), 
                              startIndex,(startIndex+nLB+nStatementList));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Function(int startIndex) {
      final String IDENTITY = "Function";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nIDClass = IDClass(startIndex);
      if(nIDClass > 0) {
         int nIDFunction = IDFunction(startIndex+nIDClass);
         if(nIDFunction > 0) {
            int nFormalList = FormalList(startIndex+nIDClass+nIDFunction);
            if(nFormalList > 0) {
               int nBlock = Block(startIndex+nIDClass+nIDFunction+nFormalList);
               if(nBlock > 0) {
                  result = nIDClass+nIDFunction+nFormalList+nBlock;
               }
            }
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Procedure(int startIndex) {
      final String IDENTITY = "Procedure";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nIDProcedure = IDProcedure(startIndex);
      if(nIDProcedure > 0) {
         int nFormalList = FormalList(startIndex+nIDProcedure);
         if(nFormalList > 0) {
            int nBlock = Block(startIndex+nIDProcedure+nFormalList);
            if(nBlock > 0) {
               result = nIDProcedure+nFormalList+nBlock;
            }
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Formal(int startIndex) {
      final String IDENTITY = "Formal";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nIDClass = IDClass(startIndex);
      if(nIDClass > 0) {
         int nID = match((startIndex+nIDClass),TOKEN_ID);
         if(nID > 0) {
            result = nIDClass + nID;
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
///////////////////////////////////////////////////////////////////////////////////////////////////

   public static int Initialization(int startIndex) {
      final String IDENTITY = "Initialization";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nP = match(startIndex,TOKEN_P);
      if(nP > 0) {
         int nInstantiation = Instantiation(startIndex+nP);
         if(nInstantiation > 0) {
            int nLP = match((startIndex+nP+nInstantiation),TOKEN_LP);
            if(nLP > 0) {
               int nActuals = Actuals(startIndex+nP+nInstantiation+nLP);
               int nRP = match((startIndex+nP+nInstantiation+nLP+nActuals),TOKEN_RP);
               if(nRP > 0) {
                  result = nP+nInstantiation+nLP+nActuals+nRP;
               } 
               else {
                  reportSyntaxError(IDENTITY,(STRING_RP+" expected"),startIndex,
                                    (startIndex+nP+nInstantiation+nLP+nActuals));
               }
            }         
            else {
               reportSyntaxError(IDENTITY,(STRING_LP+" expected"), 
                                 startIndex,(startIndex+nP+nInstantiation));
            }
         }         
         else {
            reportSyntaxError(IDENTITY,(STRING_NEW+" or "+STRING_SET+" expected"), 
                              startIndex,(startIndex+nP));
         }
      }
      if((nP > 0) && (result == 0)) {
         reportSyntaxError(IDENTITY,("incomplete initialization"), 
                           startIndex,(startIndex+nP));
      
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Instantiation(int startIndex) {
      final String IDENTITY = "Instantiation";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nNEW = match(startIndex,TOKEN_NEW);
      if(nNEW > 0) {
         result = nNEW;
      } 
      else {
         int nSET = match(startIndex,TOKEN_SET);
         if(nSET > 0) {
            result = nSET;
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Actuals(int startIndex) {
      final String IDENTITY = "Actuals";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nExpression = Expression(startIndex);
      if(nExpression > 0) {
         result = nExpression + ActualsMore(startIndex+nExpression);
      }
      return displayStop(result,IDENTITY,startIndex);
   }

   public static int ActualsMore(int startIndex) {
      final String IDENTITY = "ActualsMore";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nC = match(startIndex,TOKEN_C);
      if(nC > 0) {
         int nExpression = Expression(startIndex+nC);
         if(nExpression > 0) {
            result = nC + nExpression + ActualsMore(startIndex+nC+nExpression);
         }         
         else {
            reportSyntaxError(IDENTITY,("invalid character after "+STRING_C), 
                              startIndex,(startIndex+nC));
            reportSyntaxError("Hint", ("Possible Missing Function Argument"), 
                              startIndex,(startIndex+nC));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Operation(int startIndex) {
      final String IDENTITY = "Operation";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nInvocation = Invocation(startIndex);
      if(nInvocation > 0) {
         result = nInvocation;
      } 
      else if(nInvocation < 0) {
         /* In this case, Invocation indicats the partial match of <TOKEN_ID,TOKEN_P>.
            So there is no need to try Calls, as it will not match.
         */
      } 
      else {
         int nCall = Calls(startIndex);
         if(nCall > 0) {
            result = nCall;
         }
      }   
      return displayStop(result,IDENTITY,startIndex);
   }

   public static int Invocation(int startIndex) {
      final String IDENTITY = "Invocation";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nID = match(startIndex,TOKEN_ID);
      if(nID > 0) {
         int nP = match((startIndex+nID),TOKEN_P);
         if(nP > 0) {
            result = (0 - (nID+nP));  //To indicate the partial match of <TOKEN_ID,TOKEN_P>
            int nCalls = Calls(startIndex+nID+nP);
            if(nCalls > 0) {
               result = nID+nP+nCalls;
            }
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Calls(int startIndex) {
      final String IDENTITY = "Calls";
      displayStart(IDENTITY,startIndex);
      int result = Call(startIndex);
      if(result > 0) {
         int nCallsMore;
         do {
            nCallsMore = CallsMore(startIndex+result);
            if(nCallsMore > 0) {
               result = result + nCallsMore;
            }
         } while(nCallsMore > 0);
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Call(int startIndex) {
      final String IDENTITY = "Call";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nMethodID = MethodID(startIndex);
      if(nMethodID > 0) {
         int nLP = match((startIndex+nMethodID),TOKEN_LP);
         if(nLP > 0) {
            int nActuals = Actuals(startIndex+nMethodID+nLP);
            int nRP = match((startIndex+nMethodID+nLP+nActuals),TOKEN_RP);
            if(nRP > 0) {
               result = nMethodID+nLP+nActuals+nRP;
            }         
            else {
               reportSyntaxError(IDENTITY,(STRING_RP+" expected"), 
                                 startIndex,(startIndex+nMethodID+nLP+nActuals));
            }
         }            
         else {
            if(!throughExpression) {
               reportSyntaxError(IDENTITY,(STRING_LP+" expected"), 
                                 startIndex,(startIndex+nMethodID));
            }
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int CallsMore(int startIndex) {
      final String IDENTITY = "CallsMore";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nP = match(startIndex,TOKEN_P);
      if(nP > 0) {
         int nCalls = Calls(startIndex+nP);
         if(nCalls > 0) {
            result = nP + nCalls;
         }         
         else {
            reportSyntaxError(IDENTITY,("invalid lexeme after "+STRING_P), 
                              startIndex,(startIndex+nP));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int MethodID(int startIndex) {
      final String IDENTITY = "MethodID";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nInstantiation = Instantiation(startIndex);
      if(nInstantiation > 0) {
         result = nInstantiation;
      } 
      else {
         result = OperatorID(startIndex);
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   static final int[] ID_IDSR = {TOKEN_ID,TOKEN_IDSR};
   public static int OperatorID(int startIndex) {
      final String IDENTITY = "OperatorID";
      displayStart(IDENTITY,startIndex);
      int result = matchOneOf(startIndex,ID_IDSR);
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int StatementList(int startIndex) {
      final String IDENTITY = "StatementList";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nRETURN = match(startIndex,TOKEN_RETURN);
      if(nRETURN > 0) {
         int nExpression = Expression(startIndex+nRETURN);
         if(nExpression > 0) {
            int nSC = match((startIndex+nRETURN+nExpression),TOKEN_SC);
            if(nSC > 0) {
               result = nRETURN+nExpression+nSC;
            }            
            else {
               reportSyntaxError(IDENTITY,(STRING_SC+" expected"), 
                                 startIndex,(startIndex+nRETURN+nExpression));
            }
         }
      } 
      else {
         int nStatement = Statement(startIndex);
         if(nStatement > 0) {
            result = result + nStatement + 
                              StatementList(startIndex+nStatement);
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Statement(int startIndex) {
      final String IDENTITY = "Statement";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nBinding = Binding(startIndex);
      int nObjectDeclaration = ObjectDeclaration(startIndex+nBinding);
      if(nObjectDeclaration > 0) {
         result = nBinding + nObjectDeclaration;
      } 
      else {
         int nOperation = Operation(startIndex);
         if(nOperation > 0) {
            int nSC = match((startIndex+nOperation),TOKEN_SC);
            if(nSC > 0) {
               result = nOperation + nSC;
            }            
            else {
               reportSyntaxError(IDENTITY,(STRING_SC+" expected"), 
                                 startIndex,(startIndex+nOperation));
            }
         } 
         else {
            int nIf = If(startIndex);
            if(nIf > 0) {
               result = nIf;
            } 
            else {
               int nWhile = While(startIndex);
               if(nWhile > 0) {
                  result = nWhile;
               }
            }
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   
   static boolean throughExpression = false;
   public static int Expression(int startIndex) {
      final String IDENTITY = "Expression";
      displayStart(IDENTITY,startIndex);  throughExpression = true;
      int result = 0;
      int nTerm = Term(startIndex);
      if(nTerm > 0) {
         int nExpressionMore = ExpressionMore(startIndex+nTerm);
         result = nTerm+nExpressionMore;
      }
      throughExpression = false;
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int ExpressionMore(int startIndex) {
      final String IDENTITY = "ExpressionMore";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nOperatorID = OperatorID(startIndex);
      if(nOperatorID > 0) {
         int nTerm = Term(startIndex+nOperatorID);
         if(nTerm > 0) {
            int nExpressionMore = ExpressionMore(startIndex+nOperatorID+nTerm);
            result = nOperatorID+nTerm+nExpressionMore;
         }         
         else {
            reportSyntaxError(IDENTITY,("incomplete expression"), 
                              startIndex,(startIndex+nOperatorID));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Term(int startIndex) {
      final String IDENTITY = "Term";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nLP = match(startIndex,TOKEN_LP);
      if(nLP > 0) {
         int nExpression = Expression(startIndex+nLP);
         if(nExpression > 0) {
            int nRP = match((startIndex+nLP+nExpression),TOKEN_RP);
            if(nRP > 0) {
               result = nLP + nExpression + nRP;
            }
         }
      } 
      else {
         int nItem = Item(startIndex);
         if(nItem > 0) {
            result = nItem;
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Item(int startIndex) {
      final String IDENTITY = "Item";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nLiteral = Literal(startIndex);
      if(nLiteral > 0) {
         result = nLiteral;
      } 
      else {      
         int nOperation = Operation(startIndex);
         if(nOperation > 0) {
            result = nOperation;
         } 
         else {
            int nID = match(startIndex,TOKEN_ID);
            if(nID > 0) {
               result = nID;
            } 
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   public static int If(int startIndex) {
      final String IDENTITY = "If";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nIF = match(startIndex,TOKEN_IF);
      if(nIF > 0) {
         int nLP = match((startIndex+nIF),TOKEN_LP);
         if(nLP > 0) {
            int nCondition = Condition(startIndex+nIF+nLP);
            if(nCondition > 0) {
               int nRP = match((startIndex+nIF+nLP+nCondition),TOKEN_RP);
               if(nRP > 0) {
                  int nBlock = Block(startIndex+nIF+nLP+nCondition+nRP);
                  if(nBlock > 0) {
                     int nElse = Else(startIndex+nIF+nLP+nCondition+nRP+nBlock);
                     result = nIF+nLP+nCondition+nRP+nBlock+nElse;
                  }            
                  else {
                     reportSyntaxError(IDENTITY,("invalid if block"), 
                                       startIndex,(startIndex+nIF+nLP+nCondition+nRP));
                  }
               }                  
               else {
                  reportSyntaxError(IDENTITY,(STRING_RP+" expected"), 
                                    startIndex,(startIndex+nIF+nLP+nCondition));
               }
            }                  
            else {
               reportSyntaxError(IDENTITY,("invalid if condition"), 
                                 startIndex,(startIndex+nIF+nLP));
            }
         }                  
         else {
            reportSyntaxError(IDENTITY,(STRING_LP+" expected"), 
                              startIndex,(startIndex+nIF));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }

   public static int Else(int startIndex) {
      final String IDENTITY = "Else";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nELSE = match(startIndex,TOKEN_ELSE);
      if(nELSE > 0) {
         int nBlock = Block(startIndex+nELSE);
         if(nBlock > 0) {
            result = nELSE + nBlock;
         }                  
         else {
            reportSyntaxError(IDENTITY,("invalid else block"), 
                              startIndex,(startIndex+nELSE));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int While(int startIndex) {
      final String IDENTITY = "While";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nWHILE = match(startIndex,TOKEN_WHILE);
      if(nWHILE > 0) {
         int nLP = match((startIndex+nWHILE),TOKEN_LP);
         if(nLP > 0) {
            int nCondition = Condition(startIndex+nWHILE+nLP);
            if(nCondition > 0) {
               int nRP = match((startIndex+nWHILE+nLP+nCondition),TOKEN_RP);
               if(nRP > 0) {
                  int nBlock = Block(startIndex+nWHILE+nLP+nCondition+nRP);
                  if(nBlock > 0) {
                     result = nWHILE+nLP+nCondition+nRP+nBlock;
                  }         
                  else {
                     reportSyntaxError(IDENTITY,("invalid while block"), 
                                       startIndex,(startIndex+nWHILE+nLP+nCondition+nRP));
                  }
               }                  
               else {
                  reportSyntaxError(IDENTITY,(STRING_RP+" expected"), 
                                    startIndex,(startIndex+nWHILE+nLP+nCondition));
               }
            }               
            else {
               reportSyntaxError(IDENTITY,("invalid while condition"), 
                                 startIndex,(startIndex+nWHILE+nLP));
            }
         }            
         else {
            reportSyntaxError(IDENTITY,(STRING_LP+" expected"), 
                                 startIndex,(startIndex+nWHILE));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Condition(int startIndex) {
      final String IDENTITY = "Condition";
      displayStart(IDENTITY,startIndex);
      int result = Expression(startIndex);
      return displayStop(result,IDENTITY,startIndex);
   }

   public static int FormalList(int startIndex) {
      final String IDENTITY = "FormalList";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nLP = match(startIndex,TOKEN_LP);
      if(nLP > 0) {
         int nFormals = Formals(startIndex+nLP);
         int nRP = match((startIndex+nLP+nFormals),TOKEN_RP);
         if(nRP > 0) {
            result = nLP+nFormals+nRP;
         }         
         else {
            reportSyntaxError(IDENTITY,(STRING_RP+" expected"), 
                              startIndex,(startIndex+nLP+nFormals));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int Formals(int startIndex) {
      final String IDENTITY = "Formals";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nFormal = Formal(startIndex);
      if(nFormal > 0) {
         result = nFormal + FormalsMore(startIndex+nFormal);
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int FormalsMore(int startIndex) {
      final String IDENTITY = "FormalsMore";
      displayStart(IDENTITY,startIndex);
      int result = 0;
      int nC = match(startIndex,TOKEN_C);
      if(nC > 0) {
         int nFormal = Formal(startIndex+nC);
         if(nFormal > 0) {
            result = nC + nFormal + FormalsMore(startIndex+nC+nFormal);
         }         
         else {
            reportSyntaxError(IDENTITY,("invalid lexeme(s) after "+STRING_C), 
                              startIndex,(startIndex+nC));
         }
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
///////////////////////////////////////////////////////////////////////////////////////////////////
   static final int[] IDCLASS = {TOKEN_ID,TOKEN_IDG};
   public static int IDClass(int startIndex) {
      final String IDENTITY = "IDClass";
      displayStart(IDENTITY,startIndex);
      int result = matchOneOf(startIndex,IDCLASS);
      return displayStop(result,IDENTITY,startIndex);
   }
   
   static final int[] IDFUNCTION = {TOKEN_ID,TOKEN_IDSD};
   public static int IDFunction(int startIndex) {
      final String IDENTITY = "IDFunction";
      displayStart(IDENTITY,startIndex);
      int result = matchOneOf(startIndex,IDFUNCTION);
      return displayStop(result,IDENTITY,startIndex);
   }
   
   public static int IDProcedure(int startIndex) {
      final String IDENTITY = "IDProcedure";
      displayStart(IDENTITY,startIndex);
      int result = match(startIndex,TOKEN_NEW);
      if(result == 0) {
         result = IDFunction(startIndex);
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
///////////////////////////////////////////////////////////////////////////////////////////////////

   static final int[] BOOLEAN_LITERAL = {TOKEN_TRUE,TOKEN_FALSE};
   static final int[] OTHER_LITERAL = {TOKEN_CL,TOKEN_SL,TOKEN_IL,TOKEN_RL};
   public static int Literal(int startIndex) {
      final String IDENTITY = "Literal";
      displayStart(IDENTITY,startIndex);
      int result = matchOneOf(startIndex,BOOLEAN_LITERAL);
      if(result == 0) {
         result = matchOneOf(startIndex,OTHER_LITERAL);
      }
      return displayStop(result,IDENTITY,startIndex);
   }
   
///////////////////////////////////////////////////////////////////////////////////////////////////
// RDP Support Methods
///////////////////////////////////////////////////////////////////////////////////////////////////
   
   public static int matchOneOf(int index, int options[]) {
      int result = 0;
      for(int i=0; (result==0) && (i<options.length); i++) {
         int matched = match(index,options[i]);
         if(matched > 0) {
            result = matched;
         }
      }
      return result;
   }
   
   public static int match(int index, int option) {
      int result = 0;
      int ignore = 0;
      while(ignored(index+ignore) > 0) {
         //DEBUG System.out.print("?");
         ignore = ignore + ignored(index+ignore);
      }
      if(token[(index+ignore)] == option) {
         // DEBUG System.out.print("{"+token[index+ignore]);
         result = ignore + 1;
         if(option < 0) {           //Handle tokens with immediate following Symbol Table indexes
            //DEBUG System.out.print(","+token[index+result]);
            result = result + 1;
         }
      }
      //DEBUG if(result>0) System.out.print("("+result+")"+"}");
      return result;
   }
  
   public static int ignored(int index) {
      int result = 0;
      if(index < n) {
      //Check for and ignore new line
         if(token[index] == TOKEN_NL) {
            result = 1;
         }
      //Check for and ignore comments
         for(int i=0; (result==0) && (i<COMMENTS.length); i++) {
            if(token[index] == COMMENTS[i]) {
               result = 2;
            }
         }
      //Check for, ignore but report lexical errors
         for(int i=0; (result==0) && (i<ERRORS.length); i++) {
            if(token[index] == ERRORS[i]) {
               result = 2;
               reportLexicalError(ERRORS_MESSAGES[i], index, (index+1));          
            }
         }
      }
      return result;
   }
  
///////////////////////////////////////////////////////////////////////////////////////////////////
   static final String BLANKS = "  ";
   static final String MARKS  = "--";
   static final int K = 12;
   public static void displayStart(String name, int startIndex) {
      if(rdpDebug) {
         level = level + 1;
         System.out.print(tab(level,BLANKS)+"<"+startIndex+"<< "+token[startIndex]+" <<< "+name);
      //        System.out.println(tab(level)+"<<< "+token[startIndex]+" <<< "+name);
         show(startIndex,K);
         System.out.println();
      }
   }                                                                                                                                                                                                                       
  
   public static int displayStop(int result, String name, int startIndex) {
      if(rdpDebug) {
         String prefix = BLANKS;
         if(result > 0) prefix = MARKS;
         System.out.print(tab(level,prefix)+
                          ">>> "+token[startIndex]+" >>> "+
                          name+" "+result);
         if(result > 0) System.out.print(" "+tokenLine[startIndex]+":"+tokenLine[startIndex+result-1]);
         show(startIndex,result);
         if(echoDebug) {
            if(result > 0) {
               System.out.print(" \"");
               for(int i=0; i<result; i++) {
                  System.out.print(token[startIndex+i]);
                  if((i+1)<result) {
                     System.out.print(",");
                  }
               }
               System.out.print("\" ");
            }
         }
         System.out.println();
         level = level - 1;
      }
      return result;
   }

   public static void show(int startIndex, int N) {
      System.out.print("          ");
      for(int i=0; i<N; i++) {
         int index = startIndex+i;
         int t = token[index];
         if((t > 0) && (t < STRING.length)) {
            System.out.print(STRING[t]);
         } 
         else if(t < 0) {
            System.out.print(ist[token[index+1]]);
            i++;
         }
         if((i+1) < N) System.out.print(" ");
      }
   }
   
   public static String tab(int level, String s) {
      String result = "";
      for(int i=0; i<level; i++) {
         result = result + s;
      }
      return result;
   }
   
   public static void reportLexicalError(String message, int startIndex, int stopIndex) {
      if(token[startIndex+1] > 0) {
         reportSourceLines(startIndex, stopIndex);
         System.out.println("====> Lexical Error: " + message + " \"" + ist[token[startIndex+1]] + "\"");
         token[startIndex+1] = 0;
      }
   }
   
   static boolean syntaxErrorReported = false;
   public static void reportSyntaxError(String Identity, String message, int startIndex, int stopIndex) {
      if(!syntaxErrorReported) {
         reportSourceLines(startIndex, stopIndex);
         if(true) {
         //if(rdpDebug || echoDebug || charDebug) {
            Identity = "(" + Identity + ") ";
         } 
         else {
            Identity = "";
         }
         System.out.println("====> Syntax Error: " + Identity + message);
      }
      syntaxErrorReported = true;
   }
  
   static boolean[] tokenLinePrinted = new boolean[TOKEN_LIMIT]; 
   public static void reportSourceLines(int startIndex, int stopIndex) {
      int line = -1;
      boolean reported = false;
      do {
         if(tokenLine[startIndex] != line) {
            line = tokenLine[startIndex];
            if(token[startIndex] > 0) {
               reported = reportSourceLine(line);
            }
         }
         startIndex = startIndex + 1;
      } while((startIndex < sourceN) && (startIndex <= stopIndex));
      if(!reported) {
         line = tokenLine[stopIndex];
         reported = reportSourceLine(line);
      }
   }
   
   public static boolean reportSourceLine(int line) {
      boolean result = false;
      if(!tokenLinePrinted[line]) {
         System.out.println(intWidth((line+1),4)+": "+source[line]);
         result = true;
         tokenLinePrinted[line] = true;
      }
      return result;
   }
}
