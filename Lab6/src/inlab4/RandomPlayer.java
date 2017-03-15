package inlab4;
/**
* Randomly places an mark on the tick-tac-toe board game
* @author Karan Bengali
* @version 1.0
* @since February, 2017
*/
public class RandomPlayer extends Player{
  /**
  * Default constructor for class RandomPlayer
  * @param name of the player
  * @param mark of the player
  * @param board game board
  */
  public RandomPlayer(String name, char mark, Board board){
    super(name, mark, board);
  }
  protected void play(){
    String winner;
    Player player = this;
    while (true){
      if(board.isFull()){
        winner = "Nobody";
        System.out.println("The nobody is the winner !");
        break;
      }
      else if (board.xWins()){
        winner = player.name();
        System.out.println("The x player is the winner !");
        break;
      }
      else if (board.oWins()){
        winner = opponent.name();
        System.out.println("The o player is the winner!");
        break;
      }
      player.makeMove();
      board.display();
      player = player.opponent;
    }
  }
  /**
  * Places a mark at a random index
  */
  @Override
  protected void makeMove(){
    RandomGenerator r = new RandomGenerator();
    int row = 0; int col = 0;
    do{
      row = r.discrete(0,2);
      col = r.discrete(0,2);
    }while(board.getMark(row,col) != SPACE_CHAR);

    board.addMark(row, col, mark);
  }
}
