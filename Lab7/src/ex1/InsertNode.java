/**
 * 
 */
package ex1;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Class InsertNode adds a node to the binary search tree with user input parameters
 * 
 * @author Karan Bengali
 * @version 1.0
 * @since March 21, 2017
 *
 */
public class InsertNode {
	/**
	 * studentId 		- Id of the new student
	 * studentFaculty 	- Faculty of the new student
	 * studentMajor 	- Major of the new student
	 * studentYear 		- Year of the new student
	 */
	JTextField studentId, studentFaculty, studentMajor, studentYear;
	/**
	 * textLabels 	- panel containing user entry text labels
	 * userEntry 	- panel containing user entry text fields
	 * myPanel 		- panel containing textLabel and userEntry panel
	 */
	JPanel myPanel, textLabels, userEntry;
	/**
	 * Default constructor for the InsertNode class. 
	 * Creates panels and labels and prompts user to enter data for the new student.
	 * 
	 * @param bst Binary Search Tree
	 */
	public InsertNode(BinSearchTree bst){
		studentId = new JTextField();
		studentFaculty = new JTextField();
		studentMajor = new JTextField();
		studentYear = new JTextField();

		myPanel = new JPanel(new BorderLayout(5,5));
		
		textLabels = new JPanel(new GridLayout(0,1,2,2));
		textLabels.add(new JLabel("Student ID", SwingConstants.RIGHT));
		textLabels.add(new JLabel("Faculty", SwingConstants.RIGHT));
		textLabels.add(new JLabel("Major", SwingConstants.RIGHT));
		textLabels.add(new JLabel("Year", SwingConstants.RIGHT));
		
		myPanel.add(textLabels, BorderLayout.WEST);
		
		userEntry = new JPanel(new GridLayout(0,1,2,2));
		userEntry.add(studentId);
		userEntry.add(studentFaculty);
		userEntry.add(studentMajor);
		userEntry.add(studentYear);
		
		myPanel.add(userEntry, BorderLayout.CENTER);
		
		int result = JOptionPane.showConfirmDialog(null, myPanel, "Add Student", JOptionPane.OK_CANCEL_OPTION);
		if( result == JOptionPane.OK_OPTION){
			bst.insert(studentId.getText(), studentFaculty.getText(), studentMajor.getText(), studentYear.getText());
		}
	}
}
