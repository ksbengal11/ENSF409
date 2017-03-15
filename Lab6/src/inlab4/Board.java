package inlab4;

import java.io.PrintWriter;

//STUDENTS SHOULD ADD CLASS COMMENT, METHOD COMMENTS, FIELD COMMENTS
/**
* Provides data fields and methods to create a Java data-type, representing a
* board in a Java application.
* The overall purpose of this class is to create and populate a gameboard.
* The class also contains methods to check if existing board configuration will
* result in a winner
*
* @author ENSF 409 Professor
* @version 1
* @since January 31, 2017
*/

public class Board implements Constants {
	/**
	* A multi-dimensional array representing a game board
	*/
	private char theBoard[][];
	/**
	* Variable indicating number of counts on a game board
	*/
	private int markCount;
	/**
	*	Default constructor of class Board.
	* Constructs an object Board and creates a 3x3 array.
	* Initializes each cell with spaces.
	*/
	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	/**
	*	Returns markings in the selected row and column of gameboard.
	*	@param row board row.
	* @param col board column.
	*/
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}
	/**
	*	Check if board is full.
	* @return true if there are 9 marks on the gameboard.
	*/
	public boolean isFull() {
		return markCount == 9;
	}
	/**
	*	Checks if a player using marking 'x' has won.
	* @return true if function checkWinner returns true.
	* Otherwise return false.
	*/
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}
	/**
	*	Checks if a player using marking 'o' has won.
	* @return true if function checkWinner returns true.
	* Otherwise return false.
	*/
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}
	/**
	*	Display markings on board.
	* Add hypens and spaces where necessary.
	*/
	public void display(PrintWriter outStream) {
		displayColumnHeaders(outStream);
		addHyphens(outStream);
		for (int row = 0; row < 3; row++) {
			addSpaces(outStream);
			outStream.print("P    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				outStream.print("|  " + getMark(row, col) + "  ");
			outStream.println("|");
			addSpaces(outStream);
			addHyphens(outStream);
		}
	}
	/**
	*	Add user specified marking in selected row and coloumn.
	* @param row selected board row.
	* @param col selected board column.
	* @param mark specified marking ('x' or 'o').
	*/
	public void addMark(int row, int col, char mark) {
		theBoard[row][col] = mark;
		markCount++;
	}
	/**
	*	Clears marking on the board and resets the mark count to zero.
	*/
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}
	/**
	*	Check if new board configuration results in a winner.
	* @param mark specified marking ('x' or 'o').
	* @return 1 if a winner can be declared. Otherwise return 0.
	*/
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	/**
	*	Display column headers on the board.
	*/
	void displayColumnHeaders(PrintWriter outStream) {
		outStream.print("P          ");
		for (int j = 0; j < 3; j++)
			outStream.print("|col " + j);
		outStream.println();
	}
	/**
	*	Add hyphens on the gameboard.
	*/
	void addHyphens(PrintWriter outStream) {
		outStream.print("P          ");
		for (int j = 0; j < 3; j++)
			outStream.print("+-----");
		outStream.println("+");
	}
	/**
	*	Add spaces on the gameboard.
	*/
	void addSpaces(PrintWriter outStream) {
		outStream.print("P          ");
		for (int j = 0; j < 3; j++)
			outStream.print("|     ");
		outStream.println("|");
	}
}
