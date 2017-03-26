/**
 * 
 */
package ex1;

import javax.swing.JOptionPane;

/**
 * Class FindStudent searches for a student in binary search tree.
 * 
 * @author Karan Bengali
 * @version 1.0
 * @since March 21, 2017
 *
 */
public class FindStudent {
	/**
	 *	Node containing student data 
	 */
	Node stdNode = null;
	/**
	 * Default constructor of class FindStudent. 
	 * 	1) Search nodes in the binary search tree for a node with a matching student id. 
	 * 	2) If found, the student profile will be displayed in a new window.
	 * 
	 * @param bst Binary Search Tree 
	 */
	public FindStudent(BinSearchTree bst){
		String id = JOptionPane.showInputDialog(null, "Student Id:", "Find Student",JOptionPane.PLAIN_MESSAGE);
		stdNode = bst.find(bst.root, id);
		if(stdNode == null){
			JOptionPane.showMessageDialog(null, "Sorry, could not find student", "Error", JOptionPane.PLAIN_MESSAGE);
		}else{
			JOptionPane.showMessageDialog(null, stdNode.toString(), "Query", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
