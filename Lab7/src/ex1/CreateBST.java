/**
 * 
 */
package ex1;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * Class CreateBST creates a new binary search tree from a new file
 * 
 * @author Karan Bengali
 * @version 1.0
 * @since March 21, 2017
 *
 */
public class CreateBST {
	/**
	 * Default constructor.
	 * 	1) Prompts user to enter input file name
	 * 	2) Reads the file line by line and enters the data into the binary search tree
	 * 
	 * @param bst Binary Search Tree
	 */
	public CreateBST(BinSearchTree bst){
		String path = JOptionPane.showInputDialog(null, "File Path:", "Input File Path",JOptionPane.PLAIN_MESSAGE);
		try{
			Scanner in = new Scanner(new FileInputStream(path));
			while(in.hasNextLine()){
				String record = in.nextLine();
				String [] line = record.trim().split(" +");
				if(line.length != 4){
					JOptionPane.showMessageDialog(null, "Incomplete record", "Cannot Generate Tree", JOptionPane.PLAIN_MESSAGE);
					return;
				}
				bst.insert(line[0], line[1], line[2], line[3]);
			}
			in.close();
		}catch(IOException e){
			JOptionPane.showMessageDialog(null, "File not found + " + e.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
