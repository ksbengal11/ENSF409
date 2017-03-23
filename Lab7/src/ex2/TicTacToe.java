package ex2;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * Creates the GUI that will display a tic-tac-toe game board. 
 * 
 * @author Karan Bengali
 * @version 1.0
 * @since March 23, 2017
 */
public class TicTacToe {
	/**
	 * Frame that contains panels, buttons, etc.
	 */
	JFrame frame;
	/**
	 * messagePanel - contains the display window for communication
	 * boardPanel	- contains buttons for the gameboard
	 * mainPanel	- contains messagePanel and boardPanel
	 */
	JPanel mainPanel, messagePanel, boardPanel;
	/**
	 * messageBox	- contains messagePanel
	 * boardBox		- contains boardPanel
	 * mainBox		- conatins messageBox and boardBox
	 */
	Box mainBox, messageBox, boardBox;
	/**
	 * The title of our program
	 */
	JLabel title;
	/**
	 * Displays communication messages
	 */
	JTextArea textArea;
	/**
	 * Array of 9 buttons that represent 9 cells on a tic-tac-toe game board
	 */
	public JButton [] cellBtns = new JButton[9];
	/**
	 * Default constructor.
	 * 	1) Creates messageBox and boardBox
	 * 	2) Assigns appropriate layout to each
	 * 	3) Adds panels to each box
	 * 	4) Assigns a frame size
	 */
	public TicTacToe(){
		frame = new JFrame("Tic-Tac-Toe Game");
		
		mainBox 	= Box.createHorizontalBox();
		messageBox 	= Box.createVerticalBox();
		boardBox 	= Box.createVerticalBox();
		
		boardPanel 	= new JPanel(new GridLayout(3,3));
		
		messagePanel = new JPanel(new BorderLayout ());
		messagePanel.add(new JLabel ("Message Window:"), BorderLayout.NORTH);
		textArea = new JTextArea(5,15);
		messagePanel.add(textArea, BorderLayout.CENTER);
		
		messageBox.add(messagePanel);
		
		frame.setSize(400, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
