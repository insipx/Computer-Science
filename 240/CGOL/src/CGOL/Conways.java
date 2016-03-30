package CGOL;

/**
 * Created by insidious on 3/23/16.
 */
public class Conways implements Conways_Interface {
   //ints for patterns because Strings get extremely buggy
   public final int EMPTY = 0;
   public final int GLIDER = 1;

   final boolean ALIVE = true;
   final boolean DEAD = false;
   boolean[][] board1;
   boolean[][] board2;
   boolean board1Active = true;

   //conways constructor
   Conways(){
      board1 = new boolean[20][20];
      board2 = new boolean[20][20];
      initPattern(EMPTY);
   }
   Conways(int pattern){
      board1 = new boolean[20][20];
      board2 = new boolean[20][20];
      initPattern(pattern);
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
         //dumpWorld();
      }else{

      }
   }
   public void step(){
      //this just manages the board we are working on
      if(board1Active){
         board2 = createLife(board1);
         board1 = clearBoard(board1);
         board2 = defendBorder(board2);
         board1Active = false;
      }else{
         board1 = createLife(board2);
         board2 = clearBoard(board2);
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
      boolean[][] resultBoard = new boolean[20][20];
      resultBoard = copyArr(board, resultBoard);

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
   //copy arr1 into arr2
   private boolean[][] copyArr(boolean[][] arr1, boolean[][] arr2){
      for(int i = 0; i < arr1.length; i ++){
         for(int j = 0; j < arr1[i].length; j++){
            boolean temp;
            temp = arr1[i][j];
            arr2[i][j] = temp;
         }
      }

      return arr2;
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


   private boolean[][] clearBoard(boolean[][] board){

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


   public void dumpWorld(){
      boolean[][] board;
      if(board1Active){
         board = board1;
      }else{
         board = board2;
      }

      System.out.println("===========================================================");
      for(int i = 1; i < board.length-1; i++){
         for(int j = 1; j < board[i].length-1; j++){
            if(board[i][j]){
               System.out.print(" X ");
            }else{
               System.out.print(" o ");
            }
         }
         System.out.println();
      }
      System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
   }



}
