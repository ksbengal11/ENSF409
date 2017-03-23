package ex2;
import java.io.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Game implements Constants {
  /**
  *	The game board 
  */
  private Board theBoard;
  /**
  *	The referee
  */
  private Referee theRef;
  /**
  *	Gui that displays the game board
  */
  private TicTacToe theGUI;
  /**
  * Creates a board for the game
  */
  public Game( ) {
      theBoard  = new Board();
      theGUI = new TicTacToe();
	}
  /**
  * Calls the referee method runTheGame
  * @param r appointed referee for the game
  */
  public void appointReferee(Referee r){
	  theRef = r;
	  theRef.runTheGame();
  }
  /**
   * Creates new player
   * @param board
   * @param gui
   * @return
   */
  static public Player create_players(Board board, TicTacToe gui) {
	  Player result = new Player(board, gui);
	  return result;
  }
  /**
   * Deafult entry point for the program.
   * Creates players and appoints the referee.
   * @param args Command line arguments
   */
  public static void main(String[] args) throws IOException {
		Referee theRef;
		Player player;
		Game theGame = new Game();
		
		String p1Name = null, p2Name = null;
		
		JTextField Name1 = new JTextField();
		JTextField Name2 = new JTextField();
		
		Object[] message = {
				"Player 1 Name :", Name1,
				"Player 2 Name :", Name2,
		};
		
		while(p1Name == null || p2Name == null){
			int option = JOptionPane.showConfirmDialog(null, message, "New Game" ,JOptionPane.OK_CANCEL_OPTION);
			if(option == JOptionPane.OK_OPTION){
				p1Name = Name1.getText();
				p2Name = Name2.getText();
			}
		}
		
		theGame.theGUI.textArea.append("Welcome to the game\n");
		theGame.theGUI.textArea.append(p1Name + ", you have been assigned the character x\n");
		theGame.theGUI.textArea.append(p2Name + ", you have been assigned the character o\n");
		
		player = create_players(theGame.theBoard, theGame.theGUI);
		theRef = new Referee(player);
		theGame.appointReferee(theRef);
	}
}
