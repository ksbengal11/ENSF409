package ex2;
//STUDENTS SHOULD ADD CLASS COMMENT, METHOD COMMENTS, FIELD COMMENTS
/**
* This class checks if existing board configuration results in a winner.
*
* @author Karan Bengali
* @version 2
* @since March 23, 2017
*/
public class Board implements Constants {
	/**
	 * Text configurations on buttons that result in:
	 * 	1) Horizontal wins,
	 * 	2) Vertical wins, and,
	 * 	3) Diagonal wins 
	 */
	private static int[][] winCombinations = new int[][] {
		{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, 
		{0, 3, 6}, {1, 4, 7}, {2, 5, 8}, 
		{0, 4, 8}, {2, 4, 6}			 
	};
	/**
	*	Default constructor
	*/
	public Board() {
		
	}
	/**
	 * Checks if the exisiting text configuration on the buttons results in a winner
	 * @param gui 	Tic-Tac-Toe GUI
	 * @return true if configuration results in a winner, false otherwise
	 */
	public boolean checkwinner(TicTacToe gui){
		for(int i = 0; i < 8; i++){
			if( gui.cellBtns[winCombinations[i][0]].getText().equals(gui.cellBtns[winCombinations[i][1]].getText()) && 
					gui.cellBtns[winCombinations[i][1]].getText().equals(gui.cellBtns[winCombinations[i][2]].getText()) && 
					gui.cellBtns[winCombinations[i][0]].getText() != " "){
					return true;
			}
		}
		return false;
	}
}
