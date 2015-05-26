package edu.psu.sweng500.team8.poc.datastructure;

public class SudokuGame {

	final private static int TRIAL_LIMIT = 1000;
	private Board board;

	private boolean buildBasicBoard() {
		board = new Board();
		boolean keepGoing = true;
		int breakCounter = 0;
		boolean isComplete = false;
		
		while (keepGoing || breakCounter < TRIAL_LIMIT)
		try {
			board.populateWithSlightLogic();
			isComplete = true;
		} catch (Exception e) {
			breakCounter++;
		}
		
		return isComplete;
	}
	
	public SudokuGame() {
		
		boolean isComplete = buildBasicBoard();
		
		if (isComplete) {
			/* We have generated the board! */
			/**/
		} else {
			/* In highly unlikely scenario, we might not have generated a board within TRIAL_LIMIT 
			 * This is highly unlikely scenario, but theoretically possible limit -> 0 
			 * If we want to handle, it we can re-try or error out */
		} 
		
		/* TODO 
		 * 1. Once we build a basic board, we need to create a solve-able puzzle 
		 * 2. We need to solve it using an algorithm (DLX?) 
		 * 3. We can either empty different cells or re-generate board and do the process again */
	}

}
