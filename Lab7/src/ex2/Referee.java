package ex2;
/**
* The purpose of this class is to set up a tick-tac-toe game and start the game.
*
* @author Karan Bengali
* @version 2
* @since March 23, 2017
*/
public class Referee {
  /**
  * Public variable player
  */
  public Player player;
  /**
  * Public variable for the game board
  */
  public Board board;
  /**
  * Default constructor of the class Referee
  */
  public Referee (Player p){
	  this.player = p;
  }
  /**
  * Function runTheGame will run the game. Player x will start first
  */
  public void runTheGame(){
    player.gamePanel();
  }
}
