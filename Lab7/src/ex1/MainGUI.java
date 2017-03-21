package ex1;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

/**
 * Class MainGUI displays windows
 *
 * @author Karan Bengali
 * @version 1.0
 * @since March 17, 2017
 *
 */
public class MainGUI {
	/**
	 * Frame containing the student application window
	 */
	JFrame frame;
	/**
	 * topPanel 	- panel containing the header
	 * centerPanel 	- panel containing text Area
	 * bottomPanel 	- panel containing user buttons
	 * mainPanels 	- panel containing the top, center, and bottom panel
	 */
	JPanel topPanel, centerPanel, bottomPanel, mainPanel;
	/**
	 * insert	- button for inserting new student data
	 * find		- button for finding a student
	 * browse	- button for displaying the generated binary search tree
	 * create	- button for creating a new binary search tree from file
	 */
	JButton insert, find, browse, create;
	/**
	 * Application label
	 */
	JLabel title;
	/**
	 * Display area for binary search tree
	 */
	JTextArea textArea = new JTextArea(15, 30);
	/**
	 * An instance of the binary search tree. Remains null if user does not press create button.
	 */
	BinSearchTree bst = null;
	/**
	 * Default constructor.
	 * Creates instances of:
	 * 			- Frame
	 * 			- Panels 	(top, bottom, center, main)
	 * 			- Buttons 	(insert, find, browse, create)
	 * Displays frame.
	 * 
	 * @param pixelWidth 	width of the frame
	 * @param pixelHeight 	height of the frame
	 */
	public MainGUI(int pixelWidth, int pixelHeight){
		frame = new JFrame("Main Window");
		mainPanel = new JPanel(new BorderLayout());
		
		bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel = new JPanel();
		centerPanel = new JPanel();
		
		title = new JLabel("An Application to Manage Student Records");
		topPanel.add(title);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		centerPanel.add(scrollPane);
		
		insert = new JButton("Insert");
		find = new JButton("Find");
		browse = new JButton("Browse");
		create = new JButton("Create Tree from File");
		
		bottomPanel.add(insert);
		bottomPanel.add(find);
		bottomPanel.add(browse);
		bottomPanel.add(create);
		
		mainPanel.add(bottomPanel, BorderLayout.SOUTH);
		mainPanel.add(topPanel, BorderLayout.NORTH);
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		frame.add(mainPanel);
		frame.setSize(pixelWidth, pixelHeight); 
		frame.setVisible(true);
	}
	/**
	 * Listens for action on buttons:
	 * 		- insert
	 * 		- find
	 * 		- browse
	 * 		- create
	 */
	private void addActionListeners(){
		insert.addActionListener(new ActionListener(){
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 * Creates a new instance of the class InsertNode if the insert button is pressed and updates the display area
			 */
			public void actionPerformed(ActionEvent evt){
				if(bst != null){
					InsertNode newNode = new InsertNode(bst);
					try {
						updateTextArea(bst, textArea);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Could not display binary search tree: " + e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "You must create a binary search tree from a file first!", "No Tree Found", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		find.addActionListener(new ActionListener(){
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 * Creates an instance of the FindStudent class if the find button is pressed
			 */
			public void actionPerformed(ActionEvent evt){
				if(bst != null){
					FindStudent std = new FindStudent(bst);
				}else{
					JOptionPane.showMessageDialog(null, "You must create a binary search tree from a file first!", "No Tree Found", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		browse.addActionListener(new ActionListener(){
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 * Displays the binary search tree in the display area if it has been successfully created
			 */
			public void actionPerformed(ActionEvent evt){
				if(bst != null){
					try {
						updateTextArea(bst, textArea);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(null, "Could not display binary search tree: " + e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(null, "You must create a binary search tree from a file first!", "No Tree Found", JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		create.addActionListener(new ActionListener(){
			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 * Creates an instance of CreateBST class if the create button is pressed
			 */
			public void actionPerformed(ActionEvent evt){
				bst = new BinSearchTree();
				CreateBST b = new CreateBST(bst);
			}
		});
	}
	/**
	 * Updates the display area
	 * @param b	Binary search tree
	 * @param t	Display text area
	 * @throws IOException
	 */
	private void updateTextArea(BinSearchTree b, JTextArea t) throws IOException{
		StringWriter buffer = new StringWriter();
		PrintWriter writer = new PrintWriter(buffer);
		b.print_tree(b.root, writer);
		t.setText(buffer.toString());
	}
	/**
	 * Default entry point. 
	 * Creates a new instance of the MainGUI class and activates the action listeners
	 * @param args
	 */
	public static void main(String[] args) {
		MainGUI newGUI = new MainGUI(450, 350);
		newGUI.addActionListeners();
	}
}
