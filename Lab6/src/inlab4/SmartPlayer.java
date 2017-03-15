package inlab4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *	This class provides a tic-tac-toe AI that does the following:
 *		1. Check if it can win
 *		2. Check whether it can block opponent
 *	@author Karan Bengali
 *	@version 1.0
 *	@since February 2017
 */
class SmartPlayer extends BlockingPlayer {
	/**
	 * Default constructor for class SmartPlayer
	 * @param name - Player name
	 * @param mark - Player mark
	 * @param board - Gameboard
	 */
	public SmartPlayer(String name, char mark, Board board, BufferedReader in, PrintWriter out){
		super(name, mark, board, in, out);
	}
	/**
	 * Check if we can win the game, if not block player
	 * @throws IOException 
	 */
	protected void makeMove() throws IOException{
		for(int row = 0; row < 3; row++){
			for(int col = 0; col < 3; col++){
				if(board.getMark(row, col) == SPACE_CHAR && testForWinning(row,col)){
					board.addMark(row, col, mark);
					return;
				}
			}
		}
		super.makeMove();
	}
	/**
	 * Check if the current placement will result in a win
	 * @param row - gameboard row
	 * @param col - gameboard column
	 */
	public boolean testForWinning(int row, int col){
		boolean is_winning = true;
		// Check rows
		for(int i = 0; i < 3; i++){
			if((i != col) && (board.getMark(row,i)!= mark)){
				is_winning = false;
				break;
			}
		}
		if (is_winning) return true;
		
		// Check columns
		is_winning = true;
        for (int i = 0; i < 3; i++) {
            if ((i != row) && (board.getMark(i, col) != mark)) {
            	is_winning = false;
                break;
            }
        }
        if (is_winning) return true;
        
        // Check Diagonal
        if ((row + col) % 2 != 0)
            return false;

        switch (row) {
        case 0:
            if (col != 0 && board.getMark(2, 0) == mark && board.getMark(1, 1) == mark)
                return true;
            else if (board.getMark(2, 2) == mark && board.getMark(1, 1) == mark)
                return true;
            break;
        case 1:
            if ((board.getMark(0, 0) == mark && board.getMark(2, 2) == mark) ||
                (board.getMark(0, 2) == mark && board.getMark(2, 0) == mark))
                return true;
            break;
        case 2:
            if (col != 0 && board.getMark(0, 0) == mark && board.getMark(1, 1) == mark)
                return true;
            else if (board.getMark(0, 2) == mark && board.getMark(1, 1) == mark)
                return true;
            break;
        } 
        
		
		return false;
	}
}
