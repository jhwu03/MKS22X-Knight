public class KnightBoard{
  int[][] board;
  int size;
  int size2;
  int[] xMove = {1,2,2,1,-1,-2,-2,-1};
  int[] yMove = {2,1,-1,-2,-2,-1,1,2};
  int[][] outMoves;
  public KnightBoard(int rows,int cols){
    board = new int[rows][cols];
    outMoves = new int[rows][cols];
    size = board.length;
    size2 = board[0].length;
    for(int i =0; i < board.length; i++){
      for(int r = 0; r < board[0].length; r++){
        board[i][r] = 0;
      }
    }
    for(int i = 0; i < rows; i++) {
      for(int j = 0; j < cols; j++) {
        if(i == 0 || i == rows - 1) {
          if(j == 0 || j == cols - 1) {
            outMoves[i][j] = 2;
          }else if(j == 1 || j == cols - 2) {
            outMoves[i][j] = 3;
          }else{
            outMoves[i][j] = 4;
          }
        }else if(i == 1 || i == rows - 2) {
          if(j == 0 || j == cols - 1) {
            outMoves[i][j] = 3;
          }else if(j == 1 || j == cols - 2) {
            outMoves[i][j] = 4;
          }else{
            outMoves[i][j] = 6;
          }
        }else{
          if(j == 0 || j == cols - 1) {
            outMoves[i][j] = 4;
          }else if(j == 1 || j == cols - 2) {
            outMoves[i][j] = 6;
          }else{
            outMoves[i][j] = 8;
          }
        }
      }
    }
  }
  public boolean isSafe(int x, int y, int i) {
    return (x + xMove[i] >= 0 && x + xMove[i] < size && y + yMove[i] >= 0 && y + yMove[i] < size2);
}

  public boolean addKnight(int x, int y,int moveNumber){
    if(x >= 0 && x < size && y >= 0 && y < size2 && board[x][y] == 0){
        board[x][y] = moveNumber;
         return true;
       }
    return false;
  }

  public boolean addKnightO(int x, int y, int moveNumber) {
    if(x >= 0 && x < size && y >= 0 && y < size2 && board[x][y] == 0){
        board[x][y] = moveNumber;
      for (int i = 0; i < 8; i++) {
        if (isSafe(x,y,i)) {
          outMoves[x + xMove[i]][y + yMove[i]]-= 1;// remove one move if it goes out of bounds
        }
      return true;
    }
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

public boolean removeKnightO(int x, int y) {
  if (x >= 0 && x < size && y >= 0 && y < size2 && board[x][y] != 0) {
      board[x][y] = 0;
      for (int i = 0; i < 8; i++) {
        if (isSafe(x,y,i)) {
          outMoves[x + xMove[i]][y + yMove[i]]+= 1;
      }
      return true;
    }
  }
  return false;
}
//sorts the x and y move arrays so they move towards the lowest move number first
public void reorder(int x, int y) {
  int temp;
  int xcor;
  int ycor;
  int[] outGoingMoves = new int[8];
  for (int i = 0; i < 8; i++) {
    if (isSafe(x,y,i)) {
      if (board[x + xMove[i]][y + yMove[i]] != 0) {
        //If knight is on the the square we check, push to the back
        outGoingMoves[i] = 100;
      }else{
        outGoingMoves[i] = outMoves[x + xMove[i]][y + yMove[i]];
      }
    }else{
      outGoingMoves[i] = 100;
    }
  }
  for (int r = 1; r < 8; r++) {
    xcor = xMove[r];
    temp = outGoingMoves[r];
    ycor = yMove[r];
    int c = r;
  while ( c > 0 && outGoingMoves[c-1] > temp ) {
    outGoingMoves[c] = outGoingMoves[c-1];
    xMove[c] = xMove[c-1];
    yMove[c] = yMove[c-1];
    c -= 1;
  }
  xMove[c] = xcor;
  outGoingMoves[c] = temp;
  yMove[c] = ycor;
  c = 0;
}
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
    }else{

    if(addKnightO(startingRows,startingCols,moveNumber)){
      reorder(startingRows,startingCols);
    for(int i = 0; i < 8 ; i++){
        if(solveHelper(startingRows + xMove[i],startingCols + yMove[i],xMove,yMove,moveNumber + 1)){
          return true;
        }
      }
      removeKnightO(startingRows,startingCols);
    }
    return false;
  }
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
    for(int i =0; i < size; i++){
      for(int r = 0; r < size2; r++){
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

  public String toStringOut() {
  String ans = "";
  for (int i = 0; i < size; i++) {
    for (int j = 0; j < size2; j++) {
      ans = ans + outMoves[i][j] + " ";
    }
    ans += '\n';
  }
  return ans;
}

  public static void main(String[] args) {
    KnightBoard board = new KnightBoard(5,6);
    System.out.println(board.countSolutions(0,0));
    System.out.println(board);

  }

}
