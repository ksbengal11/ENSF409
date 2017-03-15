package inlab4;
import java.util.Scanner;

/**
* Provides data fields and methods to create a Java data-type, representing a
* tick-tac-toe referee in a Java application.
* The overall purpose of this class is to set up a tick-tac-toe game, assign
* players, and start the game.
*
* @author Karan Bengali
* @version 1
* @since February 2, 2017
*/
public class Referee {
  /**
  * Public variable for player 'o'
  */
  public Player oPlayer;
  /**
  * Public variable for player 'x'
  */
  public Player xPlayer;
  /**
  * Public variable for the game board
  */
  public Board board;
  /**
  * Default constructor of the class Referee
  */
  public Referee (){

  }
  /**
  * Function runTheGame will run the game. Player x will start first
  */
  public void runTheGame(){
    xPlayer.setOponnet(oPlayer);
    oPlayer.setOponnet(xPlayer);

    xPlayer.play();

    System.out.println("Game has ended. Thank you for playing. Program will now terminate");
    System.exit(0);
    
  }
  /**
  * Function setBoard will initialize the game board and display it
  */
  public void setBoard(Board board){
    this.board = board;
    System.out.println("Referee has started the game ...");
    board.display();
  }
  /**
  * Function settoPlayer will initialize 'o' player and set 'xplayer' as its
  * Opponent
  * @param oPlayer player with the check mark 'o'
  */
  public void setoPlayer(Player oPlayer){
    this.oPlayer = oPlayer;
    oPlayer.setOponnet(this.xPlayer);
  }
  /**
  * Function settoPlayer will initialize 'x' player and set 'oplayer' as its
  * Opponent
  * @param xPlayer player with the check mark 'x'
  */
  public void setxPlayer(Player xPlayer){
    this.xPlayer = xPlayer;
    xPlayer.setOponnet(this.oPlayer);
  }
}
