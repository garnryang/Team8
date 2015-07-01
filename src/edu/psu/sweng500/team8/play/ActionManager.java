package edu.psu.sweng500.team8.play;

import java.util.Stack;

import edu.psu.sweng500.team8.coreDataStructures.CellGrid;

public class ActionManager {

	/* We can create a properties file to store constant values 
	 * and make it available external to the compiled JAR
	 * so they can be adjusted on the fly. 
	 * Or we can still include it in the JAR but as a separate file
	 * so we can change it through the development process.  */
	private static final int QUEUE_SIZE_LIMIT = 10;
	
	private Stack<SudokuAction> sudokuActionQueue;
	private Stack<SudokuAction> sudokuActionQueueForUndo;

	public ActionManager() {
		this.sudokuActionQueue = new Stack<SudokuAction>();
		this.sudokuActionQueue.setSize(QUEUE_SIZE_LIMIT);		
		this.sudokuActionQueueForUndo = new Stack<SudokuAction>();
		this.sudokuActionQueueForUndo.setSize(QUEUE_SIZE_LIMIT);
	}

	public void addAction(SudokuAction sudokuAction) {
		this.sudokuActionQueue.add(sudokuAction);
	}

	/**
	 * Undo last action such as entering a number, deleting a number or redo
	 * 
	 */
	public void doUndo(CellGrid currentCellGridFromBoard) {
		/*
		 * revert last action on the sudokuActionQueue, and put that action into
		 * sudokuActionQueueForUndo
		 */
		if (!sudokuActionQueue.isEmpty()) {

			SudokuAction lastAction = sudokuActionQueue.pop();
			CellGrid previousCellGrid = lastAction.getCellGrid();
			lastAction.setCellGrid(new CellGrid(currentCellGridFromBoard));
			sudokuActionQueueForUndo.add(lastAction);
			
			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}

	/**
	 * redo last action reverted back back undo
	 */
	public void doRedo(CellGrid currentCellGridFromBoard) {
		/* redo last action reverted back by undo */
		if (!sudokuActionQueueForUndo.isEmpty()) {

			SudokuAction lastActionUndone = sudokuActionQueueForUndo.pop();
			CellGrid previousCellGrid = lastActionUndone.getCellGrid();
			lastActionUndone.setCellGrid(new CellGrid(currentCellGridFromBoard));
			sudokuActionQueue.add(lastActionUndone);

			currentCellGridFromBoard.copyValues(previousCellGrid);
		}
	}
}
