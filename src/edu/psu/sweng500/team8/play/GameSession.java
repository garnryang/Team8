package edu.psu.sweng500.team8.play;

import java.util.Set;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellGrid;
import edu.psu.sweng500.team8.coreDataStructures.Puzzle;

/**
 * 
 * If we are going to use Board class for operations, this class is not needed and we can move operations from this class to Board class
 * How are we going to response back to UI when redo/undo happens? Are we going to simply update board/grid and let UI call refresh?
 */
public class GameSession {
	
	private Board board = new Board();
	/* TODO - Do we want to have a player class? */
	// private Player player;
	private ActionManager actionManager;
	
	public GameSession(Puzzle puzzle) {
		this.board = new Board();
		board.Initialize(puzzle);
		
		actionManager = new ActionManager();
	}
	
	//DEPRECATED. TODO: Remove
	public GameSession(Board board) {
		this.board = board;
		actionManager = new ActionManager();
	}
	
	public Board getGameBoard() {
		return board;
	}
	
	
	/**
	 * Enter a number to given cellCoordinates
	 * If we do basic validation before calling this method, number can be an Integer
	 * If we do basic validation along with other more detailed validations on this method or forward,
	 * number should be a String.
	 * For different validation violations, how do we want to communicate to the caller?
	 * We can have, 
	 * invalid coordinate exception (which can only happen during the development where we put a number into hard-coded cell
	 * invalid value exception (if non-numeric value is entered or anything less than or greater than 1-9
	 *
	 * how do we want to communicate violation of sudoku rule?
	 * do we want to do that through a returned object?
	 * 
	 * If we want to use return object, we maybe able to use Enum to show previously defined exception cases.
	 * 
	 *
	 * @param cell
	 * @param number
	 */
	public void enterNumber(Cell currentCell, int number) {
		
		/* keep track of the last action*/
		SudokuAction sudokuAction = new SudokuAction(new CellGrid(board.getCellGrid()));
		
		boolean isDelete = false;
		
		if (number == 0) {
			isDelete = true;			
		}
		
		if (isDelete) {
			currentCell.clearNumber();
		} else {
			currentCell.setNumber(number);	
		}
		
		/* FIXME - PencilMarkManager updating/wiping out PencilMark number matching entered number */
		if (!isDelete) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					Cell eachCell = this.getGameBoard().getCellGrid().getCell(i, j);
					enterPencilMark(eachCell, number, false);
				}
			}			
		}
		
		actionManager.addAction(sudokuAction);
	}

	
	/**
	 * TODO implement
	 * @param currentCell
	 * @param number
	 */
	public void enterPencilMark(Cell currentCell, int number, boolean isEnter) {
		
		/* keep track of the last action*/
		SudokuAction sudokuAction = new SudokuAction(new CellGrid(board.getCellGrid()));
		
		Set<Integer> pencilMarks = currentCell.getPencilMarks();
		
		if (isEnter) {
			pencilMarks.add(number);
		} else {
			pencilMarks.remove(number);
		}
	}


	/**
	 * Should we just have a getter instead?
	 * @return
	 */
	public Board refresh() {
		return this.board;
	}
	
	public Object getHelp(HelpType helpType) {
		switch (helpType) {
		case RULES:
			return getRules();
		case ABOUT:
			return getAbout();
		case HINT:
			return getHint();
		}
		return null;
	}
	
	private Object getRules() {
		return null;
	}
	
	private Object getAbout() {
		return null;
	}
	
	private Object getHint() {
		return null;
	}

	/**
	 * saveFile can be name on a designated file
	 * or file's path/name  
	 * @param saveFile
	 */
	public void doSave(String saveFile) {
		
		/* TODO implement */
	}
	
	/**
	 * saveFile can be name on a designated file
	 * or file's path/name
	 * @param saveFile
	 */
	public void doLoad(String saveFile) {
		/* TODO implement */
	}

	public void doRedo() {
		actionManager.doRedo(board.getCellGrid());		
	}

	public void doUndo() {
		actionManager.doUndo(board.getCellGrid());		
	}
}
