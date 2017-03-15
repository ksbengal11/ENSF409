package inlab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

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
  public RandomPlayer(String name, char mark, Board board, BufferedReader in, PrintWriter out){
    super(name, mark, board, in, out);
  }
  protected void play() throws IOException{
    Player player = this;
    while (true){
      if(board.isFull()){
        player.stdOut.println("Nobody is the winner! Thanks for playing");
        player.opponent.stdOut.println("Nobody is the winner! Thanks for playing");
        board.display(player.stdOut);
        board.display(player.opponent.stdOut);
        break;
      }
      else if (board.xWins()){
          player.stdOut.println("Player x is the winner! Thanks for playing");
          player.opponent.stdOut.println("Player x is the winner! Thanks for playing");
          board.display(player.stdOut);
          board.display(player.opponent.stdOut);
        break;
      }
      else if (board.oWins()){
          player.stdOut.println("Player o is the winner! Thanks for playing");
          player.opponent.stdOut.println("Player o is the winner! Thanks for playing");
          board.display(player.stdOut);
          board.display(player.opponent.stdOut);
        break;
      }
      player.makeMove();
      player = player.opponent;
      board.display(player.stdOut);
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
  * Places a mark at a random index
  */
  @Override
  protected void makeMove() throws IOException{
    RandomGenerator r = new RandomGenerator();
    int row = 0; int col = 0;
    do{
      row = r.discrete(0,2);
      col = r.discrete(0,2);
    }while(board.getMark(row,col) != SPACE_CHAR);

    board.addMark(row, col, mark);
  }
}
