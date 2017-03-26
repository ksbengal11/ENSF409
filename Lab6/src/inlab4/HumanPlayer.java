package inlab4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
/**
* Provides methods to gather from input inorder to play a game 
* of tick-tac-toe
* @author Karan Bengali
* @version 2.0
* @since March 15, 2017
*/
public class HumanPlayer extends Player{
  /**
  * Default constructor of class HumanPlayer
  * @param name player name
  * @param mark of the player
  * @param board game board
  * @param in input communication stream
  * @param out output communication stream
  */
  public HumanPlayer(String name, char mark, Board board, 
		  BufferedReader in, PrintWriter out){
    super(name, mark, board, in, out);
  }
/**
 * Run the game. x Player will make the first move and then the 'o'
 * player will make their move. Continue until one wins or the board
 * is full
 */
protected void play() throws IOException{
    Player p = this;
    while (true){
      if(board.isFull()){
        p.stdOut.println("PrintNobody is the winner! Thanks "
        		+ "for playing");
        p.opponent.stdOut.println("PrintNobody is the winner! "
        		+ "Thanks for playing");
        break;
      }
      else if (board.xWins()){
        p.stdOut.println("PrintPlayer x is the winner! Thanks "
        		+ "for playing");
        p.opponent.stdOut.println("P Player x is the winner! "
        		+ "Thanks for playing");
        break;
      }
      else if (board.oWins()){
        p.stdOut.println("PrintPlayer o is the winner! Thanks "
        		+ "for playing");
        p.opponent.stdOut.println("PrintPlayer o is the winner! "
        		+ "Thanks for playing");
        break;
      }
      p.makeMove();
      p = p.opponent;
      board.display(p.stdOut);
    }
    stdOut.println("Quit");
    opponent.stdOut.println("Quit");
    
    stdOut.close();
    opponent.stdOut.close();
    
    try {
		stdIn.close();
		opponent.stdIn.close();
	} catch (IOException e) {
		e.printStackTrace();
	}
    
    System.exit(0);

  }
  /**
  * Prompt user to make move
 * @throws IOException 
  */
  @Override
  protected void makeMove() throws IOException {
    int row, col;
    String input = "" ;
    Player currentPlayer = this;
    while(true){
    	stdOut.printf("Input%s, what row should your next %c"
    			+ " be placed in?\n", currentPlayer.name, 
    			currentPlayer.mark);
    	input = stdIn.readLine();
    	row = Integer.parseInt(input);
    	stdOut.printf("Input%s, what col should your next %c"
    			+ " be placed in?\n", currentPlayer.name, 
    			currentPlayer.mark);
    	input = stdIn.readLine();
    	col = Integer.parseInt(input);

    	if(row < 0 || row > 2 || col < 0 || col > 2){
    		stdOut.println("PrintIndex out of range, please try again");
    		break;
    	}else if(board.getMark(row,col) == SPACE_CHAR){
    		board.addMark(row,col,mark);
    		break;
    	}else {
    		stdOut.println("PrintCoordinate (" + row + "," 
    	+ col + ") occupied. Please try again");
    	}
    }
  }
}
