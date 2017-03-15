package inlab4;
/**
* Tick-tac-toe AI that blocks user if they are in a winning position
* @version 1.0
* @author Karan Bengali
* @since February 2017
*/
public class BlockingPlayer extends RandomPlayer{
  /**
  * Default constructor of the class BlockingPlayer
  * @param name of the player
  * @param mark of the player
  * @param board game board
  */
  public BlockingPlayer(String name, char mark, Board board){
    super(name, mark, board);
  }
  /**
  * Determine if a player is in a winning position and block if necessary.
  * Otherwise, make a random move
  */
  @Override
  protected void makeMove(){
    for(int row = 0; row < 3; row++){
      for(int col = 0; col < 3; col++){
        if(board.getMark(row,col) == SPACE_CHAR && testforBlocking(row,col)){
          board.addMark(row, col, mark);
          return;
        }
      }
    }
    super.makeMove();
  }
  /**
  * Check if current board configuration can win the game
  * @param row of the board
  * @param col of the board
  * @return true if the space needs to be blocked
  */
  protected boolean testforBlocking(int row, int col){
    char opponent_mark = opponent.mark();
    boolean is_winner = true;

    // Check rows
    for(int i = 0; i < 3; i++){
      if((i != col) && (board.getMark(row,i) != opponent_mark)){
        is_winner = false;
        break;
      }
    }
    if (is_winner) return true;

    // Check columns
    is_winner = true;
    for(int i = 0; i < 3; i++){
      if((i != row) && (board.getMark(i,col) != opponent_mark)){
        is_winner = false;
        break;
      }
    }
    if (is_winner) return true;

    // Check diagonals
    if((row+col) % 2 != 0) return false; // Can't be a diagonal

    switch(row){
      case 0:
        if(col != 0 && board.getMark(2,0) == opponent_mark && board.getMark(1,1) == opponent_mark) return true;
        else if (board.getMark(2,2) == opponent_mark && board.getMark(1,1) == opponent_mark) return true;
        break;
      case 1:
        if(board.getMark(0,0) == opponent_mark && board.getMark(2,2) == opponent_mark) return true;
        else if (board.getMark(0,2) == opponent_mark && board.getMark(2,0) == opponent_mark) return true;
        break;
      case 2:
        if(col != 0 && board.getMark(0,0) == opponent_mark && board.getMark(1,1) == opponent_mark) return true;
        else if (board.getMark(0,2) == opponent_mark && board.getMark(1,1) == opponent_mark) return true;
        break;
    }
    return false;
  }
}
