package inlab4;
//Game.java
import java.io.*;

public class Game implements Constants {

	private Board theBoard;
	private Referee theRef;
	/**
	 * creates a board for the game
	 */
  public Game( ) {
      theBoard  = new Board();
	}
  /**
  * calls the referee method runTheGame
  * @param r refers to the appointed referee for the game
  * @throws IOException
  */
  public void appointReferee(Referee r) throws IOException {
      theRef = r;
  		theRef.runTheGame();
  }
	/*public static void main(String[] args) throws IOException {
		Referee theRef;
		Player xPlayer, oPlayer;
		BufferedReader stdin;
		Game theGame = new Game();
		stdin = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("\nPlease enter the name of the \'X\' player: ");
		String name= stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		xPlayer = create_player (name, LETTER_X, theGame.theBoard, stdin);

		System.out.print("\nPlease enter the name of the \'O\' player: ");
		name = stdin.readLine();
		while (name == null) {
			System.out.print("Please try again: ");
			name = stdin.readLine();
		}

		oPlayer = create_player (name, LETTER_O, theGame.theBoard, stdin);

		theRef = new Referee();
		theRef.setBoard(theGame.theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);

    theGame.appointReferee(theRef);
	}*/

	/**
	 * Creates the specified type of player indicated by the user.
	 *
	 * @param name player's name
	 * @param mark player's mark (X or O)
	 * @param board refers to the game board
	 * @param stdin refers to an input stream
	 * @return a newly created player
	 * @throws IOException
	 */
	static public Player create_player(String name, char mark, Board board,
			BufferedReader stdin, PrintWriter stdOut)throws IOException {
		// Get the player type.
		final int NUMBER_OF_TYPES = 4;
		stdOut.println("P What type of player is " + name +"?");
		stdOut.println("P 1. Human Player");
		stdOut.println("P 2. Random Player");
		stdOut.println("P 3. Blocking Player");
		stdOut.println("P 4. Smart Player");
		stdOut.println("I Please enter a number in the range 1-" + NUMBER_OF_TYPES + ":");
		int player_type = 0;

		String input;
		input= stdin.readLine();
		player_type = Integer.parseInt(input);
		while (player_type < 1 || player_type > NUMBER_OF_TYPES) {
			stdOut.print("I Please try again.\n");
			stdOut.print ("I Enter a number in the range 1-" +NUMBER_OF_TYPES + ": ");
			input= stdin.readLine();
			player_type = Integer.parseInt(input);
		}

		// Create a specific type of Player
		Player result = null;
		switch(player_type) {
			case 1:
				result = new HumanPlayer(name, mark, board, stdin, stdOut);
				break;
			case 2:
				result = new RandomPlayer(name, mark, board, stdin, stdOut);
				break;
			case 3:
				result = new BlockingPlayer(name, mark, board, stdin, stdOut);
				break;
			case 4:
				result = new SmartPlayer(name, mark, board, stdin, stdOut);
				break;
			default:
				System.out.print ( "\nP Default case in switch should not be reached.\n"
				+ "  Program terminated.\n");
				System.exit(0);
		}
		return result;
	}
	public void startGame(BufferedReader p1_socketIn, PrintWriter p1_socketOut, BufferedReader p2_socketIn, PrintWriter p2_socketOut) throws IOException {
		Player xPlayer, oPlayer;
		p1_socketOut.println("I Player \'X\', please enter your name.");
		String name = p1_socketIn.readLine();
		while(name == ""){
			p1_socketOut.println("I Please try again");
			name = p1_socketIn.readLine();
		}
		xPlayer = create_player(name, LETTER_X, theBoard, p1_socketIn, p1_socketOut);
		
		name = "";
		p2_socketOut.println("I Player \'O\', please enter your name.");
		name = p2_socketIn.readLine();
		while(name == ""){
			p1_socketOut.println("I Please try again");
			name = p1_socketIn.readLine();
		}
		oPlayer = create_player(name, LETTER_O, theBoard, p2_socketIn, p2_socketOut);
		
		System.out.println("Players created, appointing referee");
		theRef = new Referee();
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);
		theRef.setBoard(theBoard);
		
		theRef.runTheGame();
	}
	
}
