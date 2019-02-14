public class KnightBoard{
  int[][] board;
  int size = board.length;
  int size2 = board[0].length;
  int[] xMove = {1,2,2,1,-1,-2,-2,-1};
  int[] yMove = {2,1,-1,-2,-2,-1,1,2};
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for(int i =0; i < board.length; i++){
      for(int r = 0; r < board[0].length; r++){
        board[i][r] = 0;
      }
    }
  }

  public boolean addKnight(int x, int y,int moveNumber){
    board[x][y] = moveNumber;
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
    return solveHelper(startingRows,startingCols,xMove, yMove,1);
  }

  private boolean solveHelper(int startingRows, int startingCols, int[] xMove, int[] yMove, int moveNumber){
    int xcor,ycor;
    if(moveNumber == size * size){
      return true;
    }
    for(int i = 0; i < 8 ; i++){
      xcor = startingRows + xMove[i];
      ycor = startingCols + yMove[i];
      if(xcor >= 0 && xcor < size && ycor >= 0 && ycor < size2 && board[xcor][ycor] == 0){
        addKnight(xcor,ycor,moveNumber);
        if(solveHelper(startingRows,startingCols,xMove,yMove,moveNumber + 1)){
          return true;
        }
      }
    }

  }

}
