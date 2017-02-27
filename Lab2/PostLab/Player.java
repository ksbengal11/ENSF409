import java.util.Scanner;
/**
* Provides data fields and methods to create a Java data-type, representing a
* tick-tac-toe player in a Java application
* The overall purpose of this class is to allow users to play a tick-tac-toe
* game. This function also checks if the current board configuration can result
* in a win for 'o' player, 'x' player, or neither
*
* @author Karan Bengali
* @version 1
* @since February 2, 2017
*/
public class Player {
  /**
  * Public variable name specifying the name of the player
  */
  public String name;
  /**
  * Public variable board specifying the game board
  */
  public Board board;
  /**
  * Public variable oponnent specifying the oponnent of the player
  */
  public Player oponnent;
  /**
  * Public variable mark specifying the selected mark of the player
  */
  public char mark;

  /**
  * Default constructor of class Player. Assigns player name and associated
  * mark
  * @param name name of the player
  * @param mark specified mark of the player
  */
  public Player (String name, char mark) {
    this.name = name;
    this.mark = mark;
  }
  /**
  * Function that allows users to play the game
  *   1) Player x makes a move
  *   2) Is board full or has player x or player o won
  *   3) Displays board
  *   4) Player o makes a move
  *   5) Is board full or has player x or player o won
  *   6) Displays board
  * Displays message depending on the outcome of the game (win or tie).
  */
  public void play(){
    while(true){
      this.makeMove();
      this.board.display();

      if(board.isFull() || board.xWins() || board.oWins())
        break;

      this.oponnent.makeMove();
      this.board.display();

      if(board.isFull() || board.xWins() || board.oWins())
        break;
    }

    if(board.xWins())
      System.out.println("GAME OVER, " + this.name + " is the winner!");
    else if(board.oWins())
      System.out.println("GAME OVER, " + this.oponnent.name + " is the winner!");
    else
      System.out.println("TIE GAME");
  }
  /**
  * Function that allows users to make a move
  * Scans the command line for specified row and column for a given mark
  */
  public void makeMove(){
    Scanner scan = new Scanner(System.in);
    System.out.print(name + " what row should your next " + mark + " be placed in : ");
    int row = scan.nextInt();

    System.out.print("\n" + name + " what column should your next " + mark + " be placed in : ");
    int col = scan.nextInt();

    board.addMark(row, col, mark);
  }
  /**
  * Function that sets the opponent for a given player
  */
  public void setOponnet(Player oponnent){
    this.oponnent = oponnent;
  }
  /**
  * Function that sets the tick-tac-toe game board
  */
  public void setBoard(Board theBoard){
    this.board = theBoard;
  }
}
