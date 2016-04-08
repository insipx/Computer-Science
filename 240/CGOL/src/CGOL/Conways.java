package CGOL;

import java.io.*;

/**
 * Created by insidious on 3/23/16.
 */
public class Conways implements Conways_Interface {
    public static final int EMPTY = 0;
    public static final int GLIDER = 1;
    public static final int EXPLODER = 2;
    public static final int TEN_CELL_LINE = 3;
    public static final int PUFFER_2 = 4;
    public static final int TEST = 5;
    public static final int INF_5X5=6;
    public static final int INF_2X12=7;

    //prefix for root of project
    private static final String PREFIX = "/home/insidious/Spring Semester/Computer-Science/240/CGOL/src/CGOL/";

    private final boolean ALIVE = true;
    private final boolean DEAD = false;


    boolean[][] board1;
    boolean[][] board2;
    boolean board1Active = true;

   //conways constructor
   public Conways(){
      board1 = new boolean[20][20];
      board2 = new boolean[20][20];
      initPattern(EMPTY);
   }
   public Conways(int pattern){
      board1 = new boolean[20][20];
      board2 = new boolean[20][20];
      initPattern(pattern);
   }

    //creates a lifeForm, exits if the lifeForm is not compatible with our 20x20 grid
    public Conways(boolean[][] lifeForm){
        if(lifeForm.length == 20 && lifeForm[0].length == 20){
            board1 = lifeForm;
            board2 = new boolean[20][20];
        }else{
            System.out.print("invalid lifeForm, please reconsider your pattern");
            System.exit(1);
        }
    }

   public void initPattern(int pattern){

       if(pattern == EMPTY){

      }else if (pattern == GLIDER){

         board1[10][10] = ALIVE;

         board1[11][11] = ALIVE;

         board1[12][9] = ALIVE;
         board1[12][10] = ALIVE;
         board1[12][11] = ALIVE;
        // System.out.println("Here is the Glider Pattern: ");
        // dumpWorld(false);
      }else if(pattern == EXPLODER){
            //layer 1
          board1[8][8] = ALIVE;
          board1[8][10] = ALIVE;
          board1[8][12] = ALIVE;
            //layer 2
          board1[9][8] = ALIVE;
          board1[9][12] = ALIVE;
            //layer 3
          board1[10][8] = ALIVE;
          board1[10][12] = ALIVE;
            //layer 4
          board1[11][8] = ALIVE;
          board1[11][12] = ALIVE;

            //layer 5
          board1[12][8] = ALIVE;
          board1[12][10] = ALIVE;
          board1[12][12] = ALIVE;
          //System.out.println("Here is the Exploder Pattern: ");
          //dumpWorld(false);
      }else if(pattern == TEN_CELL_LINE){
          //ten cell line
          board1[5][4] = ALIVE;
          board1[5][5] = ALIVE;
          board1[5][6] = ALIVE;
          board1[5][7] = ALIVE;
          board1[5][8] = ALIVE;
          board1[5][9] = ALIVE;
          board1[5][10] = ALIVE;
          board1[5][11] = ALIVE;
          board1[5][12] = ALIVE;
          board1[5][13] = ALIVE;
      }else if(pattern == PUFFER_2) {
//            x1 , 5
           String file = PREFIX + "puffer2.txt";
           readPattern(file, 1, 5);
           //System.out.println("here is the puffer");
           //dumpWorld(true, true);

       }else if(pattern == TEST){
           String file = PREFIX + "test.txt";
           readPattern(file,0,0);
           System.out.println("here is test");
            dumpWorld(false, true);
           dumpWorld(false, true);
       }else if (pattern == INF_5X5) {
           String file = PREFIX + "infinite5x5.txt";
           readPattern(file, 2, 7);
          // System.out.println("here is infinite5x5");
          // dumpWorld(false, true);
       }else if(pattern == INF_2X12){
           String file = PREFIX + "infinite2x12.txt";
           readPattern(file, 2,5);
           //System.out.println("here is infinite12x2");
           //dumpWorld(false,true);
       }else{
           //formless void
       }
   }
   private void readPattern(String file, int startx, int starty){


       InputStream is = null;
       InputStreamReader isr = null;
       BufferedReader reader = null;

       try {
           is = new FileInputStream(file);
           isr = new InputStreamReader(is);
           reader = new BufferedReader(isr);
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       }

       int value = 0;

       try {
           while ((value = reader.read()) != -1 && reader.ready()) {
               char c = (char) value;
               if (c == 'O') {
                   board1[starty][startx] = ALIVE;
                   if (startx == 19) {
                       startx = 0;
                       starty++;
                   }
                   startx++;

               } else {
                   if (startx == 19) {
                       startx = 0;
                       starty++;
                   } else startx++;
               }
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       try {
           if (is != null) is.close();
           if (isr != null) isr.close();
           if (reader != null) reader.close();
       } catch (IOException e) {
           e.printStackTrace();
       }

   }
   public void evolve(){
      //this just manages the board we are working on
      if(board1Active){
         board2 = createLife(board1);
         board1 = goExtinct(board1);
         board2 = defendBorder(board2);
         board1Active = false;
      }else{
         board1 = createLife(board2);
         board2 = goExtinct(board2);
         board1 = defendBorder(board1);
         board1Active = true;
      }

   }

   private boolean[][] createLife(boolean[][] board) {
      if(board1Active){
         board = board1;
      }else{
         board = board2;
      }

      //init a board for the results
      boolean[][] resultBoard = copyArr(board);


      for(int i = 1; i < board.length - 1; i ++){
         for( int j = 1; j < board[i].length - 1; j++ ){

            if(board[i][j]){
               //keep living if 2-3 live neighbors
               if(liveOrDie(i,j, ALIVE)){
                 //it stays alive
               }else {
                  //it dies
                  resultBoard[i][j] = DEAD;
               }
               //live if exactly 3 live neighbors
            }else{
               if(liveOrDie(i,j,DEAD)){
                  //come alive
                  resultBoard[i][j] = ALIVE;
               }else{
                  //stay dead
               }
            }
         }
      }
      return resultBoard;
   }
   //returns a copy of the array
   public boolean[][] copyArr(boolean[][] arr1){

      //hope this works
       boolean[][] tmp = new boolean[arr1.length][];

       for(int i = 0; i < arr1.length; i++){
           tmp[i] = arr1[i].clone();
       }

      return tmp;
   }

   private boolean liveOrDie(int x, int y, boolean alreadyAlive) {
      boolean[][] board;
      if(board1Active){
         board = board1;
      }else{
         board = board2;
      }
      int count = 0;

      if(board[x][y-1]) count++;
      if(board[x][y+1]) count++;

      if(board[x-1][y]) count++;
      if(board[x+1][y]) count++;

      if(board[x-1][y-1]) count++;
      if(board[x+1][y+1]) count++;

      if(board[x+1][y-1]) count++;
      if(board[x-1][y+1]) count++;

      if(alreadyAlive) {
         return count > 1 && count < 4;
      }else{
         return count == 3;
      }
   }

   public boolean[][] goExtinct(boolean[][] board){

      for(int i = 0; i < board.length; i++){
         for(int j = 0; j <board[i].length; j++){
            board[i][j] = DEAD;
         }
      }
      return board;
   }

   //dead border
   private boolean[][] defendBorder(boolean[][] board){
      //kill the border
      for(int i = 0; i < board.length; i ++){
         board[i][0] = DEAD;
         board[i][19] = DEAD;
         board[0][i] = DEAD;
         board[19][i] = DEAD;
      }

      return board;
   }


   public void dumpWorld(boolean showDeadBorder, boolean printable){

       //show deadborder
       if(showDeadBorder){
          if(printable){
              printWorld(0,0,0,0,true);
          }else{
              printWorld(0,0,0,0,false);
          }
      //don't show dead border
      }else {
          if(printable){
              printWorld(1,1,1,1, true);
          }else{
              printWorld(1,1,1,1, false);
          }
      }
   }
    public boolean[][] getLifeForm(){
        boolean[][] board;
        if(board1Active){
            board = copyArr(board1);
            return board;
        }else{
            board = copyArr(board2);
            return board;
        }
    }
    public void printBoard(boolean[][] board){

        System.out.println("This is the lifeform that is being annoying");
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j ++){
                if(board[i][j]){ System.out.print(" O ");}
                else{ System.out.print(" . ");}
            }
            System.out.println();
        }
    }


   //prints the world from a starting value, and ending at a specific value
   private void printWorld(int startI, int endI, int startJ, int endJ, boolean printable){
         boolean[][] board;
       if (board1Active) board = board1;
       else board = board2;

         System.out.println("===========================================================");
         for (int i = startI; i < board.length - endI; i++) {
            for (int j = startJ; j < board[i].length - endJ; j++) {
               if (board[i][j]) {
                   //after some testing the 0 and . proved to be the easiest on the eyes
                   if(printable){
                       System.out.print("O");
                   }else {
                       System.out.print(" 0 ");
                   }
               } else {
                   if(printable){
                       System.out.print(".");
                   }else {
                       System.out.print(" . ");
                   }
               }
            }
            System.out.println();
         }
         System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
      }
   public String toBitString() {
       String bitString = "";
       boolean[][] tmpBoard = new boolean[20][20];
       if (board1Active) {
           tmpBoard = copyArr(board1);
       } else {
           tmpBoard = copyArr(board2);
       }

       for (int i = 0; i < tmpBoard.length; i++) {
           for (int j = 0; j < tmpBoard[i].length; j++) {
               if (tmpBoard[i][j]) bitString += "1";
               else bitString += "0";
           }
       }

       return bitString;
   }

   public boolean[][] toBoolArr(String str){

       boolean[][] tmpBoard = new boolean[20][20];
       int strIdx = 0;
       for(int i = 0; i < tmpBoard.length; i ++){
           for(int j = 0; j < tmpBoard[i].length ; j ++){

               if(str.charAt(strIdx) == '1'){
                   tmpBoard[i][j] = ALIVE;
               }else{
                   tmpBoard[i][j] = DEAD;
               }
               strIdx++;
           }
       }
       return tmpBoard;

   }

}
