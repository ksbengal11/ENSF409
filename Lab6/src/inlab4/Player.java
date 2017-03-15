package inlab4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
abstract class Player implements Constants{
  /**
  * Public variable name specifying the name of the player
  */
  protected String name;
  /**
  * Public variable board specifying the game board
  */
  protected Board board;
  /**
  * Public variable opponent specifying the opponent of the player
  */
  protected Player opponent;
  /**
  * Public variable mark specifying the selected mark of the player
  */
  protected char mark;
  protected BufferedReader stdIn;
  protected PrintWriter stdOut;
  
  /**
  * Default constructor of class Player. Assigns player name and associated
  * mark
  * @param name name of the player
  * @param mark specified mark of the player
  * @param boad specified game board
  */
  public Player (String name, char mark, Board b, BufferedReader in, PrintWriter out) {
    this.name = name;
    this.mark = mark;
    this.board = b;
    this.stdIn = in;
    this.stdOut = out;
  }
  /**
  * @return name of the player
  */
  protected String name(){
    return this.name;
  }
  /**
  * @return mark of the player
  */
  protected char mark(){
    return this.mark;
  }
  /**
  * Function that sets the opponent for a given player
  */
  protected void setOponnet(Player other){
    this.opponent = other;
  }
  /**
  * Initiate Game
  */
  abstract protected void play() throws IOException;
  /**
  * Prompt player to make a move
  */
  abstract protected void makeMove() throws IOException;
}
