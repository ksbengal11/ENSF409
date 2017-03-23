package ex2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
/**
* Waits for each player to make a move and determines if it 
* leads to the game finishing in a draw or a win.
* 
* @author Karan Bengali
* @version 2
* @since March 23, 2017
*/
class Player implements Constants{
  /**
  * The game board
  */
  protected Board board;
  /**
  * Tic-tac-toe game board GUI
  */
  protected TicTacToe gui;
  /**
  * Number of marks on the gameboard
  */
  protected int markCount = 0;
  /**
  * Default constructor of class Player. Assigns game board and gui.
  * @param gui	tic-tac-toe gui
  * @param boad game board
  */
  public Player (Board b, TicTacToe gui) {
    this.board = b;
    this.gui = gui;
  }
  /**
  * Adds 9 buttons to the GUI and assign an action listener to each button
  */
  protected void gamePanel(){
	  for (int i = 0; i < gui.cellBtns.length; i++){
		  gui.cellBtns[i] = new MyButton();
		  gui.boardPanel.add(gui.cellBtns[i]);
	  }
	  gui.boardBox.add(gui.boardPanel);
	  gui.mainBox.add(gui.boardBox);
	  gui.mainBox.add(gui.messageBox);
	  gui.frame.add(gui.mainBox);
	  gui.frame.setVisible(true);
  }
  /**
   * Implements an actionPerformed method.  
   * @author Karan Bengali
   * @version 1.0
   * @since February 23, 2017
   */
  private class MyButton extends JButton implements ActionListener{
	/**
	 * Default serialized id
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable set to true if a board configuration results in a winner
	 */
	boolean win = false;
	/**
	 * Letter (X or O) to display on the pressed button
	 */
	String letter;
	/**
	 * Default constructor
	 */
	public MyButton(){
		super();
		letter = " ";
		setText(letter);
		addActionListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 	1) Wait for a button to be pressed
	 * 	2) Update the button with the appropriate mark (X or O)
	 * 	3) Increment the markCounter
	 * 	4) Check for winner
	 * 	5) Quit if the game ends in with a winner or in a draw
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if((markCount%2) == 0 && getText().charAt(0) == SPACE_CHAR && !win){
			letter = Character.toString(LETTER_X);
			System.out.println(letter + "\n" + markCount);
			markCount++;
		} else if ((markCount%2) == 1 && getText().charAt(0) == SPACE_CHAR && !win){
			letter = Character.toString(LETTER_O);
			System.out.println(letter + "\n" + markCount);
			markCount++;
		}
		setText(letter);
		win = board.checkwinner(gui);
		if(win){
			gui.textArea.append("Game over " + letter + " player is the winner\n");
		} else if (markCount == 9 && !win){
			gui.textArea.append("Draw Game. Thanks for playing\n");
		}
	}
  }
}
