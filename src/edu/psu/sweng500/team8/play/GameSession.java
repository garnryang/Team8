package edu.psu.sweng500.team8.play;

import java.util.Stack;

import edu.psu.sweng500.team8.coreDataStructures.Board;
import edu.psu.sweng500.team8.coreDataStructures.Cell;
import edu.psu.sweng500.team8.coreDataStructures.CellCoordinates;

/**
 * 
 * If we are going to use Board class for operations, this class is not needed and we can move operations from this class to Board class
 * How are we going to response back to UI when redo/undo happens? Are we going to simply update board/grid and let UI call refresh?
 */
public class GameSession {
	
	private Board board;
	/* TODO - Do we want to have a player class? */
	// private Player player;
	
	private Stack<SudokuAction> sudokuActionQueue;
	private Stack<SudokuAction> sudokuActionQueueForUndo;
	
	public GameSession(Board board) {
		this.board = board;
		this.sudokuActionQueue = new Stack<SudokuAction>();
		this.sudokuActionQueueForUndo = new Stack<SudokuAction>();
	}
	
	/**
	 * Undo last action such as entering a number or redo
	 */
	public void doUndo() {
		/* revert last action on the sudokuActionQueue, and put that action into sudokuActionQueueForUndo */
		if (!sudokuActionQueue.isEmpty()) {
			SudokuAction lastAction = sudokuActionQueue.pop();
			sudokuActionQueueForUndo.add(lastAction);
			
			/* TODO - following is a sample implementation
			 * Actual board update to be implemented */			
//			Cell currentCell = board.getCell(lastAction.getCellCordinates().getRowIndex(), lastAction.getCellCordinates().getColumnIndex());
//			if (0 == lastAction.getPreviousValue()) {
//				currentCell.clearNumber();
//			} else {
//				currentCell.setNumber(lastAction.getPreviousValue());
//			}
		} 
	}
	
	/** 
	 * redo last action reverted back back undo  */
	public void doRedo() {
		/* redo last action reverted back by undo */
		if (!sudokuActionQueueForUndo.isEmpty()) {
			SudokuAction lastActionUndone = sudokuActionQueueForUndo.pop();
			sudokuActionQueue.add(lastActionUndone);
			
			/* TODO - following is a sample implementation
			 * Actual board update to be implemented */			
//			Cell currentCell = board.getCell(lastActionUndone.getCellCordinates().getRowIndex(), lastActionUndone.getCellCordinates().getColumnIndex());
//			if (0 == lastActionUndone.getNewValue()) {
//				currentCell.clearNumber();
//			} else {
//				currentCell.setNumber(lastActionUndone.getNewValue());
//			}
			

		}	
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
	 * @param cellCoordinates
	 * @param number
	 */
	public void enterNumber(CellCoordinates cellCoordinates, int number) {
		
		Cell currentCell = board.getCell(cellCoordinates.getRowIndex(), cellCoordinates.getColumnIndex());
		SudokuAction sudokuAction = new SudokuAction(cellCoordinates, currentCell.getNumber(), number);
		
		/* TODO Actual Board Update to be implemented */
		currentCell.setNumber(number);
		
		sudokuActionQueue.add(sudokuAction);
	}
	
	/**
	 * Should we just have a getter instead?
	 * @return
	 */
	public Board refresh() {
		return this.board;
	}
}
