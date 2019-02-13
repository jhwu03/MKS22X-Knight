public class KnightBoard{
  int[][] board;
  public KnightBoard(int startingRows,int startingCols){
    board = new int[startingRows][startingCols];
    for(int i =0; i < board.length; i++){
      for(int r = 0; r < board[0].length; r++){
        board[i][r] = 0;
      }
    }
  }

}
