import java.io.*;
/**
* Provides data fields and methods to create a Java data-type, representing a
* game in a Java application
* The overall purpose of this class is to execute a tick-tac-toe game
*
* @author ENSF 409 Professor
* @version 1
* @since January 31, 2017
*/

public class Game implements Constants {
	/**
	* An object of type Board
	*/
	private Board theBoard;
	/**
	* An object of type Referee
	*/
	private Referee theRef;
	/**
	*	Default constructor of class Game
	* Class initializes a new board
	*/
  public Game( ) {
        theBoard  = new Board();
	}
	/**
	* Appoints a referee for the game
	* @param r object of type referee
	* @throws IOException.
	*/
  public void appointReferee(Referee r) throws IOException {
      theRef = r;
    	theRef.runTheGame();
  }
	/**
	* Main function of the game
	* Sets players, gameboard, and referee
	* @param args command line user arguments
	* @throws IOException.
	*/
	public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		Game theGame = new Game();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name = stdin.readLine();

		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		xPlayer = new Player(name, LETTER_X);
		xPlayer.setBoard(theGame.theBoard);

		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		oPlayer = new Player(name, LETTER_O);
		oPlayer.setBoard(theGame.theBoard);

		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);

    theGame.appointReferee(theRef);
	}
}
