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
    return solveHelper(startingRows,startingCols,xMove, yMove,1);
  }

  private boolean solveHelper(int startingRows, int startingCols, int[] xMove, int[] yMove, int moveNumber){
    int xcor,ycor;
    if(moveNumber == size * size){
      System.out.println("" + size+ "," + moveNumber);
      return true;

    }
    for(int i = 0; i < 8 ; i++){
      xcor = startingRows + xMove[i];
      ycor = startingCols + yMove[i];
      if(addKnight(xcor,ycor,moveNumber)){
        if(solveHelper(xcor,ycor,xMove,yMove,moveNumber + 1)){
          return true;
        }else{
        board[xcor][ycor] = 0;
      }
      }
    }
    return false;
  }

  public String toString(){
    String ans = "";
    for(int i =0; i < board.length; i++){
      for(int r = 0; r < board[0].length; r++){
        if(board[i][r] == 0){
          System.out.println("P");
          ans += " _";
        }else{
          System.out.println("Q");
          ans += "" + board[i][r];
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
