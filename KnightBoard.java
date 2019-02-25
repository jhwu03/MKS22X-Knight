public class KnightBoard{
  int[][] board;
  int size;
  int size2;
  int[] xMove = {1,2,2,1,-1,-2,-2,-1};
  int[] yMove = {2,1,-1,-2,-2,-1,1,2};
  public KnightBoard(int rows,int cols){
    board = new int[rows][cols];
    size = board.length;
    size2 = board[0].length;
    for(int i =0; i < board.length; i++){
      for(int r = 0; r < board[0].length; r++){
        board[i][r] = 0;
      }
    }
  }
  public boolean isSafe(int x, int y, int board[][]) {
    return (x >= 0 && x < size && y >= 0 &&
            y < size && board[x][y] == 0);
}

  public boolean addKnight(int x, int y,int moveNumber){
    if(x >= 0 && x < size && y >= 0 && y < size2 && board[x][y] == 0){
        board[x][y] = moveNumber;
         return true;
       }
    return false;
  }

  public boolean solve(int startingRows, int startingCols){
    if(startingRows > board.length || startingCols > board[0].length
       || startingRows < 0 || startingCols < 0) throw new IllegalStateException();

    for(int i =0; i < board.length; i++){
      for(int r = 0; r < board[0].length; r++){
        if(board[i][r] != 0){
          throw new IllegalStateException();
        }
      }
    }
    addKnight(startingRows,startingCols,1);
    return solveHelper(startingRows,startingCols,xMove, yMove,2);
  }

  private boolean solveHelper(int startingRows, int startingCols, int[] xMove, int[] yMove, int moveNumber){
    int xcor,ycor;
    if(moveNumber > size * size){
      //System.out.println("" + size+ "," + moveNumber);
      return true;

    }
    for(int i = 0; i < 8 ; i++){
      xcor = startingRows + xMove[i];
      ycor = startingCols + yMove[i];
      if(isSafe(xcor,ycor,board)){
        board[xcor][ycor] = moveNumber;
        if(solveHelper(xcor,ycor,xMove,yMove,moveNumber + 1)){
          return true;
        }
        board[xcor][ycor] = 0;
      }
    }
    return false;
  }

  public int countSolutions(int startingRow, int startingCol){
    if(startingRow > board.length || startingCol > board[0].length
       || startingRow < 0 || startingCol < 0) throw new IllegalStateException();

     for(int i =0; i < board.length; i++){
       for(int r = 0; r < board[0].length; r++){
         if(board[i][r] != 0){
           throw new IllegalStateException();
         }
       }
     }
     addKnight(startingRow,startingCol,1);
     return countHelper(startingRow,startingCol, xMove, yMove,2);
  }

  private int countHelper(int startingRow, int startingCol, int[] xMove, int[] yMove, int moveNumber){
    int xcor,ycor,ans;
    if(moveNumber > size * size){
      //System.out.println("" + size+ "," + moveNumber);
      return 1;

    }
    for(int i = 0; i < 8 ; i++){
      xcor = startingRows + xMove[i];
      ycor = startingCols + yMove[i];
      if(isSafe(xcor,ycor,board)){
        board[xcor][ycor] = moveNumber;
          return ans += countHelper(startingRow,startingCol, xMove, yMove,moveNumber + 1);
        }
        board[xcor][ycor] = 0;
      }
      return ans;
    }

  public String toString(){
    String ans = "";
    for(int i =0; i < board.length; i++){
      for(int r = 0; r < board[0].length; r++){
        if(board[i][r] < 10){
          ans += " " + board[i][r] + " ";
        }else{
          ans += board[i][r] + " ";
        }
      }
      ans += "\n";
    }
    return ans;

  }

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(8,8);
    System.out.println(board.solve(0,0));
    System.out.println(board);

  }

}
