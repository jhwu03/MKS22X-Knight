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

  private boolean removeKnight(int r, int c){
  if (r < 0 || r >= board.length || c < 0 || c >= board.length){
    return false;
  }
  if (board[r][c] == 0) {
    return false;
  }
  board[r][c] = 0;
  return true;
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
    //addKnight(startingRows,startingCols,1);
    return solveHelper(startingRows,startingCols,xMove,yMove,1);
  }

  private boolean solveHelper(int startingRows, int startingCols, int[] xMove, int[] yMove, int moveNumber){
    if(moveNumber > size * size2){
      //System.out.println("" + size+ "," + moveNumber);
      return true;
    }
    if (startingRows < 0 || startingCols < 0 || startingRows >= size || startingCols >= size2) {
      return false; // if out of bounds go back
    }
    if (board[startingRows][startingCols] != 0) {
      return false;
      // if this is a knight go back
    }
    for(int i = 0; i < 8 ; i++){
        board[startingRows][startingCols] = moveNumber;
        if(solveHelper(startingRows + xMove[i],startingCols + yMove[i],xMove,yMove,moveNumber + 1)){
          return true;
        }
        board[startingRows][startingCols] = 0;
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
     //addKnight(startingRow,startingCol,1);
     return countHelper(startingRow,startingCol, xMove, yMove,1);
  }

  private int countHelper(int startingRow, int startingCol, int[] xMove, int[] yMove, int moveNumber){

    if (startingRow < 0 || startingCol < 0 || startingRow >= size || startingCol >= size2) {
      return 0; // if out of bounds not a solution
    }
    if (board[startingRow][startingCol] != 0) {
      return 0;
      // if this is a knight not a solution
    }
    if(moveNumber == size * size2){
      //System.out.println("" + size+ "," + moveNumber);
      return 1;
    }
    int ans = 0;
    for(int i = 0; i < 8 ; i++){
        board[startingRow][startingCol] = moveNumber;
        ans += countHelper(startingRow + xMove[i],startingCol + yMove[i],xMove,yMove,moveNumber + 1);
        board[startingRow][startingCol] = 0;
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
    KnightBoard board = new KnightBoard(5,6);
    System.out.println(board.countSolutions(0,0));
    System.out.println(board);

  }

}
