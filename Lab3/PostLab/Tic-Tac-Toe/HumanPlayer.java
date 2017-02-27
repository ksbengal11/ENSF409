import java.util.Scanner;
/**
* Provides methods to gather from input inorder to play a game of tick-tac-toe
* @author Karan Bengali
* @version 1.0
* @since February 2017
*/
public class HumanPlayer extends Player{
  /**
  * Default constructor of class HumanPlayer
  * @param name player name
  * @param mark of the player
  * @param board game board
  */
  public HumanPlayer(String name, char mark, Board board){
    super(name, mark, board);
  }
  protected void play(){
    String winner;
    Player p = this;
    while (true){
      if(board.isFull()){
        winner = "Nobody";
        System.out.println("The nobody is the winner !");
        break;
      }
      else if (board.xWins()){
        winner = p.name();
        System.out.println("The x player is the winner !");
        break;
      }
      else if (board.oWins()){
        winner = opponent.name();
        System.out.println("The o player is the winner!");
        break;
      }
      p.makeMove();
      board.display();
      p = p.opponent;
    }
    //System.out.println("The winner is : " + winner + "!");
  }
  /**
  * Prompt user to make move
  */
  @Override
  protected void makeMove() {
    int row, col;
    while(true){
      Scanner scan = new Scanner(System.in);
      System.out.print(name + " what row should your next " + mark + " be placed in : ");
      row = scan.nextInt();
      System.out.print("\n" + name + " what column should your next " + mark + " be placed in : ");
      col = scan.nextInt();

      if(row < 0 || row > 2 || col < 0 || col > 2){
        System.out.println("Index out of range, please try again");
        break;
      }

      if(board.getMark(row,col) == SPACE_CHAR){
        board.addMark(row,col,mark);
        break;
      }
      else System.out.println("Coordinate (" + row + "," + col + ") occupied. Please try again");
    }
  }
}
